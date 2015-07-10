package tool;


import java.sql.ResultSet;
import java.sql.SQLException;

import db.DB;

public class CreateKey {

	//private String table = "";
	//private String key = "";
	private String letterPart = "";//��������ĸ����
	private String numberPart = "";//���������ֲ���
	private int leng = 0;			//����������λ��
	
	public CreateKey(String table, String key , String letterString) throws NumberFormatException, SQLException{
		//this.table = table;
		//this.key = key;
		System.out.println("----------------�ѽ����������ɺ���--------------------------");
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
		System.out.println("----------------�ѽ����������ɺ���--------------------------");
	}
	
	
	public String getKey(){
		System.out.println("----------------�ѽ����ȡ��������--------------------------");
		numberPart = Integer.toString(Integer.parseInt(numberPart)+1);
		int dl=numberPart.length();
        
        for(int bl=dl;bl<leng;bl++)
        	numberPart='0'+numberPart;
		System.out.println("----------------�ѽ�����ȡ��������--------------------------");
		return letterPart+numberPart;
	}
	
}
