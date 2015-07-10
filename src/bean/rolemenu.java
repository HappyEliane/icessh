package bean;

public class rolemenu {
	private String num;
	private String id;
	private String roleid;
	private String menuid;
	private String makedate;
	private String state;
	private String makeuserid;
	private String makeroleid;
	private String rolename;
	private String menuname;
	
	public void setMakeroleid(String makeroleid)
	{
		this.makeroleid=makeroleid;
	}
	public String getMakeroleid()
	{
		return this.makeroleid;
	}
	public void setRolename(String rolename)
	{
		this.rolename=rolename;
	}
	public String getRolename()
	{
		return this.rolename;
	}
	public void setMenuname(String menuname)
	{
		this.menuname=menuname;
	}
	public String getMenuname()
	{
		return this.menuname;
	}
	public void setNum(String num)
	{
		this.num=num;
	}
	public String getNum()
	{
		return this.num;
	}
	public void setId(String id)
	{
		this.id=id;
	}
	public String getId()
	{
		return this.id;
	}
	public void setRoleid(String roleid)
	{
		this.roleid=roleid;
	}
	public String getRoleid ()
	{
		return this.roleid;
	}
	public void setMakeuserid(String userid)
	{
		this.makeuserid=userid;
	}
	public String getMakeuserid ()
	{
		return this.makeuserid;
	}
	public void setMenuid(String menuid)
	{
		this.menuid=menuid;
	}
	public String getMenuid ()
	{
		return this.menuid;
	}
	public void setMakedate(String makedate)
	{
		this.makedate=makedate;
	}
	public String getMakedate ()
	{
		return this.makedate;
	}
	public void setState(String state)
	{
		if(state.equals("1"))
			this.state="ÆôÓÃ";
		else
			this.state="½ûÓÃ";
	}
	public String getState()
	{
		return this.state;
	}

}
