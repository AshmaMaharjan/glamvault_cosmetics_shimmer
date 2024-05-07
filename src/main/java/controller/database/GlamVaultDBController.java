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
    public ArrayList<UserMakeupModel> getAllStudent() {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(stringUtil.GET_LOGIN_DETAILS)) {
            ResultSet ss = st.executeQuery();
            ArrayList<UserMakeupModel> new_user = new ArrayList<>();

            while (ss.next()) {
                UserMakeupModel new_users = new UserMakeupModel();
                new_users.setUsername(ss.getString("UserName"));
                new_users.setFullname(ss.getString("firstName"));
                new_users.setEmail(ss.getString("Email"));
                new_users.setPhone_num(ss.getString("PhoneNumber"));
                new_users.setDob(ss.getDate("DOB").toLocalDate());
                new_users.setAddress(ss.getString("Address"));
                new_users.setGender(ss.getString("Gender"));
                new_users.setImageUrlFromDB(ss.getString("user_image"));
              
                
                new_user.addAll(new_user);
            }
            return new_user;

        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return null;
        }

    }
    public boolean isUsernameExists(String username) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM user WHERE username = ?")) {
            st.setString(1, username);
            try (ResultSet ss = st.executeQuery()) {
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
    
    public boolean isEmailExists(String email) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM user WHERE email = ?")) {
            st.setString(1, email);
            try (ResultSet ss = st.executeQuery()) {
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

    public boolean isPhoneNumberExists(String phoneNumber) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM user WHERE contact_number = ?")) {
            st.setString(1, phoneNumber);
            try (ResultSet rs = st.executeQuery()) {
                if (rs.next()) {
                    int count = rs.getInt(1);
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
             PreparedStatement st = con.prepareStatement(stringUtil.User_Insert)) {
            st.setString(1, MakeupModel.getUsername());
            st.setString(2, MakeupModel.getFullname());
            st.setString(3, MakeupModel.getEmail());
            st.setString(4, MakeupModel.getPhone_num());
            st.setDate(5, Date.valueOf(MakeupModel.getDob()));
            st.setString(6, MakeupModel.getAddress());
            st.setString(7, PasswordEncryptionWithAes.encrypt(MakeupModel.getUsername(), MakeupModel.getPassword()));
            st.setString(8, MakeupModel.getGender());
            st.setString(9,MakeupModel.getImageUserUrl());
            st.setString(10,MakeupModel.getRole());

            int result = st.executeUpdate();

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
    public Loginresult getStudLoginInfo(LoginModel loginModel) {
        try (Connection con = getConnection()) {
            PreparedStatement ts = con.prepareStatement(stringUtil.GET_LOGIN_DETAILS);

            // Set the username in the first parameter of the prepared statement
            ts.setString(1, loginModel.getUsername()); // Assuming user_name is the correct method in LoginModel to get the username

            ResultSet ss = ts.executeQuery();
            if (ss.next()) {
                String DbUser = ss.getString("username"); // Assuming 'user_name' is the column name for the username
                String encryptedPw = ss.getString(stringUtil.Password);
                String decryptedPw = PasswordEncryptionWithAes.decrypt(encryptedPw, DbUser);
                
                if (DbUser.equalsIgnoreCase(loginModel.getUsername()) && decryptedPw != null && decryptedPw.equals(loginModel.getPassword())) {
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

    public int modifyPassword(String username, String newPassword) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("UPDATE user SET password = ? WHERE username = ?")) {
            st.setString(1, PasswordEncryptionWithAes.encrypt(username, newPassword));
            st.setString(2, username);

            int result = st.executeUpdate();

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
    

    // Method to retrieve all products from the database
//    public List<ProductAdmin> getAllProducts() {
//        List<ProductAdmin> products = new ArrayList<>();
//
//        try (Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
//                PreparedStatement statement = connection.prepareStatement(SELECT_ALL_PRODUCTS_SQL);
//                ResultSet resultSet = statement.executeQuery()) {
//
//            while (resultSet.next()) {
//                String productName = resultSet.getString("product_name");
//                String description = resultSet.getString("description");
//                double price = resultSet.getDouble("price");
//
//                ProductAdmin product = new ProductAdmin(productName, description, price);
//                products.add(product);
//            }
//        } catch (SQLException e) {
//            e.printStackTrace();
//        }
//
//        return products;
//    }
//}

    public int modifyPasswordValid(String username, String newPassword) {
        try (Connection con = getConnection()) {
            // Check if the username exists in the database
            if (isUsernameExists(username)) {
                // Username exists, update the password
                return modifyPassword(username, newPassword);
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
             PreparedStatement st = con.prepareStatement("DELETE FROM makeup WHERE ID_Makeup = ?")) {
            st.setInt(1, makeupId);
            
            return st.executeUpdate();

        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace();
            return -1; // Error
        }
    }
    public UserMakeupModel getProfileUser(String user_name) {
        try (Connection con = getConnection()) {
            PreparedStatement st = con.prepareStatement("SELECT * FROM user WHERE username = ?");
            st.setString(1, user_name);
            ResultSet ss = st.executeQuery();

            if (ss.next()) {
            	UserMakeupModel profileUser = new UserMakeupModel();
            	profileUser.setUsername(ss.getString("username"));
            	profileUser.setFullname(ss.getString("full_Name"));
            	profileUser.setEmail(ss.getString("email"));  
                profileUser.setAddress(ss.getString("address"));
//                userProfile.setImageUrlFromDB(rs.getString("user_image"));
                return profileUser;
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
             PreparedStatement st = con.prepareStatement("UPDATE user SET full_name=?, email=?, address=? WHERE username=?")) {         
            st.setString(1, makeupuser.getFullname());
            st.setString(2, makeupuser.getEmail());
            st.setString(3, makeupuser.getAddress());
            st.setString(4, makeupuser.getUsername());

            return st.executeUpdate();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
            return -1; // Error
        }
    }
    
    public int updateMakeup(MakeupModel makeup) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("UPDATE makeup SET Makeup_Name = ?, Price = ? WHERE ID_Makeup = ?")) {
            st.setString(1, makeup.getMakeupName());
            st.setDouble(2, makeup.getPrice());
            st.setInt(3, makeup.getMakeupID());


            int result = st.executeUpdate();

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


