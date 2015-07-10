package businessCenter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.UserRole;
import db.DB;

public class URole {
	public List queryUR(String name,String role,String sd,String ed,String loginuser,String loginrole)
	{
		String sql="select * from ices_user_role where user_name='"+loginuser+"' and role_name='"+loginrole+"'";
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
		if(!name.equals(""))
			con1=" and user_name like '%"+name+"%' ";
		if(!role.equals(""))
			con1+=" and role_name like '%"+role+"%'";
		if(!sd.equals("") && !ed.equals(""))
		{
			con1+=" and update_date<'"+ed+"' and update_date>'"+sd+"'";
		}
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
