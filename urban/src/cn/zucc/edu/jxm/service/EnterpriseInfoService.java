package cn.zucc.edu.jxm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zucc.edu.jxm.entities.EnterpriseInfo;
import cn.zucc.edu.jxm.entities.Users;
import cn.zucc.edu.jxm.repository.EnterpriseInfoRepository;

@Service
public class EnterpriseInfoService
{
    @Autowired
    private EnterpriseInfoRepository enterpriseInfoRepository;
    
    @Transactional
    public void save(EnterpriseInfo enterpriseInfo){
    	 enterpriseInfoRepository.saveAndFlush(enterpriseInfo);
    }
    @Transactional(readOnly=true)
    public Page<EnterpriseInfo> getPage(int pageNo, int pageSize){
        PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
        return enterpriseInfoRepository.findAll(pageable);
    }
    @Transactional(readOnly=true)
    public Page<EnterpriseInfo> getPageByUser(Users user,int pageNo, int pageSize){
        PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
        return enterpriseInfoRepository.findByUsers(user, pageable);
    }
    @Transactional(readOnly=true)
    public  List<EnterpriseInfo> findByUsers(Users user){
        return enterpriseInfoRepository.findByUsers(user);
    }
   
    @Transactional
    public void deleteEnterprise(Integer eId){
    	EnterpriseInfo enterpriseInfo=enterpriseInfoRepository.getByEId(eId);
    	enterpriseInfoRepository.delete(enterpriseInfo);
    }
    
    @Transactional(readOnly=true)
    public EnterpriseInfo getByEId(Integer eId){
    	return enterpriseInfoRepository.getByEId(eId);
    }
    @Transactional(readOnly=true)
    public EnterpriseInfo getByEName(String eName){
    	return enterpriseInfoRepository.getByEName(eName);
    }
    @Transactional(readOnly=true)
    public EnterpriseInfo getByEAddress(String eAddress){
    	return enterpriseInfoRepository.getByEAddress(eAddress);
    }
    @Transactional(readOnly=true)
    public Page<EnterpriseInfo> searchEnterpriseInfo(String eAddress,String eName,int pageNo, int pageSize){
    	PageRequest pageable = new PageRequest(pageNo - 1, pageSize);
    	return enterpriseInfoRepository.findByEAddressLikeOrENameLike(eAddress, eName, pageable);
    }
    
    @Transactional
    public List<EnterpriseInfo> findAllEnterprise(){
    	return enterpriseInfoRepository.findAll();
    }
}
