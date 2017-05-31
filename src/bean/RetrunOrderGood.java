package bean;

public class RetrunOrderGood {
	private int ordergoods_id;
	private int order_id;
	private Good good;
	private int og_number;
	
	public int getOrdergoods_id() {
		return ordergoods_id;
	}
	public void setOrdergoods_id(int ordergoods_id) {
		this.ordergoods_id = ordergoods_id;
	}
	public int getOrder_id() {
		return order_id;
	}
	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}
	
	public Good getGood() {
		return good;
	}
	public void setGood(Good good) {
		this.good = good;
	}
	public int getOg_number() {
		return og_number;
	}
	public void setOg_number(int og_number) {
		this.og_number = og_number;
	}
	
}
