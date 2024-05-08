package controller.database;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import controller.servlets.productAdmin;
import model.LoginModel;
import model.Loginresult;
import model.MakeupModel;
import model.PasswordEncryptionWithAes;
import model.UserMakeupModel;
import model.product;
import util.stringUtil;

public class GlamVaultDBController {
	private static final String urlfordatabase = "jdbc:mysql://localhost:3306/glamvault_cosmetics_shimmer";
    private static final String databaseuser = "root";
    private static final String password = "";
	

    public Connection getConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        return DriverManager.getConnection(urlfordatabase, databaseuser, password);
    }
   
    
    public boolean doesUsernameExist(String user_name) {
        try (Connection con = getConnection();
             PreparedStatement sp = con.prepareStatement("SELECT COUNT(*) FROM user WHERE username = ?")) {
            sp.setString(1, user_name);
            try (ResultSet ss = sp.executeQuery()) {
                if (ss.next()) {
                    int count = ss.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace(); 
        }
        return false;
    }
    
    public boolean doesEmailExists(String Email) {
        try (Connection con = getConnection();
             PreparedStatement sp = con.prepareStatement("SELECT COUNT(*) FROM user WHERE email = ?")) {
            sp.setString(1, Email);
            try (ResultSet ss = sp.executeQuery()) {
                if (ss.next()) {
                    int count = ss.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return false;
    }

    public boolean doesPhoneNumberExists(String Number) {
        try (Connection con = getConnection();
             PreparedStatement sp = con.prepareStatement("SELECT COUNT(*) FROM user WHERE phone_number = ?")) {
            sp.setString(1, Number);
            try (ResultSet ss = sp.executeQuery()) {
                if (ss.next()) {
                    int count = ss.getInt(1);
                    return count > 0;
                }
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
        }
        return false;
    }
    
    public int NewUserAdd(UserMakeupModel MakeupModel) {
        try (Connection con = getConnection();
             PreparedStatement sp = con.prepareStatement(stringUtil.User_Insert)) {
            sp.setString(1, MakeupModel.getUsername());
            sp.setString(2, MakeupModel.getFullname());
            sp.setString(3, MakeupModel.getEmail());
            sp.setString(4, MakeupModel.getPhone_num());
            sp.setDate(5, Date.valueOf(MakeupModel.getDob()));
            sp.setString(6, MakeupModel.getAddress());
            sp.setString(7, PasswordEncryptionWithAes.encrypt(MakeupModel.getUsername(), MakeupModel.getPassword()));
            sp.setString(8, MakeupModel.getGender());
            sp.setString(9,MakeupModel.getImageUserUrl());
            sp.setString(10,MakeupModel.getRole());

            int result = sp.executeUpdate();

            if (result > 0) {
                return 1; // Success
            } else {
                return 0; // No rows affected
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return -1; // Error
        }
    }
    public Loginresult getStudLoginInfo(LoginModel login_Model) {
        try (Connection con = getConnection()) {
            PreparedStatement ts = con.prepareStatement(stringUtil.GET_LOGIN_DETAILS);

            // Set the username in the first parameter of the prepared statement
            ts.setString(1, login_Model.getUsername()); // Assuming user_name is the correct method in LoginModel to get the username

            ResultSet ss = ts.executeQuery();
            if (ss.next()) {
                String DbUser = ss.getString("username"); // Assuming 'user_name' is the column name for the username
                String encryptedPw = ss.getString(stringUtil.Password);
                String decryptedPw = PasswordEncryptionWithAes.decrypt(encryptedPw, DbUser);
                
                if (DbUser.equalsIgnoreCase(login_Model.getUsername()) && decryptedPw != null && decryptedPw.equals(login_Model.getPassword())) {
                    String role = ss.getString("role"); // Assuming 'role' is the column name for the user's role
                    if (role != null) {
                        // User role found, return login result with role
                        return new Loginresult(1, role); // 1 indicates successful login
                    } else {
                        // Role not found, return login result without role
                        return new Loginresult(1, null); // 1 indicates successful login
                    }
                } else {
                    // Username or password mismatch, return login result without role
                    return new Loginresult(0, null); // 0 indicates username or password mismatch
                }
            } else {
                // Username not found in the database, return login result without role
                return new Loginresult(-1, null); // -1 indicates username not found
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return new Loginresult(-2, null); // -2 indicates error
        }
    }

    public int modifyPassword(String user_name, String latestPassword) {
        try (Connection con = getConnection();
             PreparedStatement sp = con.prepareStatement("UPDATE user SET password = ? WHERE username = ?")) {
            sp.setString(1, PasswordEncryptionWithAes.encrypt(user_name, latestPassword));
            sp.setString(2, user_name);

            int result = sp.executeUpdate();

            if (result > 0) {
                return 1; // Password updated successfully
            } else {
                return 0; // No rows affected (username not found)
            }
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return -1; // Error
        }
    }
    

   

    public int modifyPasswordValid(String user_name, String latestPassword) {
        try (Connection con = getConnection()) {
            // Check if the username exists in the database
            if (doesUsernameExist(user_name)) {
                // Username exists, update the password
                return modifyPassword(user_name, latestPassword);
            } else {
                // Username not found, return -1
                return -1;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return -3; // Error
        }
    }
    
    

    public int ADDProduct(product Product) {
    	try (Connection con = getConnection();
                PreparedStatement ss = con.prepareStatement("INSERT INTO makeup (Makeup_Name, Price,  product_image) VALUES (?, ?,  ?)")) {
    		ss.setString(1, Product.getProductName());
    		ss.setDouble(2, Product.getPrice());
    		ss.setString(3, Product.getUserImageUrl());

    		return ss.executeUpdate();
        } catch ( ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            e.getMessage();
            return -1;
        }
    }
    public int deleteMakeup(int makeupId) {
        try (Connection con = getConnection();
             PreparedStatement sp = con.prepareStatement("DELETE FROM makeup WHERE ID_Makeup = ?")) {
            sp.setInt(1, makeupId);
            
            return sp.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return -1; // Error
        }
    }
    public UserMakeupModel getProfileUser(String user_name) {
        try (Connection con = getConnection()) {
            PreparedStatement sp = con.prepareStatement("SELECT * FROM user WHERE username = ?");
            sp.setString(1, user_name);
            ResultSet ss = sp.executeQuery();

            if (ss.next()) {
            	UserMakeupModel userAccount = new UserMakeupModel();
            	userAccount.setUsername(ss.getString("username"));
            	userAccount.setFullname(ss.getString("full_Name"));
            	userAccount.setEmail(ss.getString("email"));  
            	userAccount.setAddress(ss.getString("address"));
//                userProfile.setImageUrlFromDB(rs.getString("user_image"));
                return userAccount;
            } else {
                // User not found in the database
                return null;
            }
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }
    }
    
    public int alterProfile(UserMakeupModel makeupuser) {
        try (Connection con = getConnection();
             PreparedStatement sp = con.prepareStatement("UPDATE user SET full_name=?, email=?, address=? WHERE username=?")) {         
            sp.setString(1, makeupuser.getFullname());
            sp.setString(2, makeupuser.getEmail());
            sp.setString(3, makeupuser.getAddress());
            sp.setString(4, makeupuser.getUsername());

            return sp.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return -1; // Error
        }
    }
    
    public int updateMakeup(MakeupModel makeup) {
        try (Connection con = getConnection();
             PreparedStatement sp = con.prepareStatement("UPDATE makeup SET Makeup_Name = ?, Price = ? WHERE ID_Makeup = ?")) {
            sp.setString(1, makeup.getMakeupName());
            sp.setDouble(2, makeup.getPrice());
            sp.setInt(3, makeup.getMakeupID());


            int result = sp.executeUpdate();

            if (result > 0) {
                System.out.println("Database updated successfully");
                return 1; // Success
            } else {
                System.out.println("No rows affected, database not updated");
                return 0; // No rows affected
            }
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return -1; // Error
        }
    }
    
    public ArrayList<MakeupModel> getAllMakeup() {
        try (Connection conn = getConnection();
             PreparedStatement stmt = conn.prepareStatement("SELECT * FROM makeup");
             ResultSet result = stmt.executeQuery()) {

            ArrayList<MakeupModel> makeupGlams = new ArrayList<>();
            while (result.next()) {
            	MakeupModel makeupGlam = new MakeupModel();
            	makeupGlam.setMakeupID(result.getInt("ID_Makeup"));
            	makeupGlam.setMakeupName(result.getString("Makeup_Name"));
            	makeupGlam.setPrice(result.getDouble("Price"));
            	makeupGlam.setProductImage(result.getString("product_image"));
            
            	makeupGlams.add(makeupGlam);
            }
            return makeupGlams;
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return null; // Error
        }
    }
}


