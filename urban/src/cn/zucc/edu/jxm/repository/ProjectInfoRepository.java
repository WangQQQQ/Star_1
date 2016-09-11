package cn.zucc.edu.jxm.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import cn.zucc.edu.jxm.entities.EnterpriseInfo;
import cn.zucc.edu.jxm.entities.GeographyInfo;
import cn.zucc.edu.jxm.entities.ProjectInfo;
import cn.zucc.edu.jxm.entities.ProjectStatus;


public interface ProjectInfoRepository extends JpaRepository<ProjectInfo, Integer>{
	
	
	Page<ProjectInfo> findByProjectNameLikeOrPIdLike(String projectName,String pId, Pageable pageRequest);
	Page<ProjectInfo> findByProjectName(String projectName, Pageable pageRequest);
	
	ProjectInfo getByPId(String pId);
	ProjectInfo getByProjectName(String projectName);
	
/*	@QueryHints({ @QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE, value = "true") })
    @Query("select projectStatus.sName FROM ProjectInfo")
    List<ProjectStatus> findAllsName();*/
	
	@QueryHints({ @QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE, value = "true") })
	@Query("From ProjectInfo ")
	List<ProjectInfo> findAlltest();
	
	@QueryHints({ @QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE, value = "true") })
    @Query("FROM ProjectInfo where constructTime between ?1 and ?2")
	List<ProjectInfo> findAllBetweenTime(Date date1,Date date2);
	
	List<ProjectInfo> findByEnterpriseInfo(EnterpriseInfo enterpriseInfo);
/*	@QueryHints({ @QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE, value = "true") })
	@Query("From ProjectInfo p INNER JOIN GeographyInfo g on p.pId = g.pId INNER JOIN EnterpriseInfo e on p.eId = e.eId WHERE p.pId=?1")
	List<Object[]> result(String pId);*/
}
