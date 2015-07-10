package servlet.businessCenter;





import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessCenter.URole;

import net.sf.json.JSONArray;









public class UserRole extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public UserRole()
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
		System.out.println("查询用户-角色数据表"+kind);
		
		URole mc=new URole();
		String result="wrong";
		 if(kind.equals("queryUserRole"))
			{
				String name=request.getParameter("name");
				String role=request.getParameter("role");
				String sd=request.getParameter("sd");
				String ed=request.getParameter("ed");
				String loginuser=request.getParameter("makeuser");
				String loginrole=request.getParameter("makerole");
				
				List comp2=null;
				comp2=mc.queryUR(name, role, sd, ed, loginuser, loginrole);
				JSONArray jsonList = JSONArray.fromObject(comp2);
				  result=jsonList.toString();
				
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
