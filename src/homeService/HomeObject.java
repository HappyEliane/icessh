package homeService;

import bean.HomeInfo;
import bean.HomeUserDetail;
import db.DB;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class HomeObject {

	private DB db;

	public HomeObject() {
		db = new DB();
	}

	public List queryHomeInfo() {
		String sql = "select * from homeinfo";
		ResultSet rs = db.query(sql);
		List list = new ArrayList();
			try {
				while(rs!=null && rs.next()){
				HomeInfo homeInfo = new HomeInfo();
				homeInfo.setHomeID(rs.getString("homeid"));
				homeInfo.setHomeName(rs.getString("homename"));
				homeInfo.setHomeAddress(rs.getString("homeaddress"));
				homeInfo.setMainPersonID(rs.getString("mainpersonid"));
				homeInfo.setMainPerson(rs.getString("mainperson"));
				homeInfo.setPhoneNumber(rs.getString("phonenumber"));
				homeInfo.setEmail(rs.getString("email"));
				homeInfo.setZipCode(rs.getString("zipcode"));
				homeInfo.setHomeSort(rs.getString("homesort"));
				homeInfo.setMagicboxAddress(rs.getString("magicboxaddress"));
				list.add(homeInfo);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}		
			db.close();
			return list;
		}
	}
