package servlet.businessCenter;



import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import businessCenter.FaultMenu;
import businessCenter.QueryUser;
import businessCenter.orderBus;
import businessCenter.spattern;



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
		System.out.println("更改业务数据表"+kind);
		
		FaultMenu cm=new FaultMenu();
		String result="wrong";
		if(kind.equals("add"))
		{
			String login_user=request.getParameter("user");
			String login_role=request.getParameter("role");
			String maker=request.getParameter("maker");
			String des=request.getParameter("des");
			String name=request.getParameter("name");
			String smenu=request.getParameter("smenu");
			String bmenu=request.getParameter("bmenu");
			String hmenu=request.getParameter("hmenu");
			String pmenu=request.getParameter("pmenu");
			String admin=request.getParameter("admin");
			try {
				result=cm.addMenu(login_user, login_role, name, des, maker, smenu, bmenu, hmenu, pmenu,admin);
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
			String name=request.getParameter("name");
			String sd=request.getParameter("sd");
			String ed=request.getParameter("ed");
			String des=request.getParameter("des");
			String maker=request.getParameter("maker");
			List l=cm.getBusiness(name, sd, ed, maker, des);
			JSONArray jsonList = JSONArray.fromObject(l);
			  result=jsonList.toString();
		}
		if(kind.equals("queryMenu"))
		{
			String sm=request.getParameter("sm");
			String bm=request.getParameter("bm");
			String hm=request.getParameter("hm");
			String pm=request.getParameter("pm");
			result=cm.getBusMenu(sm, bm, hm, pm);
		}
		if(kind.equals("change"))
		{
			String id=request.getParameter("id");
			String name=request.getParameter("name");
			String des=request.getParameter("des");
			String smenuid=request.getParameter("smenuid");
			String smenu=request.getParameter("smenu");
			String bmenuid=request.getParameter("bmenuid");
			String bmenu=request.getParameter("bmenu");
			String hmenuid=request.getParameter("hmenuid");
			String hmenu=request.getParameter("hmenu");
			String pmenuid=request.getParameter("pmenuid");
			String pmenu=request.getParameter("pmenu");
			String make=request.getParameter("make");
			
			try {
				result=cm.changeBus(id, name, des, make, smenuid, smenu, bmenuid, bmenu, hmenuid, hmenu, pmenuid, pmenu);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(kind.equals("del"))
		{
			String id=request.getParameter("id");
			result=cm.delBus(id);
		}
		if(kind.equals("orderBus"))
		{
			orderBus ob=new orderBus();
			String loginuser=request.getParameter("Login_user");
			String loginrole=request.getParameter("Login_role");
			String name=request.getParameter("busname");
			String sd=request.getParameter("sd");
			String ed=request.getParameter("ed");
			String businfo=request.getParameter("des");
			List l=ob.getBus(loginuser, loginrole, name, sd, ed, businfo);
			JSONArray jsonList = JSONArray.fromObject(l);
			  result=jsonList.toString();
		}
		if(kind.equals("checkBus"))
		{
			String sm=request.getParameter("sm");
			result=cm.getMenu(sm);
		}
		if(kind.equals("order"))
		{
			String loginuser=request.getParameter("Login_user");
			String loginrole=request.getParameter("Login_role");
			String busid=request.getParameter("busid");
			String busname=request.getParameter("busname");
			String menuid=request.getParameter("menuid");
			try {
				result=cm.orderBus(loginuser, loginrole, busname, busid, menuid);
			} catch (NumberFormatException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		if(kind.equals("haveBus"))
		{
			orderBus ob=new orderBus();
			String user=request.getParameter("user");
			String role=request.getParameter("role");
			List l=ob.getHaveBus(user, role);
			JSONArray jsonList = JSONArray.fromObject(l);
			  result=jsonList.toString();
		}
		if(kind.equals("quitBus"))//退订业务
		{
			orderBus ob=new orderBus();
			
			String busid=request.getParameter("orderbusid");
			String user=request.getParameter("loginuser");
			String role=request.getParameter("loginrole");
			result=ob.quitBus(busid,user,role);
		}
		if(kind.equals("queryUser"))
		{
			QueryUser qb=new QueryUser();
			String user=request.getParameter("Login_user");
			String role=request.getParameter("Login_role");
			String username=request.getParameter("username");
			String userrole=request.getParameter("userrole");
			String sd=request.getParameter("sd");
			String ed=request.getParameter("ed");
			List l=qb.getUser(user, role, username, userrole, sd, ed);
			JSONArray jsonList = JSONArray.fromObject(l);
			  result=jsonList.toString();
			
		}
		if(kind.equals("querybusiness"))
		{
			orderBus ob=new orderBus();
			List l=ob.getAllBus();
			JSONArray jsonList = JSONArray.fromObject(l);
			  result=jsonList.toString();
		}
		if(kind.equals("ordersp"))
		{
			String spid=request.getParameter("spid");
			String spname=request.getParameter("spname");
			String spdes=request.getParameter("spdes");
			String busid=request.getParameter("busid");
			String home=request.getParameter("home");
			String provider=request.getParameter("provider");
			
			
			spattern sp=new spattern();
			sp.orderSP(spid, spname, spdes, busid, home, provider);
		}
		if(kind.equals("homehaveordersp"))
		{
			spattern sp=new spattern();
			List l=sp.getHomeOrderSP();
			JSONArray jsonList = JSONArray.fromObject(l);
			  result=jsonList.toString();
		}
		if(kind.equals("haveordersp"))
		{
			spattern sp=new spattern();
			List l=sp.getOrderSP();
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

