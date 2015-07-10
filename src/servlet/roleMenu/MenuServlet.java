package servlet.roleMenu;



import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import roleMenu.ChangeMenu;

import net.sf.json.JSONArray;










public class MenuServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public MenuServlet()
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
		System.out.println("更改菜单数据表"+kind);
		
		ChangeMenu cm=new ChangeMenu();
		String result="wrong";
		
		 if(kind.equals("query"))
		{
			String user=request.getParameter("user");
			String role=request.getParameter("role");
			List l=cm.getMenu(user,role);
			JSONArray jsonList = JSONArray.fromObject(l);
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

