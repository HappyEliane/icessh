package sc;



import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;

import db.DB;

import tool.CreateKey;


public class icesProvider {
	public String insertProvider(String userid,String realname,String password,String email,
			String phone,String address,String company,String legalperson,String comcode,String comnum,String comtact,String comphone) throws NumberFormatException, SQLException
	{
		System.out.println("��Ӧ���û�ע�ᣡ");
		String result1="wrong";
		String result2="wrong";
		String result3="wrong";
		String result4="wrong";
		String temp_str="";//��ȡ��ǰ����   
	    Date dt = new Date();   
	    //����aa��ʾ�����硱�����硱    HH��ʾ24Сʱ��    �������hh��ʾ12Сʱ��   
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd ");   
	    temp_str=sdf.format(dt);  
	    String homeid="";
	    CreateKey ck=new CreateKey("ices_provider","id","PRI");
	    homeid=ck.getKey();
	    String sql="INSERT INTO ices_provider( " +
			    " id, realname, email, phone, address, company, legalperson, comcode, comnum, comcontact, comphone, registerdate)"+
			    " VALUES ('"+homeid+"','"+realname+"', '"+email+"',  '"+phone+"',  '"+address+"', '"+company+"', '"+legalperson+"', '"+comcode
			    +"', '"+comnum+"', '"+comtact+"','"+comphone+"','"+temp_str+"')";
	    DB db=new DB();
	    
	    int result=db.insert(sql);
	    if(result!=0)
	    {
	    	 result1="ok";
	    }
	    else
	    	result1="wrong";
	    
	    String user_id="";//�û�ID
	  //  CreateKey ck11=new CreateKey("ices_provider","id","PRI");
		try {
			user_id=getUseridKey();
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	//	CreateKey ck1=new CreateKey("ices_sc_role","sc_role_id","CRI");
		String role_id="CRI0003";//��Ӧ���û�����Ա��ɫ��ID
	    result2=insertIcesUser(user_id,userid,password,temp_str,homeid);//�������û�
	  //  result3=insertProviderUserAdminRole(role_id,userid,temp_str);//Ϊ���û����Ĭ�Ϲ���Ա��ɫ
	    try {
			result4=insertUserRole(user_id,userid,role_id,"Padmin",temp_str);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}//����û�-��ɫ��
		db.close();
	    if(result1.equals("ok") && result2.equals("ok")  && result4.equals("ok"))
	    {
	    	System.out.println("���û�ע��ɹ���");
	    	return "ok";
	    }
	    System.out.println("���û�ע��ʧ�ܣ�");
	    return "wrong";
	}
	public String insertIcesUser(String user_id,String username,String password,String make_date,String homeid)
	{
		
		String sql="INSERT INTO ices_user( " +
			    " user_id, user_name, password,  make_date, user_state,  cloud, make)"+
			    " VALUES ('"+user_id+"','"+username+"', '"+password+"', '"+make_date+"',  '"+"1"+"', '"+homeid+"', '"+"URI0003"+"')";
		 DB db=new DB();
		    
		    int result=db.insert(sql);
		    db.close();
		    if(result!=0)
		    {
		    	return "ok";
		    }
		    else
		    	return "wrong";
	}
	
	public String getUseridKey() throws NumberFormatException, SQLException
	{
		System.out.println("��ȡices_user�������û���������");
		DB db1=new DB();
		String num="";
		ResultSet rs=db1.query("SELECT * FROM ices_user");
		String s1="0000000";
	    String bn="";
	    int b=0;
	    if(rs!=null && rs.next())
	     {
		    s1=rs.getString("user_id");
			while(rs.next())
		   {	 
				 bn=rs.getString("user_id");
				 if(bn.compareTo(s1)>=0)
					 s1=bn;		
		   }
			  String bn33=s1.substring(3, 7);
		      b=Integer.parseInt(bn33);//��ǰ�������ֲ���
		} 
		 b=b+1;
		String ss=Integer.toString(b);
		int le=ss.length();
		int leng=4;
		 for(int bl=le;bl<leng;bl++)
			 ss='0'+ss;
		num="USI"+ss;   //��������
		db1.close();
		return num;
	}
	public String check_userID(String id) throws SQLException
	{
		System.out.println("��ѯ��ǰ�û��Ƿ���ڣ�ע����û���Ϊ"+id);
		String sql="select * from ices_user where user_name = '"+id+"' ";
		DB db=new DB();
		ResultSet rs=db.query(sql);
		boolean result=true;
		while(rs!=null && rs.next())
    	{
    		result=false;
    	}
		db.close();
		if(result)
		{
			System.out.println(id+"�����ڣ����û�������ʹ��");
			return "ok";
		}
		System.out.println(id+"���ڣ����û�������ʹ��");
		return "wrong";
	}

	public String insertUserRole(String userid,String username,String roleid,String rolename,String update_date) throws NumberFormatException, SQLException
	{
		System.out.println("����û�-��ɫ��ϵ��");
			CreateKey ck=new CreateKey("ices_user_role","user_role_id","URI");
			String sql="INSERT INTO ices_user_role( " +
				    " user_role_id, user_id, user_name, role_id, role_type, role_name,  update_date, make)"+
				    " VALUES ('"+ck.getKey()+"','"+userid+"', '"+username+"', '"+roleid+"',  '"+"admin"+"', '"+rolename+"', '"+update_date+"', '"+"URI0003"+"')";
			 DB db=new DB();
			 int result=db.insert(sql);
			 db.close();
			 if(result!=0)
			    {
				 System.out.println("�û���ɫ��ϵ��ӳɹ���");
			    	return "ok";
			    }
			    else
			    {
			    	System.out.println("�û���ɫ��ϵ���ʧ�ܣ�");
			    	return "wrong";
			    }
	}
	
	
}
