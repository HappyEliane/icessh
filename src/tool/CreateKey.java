package tool;


import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;

public class CreateKey {

	//private String table = "";
	//private String key = "";
	private String letterPart = "";//主键的字母部分
	private String numberPart = "";//主键的数字部分
	private int leng = 0;			//主键的数字位数
	
	public CreateKey(String table, String key , String letterString) throws NumberFormatException, SQLException{
		//this.table = table;
		//this.key = key;
		System.out.println("----------------已进入主键生成函数--------------------------");
		DB db = new DB();
		ResultSet rs=db.query("SELECT "+key+" FROM "+table+"");
	    String ketStrings = letterString + "0000";
		
		System.out.println("======================================================");	
		System.out.println(rs);	
		if(rs.next()){
			System.out.println("++++++++++++++++++++=======================================");
			ketStrings=rs.getString(key);
		}

		  while(rs.next())
		  {	 
			 String temp=rs.getString(key);
			 System.out.println(",,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,,");			
			 System.out.println("temp:"+temp);
			 if(temp.compareTo(ketStrings)>=0)
				 ketStrings=temp;		
		   }
		  
		  int i=0;
		  for( ; i<ketStrings.length() ; i++)
			  if(ketStrings.charAt(i)-'0'>=0 && ketStrings.charAt(i)-'0'<=9)
				  break;
		  if(i==ketStrings.length()) return;
		  
		  letterPart = ketStrings.substring(0, i);
		  numberPart = ketStrings.substring(i);
		  leng = numberPart.length();
		  System.out.println("sssssssssssssssssssssssssssssssssssssssssssssssssssss");
		  System.out.println("numberPart:"+numberPart);
		System.out.println("----------------已结束主键生成函数--------------------------");
	}
	
	
	public String getKey(){
		System.out.println("----------------已进入获取主键函数--------------------------");
		numberPart = Integer.toString(Integer.parseInt(numberPart)+1);
		int dl=numberPart.length();
        
        for(int bl=dl;bl<leng;bl++)
        	numberPart='0'+numberPart;
		System.out.println("----------------已结束获取主键函数--------------------------");
		return letterPart+numberPart;
	}
	
}
