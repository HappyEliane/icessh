package servicetheme;

import bean.servicetheme;
import db.DB;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import tool.CreateKey;


public class theme {

	private DB db;

	public theme() {
		db = new DB();
	}
	
	public String registerTheme(String themeName,String themeDes)throws NumberFormatException, SQLException {
			CreateKey ck=new CreateKey("ices_comp_theme","comp_theme_id","THEME");
			String sql = "insert into ices_comp_theme (comp_theme_id,comp_theme_name,comp_theme_des) values";
			sql+="('"+ck.getKey()+"','"+themeName+"','"+themeDes+"')";
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


	public List queryTheme(String themeid,String themeName ) {
		String sql = "select * from ices_comp_theme where 1=1 "  ;
		if(themeid!="")
		{
			sql+="and comp_theme_id = '"+themeid+"' ";
		}
		if(themeName!="")
		{
			sql+="and comp_theme_name like '%"+themeName+"%' ";
		}
		
		ResultSet rs = db.query(sql);
		List list = new ArrayList();
		int i = 1;
			try {
				while(rs!=null && rs.next()){
					servicetheme theme = new servicetheme();
					theme.setServicethemeID(rs.getString("comp_theme_id"));
					theme.setServicethemeName(rs.getString("comp_theme_name"));
					theme.setServicethemeDes(rs.getString("comp_theme_des"));
					theme.setNum(""+i);
					i++;
					list.add(theme);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			db.close();
			return list;
		
	}

	public String updateServiceTheme(String themeid,String themeName,String themeDes) {
		String sql = "update ices_comp_theme set comp_theme_name = '" + themeName
				+ "',comp_theme_des = '"+themeDes+"' where comp_theme_id ='" + themeid + "'";
		db.update(sql);
		db.close();
		return "success";
	}

	public List queryAllTheme() {
		String sql = "select * from ices_comp_theme";
		ResultSet rs = db.query(sql);
		List list = new ArrayList();
		int i = 1;
			try {
				while(rs!=null && rs.next()){
					servicetheme theme = new servicetheme();
					theme.setServicethemeID(rs.getString("servicethemeID"));
					theme.setServicethemeName(rs.getString("servicethemeName"));
					theme.setServicethemeDes(rs.getString("servicethemeDes"));
					theme.setNum(""+i);
					i++;
					list.add(theme);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			db.close();
			return list;
		
	}
	public String deletTheme(String themeid){
		String sql="delete from ices_comp_theme where comp_theme_id='"+themeid+"'";
		db=new DB();
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
	
	
}
