package bean;
/**
 * 申请的 实体类
 * @author Administrator
 *
 */
public class Apply {
	private Integer id;
	private String name;
	private String barcode;
	private Double purchaseprice;
	private int num;
	private Double sums;
	private String manager;
	private String releaseDate;
	private String flag;
	public Apply() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Apply(Integer id, String name, String barcode, Double purchaseprice, int num, Double sums, String manager,
			String releaseDate, String flag) {
		super();
		this.id = id;
		this.name = name;
		this.barcode = barcode;
		this.purchaseprice = purchaseprice;
		this.num = num;
		this.sums = sums;
		this.manager = manager;
		this.releaseDate = releaseDate;
		this.flag = flag;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBarcode() {
		return barcode;
	}
	public void setBarcode(String barcode) {
		this.barcode = barcode;
	}
	public Double getPurchaseprice() {
		return purchaseprice;
	}
	public void setPurchaseprice(Double purchaseprice) {
		this.purchaseprice = purchaseprice;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public Double getSums() {
		return sums;
	}
	public void setSums(Double sums) {
		this.sums = sums;
	}
	public String getManager() {
		return manager;
	}
	public void setManager(String manager) {
		this.manager = manager;
	}
	public String getReleaseDate() {
		return releaseDate;
	}
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}
	public String getFlag() {
		return flag;
	}
	public void setFlag(String flag) {
		this.flag = flag;
	}
	@Override
	public String toString() {
		return "Apply [id=" + id + ", name=" + name + ", barcode=" + barcode + ", purchaseprice=" + purchaseprice
				+ ", num=" + num + ", sums=" + sums + ", manager=" + manager + ", releaseDate=" + releaseDate
				+ ", flag=" + flag + "]";
	}
	
	 
	
	
}
