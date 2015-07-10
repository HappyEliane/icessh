package homeService;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import bean.component;
import db.DB;
import tool.CreateKey;

public class ModifyComponent {
	public List queryComp()
	{
		System.out.println("查询构件");
		
		
		String sql8="select * from ices_component";
		DB db=new DB();
		ResultSet rs=db.query(sql8);
		List l=new ArrayList();
		int i=1;
		try {
			while(rs!=null && rs.next())
			{
				component cp=new component();
				cp.setComId(rs.getString("comp_id"));
				cp.setComName(rs.getString("comp_name"));
				cp.setComURL(rs.getString("comp_url"));
				cp.setComDev(rs.getString("comp_maker"));
				cp.setCominfo(rs.getString("comp_info"));
			//	cp.setComOrg(rs.getString("comp_org"));
				cp.setComMaker(rs.getString("makerid"));
				
				cp.setComDate(rs.getString("makedate"));
				
				cp.setHome(rs.getString("home"));
				cp.setProvider(rs.getString("provider"));
				cp.setBusiness(rs.getString("business"));
				cp.setCenter(rs.getString("center"));
				String rorg=(rs.getString("home").equals("1")?"家庭用户,":"")+(rs.getString("provider").equals("1")?"供应商用户,":"")+(rs.getString("business").equals("1")?"业务中心,":"")+(rs.getString("center").equals("1")?"服务中心":"");
				System.out.println(rorg);
				cp.setComOrg(rorg);
				cp.setNum(""+i);
				i++;
				l.add(cp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
		
	}
	public List getThemeService(String themeid)
	{
		System.out.println("查询构件");
		String sql8="select * from ices_component where comp_theme_id ='"+themeid+"'";
		DB db=new DB();
		ResultSet rs=db.query(sql8);
		List l=new ArrayList();
		int i=1;
		try {
			while(rs!=null && rs.next())
			{
				component cp=new component();
				cp.setComId(rs.getString("comp_id"));
				cp.setComName(rs.getString("comp_name"));
				cp.setComURL(rs.getString("comp_url"));
				cp.setComDev(rs.getString("comp_maker"));
				cp.setCominfo(rs.getString("comp_info"));
			//	cp.setComOrg(rs.getString("comp_org"));
				cp.setComMaker(rs.getString("makerid"));
				
				cp.setComDate(rs.getString("makedate"));
				
				cp.setHome(rs.getString("home"));
				cp.setProvider(rs.getString("provider"));
				cp.setBusiness(rs.getString("business"));
				cp.setCenter(rs.getString("center"));
				String rorg=(rs.getString("home").equals("1")?"家庭用户,":"")+(rs.getString("provider").equals("1")?"供应商用户,":"")+(rs.getString("business").equals("1")?"业务中心,":"")+(rs.getString("center").equals("1")?"服务中心":"");
				System.out.println(rorg);
				cp.setComOrg(rorg);
				cp.setNum(""+i);
				i++;
				l.add(cp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
		
	}
	public List queryWSComp()
	{
		System.out.println("查询构件");
		
		String sql8="select * from ices_ws_comp";
		DB db=new DB();
		ResultSet rs=db.query(sql8);
		List l=new ArrayList();
		int i=1;
		try {
			while(rs!=null && rs.next())
			{
				component cp=new component();
				cp.setWSComId(rs.getString("comp_id"));
				cp.setWSComName(rs.getString("comp_cname"));
				cp.setWSComURL(rs.getString("comp_url"));
				cp.setWSComDev(rs.getString("comp_maker"));
				cp.setWSCominfo(rs.getString("comp_info"));
			//	cp.setComOrg(rs.getString("comp_org"));
				cp.setWSComMaker(rs.getString("makerid"));
				
				cp.setWSComDate(rs.getString("makedate"));
				cp.setWSComSort(rs.getString("comp_sort"));
				cp.setWSComField(rs.getString("comp_theme_id"));
				
				
				//String rsort=(rs.getString("comp_sort").equals("内部Web service")?"家庭用户,":"")+(rs.getString("provider").equals("1")?"供应商用户,":"")+(rs.getString("business").equals("1")?"业务中心,":"")+(rs.getString("center").equals("1")?"服务中心":"");
				//System.out.println(rsort);
				//cp.setComOrg(rsort);
				cp.setWSNum(""+i);
				i++;
				l.add(cp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return l;
		
	}

}

