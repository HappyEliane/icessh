package servlet.icesmenu;

import icesmenu.QueryComponent;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;



import net.sf.json.JSONArray;



public class ComponentQuery extends HttpServlet{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public ComponentQuery()
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
		
		QueryComponent qc=new QueryComponent();
		String result="wrong";
		if(kind.equals("add"))
		{
		}
		 if(kind.equals("query"))
		{String name=request.getParameter("name");
		String maker=request.getParameter("maker");
		String sd=request.getParameter("sd");
		String ed=request.getParameter("ed");
		String qorg=request.getParameter("qorg");
		String qinfo=request.getParameter("qinfo");
		String role=request.getParameter("role");
		String user=request.getParameter("user");
		String menu=request.getParameter("menu");
		List comp=null;
		comp=qc.getComp(name, qinfo, maker, sd, ed, qorg, role, menu,user);
		JSONArray jsonList = JSONArray.fromObject(comp);
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

