package CompReg;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import bean.component;
import db.DB;
import tool.CreateKey;

public class ModifyComponent {
	public String AddComp(String name,String url,String info,String maker,String userName,String org,String theme) throws NumberFormatException, SQLException
	{
		String sql="select * from ices_user where user_name='"+userName+"'";
		DB db=new DB();
		ResultSet rs=db.query(sql);
		String userid="";
		try {
			while(rs!=null && rs.next())
			{
				userid=rs.getString("user_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(userid!="")
		{
			String temp_str="";//获取当前日期   
		    Date dt = new Date();   
		    //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");   
		    temp_str=sdf.format(dt); 
		    String[] A=org.split(",");
			 String home=A[0];
			 String provider=A[1];
			 String business=A[2];
			 String center=A[3];
		    CreateKey ck=new CreateKey("ices_component","comp_id","COM");
		    String sql2="INSERT INTO ices_component( " +
		    " comp_id, comp_name, comp_maker, comp_url, comp_info, makerid, makedate, home, provider, business, center, comp_theme_id)"+
		    " VALUES ('"+ck.getKey()+"','"+name+"', '"+maker+"', '"+url+"',  '"+info+"',  '"+userid+"', '"+temp_str+"', '"+home+"', '"+provider+"', '"+business+"', '"+center+"', '"+theme+"')";
		    
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
		else
		{
			db.close();
			return "wrong";
		}
	}
	public List queryComp(String name,String info,String dev,String sd,String ed,String org)
	{
		System.out.println("查询构件");
		String sql1="";
		String sql2="";
		String sql3="";
		String sql4="";
		String sql5="";
		String sql6="";
		if(name!="")
		{
			sql1=" and comp_name like '%"+name+"%' ";
		}
		if(info!="")
		{
			sql2=" and comp_info like '%"+info+"%' ";
		}
		if(dev!="")
		{
			sql3=" and comp_maker like '%"+dev+"%' ";
		}
		if(sd!="" && ed!="")
		{
			sql4="and  makedate > '"+sd+"' and makedate <'"+ed+"' ";
		}
		if(org!="")
		{
			if(!org.equals("0"))
			{
				if(org.equals("1"))
				{
					sql6=" and  center='1' ";
				}
				if(org.equals("2"))
				{
					sql6=" and business='1' ";
				}
				if(org.equals("3"))
				{
					sql6=" and provider='1' ";
				}
				if(org.equals("4"))
				{
					sql6=" and home='1' ";
				}
					
			}
		}
		String sql7=" 1=1 ";
		String sql8="select * from ices_component where "+sql7+sql1+sql2+sql3+sql4+sql6;
		DB db=new DB();
		ResultSet rs=db.query(sql8);
		List l=new ArrayList();
		int i=1;
		try {
			while(rs!=null && rs.next())
			{
				component cp=new component();
				cp.setComId(rs.getString("comp_id"));
				cp.setComName(rs.getString("comp_name"));
				cp.setComURL(rs.getString("comp_url"));
				cp.setComDev(rs.getString("comp_maker"));
				cp.setCominfo(rs.getString("comp_info"));
			//	cp.setComOrg(rs.getString("comp_org"));
				cp.setComMaker(rs.getString("makerid"));
				
				cp.setComDate(rs.getString("makedate"));
				
				cp.setHome(rs.getString("home"));
				cp.setProvider(rs.getString("provider"));
				cp.setBusiness(rs.getString("business"));
				cp.setCenter(rs.getString("center"));
				String rorg=(rs.getString("home").equals("1")?"家庭用户,":"")+(rs.getString("provider").equals("1")?"供应商用户,":"")+(rs.getString("business").equals("1")?"业务中心,":"")+(rs.getString("center").equals("1")?"服务中心":"");
				System.out.println(rorg);
				cp.setComOrg(rorg);
				cp.setNum(""+i);
				i++;
				l.add(cp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
		
	}
	public List chooseComp(String name,String info,String dev,String sd,String ed,String org,String menu)
	{
		System.out.println(menu);
		List c=queryComp( name, info, dev, sd, ed, org);
		System.out.println(c.toString());
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
				
				if(!b.contains(re))
				{
					System.out.println(re);
					b.add(re);
				
				}
			}
		 }
		 List d1=new ArrayList();
		System.out.println(c.size());
		 for(int j=0;j<c.size();j++)
		 {
			 component com=(component) c.get(j);
			 String object=com.getComId();
			
			 if(!b.contains(object))
			 {
				 System.out.println(object);
				 d1.add(com);
			 }
		 }
		
		 return d1;
	}
	public String delComp(String compid)
	{
		Map<String,String> allMenu=new HashMap<String,String>();
		String sql="select * from ices_menu";
		DB db=new DB();
		ResultSet rs=db.query(sql);
		try {
			while(rs!=null && rs.next())
			{
				String menuid=rs.getString("menu_id");
				String menu=rs.getString("menu");
				allMenu.put(menuid, menu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String,String> result=new HashMap<String,String>();
		result=UpdateMenu(allMenu,compid);//更新所有的菜单
		String r1=ChangeMenu(result);//将更新后的菜单存入数据库
		DeleteMenuComp(compid);//删除菜单-构件数据表
		String r2=deleteComp(compid);
		if(r1.equals("ok") && r2.equals("ok"))
		{
			return "ok";
		}
		return "wrong";
		
	}
	private String deleteComp(String compid)
	{
		DB db=new DB();
		int rs;
		String sql="delete from ices_component where comp_id='"+compid+"'";
		rs=db.delete(sql);
		db.close();
		if(rs!=0)
		{
			return "ok";
		}
		else
			return "wrong";
	}
	private String newMenu(String menu,String compid)
	{
		String []array=menu.split(">");
		String result="";
		for(int i=0;i<array.length;i++)
		{
			String m=array[i];
			System.out.println(m);
			int index=m.indexOf("id=");
			System.out.println(index);
			int end=m.indexOf("\"/");
			System.out.println(end);
			if(index!=-1 && end!=-1)
			{
				String id=m.substring(index+4, end);
				System.out.println(id);
				if(!compid.equals(id))
				{
					result+=m;
					result+=">";
				}
			}
			else
			{
				result+=m;
				result+=">";
			}
		}
		return result;
	}
	Map<String,String> UpdateMenu(Map<String,String>menu,String compid)
	{
		Iterator it=menu.keySet().iterator();
		
		Map<String,String> result=new HashMap<String,String>();
		while(it.hasNext())
		{
			String k=(String) it.next();
			String m=(String) menu.get(k);
			System.out.println(m);
			String men=newMenu(m,compid);
			result.put(k, men);
			
		}
		return result;
	}
	private String ChangeMenu(Map menu)
	{
		Iterator it=menu.keySet().iterator();
		DB db=new DB();
		String sql="";
		int rs;
		int r=0;
		while(it.hasNext())
		{
			String k=(String) it.next();
			String men=(String) menu.get(k);
			sql="UPDATE ices_menu set menu='"+men+"' where menu_id='"+k+"'";
			r=db.update(sql);
			if(r==0)
				return "wrong";
			else
				r=0;
		}
		return "ok";
	}
	private String DeleteMenuComp(String compid)
	{
		DB db=new DB();
		int rs;
		String sql="delete from ices_menu_comp where compid='"+compid+"'";
		rs=db.delete(sql);
		db.close();
		if(rs!=0)
		{
			return "ok";
		}
		else
			return "wrong";
			
		
	}
	public String AddWSComp(String cname,String name,String url,String info,String maker,String userName,String sort,String theme) throws NumberFormatException, SQLException
	{
		String sql="select * from ices_user where user_name='"+userName+"'";
		DB db=new DB();
		ResultSet rs=db.query(sql);
		String userid="";
		try {
			while(rs!=null && rs.next())
			{
				userid=rs.getString("user_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(userid!="")
		{
			String temp_str="";//获取当前日期   
		    Date dt = new Date();   
		    //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
		    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");   
		    temp_str=sdf.format(dt); 
		    
		    CreateKey ck=new CreateKey("ices_ws_comp","comp_id","WSC");
		    String sql2="INSERT INTO ices_ws_comp( " +
		    " comp_id,comp_cname,comp_name, comp_maker, comp_url, comp_info, makerid, makedate, comp_sort, comp_theme_id)"+
		    " VALUES ('"+ck.getKey()+"', '"+cname+"', '"+name+"', '"+maker+"', '"+url+"',  '"+info+"',  '"+userid+"', '"+temp_str+"', '"+sort+"', '"+theme+"')";
		    
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
		else
		{
			db.close();
			return "wrong";
		}
	}
	public List queryWSComp(String cname,String info,String dev,String sd,String ed,String sort)
	{
		System.out.println("查询构件");
		String sql1="";
		String sql2="";
		String sql3="";
		String sql4="";
		String sql5="";
		String sql6="";
		if(cname!="")
		{
			sql1=" and comp_cname like '%"+cname+"%' ";
		}
		if(info!="")
		{
			sql2=" and comp_info like '%"+info+"%' ";
		}
		if(dev!="")
		{
			sql3=" and comp_maker like '%"+dev+"%' ";
		}
		if(sd!="" && ed!="")
		{
			sql4="and  makedate > '"+sd+"' and makedate <'"+ed+"' ";
		}
		if(sort!="")
		{
			if(!sort.equals("0"))
			{
				if(sort.equals("1"))
				{
					sql6=" and  comp_sort='内部Web service' ";
				}
				if(sort.equals("2"))
				{
					sql6=" and comp_sort='外部Web service' ";
				}
				
					
			}
		}
		String sql7=" 1=1 ";
		String sql8="select * from ices_ws_comp where "+sql7+sql1+sql2+sql3+sql4+sql6;
		DB db=new DB();
		ResultSet rs=db.query(sql8);
		List l=new ArrayList();
		int i=1;
		try {
			while(rs!=null && rs.next())
			{
				component cp=new component();
				cp.setWSComId(rs.getString("comp_id"));
				cp.setWSComName(rs.getString("comp_cname"));
				cp.setWSComURL(rs.getString("comp_url"));
				cp.setWSComDev(rs.getString("comp_maker"));
				cp.setWSCominfo(rs.getString("comp_info"));
			//	cp.setComOrg(rs.getString("comp_org"));
				cp.setWSComMaker(rs.getString("makerid"));
				
				cp.setWSComDate(rs.getString("makedate"));
				cp.setWSComSort(rs.getString("comp_sort"));
				cp.setWSComField(rs.getString("comp_theme_id"));
				
				
				//String rsort=(rs.getString("comp_sort").equals("内部Web service")?"家庭用户,":"")+(rs.getString("provider").equals("1")?"供应商用户,":"")+(rs.getString("business").equals("1")?"业务中心,":"")+(rs.getString("center").equals("1")?"服务中心":"");
				//System.out.println(rsort);
				//cp.setComOrg(rsort);
				cp.setWSNum(""+i);
				i++;
				l.add(cp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
		
	}

}

