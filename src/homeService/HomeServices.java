package homeService;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.component;
import bean.homeService;
import db.DB;
import tool.CreateKey;

public class HomeServices {
	public String addHomeService(String homeid,String servicethemeid,String serviceid,String permission) throws NumberFormatException, SQLException
	{
		//String sql="select * from homeservice where user_name='"+user+"' and role_name='"+role+"'";
		DB db=new DB();
		
		String make="";
		//ResultSet rs=db.query(sql);
		//try {
		//	while(rs!=null && rs.next())
		//	{
			
			//	make=rs.getString("user_role_id");
			//}
		//} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
		//}
		
		
		/*String temp_str="";//获取当前日期   
	    Date dt = new Date();   
	    //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");   
	    temp_str=sdf.format(dt); 
	    
	    String sql3="select * from homeuser where homeid='"+homeid+"' and servicethemeid='"+servicethemeid+"'";
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
	    }*/
		 String sql3="select * from homeservice where homeid='"+homeid+"' and serviceid='"+serviceid+"'";
		    ResultSet rs2=db.query(sql3);
		    while(rs2!=null && rs2.next())
		    {
		    	return "do";
		    }
	    CreateKey ck=new CreateKey("homeservice","id","HS");
	    String sql2="INSERT INTO homeservice( " +
	    " id, homeid, servicethemeid,serviceid,permission)"+
	    " VALUES ('"+ck.getKey()+"','"+homeid+"', '"+servicethemeid+"', '"+serviceid+"', '"+permission+"')";
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
	public List getHomeService(String homename,String servicetheme,String service)
	{
		//由登录的用户名和角色名分别查询对应的id值
		//String sql="select * from ices_user_role where user_name='"+loginuser+"' and role_name='"+loginrole+"'";
		DB db=new DB();
		
		//String make="";
		//ResultSet rs=db.query(sql);
		//try {
		//	while(rs!=null &&rs.next())
		//	{
		//		make=rs.getString("user_role_id");
				
		//	}
		//} catch (SQLException e) {
			// TODO Auto-generated catch block
		//	e.printStackTrace();
	//	}
		//String sql1="";
		//if(sd!="" && ed!="")
		//{
		//	sql1=" and makedate> '"+sd+"' and makedate<'"+ed+"'";
		//}
		String sql3="";
			sql3="select b.id, b.homeid, b.servicethemeid, b.servicethemeid,b.serviceid,b.permission,a.comp_name, c.homename,d.comp_theme_name  " +
					"from ices_component as a, homeservice as b, homeinfo as c ,ices_comp_theme as d " +
					"where b.serviceid=a.comp_id and b.homeid=c.homeid and b.servicethemeid=d.comp_theme_id ";
			
			//sql3="select b.id, b.roleid, b.menuid, a.menu_name, c.role_name, b.makedate,b.state " +
			//"from homeinfo as a, ices_role_menu as b, ices_sc_role as c " +
			//"where b.menuid=a.menu_id and b.roleid=c.sc_role_id and b.make='"+make+"' ";
			
		if(homename!="")
		{
			sql3+=" and c.homename like '%"+homename+"%' ";
		}
		if(service!="")
		{
			sql3+=" and a.comp_name like '%"+service+"%' ";
		}
		if(servicetheme!="")
		{
			sql3+=" and d.comp_theme_name like '%"+servicetheme+"%' ";
		}
		System.out.println(sql3);
		ResultSet rs1=db.query(sql3);
		List l=new ArrayList();
		List l1=new ArrayList();
		int i=1;
		try {
			while(rs1!=null && rs1.next())
			{
				homeService rm=new homeService();
				rm.setNum(""+i);
				rm.setID(rs1.getString("b.id"));
				rm.setHomeID(rs1.getString("b.homeid"));
				rm.setHomeName(rs1.getString("c.homename"));
				rm.setServiceID(rs1.getString("b.serviceid"));
				rm.setServiceName(rs1.getString("a.comp_name"));
				rm.setServicethemeID(rs1.getString("b.servicethemeid"));
				rm.setServicethemeName(rs1.getString("d.comp_theme_name"));
				rm.setPermission(rs1.getString("b.permission"));
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


	public String delHomeService(String id)
	{
		String sql="delete from homeservice where id='"+id+"'";
		DB db=new DB();
		int r=db.delete(sql);
		if(r!=0)
		{
			return "ok";
		}
		return "wrong";
	}

}
