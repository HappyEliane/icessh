package icesmenu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.CloudRole;
import bean.menu;
import db.DB;
import tool.CreateKey;

public class ChangeMenu {
	public String insertMenu(String username, String rolename,String menu,String menuname) throws NumberFormatException, SQLException
	{
		String sql="select * from ices_user_role where user_name='"+username+"' and role_name='"+rolename+"'";
		DB db=new DB();
		String roleid="";
		String make="";
		ResultSet rs=db.query(sql);
		try {
			while(rs!=null && rs.next())
			{
				roleid=rs.getString("role_id");
				make=rs.getString("user_role_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(make=="" )
			return "wrong";
		
		String temp_str="";//获取当前日期   
	    Date dt = new Date();   
	    //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");   
	    temp_str=sdf.format(dt); 
	    
	    CreateKey ck=new CreateKey("ices_menu","menu_id","MENU");
	    String menukey=ck.getKey();
	    String sql2="INSERT INTO ices_menu( " +
	    " menu_id, menu_name, menu, makedate, make )"+
	    " VALUES ('"+menukey+"','"+menuname+"', '"+menu+"', '"+temp_str+"',  '"+make+"')";
	    int re=db.insert(sql2);
	    db.close();
	    if(re==0)
	    {
	    	return "wrong";
	    }
	    else
	    {
	    	String fmenuid=getMenuid(roleid);
	    	List add=getComp(menu);
	    	insertComp(menukey,add,fmenuid);
	    	
	    	
	    	return "ok";
	    }
	}
	public String getMenuid(String roleid)
	{
		String sql="select * from ices_role_menu where roleid='"+roleid+"'";
		String menuid="";
		DB db=new DB();
		ResultSet rs=db.query(sql);
		try {
			while(rs!=null && rs.next())
			{
				menuid=rs.getString("menuid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.close();
		return menuid;
	}
	
	//通过此函数，能够得到menu中所有的构件id
	public List getComp(String menu) throws NumberFormatException, SQLException
	{
		System.out.println(menu);
		 String[] a=menu.split(">"); 
		 List b=new ArrayList();
		 String test="id=";
		 for(int i=0;i<a.length;i++)
		 {
			 String temp=a[i];
			 String re="";
			int start=temp.indexOf(test);
			int end=temp.lastIndexOf("\"");
			if(start!=-1)
			{
				re=temp.substring(start+4, end);
				
			//	if(!b.contains(re))
				{
					System.out.println(re);
					b.add(re);
				//	insertComp(q,re);
				}
			}
		 }
		
		return b;
	}
	public String getID(String menuid,String compid)
	{
		String sql="select * from ices_menu_comp where menuid='"+menuid+"' and compid='"+compid+"'";
		DB db=new DB();
		ResultSet rs=db.query(sql);
		String id="";
		try {
			while(rs!=null && rs.next())
			{
				id=rs.getString("id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.close();
		return id;
	}
	public void insertComp(String menuid,List add,String fmenuid) throws NumberFormatException, SQLException
	{
		DB db=new DB();
		CreateKey ck=new CreateKey("ices_menu_comp","id","MC");
		for(int i=0;i<add.size();i++)
		{
			String temp=(String) add.get(i);
			String fid=getID(fmenuid,temp);
			String key=ck.getKey();
		 String sql2="INSERT INTO ices_menu_comp ( " +
		    " id, menuid, compid, fid)"+
		    " VALUES ('"+key+"', '"+menuid+"', '"+temp+"', '"+fid+"')";
		    
		    int re=db.insert(sql2);
		    
		}
		    db.close();
		  
	}
	
	
	public List getMenu(String name,String sd,String ed,String user,String role)
	{
		String sql1="";
		String sql2="";
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
			
		if(name!="")
		{
			sql1=" and menu_name like '%"+name+"%' ";
		}
		if(sd!="" && ed!="")
		{
			sql2=" and makedate <'"+ed+"' and makedate >'"+sd+"' ";
		}
		 sql="select * from ices_menu where make='"+make+"' "+sql1+sql2;
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
	public String delMenu(String menuid)//删除菜单表数据
	{
		String result="wrong";
		String sql="delete from ices_menu where menu_id='"+menuid+"'";
		DB db=new DB();
		int re=db.delete(sql);
		db.close();
			result=delComp(menuid);
			result=delRoleMenu(menuid);
			if(re!=0)
				result="ok";
		return result;
		
	}
	public String delComp(String menuid)//删除菜单-构件表数据
	{
		String sql="delete from ices_menu_comp where menuid='"+menuid+"'";
		DB db=new DB();
		int result=db.delete(sql);
		db.close();
		if(result!=0)
			return "ok";
		return "wrong";
	}
	public String delRoleMenu(String menuid)//删除角色-菜单表数据
	{
		String sql="delete from ices_role_menu where menuid='"+menuid+"'";
		DB db=new DB();
		int result=db.delete(sql);
		db.close();
		if(result!=0)
			return "ok";
		return "wrong";
	}
	public List getRole(String menuid)
	{
		String sql="select * from ices_role_menu as a, ices_sc_role as b  where a.roleid=b.sc_role_id and a.menuid='"+menuid+"'";
		DB db=new DB();
		List l=new ArrayList();
		List l1=new ArrayList();
		ResultSet rs=db.query(sql);
		int num=0;
		try {
			while(rs!=null && rs.next())
			{
				num++;
				CloudRole cr=new CloudRole();
				cr.setCloudroleid(rs.getString("b.sc_role_id"));
				cr.setRoleid(rs.getString("role_id"));
				cr.setUpdate(rs.getString("update_date"));
				cr.setRolename(rs.getString("role_name"));
				cr.setNid(""+num);
				l1.add(cr);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		l.add(l1);
		return l;
	}


}
