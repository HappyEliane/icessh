package roleMenu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.rolemenu;
import db.DB;
import tool.CreateKey;

public class ModifyRoleMenu {
	public String addRoleMenu(String roleid,String menuid,String user,String role) throws NumberFormatException, SQLException
	{
		String sql="select * from ices_user_role where user_name='"+user+"' and role_name='"+role+"'";
		DB db=new DB();
		
		String make="";
		ResultSet rs=db.query(sql);
		try {
			while(rs!=null && rs.next())
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
	    
	    String sql3="select * from ices_role_menu where roleid='"+roleid+"' and menuid='"+menuid+"'";
	    ResultSet rs2=db.query(sql3);
	    while(rs2!=null && rs2.next())
	    {
	    	return "do";
	    }
	    String sql4="select * from ices_role_menu where roleid='"+roleid+"'";
	    ResultSet rs3=db.query(sql4);
	    while(rs3!=null && rs3.next())
	    {
	    	return "done";
	    }
	    CreateKey ck=new CreateKey("ices_role_menu","id","RM");
	    String sql2="INSERT INTO ices_role_menu( " +
	    " id, roleid, menuid,  makedate, state,make )"+
	    " VALUES ('"+ck.getKey()+"','"+roleid+"', '"+menuid+"', '"+temp_str+"', '"+"1"+"', '"+make+"')";
	    int re=db.insert(sql2);
	    db.close();
	    if(re==0)
	    {
	    	return "wrong";
	    }
	    else
	    {
	    	return "ok";
	    }
	}
	public List getRoleMenu(String rolename,String loginuser,String loginrole,String menuname, String sd,String ed)
	{
		//由登录的用户名和角色名分别查询对应的id值
		String sql="select * from ices_user_role where user_name='"+loginuser+"' and role_name='"+loginrole+"'";
		DB db=new DB();
		
		String make="";
		ResultSet rs=db.query(sql);
		try {
			while(rs!=null &&rs.next())
			{
				make=rs.getString("user_role_id");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql1="";
		if(sd!="" && ed!="")
		{
			sql1=" and makedate> '"+sd+"' and makedate<'"+ed+"'";
		}
		String sql3="";
			sql3="select b.id, b.roleid, b.menuid, a.menu_name, c.role_name, b.makedate,b.state " +
					"from ices_menu as a, ices_role_menu as b, ices_sc_role as c " +
					"where b.menuid=a.menu_id and b.roleid=c.sc_role_id and b.make='"+make+"' ";
		if(menuname!="")
		{
			sql3+=" and a.menu_name like '%"+menuname+"%' ";
		}
		if(rolename!="")
		{
			sql3+=" and c.role_name like '%"+rolename+"%' ";
		}
		if(sd!="" && ed!="")
		{
			sql3+=" and b.makedate>'"+sd+"' and b.makedate<'"+ed+"' ";
		}
		System.out.println(sql3);
		ResultSet rs1=db.query(sql3);
		List l=new ArrayList();
		List l1=new ArrayList();
		int i=1;
		try {
			while(rs1!=null && rs1.next())
			{
				rolemenu rm=new rolemenu();
				rm.setId(rs1.getString("b.id"));
				rm.setMenuid(rs1.getString("b.menuid"));
				rm.setMenuname(rs1.getString("a.menu_name"));
				rm.setRoleid(rs1.getString("b.roleid"));
				rm.setRolename(rs1.getString("c.role_name"));

				rm.setMakedate(rs1.getString("b.makedate"));
				rm.setState(rs1.getString("b.state"));
				rm.setNum(""+i);
				i++;
				l1.add(rm);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		l.add(l1);
		return l;
	}
	public String delRoleMenu(String id)
	{
		String sql="delete from ices_role_menu where id='"+id+"'";
		DB db=new DB();
		int r=db.delete(sql);
		if(r!=0)
		{
			return "ok";
		}
		return "wrong";
	}

}
