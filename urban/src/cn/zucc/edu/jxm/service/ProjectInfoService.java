package cn.zucc.edu.jxm.service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zucc.edu.jxm.analysis.ProjectCompare;
import cn.zucc.edu.jxm.analysis.StringSameCount;
import cn.zucc.edu.jxm.entities.EnterpriseInfo;
import cn.zucc.edu.jxm.entities.GeographyInfo;
import cn.zucc.edu.jxm.entities.ProjectInfo;
import cn.zucc.edu.jxm.entities.ProjectStatus;
import cn.zucc.edu.jxm.repository.EnterpriseInfoRepository;
import cn.zucc.edu.jxm.repository.GeographyRepository;
import cn.zucc.edu.jxm.repository.ProjectInfoRepository;
import cn.zucc.edu.jxm.repository.ProjectStatusRepository;

@Service
public class ProjectInfoService {
	@Autowired
	private ProjectInfoRepository projectInfoRepository;
	
	@Autowired
	private GeographyRepository geographyRepository;
	
	@Autowired
	private EnterpriseInfoRepository enterpriseInfoRepository;
	
	@Autowired
    private ProjectStatusRepository projectStatusRepository;
    

	@Transactional
	public void save(ProjectInfo projectInfo) {
		projectInfoRepository.saveAndFlush(projectInfo);
	}

	@Transactional(readOnly = true)
	public Page<ProjectInfo> getPage(int pageNo, int pageSize) {
		PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
		return projectInfoRepository.findAll(pageable);
	}

	@Transactional(readOnly = true)
	public Page<ProjectInfo> searchProjectInfo(String projectName, String pId,
			int pageNo, int pageSize) {
		PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
		return projectInfoRepository.findByProjectNameLikeOrPIdLike(
				projectName, pId, pageable);
	}

	@Transactional
	public void deleteProjectInfo(String pId) {
		ProjectInfo projectInfo = projectInfoRepository.getByPId(pId);
		projectInfoRepository.delete(projectInfo);
	}

	@Transactional(readOnly = true)
	public ProjectInfo getModifyProjectInfo(String pId) {
		return projectInfoRepository.getByPId(pId);
	}

	@Transactional(readOnly = true)
	public ProjectInfo getByProjectName(String projectName) {
		return projectInfoRepository.getByProjectName(projectName);
	}

	@Transactional(readOnly = true)
	public List<ProjectInfo> findAll() {
		return projectInfoRepository.findAll();
	}
	
	@Transactional(readOnly = true)
    public List<ProjectInfo> findByEnterpriseInfo(EnterpriseInfo enterpriseInfo) {
        return projectInfoRepository.findByEnterpriseInfo(enterpriseInfo);
    }

	//查询全部建设项目
	@Transactional
	public List<ProjectCompare> loadAllProjectCompare() {
		List<ProjectCompare> result = new ArrayList<ProjectCompare>();
		List<ProjectInfo> projectInfos = projectInfoRepository.findAll();
		for (int i = 0; i < projectInfos.size(); i++) {
			ProjectCompare project = new ProjectCompare();
			project.setPid(projectInfos.get(i).getpId());
			project.setProjectName(projectInfos.get(i).getProjectName());
			project.setTotalInvestment(projectInfos.get(i).getTotalInvestment());
			project.setProjectPeriod(projectInfos.get(i).getProjectPeriod());
			project.setBuildTotalArea(projectInfos.get(i).getBuildTotalArea());
			project.setConstructTime(projectInfos.get(i).getConstructTime());
			project.setMaxSpan(projectInfos.get(i).getMaxSpan());
			
			GeographyInfo geographyInfo=geographyRepository.getByProjectInfo(projectInfos.get(i));
			project.setGid(geographyInfo.getgId());
			project.setGeographyAddress(geographyInfo.getGeographyAddress());
			project.setGeographyRegion(geographyInfo.getGeographyRegion());
			project.setLat(geographyInfo.getLat());
			project.setLng(geographyInfo.getLng());
			
			EnterpriseInfo enterpriseInfo= enterpriseInfoRepository.getByEId(projectInfos.get(0).getEnterpriseInfo().geteId());
			project.seteName(enterpriseInfo.geteName());
			result.add(project);
		}
		return result;
	}
	
	// 查询相似项目
	@Transactional
	public  List<ProjectCompare> loadNeedProject(List<Map.Entry<String, BigDecimal>> simprojects){
		List<ProjectCompare> result = new ArrayList<ProjectCompare>();
		if (simprojects != null) {
			for (int i = 0; i < 6; i++) {
				ProjectInfo projectInfo=projectInfoRepository.getByPId(simprojects.get(i).getKey());
				ProjectCompare project = new ProjectCompare();
				
				project.setValue(simprojects.get(i).getValue());
				project.setPid(projectInfo.getpId());
				project.setProjectName(projectInfo.getProjectName());
				project.setTotalInvestment(projectInfo.getTotalInvestment());
				project.setProjectPeriod(projectInfo.getProjectPeriod());
				project.setBuildTotalArea(projectInfo.getBuildTotalArea());
				project.setConstructTime(projectInfo.getConstructTime());
				project.setMaxSpan(projectInfo.getMaxSpan());
				
				GeographyInfo geographyInfo=geographyRepository.getByProjectInfo(projectInfo);
				project.setGid(geographyInfo.getgId());
				project.setGeographyAddress(geographyInfo.getGeographyAddress());
				project.setGeographyRegion(geographyInfo.getGeographyRegion());
				project.setLat(geographyInfo.getLat());
				project.setLng(geographyInfo.getLng());
				
				EnterpriseInfo enterpriseInfo= enterpriseInfoRepository.getByEId(projectInfo.getEnterpriseInfo().geteId());
				project.seteName(enterpriseInfo.geteName());
				result.add(project);
			}
		}
		
		return result;
	}
	
	@Transactional
	public StringSameCount loadNeedEnterprise(List<Map.Entry<String, BigDecimal>> simprojects){
		 StringSameCount result=new StringSameCount();
		 if (simprojects != null) {
				for (int i = 0; i < 6; i++) {
					ProjectInfo projectInfo=projectInfoRepository.getByPId(simprojects.get(i).getKey());
					EnterpriseInfo enterpriseInfo= enterpriseInfoRepository.getByEId(projectInfo.getEnterpriseInfo().geteId());
					String name =enterpriseInfo.geteName();
					result.hashInsert(name);
				}
		 }
		 return result;
	}
	
	@Transactional
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
        }
        return result;
    }
	@Transactional
    public StringSameCount searchCountStatus(Date date1,Date date2)
    {
	    StringSameCount result = new StringSameCount();
        List<ProjectInfo> list = projectInfoRepository.findAllBetweenTime(date1, date2);
        for (int i = 0; i < list.size(); i++)
        {
            int sId=list.get(i).getProjectStatus().getsId();
            ProjectStatus ps=projectStatusRepository.findBySId(sId);
            String region =ps.getsName();
            result.hashInsert(region);
        }
        return result;
    }
}
