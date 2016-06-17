package en2decoder.serialize.server;

import java.io.Serializable;

public class SubscribeRes implements Serializable {

	/**  */
	private static final long serialVersionUID = 1L;
	private int uuid;
	private int status;
	private String name;
	private String product;
	private String address;

	public int getUuid() {
		return uuid;
	}

	public void setUuid(int uuid) {
		this.uuid = uuid;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return new String("   uuid:" + this.uuid + "   name:" + this.name
				+ "   product:" + product + "  address:" + address);
	}

}
