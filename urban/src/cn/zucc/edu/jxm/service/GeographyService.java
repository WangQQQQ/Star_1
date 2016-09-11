package cn.zucc.edu.jxm.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zucc.edu.jxm.analysis.StringSameCount;
import cn.zucc.edu.jxm.entities.GeographyInfo;
import cn.zucc.edu.jxm.entities.ProjectInfo;
import cn.zucc.edu.jxm.repository.GeographyRepository;
import cn.zucc.edu.jxm.repository.ProjectInfoRepository;

@Service
public class GeographyService
{
    @Autowired
    private GeographyRepository geographyRepository;

    @Autowired
    private ProjectInfoRepository projectInfoRepository;

    @Transactional
    public void save(GeographyInfo geographyInfo)
    {
        geographyRepository.saveAndFlush(geographyInfo);
    }

    @Transactional(readOnly = true)
    public Page<GeographyInfo> getPage(int pageNo, int pageSize)
    {
        PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
        return geographyRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public Page<GeographyInfo> searchGeographyInfo(String geographyAddress, String pId, int pageNo, int pageSize)
    {
        PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
        return geographyRepository.search(geographyAddress, pId, pageable);
    }

    @Transactional
    public void deleteFromProjectInfo(String pId)
    {
        ProjectInfo projectInfo = projectInfoRepository.getByPId(pId);
        GeographyInfo geographyInfo = geographyRepository.getByProjectInfo(projectInfo);
        geographyRepository.delete(geographyInfo);
    }

    @Transactional(readOnly = true)
    public GeographyInfo getModifyGeography(int gId)
    {
        return geographyRepository.getByGId(gId);
    }
    
    @Transactional(readOnly = true)
    public GeographyInfo getByProjectInfo(ProjectInfo projectInfo)
    {
        return geographyRepository.getByProjectInfo(projectInfo);
    }
    

    @Transactional(readOnly = true)
    public GeographyInfo getByGeographyAddress(String geographyAddress)
    {
        return geographyRepository.getByGeographyAddress(geographyAddress);
    }

    @Transactional(readOnly = true)
    public List<GeographyInfo> findAll()
    {
        return geographyRepository.findAll();
    }
    
   /* @Transactional(readOnly = true)
    public List<GeographyInfo> findAllBetweenTime(Date date1,Date date2)
    {
        return geographyRepository.findAllBetweenTime(date1, date2);
    }*/

    @Transactional(readOnly = true)
    public List<GeographyInfo> findAllByLngLat()
    {
        return geographyRepository.findAllByLngLat();
    }

    @Transactional(readOnly = true)
    public List<GeographyInfo> findAllByLngLat2(ProjectInfo projectInfo)
    {
        return geographyRepository.findAllByLngLat2(projectInfo);
    }
    
    @Transactional
    public StringSameCount CountRegion()
    {
        StringSameCount result = new StringSameCount();
        List<GeographyInfo> list=geographyRepository.findAll();
        for(int i=0;i<list.size();i++){
            String region=list.get(i).getGeographyRegion();
            result.hashInsert(region);
        }
        /*ProjectInfo projectInfo = projectInfoRepository.getByPId(simprojects.get(i).getKey());
        EnterpriseInfo enterpriseInfo = enterpriseInfoRepository.getByEId(projectInfo.getEnterpriseInfo().geteId());
        String name = enterpriseInfo.geteName();
        result.hashInsert(name);*/
        return result;
    }
    @Transactional
    public StringSameCount CountRegionByTime(Date date1,Date date2)
    {
        StringSameCount result = new StringSameCount();
        List<GeographyInfo> list=geographyRepository.findAllBetweenTime(date1, date2);
        for(int i=0;i<list.size();i++){
            String region=list.get(i).getGeographyRegion();
            result.hashInsert(region);
        }
        return result;
    }
    
}
