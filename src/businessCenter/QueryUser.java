package businessCenter;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import bean.busUser;
import db.DB;

public class QueryUser {
	/*
	 * �鿴���Ƶ�ǰҵ�����Ա�����ҵ����û�
	 */
	public List getUser(String Login_user,String Login_role,String username,String userrole,String sd,String ed)
	{
		String sql="select * from ices_user_role where user_name='"+Login_user+"' and role_name='"+Login_role+"'";
		DB db=new DB();
		String make="";
		ResultSet rs=db.query(sql);
		try {
			while(rs.next())
			{
				make=rs.getString("user_role_id");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		List business=new ArrayList();//��ǰ�û����������ҵ��ID
		sql="select * from ices_business where admin='"+make+"'";
		rs=db.query(sql);
		String temp="";
		try {
			while(rs.next())
			{
				temp=rs.getString("id");
				business.add(temp);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String allbus=" and a.busid in (";
		for(int i=0;i<business.size();i++)
		{
			allbus+=" '";
			allbus+=business.get(i);
			allbus+="'";
			if(i!=business.size()-1)
			{
				allbus+=", ";
			}
		}
		allbus+=")";
		sql="select * from ices_orderbusiness as a , ices_user_role as b where a.orderid=b.user_role_id "+allbus;
		rs=db.query(sql);
		List l=new ArrayList();
		List l1=new ArrayList();
		String t="";
		try {
			System.out.println(rs.getRow());
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try {
			int num=1;
			while(rs.next())
			{
				System.out.println("�鵽�û�"+rs.getString("a.orderid"));
				t=rs.getString("a.cloud");
				String p="";
				System.out.println(t);
				if(t!=null && !t.equals("") )//��ǰ�û�Ϊ��ͥ�û����߹�Ӧ���û�
				{
					System.out.println("�鵽��ͥ�û����߹�Ӧ���û�");
					busUser bu=new busUser();
					bu.setId(rs.getString("a.id"));
					bu.setBusname(rs.getString("a.busname"));
					bu.setUsername(rs.getString("b.user_name"));
					bu.setRolename(rs.getString("b.role_name"));
					bu.setOrderdate(rs.getString("a.makedate"));
					
					p=t.substring(0, 3);		
					if(p.equals("HRI"))//���Ƶ�ǰҵ���Ϊ��ͥ�û�
					{
						System.out.println("��ͥ�û�");
						bu.setCloud("��ͥ�û�");
					}
					else if(p.equals("PRI"))
					{
						System.out.println("��Ӧ���û�");
						bu.setCloud("��Ӧ���û�");
					}
					bu.setState("����");
					bu.setNum(""+num);
					num++;
					l1.add(bu);
				}
			}
			System.out.println("��ѯ����");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		db.close();
		l.add(l1);
		
		return l;
	}
	public static void main(String []args)
	{
		QueryUser qu=new QueryUser();
		qu.getUser("bus", "Badmin", "", "", "", "");
	}

}
