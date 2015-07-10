package businessCenter;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.component;
import db.DB;
import tool.CreateKey;

public class ModifyComponent {
	
	public List queryComp(String name,String info,String dev,String sd,String ed,String org)
	{
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
			while(rs.next())
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
	public String delComp(String menuid)
	{
		String sql="delete from ices_menu_comp where menuid='"+menuid+"'";
		DB db=new DB();
		int result=db.delete(sql);
		if(result!=0)
			return "ok";
		return "wrong";
	}

}

