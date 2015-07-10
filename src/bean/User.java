package bean;


import java.util.Date;

public class User {

	private String id;
	
	private String userid;
	
	private String username;
	
	private String password;
	
	private String makepersonid;
	private String makeroleid;
	
	private String  makedate;
	
	private String userstate;
	
	private String oforgid;
	
	private String regid;
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
	public String getMakeroleid()
	{
		return this.makeroleid;
	}
	public void setMakeroleid(String makeroleid)
	{
		this.makeroleid=makeroleid;
	}
	public String getNid()
	{
		return nid;
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

	


	

	public String getMakedate() {
		return makedate;
	}

	public void setMakedate(String makedate) {
		this.makedate = makedate;
	}

	public String getMakepersonid() {
		return makepersonid;
	}

	public void setMakepersonid(String makepersonid) {
		this.makepersonid = makepersonid;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
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

	public String getUserstate() {
		return userstate;
	}

	public void setUserstate(String userstate) {
		if(userstate.equals("1"))
			this.userstate="启用";
		else
			this.userstate = "禁用";
	}

	public String getOforgid() {
		return oforgid;
	}

	public void setOforgid(String oforgid) {
		if(oforgid.equals("HC00001"))
			this.oforgid = "家庭用户";
		else if(oforgid.equals("SC00001"))
			this.oforgid = "服务中心";
		else if(oforgid.equals("BC00001"))
			this.oforgid = "业务中心";
		else if(oforgid.equals("PC00001"))
			this.oforgid = "供应商";
	}

	public String getRegid() {
		return regid;
	}

	public void setRegid(String regid) {
		this.regid = regid;
	}
	
	
	
	
	
	
}

