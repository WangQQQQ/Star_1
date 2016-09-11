package cn.zucc.edu.jxm.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import cn.zucc.edu.jxm.entities.ProjectStatus;


public interface ProjectStatusRepository extends JpaRepository<ProjectStatus, Integer>{
	
    ProjectStatus findBySId(Integer sId);

}
