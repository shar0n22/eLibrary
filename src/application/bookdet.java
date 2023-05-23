package application;

public class bookdet {
    private int bid;
    private String bname;
    private String bauthor;
    private int byear;
    public bookdet(int bid, String bname, String bauthor, int byear) {
		super();
		this.bid = bid;
		this.bname = bname;
		this.bauthor = bauthor;
		this.byear = byear;
	}
    public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
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
	public int getByear() {
		return byear;
	}
	public void setByear(int byear) {
		this.byear = byear;
	}
	
	
}
