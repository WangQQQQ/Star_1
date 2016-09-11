package cn.zucc.edu.jxm.handler;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.poi.openxml4j.exceptions.InvalidFormatException;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import org.jfree.chart.plot.PlotOrientation;
import org.jfree.chart.servlet.ServletUtilities;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.jfree.data.general.DefaultPieDataset;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import java.awt.Font;

import cn.zucc.edu.jxm.analysis.AnalysisUtil;
import cn.zucc.edu.jxm.analysis.ProjectCompare;
import cn.zucc.edu.jxm.analysis.StringSameCount;
import cn.zucc.edu.jxm.entities.EnterpriseInfo;
import cn.zucc.edu.jxm.entities.GeographyInfo;
import cn.zucc.edu.jxm.entities.ProjectInfo;
import cn.zucc.edu.jxm.entities.ProjectStatus;
import cn.zucc.edu.jxm.entities.Users;
import cn.zucc.edu.jxm.service.EnterpriseInfoService;
import cn.zucc.edu.jxm.service.GeographyService;
import cn.zucc.edu.jxm.service.ProjectInfoService;
import cn.zucc.edu.jxm.service.ProjectStatusService;
import cn.zucc.edu.jxm.service.UsersService;
import cn.zucc.edu.jxm.util.ExcelUtil;
import cn.zucc.edu.jxm.util.LatitudeUtils;

import java.text.NumberFormat;

@Controller
public class ProjectInfoHandler
{
    @Autowired
    private ProjectInfoService projectInfoService;

    @Autowired
    private EnterpriseInfoService enterpriseInfoService;

    @Autowired
    private GeographyService geographyService;

    @Autowired
    private ProjectStatusService projectStatusService;
    
    @Autowired
    private UsersService usersService;

    @RequestMapping(value = "/goToProject", method = RequestMethod.GET)
    public String backToMain()
    {
        return "project/project";
    }

    @RequestMapping("/downloadFile")
    public ResponseEntity<byte[]> testResponseEntity(HttpSession session) throws IOException
    {
        byte[] body = null;
        ServletContext servletContext = session.getServletContext();
        InputStream in = servletContext.getResourceAsStream("/files/templet.xls");
        body = new byte[in.available()];
        in.read(body);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "attachment;filename=templet.xls");

        HttpStatus statusCode = HttpStatus.OK;

