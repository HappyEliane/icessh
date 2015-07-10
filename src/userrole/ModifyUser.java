package userrole;





import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.User;
import db.DB;
import tool.CreateKey;

public class ModifyUser {
	public String addUser(String username,String userpass,String makeid,String roleid,String rolename,String roletype,String makerole) throws NumberFormatException, SQLException
	{
		String sql="select * from ices_user_role where user_name='"+makeid+"' and role_name='"+makerole+"'";
		DB db=new DB();
		ResultSet rs=db.query(sql);
		String make="";
		
		String cloud="";
		try {
			while(rs.next())
			{
				make=rs.getString("user_role_id");
			//	cloud=rs.getString("cloud");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(make.equals("") || make==null)
			return "wrong";
		else
		{

			 sql="select * from ices_user where user_name='"+makeid+"'";
			 rs=db.query(sql);
			 while(rs.next())
			 {
				 cloud=rs.getString("cloud");
			 }
			String temp_str="";//获取当前日期   
		    Date dt = new Date();   
		    //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");   
		    temp_str=sdf.format(dt); 
		    
		    String user_id="";//用户主键
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
				return "wrong";
			}
			String sql2="INSERT INTO ices_user( " +
		    " user_id, user_name, password, make_date, user_state, cloud, make )"+
		    " VALUES ('"+user_id+"','"+username+"', '"+userpass+"', '"+temp_str+"',  '"+"1"+"', '"+cloud+"', '"+make+"')";
			
			int result=db.insert(sql2);
			 if(result!=0)
			    {
				 System.out.println("用户注册成功！");
				 
				 if(roleid!="")
				 {
					 System.out.println("用户角色关联注册！");
					 CreateKey ck=new CreateKey("ices_user_role","user_role_id","URI");
					 String sql3="INSERT INTO ices_user_role( " +
					 	" user_role_id, user_id, user_name, role_id, role_type, role_name,  update_date, make )"+
					 	" VALUES ('"+ck.getKey()+"','"+user_id+"', '"+username+"', '"+roleid+"',  '"+roletype+"', '"+rolename+"',  '"+temp_str+"', '"+make+"')";
			    	
					 int re2=db.insert(sql3);//插入用户-角色关系表
					 if(re2!=0)
					 {
						 System.out.println("用户角色关联注册成功！");
						 return "ok";
					 }
					 else 
					 {
						 System.out.println("用户角色关联失败！");
						 return "wrong";
					 }
				 }
				 else
				 {
					 return "ok";
				 }
			    }
			    else
			    {
			    	System.out.println("用户注册失败！");
			    	return "wrong";
			    }
		}
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
		return num;
	}
	public String delUser(String delid)
	{
		System.out.println(delid);
		String [] uid=delid.split(",");
		String delsql="";
		for(int i=0;i<uid.length;i++)
		{
			delsql+="'"+uid[i]+"'";
			if(i!=uid.length-1)
				delsql+=", ";
		}
		//删除用户表
		String sql="delete from ices_user where user_id in ("+delsql+")";
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
	public List getUser(String makeuser,String makerole,String username,String startdate,String enddate)
	{
		 System.out.println("查询用户");
		 String condition = " 1=1";
		
		 List l=new ArrayList();
		 List l1=new ArrayList();
		
		 
		 if(!username.equals(""))
		    {
		    	condition+=" and a.user_name like '"+username+"'";
		    }
		 if(!startdate.equals("")&&!enddate.equals(""))
			{
		    	condition+=" and a.make_date >'"+startdate+"' and a.make_date<'"+enddate+"'";
			}
		    String sql2="select a.user_id, a.user_name,a.password, a.make_date,  a.user_state, i.user_name,i.role_name " +
		    		"from ices_user as a, ices_user_role as i where a.make=i.user_role_id  and i.user_name='"+makeuser+"' and i.role_name='"+makerole+"' and " +condition;
		    System.out.println(sql2);
		    DB db=new DB();
		    ResultSet rs2=db.query(sql2);
		    int num=0;
		    try {
				while(rs2.next())
				 {
					System.out.println("查到结果！");
					num++;
					 User o2=new User();
					 o2.setId(rs2.getString("a.user_id"));
					 System.out.println(rs2.getString("a.user_id")+"");
					 o2.setUserid(rs2.getString("a.user_id"));
					 o2.setUsername(rs2.getString("a.user_name"));
					 o2.setMakedate(rs2.getString("a.make_date"));
					 o2.setMakepersonid(rs2.getString("i.user_name"));
					 o2.setPassword(rs2.getString("a.password"));
					 o2.setMakeroleid(rs2.getString("i.role_name"));
					// System.out.println(rs2.getString("i.user_name"));
					
					 o2.setUserstate(rs2.getString("a.user_state"));
					 o2.setNid(""+num);
					 l1.add(o2);
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		    l.add(l1);
		    System.out.println("查询用户结束");
		    System.out.println("length="+l.size());
		   
		   return l;
	}
	public String ModifyUserInfo(String userid,String pass,String state)
	{
		 String sql="UPDATE ices_user SET password = '"+pass+"', user_state = '"+state+"' WHERE user_id = '"+userid+"'";
		DB db=new DB();
		 int r=db.update(sql);
		 String result="wrong";
		 if(r!=0)
		 {
			 result="ok";
		 }
		 else
			 result="wrong";
		 System.out.println("更新完成，"+result);
		 return result;
	}
	public List getUserRole(String roleid)
	{
		 String sql="select * from ices_user as a, ices_user_role as b where a.user_id=b.user_id  and b.role_id ='"+roleid+"'";
		 DB db=new DB();
		List l=new ArrayList();
		List l1=new ArrayList();
		 ResultSet rs2=db.query(sql);
		    int num=0;
		    try {
				while(rs2.next())
				 {
					System.out.println("查到结果！");
					num++;
					 User o2=new User();
					 o2.setId(rs2.getString("a.user_id"));
					
					 o2.setUserid(rs2.getString("a.user_id"));
					 o2.setUsername(rs2.getString("a.user_name"));
					 o2.setMakedate(rs2.getString("a.make_date"));
					 o2.setMakepersonid(rs2.getString("a.user_name"));
					 o2.setUserrole(rs2.getString("b.user_role_id"));
					 o2.setUserstate(rs2.getString("a.user_state"));
					 o2.setNid(""+num);
					 l1.add(o2);
				 }
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  
		    l.add(l1);
		 return l;
	}

}
