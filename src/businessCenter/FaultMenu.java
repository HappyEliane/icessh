package businessCenter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import bean.business;
import db.DB;
import tool.CreateKey;

public class FaultMenu {
	/*
	 * 业务注册
	 * 1.将业务基本信息加入到ices_business表中
	 * 2.分析业务中的四个菜单，抽取出其中的构件，保存到ices_men_comp表中，
	 * 3.
	 */
	public String addMenu(String login_user,String login_role,String name,String des,String maker,String smenu,String bmenu,String hmenu,String pmenu,String admin) throws NumberFormatException, SQLException
	{

		String sql="select * from ices_user_role where user_name='"+login_user+"' and role_name='"+login_role+"'";
		DB db=new DB();
		boolean result=true;
		String make="";
		ResultSet rs=db.query(sql);
		try {
			while(rs.next())
			{
			
				make=rs.getString("user_role_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result=false;
		}
		if(make=="" )
			return "wrong";
		
		String temp_str="";//获取当前日期   
	    Date dt = new Date();   
	    //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");   
	    temp_str=sdf.format(dt); 
	    List scomp=null;
	    System.out.println("服务中心菜单："+smenu);
	    System.out.println("业务中心菜单："+bmenu);
	    System.out.println("家庭用户菜单："+hmenu);
	    System.out.println("供应商菜单："+pmenu);
	    scomp = getComp(smenu);
	    List bcomp=null;
	    bcomp = getComp(bmenu);
	    List hcomp=null;
	    hcomp = getComp(hmenu);
	    List pcomp=null;
	    pcomp = getComp(pmenu);
	    CreateKey ck=new CreateKey("ices_menu","menu_id","MENU");
	    CreateKey ck1=new CreateKey("ices_business","id","BUS");
	    String buskey=ck1.getKey();
	    String smenukey="";
	    String bmenukey="";
	    String hmenukey="";
	    String pmenukey="";
	    
	   
	    
	    
	    if(scomp!=null && scomp.size()!=0)
	    {
	    	smenukey=ck.getKey();
	    	insertMenu(smenu,make,name+"-服务中心",smenukey);//加入ices_menu表中
	    	insertBusComp(scomp,smenukey,"MENU0001");//加入到ices_men_comp表中
			
	    }
	    if(bcomp!=null && bcomp.size()!=0)
	    {
	    	bmenukey=ck.getKey();
	    	insertMenu(bmenu,make,name+"-业务中心",bmenukey);
	    	insertComp(bcomp,bmenukey);
			
	    }
	    if(hcomp!=null && hcomp.size()!=0)
	    {
	    	hmenukey=ck.getKey();
	    	insertMenu(hmenu,make,name+"-家庭用户",hmenukey);
			insertComp(hcomp,hmenukey);
	    }
	    if(pcomp!=null && pcomp.size()!=0)
	    {
	    	pmenukey=ck.getKey();
	    	insertMenu(pmenu,make,name+"-供应商",pmenukey);
			insertComp(pcomp,pmenukey);
	    }
	    
	    String sql2="INSERT INTO ices_business( " +
	    " id, bus_name, author, bus_des, smenu, bmenu, hmenu, pmenu, makedate, make,admin )"+
	    " VALUES ('"+buskey+"','"+name+"', '"+maker+"', '"+des+"', '"+smenukey+"', '"+bmenukey+"', '"+hmenukey+"', '"+pmenukey+"', '"+temp_str+"',  '"+make+"', '"+admin+"')";
	    int re=db.insert(sql2);
	    db.close();
	    if(re==0)
	    {
	    	result=false;
	    }
	    if(scomp!=null && scomp.size()!=0)
	    {
	    	insertOrderBusiness(buskey,"URI0001",smenukey,name);
	    }
	    if(bcomp!=null && bcomp.size()!=0)
	    {
	    	insertOrderBusiness(buskey,admin,bmenukey,name);
	    }
		
	    
		if(result)
			return "ok";
		return "wrong";
	    
	   
	}
	void insertOrderBusiness(String busid,String orderid,String menuid,String busname) throws NumberFormatException, SQLException
	{
		String temp_str="";//获取当前日期   
	    Date dt = new Date();   
	    //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");   
	    temp_str=sdf.format(dt); 
	    System.out.println(busname);
		 CreateKey ck=new CreateKey("ices_orderBusiness","id","ORBUS");
		 String sql2="INSERT INTO ices_orderBusiness( " +
		    " id, busid, busname, orderid, menuid, makedate, cloud )"+
		    " VALUES ('"+ck.getKey()+"','"+busid+"', '"+busname+"', '"+orderid+"', '"+menuid+"', '"+temp_str+"', '"+""+"')";
		DB db=new DB();
		int a=db.insert(sql2);
		db.close();
	}
	String insertMenu(String menu,String make,String menuname,String key)
	{

		DB db=new DB();
		String temp_str="";//获取当前日期   
	    Date dt = new Date();   
	    //最后的aa表示“上午”或“下午”    HH表示24小时制    如果换成hh表示12小时制   
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");   
	    temp_str=sdf.format(dt); 
	    
	   
	    String sql2="INSERT INTO ices_menu( " +
	    " menu_id, menu_name, menu, makedate, make )"+
	    " VALUES ('"+key+"','"+menuname+"', '"+menu+"', '"+temp_str+"',  '"+make+"')";
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
	
	
	//通过此函数，能够得到menu中所有的构件id
	public List getComp(String menu) throws NumberFormatException, SQLException
	{
		String sql="select * from ";
		String q="";
			q="ices_menu_comp where menuid='MENU0001'";
		sql+=q;
		DB db=new DB();
		ResultSet rs=db.query(sql);
		List a1=new ArrayList();
		String t="";
		try {
			while(rs.next())
			{
				t=rs.getString("compid");
				a1.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
				
				if(!b.contains(re))
				{
					System.out.println(re);
					b.add(re);
				//	insertComp(q,re);
				}
			}
		 }
		 db.close();
		return b;
		 
	}
	public void insertComp(List add,String menuid) throws NumberFormatException, SQLException
	{
		DB db=new DB();
		CreateKey ck=new CreateKey("ices_menu_comp","id","MC");
		for(int i=0;i<add.size();i++)
		{
			String temp=(String) add.get(i);
			String key=ck.getKey();
		 String sql2="INSERT INTO ices_menu_comp ( " +
		    " id, menuid, compid, fid)"+
		    " VALUES ('"+key+"', '"+menuid+"', '"+temp+"', '"+key+"')";
		    
		    int re=db.insert(sql2);
		    
		}
		    db.close();
		  
	}
	public void insertBusComp(List add,String menuid,String menuid1) throws NumberFormatException, SQLException
	{
		DB db=new DB();
		CreateKey ck=new CreateKey("ices_menu_comp","id","MC");
		for(int i=0;i<add.size();i++)
		{
			String temp=(String) add.get(i);
			String key=ck.getKey();
		 String sql2="INSERT INTO ices_menu_comp ( " +
		    " id, menuid, compid, fid)"+
		    " VALUES ('"+key+"', '"+menuid+"', '"+temp+"', '"+key+"')";
		    
		    int re=db.insert(sql2);
		    
		    String key1=ck.getKey();
		    String sql3="INSERT INTO ices_menu_comp ( " +
		    " id, menuid, compid, fid)"+
		    " VALUES ('"+key1+"', '"+menuid1+"', '"+temp+"', '"+key+"')";
		    
		    int re1=db.insert(sql3);
		    
		}
		    db.close();
		  
	}
	public List getBusiness(String name,String sd,String ed,String author,String des)
	{
		String sql1="";
		if(!name.equals(""))
		{
			sql1=" and a.bus_name like '%"+name+"%'";
		}
		String sql2="";
		if(!sd.equals("") && !ed.equals(""))
		{
			sql2=" and a.makedate >'"+sd+"' and a.makedate <'"+ed+"'";
		}
		String sql3="";
		if(!author.equals(""))
		{
			sql3=" and a.author like '%"+author+"%'";
		}
		String sql4="";
		if(!des.equals(""))
		{
			sql4=" and a.bus_des like '%"+des+"%'";
		}
		String sql="select * from ices_business as a, ices_user_role as b where a.admin=b.user_role_id "+sql1+sql2+sql3+sql4;
		DB db=new DB();
		ResultSet rs=db.query(sql);
		List l=new ArrayList();
		List l1=new ArrayList();
		int num=1;
		try {
			while(rs.next())
			{
				business b=new business();
				b.setNum(""+num);
				b.setId(rs.getString("a.id"));
				b.setName(rs.getString("a.bus_name"));
				b.setAuthor(rs.getString("a.author"));
				b.setDes(rs.getString("a.bus_des"));
				b.setMakedate(rs.getString("a.makedate"));
				b.setMake(rs.getString("a.make"));
				b.setSmenu(rs.getString("a.smenu"));
				b.setBmenu(rs.getString("a.bmenu"));
				b.setHmenu(rs.getString("a.hmenu"));
				b.setPmenu(rs.getString("a.pmenu"));
				b.setAdminuser(rs.getString("b.user_name"));
				b.setAdminrole(rs.getString("b.role_name"));
				num++;
				l1.add(b);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		l.add(l1);
		return l;
	}
	public String getBusMenu(String sm,String bm,String hm,String pm)
	{
		DB db=new DB();
		String result="";
		
		String sql="select * from ices_menu where menu_id='"+sm+"'";
		ResultSet rs=db.query(sql);
		try {
			while(rs.next())
			{
				result+=rs.getString("menu");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result+=",";
		
			sql="select * from ices_menu where menu_id='"+bm+"'";
		 rs=db.query(sql);
		try {
			while(rs.next())
			{
				result+=rs.getString("menu");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result+=",";
		
		sql="select * from ices_menu where menu_id='"+hm+"'";
		 rs=db.query(sql);
		try {
			while(rs.next())
			{
				result+=rs.getString("menu");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		result+=",";
		
		sql="select * from ices_menu where menu_id='"+pm+"'";
		 rs=db.query(sql);
		try {
			while(rs.next())
			{
				result+=rs.getString("menu");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
		
	}
	public String changeBus(String id,String name,String des, String make,String smenuid,String smenu,String bmenuid,String bmenu,String hmenuid,String hmenu,String pmenuid,String pmenu) throws NumberFormatException, SQLException
	{
		System.out.println(smenuid+smenu);
		String sql="UPDATE ices_business SET bus_name = '"+name+"', bus_des = '"+des+"' WHERE id = '"+id+"'";
		DB db=new DB();
		 int r=db.update(sql);
		 String result="wrong";
		 if(r!=0)
		 {
			 result="ok";
		 }
		 else
			 result="wrong";
		 //判断更新后的服务中心菜单是否有变化
		 if(!smenuid.equals(""))//原来该业务中的服务中心菜单存在
		 {
			 List b=null;
			 try {
				 b=getComp(smenu);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result="wrong";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result="wrong";
			}
			List a1=null;
			a1=comp(smenuid);
			 List del=new ArrayList();
			 for(int j=0;j<a1.size();j++)
			 {
				 String temp=(String) a1.get(j);
				 if(!b.contains(temp))
					 del.add(temp);
			 }
			 List add=new ArrayList();
			 for(int k=0;k<b.size();k++)
			 {
				 String temp=(String) b.get(k);
				 if(!a1.contains(temp))
					 add.add(temp);
			 }
			 if(del.size()!=0)//需要删除原服务中心菜单的构件
			 {
				 delData(del,smenuid);
				 List temp=comp(smenuid);
				 if(temp.size()==0)//如果该菜单已经没有构件，则需要删除该菜单相关信息
				 {
					 sql="delete from ices_menu where menu_id='"+smenuid+"'";
					 db.delete(sql);//删除该菜单
					 sql="UPDATE ices_business SET smenu = '"+""+"'  WHERE id = '"+id+"'";	
					 db.update(sql);//删除该业务中对应的该菜单
				 }
			 }
			 if(add.size()!=0)//需要添加原服务中心菜单的构件
			 {
				try {
					insertComp(add,smenuid);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result="wrong";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result="wrong";
				}
			 }
			
		 }
		 else//原服务中心菜单不存在
		 {
			 List a=null;
			try {
				a = getComp(smenu);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 if(a.size()>0)//新菜单有构件
			 {
				 CreateKey ck=new CreateKey("ices_menu","menu_id","MENU");
				    String smenukey="";
				    smenukey=ck.getKey();
			    	insertMenu(smenu,make,name+"-服务中心",smenukey);
					insertComp(a,smenukey);
					 sql="UPDATE ices_business SET smenu = '"+smenukey+"'  WHERE id = '"+id+"'";
					
					r=db.update(sql);
					 
					 if(r!=0)
					 {
						 result="ok";
					 }
					 else
						 result="wrong";
			 }
		 }
		 
		 //判断更新后的业务中心菜单是否有变化
		 if(!bmenuid.equals(""))//原来该业务中的服务中心菜单存在
		 {
			 List b=null;
			 try {
				 b=getComp(bmenu);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result="wrong";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result="wrong";
			}
			List a1=null;
			a1=comp(bmenuid);
			 List del=new ArrayList();
			 for(int j=0;j<a1.size();j++)
			 {
				 String temp=(String) a1.get(j);
				 if(!b.contains(temp))
					 del.add(temp);
			 }
			 List add=new ArrayList();
			 for(int k=0;k<b.size();k++)
			 {
				 String temp=(String) b.get(k);
				 if(!a1.contains(temp))
					 add.add(temp);
			 }
			 if(del.size()!=0)//需要删除原业务中心菜单的构件
			 {
				 delData(del,bmenuid);
				 List temp=comp(bmenuid);
				 if(temp.size()==0)//如果该菜单已经没有构件，则需要删除该菜单相关信息
				 {
					 sql="delete from ices_menu where menu_id='"+bmenuid+"'";
					 db.delete(sql);//删除该菜单
					 sql="UPDATE ices_business SET bmenu = '"+""+"'  WHERE id = '"+id+"'";	
					 db.update(sql);//删除该业务中对应的该菜单
				 }
			 }
			 if(add.size()!=0)//需要添加原业务中心菜单的构件
			 {
				try {
					insertComp(add,bmenuid);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result="wrong";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result="wrong";
				}
			 }
			
		 }
		 else//原业务中心菜单不存在
		 {
			 List a=null;
			try {
				a = getComp(bmenu);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 if(a.size()>0)//新菜单有构件
			 {
				 CreateKey ck=new CreateKey("ices_menu","menu_id","MENU");
				    String bmenukey="";
				    bmenukey=ck.getKey();
			    	insertMenu(bmenu,make,name+"-业务中心",bmenukey);
					insertComp(a,bmenukey);
					 sql="UPDATE ices_business SET bmenu = '"+bmenukey+"'  WHERE id = '"+id+"'";
					
					r=db.update(sql);
					 
					 if(r!=0)
					 {
						 result="ok";
					 }
					 else
						 result="wrong";
			 }
		 }
		 
		 //判断更新后的家庭用户菜单是否有变化
		 if(!hmenuid.equals(""))//原来该业务中的家庭用户菜单存在
		 {
			 List b=null;
			 try {
				 b=getComp(hmenu);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result="wrong";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result="wrong";
			}
			List a1=null;
			a1=comp(hmenuid);
			 List del=new ArrayList();
			 for(int j=0;j<a1.size();j++)
			 {
				 String temp=(String) a1.get(j);
				 if(!b.contains(temp))
					 del.add(temp);
			 }
			 List add=new ArrayList();
			 for(int k=0;k<b.size();k++)
			 {
				 String temp=(String) b.get(k);
				 if(!a1.contains(temp))
					 add.add(temp);
			 }
			 if(del.size()!=0)//需要删除原家庭用户菜单的构件
			 {
				 delData(del,hmenuid);//删除菜单-构件表
				 List temp=comp(hmenuid);
				 if(temp.size()==0)//如果该菜单已经没有构件，则需要删除该菜单相关信息
				 {
					 sql="delete from ices_menu where menu_id='"+hmenuid+"'";
					 db.delete(sql);//删除该菜单
					 sql="UPDATE ices_business SET hmenu = '"+""+"'  WHERE id = '"+id+"'";	
					 db.update(sql);//删除该业务中对应的该菜单
				 }
			 }
			 if(add.size()!=0)//需要添加原家庭用户菜单的构件
			 {
				try {
					insertComp(add,hmenuid);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result="wrong";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result="wrong";
				}
			 }
			
		 }
		 else//原家庭用户菜单不存在
		 {
			 List a=null;
			try {
				a = getComp(hmenu);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 if(a.size()>0)//新菜单有构件
			 {
				 CreateKey ck=new CreateKey("ices_menu","menu_id","MENU");
				    String hmenukey="";
				    hmenukey=ck.getKey();
			    	insertMenu(hmenu,make,name+"-家庭用户",hmenukey);
					insertComp(a,hmenukey);
					 sql="UPDATE ices_business SET hmenu = '"+hmenukey+"'  WHERE id = '"+id+"'";
					
					r=db.update(sql);
					 
					 if(r!=0)
					 {
						 result="ok";
					 }
					 else
						 result="wrong";
			 }
		 }
		 
		 //判断更新后的供应商菜单是否有变化
		 if(!pmenuid.equals(""))//原来该业务中的供应商菜单存在
		 {
			 List b=null;
			 try {
				 b=getComp(pmenu);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result="wrong";
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				result="wrong";
			}
			List a1=null;
			a1=comp(pmenuid);
			 List del=new ArrayList();
			 for(int j=0;j<a1.size();j++)
			 {
				 String temp=(String) a1.get(j);
				 if(!b.contains(temp))
					 del.add(temp);
			 }
			 List add=new ArrayList();
			 for(int k=0;k<b.size();k++)
			 {
				 String temp=(String) b.get(k);
				 if(!a1.contains(temp))
					 add.add(temp);
			 }
			 if(del.size()!=0)//需要删除原服务中心菜单的构件
			 {
				 delData(del,pmenuid);
				 List temp=comp(pmenuid);
				 if(temp.size()==0)//如果该菜单已经没有构件，则需要删除该菜单相关信息
				 {
					 sql="delete from ices_menu where menu_id='"+pmenuid+"'";
					 db.delete(sql);//删除该菜单
					 sql="UPDATE ices_business SET pmenu = '"+""+"'  WHERE id = '"+id+"'";	
					 db.update(sql);//删除该业务中对应的该菜单
				 }
			 }
			 if(add.size()!=0)//需要添加原服务中心菜单的构件
			 {
				try {
					insertComp(add,pmenuid);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result="wrong";
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
					result="wrong";
				}
			 }
			
		 }
		 else//原家庭用户菜单不存在
		 {
			 List a=null;
			try {
				a = getComp(pmenu);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			 if(a.size()>0)//新菜单有构件
			 {
				 CreateKey ck=new CreateKey("ices_menu","menu_id","MENU");
				    String pmenukey="";
				    pmenukey=ck.getKey();
			    	insertMenu(pmenu,make,name+"-供应商",pmenukey);
					insertComp(a,pmenukey);
					 sql="UPDATE ices_business SET pmenu = '"+pmenukey+"'  WHERE id = '"+id+"'";
					
					r=db.update(sql);
					 
					 if(r!=0)
					 {
						 result="ok";
					 }
					 else
						 result="wrong";
			 }
		 }
		 db.close();
		 return result;
	}
	List comp(String menuid)
	{
		String sql="select * from ";
		String q="";
			q="ices_menu_comp where menuid='"+menuid+"'";
		sql+=q;
		DB db=new DB();
		ResultSet rs=db.query(sql);
		List a1=new ArrayList();
		String t="";
		try {
			while(rs.next())
			{
				t=rs.getString("compid");
				a1.add(t);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.close();
		return a1;
	}
	public void delData(List del,String menuid)
	{
		String sql="delete from ices_menu_comp where menuid='"+menuid+"' and (";
		for(int i=0;i<del.size();i++)
		{
			String temp=(String) del.get(i);
			sql+=" compid='"+temp+"' ";
			if(i!=del.size()-1)
				sql+=" or ";
			else
				sql+=")";
		}
		DB db=new DB();
		db.delete(sql);
		db.close();
	}
	public String delBus(String id)
	{
		String smenu="";
		String bmenu="";
		String hmenu="";
		String pmenu="";
		String sql="select * from ices_business where id='"+id+"'";
		DB db=new DB();
		ResultSet rs=db.query(sql);
		String result="ok";
		try {
			while(rs.next())
			{
				smenu=rs.getString("smenu");
				bmenu=rs.getString("bmenu");
				hmenu=rs.getString("hmenu");
				pmenu=rs.getString("pmenu");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result="wrong";
		}
		if(!smenu.equals(""))
		{
			sql="delete from ices_menu where menu_id='"+smenu+"'";
			db.delete(sql);
		}
		if(!bmenu.equals(""))
		{
			sql="delete from ices_menu where menu_id='"+bmenu+"'";
			db.delete(sql);
		}
		if(!hmenu.equals(""))
		{
			sql="delete from ices_menu where menu_id='"+hmenu+"'";
			db.delete(sql);
		}
		if(!pmenu.equals(""))
		{
			sql="delete from ices_menu where menu_id='"+pmenu+"'";
			db.delete(sql);
		}
		sql="delete from ices_business where id='"+id+"'";
		db.delete(sql);
		db.close();
		return result;
	}
	public String getMenu(String menuid)
	{
		String sql="select * from ices_menu where menu_id='"+menuid+"'";
		DB db=new DB();
		ResultSet rs=db.query(sql);
		String menu="";
		try {
			while(rs.next())
			{
				menu=rs.getString("menu");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return menu;
	}
	/*
	 * 业务定制
	 */
	public String orderBus(String loginuser,String loginrole,String busname,String busid,String menuid) throws NumberFormatException, SQLException
	{
		String sql="select * from ices_user_role where user_name='"+loginuser+"' and role_name='"+loginrole+"'";
		DB db=new DB();
		ResultSet rs=db.query(sql);
		String make="";
		String cloud="";
		try {
			while(rs.next())
			{
				make=rs.getString("user_role_id");
				cloud=rs.getString("make");
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
	    String temp="";
	    sql="select * from ices_user where user_name='"+loginuser+"'";
	    rs=db.query(sql);
	    while(rs.next())
	    {
	    	temp=rs.getString("cloud");//当前用户的所属组织，HRI为家庭用户，PRI为供应商用户
	    }
	    
		CreateKey ck=new CreateKey("ices_orderbusiness","id","ORBUS");
		String sql2="INSERT INTO ices_orderbusiness ( " +
	    " id, busid,busname,orderid, menuid, makedate, cloud)"+
	    " VALUES ('"+ck.getKey()+"', '"+busid+"', '"+busname+"', '"+make+"','"+menuid+"', '"+temp_str+"', '"+temp+"')";
		int re=db.insert(sql2);
		if(re!=0)
		{
			return "ok";
		}
		return "wrong";
		
	}
}
