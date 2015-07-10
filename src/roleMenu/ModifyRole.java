package roleMenu;



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
	
	public List getAllRole(String makeuser,String makerole)
	{
		String sql="select * from ices_user_role where user_name='"+makeuser+"' and role_name='"+makerole+"'";
		DB db=new DB();
		ResultSet rs=db.query(sql);
		String make="";
		try {
			while(rs!=null && rs.next())
			{
				make=rs.getString("user_role_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			sql="select * from ices_sc_role where make='"+make+"'";
		
		ResultSet rs1=db.query(sql);
		List l=new ArrayList();
		List l1=new ArrayList();
		int num=1;
		try {
			while(rs1!=null && rs1.next())
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
	

}
