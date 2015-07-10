package businessCenter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.ordersp;

import db.DB;

import tool.CreateKey;

public class spattern {
	public String orderSP(String spid,String spname,String spdes,String busid,String home,String provider)
	{
		CreateKey ck=null;
		try {
			ck=new CreateKey("ordersp","id","ORDERSP");
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String sql="INSERT INTO ordersp( " +
	    " id, spid, spname, spdes, busid, home, provider)"+
	    " VALUES ('"+ck.getKey()+"','"+spid+"', '"+spname+"', '"+spdes+"', '"+busid+"', '"+home+"', '"+provider+"')";
		DB db=new DB();
		db.insert(sql);
		return "ok";
		
	}
	public List getOrderSP()
	{
		List result=new ArrayList();
		String sql="select * from ordersp";
		DB db=new DB();
		ResultSet rs=db.query(sql);
		try {
			while(rs.next())
			{
				ordersp sp=new ordersp();
				sp.setId(rs.getString("id"));
				sp.setSpid(rs.getString("spid"));
				sp.setSpname(rs.getString("spname"));
				sp.setSpdes(rs.getString("spdes"));
				sp.setBusid(rs.getString("busid"));
				sp.setHome(rs.getString("home"));
				sp.setProvider(rs.getString("provider"));
				
				
				result.add(sp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	public List getHomeOrderSP()
	{
		List result=new ArrayList();
		String sql="select * from ordersp where home='1'";
		DB db=new DB();
		ResultSet rs=db.query(sql);
		try {
			while(rs.next())
			{
				ordersp sp=new ordersp();
				sp.setId(rs.getString("id"));
				sp.setSpid(rs.getString("spid"));
				sp.setSpname(rs.getString("spname"));
				sp.setSpdes(rs.getString("spdes"));
				sp.setBusid(rs.getString("busid"));
				sp.setHome(rs.getString("home"));
				sp.setProvider(rs.getString("provider"));
				
				
				result.add(sp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}

}
