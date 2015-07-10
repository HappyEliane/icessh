package servlet.roleMenu;




import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import roleMenu.ModifyRole;

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
		
		 if(kind.equals("role"))
		{
			String user=request.getParameter("user");
			String role=request.getParameter("role");
	
			 List roles=mu.getAllRole(user, role);
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

