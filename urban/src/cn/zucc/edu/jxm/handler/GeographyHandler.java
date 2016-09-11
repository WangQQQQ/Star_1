package cn.zucc.edu.jxm.handler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.general.DatasetUtilities;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.zucc.edu.jxm.analysis.StringSameCount;
import cn.zucc.edu.jxm.entities.EnterpriseInfo;
import cn.zucc.edu.jxm.entities.GeographyInfo;
import cn.zucc.edu.jxm.entities.ProjectInfo;
import cn.zucc.edu.jxm.entities.Users;
import cn.zucc.edu.jxm.service.EnterpriseInfoService;
import cn.zucc.edu.jxm.service.GeographyService;
import cn.zucc.edu.jxm.service.ProjectInfoService;
import cn.zucc.edu.jxm.service.UsersService;
import cn.zucc.edu.jxm.util.LatitudeUtils;

@Controller
public class GeographyHandler
{

    @Autowired
    private GeographyService geographyService;
    
    @Autowired
    private UsersService usersService;
    
    @Autowired
    private EnterpriseInfoService enterpriseInfoService;
    
    @Autowired
    private ProjectInfoService projectInfoService;

    @RequestMapping("/geographyList")
    public String list(@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr, Map<String, Object> map,HttpServletRequest request)
    {
        int pageNo = 1;
        try
        {
            // // 对 pageNo 的校验
            pageNo = Integer.parseInt(pageNoStr);
            if (pageNo < 1)
            {
                pageNo = 1;
            }
        }
        catch (Exception e)
        {
        }
        String userName=(String) request.getSession().getAttribute("currentUser");
        Users user=usersService.getByUserName(userName);
        
        Page<GeographyInfo> page = geographyService.getPage(pageNo, 8);
        map.put("page", page);
        return "geography/geography";
    }

    
    @RequestMapping("/searchGeography")
    public String searchProject(@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr,
                                Map<String, Object> map, @RequestParam(value = "searchName", required = false) String searchName)
    {
        int pageNo = 1;
        try
        {
            // 对 pageNo 的校验
            pageNo = Integer.parseInt(pageNoStr);
            if (pageNo < 1)
            {
                pageNo = 1;
            }
        }
        catch (Exception e)
        {
        }
        Page<GeographyInfo> page = geographyService.searchGeographyInfo(searchName, searchName, pageNo, 8);
        map.put("page", page);
        return "geography/geography";
    }

    @RequestMapping(value = "/modifyGeography/{id}", method = RequestMethod.GET)
    public String modifyGeography(@PathVariable("id") int gId, Map<String, Object> map)
    {
        GeographyInfo geographyInfo = geographyService.getModifyGeography(gId);
        map.put("modifyGeography", geographyInfo);
        return "geography/modifyGeography";
    }

    @RequestMapping(value = "/updateGeography/{id}", method = RequestMethod.PUT)
    public String updateGeography(GeographyInfo geographyInfo)
    {

        String Address = geographyInfo.getGeographyAddress();
        Map<String, String> json = LatitudeUtils.getGeocoderLatitude(Address);
        geographyInfo.setLng(json.get("lng"));
        geographyInfo.setLat(json.get("lat"));
        geographyService.save(geographyInfo);
        return "redirect:/geographyList";
    }

    @ResponseBody
    @RequestMapping(value = "/ajaxValidateNewG", method = RequestMethod.POST)
    public String ajaxValidateNewG(@RequestParam(value = "geographyAddress", required = true) String geographyAddress)
    {
        GeographyInfo geographyInfo = geographyService.getByGeographyAddress(geographyAddress);
        if (geographyInfo == null)
            return "0";
        else
            return "1";
    }

    @RequestMapping(value = "/baiduMap", method = RequestMethod.GET)
    public String goToBaiduMap(HttpServletRequest request)
    {
        String userName=(String) request.getSession().getAttribute("currentUser");
        Users user=usersService.getByUserName(userName);
        if (user.getUserLevel().equals("管理员"))
        {
            return "geography/baiduMap";
        }else{
            return "geography/userBaiduMap";
        }
    }
    
