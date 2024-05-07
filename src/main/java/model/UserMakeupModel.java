package model;

import java.io.File;
import java.io.Serializable;
import java.time.LocalDate;

import javax.servlet.http.Part;

import util.stringUtil;

public class UserMakeupModel implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String fullname;
	private String email;
	private String phone_num;
	private LocalDate dob;
	private String address;
	private String password;
	private String gender;
	private String imageUserUrl;
	private String role;
	
	public UserMakeupModel () {}



	public UserMakeupModel(String username, String full_name, String email, String phone_number, LocalDate dob,
			String address, String password, String gender, Part user_image, String role) {
		super();
		this.username = username;
		this.fullname = full_name;
		this.email = email;
		this.phone_num = phone_number;
		this.dob = dob;
		this.address = address;
		this.password = password;
		this.gender = gender;
		this.imageUserUrl = getImageUrl(user_image);
		this.role= role;
	}



	

	public String getUsername() {
		return username;
	}



	public void setUsername(String username) {
		this.username = username;
	}



	

	public String getFullname() {
		return fullname;
	}



	public void setFullname(String fullname) {
		this.fullname = fullname;
	}



	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	
	public String getPhone_num() {
		return phone_num;
	}



	public void setPhone_num(String phone_num) {
		this.phone_num = phone_num;
	}



	public LocalDate getDob() {
		return dob;
	}

	public void setDob(LocalDate dob) {
		this.dob = dob;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getImageUserUrl() {
		return imageUserUrl;
	}

	public void setImageUserUrl(Part part) {
        this.imageUserUrl = getImageUrl(part);    
    }
    
    public void setImageUrlFromDB(String imageUrl) {
        this.imageUserUrl = imageUrl;
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



	public String getRole() {
		return role;
	}



	public void setRole(String role) {
		this.role = role;
	}
}
