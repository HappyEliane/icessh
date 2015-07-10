package bean;

public class HomeUser {
	private String homeid;
	private String realname;

	private String email;
	private String phone;
	private String address;
	private String contact;
	private String contactid;
	private String contactphone;
	private String registerdate;
	private String admin;
	public void setAdmin(String admin)
	{
		this.admin=admin;
	}
	public String getAdmin()
	{
		return this.admin;
	}
	
	public void setHomeid(String userid)
	{
		this.homeid=userid;
	}
	public String getHomeid()
	{
		return this.homeid;
	}
	public void setRealName(String realname)
	{
		this.realname=realname;
	}
	public String getRealName()
	{
		return this.realname;
	}

	public void setEmail(String email)
	{
		this.email=email;
	}
	public String getEmail()
	{
		return this.email;
	}
	public void setPhone(String phone)
	{
		this.phone=phone;
	}
	public String getPhone()
	{
		return this.phone;
	}
	public void setAddress(String address)
	{
		this.address=address;
	}
	public String getAddress()
	{
		return this.address;
	}

	public void setContact(String contact)
	{
		this.contact=contact;
	}
	public String getContact()
	{
		return this.contact;
	}
	public void setContactid(String contactid)
	{
		this.contactid=contactid;
	}
	public String getContactid()
	{
		return this.contactid;
	}
	public void setContactphone(String contactphone)
	{
		this.contactphone=contactphone;
	}
	public String getContactphone()
	{
		return this.contactphone;
	}
	public void setRegisterdate(String registerdate)
	{
		this.registerdate=registerdate;
	}
	public String getRegisterdate()
	{
		return this.registerdate;
	}

}
