package com.wq.dao;

import java.util.List;

import com.wq.entity.Student;

public interface StudentDAO {
	public List<Student> findStudentMsgByPage(int page) throws Exception;
	
	public List<Student> findAllStudent()throws Exception;

	public void deleteStudentById(long id) throws Exception;

	public Student loadStudentById(long id) throws Exception;

	public void updateStudent(Student student) throws Exception;

	public void addStudent(Student student) throws Exception;

	public void addStudentHasCourse(Student student) throws Exception;

	public List<String> findAllClassFromClass() throws Exception;

	public void addCourse() throws Exception;

	public Student findStudentByName(String name) throws Exception;

	public String findStudentNameById(Long id) throws Exception;

	public Long findclassIdByClassName(String classname) throws Exception;

	public Long findcourseIdByStuName(String name) throws Exception;

	public void updatecourseId(Long id, String name) throws Exception;

	public Long findcourseIdByCourseName(String coursename) throws Exception;
}
