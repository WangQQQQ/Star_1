package com.wq.dao;

import java.util.List;

import com.wq.entity.Course;
import com.wq.entity.Score;

public interface ScoreDAO {
	public List<Score> findScoreMsgByPage(int page) throws Exception;

	public List<Score> findAllScore() throws Exception;

	public void deleteScoreById(long id) throws Exception;

	public void deleteScoreByName(String name)throws Exception;
	
	public Score loadScoreById(long id) throws Exception;

	public void updateScore(Score score) throws Exception;

	public void addScore(Score score) throws Exception;

	public List<String> allStuNameFromStudent() throws Exception;

	public List<String> findAllCourseByStuname(String stuname) throws Exception;

	public List<String> findAllCourseFromCourse() throws Exception;
	
	public List<Score> selectScoreBy(String studentname,String coursename)throws Exception;
	
}
