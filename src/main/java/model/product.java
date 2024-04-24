package model;

import java.io.File;

import javax.servlet.http.Part;

import util.stringUtil;

public class product {

	private String productName;
    
    private double price;
    private String userImageUrl;
    
	public product(String productName, double price, Part user_image) {
		super();
		this.productName = productName;
		this.price = price;
		this.userImageUrl = getImageUrl(user_image);
	}

	public String getProductName() {
		return productName;
	}

	public void setProductName(String productName) {
		this.productName = productName;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getUserImageUrl() {
		return userImageUrl;
	}

	public void setUserImageUrl(String userImageUrl) {
		this.userImageUrl = userImageUrl;
	}
	public void setImageUrlFromDB(String imageUrl) {
        this.userImageUrl = imageUrl;
    }
    
    private String getImageUrl(Part part) {
        String savePath = stringUtil.IMAGE_DIR_SAVE_PATH;
        File fileSaveDir = new File(savePath);
        String imageUserUrl = null;
        if (!fileSaveDir.exists()) {
            fileSaveDir.mkdir();
        }
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String s : items) {
            if (s.trim().startsWith("filename")) {
            	imageUserUrl = s.substring(s.indexOf("=") + 2, s.length() - 1);
            }
        }
        if ( imageUserUrl== null || imageUserUrl.isEmpty()) {
        	imageUserUrl = "download.jpg";
        }
        return imageUserUrl;
    }

	
	
    
}
