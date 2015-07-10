package db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import com.sina.sae.util.SaeUserInfo;


public class DB {
	private String url="";
	private Connection conn;
	private Statement stmt = null;
	
	public DB(){
		try { 
			
			String className="com.mysql.jdbc.Driver";
			url = "jdbc:mysql://127.0.0.1:3306/icesshsc?useunicode=true&characterEncoding=UTF-8";     //root.elementText("url");
			String user = "root";  //root.elementText("user");
			String password = "123456"; //root.elementText("password");
			
			
			Class.forName(className);
			conn = DriverManager.getConnection(url,user,password);
			stmt = conn.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,
					ResultSet.CONCUR_UPDATABLE);
			
			/*
			String username=SaeUserInfo.getAccessKey();
			String password=SaeUserInfo.getSecretKey();
			String className="com.mysql.jdbc.Driver";
			url = "jdbc:mysql://w.rdc.sae.sina.com.cn:3307/app_icessh";    	
			Class.forName(className);
			conn = DriverManager.getConnection(url,username,password);
			stmt = conn.createStatement();
			*/
		} catch (Exception e) {
			e.printStackTrace();
		} 
	}
	
	
	public int insert(String sql) {
		try {
			
			System.out.println("ִ�в���sql���:"+sql);
			return stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			ex.printStackTrace();
			return 0;
		} 
	}

	public int delete(String sql) {
		int m = 0;
		try {
			System.out.println("ִ��ɾ��sql���:"+sql);
			m = stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			ex.printStackTrace();

		}
		return m;
	}

	public ResultSet query(String sql) {
		try {
			
			System.out.println("ִ�в�ѯsql���:"+sql);
			ResultSet rs = stmt.executeQuery(sql);
			if(rs!=null)
			{
				System.out.println("��ѯ�ɹ�����");
			}
			return rs;
		} catch (SQLException ex) {
			ex.printStackTrace();
			return null;
		} 
	}

	public int update(String sql) {
		int m = 0;
		try {
			System.out.println("ִ�и���sql���:"+sql);
			m = stmt.executeUpdate(sql);
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		return m;
	}
	
	public void close(){
		try {
			stmt.close();
			conn.close();
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		
	}


}
