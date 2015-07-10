package icesmenu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.component;
import db.DB;

public class QueryComponent {
	public List queryComp(String name,String info,String dev,String sd,String ed,String org,String role,String user)
	{
		/*
		 * 查询当前用户的基础构件
		 */
		String sql="select * from ices_user_role where user_name='"+user+"' and role_name='"+role+"'";
		DB db=new DB();
		ResultSet r=db.query(sql);
		String make="";
		try {
			while(r!=null && r.next())
			{
				make=r.getString("user_role_id");
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		String menuid=getMenuid(role);
		String sql1="";
		String sql2="";
		String sql3="";
		String sql4="";
		String sql5="";
		String sql6="";
		if(name!="")
		{
			sql1=" and b.comp_name like '%"+name+"%' ";
		}
		if(info!="")
		{
			sql2=" and b.comp_info like '%"+info+"%' ";
		}
		if(dev!="")
		{
			sql3=" and b.comp_maker like '%"+dev+"%' ";
		}
		if(sd!="" && ed!="")
		{
			sql4="and  b.makedate > '"+sd+"' and b.makedate <'"+ed+"' ";
		}
		
		String sql8="";
			sql8="select * from " +
			"ices_menu_comp as a, ices_component as b " +
			"where a.compid=b.comp_id and a.menuid='"+menuid+"' "+sql1+sql2+sql3+sql4;
		
		ResultSet rs=db.query(sql8);
		List l=new ArrayList();
		int i=1;
		try {
			while(rs!=null && rs.next())
			{
				component cp=new component();
				cp.setComId(rs.getString("b.comp_id"));
				cp.setComName(rs.getString("b.comp_name"));
				cp.setComURL(rs.getString("b.comp_url"));
				cp.setComDev(rs.getString("b.comp_maker"));
				cp.setCominfo(rs.getString("b.comp_info"));
			//	cp.setComOrg(rs.getString("comp_org"));
				cp.setComMaker(rs.getString("b.makerid"));
				
				cp.setComDate(rs.getString("b.makedate"));
				
				cp.setHome(rs.getString("b.home"));
				cp.setProvider(rs.getString("b.provider"));
				cp.setBusiness(rs.getString("b.business"));
				cp.setCenter(rs.getString("b.center"));
				String rorg=(rs.getString("b.home").equals("1")?"家庭用户,":"")+(rs.getString("b.provider").equals("1")?"供应商用户,":"")+(rs.getString("b.business").equals("1")?"业务中心,":"")+(rs.getString("b.center").equals("1")?"服务中心":"");
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
		/*
		 * 查询当前用户定制的业务所含有的构件
		 */
		List l2=new ArrayList();
		 sql="select * from ices_orderbusiness where orderid='"+make+"'";
		 String temp="";
		 r=db.query(sql);
		 try {
			while(r!=null && r.next())
			 {
				 temp=r.getString("menuid");
				 l2.add(temp);
			 }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(l2.size()>0)
		{
			temp=" and a.menuid in ( ";
			for(int j=0;j<l2.size();j++)
			{
				temp+="'";
				temp+=(String) l2.get(j);
				temp+="'";
				if(j!=(l2.size()-1))
					temp+=", ";
			}
			temp+=")";
			sql8="select * from " +
			"ices_menu_comp as a, ices_component as b " +
			"where a.compid=b.comp_id   "+sql1+sql2+sql3+sql4+temp;
			rs=db.query(sql8);
			try {
				while(rs!=null && rs.next())
				{
					component cp=new component();
					cp.setComId(rs.getString("b.comp_id"));
					cp.setComName(rs.getString("b.comp_name"));
					cp.setComURL(rs.getString("b.comp_url"));
					cp.setComDev(rs.getString("b.comp_maker"));
					cp.setCominfo(rs.getString("b.comp_info"));
				//	cp.setComOrg(rs.getString("comp_org"));
					cp.setComMaker(rs.getString("b.makerid"));
					
					cp.setComDate(rs.getString("b.makedate"));
					
					cp.setHome(rs.getString("b.home"));
					cp.setProvider(rs.getString("b.provider"));
					cp.setBusiness(rs.getString("b.business"));
					cp.setCenter(rs.getString("b.center"));
					String rorg=(rs.getString("b.home").equals("1")?"家庭用户,":"")+(rs.getString("b.provider").equals("1")?"供应商用户,":"")+(rs.getString("b.business").equals("1")?"业务中心,":"")+(rs.getString("b.center").equals("1")?"服务中心":"");
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
		}
		return l;
		
	}
	public String getMenuid(String role)
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
		String menuid="";
		ResultSet rs1=db.query(sql);
		try {
			while(rs1!=null && rs1.next())
			{
				menuid=rs1.getString("menuid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return menuid;
	}
	public List getComp(String name,String info,String dev,String sd,String ed,String org,String role,String menu,String user)
	{
		List c=queryComp( name, info, dev, sd, ed, org, role,user);
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
				
			//	if(!b.contains(re))
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

}
