package servlet.sc;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import sc.icesProvider;
import sc.icesuser;


public class userOperation extends HttpServlet{
	public userOperation()
	{
		super();
	}
	protected void doGet(HttpServletRequest request,HttpServletResponse response)
	{
		doPost(request,response);
	}
	protected void doPost(HttpServletRequest request,HttpServletResponse response)
	{
		System.out.println("收到家庭用户注册请求！！！");
		try{
			request.setCharacterEncoding("UTF-8");
		}catch(UnsupportedEncodingException e1)
		{
			e1.printStackTrace();
		}
		String kind=request.getParameter("kind");
		icesuser iu=new icesuser();
		String result="wrong";
		if(kind.equals("home"))
		{
		String id=request.getParameter("id");
		String realname=request.getParameter("realname");
		String pass=request.getParameter("pass");
		String email=request.getParameter("email");
		String phone=request.getParameter("phone");
		String contact=request.getParameter("contact");
		String contactid=request.getParameter("contactid");
		String contactphone=request.getParameter("contactphone");
		String address=request.getParameter("address");
		
		
		try {
			result=iu.insertHomeUser(id, realname, pass, email, phone, address, contact, contactid, contactphone);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
		if(kind.equals("provider"))
		{
			String id=request.getParameter("id");
			String realname=request.getParameter("realname");
			String pass=request.getParameter("pass");
			String email=request.getParameter("email");
			String phone=request.getParameter("phone");
			
		
			
			String address=request.getParameter("address");
			String company=request.getParameter("company");
			String legalperson=request.getParameter("legalperson");
			String comcode=request.getParameter("comcode");
			String comnum=request.getParameter("comnum");
			String comtact=request.getParameter("comtact");
			String comphone=request.getParameter("comphone");
			icesProvider ip=new icesProvider();
			try {
				result=ip.insertProvider(id, realname, pass, email, phone, address, company, legalperson, comcode, comnum, comtact, comphone);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
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
