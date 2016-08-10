package com.wq.daoImp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.wq.dao.ClassDAO;
import com.wq.entity.TbClass;
import com.wq.utils.DBUtils;

public class ClassDAOImp implements ClassDAO {

	public List<TbClass> findClassMsgByPage(int page) throws Exception {
		Connection con = null;
		List<TbClass> list = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			list = new ArrayList<TbClass>();
			con = DBUtils.getConnection();
			stmt = null;
			stmt = con
					.prepareStatement("select t2.id,t2.class_name,t2.class_teacher,t1.num from (select class_name,count(class_name)num from student_1  group by class_name )t1 right join class t2 on t1.class_name = t2.class_name limit ?,?");
			stmt.setInt(1, page * 10);
			stmt.setInt(2, 10);
			rs = stmt.executeQuery();
			while (rs.next()) {
				TbClass tbclass = new TbClass();
				tbclass.setId(rs.getLong("id"));
				tbclass.setClassName(rs.getString("class_name"));
				tbclass.setClassTeacher(rs.getString("class_teacher"));
				tbclass.setStudentNum(rs.getInt("num"));
				list.add(tbclass);
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

	public void deleteClassById(int id) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBUtils.getConnection();
			stmt = con.prepareStatement("delete from class where id = ?");
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

	public TbClass loadClassById(long id) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("select class_name,class_teacher from class where id = ?");
			stmt.setLong(1, id);
			rs = stmt.executeQuery();
			TbClass tbclass = null;
			while (rs.next()) {
				tbclass = new TbClass();
				tbclass.setId(id);
				tbclass.setClassName(rs.getString("class_name"));
				tbclass.setClassTeacher(rs.getString("class_teacher"));
			}
			return tbclass;
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

	public void updateClass(TbClass tbclass) throws Exception {
		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("update class set class_name=?,class_teacher=?where id = ?");
			stmt.setString(1, tbclass.getClassName());
			stmt.setString(2, tbclass.getClassTeacher());
			stmt.setLong(3, tbclass.getId());
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

	public void addClass(TbClass tbclass) throws Exception {

		Connection con = null;
		PreparedStatement stmt = null;
		try {
			con = DBUtils.getConnection();
			stmt = con
					.prepareStatement("insert into class (class_name,class_teacher) values(?,?)");
			if (!(tbclass.getClassName().equals("") && tbclass
					.getClassTeacher().equals(""))) {
				stmt.setString(1, tbclass.getClassName());
				stmt.setString(2, tbclass.getClassTeacher());
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

	public List<TbClass> findClass() throws Exception {

		Connection con = null;
		List<TbClass> list = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			list = new ArrayList<TbClass>();
			con = DBUtils.getConnection();
			stmt = null;
			stmt = con
					.prepareStatement("select t2.id,t2.class_name,t2.class_teacher,t1.num from (select class_name,count(class_name)num from student_1  group by class_name )t1 right join class t2 on t1.class_name = t2.class_name");
			rs = stmt.executeQuery();
			while (rs.next()) {
				TbClass tbclass = new TbClass();
				tbclass.setId(rs.getLong("id"));
				tbclass.setClassName(rs.getString("class_name"));
				tbclass.setClassTeacher(rs.getString("class_teacher"));
				tbclass.setStudentNum(rs.getInt("num"));
				list.add(tbclass);
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

	public int findManNum(String classname) throws Exception {

		Connection con = null;
		List<TbClass> list = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int n = 0;
		try {
			list = new ArrayList<TbClass>();
			con = DBUtils.getConnection();
			stmt = null;
			stmt = con
					.prepareStatement("select count(gender)count from student where class_name = ? and gender = '男' group by gender ");
			stmt.setString(1, classname);
			rs = stmt.executeQuery();
			while (rs.next()) {
				n = rs.getInt("count");
			}
			return n;
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

	public int findWonmanNum(String classname) throws Exception {

		Connection con = null;
		List<TbClass> list = null;
		PreparedStatement stmt = null;
		ResultSet rs = null;
		int n = 0;
		try {
			list = new ArrayList<TbClass>();
			con = DBUtils.getConnection();
			stmt = null;
			stmt = con
					.prepareStatement("select count(gender)count from student where class_name= ? and gender = '女' group by gender ");
			stmt.setString(1, classname);
			rs = stmt.executeQuery();
			while (rs.next()) {
				n = rs.getInt("count");
			}
			return n;
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

}
