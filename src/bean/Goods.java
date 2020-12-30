package bean;
/**
 * 物品 的实体类
 * @author Administrator
 *
 */
public class Goods {
	private Integer id;
	private String name;
	private String barcode;
	private String purchaseprice;
	private String typeone;
	private String info;
	private String flag;
	private String typetwo;
	
	public Goods() {
		super();
	}

	public Goods(Integer id, String name, String barcode, String purchaseprice, String typeone, String info, String flag,
			String typetwo) {
		super();
		this.id = id;
		this.name = name;
		this.barcode = barcode;
		this.purchaseprice = purchaseprice;
		this.typeone = typeone;
		this.info = info;
		this.flag = flag;
		this.typetwo = typetwo;
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

	public String getPurchaseprice() {
		return purchaseprice;
	}

	public void setPurchaseprice(String purchaseprice) {
		this.purchaseprice = purchaseprice;
	}

	public String getTypeone() {
		return typeone;
	}

	public void setTypeone(String typeone) {
		this.typeone = typeone;
	}

	public String getInfo() {
		return info;
	}

	public void setInfo(String info) {
		this.info = info;
	}

	public String getFlag() {
		return flag;
	}

	public void setFlag(String flag) {
		this.flag = flag;
	}

	public String getTypetwo() {
		return typetwo;
	}

	public void setTypetwo(String typetwo) {
		this.typetwo = typetwo;
	}

	@Override
	public String toString() {
		return "Goods [id=" + id + ", name=" + name + ", barcode=" + barcode + ", purchaseprice=" + purchaseprice
				+ ", typeone=" + typeone + ", info=" + info + ", flag=" + flag + ", typetwo=" + typetwo + "]";
	}
	
	
	
	
}
