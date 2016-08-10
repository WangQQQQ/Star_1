package com.wq.daoImp;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import com.wq.dao.StudentDAO;
import com.wq.entity.Student;
import com.wq.utils.DBUtils;

public class StudentDAOImp implements StudentDAO {

	public void addStudent(Student student) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("insert into student_1 (student_name,gender,birthday,class_name) values(?,?,?,?)");
			if (!(student.getStudentName().equals("") && student.getDate()
					.equals(""))) {
				stmt.setString(1, student.getStudentName());
				stmt.setString(2, student.getGender() + "");
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				stmt.setDate(3, new java.sql.Date(format.parse(
						student.getDate()).getTime()));
				stmt.setString(4, student.getClassName());
				stmt.executeUpdate();

				stmt = con
						.prepareStatement("insert into student (class_id,student_name,gender,birthday,class_name) values(?,?,?,?,?)");
				stmt.setLong(1, student.getClassId());
				stmt.setString(2, student.getStudentName());
				stmt.setString(3, student.getGender() + "");
				stmt.setDate(4, new java.sql.Date(format.parse(
						student.getDate()).getTime()));
				stmt.setString(5, student.getClassName());
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

	public void deleteStudentById(long id) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String str = null;
		try {
			con = DBUtils.getConnection();
			stmt = con
			.prepareStatement("select student_name from student_1 where id = ?");
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			while (rs.next()) {
				str = rs.getString("student_name");
			}
			
			//删除student_1中的学生
			stmt = con.prepareStatement("delete from student_1 where id = ?");
			stmt.setLong(1, id);
			stmt.executeUpdate();
			//删除student中的学生
			stmt = con.prepareStatement("delete from student where student_name = ?");
			stmt.setString(1, str);
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

	public List<Student> findStudentMsgByPage(int page) throws Exception {

		Connection con = null;
		List<Student> list = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			list = new ArrayList<Student>();
			con = DBUtils.getConnection();
			stmt = null;
			stmt = con
					.prepareStatement("select id,student_name,gender,birthday,class_name from student_1 limit ?,?");
			stmt.setInt(1, page * 10);
			stmt.setInt(2, 10);
			rs = stmt.executeQuery();
			SimpleDateFormat format = null;
			while (rs.next()) {
				format = new SimpleDateFormat("yyyy");
				Student student = new Student();
				student.setId(rs.getLong("id"));
				student.setStudentName(rs.getString("student_name"));
				student.setGender(rs.getString("gender").charAt(0));
				String date = rs.getString("birthday");
				String now = format.format(new java.util.Date());
				int year = Integer.parseInt(date.substring(0, 4));
				student.setAge(Integer.parseInt(now) - year);
				student.setClassName(rs.getString("class_name"));
				list.add(student);
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

	public Student loadStudentById(long id) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("select student_name,gender,birthday,class_name from student_1 where id = ?");
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			Student student = null;
			while (rs.next()) {
				student = new Student();
				student.setId(id);
				student.setStudentName(rs.getString("student_name"));
				student.setGender(rs.getString("gender").charAt(0));
				student.setDate(rs.getString("birthday"));
				student.setClassName(rs.getString("class_name"));
			}
			return student;
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

	public void updateStudent(Student student) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("update student set student_name = ?,gender = ?,birthday = ?,class_name = ? where id = ?");
			stmt.setString(1, student.getStudentName());
			stmt.setString(2, student.getGender() + "");
			stmt.setString(3, student.getDate());
			stmt.setString(4, student.getClassName());
			stmt.setLong(5, student.getId());
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

	public List<String> findAllClassFromClass() throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<String> list = null;
		try {
			list = new ArrayList<String>();
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("select class_name from class group by class_name");
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("class_name"));
			}
			return list;
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

	public void addCourse() throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		List<String> list = null;
		try {
			list = new ArrayList<String>();
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("select class_name from class group by class_name");
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("class_name"));
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
	}

	public Student findStudentByName(String name) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Student student = new Student();
		try {
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("select distinct class_id,student_name,gender,birthday,class_name from student where student_name = ?");
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			while (rs.next()) {
				student.setClassId(rs.getLong("class_id"));
				student.setStudentName(rs.getString("student_name"));
				student.setGender(rs.getString("gender").charAt(0));
				student.setDate(rs.getString("birthday"));
				student.setClassName(rs.getString("class_name"));
			}
			return student;
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

	public Long findclassIdByClassName(String classname) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Long classid = null;
		try {
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("select id from class where class_name = ? ");
			stmt.setString(1, classname);
			rs = stmt.executeQuery();
			while (rs.next()) {
				classid = rs.getLong("id");
			}
			return classid;
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

	public Long findcourseIdByStuName(String name) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Long courseid = null;
		try {
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("select course_id from student where student_name = ?");
			stmt.setString(1, name);
			rs = stmt.executeQuery();
			while (rs.next()) {
				courseid = rs.getLong("course_id");
			}
			return courseid;
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

	public void updatecourseId(Long id, String name) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("update student set course_id = ? where student_name = ?");
			stmt.setLong(1, id);
			stmt.setString(2, name);
			stmt.executeUpdate();
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

	public Long findcourseIdByCourseName(String coursename) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		Long courseid = null;
		try {
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("select id from course where course_name = ?");
			stmt.setString(1, coursename);
			rs = stmt.executeQuery();
			while (rs.next()) {
				courseid = rs.getLong("id");
			}
			return courseid;
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

	public void addStudentHasCourse(Student student) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("insert into student (course_id,class_id,student_name,gender,birthday,class_name) values(?,?,?,?,?,?)");
			if (!(student.getStudentName().equals("") && student.getDate()
					.equals(""))) {
				stmt.setLong(1, student.getCourseId());
				stmt.setLong(2, student.getClassId());
				stmt.setString(3, student.getStudentName());
				stmt.setString(4, student.getGender() + "");
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				stmt.setDate(5, new java.sql.Date(format.parse(
						student.getDate()).getTime()));
				stmt.setString(6, student.getClassName());
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

	public String findStudentNameById(Long id) throws Exception {


		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		String name = null;
		try {
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("select student_name from student_1 where id =?");
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			while(rs.next()){
				name = rs.getString("student_name");
			}
			return name;
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

	public List<Student> findAllStudent() throws Exception {


		Connection con = null;
		List<Student> list = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			list = new ArrayList<Student>();
			con = DBUtils.getConnection();
			stmt = null;
			stmt = con
					.prepareStatement("select id,student_name,gender,birthday,class_name from student_1");
			rs = stmt.executeQuery();
			SimpleDateFormat format = null;
			while (rs.next()) {
				format = new SimpleDateFormat("yyyy");
				Student student = new Student();
				student.setId(rs.getLong("id"));
				student.setStudentName(rs.getString("student_name"));
				student.setGender(rs.getString("gender").charAt(0));
				String date = rs.getString("birthday");
				String now = format.format(new java.util.Date());
				int year = Integer.parseInt(date.substring(0, 4));
				student.setAge(Integer.parseInt(now) - year);
				student.setClassName(rs.getString("class_name"));
				list.add(student);
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
