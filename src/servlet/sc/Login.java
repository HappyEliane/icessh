package servlet.sc;

import sc.userLogin;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class Login extends HttpServlet{

	public Login()
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
		String result="wrong";
		String kind=request.getParameter("kind");
		userLogin ul=new userLogin();
		if(kind.equals("verify"))
		{
			String name=request.getParameter("name");
			String pw=request.getParameter("pw");
			String org=request.getParameter("org");
			String role=request.getParameter("role");
			
			
			result=ul.login(name, pw, org, role);
			System.out.println(result);
		}
		if(kind.equals("menu"))
		{
			String role=request.getParameter("role");
			String name=request.getParameter("user");
			result=ul.getMenu(name,role);
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


