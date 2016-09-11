package cn.zucc.edu.jxm.test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import cn.zucc.edu.jxm.analysis.StringSameCount;
import cn.zucc.edu.jxm.entities.EnterpriseInfo;
import cn.zucc.edu.jxm.entities.GeographyInfo;
import cn.zucc.edu.jxm.entities.ProjectInfo;
import cn.zucc.edu.jxm.entities.ProjectStatus;
import cn.zucc.edu.jxm.entities.Users;
import cn.zucc.edu.jxm.repository.EnterpriseInfoRepository;
import cn.zucc.edu.jxm.repository.GeographyRepository;
import cn.zucc.edu.jxm.repository.ProjectInfoRepository;
import cn.zucc.edu.jxm.repository.ProjectStatusRepository;
import cn.zucc.edu.jxm.repository.UsersRepository;

public class SSSPTest
{
    private ApplicationContext ctx = null;
    private UsersRepository usersRepository=null;
    private ProjectInfoRepository projectInfoRepository=null;
    private GeographyRepository geographyRepository=null;
    private EnterpriseInfoRepository enterpriseInfoRepository=null;
    private ProjectStatusRepository projectStatusRepository=null;
 /*   private ProjectCompareRepository projectCompareRepository=null;*/
    
    {
        ctx = new ClassPathXmlApplicationContext("applicationContext.xml");
        usersRepository = ctx.getBean(UsersRepository.class);
        projectInfoRepository=ctx.getBean(ProjectInfoRepository.class);
        geographyRepository=ctx.getBean(GeographyRepository.class);
        enterpriseInfoRepository=ctx.getBean(EnterpriseInfoRepository.class);
        projectStatusRepository=ctx.getBean(ProjectStatusRepository.class);
    }
    
    
    @Test
    public void testHelloWorldSpringData() throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException{
        System.out.println(usersRepository.getClass().getName());
        Users user = usersRepository.getByUserNameAndPassword("jxm", "jxm");
        System.out.println(user.getUserLevel());
    }
    
    @Test
    public void test2() throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException{
        List<Users> user = usersRepository.getByUserLevel("普通用户");
        System.out.println(user.size());
        for(int i=0;i<user.size();i++){
        	System.out.println(user.get(i).getUserName());
        }
    }
    
    @Test
    public void testMap(){
        Users user=usersRepository.getByUserName("jxm");
        List<EnterpriseInfo> list=enterpriseInfoRepository.findByUsers(user);
        List result = new ArrayList<>();
        for(int i=0;i<list.size();i++){
//        	System.out.println("企业:"+list.get(i).geteName());
        	 List<ProjectInfo> list2=projectInfoRepository.findByEnterpriseInfo(list.get(i));
        	 for(int j=0;j<list2.size();j++)
             {
        		 result.add(geographyRepository.findAllByLngLat2(list2.get(j)).get(0));
//        		 System.out.println("项目名称："+list2.get(j).getProjectName()+"项目ID"+list2.get(j).getpId());
//        		 geographyRepository.findAllByLngLat2(list2.get(j)).get(0);
//        		 System.out.println("地理信息:"+geographyRepository.findAllByLngLat2(list2.get(j)).get(0));
             }
        }
        for(int i=0;i<result.size();i++){
            System.out.println(result+","+result.get(i));
        }
        
    }
    
    @Test
    public void testMap2(){
    	List<GeographyInfo> g=geographyRepository.findAllByLngLat();
    	System.out.println(g.get(0).getGeographyAddress());
//    	for(int i=0;i<g.size();i++){
//    		System.out.println(g.get(i).getGeographyAddress());
//    	}
    }
    
    @Test
    public void testHello() throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException{
        List<ProjectInfo> projectInfos = projectInfoRepository.findAll();
        GeographyInfo geographyInfo=geographyRepository.getByProjectInfo(projectInfos.get(0));
        EnterpriseInfo enterpriseInfo= enterpriseInfoRepository.getByEId(projectInfos.get(0).getEnterpriseInfo().geteId()); 
        System.out.println(geographyInfo.getGeographyAddress()+"  "+geographyInfo.getLat()+" "+projectInfos.get(0).getEnterpriseInfo().geteId()+":"+enterpriseInfo.geteName());
    }
    
    @Test
    public void testSt() throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException{
        List<ProjectStatus> projectStatus = projectStatusRepository.findAll();
        for(int i=0;i<projectStatus.size();i++){
        	 System.out.println(projectStatus.get(i).getsName());
        }
    }
    
    @Test
    public void testPage() throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException{
    	PageRequest pageable = new PageRequest(5, 2);
    	Users user = usersRepository.getByUserNameAndPassword("jxm", "jxm");
//    	Page<EnterpriseInfo> enterpriseInfo = enterpriseInfoRepository.findByUsers(user, pageable);
    	List<EnterpriseInfo> list=enterpriseInfoRepository.findByUsers(user);
    	List<ProjectInfo> result = new ArrayList<ProjectInfo>();
    	for(int i=0;i<list.size();i++){
//    		System.out.println(i+":企业名称："+list.get(i).geteName());
    		List<ProjectInfo> list2=projectInfoRepository.findByEnterpriseInfo(list.get(i));
    		for(int j=0;j<list2.size();j++)
    		{
//    			System.out.println(j+":项目名称："+list2.get(j).getProjectName());
    			result.add(list2.get(j));
    			/*System.out.println(result.get(j).getProjectName());*/
    		}
    	}
    	Page<ProjectInfo> p;
    	for(int i=0;i<result.size();i++){
    		
    		 p=projectInfoRepository.findByProjectName(result.get(i).getProjectName(), pageable);
    		
    		System.out.println(result.get(i).getProjectName());
    	}
    	
    	
    }
    
