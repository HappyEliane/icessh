package servlet.userrole;



import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userrole.ModifyUser;

import net.sf.json.JSONArray;






public class ChangeUser extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ChangeUser()
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
		
		
		String id=request.getParameter("id");
		System.out.println("收到用户请求，查看用户名"+id+"是否可用");
		String kind=request.getParameter("kind");
		ModifyUser mu=new ModifyUser();
		String result="wrong";
		if(kind.equals("add"));
		{
			String username=request.getParameter("username");
			String userpass=request.getParameter("userpass");
			String makeid=request.getParameter("makeid");
			String roleid=request.getParameter("roleid");
			String rolename=request.getParameter("rolename");
			String roletype=request.getParameter("roletype");
			String makerole=request.getParameter("makerole");
			 try {
				result=mu.addUser(username, userpass, makeid,roleid,rolename,roletype,makerole);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 if(kind.equals("del"))
		{
			String userid=request.getParameter("deluserid");
			result=mu.delUser(userid);
		}
		 if(kind.equals("query"))
		 {
			 String makeuser=request.getParameter("makeuser");
			 String makerole=request.getParameter("makerole");
			 String username=request.getParameter("username");
			 String sd=request.getParameter("sd");
			 String ed=request.getParameter("ed");
			 
			 List users=mu.getUser(makeuser, makerole, username, sd, ed);
				JSONArray jsonList = JSONArray.fromObject(users);
				  result=jsonList.toString();
		 }
		 if(kind.equals("queryuser"))
		 {
			 String roleid=request.getParameter("roleid");
			 List users=mu.getUserRole(roleid);
				JSONArray jsonList = JSONArray.fromObject(users);
				  result=jsonList.toString();
		 }
		 if(kind.equals("modify"))
		 {
			 String userid=request.getParameter("userid");
			 String pass=request.getParameter("pass");
			 String state=request.getParameter("state");
			 result=mu.ModifyUserInfo(userid, pass, state);
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
