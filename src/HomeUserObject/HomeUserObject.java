package HomeUserObject;

import bean.HomeUserDetail;
//import ices.sh.mbs.bean.UserIDAndName;
import db.DB;
import tool.CreateKey;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class HomeUserObject {

	//private DB db;

	public HomeUserObject() {
		DB db = new DB();
	}
	/*public UserIDAndName getUserNameByUserID(String userID){
		String sql="select h_name from mbs.home_userinfo where h_id='"+userID+"'";
		System.out.println(sql);
		ResultSet rs=db.query(sql);
		UserIDAndName userIDAndName = new UserIDAndName();
		userIDAndName.setUserID(userID);
		try {
			if(rs.next()){
				userIDAndName.setUserName(rs.getString("h_name"));
				db.close();
				return userIDAndName;
			}
			else{
				db.close();
				return userIDAndName;
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			db.close();
			return userIDAndName;
		}
	}*/
	/*public void deletHuser(String userid){
		String sql="delete from home_userinfo where h_id='"+userid+"'";
		db=new DB();
		db.delete(sql);
		db.close();
	}
	
	public void saveHuser(String h_id,String h_name,String gender,String birth,String birthplace,String education,String idcard,String mobile,String address,String qqnum,String msnnum,String email,String zipcode,String ismainperson,String homeid){
		String sql="insert into home_userinfo ";
		sql+="(h_id,h_name,gender,birth,birthplace,education,idcard,mobile,address,qqnum,msnnum,email,zipcode,homeid)"; 
		sql+="values ('"+h_id+"','"+h_name+"','"+gender+"','"+birth+"','"+birthplace+"','"+education+"','"+idcard+"',";
		sql+="'"+mobile+"','"+address+"','"+qqnum+"','"+msnnum+"','"+email+"','"+zipcode+"','"+ismainperson+"'+'"+homeid+"')";
		
		db=new DB();
		db.insert(sql);
		db.close();
		
	}
	
	public List queryHuser(String homeid){
		
		List list=new ArrayList();
		
		//String sql="(select * from mbs.home_userinfo where homeid='"+homeid+"')";
		String sql="(select * from home_userinfo where homeid='1')";
		db=new DB();
		ResultSet rs=db.query(sql);
		try{
			while(rs!=null && rs.next()){
				HomeUserDetail user=new HomeUserDetail();
				user.setH_id(rs.getString("h_id"));
				user.setH_name(rs.getString("h_name"));
				user.setGender(rs.getString("gender"));
				user.setBirth(rs.getString("birth"));
				user.setBirthplace(rs.getString("birthplace"));
				user.setEducation(rs.getString("education"));
				user.setZipcode(rs.getString("zipcode"));
				user.setIdCard(rs.getString("idcard"));
				user.setMobile(rs.getString("mobile"));
				user.setAddress(rs.getString("address"));
				user.setQqnum(rs.getString("qqnum"));
				user.setMsnnum(rs.getString("msnnum"));
				user.setEmail(rs.getString("email"));
				user.setIsMainPerson(rs.getString("ismainperson"));
				user.setHomeId(rs.getString("homeid"));
				list.add(user);
			}
			
		}
		catch(Exception e){
			db.close();
			return list;
		}
		db.close();
		return list;
	}
	

	
	public List queryHomeUser(){
		System.out.println("查询家庭成员");
		List list=new ArrayList();
		//String sql="select * from home_userinfo where homeid = '1' order by h_id";
		String sql="select * from home_userinfo ";
		
		db=new DB();
		ResultSet rs=db.query(sql);
		try{
			while(rs.next()){
				HomeUserDetail hu=new HomeUserDetail();
				hu.setH_id(rs.getString("h_id"));
				hu.setH_name(rs.getString("h_name"));
				hu.setGender(rs.getString("gender"));
				hu.setBirth(rs.getString("birth"));
				hu.setBirthplace(rs.getString("birthplace"));
				hu.setEducation(rs.getString("education"));
			
				hu.setZipcode(rs.getString("zipcode"));
				hu.setIdCard(rs.getString("idcard"));
				
				hu.setMobile(rs.getString("mobile"));
				hu.setAddress(rs.getString("address"));
				hu.setQqnum(rs.getString("qqnum"));
				hu.setMsnnum(rs.getString("msnnum"));
				hu.setEmail(rs.getString("email"));
				hu.setIsMainPerson(rs.getString("ismainperson"));
				hu.setHomeId(rs.getString("homeid"));
				list.add(hu);
			}
			
		}
		catch(Exception e){
			db.close();
			return list;
		}
		db.close();
		return list;
	}
	
	public void updateHomeUser(String userid,String newname,String newgender,String newbirth,String newbirthplace,String neweducation,String newidcard,String newmobile,String newaddress,String newqq,String newmsn,String newemail,String newzipcode,String ismainperson,String homeid){
		String sql="update home_userinfo set ";
		sql+="h_name='"+newname+"',gender='"+newgender+"',birth='"+newbirth+"',birthplace='"+newbirthplace+"',education='"+neweducation+"',idcard='"+newidcard+"'," +
			"mobile='"+newmobile+"',address='"+newaddress+"',qqnum='"+newqq+"',msnnum='"+newmsn+"',email='"+newemail+"',"+
			"zipcode='"+newzipcode+"',ismainperson='"+ismainperson+"' where h_id='"+userid+"'";
		db=new DB();
		db.update(sql);
		db.close();
	}*/
	public String deletHuser(String userid){
		String sql="delete from home_userinfo where h_id='"+userid+"'";
		DB db=new DB();
		int re = db.delete(sql);
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
	
	public String saveHuser(String h_name,String gender,String birth,String birthplace,String education,String idcard,String mobile,String address,String qqnum,String msnnum,String email,String zipcode,String homeid)throws NumberFormatException, SQLException{
		CreateKey ck=new CreateKey("home_userinfo","h_id","HU");
		String sql="insert into home_userinfo ";
		sql+="(h_id,h_name,gender,birth,birthplace,education,idcard,mobile,address,qqnum,msnnum,email,zipcode,homeid)"; 
		sql+="values ('"+ck.getKey()+"','"+h_name+"','"+gender+"','"+birth+"','"+birthplace+"','"+education+"','"+idcard+"',";
		sql+="'"+mobile+"','"+address+"','"+qqnum+"','"+msnnum+"','"+email+"','"+zipcode+"','"+homeid+"')";
		
		DB db=new DB();
		int re=db.insert(sql);
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
	
	
	public List queryHuser(String homeid){
		
		List list=new ArrayList();
		
		//String sql="(select * from mbs.home_userinfo where homeid='"+homeid+"')";
		String sql="(select * from home_userinfo where homeid='"+homeid+"')";
		DB db=new DB();
		ResultSet rs=db.query(sql);
		try{
			while(rs!=null && rs.next()){
				HomeUserDetail user=new HomeUserDetail();
				user.setH_id(rs.getString("h_id"));
				user.setH_name(rs.getString("h_name"));
				user.setGender(rs.getString("gender"));
				user.setBirth(rs.getString("birth"));
				user.setBirthplace(rs.getString("birthplace"));
				user.setEducation(rs.getString("education"));
				user.setIdCard(rs.getString("idcard"));	
				user.setMobile(rs.getString("mobile"));
				user.setAddress(rs.getString("address"));
				user.setQqnum(rs.getString("qqnum"));
				user.setMsnnum(rs.getString("msnnum"));
				user.setEmail(rs.getString("email"));
				user.setZipcode(rs.getString("zipcode"));
				//user.setIsMainPerson(rs.getString("ismainperson"));
				user.setHomeId(rs.getString("homeid"));
				list.add(user);
				String rorg=(rs.getString("homeid"));
				System.out.println(rorg);
				String rorg1=(rs.getString("ismainperson"));
				System.out.println(rorg1);
				String rorg2=(rs.getString("idcard"));
				System.out.println(rorg2);
			}
			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		db.close();
		return list;
	}
	

	
	public List queryHomeUser(){
		System.out.println("查询家庭成员");
		List list=new ArrayList();
		//String sql="select * from home_userinfo where homeid = '1' order by h_id";
		String sql="select * from home_userinfo ";
		
		DB db=new DB();
		ResultSet rs=db.query(sql);
		try{
			while(rs.next()){
				HomeUserDetail hu=new HomeUserDetail();
				hu.setH_id(rs.getString("h_id"));
				hu.setH_name(rs.getString("h_name"));
				hu.setGender(rs.getString("gender"));
				hu.setBirth(rs.getString("birth"));
				hu.setBirthplace(rs.getString("birthplace"));
				hu.setEducation(rs.getString("education"));
			
				hu.setZipcode(rs.getString("zipcode"));
				hu.setIdCard(rs.getString("idcard"));
				
				hu.setMobile(rs.getString("mobile"));
				hu.setAddress(rs.getString("address"));
				hu.setQqnum(rs.getString("qqnum"));
				hu.setMsnnum(rs.getString("msnnum"));
				hu.setEmail(rs.getString("email"));
				hu.setIsMainPerson(rs.getString("ismainperson"));
				hu.setHomeId(rs.getString("homeid"));
				list.add(hu);
			}
			
		}
		catch(Exception e){
			db.close();
			return list;
		}
		db.close();
		return list;
	}
	
	public String updateHomeUser(String userid,String newname,String newgender,String newbirth,String newbirthplace,String neweducation,String newidcard,String newmobile,String newaddress,String newqq,String newmsn,String newemail,String newzipcode,String ismainperson){
		String sql="update home_userinfo set ";
		sql+="h_name='"+newname+"',gender='"+newgender+"',birth='"+newbirth+"',birthplace='"+newbirthplace+"',education='"+neweducation+"',idcard='"+newidcard+"'," +
			"mobile='"+newmobile+"',address='"+newaddress+"',qqnum='"+newqq+"',msnnum='"+newmsn+"',email='"+newemail+"',"+
			"zipcode='"+newzipcode+"',ismainperson='"+ismainperson+"' where h_id='"+userid+"'";
		DB db=new DB();
		int re=db.update(sql);
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
