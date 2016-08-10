package com.wq.utils;

import java.io.IOException;
import java.util.Properties;

/**
 * 工厂类 依据type(接口名称)返回一个符合该接口要求的对象
 * 可以将type与实现累得对应关系写在配置文件(dao.properties)里面,DAOFactory读该配置文件
 * 依据配置文件的内容来创建合适的对象
 * @author gungun
 * 
 */
public class DAOFactory {
	private static Properties pro = new Properties();
	static{
		try {
			pro.load(DAOFactory.class.getClassLoader().getResourceAsStream("com/wq/utils/dao.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	public static String getValue(String key){
		return pro.getProperty(key);
	}
	public static Object getInstance(String type) throws Exception{
		Object obj = null;
		//依据接口名找到类名
		String className = getValue(type);
		//依据类名，通过反射机制创建一个实例
		try {
			Class c = Class.forName(className);
			//通过class对象创建一个实例。
			obj = c.newInstance();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
			throw e;
		}
		return obj;
	}
}
