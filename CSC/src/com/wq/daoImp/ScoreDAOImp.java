package com.wq.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wq.dao.ScoreDAO;
import com.wq.entity.Course;
import com.wq.entity.Score;
import com.wq.utils.DBUtils;

public class ScoreDAOImp implements ScoreDAO {

	public void addScore(Score score) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("insert into score (student_name,course_name,course_score) values(?,?,?)");
			stmt.setString(1, score.getStudentName());
			stmt.setString(2, score.getCourseName());
			stmt.setDouble(3, score.getCourseScore());
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

	public void deleteScoreById(long id) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBUtils.getConnection();
			stmt = con.prepareStatement("delete from score where id = ?");
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

	public List<Score> findScoreMsgByPage(int page) throws Exception {

		Connection con = null;
		List<Score> list = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			list = new ArrayList<Score>();
			con = DBUtils.getConnection();
			stmt = null;
			stmt = con.prepareStatement("select *from score limit ?,?");
			stmt.setInt(1, page * 10);
			stmt.setInt(2, 10);
			rs = stmt.executeQuery();
			while (rs.next()) {
				Score score = new Score();
				score.setId(rs.getLong("id"));
				score.setStudentName(rs.getString("student_name"));
				score.setCourseName(rs.getString("course_name"));
				score.setCourseScore(rs.getDouble("course_score"));
				list.add(score);
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

	public Score loadScoreById(long id) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("select student_name,course_name,course_score from score where id = ?");
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			Score score = null;
			while (rs.next()) {
				score = new Score();
				score.setId(id);
				score.setCourseName(rs.getString("course_name"));
				score.setStudentName(rs.getString("student_name"));
				score.setCourseScore(rs.getDouble("course_score"));
			}
			return score;
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

	public void updateScore(Score score) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("update score set course_score = ? where id = ?");
			stmt.setDouble(1, score.getCourseScore());
			stmt.setLong(2, score.getId());
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

	public List<String> allStuNameFromStudent() throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		List<String> list = null;
		ResultSet rs = null;
		try {
			list = new ArrayList<String>();
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("select distinct student_name from student");
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("student_name"));
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

	public List<String> findAllCourseByStuname(String stuname) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		List<String> list = null;
		ResultSet rs = null;
		try {
			list = new ArrayList<String>();
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("select course_name from course where id in(select course_id from student where student_name = ?);");
			rs = stmt.executeQuery();
			while (rs.next()) {
				list.add(rs.getString("course_name"));
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
		return list;
	}

	public List<String> findAllCourseFromCourse() throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		List<String> list = null;
		ResultSet rs = null;
		try {
			list = new ArrayList<String>();
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("select distinct course_name from course");
			rs = stmt.executeQuery();
			while (rs.next()) {
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

	public List<Score> selectScoreBy(String studentname, String coursename)
			throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		List<Score> list = null;
		ResultSet rs = null;
		try {
			list = new ArrayList<Score>();
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("select *from score where (0 = ? or student_name like ?) and (0 = ? or course_name like ?) ");
			if(studentname!=null){
				stmt.setInt(1, 1);
				stmt.setString(2, "%"+studentname+"%");
			}else{
				stmt.setInt(1, 0);
				stmt.setString(2, "%"+studentname+"%");
			}
			if(coursename!=null){
				stmt.setInt(3, 1);
				stmt.setString(4, "%"+coursename+"%");
			}else{
				stmt.setInt(3, 0);
				stmt.setString(4, "%"+coursename+"%");
			}
			rs = stmt.executeQuery();
			while (rs.next()) {
				Score score = new Score();
				score.setId(rs.getLong("id"));
				score.setStudentName(rs.getString("student_name"));
				score.setCourseName(rs.getString("course_name"));
				score.setCourseScore(rs.getDouble("course_score"));
				list.add(score);
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

	public void deleteScoreByName(String name) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBUtils.getConnection();
			stmt = con.prepareStatement("delete from score where student_name = ?");
			stmt.setString(1, name);
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

	public List<Score> findAllScore() throws Exception {
		Connection con = null;
		List<Score> list = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			list = new ArrayList<Score>();
			con = DBUtils.getConnection();
			stmt = null;
			stmt = con.prepareStatement("select *from score");
			rs = stmt.executeQuery();
			while (rs.next()) {
				Score score = new Score();
				score.setId(rs.getLong("id"));
				score.setStudentName(rs.getString("student_name"));
				score.setCourseName(rs.getString("course_name"));
				score.setCourseScore(rs.getDouble("course_score"));
				list.add(score);
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
