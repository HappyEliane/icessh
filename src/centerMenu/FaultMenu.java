package centerMenu;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import db.DB;
import tool.CreateKey;

public class FaultMenu {
	public String getMenu(String user)
	{
		if(user.equals("家庭用户"))
			user="CRI0001";
		else if(user.equals("供应商用户"))
			user="CRI0002";
		else if(user.equals("业务中心"))
			user="CRI0003";
		else if(user.equals("服务中心"))
			user="CRI0004";
		String sql="select * from ices_role_menu where roleid='"+user+"'";
		DB db=new DB();
		ResultSet rs=db.query(sql);
		String menuid="";
		try {
			while(rs.next() && rs!=null)
			{
				menuid=rs.getString("menuid");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		if(!menuid.equals(""))
		{
			sql="select * from ices_menu where menu_id='"+menuid+"'";
			ResultSet rs1=db.query(sql);
			String menu="";
			try {
				while(rs1!=null && rs1.next()  )
				{
					menu=rs1.getString("menu");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			db.close();
			if(menu!="")
				return menu;
			else 
				return "wrong";
		}
		return "wrong";
	}
	public String ChangeMenu(String menu) throws NumberFormatException, SQLException
	{
		
		getComp(menu);
		String sql="UPDATE ices_menu SET menu = '"+menu+"' "+" WHERE menu_id = 'MENU0001'";
		DB db=new DB();
		int result=db.update(sql);
		 System.out.println("更新完成");
		 if(result!=0)
		 {
			 return "ok";
		 }
		 else
			  return "wrong";
		
		 
	}
	//通过此函数，能够得到menu中所有的构件id
	public void getComp(String menu) throws NumberFormatException, SQLException
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
			while(rs!=null && rs.next()  )
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
		 if(del.size()!=0)
			 delData(del);
		 if(add.size()!=0)
			 insertComp(add);
		 
	}
	public void insertComp(List add) throws NumberFormatException, SQLException
	{
		DB db=new DB();
		CreateKey ck=new CreateKey("ices_menu_comp","id","MC");
		for(int i=0;i<add.size();i++)
		{
			String temp=(String) add.get(i);
			String key=ck.getKey();
		 String sql2="INSERT INTO ices_menu_comp ( " +
		    " id, menuid, compid, fid)"+
		    " VALUES ('"+key+"', '"+"MENU0001"+"', '"+temp+"', '"+key+"')";
		    
		    int re=db.insert(sql2);
		    
		}
		    db.close();
		  
	}
	public void delData(List del)
	{
		String sql="delete from ices_menu_comp where menuid='MENU0001' and (";
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
	public String getServiceMenu()
	{
		
			DB db=new DB();
			String sql="select * from ices_menu where menu_id='MENU0001'";
			ResultSet rs1=db.query(sql);
			String menu="";
			try {
				while(rs1!=null && rs1.next()  )
				{
					menu=rs1.getString("menu");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			db.close();
			if(menu!="")
				return menu;
			else 
				return "wrong";
	
	
	} 
	
	

}
