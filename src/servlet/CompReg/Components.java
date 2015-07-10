package servlet.CompReg;





import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import CompReg.ModifyComponent;

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
		System.out.println("更改构件数据表。。。"+kind);
		
		ModifyComponent mc=new ModifyComponent();
		String result="wrong";
		if(kind.equals("add"))
		{
			String name=request.getParameter("name");
			String url=request.getParameter("url");
			String org=request.getParameter("org");
			String info=request.getParameter("info");
			String maker=request.getParameter("maker");
			String username=request.getParameter("username");
			String theme=request.getParameter("theme");
			try {
				result=mc.AddComp(name, url, info, maker, username, org,theme);
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
			String name=request.getParameter("name");
			String maker=request.getParameter("maker");
			String sd=request.getParameter("sd");
			String ed=request.getParameter("ed");
			String qorg=request.getParameter("qorg");
			String qinfo=request.getParameter("qinfo");
			List comp=null;
			comp=mc.queryComp(name, qinfo, maker, sd, ed, qorg);
			JSONArray jsonList = JSONArray.fromObject(comp);
			  result=jsonList.toString();
			  
			
		}
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
		 if(kind.equals("del"))
		 {
			 String compid=request.getParameter("comp_id");
			 result=mc.delComp(compid);
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
		
		if(kind.equals("wsadd"))
		{
			String cname=request.getParameter("cname");
			String name=request.getParameter("name");
			String url=request.getParameter("url");
			
			String info=request.getParameter("info");
			String maker=request.getParameter("maker");
			String username=request.getParameter("username");
			String sort=request.getParameter("sort");
			String theme=request.getParameter("theme");
			try {
				result=mc.AddWSComp(cname,name, url, info, maker, username, sort, theme);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		 if(kind.equals("wsquery"))
			{
				 System.out.println("开始查询");
				String name=request.getParameter("name");
				String maker=request.getParameter("maker");
				String sd=request.getParameter("sd");
				String ed=request.getParameter("ed");
				String qsort=request.getParameter("qsort");
				String qinfo=request.getParameter("qinfo");
				List comp=null;
				comp=mc.queryWSComp(name, qinfo, maker, sd, ed, qsort);
				JSONArray jsonList = JSONArray.fromObject(comp);
				  result=jsonList.toString();
				  
				
			}
	}
	

}
