package servlet.roleMenu;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import roleMenu.ModifyRoleMenu;



import net.sf.json.JSONArray;










public class RoleMenu extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public RoleMenu()
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
		System.out.println("更改角色-菜单数据表"+kind);
		
		ModifyRoleMenu cm=new ModifyRoleMenu();
		String result="wrong";
		if(kind.equals("add"))
		{
			System.out.println("插入角色-菜单数据");
			String makeruser=request.getParameter("makeruser");
			String makerrole=request.getParameter("makerrole");
			String menuid=request.getParameter("menuid");
			String roleid=request.getParameter("roleid");
			try {
				result=cm.addRoleMenu(roleid, menuid, makeruser, makerrole);
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
			 System.out.println("查询角色-菜单数据");
			String makeruser=request.getParameter("makeruser");
			String makerrole=request.getParameter("makerrole");
			String rolename=request.getParameter("rolename");
			String menuname=request.getParameter("menuname");
			String sd=request.getParameter("sd");
			String ed=request.getParameter("ed");
			List l=cm.getRoleMenu(rolename, makeruser, makerrole, menuname, sd, ed);
			JSONArray jsonList = JSONArray.fromObject(l);
			  result=jsonList.toString();
			
		}
		 if(kind.equals("del"))
		 {
			 String id=request.getParameter("rolemenuid");
			 result=cm.delRoleMenu(id);
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

