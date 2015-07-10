package sc;

import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.jsp.jstl.sql.Result;

import db.DB;

public class userLogin {
	public String login(String name, String pw,String org,String role)
	{
		String orgid="";
		if(org.equals("家庭用户"))
			orgid="HC00001";
		else if(org.equals("供应商"))
			orgid="PC00001";
		else if(org.equals("服务中心"))
			orgid="SC00001";
		else if(org.equals("业务中心"))
			orgid="BC00001";
		String sql="select * from ices_user where user_name='"+name+"' and password='"+pw+"' ";
		DB db=new DB();
		ResultSet rs=db.query(sql);
		String userid="";
		try {
			while( rs.next())
			{
				userid=rs.getString("user_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql1="select * from ices_user_role where user_id='"+userid+"' and role_name='"+role+"'";
		ResultSet rs2=db.query(sql1);
		String roleid="";
		try {
			while(rs2!=null && rs2.next())
			{
				roleid=rs2.getString("role_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(roleid.equals(""))
		{
			return "wrong";
		}
		else
			return "ok";
	}
	public String getMenu(String user,String role)
	{
		String bmenu=getBasicMenu(role);
		String smenu=getBusinessMenu(user,role);
		String menu="";
		menu+=bmenu;
		menu+="<menu name=\"我的业务\">";
		menu+=smenu;
		menu+="</menu>";
		return menu;
	}
	String getBasicMenu(String role)
	{
		String sql="select * from ices_sc_role where role_name='"+role+"'";
		DB db=new DB();
		ResultSet rs=db.query(sql);
		String roleid="";
		try {
			while(rs!=null && rs.next())
			{
				roleid=rs.getString("sc_role_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql="select * from ices_role_menu where roleid='"+roleid+"'";
		ResultSet rs1=db.query(sql);
		String menuid="";
		try {
			while(rs1!=null && rs1.next())
			{
				menuid=rs1.getString("menuid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql="select * from ices_menu where menu_id='"+menuid+"'";
		ResultSet rs2=db.query(sql);
		String menu="";
		try {
			while(rs2!=null && rs2.next())
			{
				menu=rs2.getString("menu");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.close();
		return menu;
	}
	String getBusinessMenu(String name,String role)
	{
		DB db=new DB();
		String sql1="select * from ices_user_role where user_name='"+name+"' and role_name='"+role+"'";
		ResultSet rs2=db.query(sql1);
		String make="";
		try {
			while(rs2!=null && rs2.next())
			{
				make=rs2.getString("user_role_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		String sql="select * from ices_orderbusiness as a, ices_menu as b  where a.menuid=b.menu_id and  a.orderid='"+make+"'";
		String menu="";
		ResultSet rs=db.query(sql);
		try {
			while(rs!=null &&rs.next())
			{
				menu+="<order name=\""+rs.getString("a.busname")+"\">";
				menu+=rs.getString("b.menu");
				menu+="</order>";
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("123"+menu);
		return menu;
	}

}
