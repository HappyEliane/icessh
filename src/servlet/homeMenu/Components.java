package servlet.homeMenu;





import homeMenu.ModifyComponent;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;








public class Components extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public Components()
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
		System.out.println("更改构件数据表"+kind);
		
		ModifyComponent mc=new ModifyComponent();
		String result="wrong";
		 if(kind.equals("query1"))
			{
				String name=request.getParameter("name");
				String maker=request.getParameter("maker");
				String sd=request.getParameter("sd");
				String ed=request.getParameter("ed");
				String qorg=request.getParameter("qorg");
				String qinfo=request.getParameter("qinfo");
				String comp1=request.getParameter("menu");
				List comp2=null;
				comp2=mc.chooseComp(name, qinfo, maker, sd, ed, qorg, comp1);
				JSONArray jsonList = JSONArray.fromObject(comp2);
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
