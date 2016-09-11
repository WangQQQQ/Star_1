package cn.zucc.edu.jxm.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import cn.zucc.edu.jxm.entities.EnterpriseInfo;
import cn.zucc.edu.jxm.entities.Users;

public interface EnterpriseInfoRepository extends JpaRepository<EnterpriseInfo, Integer>
{
	EnterpriseInfo getByEId(Integer eId);
	EnterpriseInfo getByEName(String eName);
	EnterpriseInfo getByEAddress(String eAddress);
    
//	Page<EnterpriseInfo> findByEIdLikeOrENameLike(String eId,String eName, Pageable pageRequest);
	Page<EnterpriseInfo> findByEAddressLikeOrENameLike(String eAddress,String eName, Pageable pageRequest);
	Page<EnterpriseInfo> findByUsers(Users user, Pageable pageRequest);
	
	List<EnterpriseInfo> findByUsers(Users user);

}
