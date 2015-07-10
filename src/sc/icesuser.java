package sc;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import db.DB;

import tool.CreateKey;


public class icesuser {
	public String insertHomeUser(String userid,String realname,String password,String email,
			String phone,String address,String contact,String contactid,String contactphone) throws NumberFormatException, SQLException
	{
		System.out.println("新用户注册！");
		String result1="wrong";
		String result2="wrong";
		
		String result4="wrong";
		String temp_str="";//获取当前日期   
	    Date dt = new Date();   
	    //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");   
	    temp_str=sdf.format(dt);  
	    String homeid="";
	    CreateKey ck=new CreateKey("home_user","homeid","HRI");
	    homeid=ck.getKey();
	    String sql="INSERT INTO home_user( " +
			    " homeid, realname, email, phone, address, contact, contactid, contactphone, registerdate )"+
			    " VALUES ('"+homeid+"','"+realname+"', '"+email+"',  '"+phone+"',  '"+address+"', '"+contact+"', '"+contactid+"', '"+contactphone
			    +"', '"+temp_str+"')";
	    DB db=new DB();
	    
	    int result=db.insert(sql);
	    if(result!=0)
	    {
	    	 result1="ok";
	    }
	    else
	    	result1="wrong";
	    
	    String user_id="";//用户ID
		try {
			user_id = getUseridKey();
			System.out.println("ices_user表主键获取成功！");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("ices_user表主键获取失败！");
		}
		
	//	CreateKey ck1=new CreateKey("ices_sc_role","sc_role_id","CRI");
	//	String role_id=ck1.getKey();//新角色的ID
	    result2=insertIcesUser(user_id,userid,password,temp_str,homeid);//加入新用户
	
	    result4=insertUserRole(user_id,userid,"CRI0002","Hadmin",temp_str);//添加用户-角色表
	    db.close();
	    if(result1.equals("ok") && result2.equals("ok") && result4.equals("ok"))
	    {
	    	System.out.println("新用户注册成功！");
	    	return "ok";
	    }
	    System.out.println("新用户注册失败！");
	    return "wrong";
	}
	public String insertIcesUser(String user_id,String username,String password,String make_date,String homeid)
	{
		
		String sql="INSERT INTO ices_user( " +
			    " user_id, user_name, password,  make_date, user_state,  cloud, make )"+
			    " VALUES ('"+user_id+"','"+username+"', '"+password+"', '"+make_date+"',  '"+"1"+"', '"+homeid+"', '"+"URI0002"+"')";
		 DB db=new DB();
		    
		    int result=db.insert(sql);
		    db.close();
		    if(result!=0)
		    {
		    	return "ok";
		    }
		    else
		    	return "wrong";
	}
	
	public String getUseridKey() throws NumberFormatException, SQLException
	{
		System.out.println("获取ices_user表中新用户的主键！");
		DB db1=new DB();
		String num="";
		ResultSet rs=db1.query("SELECT * FROM ices_user");
		String s1="0000000";
	    String bn="";
	    int b=0;
	    if(rs!=null && rs.next())
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
		db1.close();
		return num;
	}
	public String check_userID(String id) throws SQLException
	{
		System.out.println("查询当前用户是否存在，注册的用户名为"+id);
		String sql="select * from ices_user where user_name = '"+id+"' ";
		DB db=new DB();
		ResultSet rs=db.query(sql);
		boolean result=true;
		while(rs!=null && rs.next())
    	{
    		result=false;
    	}
		db.close();
		if(result)
		{
			System.out.println(id+"不存在，该用户名可以使用");
			return "ok";
		}
		System.out.println(id+"存在，该用户名不可使用");
		return "wrong";
	}

	public String insertUserRole(String userid,String username,String roleid,String rolename,String update_date) throws NumberFormatException, SQLException
	{
		System.out.println("添加用户-角色关系！");
			CreateKey ck=new CreateKey("ices_user_role","user_role_id","URI");
			String sql="INSERT INTO ices_user_role( " +
				    " user_role_id, user_id, user_name, role_id, role_type, role_name, update_date, make )"+
				    " VALUES ('"+ck.getKey()+"','"+userid+"', '"+username+"', '"+roleid+"',  '"+"admin"+"', '"+rolename+"',  '"+update_date+"', '"+"URI0002"+"')";
			 DB db=new DB();
			 int result=db.insert(sql);
			 db.close();
			 if(result!=0)
			    {
				 System.out.println("用户角色关系添加成功！");
			    	return "ok";
			    }
			    else
			    {
			    	System.out.println("用户角色关系添加失败！");
			    	return "wrong";
			    }
	}
	
	
}
