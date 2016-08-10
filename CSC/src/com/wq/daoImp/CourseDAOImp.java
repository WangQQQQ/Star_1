package com.wq.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wq.dao.CourseDAO;
import com.wq.entity.Course;
import com.wq.utils.DBUtils;

public class CourseDAOImp implements CourseDAO {

	public void addCourse(Course course) throws Exception {


		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("insert into course (course_name,teacher_name) values(?,?)");
			if (!(course.getCourseName().equals("") && course
					.getTeacherName().equals(""))) {
				stmt.setString(1, course.getCourseName());
				stmt.setString(2, course.getTeacherName());
				stmt.executeUpdate();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public void deleteCourseById(long id) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBUtils.getConnection();
			stmt = con.prepareStatement("delete from course where id = ?");
			stmt.setLong(1, id);
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	
	}

	public List<Course> findCourseMsgByPage(int page) throws Exception {

		Connection con = null;
		List<Course> list = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			list = new ArrayList<Course>();
			con = DBUtils.getConnection();
			stmt = null;
			stmt = con
					.prepareStatement("select t2.id,t2.course_name,t2.teacher_name,t1.num from (select course_id,count(course_id)num from student  group by course_id )t1 right join course t2 on t1.course_id = t2.id limit ?,?;");
			stmt.setInt(1, page * 10);
			stmt.setInt(2, 10);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Course course = new Course();
				course.setId(rs.getLong("id"));
				course.setCourseName(rs.getString("course_name"));
				course.setTeacherName(rs.getString("teacher_name"));
				course.setNum(rs.getInt("num"));
				list.add(course);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return list;
	}

	public Course loadCourseById(long id) throws Exception {


		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("select course_name,teacher_name from course where id = ?");
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			Course course = null;
			while (rs.next()) {
				course = new Course();
				course.setId(id);
				course.setCourseName(rs.getString("course_name"));
				course.setTeacherName(rs.getString("teacher_name"));
			}
			return course;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public void updateCourse(Course course) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("update course set course_name=?,teacher_name =? where id = ?");
			stmt.setString(1, course.getCourseName());
			stmt.setString(2, course.getTeacherName());
			stmt.setLong(3, course.getId());
			stmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public List<String> findAllCourse() throws Exception {


		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<String> list = null;
		try {
			list = new ArrayList<String>();
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("select course_name from course");
			rs = stmt.executeQuery();
			while(rs.next()){
				list.add(rs.getString("course_name"));
			}
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
	}

	public List<Course> findCourse() throws Exception {


		Connection con = null;
		List<Course> list = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			list = new ArrayList<Course>();
			con = DBUtils.getConnection();
			stmt = null;
			stmt = con
					.prepareStatement("select t2.id,t2.course_name,t2.teacher_name,t1.num from (select course_id,count(course_id)num from student  group by course_id )t1 right join course t2 on t1.course_id = t2.id;");
			rs = stmt.executeQuery();
			while (rs.next()) {
				Course course = new Course();
				course.setId(rs.getLong("id"));
				course.setCourseName(rs.getString("course_name"));
				course.setTeacherName(rs.getString("teacher_name"));
				course.setNum(rs.getInt("num"));
				list.add(course);
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw e;
		} finally {
			if (rs != null) {
				rs.close();
			}
			if (stmt != null) {
				stmt.close();
			}
			if (con != null) {
				con.close();
			}
		}
		return list;
	}

}
