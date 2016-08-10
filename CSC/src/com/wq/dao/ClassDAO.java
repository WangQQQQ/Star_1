package com.wq.dao;

import java.util.ArrayList;
import java.util.List;

import com.wq.entity.TbClass;

public interface ClassDAO {
	public List<TbClass> findClassMsgByPage(int page) throws Exception;

	public List<TbClass> findClass() throws Exception;
	
	public void deleteClassById(int id) throws Exception;

	public TbClass loadClassById(long id) throws Exception;

	public void updateClass(TbClass tbclass) throws Exception;

	public void addClass(TbClass tbclass) throws Exception;
	/**
	 * 找到一个班中男生数量
	 */
	public int findManNum(String classname)throws Exception;
	/**
	 * 找到一个班中女生数量
	 */
	public int findWonmanNum(String classname)throws Exception;
}
