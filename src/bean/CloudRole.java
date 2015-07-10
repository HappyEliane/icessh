package bean;


public class CloudRole {

	private String state;//状态
	private String cloudroleid;//主键
	private String madeuser;//创建者
    private String roleid;//角色id
	private String rolename;//角色名
    private String orgname;//所属组织名
    private String update;//修改时间
    private String id;
    private String nid;
    private String userrole;
    public String getUserrole()
    {
    	return this.userrole;
    }
    public void setUserrole(String userrole)
    {
    	this.userrole=userrole;
    }
	
	//private String cloudname;

    public String getNid()
    {
    	return nid;
    }
    public void setNid(String nid)
    {
    	this.nid=nid;
    }
	public String getState() {
		return state;
	}

	public void setState(String state) {
		if(state.equals("1"))
			this.state = "启用";
		else
			this.state="禁用";
	}

	public String getCloudroleid() {
		return cloudroleid;
	}

	public void setCloudroleid(String cloudroleid) {
		this.cloudroleid = cloudroleid;
	}

	public String getMadeuser()
	{
		return madeuser;
	}
	public void setMadeuser(String madeuser)
	{
		this.madeuser=madeuser;
	}
	public String getRoleid() {
		return roleid;
	}

	public void setRoleid(String roleid) {
		this.roleid = roleid;
	}

	public String getRolename() {
		return rolename;
	}

	public void setRolename(String rolename) {
		this.rolename = rolename;
	}

	public String getOrgname() {
		return orgname;
	}

	public void setOrgname(String orgname) {
		this.orgname = orgname;
	}

	public String getUpdate() {
		return update;
	}

	public void setUpdate(String update) {
		this.update = update;
	}
	public String getId(){
		return this.id;
	}
	public void setId(String id){
		this.id=id;
	}
	
	
}

