package servlet.homeservice;




import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import homeService.ModifyComponent;

import net.sf.json.JSONArray;







public class serviceServlet extends HttpServlet{

	public serviceServlet()
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
		ModifyComponent co=new ModifyComponent();
		String result="wrong";
		
		 if(kind.equals("querycomp"))
		{
			 List roles=co.queryComp();
				JSONArray jsonList = JSONArray.fromObject(roles);
				  result=jsonList.toString();
		}
		 if(kind.equals("querythemeservice"))
			{
				 System.out.println("查询主题-服务数据");
				
				String themeid=request.getParameter("themeid");
				
				List l=co.getThemeService(themeid);
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

