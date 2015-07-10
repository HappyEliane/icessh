package homeService;

import bean.servicetheme;
import db.DB;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;


public class getAllTheme {

	private DB db;

	public getAllTheme() {
		db = new DB();
	}

	public List queryServiceTheme() {
		String sql = "select * from ices_comp_theme";
		
		ResultSet rs = db.query(sql);
		List list = new ArrayList();
			try {
				while(rs!=null && rs.next()){
				servicetheme theme = new servicetheme();
				theme.setServicethemeID(rs.getString("comp_theme_id"));
				theme.setServicethemeName(rs.getString("comp_theme_name"));
				theme.setServicethemeDes(rs.getString("comp_theme_des"));
				list.add(theme);
				}
			} 
				catch (SQLException e) {
				e.printStackTrace();
			}
			
			db.close();
			return list;
		}
	}
