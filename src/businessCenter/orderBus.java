package businessCenter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import bean.business;
import db.DB;

public class orderBus {
	/*
	 * 查看当前登录用户定制的业务
	 */
	public List getBus(String loginuser,String loginrole,String name,String sd,String ed,String des)
	{
		List l=new ArrayList();
		String sql="select * from ices_user_role where user_name='"+loginuser+"' and role_name='"+loginrole+"'";
		String make="";
		String userid="";
		DB db=new DB();
		ResultSet rs=db.query(sql);
		try {
			while(rs.next())
			{
				make=rs.getString("user_role_id");
				userid=rs.getString("user_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql="select * from ices_user where user_id='"+userid+"'";
		String cloud="";
		rs=db.query(sql);
		try {
			while(rs.next())
			{
				cloud=rs.getString("cloud");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!cloud.equals(""))
		{
			String sql1="";
			if(!name.equals(""))
			{
				sql1=" and bus_name like'%"+name+"%' ";
			}
			String sql2="";
			if(!sd.equals("") && !ed.equals(""))
			{
				sql2=" and makedate<'"+ed+"' and makedate>'"+sd+"' ";
			}
			String sql3="";
			if(!des.equals(""))
			{
				sql3=" and bus_des like '%"+des+"%' ";
			}
			char c=cloud.charAt(0);
			if(c=='H')//当前用户是家庭用户
			{
				/*
				 * 1.业务菜单中应该含有家庭用户或供应商用户的菜单
				 * 2.没有被当前用户定制过的业务
				 */
				sql="select * from ices_business where hmenu is not null and id not in ( select busid from ices_orderbusiness where orderid='"+make+"')"+sql1+sql2+sql3;
				
			
				 rs=db.query(sql);
				
				List l1=new ArrayList();
				int num=1;
				try {
					while(rs.next())
					{
						business b=new business();
						b.setNum(""+num);
						b.setId(rs.getString("id"));
						b.setName(rs.getString("bus_name"));
						b.setAuthor(rs.getString("author"));
						b.setDes(rs.getString("bus_des"));
						b.setMakedate(rs.getString("makedate"));
						b.setMake(rs.getString("make"));

						b.setHmenu(rs.getString("hmenu"));

						
						num++;
						l1.add(b);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				l.add(l1);
			}
			if(c=='P')//当前用户是供应商用户
			{
				sql="select * from ices_business where pmenu is not null and id not in ( select busid from ices_orderbusiness where orderid='"+make+"')"+sql1+sql2+sql3;
				
				
				 rs=db.query(sql);
				
				List l1=new ArrayList();
				int num=1;
				try {
					while(rs.next())
					{
						business b=new business();
						b.setNum(""+num);
						b.setId(rs.getString("id"));
						b.setName(rs.getString("bus_name"));
						b.setAuthor(rs.getString("author"));
						b.setDes(rs.getString("bus_des"));
						b.setMakedate(rs.getString("makedate"));
						b.setMake(rs.getString("make"));

						b.setHmenu(rs.getString("pmenu"));

						
						num++;
						l1.add(b);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				l.add(l1);
			}
			
		}
		
		return l;
	}
	/*
	 * 查看当前用户尚未定制的业务
	 */
	public List getHaveBus(String user,String role)
	{
		List l=new ArrayList();
		String sql="select * from ices_user_role where user_name='"+user+"' and role_name='"+role+"'";
		String make="";
		String userid="";
		DB db=new DB();
		ResultSet rs=db.query(sql);
		try {
			while(rs.next())
			{
				make=rs.getString("user_role_id");
				userid=rs.getString("user_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql="select * from ices_user where user_id='"+userid+"'";
		String cloud="";
		rs=db.query(sql);
		try {
			while(rs.next())
			{
				cloud=rs.getString("cloud");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!cloud.equals(""))
		{
			
			char c=cloud.charAt(0);
			if(c=='H')//当前用户是家庭用户
			{
				/*
				 * 1.业务菜单中应该含有家庭用户或供应商用户的菜单
				 * 2.没有被当前用户定制过的业务
				 */
				sql="select * from ices_business as a, ices_orderbusiness as b where a.id=b.busid and b.orderid='"+make+"'";
				
			
				 rs=db.query(sql);
				
				List l1=new ArrayList();
				int num=1;
				try {
					while(rs.next())
					{
						business b=new business();
						b.setNum(""+num);
						b.setId(rs.getString("b.id"));
						b.setName(rs.getString("b.busname"));
						b.setAuthor(rs.getString("a.author"));
						b.setDes(rs.getString("a.bus_des"));
						b.setMakedate(rs.getString("a.makedate"));
						b.setMake(rs.getString("a.make"));
						b.setOrderdate(rs.getString("b.makedate"));
						b.setHmenu(rs.getString("a.hmenu"));
						num++;
						l1.add(b);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				l.add(l1);
			}
			if(c=='P')//当前用户是供应商用户
			{
				sql="select * from ices_business as a, ices_orderbusiness as b where a.id=b.busid and b.orderid='"+make+"'";
				
				
				 rs=db.query(sql);
				
				List l1=new ArrayList();
				int num=1;
				try {
					while(rs.next())
					{
						business b=new business();
						b.setNum(""+num);
						b.setId(rs.getString("b.id"));
						b.setName(rs.getString("b.bus_name"));
						b.setAuthor(rs.getString("a.author"));
						b.setDes(rs.getString("a.bus_des"));
						b.setMakedate(rs.getString("a.makedate"));
						b.setMake(rs.getString("a.make"));
						b.setOrderdate(rs.getString("b.makedate"));
						b.setHmenu(rs.getString("a.pmenu"));
						num++;
						l1.add(b);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				l.add(l1);
			}
			
		}
		
		return l;
	}
	public List getAllBus()
	{
	
		
		String sql="select * from ices_business ";
				
		DB db=new DB();
		ResultSet rs=db.query(sql);
				
				List l1=new ArrayList();
				int num=1;
				try {
					while(rs.next())
					{
						business b=new business();
						b.setName(rs.getString("bus_name"));
						b.setId(rs.getString("id"));

						l1.add(b);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
	
		
		return l1;
	}
	/*
	 * 退订业务，
	 * 1.删除ices_orderbusiness表中对应的数据
	 * 2.找到当前用户创建的ices_role_menu中的菜单
	 * 3.修改找到的菜单，
	 * 4.在ices_menu_comp中删除菜单中对应修改的构件
	 */
	public String quitBus(String orderbusid,String user,String role)
	{
		String sql1="select * from ices_user_role where user_name='"+user+"' and role_name='"+role+"'";
		String sql2="select * from ices_orderbusiness as a, ices_menu_comp as b  where a.menuid=b.menuid and a.id='"+orderbusid+"'";
		String sql="delete from ices_orderbusiness where id='"+orderbusid+"'";
		
		DB db=new DB();
		List allComp=new ArrayList();
		ResultSet rs2=db.query(sql2);//获取当前所定制的业务菜单包含的所有构件
		try {
			while(rs2.next())
			{
				String temp=rs2.getString("b.compid");
				allComp.add(temp);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		int re=db.delete(sql);//退订业务
		String result="";
		if(re!=0)
		{
			result="ok";
		}
		else
			result="wrong";
	
		String make="";
		ResultSet rs=db.query(sql1);
		try {
			while(rs.next())
			{
				make=rs.getString("user_role_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Map<String, String > AllRole=new HashMap<String,String>();//当前用户制作的所有菜单
		sql="select * from ices_role_menu as a, ices_menu as b where a.menuid=b.menu_id and  a.make='"+make+"'";
		rs=db.query(sql);
		try {
			while(rs.next())
			{
				String menuid=rs.getString("a.menuid");
				String menu=rs.getString("b.menu");
				AllRole.put(menuid, menu);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		/*
		 * 1.在AllRole菜单中删除业务中含有的构件
		 * 2.修改AllRole中的菜单
		 */
		DeleteComp(allComp,AllRole);
		Map<String,String> men=new HashMap<String,String>();
		men=ModifyMenu(allComp,AllRole);
		ChangeMenu(men);
		return result;
		
	}
	private void ChangeMenu(Map menu)
	{
		Iterator it=menu.keySet().iterator();
		DB db=new DB();
		String sql="";
		int rs;
		while(it.hasNext())
		{
			String k=(String) it.next();
			String men=(String) menu.get(k);
			sql="UPDATE ices_menu set menu='"+men+"' where menu_id='"+k+"'";
			db.update(sql);
		}
	}
	private void DeleteComp(List comp,Map menu)
	{
		Iterator it=menu.keySet().iterator();
		DB db=new DB();
		int rs;
		while(it.hasNext())
		{
			String k=(String) it.next();
			
			for(int j=0;j<comp.size();j++)
			{
				String c=(String) comp.get(j);
				String sql="delete from ices_menu_comp where menuid='"+k+"' and compid='"+c+"'";
				rs=db.delete(sql);
			}
		}
		
	}
	private Map<String,String> ModifyMenu(List comp, Map menu)
	{
		Iterator it=menu.keySet().iterator();
		DB db=new DB();
		int rs;
		Map<String,String> result=new HashMap<String,String>();
		while(it.hasNext())
		{
			String k=(String) it.next();
			String m=(String) menu.get(k);
			System.out.println(m);
			String men=newMenu(m,comp);
			result.put(k, men);
			
		}
		return result;
	}
	private String newMenu(String menu,List comp)
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
				if(!comp.contains(id))
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
	public static void main(String args[])
	{
		List l=new ArrayList();
		l.add("COM0001");
		l.add("COM0002");
		String menu1="<order name=\"我的业务\"><order name=\"用户角色管理\" url=\"http://localhost:8080/userole/icesuser.html\" id=\"COM0001\"/></order>";
		String menu2="<order name=\"我的业务\"><order name=\"aaaaaaaa\" url=\"http://localhost:8080/userole/icesuser.html\" id=\"COM0004\"/></order>";
		Map<String,String> m=new HashMap();
		m.put("MENU0001", menu1);
		m.put("MENU0002", menu2);
		Map<String,String>m1=new HashMap<String,String>();
		orderBus ob=new orderBus();
		m1=ob.ModifyMenu(l, m);
		System.out.println(m1.toString());
	}
}
