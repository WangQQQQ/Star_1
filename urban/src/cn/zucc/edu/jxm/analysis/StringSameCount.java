package cn.zucc.edu.jxm.analysis;

import java.util.HashMap;

public class StringSameCount {

	    private HashMap<String, Integer> map;
	    private int counter;   //用于统计 map中的value
	    
	    public StringSameCount()
	    {
	        map=new HashMap<String,Integer>();
	    }
	    
	    /**
	     * 用于在hashmap中插入字符串
	     * @param string
	     */
	    public void hashInsert(String string){
	        if (map.containsKey(string)){
	            counter=(Integer)map.get(string);
	            map.put(string,++counter);
	        }
	        else{
	            map.put(string, 1);
	        }
	    }
	    
	    public HashMap<String, Integer> getHashMap(){
	        return map;
	    }

}
