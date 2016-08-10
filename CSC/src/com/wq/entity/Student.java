package com.wq.entity;

import java.sql.Date;

public class Student {
	private long id;//编号
	private long courseId;//课程id
	private long classId;//班级id
	private String studentName;//学生姓名
	private char gender;//性别
	private String date;//出生日期
	private int age;//年龄
	private String className;//所在班级名称
	
	public long getClassId() {
		return classId;
	}
	public void setClassId(long classId) {
		this.classId = classId;
	}
	public long getCourseId() {
		return courseId;
	}
	public void setCourseId(long courseId) {
		this.courseId = courseId;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getStudentName() {
		return studentName;
	}
	public void setStudentName(String studentName) {
		this.studentName = studentName;
	}
	public char getGender() {
		return gender;
	}
	public void setGender(char gender) {
		this.gender = gender;
	}
	public String getDate() {
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getClassName() {
		return className;
	}
	public void setClassName(String className) {
		this.className = className;
	}
	@Override
	public String toString() {
		return "Student [age=" + age + ", classId=" + classId + ", className="
				+ className + ", courseId=" + courseId + ", date=" + date
				+ ", gender=" + gender + ", id=" + id + ", studentName="
				+ studentName + "]";
	}
}
