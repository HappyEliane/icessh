package sc;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

import db.DB;

import tool.*;
import tool.CreateKey;

public class userbrowse {
	
	
	
	public String querysc(String s) throws SQLException, IOException
	{
		//System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
		try {
			Class.forName("org.postgresql.Driver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		DB db=new DB();
		DB db1=new DB();
		String result="ok";
	    String sql="select * from service.ices_user where user_id='"+s+"'";
	    ResultSet rs=db.query(sql);	
	    while(rs!=null && rs.next())
	    {
	    	String oforgid = rs.getString("oforgid");
	    	result=oforgid+",";
	    	ResultSet rs1=db1.query("select * from service.ices_scinformation where sc_id='"+oforgid+"'");
	    	while(rs1.next())
	    	{
	    		result += rs1.getString("sc_name");
	    	}
	    	
	    }
	    
		return result;
	}
	
	
	
	public String querycloudname(String s) throws SQLException, IOException
	{
		//System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
		try {
			Class.forName("org.postgresql.Driver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		DB db=new DB();
		String result="ok";
	    String sql="select * from website.ices_servclusteritem where serviceitem_id='"+s+"'";
	    ResultSet rs=db.query(sql);	
	    while(rs!=null &&rs.next())
	    {
	    	String cloudname = rs.getString("serviceitem_name");
	    	result = cloudname;
	    }
	    
		return result;
	}
	
	public List search(String s) throws SQLException, IOException
	{
		//System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
		try {
			Class.forName("org.postgresql.Driver").newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		
		DB db1=new DB();
		DB db2=new DB();
		DB db3=new DB();
		DB db4=new DB();
		DB db5=new DB();
		List l=new ArrayList();
		List l1=new ArrayList();
		List l2=new ArrayList();
		List l3=new ArrayList();
		List l4=new ArrayList();
		List l5=new ArrayList();
//查询用户表		
	    String sql1="select * from service.ices_user where cloud='"+s+"'";
	    ResultSet rs1=db1.query(sql1);	
	    while(rs1!=null && rs1.next())
	    {
	    	String userid = rs1.getString("user_id");
	    	String username = rs1.getString("user_name");
	    	String make_date = rs1.getString("make_date");
	    	String []B={userid,username,make_date};
	    	l1.add(B);    	
	    }
	    System.out.println("aaaaaaaaaaaaaaaaaaaaaaaaaaaa"+l1.size());
	    l.add(l1);
	    
//查询员工表	    
	    String sql2="select * from service.ices_cloud_employ where cloud_id='"+s+"'";
	    ResultSet rs2=db2.query(sql2);	
	    while(rs2!=null && rs2.next())
	    {
	    	String cloud_empid = rs2.getString("cloud_empid");
	    	String cloud_empname = rs2.getString("cloud_empname");
	    	
	    	String []B={cloud_empid,cloud_empname};
	    	l2.add(B);    	
	    }
	    System.out.println("bbbbbbbbbbbbbbbbbbbbbbbbbbbbb"+l2.size());
	    l.add(l2);
	    
//查询角色表	   
	    String sql3="select * from service.ices_cloud_role where cloud_id='"+s+"'";
	    ResultSet rs3=db3.query(sql3);	
	    while(rs3!=null &&rs3.next())
	    {   String cloud_role_id = rs3.getString("cloud_role_id");          
	    	String role_id = rs3.getString("role_id");
	    	String role_name = rs3.getString("role_name");
	    	String if_use = rs3.getString("if_use");	    	
	    	String []B={cloud_role_id,role_id,role_name,if_use};
	    	l3.add(B);    	
	    }
	    System.out.println("cccccccccccccccccccccccccccccccccc"+l3.size());
	    l.add(l3);
	    
//查询用户员工关联表
	    String sql4="select * from service.ices_clouduser_employ where cloud_id='"+s+"'";
	    ResultSet rs4=db4.query(sql4);	
	    while(rs4!=null &&rs4.next())
	    {
	    	String user_employ_id = rs4.getString("user_employ_id");
	    	String cloud_empid = rs4.getString("cloud_empid");
	    	String cloud_empname = rs4.getString("cloud_empname");
	    	String cloud_userid = rs4.getString("cloud_userid");
	    	String cloud_username = rs4.getString("cloud_username");
	    	
	    	String []B={user_employ_id,cloud_empid,cloud_empname,cloud_userid,cloud_username};
	    	l4.add(B);    	
	    }
	    System.out.println("dddddddddddddddddddddddddddddd"+l4.size());
	    l.add(l4);
	    
//查询用户角色关联表
	    String sql5="select * from service.ices_clouduser_role where cloud_id='"+s+"'";
	    ResultSet rs5=db5.query(sql5);	
	    while(rs5!=null && rs5.next())
	    {
	    	String user_role_id = rs5.getString("user_role_id");
	    	String user_id = rs5.getString("user_id");
	    	String role_id = rs5.getString("role_id");
	    	String role_name = rs5.getString("role_name");
	    	
	    	String []B={user_role_id,user_id,role_id,role_name};
	    	l5.add(B);    	
	    }
	    System.out.println("eeeeeeeeeeeeeeeeeeeeeee"+l5.size());
	    l.add(l5);
	    
		return l;
	}

public Boolean save3(String ss1,String ss2,List l) throws SQLException{

		DB db = new DB();
		System.out.println("ruiruirui3");
		
		for(Object o:l){
			Map opmap = (Map)o;
			int j=0;
		 if(!opmap.get("cloud_role_id").equals("null"))
		 {
			System.out.println("====================================================ruiruirui3");
			String delSql="delete from service.ices_cloud_role where cloud_role_id='"+opmap.get("cloud_role_id")+"'";
			db.delete(delSql);
			String insertSql = "INSERT INTO service.ices_cloud_role("+
            "cloud_role_id, cloud_id, cloud_name, role_id,role_name,if_use)"+
            "VALUES ( '"+opmap.get("cloud_role_id")+"', '"+ss1+"', '"+ss2+"', '"+opmap.get("role_id")+"','"+opmap.get("role_name")+"','"+opmap.get("if_use")+"')";
			System.out.println("ruiruirui5");
			j =db.insert(insertSql);
		 }
		 else if(opmap.get("cloud_role_id").equals("null"))
		 {
				
				
				DB db1=new DB();
				String num="";
				ResultSet rs=db1.query("SELECT * FROM service.ices_cloud_role");
				String s1="0000000";
			    String bn="";
			    int b=0;
			    if(rs.next())
			     {
				    s1=rs.getString("cloud_role_id");
					while(rs.next())
				   {	 
						 bn=rs.getString("cloud_role_id");
						 if(bn.compareTo(s1)>=0)
							 s1=bn;		
				   }
					  String bn33=s1.substring(3, 7);
				      b=Integer.parseInt(bn33);//当前主键数字部分
				} 
				 b=b+1;
				String ss=Integer.toString(b);
				int le=ss.length();
				int leng=4;
				 for(int bl=le;bl<leng;bl++)
					 ss='0'+ss;
				num="CRI"+ss;   //主键生成
				System.out.println("================================="+num);
				String insertSql = "INSERT INTO service.ices_cloud_role("+
	            "cloud_role_id, cloud_id, cloud_name, role_id,role_name,if_use)"+
	            "VALUES ( '"+num+"', '"+ss1+"', '"+ss2+"', '"+opmap.get("role_id")+"','"+opmap.get("role_name")+"','"+opmap.get("if_use")+"')";
				System.out.println("ruiruirui5");
				j =db.insert(insertSql);
			    
		 }
			if (j==0){
				return false;
			}
		}
		return true;
	}

public List queryrole(String s) throws SQLException, IOException
{
	//System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
	try {
		Class.forName("org.postgresql.Driver").newInstance();
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	DB db=new DB();
	List l=new ArrayList();
	System.out.println("=================================="+s);
    String sql="select * from service.ices_cloud_role where cloud_id='"+s+"' and if_use='启用'";
    ResultSet rs=db.query(sql);	
    while(rs.next())
    {
    	String role_id=rs.getString("role_id");
    	String role_name=rs.getString("role_name");
 
    	String []B={role_id,role_name};
    	l.add(B);    	
    }
    System.out.println("=================================="+l.size());
	return l;
}

public Boolean saveandclose(String s,List l) throws SQLException, IOException
{
	//System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
	try {
		Class.forName("org.postgresql.Driver").newInstance();
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	System.out.println("=========================================ggggg");
	DB db1=new DB();
	String num="";
	ResultSet rs=db1.query("SELECT * FROM service.ices_user");
	String s1="0000000";
    String bn="";
    int b=0;
    if(rs.next())
     {
	    s1=rs.getString("user_id");
		while(rs.next())
	   {	 
			 bn=rs.getString("user_id");
			 if(bn.compareTo(s1)>=0)
				 s1=bn;		
	   }
		  String bn33=s1.substring(3, 7);
	      b=Integer.parseInt(bn33);//当前主键数字部分
	} 
	 b=b+1;
	String ss=Integer.toString(b);
	int le=ss.length();
	int leng=4;
	 for(int bl=le;bl<leng;bl++)
		 ss='0'+ss;
	num="USI"+ss;   //主键生成
	System.out.println("=========================================s");
	SimpleDateFormat   df   =   new   SimpleDateFormat("yyyy-MM-dd");
    Calendar   rightNow   =   Calendar.getInstance();  
    String nowday=df.format(rightNow.getTime());//当前日期
	
	DB db=new DB();
	String[] aa = s.split(",");
	String result="ok";
	String sql1="INSERT INTO service.ices_user( " +
    " user_id, user_name, password, make_personid, make_date, user_state, oforgid, cloud )"+
    " VALUES ('"+num+"','"+aa[0]+"', '"+aa[1]+"', '"+aa[3]+"',  '"+nowday+"',  '1', '"+aa[4]+"', '"+aa[2]+"')";
    
	int j=db.insert(sql1);
	//存入用户表结束
	DB db2=new DB();
	String num2="";
	ResultSet rs2=db2.query("SELECT * FROM service.ices_user");
	String s2="0000000";
    String bn2="";
    int b2=0;
    if(rs2.next())
     {
	    s2=rs2.getString("user_id");
		while(rs2.next())
	   {	 
			 bn2=rs2.getString("user_id");
			 if(bn2.compareTo(s2)>=0)
				 s2=bn2;		
	   }
		  String bn33=s2.substring(3, 7);
	      b2=Integer.parseInt(bn33);//当前主键数字部分
	} 
    int k=0;
    for(Object o:l){
		Map opmap = (Map)o;
	     b2=b2+1;
	     String ss2=Integer.toString(b2);
	     int le2=ss2.length();
	     int leng2=4;
	     for(int bl=le2;bl<leng2;bl++)
		     ss2='0'+ss2;
	     num2="URI"+ss2;   //主键生成
	     String sql2="INSERT INTO service.ices_clouduser_role( " +
	     " user_role_id, user_id, user_name, cloud_id, role_id, role_name )"+
	     " VALUES ('"+num2+"','"+num+"', '"+aa[0]+"', '"+aa[2]+"','"+opmap.get("role_id")+"', '"+opmap.get("role_name")+"')";
	     k=db.insert(sql2);
	     
	
    }
	//存入用户角色关联表
	
    if(j==0||k==0)
    	return false;
    else 
    	return true;

}

public Boolean saveemp(String ss1,String ss2,List l) throws SQLException{

	DB db = new DB();
	
	for(Object o:l){
	
		Map opmap = (Map)o;
		int j=0;
		
	 if(!opmap.get("cloud_empid").equals("自动生成"))
	 {
		
		String delSql="delete from service.ices_cloud_employ where cloud_empid='"+opmap.get("cloud_empid")+"'";
		db.delete(delSql);
		String insertSql = "INSERT INTO service.ices_cloud_employ("+
        "cloud_id, cloud_name, cloud_empid,cloud_empname)"+
        "VALUES ( '"+ss1+"','"+ss2+"', '"+opmap.get("cloud_empid")+"','"+opmap.get("cloud_empname")+"')";
		System.out.println("ruiruirui5");
		j =db.insert(insertSql);
	 }
	 else if(opmap.get("cloud_empid").equals("自动生成"))
	 {
		
			DB db1=new DB();
			String num="";
			ResultSet rs=db1.query("SELECT * FROM service.ices_cloud_employ");
			String s1="0000000";
		    String bn="";
		    int b=0;
		    if(rs.next())
		     {
			    s1=rs.getString("cloud_empid");
				while(rs.next())
			   {	 
					 bn=rs.getString("cloud_empid");
					 if(bn.compareTo(s1)>=0)
						 s1=bn;		
			   }
				  String bn33=s1.substring(3, 7);
			      b=Integer.parseInt(bn33);//当前主键数字部分
			} 
			 b=b+1;
			String ss=Integer.toString(b);
			int le=ss.length();
			int leng=4;
			 for(int bl=le;bl<leng;bl++)
				 ss='0'+ss;
			num="CEI"+ss;   //主键生成
			System.out.println("================================="+num);
			String insertSql = "INSERT INTO service.ices_cloud_employ("+
	        "cloud_id, cloud_name, cloud_empid,cloud_empname)"+
	        "VALUES ( '"+ss1+"','"+ss2+"', '"+num+"','"+opmap.get("cloud_empname")+"')";
			System.out.println("ruiruirui5");
			j =db.insert(insertSql);
		    
	 }
		if (j==0){
			return false;
		}
	}
	return true;
}

public List saveLevel(List customerList) throws SQLException {
	System.out.println("=====================进入saveLevel=======================");
	DB db = new DB();	//保存
	DB db2 = new DB();	//查询

	for(Object item:customerList){
		Map customer = (Map)item;
		
		String level = customer.get("level").toString();
		if(level.equals("一级客户"))
    		level="D";
    	else if(level.equals("二级客户"))
    		level="C";
    	else if(level.equals("三级客户"))
    		level="B";
    	else if(level.equals("四级客户"))
    		level="A";
		
		String sqlItem = "";
		if(customer.get("oop").toString().equals("个体"))
			sqlItem = "UPDATE service.ices_perdetails SET " +
					" \"level\" = '"+level+"'" +
					"WHERE person_id='"+customer.get("customer_id").toString()+"'";
		else
			sqlItem = "UPDATE service.ices_orgdetails SET " +
					" \"level\" = '"+level+"'" +
					"WHERE org_id='"+customer.get("customer_id").toString()+"'";
		
			int m = db.update(sqlItem);
			System.out.println("数据库操作影响行数:"+m);
	}
	
	String sql2 = "SELECT * FROM service.ices_customer ";
	ResultSet rs2 = db2.query(sql2);
	List resultList = new ArrayList();
    while(rs2.next())
    {
    	String customer_id=rs2.getString("customer_id");
    	String cname=rs2.getString("cname");
    	String oop=rs2.getString("oop");
    	if(oop.equals("0"))
    		oop="组织";
    	else if(oop.equals("1"))
    		oop="个体";
    	String reg_date=rs2.getString("reg_date");
    	String level=rs2.getString("level");
    	if(level.equals("D"))
    		level="一级客户";
    	else if(level.equals("C"))
    		level="二级客户";
    	else if(level.equals("B"))
    		level="三级客户";
    	else if(level.equals("A"))
    		level="四级客户";
    	String []B={customer_id,cname,level,oop,reg_date};
    	resultList.add(B);    	
    }
	System.out.println("=====================退出saveLevel=======================");
	return resultList;
}

public List saveProLevel(List customerList) throws SQLException {
	System.out.println("=====================进入saveLevel=======================");
	DB db = new DB();	//保存
	DB db2 = new DB();	//查询

	for(Object item:customerList){
		Map provider = (Map)item;
		
		String level = provider.get("level").toString();
		if(level.equals("一级提供者"))
    		level="D";
    	else if(level.equals("二级提供者"))
    		level="C";
    	else if(level.equals("三级提供者"))
    		level="B";
    	else if(level.equals("四级提供者"))
    		level="A";
		
		String sqlItem = "";
		if(provider.get("oop").toString().equals("个体"))
			sqlItem = "UPDATE service.ices_perdetails SET " +
					" \"level\" = '"+level+"'" +
					"WHERE person_id='"+provider.get("provider_id").toString()+"'";
		else
			sqlItem = "UPDATE service.ices_orgdetails SET " +
					" \"level\" = '"+level+"'" +
					"WHERE org_id='"+provider.get("provider_id").toString()+"'";
		
			int m = db.update(sqlItem);
			System.out.println("数据库操作影响行数:"+m);
	}
	
	String sql2 = "SELECT * FROM service.ices_provider ";
	ResultSet rs2 = db2.query(sql2);
	List resultList = new ArrayList();
    while(rs2.next())
    {
    	String provider_id=rs2.getString("provider_id");
    	String pname=rs2.getString("pname");
    	String oop=rs2.getString("oop");
    	if(oop.equals("0"))
    		oop="组织";
    	else if(oop.equals("1"))
    		oop="个体";
    	String reg_date=rs2.getString("reg_date");
    	String level=rs2.getString("level");
    	if(level.equals("D"))
    		level="一级提供者";
    	else if(level.equals("C"))
    		level="二级提供者";
    	else if(level.equals("B"))
    		level="三级提供者";
    	else if(level.equals("A"))
    		level="四级提供者";
    	String []B={provider_id,pname,level,oop,reg_date};
    	resultList.add(B);    	
    }
	System.out.println("=====================退出saveLevel=======================");
	return resultList;
}

public List deleteCus(List customerList) throws SQLException {
	System.out.println("=====================进入deleteCus=======================");
	DB db = new DB();	//删除
	DB db2 = new DB();	//查询

	for(Object item:customerList){
		Map customer = (Map)item;
		
		String per_org_id = customer.get("customer_id").toString();
		int m = 0;
		
		if(customer.get("oop").toString().equals("个体")){
			String sqlreg_main = "DELETE FROM service.ices_regmain WHERE reg_id= (SELECT reg_id FROM service.ices_regper WHERE person_id='"+per_org_id+"')";//注册单主表
			m = db.delete(sqlreg_main);
			System.out.println("数据库操作影响行数:"+m);
			String sqluser = "DELETE FROM service.ices_user WHERE oforgid='"+per_org_id+"'";//用户表
			m = db.delete(sqluser);
			System.out.println("数据库操作影响行数:"+m);
			String sqlreg_per = "DELETE FROM service.ices_regper WHERE person_id='"+per_org_id+"'";//注册单个体类信息
			m = db.delete(sqlreg_per);
			System.out.println("数据库操作影响行数:"+m);
			String sqlper_detail = "DELETE FROM service.ices_perdetails WHERE person_id='"+per_org_id+"'";//个体类信息
			m = db.delete(sqlper_detail);
			System.out.println("数据库操作影响行数:"+m);
			String sqluser_person = "DELETE FROM service.ices_user_person WHERE person_id='"+per_org_id+"'";//用户-个体关联表
			m = db.delete(sqluser_person);
			System.out.println("数据库操作影响行数:"+m);
		}
		else{
			String sqlreg_main = "DELETE FROM service.ices_regmain WHERE reg_id= (SELECT reg_id FROM service.ices_regorg WHERE org_id='"+per_org_id+"')";//注册单主表
			m = db.delete(sqlreg_main);
			System.out.println("数据库操作影响行数:"+m);
			String sqluser = "DELETE FROM service.ices_user WHERE oforgid='"+per_org_id+"'";//用户表
			m = db.delete(sqluser);
			System.out.println("数据库操作影响行数:"+m);
			String sqlreg_org = "DELETE FROM service.ices_regorg WHERE org_id='"+per_org_id+"'";//注册单组织类信息
			m = db.delete(sqlreg_org);
			System.out.println("数据库操作影响行数:"+m);
			String sqlorg_detail = "DELETE FROM service.ices_orgdetails WHERE org_id='"+per_org_id+"'";//组织类信息
			m = db.delete(sqlorg_detail);
			System.out.println("数据库操作影响行数:"+m);
			String sqlorg_role = "DELETE FROM service.ices_orgrole WHERE org_id='"+per_org_id+"'";//组织角色关联
			m = db.delete(sqlorg_role);
			System.out.println("数据库操作影响行数:"+m);
			String sqluser_role = "DELETE FROM service.ices_user_role WHERE org_id='"+per_org_id+"'";//用户角色关联
			m = db.delete(sqluser_role);
			System.out.println("数据库操作影响行数:"+m);
		}
	}
	
	String sql2 = "SELECT * FROM service.ices_customer ";
	ResultSet rs2 = db2.query(sql2);
	List resultList = new ArrayList();
    while(rs2.next())
    {
    	String customer_id=rs2.getString("customer_id");
    	String cname=rs2.getString("cname");
    	String oop=rs2.getString("oop");
    	if(oop.equals("0"))
    		oop="组织";
    	else if(oop.equals("1"))
    		oop="个体";
    	String reg_date=rs2.getString("reg_date");
    	String level=rs2.getString("level");
    	if(level.equals("D"))
    		level="一级客户";
    	else if(level.equals("C"))
    		level="二级客户";
    	else if(level.equals("B"))
    		level="三级客户";
    	else if(level.equals("A"))
    		level="四级客户";
    	String []B={customer_id,cname,level,oop,reg_date};
    	resultList.add(B);    	
    }
	System.out.println("=====================退出deleteCus=======================");
	return resultList;
}

public List deleteProCus(List customerList) throws SQLException {
	System.out.println("=====================进入deleteCus=======================");
	DB db = new DB();	//删除
	DB db2 = new DB();	//查询

	for(Object item:customerList){
		Map provider = (Map)item;
		
		String per_org_id = provider.get("provider_id").toString();
		int m = 0;
		
		if(provider.get("oop").toString().equals("个体")){
			String sqlreg_main = "DELETE FROM service.ices_regmain WHERE reg_id= (SELECT reg_id FROM service.ices_regper WHERE person_id='"+per_org_id+"')";//注册单主表
			m = db.delete(sqlreg_main);
			System.out.println("数据库操作影响行数:"+m);
			String sqluser = "DELETE FROM service.ices_user WHERE oforgid='"+per_org_id+"'";//用户表
			m = db.delete(sqluser);
			System.out.println("数据库操作影响行数:"+m);
			String sqlreg_per = "DELETE FROM service.ices_regper WHERE person_id='"+per_org_id+"'";//注册单个体类信息
			m = db.delete(sqlreg_per);
			System.out.println("数据库操作影响行数:"+m);
			String sqlper_detail = "DELETE FROM service.ices_perdetails WHERE person_id='"+per_org_id+"'";//个体类信息
			m = db.delete(sqlper_detail);
			System.out.println("数据库操作影响行数:"+m);
			String sqluser_person = "DELETE FROM service.ices_user_person WHERE person_id='"+per_org_id+"'";//用户-个体关联表
			m = db.delete(sqluser_person);
			System.out.println("数据库操作影响行数:"+m);
		}
		else{
			String sqlreg_main = "DELETE FROM service.ices_regmain WHERE reg_id= (SELECT reg_id FROM service.ices_regorg WHERE org_id='"+per_org_id+"')";//注册单主表
			m = db.delete(sqlreg_main);
			System.out.println("数据库操作影响行数:"+m);
			String sqluser = "DELETE FROM service.ices_user WHERE oforgid='"+per_org_id+"'";//用户表
			m = db.delete(sqluser);
			System.out.println("数据库操作影响行数:"+m);
			String sqlreg_org = "DELETE FROM service.ices_regorg WHERE org_id='"+per_org_id+"'";//注册单组织类信息
			m = db.delete(sqlreg_org);
			System.out.println("数据库操作影响行数:"+m);
			String sqlorg_detail = "DELETE FROM service.ices_orgdetails WHERE org_id='"+per_org_id+"'";//组织类信息
			m = db.delete(sqlorg_detail);
			System.out.println("数据库操作影响行数:"+m);
			String sqlorg_role = "DELETE FROM service.ices_orgrole WHERE org_id='"+per_org_id+"'";//组织角色关联
			m = db.delete(sqlorg_role);
			System.out.println("数据库操作影响行数:"+m);
			String sqluser_role = "DELETE FROM service.ices_user_role WHERE org_id='"+per_org_id+"'";//用户角色关联
			m = db.delete(sqluser_role);
			System.out.println("数据库操作影响行数:"+m);
		}
	}
	
	String sql2 = "SELECT * FROM service.ices_provider ";
	ResultSet rs2 = db2.query(sql2);
	List resultList = new ArrayList();
    while(rs2.next())
    {
    	String provider_id=rs2.getString("provider_id");
    	String pname=rs2.getString("pname");
    	String oop=rs2.getString("oop");
    	if(oop.equals("0"))
    		oop="组织";
    	else if(oop.equals("1"))
    		oop="个体";
    	String reg_date=rs2.getString("reg_date");
    	String level=rs2.getString("level");
    	if(level.equals("D"))
    		level="一级客户";
    	else if(level.equals("C"))
    		level="二级客户";
    	else if(level.equals("B"))
    		level="三级客户";
    	else if(level.equals("A"))
    		level="四级客户";
    	String []B={provider_id,pname,level,oop,reg_date};
    	resultList.add(B);    	
    }
	System.out.println("=====================退出deleteCus=======================");
	return resultList;
}

public List searchcustomer(String s) throws SQLException, IOException
{
	
	DB db=new DB();
	List l=new ArrayList();
	String condition=" 1=1 ";
	String condition2=" 1=1";
	String[] A=s.split(",");
    String startdate=A[0];
    String enddate=A[1];
    String state=A[2];
    String reg_class=A[3];
   System.out.println("=======searchcustomer===================s================="+s);
    if(!startdate.equals("null")&&!enddate.equals("null"))//有开始日期和结束日期
	{
		String month1=startdate.substring(0, 2);
		String day1=startdate.substring(3, 5);
		String year1=startdate.substring(6, 10);		
		String s_date=year1+"-"+month1+"-"+day1; 
		
		String month2=enddate.substring(0, 2);
		String day2=enddate.substring(3, 5);
		String year2=enddate.substring(6, 10);		
		String e_date=year2+"-"+month2+"-"+day2; 
    	condition+=" and reg_date between '"+s_date+"' and '"+e_date+"'";
	}
    if(!startdate.equals("null")&&enddate.equals("null"))  //有开始日期
    {
    	String month1=startdate.substring(0, 2);
		String day1=startdate.substring(3, 5);
		String year1=startdate.substring(6, 10);		
		String s_date=year1+"-"+month1+"-"+day1;
		
		SimpleDateFormat   df   =   new   SimpleDateFormat("yyyy-MM-dd");
		Calendar   rightNow   =   Calendar.getInstance();  
		String e_date=df.format(rightNow.getTime());
		condition+=" and reg_date between '"+s_date+"' and '"+e_date+"'";
    }
    if(!state.equals("null"))
    {
    	 if(state.equals("已审核"))
    		 condition+=" and reg_state like '1'";
    	 else if(state.equals("未审核"))
    		 condition+=" and reg_state like '0'";
    }
    if(!reg_class.equals("null"))
    {
    		 condition+=" and reg_class like '"+reg_class+"'"; 
    }
   
  
    String sql="select * from service.ices_regmain where if_use='1' and "+condition;
    ResultSet rs=db.query(sql);	
    while(rs.next())
    {
    	DB db1= new DB();
    	String reg_id=rs.getString("reg_id");
    	String reg_date=rs.getString("reg_date");
    	String reg_state=rs.getString("reg_state");
    	String regstate="";
    	  if(reg_state.equals("0"))
    	      regstate="未审核";
    	  else
    		  regstate="已审核";
    	String biller=rs.getString("biller");
    	String reg_class1=rs.getString("reg_class");
    	
    	String []B={reg_id,reg_date,biller,regstate,reg_class1};
    	l.add(B);    	
    }
    System.out.println("=================================="+l.size());
	return l;

  }



public Boolean del(List l) throws SQLException{

	DB db = new DB();
	
	for(Object o:l){
	
		Map opmap = (Map)o;
		db.update("update service.ices_regmain set if_use='0' where reg_id='"+opmap.get("reg_id")+"'");
        
	}
	return true;
}



public String  search1(String s) throws SQLException{

	DB db = new DB();
	DB db1 = new DB();
	String result="";
	ResultSet rs=db.query("select * from service.ices_regper where reg_id='"+s+"'");
	while(rs.next())
	{
		String personid=rs.getString("person_id");
		String identity_id=rs.getString("identity_id");
		String pername=rs.getString("pername");
		String percode=rs.getString("percode");
		String peraddress=rs.getString("peraddress");
		String phone=rs.getString("phone");
		String email=rs.getString("email");
		ResultSet rs1=db1.query("select * from service.ices_perdetails where reg_id='"+s+"'");
		String level="";
		while(rs1.next())
		{
			level = rs1.getString("level");
		}
		
		result=personid+","+identity_id+","+pername+","+percode+","+peraddress+","+phone+","+email+","+level;
		
	}
	
	return result;
}

public String  search2(String s) throws SQLException{

	DB db = new DB();
	DB db1 = new DB();
	String result="";
	ResultSet rs=db.query("select * from service.ices_regorg where reg_id='"+s+"'");
	while(rs.next())
	{
		String org_id=rs.getString("org_id");
		String reg_code=rs.getString("reg_code");
		String orgname=rs.getString("orgname");
		String orgaddress=rs.getString("orgaddress");
		String orgcode=rs.getString("orgcode");
		String legalper=rs.getString("legalper");
		String size=rs.getString("size");
		String licence_id=rs.getString("licence_id");
		String reg_capital=rs.getString("reg_capital");
		ResultSet rs1=db1.query("select * from service.ices_orgdetails where reg_id='"+s+"'");
		String level="";
		while(rs1.next())
		{
			level = rs1.getString("level");
		}
		
		result=org_id+","+reg_code+","+orgname+","+orgaddress+","+orgcode+","+legalper+","+size+","+licence_id+","+reg_capital+","+level;
		
	}
	
	return result;
}
public Boolean savecustomer(List l) throws SQLException, ArrayIndexOutOfBoundsException{

	DB db = new DB();
	DB db1 = new DB();
	SimpleDateFormat   df   =   new   SimpleDateFormat("yyyy-MM-dd");
	Calendar   rightNow   =   Calendar.getInstance();  
	String now=df.format(rightNow.getTime());
	
	for(Object o:l){
	
		Map opmap = (Map)o;
		String reg_id=opmap.get("reg_id").toString();
		int tmp=0;
		
		db.update("update service.ices_regmain set if_use='1',reg_state='1' where reg_id='"+reg_id+"'");//修改注册主表状态为已审核
		ResultSet rs=db.query("select * from service.ices_regorg where reg_id='"+reg_id+"'");//信息存入细节表，级别默认为A级
		while(rs.next())
		{
			if(reg_id.equals(rs.getString("reg_id")))
			{
				tmp=1;
				DB d=new DB();
				
				d.insert("insert into service.ices_orgdetails (org_id,reg_code,orgname,orgaddress,orgcode,legalper,size,licence_id,reg_capital,org_class,update_date,update_per,level,reg_id)"+
						"values('"+rs.getString("org_id")+"','"+rs.getString("reg_code")+"','"+rs.getString("orgname")+"','"+rs.getString("orgaddress")+"','"+rs.getString("orgcode")+"','"+rs.getString("legalper")+"','"+rs.getString("size")+"','"+rs.getString("licence_id")+"','"+rs.getString("reg_capital")+"','"+rs.getString("org_class")+"','"+now+"','运营中心','A','"+reg_id+"')");
			 
				CreateKey ck=new CreateKey("service.ices_user","user_id","USI");
				String userid = ck.getKey();
				String sql="insert into service.ices_user (user_id,user_name,password,make_personid,make_date,user_state,oforgid,cloud)" +
						"values('"+userid+"','"+rs.getString("org_username")+"','"+rs.getString("org_password")+"','USER00','"+now+"','1','"+rs.getString("org_id")+"','0')";
				d.insert(sql);//插入用户表
				CreateKey ck1=new CreateKey("service.ices_orgrole","org_role_id","ORI");
				String org_role_id = ck1.getKey();
				String sql1="insert into service.ices_orgrole (org_role_id,org_id,role_id,role_name) values('"+org_role_id+"','"+rs.getString("org_id")+"','"+rs.getString("org_roleid")+"','"+rs.getString("org_rolename")+"')";
				d.insert(sql1);//插入角色表
				
				//插入角色功能关联表：组织类用户管理，角色功能配置
				CreateKey key = new CreateKey("service.ices_role_operation","id_","RO00");
				if(rs.getString("org_class").equals("0")){		//消费者
					String sql3="insert into service.ices_role_operation (id_,org_id,orgrole_id,serviceitem_id,oper_id) " +
							"values('"+key.getKey()+"','"+rs.getString("org_id")+"','"+org_role_id+"','M-Service','ZCDB_YINFO')";
					d.insert(sql3);//插入用户管理
					
					sql3="insert into service.ices_role_operation (id_,org_id,orgrole_id,serviceitem_id,oper_id) " +
						"values('"+key.getKey()+"','"+rs.getString("org_id")+"','"+org_role_id+"','M-Service','ZCDC_RoleConfig')";
					d.insert(sql3);//插入用户管理
				}
				else{			//提供者
					String sql3="insert into service.ices_role_operation (id_,org_id,orgrole_id,serviceitem_id,oper_id) " +
						"values('"+key.getKey()+"','"+rs.getString("org_id")+"','"+org_role_id+"','M-Service','ZPBB_YINFO')";
					d.insert(sql3);//插入用户管理
					
					sql3="insert into service.ices_role_operation (id_,org_id,orgrole_id,serviceitem_id,oper_id) " +
						"values('"+key.getKey()+"','"+rs.getString("org_id")+"','"+org_role_id+"','M-Service','ZPBC_RoleConfig')";
					d.insert(sql3);//插入用户管理
				}
				
				CreateKey ck2=new CreateKey("service.ices_user_role","user_role_id","URI");
				String sql2="insert into service.ices_user_role (user_role_id,user_id,org_id,role_id,role_name) values ('"+ck2.getKey()+"','"+userid+"','"+rs.getString("org_id")+"','"+rs.getString("org_roleid")+"','"+rs.getString("org_rolename")+"')";
				d.insert(sql2);//插入用户角色关联表
			}
		}
		if(tmp==0)
		{
			ResultSet rs1=db1.query("select * from service.ices_regper where reg_id='"+reg_id+"'");
			DB d=new DB();
			while(rs1.next())
			{
			System.out.println("======"+rs1.getString("person_id")+"==============="+rs1.getString("identity_id")+"=================================="+rs1.getString("person_class"));
			d.insert("insert into service.ices_perdetails (person_id,identity_id,pername,percode,peraddress,person_class,phone,email,update_date,update_per,level,reg_id)"+
					"values('"+rs1.getString("person_id")+"','"+rs1.getString("identity_id")+"','"+rs1.getString("pername")+"','"+rs1.getString("percode")+"','"+rs1.getString("peraddress")+"','"+rs1.getString("person_class")+"','"+rs1.getString("phone")+"','"+rs1.getString("email")+"','"+now+"','运营中心','A','"+reg_id+"')");
		    System.out.println("=======================================================");
		    CreateKey ck=new CreateKey("service.ices_user","user_id","USI");
			String userid = ck.getKey();
			String sql="insert into service.ices_user (user_id,user_name,password,make_personid,make_date,user_state,oforgid,cloud)" +
					"values('"+userid+"','"+rs1.getString("per_username")+"','"+rs1.getString("per_password")+"','USER00','"+now+"','1','"+rs1.getString("person_id")+"','0')";
			d.insert(sql);//插入用户表
			CreateKey ck1=new CreateKey("service.ices_user_person","user_person_id","UPI");
			String sql1="insert into service.ices_user_person (user_person_id,user_id,person_id) values ('"+ck1.getKey()+"','"+userid+"','"+rs1.getString("person_id")+"')";
			d.insert(sql1);//插入用户个体关联表
			}
		}
		
        
	}
	return true;
}

public Boolean save1(String s1,String s2) throws SQLException{

	DB db = new DB();
	SimpleDateFormat   df   =   new   SimpleDateFormat("yyyy-MM-dd");
	Calendar   rightNow   =   Calendar.getInstance();  
	String now=df.format(rightNow.getTime());

		db.update("update service.ices_regmain set if_use='1',reg_state='1' where reg_id='"+s1+"'");//修改注册主表状态为已审核
		
			ResultSet rs=db.query("select * from service.ices_regper where reg_id='"+s1+"'");
			DB d=new DB();
			while(rs.next())
			{
			d.insert("insert into  service.ices_perdetails (person_id,identity_id,pername,percode,peraddress,person_class,phone,email,update_date,update_per,level,reg_id)"+
					"values('"+rs.getString("person_id")+"','"+rs.getString("identity_id")+"','"+rs.getString("pername")+"','"+rs.getString("percode")+"','"+rs.getString("peraddress")+"','"+rs.getString("person_class")+"','"+rs.getString("phone")+"','"+rs.getString("email")+"','"+now+"','运营中心','"+s2+"','"+s1+"')");
			CreateKey ck=new CreateKey("service.ices_user","user_id","USI");
			String userid = ck.getKey();
			String sql="insert into service.ices_user (user_id,user_name,password,make_personid,make_date,user_state,oforgid,cloud)" +
					"values('"+userid+"','"+rs.getString("per_username")+"','"+rs.getString("per_password")+"','USER00','"+now+"','1','"+rs.getString("person_id")+"','0')";
			d.insert(sql);//插入用户表
			CreateKey ck1=new CreateKey("service.ices_user_person","user_person_id","UPI");
			String sql1="insert into service.ices_user_person (user_person_id,user_id,person_id) values ('"+ck1.getKey()+"','"+userid+"','"+rs.getString("person_id")+"')";
			d.insert(sql1);//插入用户个体关联表
			}
			return true;
}

public Boolean save2(String s1,String s2) throws SQLException{

	DB db = new DB();
	SimpleDateFormat   df   =   new   SimpleDateFormat("yyyy-MM-dd");
	Calendar   rightNow   =   Calendar.getInstance();  
	String now=df.format(rightNow.getTime());

		db.update("update service.ices_regmain set if_use='1',reg_state='1' where reg_id='"+s1+"'");//修改注册主表状态为已审核
		
			ResultSet rs=db.query("select * from service.ices_regorg where reg_id='"+s1+"'");
			DB d=new DB();
			while(rs.next())
			{
			d.insert("insert into service.ices_orgdetails (org_id,reg_code,orgname,orgaddress,orgcode,legalper,size,licence_id,reg_capital,org_class,update_date,update_per,level,reg_id)"+
					"values('"+rs.getString("org_id")+"','"+rs.getString("reg_code")+"','"+rs.getString("orgname")+"','"+rs.getString("orgaddress")+"','"+rs.getString("orgcode")+"','"+rs.getString("legalper")+"','"+rs.getString("size")+"','"+rs.getString("licence_id")+"','"+rs.getString("reg_capital")+"','"+rs.getString("org_class")+"','"+now+"','运营中心','"+s2+"','"+s1+"')");
			CreateKey ck=new CreateKey("service.ices_user","user_id","USI");
			String userid = ck.getKey();
			String sql="insert into service.ices_user (user_id,user_name,password,make_personid,make_date,user_state,oforgid,cloud)" +
					"values('"+userid+"','"+rs.getString("org_username")+"','"+rs.getString("org_password")+"','USER00','"+now+"','1','"+rs.getString("org_id")+"','0')";
			d.insert(sql);//插入用户表
			CreateKey ck1=new CreateKey("service.ices_orgrole","org_role_id","ORI");
			String org_role_id = ck1.getKey();
			String sql1="insert into service.ices_orgrole (org_role_id,org_id,role_id,role_name) values('"+org_role_id+"','"+rs.getString("org_id")+"','"+rs.getString("org_roleid")+"','"+rs.getString("org_rolename")+"')";
			d.insert(sql1);//插入角色表
			
			//插入角色功能关联表：组织类用户管理，角色功能配置
			CreateKey key = new CreateKey("service.ices_role_operation","id_","RO00");
			if(rs.getString("org_class").equals("0")){		//消费者
				String sql3="insert into service.ices_role_operation (id_,org_id,orgrole_id,serviceitem_id,oper_id) " +
						"values('"+key.getKey()+"','"+rs.getString("org_id")+"','"+org_role_id+"','M-Service','ZCDB_YINFO')";
				d.insert(sql3);//插入用户管理
				
				sql3="insert into service.ices_role_operation (id_,org_id,orgrole_id,serviceitem_id,oper_id) " +
					"values('"+key.getKey()+"','"+rs.getString("org_id")+"','"+org_role_id+"','M-Service','ZCDC_RoleConfig')";
				d.insert(sql3);//插入用户管理
			}
			else{			//提供者
				String sql3="insert into service.ices_role_operation (id_,org_id,orgrole_id,serviceitem_id,oper_id) " +
					"values('"+key.getKey()+"','"+rs.getString("org_id")+"','"+org_role_id+"','M-Service','ZPBB_YINFO')";
				d.insert(sql3);//插入用户管理
				
				sql3="insert into service.ices_role_operation (id_,org_id,orgrole_id,serviceitem_id,oper_id) " +
					"values('"+key.getKey()+"','"+rs.getString("org_id")+"','"+org_role_id+"','M-Service','ZPBC_RoleConfig')";
				d.insert(sql3);//插入用户管理
			}
			
			CreateKey ck2=new CreateKey("service.ices_user_role","user_role_id","URI");
			String sql2="insert into service.ices_user_role (user_role_id,user_id,org_id,role_id,role_name) values ('"+ck2.getKey()+"','"+userid+"','"+rs.getString("org_id")+"','"+rs.getString("org_roleid")+"','"+rs.getString("org_rolename")+"')";
			d.insert(sql2);//插入用户角色关联表
			}
			return true;
}

//-------------------结束--------------------------------------

public String queryscname(String s) throws SQLException, IOException
{
	//System.out.println("kkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk");
	try {
		Class.forName("org.postgresql.Driver").newInstance();
	} catch (InstantiationException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (IllegalAccessException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} 
	
	DB db=new DB();
	DB db1=new DB();
	String result="ok";
    String sql="select * from service.ices_user where user_id='"+s+"'";
    ResultSet rs=db.query(sql);	
    while(rs.next())
    {
    	String oforgid = rs.getString("oforgid");
    	ResultSet rs1=db1.query("select * from service.ices_scinformation where sc_id='"+oforgid+"'");
    	while(rs1.next())
    	{
    		result = rs1.getString("sc_name");
    	}
    	
    }
    
	return result;
}
}
