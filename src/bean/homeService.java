package bean;

public class homeService{
	private String num;
	private String ID;
	private String homeID;
	private String servicethemeID;
	private String serviceID;
	private String homeName;
	private String servicethemeName;
	private String serviceName;
	private String permission;
	
	public void setNum(String num)
	{
		this.num=num;
	}
	public String getNum()
	{
		return this.num;
	}
	
	public String getID() {
		return ID;
	}
	public void setID(String ID) {
		this.ID = ID;
	}
	public String getHomeID() {
		return homeID;
	}
	public void setHomeID(String homeID) {
		this.homeID = homeID;
	}
	public String getServicethemeID() {
		return servicethemeID;
	}
	public void setServicethemeID(String servicethemeID) {
		this.servicethemeID = servicethemeID;
	}
	public String getServiceID() {
		return serviceID;
	}
	public void setServiceID(String serviceID) {
		this.serviceID = serviceID;
	}
	public String getHomeName() {
		return homeName;
	}
	public void setHomeName(String homeName) {
		this.homeName = homeName;
	}
	public String getServicethemeName() {
		return servicethemeName;
	}
	public void setServicethemeName(String servicethemeName) {
		this.servicethemeName = servicethemeName;
	}
	public String getServiceName() {
		return serviceName;
	}
	public void setServiceName(String serviceName) {
		this.serviceName= serviceName;
	}
	public String getPermission() {
		return permission;
	}
	public void setPermission(String permission) {
		this.permission = permission;
	}
}