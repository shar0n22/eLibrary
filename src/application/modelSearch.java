package application;

public class modelSearch {
	
	String sfuid, sfbid, sfname, sfauthor;
    public modelSearch(String sfuid, String sfbid, String sfname, String sfauthor) {
		super();
		this.sfuid = sfuid;
		this.sfbid = sfbid;
		this.sfname = sfname;
		this.sfauthor = sfauthor;
	}
	public String getSfuid() {
		return sfuid;
	}
	public void setSfuid(String sfuid) {
		this.sfuid = sfuid;
	}
	public String getSfbid() {
		return sfbid;
	}
	public void setSfbid(String sfbid) {
		this.sfbid = sfbid;
	}
	public String getSfname() {
		return sfname;
	}
	public void setSfname(String sfname) {
		this.sfname = sfname;
	}
	public String getSfauthor() {
		return sfauthor;
	}
	public void setSfauthor(String sfauthor) {
		this.sfauthor = sfauthor;
	}
    

	
}
