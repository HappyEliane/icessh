package servlet.homeservice;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import homeService.HomeServices;
import homeService.HomeObject;
import homeService.getAllTheme;
import homeService.ModifyComponent;

import net.sf.json.JSONArray;










public class HomeService extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public HomeService()
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
		System.out.println("更改家庭-服务数据表"+kind);
		
		HomeServices cm=new HomeServices();
		String result="wrong";
		if(kind.equals("add"))
		{
			System.out.println("插入家庭-服务数据");
			String homeid=request.getParameter("homeid");
			String servicethemeid=request.getParameter("servicethemeid");
			String serviceid=request.getParameter("serviceid");
			String permission=request.getParameter("permission");
			try {
				result=cm.addHomeService(homeid, servicethemeid, serviceid, permission);
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
			 System.out.println("查询家庭-服务数据");
			
			String homename=request.getParameter("homename");
			String servicetheme=request.getParameter("servicetheme");
			String service=request.getParameter("service");
			
			List l=cm.getHomeService(homename, servicetheme, service);
			JSONArray jsonList = JSONArray.fromObject(l);
			  result=jsonList.toString();
			
		}
	
		 if(kind.equals("del"))
		 {
			 String id=request.getParameter("id");
			 result=cm.delHomeService(id);
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

