package application;

public class modelView {
    String bid, bname, bauthor, byear;
    public modelView(String bid, String bname, String bauthor, String byear) {
		super();
		this.bid = bid;
		this.bname = bname;
		this.bauthor = bauthor;
		this.byear = byear;
	}
    public String getBid() {
		return bid;
	}
	public void setBid(String bid) {
		this.bid = bid;
	}
	public String getBname() {
		return bname;
	}
	public void setBname(String bname) {
		this.bname = bname;
	}
	public String getBauthor() {
		return bauthor;
	}
	public void setBauthor(String bauthor) {
		this.bauthor = bauthor;
	}
	public String getByear() {
		return byear;
	}
	public void setByear(String byear) {
		this.byear = byear;
	}
}
