package servlet.serviceTheme;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import servicetheme.theme;

import net.sf.json.JSONArray;








public class serviceThemeServlet extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public serviceThemeServlet()
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
		System.out.println("更改构件数据表。。。"+kind);
		
		theme th=new theme();
		String result="wrong";
		if(kind.equals("add"))
		{
			String servicethemeName=request.getParameter("servicethemeName");
			String servicethemeDes=request.getParameter("servicethemeDes");
			try {
				result=th.registerTheme(servicethemeName, servicethemeDes);
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
			 System.out.println("开始查询");
			String servicethemeID=request.getParameter("servicethemeID");
			String servicethemeName=request.getParameter("servicethemeName");
			List theme=null;
			theme=th.queryTheme(servicethemeID, servicethemeName);
			JSONArray jsonList = JSONArray.fromObject(theme);
			  result=jsonList.toString();
			  
			
		}
		 if(kind.equals("edit"))
			{
			 String servicethemeID=request.getParameter("servicethemeID");
				String servicethemeName=request.getParameter("servicethemeName");
				String servicethemeDes=request.getParameter("servicethemeDes");
				try {
					result=th.updateServiceTheme(servicethemeID,servicethemeName, servicethemeDes);
				} catch (NumberFormatException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		 if(kind.equals("del"))
		 {
			 String themeid=request.getParameter("themeid");
			 result=th.deletTheme(themeid);
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