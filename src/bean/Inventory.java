package bean;
/**
 * 库存的实体类
 * @author Administrator
 *
 */
public class Inventory {
	private Integer id;
	private String name;
	private String barcode;
	private Integer num;
	private String typeone;
	private String typetwo;
	private String status;	//0表示物品被删除，1表示物品存在
	
	public Inventory() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Inventory(Integer id, String name, String barcode, Integer num, String typeone, String typetwo,
			String status) {
		super();
		this.id = id;
		this.name = name;
		this.barcode = barcode;
		this.num = num;
		this.typeone = typeone;
		this.typetwo = typetwo;
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
	public Integer getNum() {
		return num;
	}
	public void setNum(Integer num) {
		this.num = num;
	}
	public String getTypeone() {
		return typeone;
	}
	public void setTypeone(String typeone) {
		this.typeone = typeone;
	}
	public String getTypetwo() {
		return typetwo;
	}
	public void setTypetwo(String typetwo) {
		this.typetwo = typetwo;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	@Override
	public String toString() {
		return "Inventory [id=" + id + ", name=" + name + ", barcode=" + barcode + ", num=" + num + ", typeone="
				+ typeone + ", typetwo=" + typetwo + ", status=" + status + "]";
	}
	
	
	
}
