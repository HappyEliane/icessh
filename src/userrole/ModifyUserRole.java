package userrole;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.UserRole;
import db.DB;
import tool.CreateKey;

public class ModifyUserRole {
	public String insertUserRole(String userid,String roleid,String makeuser,String makerole) throws SQLException
	{
		String sql="select * from ices_user_role where user_name='"+makeuser+"' and role_name='"+makerole+"'";
		DB db=new DB();
		ResultSet rs=db.query(sql);
		String make="";
		try {
			while(rs.next())
			{
				make=rs.getString("user_role_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		String sql2="select * from ices_sc_role where sc_role_id='"+roleid+"'";
		ResultSet rs2=db.query(sql2);
		String rolename="";
		String roletype="";
		
		while (rs2.next())
		{
			rolename=rs2.getString("role_name");
			roletype=rs2.getString("role_id");
		}
		
		String sql4="select * from ices_user where user_id='"+userid+"'";
		ResultSet rs4=db.query(sql4);
		String username="";
		while(rs4.next())
		{
			username=rs4.getString("user_name");
		}
		String temp_str="";//获取当前日期   
	    Date dt = new Date();   
	    //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");   
	    temp_str=sdf.format(dt); 
	    
	    
	    String sql5="select * from ices_user_role where user_id='"+userid+"' and role_id='"+roleid+"'";
	    ResultSet rs5=db.query(sql5);
	    boolean re=false;
	    while(rs5.next())
	    {
	    	re=true;
	    }
	    if(re)
	    {
	    	return "do";
	    }
	    else
	    {
		CreateKey ck=new CreateKey("ices_user_role","user_role_id","URI");
		 String sql3="INSERT INTO ices_user_role( " +
		    " user_role_id, user_id, user_name, role_id, role_type, role_name,  update_date, make )"+
		    " VALUES ('"+ck.getKey()+"','"+userid+"', '"+username+"', '"+roleid+"',  '"+roletype+"', '"+rolename+"', '"+temp_str+"', '"+make+"')";
	    	
		 int re2=db.insert(sql3);//插入用户-角色关系表
		 if(re2!=0)
		 {
			 return "ok";
		 }
		 else 
		 {
			 return "wrong";
		 }
	    }
		
	}
	public String delUserRole(String userroleid)
	{
		String sql="delete from ices_user_role where user_role_id='"+userroleid+"'";
		System.out.println(sql);
		DB db=new DB();
		int r1=db.delete(sql);
		if(r1!=0)
		{
			System.out.println("删除成功");
			return "ok";
		}
		else
		{
			System.out.println("删除失败");
			return "wrong";
		}
	}
	public List getUserRole(String makeuser,String makerole,String username,String rolename)
	{
		
		String sql="select * from ices_user_role where user_name='"+makeuser+"' and role_name='"+makerole+"'";
		DB db=new DB();
		ResultSet rs=db.query(sql);
		String make="";
		try {
			while(rs.next())
			{
				make=rs.getString("user_role_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String con1="";
		if(!username.equals(""))
			con1=" and user_name='"+username+"' ";
		if(!rolename.equals(""))
			con1+=" and role_name='"+rolename+"'";
		sql="select * from ices_user_role where make='"+make+"' "+con1;
         ResultSet rs5=db.query(sql);
         int num=0;
         List l=new ArrayList();
         List l1=new ArrayList();
		 try {
			while(rs5.next())
			 {
				 num++;
				 UserRole o5=new UserRole();
				 o5.setId(rs5.getString("user_role_id"));
				 o5.setUserroleid(rs5.getString("user_role_id"));
				 o5.setRoleid(rs5.getString("role_id"));
				 o5.setRolename(rs5.getString("role_name"));	
				 
				 o5.setUserid(rs5.getString("user_id"));
				 o5.setRoletype(rs5.getString("role_type"));
				 o5.setUsername(rs5.getString("user_name"));
				 o5.setUpdate(rs5.getString("update_date"));
				 o5.setNid(""+num);
				 l1.add(o5);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 l.add(l1);
         System.out.println("用户-角色关系表查询结束");
         return l;
	}

}
