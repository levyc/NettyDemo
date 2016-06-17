package en2decoder.serialize.server;

import java.io.Serializable;

public class SubscribeReq implements Serializable {

	/**  */
	private static final long serialVersionUID = 1L;
	private String name;
	private String address;
	private String productName;
	private int id;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	@Override
	public String toString() {
		return new String("   id:" + this.id + "    name:" + this.name
				+ "  product:" + productName + "   address:" + address);
	}
}
