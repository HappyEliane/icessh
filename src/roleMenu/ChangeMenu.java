package roleMenu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.menu;
import db.DB;

public class ChangeMenu {
	
	
	
	public List getMenu(String user,String role)
	{
		
		List l=new ArrayList();
		 List l1=new ArrayList();
		 
		 String sql="select * from ices_user_role where user_name='"+user+"' and role_name='"+role+"'";
			DB db=new DB();
			
			String make="";
			ResultSet rs0=db.query(sql);
			try {
				while(rs0!=null && rs0.next())
				{
					
					make=rs0.getString("user_role_id");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		 sql="select * from ices_menu where make='"+make+"' ";
		//DB db=new DB();
		ResultSet rs=db.query(sql);
		try {
			int i=1;
			while(rs!=null && rs.next())
			{
				menu m=new menu();
				m.setId(rs.getString("menu_id"));
				m.setName(rs.getString("menu_name"));
				String me=rs.getString("menu");
				m.setMenu(me);
				
				
				m.setMake(rs.getString("make"));
				m.setMakedate(rs.getString("makedate"));
				m.setNum(""+i);
			//	m.setShortmenu(me.substring(0, 50));
				i++;
				l1.add(m);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		l.add(l1);
		
		return l;
	}
	

}
