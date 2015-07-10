package bean;

public class UserRole {
	private String id;
	
	private String userroleid;
	
	private String userid;
	
	private String username;
	
	private String cloudid;
	
	private String roleid;
	
	private String rolename;
	private String update;
	private String roletype;
	private String nid;
	
	public String getNid()
	{
		return this.nid;
	}
	public void setNid(String nid)
	{
		this.nid=nid;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserroleid() {
		return userroleid;
	}

	public void setUserroleid(String userroleid) {
		this.userroleid = userroleid;
	}

	public String getUserid() {
		return userid;
	}

	public void setUserid(String userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getCloudid() {
		return cloudid;
	}

	public void setCloudid(String cloudid) {
		if(cloudid.equals("HC00001"))
			this.cloudid = "家庭用户";
		else if(cloudid.equals("SC00001"))
			this.cloudid = "服务中心";
		else if(cloudid.equals("BC00001"))
			this.cloudid = "业务中心";
		else if(cloudid.equals("PC00001"))
			this.cloudid = "供应商";
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
	public void setUpdate(String update)
	{
		this.update=update;
	}
	public String getUpdate()
	{
		return update;
	}
	public void setRoletype(String roletype)
	{
		if(roletype.equals("admin"))
			this.roletype="管理员";
		else
			this.roletype="普通用户";
	}
	public String getRoletype()
	{
		return roletype;
	}
	
	

}
