package servlet.providerMenu;



import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import providerMenu.FaultMenu;



import net.sf.json.JSONArray;


public class ModifyMenuServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ModifyMenuServlet()
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
		
		FaultMenu cm=new FaultMenu();
		String result="wrong";
		if(kind.equals("query"))
		{
			String user=request.getParameter("userkind");
			result=cm.getMenu(user);
			
		}
		if(kind.equals("change"))
		{
			String menu=request.getParameter("menu");
			try {
				result=cm.ChangeMenu(menu);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(kind.equals("service"))
		{
			result=cm.getServiceMenu();
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

