package servlet.userrole;




import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import userrole.ModifyRole;

import net.sf.json.JSONArray;






public class ChangeRole extends HttpServlet{

	public ChangeRole()
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
		ModifyRole mu=new ModifyRole();
		String result="wrong";
		if(kind.equals("add"))
		{
		System.out.println("角色注册");
		String rolename=request.getParameter("rolename");
		String rolekind=request.getParameter("rolekind");
		String rolestate=request.getParameter("rolestate");
		String makerole=request.getParameter("makerole");
		String makeuser=request.getParameter("makeuser");
		
		try {
			result = mu.insertRole(rolename, rolekind,makeuser, makerole, rolestate);
		} catch (NumberFormatException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		}
		else if(kind.equals("del"))
		{
			System.out.println("角色删除");
			String userroleid=request.getParameter("roleid");
			result=mu.delRole(userroleid);
		}
		else if(kind.equals("query"))
		{
			System.out.println("角色名称验证");
			String rname=request.getParameter("rolename");
			result=mu.queryRoleName(rname);
		}
		else if(kind.equals("queryrole"))
		{
			String userid=request.getParameter("userid");
			 List roles=mu.getUserRole(userid);
				JSONArray jsonList = JSONArray.fromObject(roles);
				  result=jsonList.toString();
		}
		else if(kind.equals("role"))
		{
			String user=request.getParameter("user");
			String role=request.getParameter("role");
			String rolename=request.getParameter("rolename");
			 List roles=mu.getAllRole(user, role,rolename);
				JSONArray jsonList = JSONArray.fromObject(roles);
				  result=jsonList.toString();
		}
		if(kind.equals("modify"))
		{
			String roleid=request.getParameter("roleid");
			String state=request.getParameter("state");
			result=mu.changeRoleInfo(roleid, state);
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

