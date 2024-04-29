package model;

public class MakeupModel {
	private int makeupID;
	private String makeupName;
	private double price;
	private String productImage;

	public MakeupModel(int makeupID, String makeupName, double price, String productImage) {
		this.makeupID = makeupID;
		this.makeupName = makeupName;
		this.price = price;
		this.productImage = productImage;
	}

	public MakeupModel() {
	}

	public int getMakeupID() {
		return makeupID;
	}

	public void setMakeupID(int makeupID) {
		this.makeupID = makeupID;
	}

	public String getMakeupName() {
		return makeupName;
	}

	public void setMakeupName(String makeupName) {
		this.makeupName = makeupName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getProductImage() {
		return productImage;
	}

	public void setProductImage(String productImage) {
		this.productImage = productImage;
	}

}
