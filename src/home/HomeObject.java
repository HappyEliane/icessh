package home;

import bean.HomeInfo;
import bean.HomeUserDetail;
import db.DB;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tool.CreateKey;


public class HomeObject {

	private DB db;

	public HomeObject() {
		db = new DB();
	}
	
	public String registerHome(String homeName,String homeSort, String homeAddress,String mainPersonID, String mainPerson,
			String phoneNumber, String email, String zipCode,String magicboxAddress)throws NumberFormatException, SQLException {
			CreateKey ck=new CreateKey("homeinfo","homeid","HOME");
			String sql = "insert into homeinfo (homeid,homename,homesort,homeaddress,mainpersonid,mainperson,phonenumber,email,zipcode,magicboxaddress) values";
			sql+="('"+ck.getKey()+"','"+homeName+"','"+homeSort+"','"+homeAddress+"','"+mainPersonID+"','"+mainPerson+"','"+phoneNumber+"','"+email+"','"+zipCode+"','"+magicboxAddress+"')";
			int re =db.insert(sql);
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
	

	public List queryHomeInfo(String homeid,String homename ) {
		String sql = "select * from homeinfo where 1=1 "  ;
		if(homeid!="")
		{
			sql+="and homeid = '"+homeid+"' ";
		}
		if(homename!="")
		{
			sql+="and homename like '%"+homename+"%' ";
		}
		
		ResultSet rs = db.query(sql);
		List list = new ArrayList();
		
			try {
				while(rs!=null && rs.next()){
					HomeInfo homeInfo = new HomeInfo();
				homeInfo.setHomeID(rs.getString("homeID"));
				homeInfo.setHomeName(rs.getString("homeName"));
				homeInfo.setHomeAddress(rs.getString("homeAddress"));
				homeInfo.setMainPersonID(rs.getString("mainPersonID"));
				homeInfo.setMainPerson(rs.getString("mainPerson"));
				homeInfo.setPhoneNumber(rs.getString("phoneNumber"));
				homeInfo.setEmail(rs.getString("email"));
				homeInfo.setZipCode(rs.getString("zipCode"));
				homeInfo.setHomeSort(rs.getString("homeSort"));
				homeInfo.setMagicboxAddress(rs.getString("magicboxAddress"));
				list.add(homeInfo);
				}
			} catch (SQLException e) {
				db.close();
				return list;
			}
			
			db.close();
			return list;
		
	}

	public String updateHomeInfo(String homeID,String homeName,String homeSort, String homeAddress,
			String mainPersonID, String mainPerson,
			String phoneNumber, String email, String zipCode,String magicboxAddress) {
		String sql = "update homeinfo set homename = '" + homeName
				+ "',homesort = '"+homeSort+"',homeaddress='" + homeAddress + "',mainpersonid='"
				+ mainPersonID + "',mainperson='" + mainPerson
				+ "',phoneNumber='" + phoneNumber + "',email='" + email
				+ "',zipCode='" + zipCode + "',magicboxaddress='" + magicboxAddress
				+ "' where homeid ='" + homeID + "'";
		db.update(sql);
		db.close();
		return "success";
	}

	public List queryUser(String homeid) {
		String sql = "select * from home_userinfo where homeid='"+homeid+"'";
		List list = new ArrayList();
		ResultSet rs = db.query(sql);
		try {
				while(rs!=null && rs.next()){
					HomeUserDetail user=new HomeUserDetail();
					user.setH_id(rs.getString("h_id"));
					user.setH_name(rs.getString("h_name"));
					user.setGender(rs.getString("gender"));
				list.add(user);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			db.close();

			return list;
		}
		db.close();
		return list;
	}
	public String deletHome(String homeid){
		String sql="delete from homeinfo where homeid='"+homeid+"'";
		//String sql1="delete from home_userinfo where homeid='"+homeid+"'";
		db=new DB();
		int re = db.delete(sql);
		//int re1 = db.delete(sql1);
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
	
	
}
