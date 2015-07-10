package userrole;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.CloudRole;
import db.DB;
import tool.CreateKey;


public class ModifyRole {
	public String insertRole(String rolename,String rolekind,String makeuser,String makerole,String ifuse) throws NumberFormatException, SQLException
	{
		System.out.println("角色注册！");
		
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
		
		String temp_str="";//获取当前日期   
	    Date dt = new Date();   
	    //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");   
	    temp_str=sdf.format(dt); 
			CreateKey ck=new CreateKey("ices_sc_role","sc_role_id","CRI");
			 sql="INSERT INTO ices_sc_role( " +
				    " sc_role_id,  role_id, role_name, if_use, update_date, make )"+
				    " VALUES ('"+ck.getKey()+"', '"+rolekind+"',  '"+rolename+"',  '"+ifuse+"', '"+temp_str+"', '"+make+"')";
			 
			 int result=db.insert(sql);
			 db.close();
			 if(result!=0)
			    {
				 System.out.println("角色注册成功！");
			    	return "ok";
			    }
			    else
			    {
			    	System.out.println("角色注册失败！");
			    	return "wrong";
			    }
	}
	public String delRole(String delid)
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
		String sql="delete from ices_sc_role where sc_role_id in ("+delsql+")";
		DB db=new DB();
		int result=db.delete(sql);
		if(result!=0)
		{
			return "ok";
		}
		else
			return "wrong";
	}
	public String queryRoleName(String rname)
	{
		String sql="select * from ices_sc_role where role_name='"+rname+"'";
		DB db=new DB();
		ResultSet rs=db.query(sql);
		String result="ok";
		try {
			while(rs.next())
			{
				db.close();
				result="wrong";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.close();
		return result;
	}
	public List getUserRole(String userid)
	{
		List l=new ArrayList();
		 List l3=new ArrayList();
		    String sql5="select * from ices_sc_role as a, ices_user_role as b where a.sc_role_id=b.role_id and  b.user_id ='"+userid+"'";
		   DB db=new DB();
		    ResultSet rs4=db.query(sql5);
		    int id=1;
          try {
			while(rs4.next())
				 {			
			 	 System.out.println("查到结果");
			 	 CloudRole o4=new CloudRole();
					 o4.setCloudroleid(rs4.getString("a.sc_role_id"));
					 o4.setRoleid(rs4.getString("a.role_id"));
					 o4.setMadeuser(rs4.getString("a.make"));
					 o4.setState(rs4.getString("a.if_use"));
					 o4.setRolename(rs4.getString("a.role_name"));
					 o4.setUserrole(rs4.getString("b.user_role_id"));
					 o4.setUpdate(rs4.getString("a.update_date"));
					 o4.setId(Integer.toString(id));
					 id++;
					 l3.add(o4);
				 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
          l.add(l3);
          return l;
	}
	public List getAllRole(String makeuser,String makerole,String rolename)
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
		if(rolename.equals(""))
			sql="select * from ices_sc_role where make='"+make+"'";
		else
			sql="select * from ices_sc_role where make='"+make+"' and role_name='"+rolename+"'";
		ResultSet rs1=db.query(sql);
		List l=new ArrayList();
		List l1=new ArrayList();
		int num=1;
		try {
			while(rs1.next())
			{
				CloudRole role=new CloudRole();
				role.setCloudroleid(rs1.getString("sc_role_id"));
				role.setRoleid(rs1.getString("role_id"));
				role.setRolename(rs1.getString("role_name"));
				role.setState(rs1.getString("if_use"));
				role.setUpdate(rs1.getString("update_date"));
				role.setNid(""+num);
				num++;
				l1.add(role);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		l.add(l1);
		
		return l;
	}
	public String changeRoleInfo(String roleid, String state)
	{
		 String sql="UPDATE ices_sc_role SET  if_use = '"+state+"' WHERE sc_role_id = '"+roleid+"'";
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

}
