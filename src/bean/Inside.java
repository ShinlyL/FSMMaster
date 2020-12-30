package bean;
/**
 * 入库的实体类
 * @author Administrator
 *
 */
public class Inside {
	private Integer id;
	private String name;
	private String barcode;
	private double purchaseprice;
	private int num;
	private double sums;
	private String manager;
	private String releaseDate;
	private int status;	//0表示物品被需要入库，1表示物品已入库
	public Inside() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Inside(Integer id, String name, String barcode, double purchaseprice, int num, double sums, String manager,
			String releaseDate, int status) {
		super();
		this.id = id;
		this.name = name;
		this.barcode = barcode;
		this.purchaseprice = purchaseprice;
		this.num = num;
		this.sums = sums;
		this.manager = manager;
		this.releaseDate = releaseDate;
		this.status = status;
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
	public double getPurchaseprice() {
		return purchaseprice;
	}
	public void setPurchaseprice(double purchaseprice) {
		this.purchaseprice = purchaseprice;
	}
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public double getSums() {
		return sums;
	}
	public void setSums(double sums) {
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
	public int getStatus() {
		return status;
	}
	public void setStatus(int status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Inside [id=" + id + ", name=" + name + ", barcode=" + barcode + ", purchaseprice=" + purchaseprice
				+ ", num=" + num + ", sums=" + sums + ", manager=" + manager + ", releaseDate=" + releaseDate
				+ ", status=" + status + "]";
	}
}