    @Test
    public void testSName() throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException{
        List<ProjectInfo> projectInfo = projectInfoRepository.findAll();
        for(int i=0;i<projectInfo.size();i++){
             System.out.println(projectInfo.get(i).getProjectStatus().getsId());
        }
    }
    
    @Test
    public void testFindAllTime() throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException, ParseException{
    	SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");//小写的mm表示的是分钟  
    	String dstr="2016-4-4";  
    	Date date1=sdf.parse(dstr);  
    	Date date2=sdf.parse(dstr);
    	
    	List<GeographyInfo> geographyInfo = geographyRepository.findAllBetweenTime(date1, date2);
        for(int i=0;i<geographyInfo.size();i++){
             System.out.println(geographyInfo.get(i).getGeographyAddress());
        }
    }
    
    @Test
    public void testCount() {
        /*
        StringSameCount result = new StringSameCount();
        List<GeographyInfo> list=geographyRepository.findAll();
        for(int i=0;i<list.size();i++){
            String region=list.get(i).getGeographyRegion();
            result.hashInsert(region);
            System.out.println(region+","+result.getHashMap()+",");
        }*/
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>() ;
        StringSameCount countRegion=CountRegion();
        HashMap regions = countRegion.getHashMap();
        Iterator iterator = regions.keySet().iterator();
        String temp="";
        int count=0;
       /*
        *  StringSameCount needEnterprises=projectInfoService.loadNeedEnterprise(similarProjects);
            HashMap enterprises = needEnterprises.getHashMap(); 
        *DefaultPieDataset dataset = new DefaultPieDataset();
        Iterator iterator2 = enterprises.keySet().iterator();
        String temp2="";
        while(iterator2.hasNext()){
            temp2=(String) iterator2.next();
            dataset.setValue(temp2, (int) enterprises.get(temp2));
        }*/
        while(iterator.hasNext()){
            count++;
            temp=(String) iterator.next();
            /*System.out.println(temp);
            System.out.println("count="+count);*/
            //System.out.println(temp+","+(int) regions.get(temp));
            hashMap.put(temp, (int) regions.get(temp));
        }
        System.out.println("size:"+hashMap.size());
        int size=hashMap.size();
        int[][] data = new int[size][size];
        String [] rowKeys = new String[size];
        Iterator iterator2 = regions.keySet().iterator();
        String temp2="";
        int i=0;
        while(iterator2.hasNext()){
        	temp2=(String) iterator2.next();
        	data[i][0]=(int) regions.get(temp2);
        	rowKeys[i]=temp2;
        	System.out.println(temp2+":"+data[i][0]);
        	
        	i++;
        	
        //	System.out.println(temp2+":"+(int) regions.get(temp2));
        }
    }
    
    @Test
    public void testStatusCount() {
        HashMap<String, Integer> hashMap = new HashMap<String, Integer>() ;
        StringSameCount countRegion=countStatus();
        HashMap regions = countRegion.getHashMap();
        Iterator iterator = regions.keySet().iterator();
        String temp="";
        int count=0;
        while(iterator.hasNext()){
            count++;
            temp=(String) iterator.next();
            hashMap.put(temp, (int) regions.get(temp));
        }
        System.out.println("size:"+hashMap.size());
        Iterator iterator2 = regions.keySet().iterator();
        String temp2="";
        while(iterator2.hasNext()){
            temp2=(String) iterator2.next();
            
            System.out.println(temp2+":"+(int) regions.get(temp2));
        }
    }
    
    public StringSameCount CountRegion()
    {
        StringSameCount result = new StringSameCount();
        List<GeographyInfo> list=geographyRepository.findAll();
        for(int i=0;i<list.size();i++){
            String region=list.get(i).getGeographyRegion();
            result.hashInsert(region);
            System.out.println("region"+region);
        }
        /*ProjectInfo projectInfo = projectInfoRepository.getByPId(simprojects.get(i).getKey());
        EnterpriseInfo enterpriseInfo = enterpriseInfoRepository.getByEId(projectInfo.getEnterpriseInfo().geteId());
        String name = enterpriseInfo.geteName();
        result.hashInsert(name);*/
        //System.out.println(result);
        return result;
    }
    
    public StringSameCount countStatus()
    {
        StringSameCount result = new StringSameCount();
        List<ProjectInfo> list = projectInfoRepository.findAll();
        for (int i = 0; i < list.size(); i++)
        {
            int sId=list.get(i).getProjectStatus().getsId();
            ProjectStatus ps=projectStatusRepository.findBySId(sId);
            String region =ps.getsName();
            result.hashInsert(region);
            System.out.println("region"+region);
        }
        return result;
    }
    @Test
    public void testDataSource() throws SQLException {
        DataSource dataSource = ctx.getBean(DataSource.class);
        System.out.println(dataSource.getConnection());
    }
    
    @Test
    public void testRepositorySecondLevelCache(){
        
//        List<UserLevel> userL=userLevelRepository.getAll();
//        userL=userLevelRepository.getAll();
    }
    
   /* @Test
    public void tes() throws FileNotFoundException, IOException, InstantiationException, IllegalAccessException{
        System.out.println(usersRepository.getClass().getName());
        
        List<ProjectCompare> projectCompare = projectCompareRepository.findAllTest("312000004");
        System.out.println(projectCompare);
    }*/
    
}