    @RequestMapping(value = "/loadMap", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> loadMap() {
        Map<String, Object> map = new HashMap<>();
        map.put("list", geographyService.findAllByLngLat());
        return map;
    }
    
    @RequestMapping(value = "/loadMap2", method = RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> loadMap2(HttpServletRequest request) {
    	Map<String, Object> map = new HashMap<>();
        String userName=(String) request.getSession().getAttribute("currentUser");
        Users user=usersService.getByUserName(userName);
        List<EnterpriseInfo> list=enterpriseInfoService.findByUsers(user);
        List result = new ArrayList<>();
        for(int i=0;i<list.size();i++){
            List<ProjectInfo> list2=projectInfoService.findByEnterpriseInfo(list.get(i));
            for(int j=0;j<list2.size();j++)
            {
                result.add(geographyService.findAllByLngLat2(list2.get(j)).get(0));
            }
        }
      /*  for(int i=0;i<result.size();i++){
            System.out.println(result+","+result.get(i).toString());
        }*/
        map.put("list", result);
        return map;
    }
    
    
   /* @RequestMapping(value = "/goToGeographyCount", method = RequestMethod.GET)
    public String goToGeographyCount()
    {
        return "analysisComplex/geographyCount";
    }*/
    @RequestMapping(value = "/geographyCount", method = RequestMethod.GET)
    public String countRegion(Map<String,Object> map,HttpServletRequest request) throws IOException
    {
        HashMap<String, Integer> hashMap =  new HashMap<String, Integer>() ;
        StringSameCount countRegion=geographyService.CountRegion();
        HashMap regions = countRegion.getHashMap();
        Iterator iterator = regions.keySet().iterator();
        String temp="";
        while(iterator.hasNext()){
            temp=(String) iterator.next();
            hashMap.put(temp, (int) regions.get(temp));
        }
        map.put("hashMap", hashMap);
        
      //图表生成
        int size=hashMap.size();
        //double[][] data = new double[size][size];
        double[][] data = new double[size][1];
        String [] rowKeys = new String[size];
        //String []columnKeys=new String[size];
        Iterator iterator2 = regions.keySet().iterator();
        String temp2="";
        int i=0;
        while(iterator2.hasNext()){
        	temp2=(String) iterator2.next();
        	data[i][0]=(int) regions.get(temp2);
        	rowKeys[i]=temp2;
        	//columnKeys[i]=temp2;
        	i++;
        }
        int flag=1;
        request.setAttribute("flag",flag);//为了判断是否有数据
        String []columnKeys={"杭州"};
        CategoryDataset dataset=DatasetUtilities.createCategoryDataset(rowKeys,columnKeys,data);
        JFreeChart chart=ChartFactory.createBarChart("历年来杭州各区项目分布",null, "数量", dataset,
				PlotOrientation.VERTICAL, true, true, true);
        String fileName=ServletUtilities.saveChartAsPNG(chart, 700, 500, null,request.getSession());
        String chartURL=request.getContextPath() + "/DisplayChart?filename="+fileName;
        map.put("chartURL", chartURL);
        String userName=(String) request.getSession().getAttribute("currentUser");
        Users user=usersService.getByUserName(userName);
        if(user.getUserLevel().equals("管理员")){
        	 return "analysisComplex/geographyCount";
        }else{
        	return "analysisComplex/userGCount";
        }
       
    }
    
    @RequestMapping("/searchGCount")
    public String searchEnterprise(Map<String, Object> map,
			@RequestParam(value = "demo", required = false) String demo,
			@RequestParam(value = "demo2", required = false) String demo2,
			HttpServletRequest request) throws IOException, ParseException {
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	Date date1=sdf.parse(demo);
    	Date date2=sdf.parse(demo2);
    	HashMap<String, Integer> hashMap =  new HashMap<String, Integer>() ;
        StringSameCount countRegion=geographyService.CountRegionByTime(date1, date2);
        HashMap regions = countRegion.getHashMap();
        Iterator iterator = regions.keySet().iterator();
        String temp="";
        while(iterator.hasNext()){
            temp=(String) iterator.next();
            hashMap.put(temp, (int) regions.get(temp));
        }
        map.put("hashMap", hashMap);
        int flag=0;   //为了判断是否有数据
      //图表生成
        int size=hashMap.size();
        if(size>0){
        	flag=1;
        double[][] data = new double[size][1];
        String [] rowKeys = new String[size];
        Iterator iterator2 = regions.keySet().iterator();
        String temp2="";
        int i=0;
        while(iterator2.hasNext()){
        	temp2=(String) iterator2.next();
        	data[i][0]=(int) regions.get(temp2);
        	rowKeys[i]=temp2;
        	i++;
        }
        String []columnKeys={"杭州"};
        CategoryDataset dataset=DatasetUtilities.createCategoryDataset(rowKeys,columnKeys,data);
        JFreeChart chart=ChartFactory.createBarChart(demo+"~"+demo2+"：杭州各区项目分布",null, "数量", dataset,
				PlotOrientation.VERTICAL, true, true, true);
        String fileName=ServletUtilities.saveChartAsPNG(chart, 700, 500, null,request.getSession());
        String chartURL=request.getContextPath() + "/DisplayChart?filename="+fileName;
        map.put("chartURL", chartURL);
        }
        request.setAttribute("flag", flag); //为了判断是否有数据
        request.setAttribute("demo", demo); 
        request.setAttribute("demo2", demo2); 
        String userName=(String) request.getSession().getAttribute("currentUser");
        Users user=usersService.getByUserName(userName);
        if(user.getUserLevel().equals("管理员")){
        	return "analysisComplex/geographyCount";
        }else{
        	return "analysisComplex/userGCount";
        }
    }
    
    @RequestMapping(value = "/userGeographyList")
    public String userGeographyList(Map<String, Object> map,HttpServletRequest request) throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException{
        String userName=(String) request.getSession().getAttribute("currentUser");
        Users user=usersService.getByUserName(userName);
        List<EnterpriseInfo> list=enterpriseInfoService.findByUsers(user);
//        List<ProjectInfo> result = new ArrayList<ProjectInfo>();
        List<GeographyInfo> result = new ArrayList<GeographyInfo>();
        for(int i=0;i<list.size();i++){
            List<ProjectInfo> list2=projectInfoService.findByEnterpriseInfo(list.get(i));
            for(int j=0;j<list2.size();j++)
            {
                GeographyInfo g=geographyService.getByProjectInfo(list2.get(j));
                result.add(g);
            }
        }
        map.put("list", result);
        return "geography/userGeographyList";
        
    }
    
}