        ResponseEntity<byte[]> response = new ResponseEntity<byte[]>(body, headers, statusCode);
        return response;
    }

    @RequestMapping(value = "/importExcelData", method = RequestMethod.POST)
    public String importOCRExcelData(HttpServletRequest request, HttpServletResponse response) throws FileNotFoundException, IOException,
                                                                                               InvalidFormatException
    {

        MultipartHttpServletRequest mulRequest = (MultipartHttpServletRequest) request;
        MultipartFile file = mulRequest.getFile("file");
        String filename = file.getOriginalFilename();

        if (filename == null || "".equals(filename))
        {
        }
        Workbook workbook = WorkbookFactory.create(file.getInputStream());
        Sheet hssfSheet = workbook.getSheetAt(0); // 示意访问sheet
        if (hssfSheet != null)
        {
            for (int rowNum = 1; rowNum <= hssfSheet.getLastRowNum(); rowNum++)
            {
                Row row = hssfSheet.getRow(rowNum);
                if (row == null)
                {
                    continue;
                }
                // 往建设信息表数据库插入excel表的数据

                ProjectInfo projectInfo = new ProjectInfo();
                String pId = ExcelUtil.formatCellInt(row.getCell(0)) + "";
                projectInfo.setpId(pId);
                EnterpriseInfo enterpriseInfo = new EnterpriseInfo();
                enterpriseInfo.seteId(ExcelUtil.formatCellInt(row.getCell(1)));
                projectInfo.setEnterpriseInfo(enterpriseInfo);
                projectInfo.setProjectName(ExcelUtil.formatCell(row.getCell(2)));
                projectInfo.setTotalInvestment(ExcelUtil.formatCellDouble(row.getCell(5)));
                projectInfo.setProjectPeriod(ExcelUtil.formatCellInt(row.getCell(6)));
                projectInfo.setBuildTotalArea(ExcelUtil.formatCellDouble(row.getCell(7)));
                projectInfo.setConstructTime(ExcelUtil.formatCellDate(row.getCell(8)));
                projectInfo.setMaxSpan(ExcelUtil.formatCellDouble(row.getCell(9)));
                ProjectStatus projectStatus = new ProjectStatus();
                projectStatus.setsId(1);
                projectInfo.setProjectStatus(projectStatus);
                projectInfo.setpStatus("正常");
                projectInfoService.save(projectInfo);

                // 往地理信息表数据库插入excel表的数据
                GeographyInfo geographyInfo = new GeographyInfo();
                String geographyAddress = ExcelUtil.formatCell(row.getCell(3));
                Map<String, String> json = LatitudeUtils.getGeocoderLatitude(geographyAddress);
                geographyInfo.setGeographyAddress(geographyAddress);
                geographyInfo.setProjectInfo(projectInfo);
                geographyInfo.setLng(json.get("lng"));
                geographyInfo.setLat(json.get("lat"));
                geographyInfo.setGeographyRegion(ExcelUtil.formatCell(row.getCell(4)));
                geographyInfo.setConstructTime(ExcelUtil.formatCellDate(row.getCell(8)));
                geographyInfo.setgStatus("项目开始");
                geographyService.save(geographyInfo);

            }
        }

        return "redirect:/projectList";
    }

    @RequestMapping("/projectList")
    public String list(@RequestParam(value = "pageNo", required = false, defaultValue = "1") String pageNoStr, Map<String, Object> map)
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
        Page<ProjectInfo> page = projectInfoService.getPage(pageNo, 8);
        map.put("page", page);
        return "project/projectList";
    }
    
    @RequestMapping("/searchProject")
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

        Page<ProjectInfo> page = projectInfoService.searchProjectInfo('%' + searchName + '%', '%' + searchName + '%', pageNo, 8);
        map.put("page", page);
        return "project/projectList";
    }

    @RequestMapping(value = "/deleteProject/{id}", method = RequestMethod.DELETE)
    public String deleteProject(@PathVariable("id") String pId)
    {
        // 删除项目信息时，同时删除地理信息表中对应的数据
        geographyService.deleteFromProjectInfo(pId);
        projectInfoService.deleteProjectInfo(pId);
        return "redirect:/projectList";
    }

    @RequestMapping(value = "/modifyProject/{id}", method = RequestMethod.GET)
    public String modifyProject(@PathVariable("id") String pId, Map<String, Object> map)
    {
        ProjectInfo projectInfo = projectInfoService.getModifyProjectInfo(pId);
        map.put("enterpriseList", enterpriseInfoService.findAllEnterprise());
        map.put("statusList", projectStatusService.findAll());
        map.put("modifyProject", projectInfo);
        return "project/modifyProject";
    }

    @RequestMapping(value = "/updateProject/{id}", method = RequestMethod.PUT)
    public String updateProject(ProjectInfo projectInfo)
    {
        projectInfoService.save(projectInfo);
        return "redirect:/projectList";
    }

    @ResponseBody
    @RequestMapping(value = "/ajaxValidateNewProjectName", method = RequestMethod.POST)
    public String validateProjectName(@RequestParam(value = "projectName", required = true) String projectName)
    {
        ProjectInfo projectInfo = projectInfoService.getByProjectName(projectName);
        if (projectInfo == null)
            return "0";
        else
            return "1";
    }

    @RequestMapping(value = "/testtest2", method = RequestMethod.POST)
    public String test(Map<String, Object> map)
    {
        List<ProjectCompare> projectCompare = projectInfoService.loadAllProjectCompare();
        System.out.println(projectCompare.get(0).geteName());
        map.put("projectCompareList", projectCompare);
        return "test2222";
    }

    @RequestMapping(value = "/analysis", method = RequestMethod.GET)
    public String input(Map<String, Object> map,HttpServletRequest request)
    {
    	String userName=(String) request.getSession().getAttribute("currentUser");
        Users user=usersService.getByUserName(userName);
        if(user.getUserLevel().equals("管理员")){
        map.put("analysisProCom", new ProjectCompare());
        return "analysis/analysisPro";
        }else{
        	map.put("analysisProCom", new ProjectCompare());
            return "analysis/userAnalysis";
        }
    }

    @RequestMapping(value = "/analysis2", method = RequestMethod.POST)
    public String Compare(@RequestParam(value = "projectName", required = false) String projectName,
                          @RequestParam(value = "totalInvestment", required = false) double totalInvestment,
                          @RequestParam(value = "projectPeriod", required = false) int projectPeriod,
                          @RequestParam(value = "buildTotalArea", required = false) double buildTotalArea,
                          @RequestParam(value = "constructTime", required = false) String constructTime,
                          @RequestParam(value = "maxSpan", required = false) double maxSpan,
                          @RequestParam(value = "geographyAddress", required = false) String geographyAddress,
                          @RequestParam(value = "geographyRegion", required = false) String geographyRegion,
                          @RequestParam(value = "percent1", required = false) double percent1, 
                          @RequestParam(value = "percent2", required = false) double percent2,
                          @RequestParam(value = "percent3", required = false) double percent3,
                          @RequestParam(value = "percent4", required = false) double percent4,
                          @RequestParam(value = "percent5", required = false) double percent5, 
                          @RequestParam(value = "percent6", required = false) double percent6,
                          @RequestParam(value = "percent7", required = false) double percent7,
                          @RequestParam(value = "percent8", required = false) double percent8,
                          Model model,
                          Map<String, Object> map, HttpServletRequest request) throws ParseException, IOException
    {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = formatter.parse(constructTime);
        // System.out.println(date);
        ProjectCompare example = new ProjectCompare();
        example.setProjectName(projectName);
        example.setTotalInvestment(totalInvestment);
        example.setProjectPeriod(projectPeriod);
        example.setBuildTotalArea(buildTotalArea);
        example.setConstructTime(date);
        example.setMaxSpan(maxSpan);
        example.setGeographyAddress(geographyAddress);
        example.setGeographyRegion(geographyRegion);
        example.setPercent1(percent1);
        example.setPercent2(percent2);
        example.setPercent3(percent3);
        example.setPercent4(percent4);
        example.setPercent5(percent5);
        example.setPercent6(percent6);
        example.setPercent7(percent7);
        example.setPercent8(percent8);

        map.put("example", example);
        map.put("constructTime", constructTime);

        List<ProjectCompare> projects = projectInfoService.loadAllProjectCompare();
        List<Map.Entry<String, BigDecimal>> similarProjects = new AnalysisUtil().similarProject(example, projects,percent1,percent2,percent3,percent4,percent5,percent6,percent7,percent8);

        List<ProjectCompare> listNeed = projectInfoService.loadNeedProject(similarProjects);
        map.put("listNeed", listNeed);

        // System.out.println(similarProjects+","+listNeed);
        // System.out.println(similarProjects.get(0).getKey()+","+similarProjects.get(0).getValue());
        StringSameCount needEnterprises = projectInfoService.loadNeedEnterprise(similarProjects);
        HashMap enterprises = needEnterprises.getHashMap();
        Iterator iterator = enterprises.keySet().iterator();
        String temp = "";
        String suggestion1 = "";
        String suggestion2 = "";
        int length = 0;
        int count = 0;
        while (iterator.hasNext())
        {
            temp = (String) iterator.next();
            if (length == 0)
            {
                suggestion1 = temp;
            }
            if (count < (int) enterprises.get(temp))
            {
                count = (int) enterprises.get(temp);
                suggestion2 = temp;
            }
            length++;
        }
        // System.out.println(suggestion1+","+suggestion2+","+enterprises);
        map.put("suggestion1", suggestion1);
        map.put("suggestion2", suggestion2);

        // 图表生成
        DefaultPieDataset dataset = new DefaultPieDataset();
        Iterator iterator2 = enterprises.keySet().iterator();
        String temp2 = "";
        while (iterator2.hasNext())
        {
            temp2 = (String) iterator2.next();
            dataset.setValue(temp2, (int) enterprises.get(temp2));
        }
        JFreeChart chart = ChartFactory.createPieChart("推荐建设单位", dataset, true, true, true);
        PiePlot pieplot = (PiePlot) chart.getPlot();
        pieplot.setLabelFont(new Font("宋体", 0, 11));
        // 设置饼图是圆的（true），还是椭圆的（false）；默认为true
        pieplot.setCircular(true);
        // 没有数据的时候显示的内容
        pieplot.setNoDataMessage("无数据显示");
        StandardPieSectionLabelGenerator standarPieIG = new StandardPieSectionLabelGenerator("{0}:(总：{1},占：{2})",
                                                                                             NumberFormat.getNumberInstance(),
                                                                                             NumberFormat.getPercentInstance());
        pieplot.setLabelGenerator(standarPieIG);
        String fileName = ServletUtilities.saveChartAsPNG(chart, 700, 500, null, request.getSession());
        String chartURL = request.getContextPath() + "/DisplayChart?filename=" + fileName;
        String userName=(String) request.getSession().getAttribute("currentUser");
        Users user=usersService.getByUserName(userName);
        map.put("chartURL", chartURL);
        if(user.getUserLevel().equals("管理员")){
        	 return "analysis/result";
        }else{
             return "analysis/userResult";
        }
    }

    @RequestMapping(value = "/statusCount", method = RequestMethod.GET)
    public String statusCount(Map<String, Object> map, HttpServletRequest request) throws IOException
    {
        HashMap<String, Integer> hashMap =  new HashMap<String, Integer>() ;
        StringSameCount statusCount=projectInfoService.countStatus();
        HashMap status = statusCount.getHashMap();
        Iterator iterator = status.keySet().iterator();
        String temp="";
        while(iterator.hasNext()){
            temp=(String) iterator.next();
            hashMap.put(temp, (int) status.get(temp));
        }
        map.put("hashMap", hashMap);
        
        //图表生成
        DefaultPieDataset dataset = new DefaultPieDataset();
        Iterator iterator2 = status.keySet().iterator();
        String temp2 = "";
        int count=0;
        int number=0;
        while (iterator2.hasNext())
        {
            temp2 = (String) iterator2.next();
            dataset.setValue(temp2, (int) status.get(temp2));
            if(temp2.equals("项目完工")){
                count=1;
                number=(int) status.get(temp2);
            }
        }
        JFreeChart chart = ChartFactory.createPieChart("目前建设工程状态", dataset, true, true, true);
        PiePlot pieplot = (PiePlot) chart.getPlot();
        pieplot.setLabelFont(new Font("宋体", 0, 11));
        // 设置饼图是圆的（true），还是椭圆的（false）；默认为true
        pieplot.setCircular(true);
        // 没有数据的时候显示的内容
        pieplot.setNoDataMessage("无数据显示");
        StandardPieSectionLabelGenerator standarPieIG = new StandardPieSectionLabelGenerator("{0}:(总：{1},占：{2})",
                                                                                             NumberFormat.getNumberInstance(),
                                                                                              NumberFormat.getPercentInstance());
        pieplot.setLabelGenerator(standarPieIG);  
        if(count==1){
            pieplot.setExplodePercent("项目完工",number); 
        }
        String fileName = ServletUtilities.saveChartAsPNG(chart, 700, 500, null, request.getSession());
        String chartURL2 = request.getContextPath() + "/DisplayChart?filename=" + fileName;
        map.put("chartURL2", chartURL2);
        String userName=(String) request.getSession().getAttribute("currentUser");
        Users user=usersService.getByUserName(userName);
        if(user.getUserLevel().equals("管理员")){
        	return "analysisComplex/statusCount";
        }else{
        	return "analysisComplex/userStatusCount";
        }
        
    }
    
    @RequestMapping(value = "/searchStatusCount")
    public String searchStatusCount(Map<String, Object> map, HttpServletRequest request,@RequestParam(value = "demo", required = false) String demo,
			@RequestParam(value = "demo2", required = false) String demo2) throws IOException, ParseException
    {
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
    	Date date1=sdf.parse(demo);
    	Date date2=sdf.parse(demo2);
        HashMap<String, Integer> hashMap =  new HashMap<String, Integer>() ;
        StringSameCount statusCount=projectInfoService.searchCountStatus(date1, date2);
        HashMap status = statusCount.getHashMap();
        Iterator iterator = status.keySet().iterator();
        String temp="";
        while(iterator.hasNext()){
            temp=(String) iterator.next();
            hashMap.put(temp, (int) status.get(temp));
        }
        map.put("hashMap", hashMap);
        
        //图表生成
        DefaultPieDataset dataset = new DefaultPieDataset();
        Iterator iterator2 = status.keySet().iterator();
        String temp2 = "";
        int count=0;
        int number=0;
        while (iterator2.hasNext())
        {
            temp2 = (String) iterator2.next();
            dataset.setValue(temp2, (int) status.get(temp2));
            if(temp2.equals("项目完工")){
                count=1;
                number=(int) status.get(temp2);
            }
        }
        JFreeChart chart = ChartFactory.createPieChart(demo+"~"+demo2+":目前建设工程状态", dataset, true, true, true);
        PiePlot pieplot = (PiePlot) chart.getPlot();
        pieplot.setLabelFont(new Font("宋体", 0, 11));
        // 设置饼图是圆的（true），还是椭圆的（false）；默认为true
        pieplot.setCircular(true);
        // 没有数据的时候显示的内容
        pieplot.setNoDataMessage("无数据显示");
        StandardPieSectionLabelGenerator standarPieIG = new StandardPieSectionLabelGenerator("{0}:(总：{1},占：{2})",
                                                                                             NumberFormat.getNumberInstance(),
                                                                                              NumberFormat.getPercentInstance());
        pieplot.setLabelGenerator(standarPieIG);  
        if(count==1){
            pieplot.setExplodePercent("项目完工",number); 
        }
        String fileName = ServletUtilities.saveChartAsPNG(chart, 700, 500, null, request.getSession());
        String chartURL2 = request.getContextPath() + "/DisplayChart?filename=" + fileName;
        map.put("chartURL2", chartURL2);
        String userName=(String) request.getSession().getAttribute("currentUser");
        Users user=usersService.getByUserName(userName);
        if(user.getUserLevel().equals("管理员")){
        	return "analysisComplex/statusCount";
        }else{
        	return "analysisComplex/userStatusCount";
        }
    }
    
    @RequestMapping(value = "/userProjectList")
    public String userProjectList(Map<String, Object> map,HttpServletRequest request) throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException{
        String userName=(String) request.getSession().getAttribute("currentUser");
        Users user=usersService.getByUserName(userName);
        List<EnterpriseInfo> list=enterpriseInfoService.findByUsers(user);
        List<ProjectInfo> result = new ArrayList<ProjectInfo>();
        for(int i=0;i<list.size();i++){
            List<ProjectInfo> list2=projectInfoService.findByEnterpriseInfo(list.get(i));
            for(int j=0;j<list2.size();j++)
            {
                result.add(list2.get(j));
            }
        }
        map.put("list", result);
        return "project/userProjectList";
        
    }
}
