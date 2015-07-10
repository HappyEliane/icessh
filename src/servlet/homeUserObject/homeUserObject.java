package servlet.homeUserObject;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import HomeUserObject.HomeUserObject;

import net.sf.json.JSONArray;

public class homeUserObject extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public homeUserObject()
	{
		super();
	}
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		
		try{
			request.setCharacterEncoding("UTF-8");
		}catch(UnsupportedEncodingException e1)
		{
			e1.printStackTrace();
		}
		
		
		String kind=request.getParameter("kind");
		System.out.println("更改构件数据表。。。"+kind);
		
		HomeUserObject hu=new HomeUserObject();
		String result="wrong";
		if(kind.equals("add"))
		{
			//String h_id=request.getParameter("h_id");
			String h_name=request.getParameter("h_name");
			String gender=request.getParameter("gender");
			String birth=request.getParameter("birth");
			String birthplace=request.getParameter("birthplace");
			String education=request.getParameter("education");
			String zipcode=request.getParameter("zipcode");
			String idcard=request.getParameter("idcard");
			String mobile=request.getParameter("mobile");
			String address=request.getParameter("address");
			String qqnum=request.getParameter("qqnum");
			String msnnum=request.getParameter("msnnum");
			String email=request.getParameter("email");
			//String ismainperson=request.getParameter("ismainperson");
			String homeid=request.getParameter("homeid");
			
			try {
				result=hu.saveHuser(h_name,gender,birth,birthplace,education,idcard,mobile,address,qqnum,msnnum,email,zipcode,homeid);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 if(kind.equals("query"))
		{
			 System.out.println("开始查询");
			 String homeid=request.getParameter("homeid");
			
			//List huser=null;
			List huser=hu.queryHuser(homeid);		
			JSONArray jsonList = JSONArray.fromObject(huser);
			  result=jsonList.toString();		
		}
		
	
		 if(kind.equals("del"))
		 {
			 String h_id=request.getParameter("h_id");
			 result=hu.deletHuser(h_id);
		 }
		 
		 if(kind.equals("edit"))
			{
				String h_id=request.getParameter("h_id");
				String h_name=request.getParameter("h_name");
				String gender=request.getParameter("gender");
				String birth=request.getParameter("birth");
				String birthplace=request.getParameter("birthplace");
				String education=request.getParameter("education");
				String zipcode=request.getParameter("zipcode");
				String idcard=request.getParameter("idcard");
				String mobile=request.getParameter("mobile");
				String address=request.getParameter("address");
				String qqnum=request.getParameter("qqnum");
				String msnnum=request.getParameter("msnnum");
				String email=request.getParameter("email");
				String ismainperson=request.getParameter("ismainperson");
				//String homeid=request.getParameter("homeid");
				
				try {
					result=hu.updateHomeUser(h_id,h_name,gender,birth,birthplace,education,idcard,mobile,address,qqnum,msnnum,email,zipcode,ismainperson);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 
		response.setContentType("text/html;charset=UTF-8");
		PrintWriter pout=null;
		try{
			pout=response.getWriter();
		}catch(IOException e)
		{
			e.printStackTrace();
		}
		pout.print(result);
		
	}
	
	
	

}
