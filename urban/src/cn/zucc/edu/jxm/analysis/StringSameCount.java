package cn.zucc.edu.jxm.analysis;

import java.util.HashMap;

public class StringSameCount {

	    private HashMap<String, Integer> map;
	    private int counter;   //����ͳ�� map�е�value
	    
	    public StringSameCount()
	    {
	        map=new HashMap<String,Integer>();
	    }
	    
	    /**
	     * ������hashmap�в����ַ���
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
