package cn.zucc.edu.jxm.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.zucc.edu.jxm.entities.ProjectStatus;
import cn.zucc.edu.jxm.repository.ProjectStatusRepository;

@Service
public class ProjectStatusService {
	
	@Autowired
	private ProjectStatusRepository projectStatusRepository; 
	
	@Transactional(readOnly = true)
	public List<ProjectStatus> findAll(){
		return projectStatusRepository.findAll();
	}

}
