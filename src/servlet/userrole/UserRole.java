package servlet.userrole;


import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userrole.ModifyUserRole;

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
		
		
		
		System.out.println("收到用户请求，用户-角色关系表操作");
		String kind=request.getParameter("roleKind");
		ModifyUserRole mu=new ModifyUserRole();
		String result="wrong";
		
		if(kind.equals("add"))
		{
			System.out.println("插入用户角色关系");
			String userid=request.getParameter("userid");
			String roleid=request.getParameter("roleid");
			String makeuser=request.getParameter("makeuser");
			String makerole=request.getParameter("makerole");
			try {
				result=mu.insertUserRole(userid, roleid, makeuser,makerole);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 if(kind.equals("del"))
		{
			 System.out.println("删除用户-角色关系");
			String userroleid=request.getParameter("userroleid");
			result=mu.delUserRole(userroleid);
		}
		 if(kind.equals("query"))
		 {
			 System.out.println("查询");
			 String makeuser=request.getParameter("makeuser");
			 String makerole=request.getParameter("makerole");
			 String username=request.getParameter("username");
			 String rolename=request.getParameter("rolename");
			 List roles=mu.getUserRole(makeuser, makerole, username, rolename);
			 JSONArray jsonList = JSONArray.fromObject(roles);
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

