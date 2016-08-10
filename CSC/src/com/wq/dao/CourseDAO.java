package com.wq.dao;

import java.util.List;

import com.wq.entity.Course;

public interface CourseDAO {
	public List<Course> findCourseMsgByPage(int page) throws Exception;

	public List<Course> findCourse() throws Exception;
	
	public void deleteCourseById(long id) throws Exception;

	public Course loadCourseById(long id) throws Exception;

	public void updateCourse(Course course) throws Exception;

	public void addCourse(Course course) throws Exception;

	public List<String> findAllCourse() throws Exception;
}
