package bean;


public class CloudRole {

	private String state;//״̬
	private String cloudroleid;//����
	private String madeuser;//������
    private String roleid;//��ɫid
	private String rolename;//��ɫ��
    private String orgname;//������֯��
    private String update;//�޸�ʱ��
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
			this.state = "����";
		else
			this.state="����";
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

