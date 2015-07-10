package servlet.homeInfo;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import home.HomeObject;

import net.sf.json.JSONArray;

public class homeObject extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public homeObject()
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
		
		HomeObject hu=new HomeObject();
		String result="wrong";
		if(kind.equals("add"))
		{
			//String homeid=request.getParameter("homeid");
			String homename=request.getParameter("homename");
			String homesort=request.getParameter("homesort");
			String homeaddress=request.getParameter("homeaddress");
			String mainpersonid=request.getParameter("mainpersonid");
			String mainperson=request.getParameter("mainperson");
			String zipcode=request.getParameter("zipcode");
			String phonenumber=request.getParameter("phonenumber");
			String email=request.getParameter("email");
			String magicboxaddress=request.getParameter("magicboxaddress");
			
			
			try {
				result=hu.registerHome(homename,homesort,homeaddress,mainpersonid,mainperson,phonenumber,email,zipcode,magicboxaddress);
				
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
			 String homename=request.getParameter("homename");
			
			List huser=null;
			huser=hu.queryHomeInfo(homeid,homename);
			JSONArray jsonList = JSONArray.fromObject(huser);
			  result=jsonList.toString();
			  
			
		}
		
	
		 if(kind.equals("del"))
		 {
			 String homeid=request.getParameter("homeid");
			 result=hu.deletHome(homeid);
		 }
		 
		 if(kind.equals("edit"))
			{
			 	String homeid=request.getParameter("homeid");
				String homename=request.getParameter("homename");
				String homesort=request.getParameter("homesort");
				String homeaddress=request.getParameter("homeaddress");
				String mainpersonid=request.getParameter("mainpersonid");
				String mainperson=request.getParameter("mainperson");
				String zipcode=request.getParameter("zipcode");
				String phonenumber=request.getParameter("phonenumber");
				String email=request.getParameter("email");
				String magicboxaddress=request.getParameter("magicboxaddress");
				
				try {
					result=hu.updateHomeInfo(homeid,homename,homesort,homeaddress,mainpersonid,mainperson,phonenumber,email,zipcode,magicboxaddress);
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
