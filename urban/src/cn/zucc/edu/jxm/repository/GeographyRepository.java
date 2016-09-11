package cn.zucc.edu.jxm.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.QueryHint;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.QueryHints;

import cn.zucc.edu.jxm.entities.GeographyInfo;
import cn.zucc.edu.jxm.entities.ProjectInfo;

public interface GeographyRepository extends
		JpaRepository<GeographyInfo, Integer> {
	
	GeographyInfo getByProjectInfo(ProjectInfo projectInfo);

	@QueryHints({ @QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE, value = "true") })
	@Query("FROM GeographyInfo g where g.geographyAddress Like %?1% OR g.projectInfo.pId Like %?2%")
	Page<GeographyInfo> search(String geographyAddress, String pId,
			Pageable pageRequest);
	
	GeographyInfo getByGId(Integer gId);
	
	GeographyInfo getByGeographyAddress(String geographyAddress);
	
	@QueryHints({ @QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE, value = "true") })
    @Query("select lng,lat,projectInfo.projectName,projectInfo.enterpriseInfo.eName,projectInfo.totalInvestment,projectInfo.projectPeriod,projectInfo.buildTotalArea,projectInfo.constructTime FROM GeographyInfo")
	List<GeographyInfo> findAllByLngLat();
	
	@QueryHints({ @QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE, value = "true") })
    @Query("select lng,lat,projectInfo.projectName,projectInfo.enterpriseInfo.eName,projectInfo.totalInvestment,projectInfo.projectPeriod,projectInfo.buildTotalArea,projectInfo.constructTime FROM GeographyInfo where projectInfo=?1")
	List<GeographyInfo> findAllByLngLat2(ProjectInfo projectInfo);
	
	@QueryHints({ @QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE, value = "true") })
    @Query("FROM GeographyInfo where constructTime between ?1 and ?2")
	List<GeographyInfo> findAllBetweenTime(Date date1,Date date2);
	
	/*@QueryHints({ @QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE, value = "true") })
	@Query("FROM GeographyInfo g where g.geographyAddress Like %?1% OR g.projectInfo.pId Like %?2%")
	Page<GeographyInfo> search2(String geographyAddress, String pId,
			Pageable pageRequest);*/
	
	/*@QueryHints({ @QueryHint(name = org.hibernate.ejb.QueryHints.HINT_CACHEABLE, value = "true") })
    @Query("select count(1) from GeographyInfo group by geographyRegion")
	int countRegion();
	*/
	
	/*//设置 nativeQuery=true 即可以使用原生的 SQL 查询
    @Query(value="SELECT * FROM  Geography_Info", nativeQuery=true)
    List<GeographyInfo> findAllByTest2();*/

}
