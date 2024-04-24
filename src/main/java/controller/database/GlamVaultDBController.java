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
import model.PasswordEncryptionWithAes;
import model.UserModel;
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
    public ArrayList<UserModel> getAllStudent() {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(stringUtil.GET_LOGIN_DETAILS)) {
            ResultSet ss = st.executeQuery();
            ArrayList<UserModel> new_user = new ArrayList<>();

            while (ss.next()) {
                UserModel new_users = new UserModel();
                new_users.setUser_name(ss.getString("UserName"));
                new_users.setFull_name(ss.getString("firstName"));
                new_users.setEmail(ss.getString("Email"));
                new_users.setPhone_number(ss.getString("PhoneNumber"));
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
//    public boolean isUsernameExists(String username) {
//        try (Connection con = getConnection();
//             PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM user WHERE user_name = ?")) {
//            st.setString(1, username);
//            try (ResultSet ss = st.executeQuery()) {
//                if (ss.next()) {
//                    int count = ss.getInt(1);
//                    return count > 0;
//                }
//            }
//        } catch (SQLException | ClassNotFoundException ex) {
//            ex.printStackTrace(); 
//        }
//        return false;
//    }
//    
//    public boolean isEmailExists(String email) {
//        try (Connection con = getConnection();
//             PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM user WHERE email = ?")) {
//            st.setString(1, email);
//            try (ResultSet ss = st.executeQuery()) {
//                if (ss.next()) {
//                    int count = ss.getInt(1);
//                    return count > 0;
//                }
//            }
//        } catch (SQLException | ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//        return false;
//    }

//    public boolean isPhoneNumberExists(String phoneNumber) {
//        try (Connection con = getConnection();
//             PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM user WHERE phone_number = ?")) {
//            st.setString(1, phoneNumber);
//            try (ResultSet ss = st.executeQuery()) {
//                if (ss.next()) {
//                    int count = ss.getInt(1);
//                    return count > 0;
//                }
//            }
//        } catch (SQLException | ClassNotFoundException ex) {
//            ex.printStackTrace();
//        }
//        return false;
//    }
    
    public int NewUserAdd(UserModel MakeupModel) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement(stringUtil.User_Insert)) {
            st.setString(1, MakeupModel.getUser_name());
            st.setString(2, MakeupModel.getFull_name());
            st.setString(3, MakeupModel.getEmail());
            st.setString(4, MakeupModel.getPhone_number());
            st.setDate(5, Date.valueOf(MakeupModel.getDob()));
            st.setString(6, MakeupModel.getAddress());
            st.setString(7, PasswordEncryptionWithAes.encrypt(MakeupModel.getUser_name(), MakeupModel.getPassword()));
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
            ts.setString(1, loginModel.getUser_name()); // Assuming user_name is the correct method in LoginModel to get the username

            ResultSet ss = ts.executeQuery();
            if (ss.next()) {
                String DbUser = ss.getString("user_name"); // Assuming 'user_name' is the column name for the username
                String encryptedPw = ss.getString(stringUtil.Password);
                String decryptedPw = PasswordEncryptionWithAes.decrypt(encryptedPw, DbUser);
                
                if (DbUser.equalsIgnoreCase(loginModel.getUser_name()) && decryptedPw != null && decryptedPw.equals(loginModel.getPassword())) {
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

    public int modifyPassword(String phoneNumber, String newPassword) {
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("UPDATE user SET password = ? WHERE phone_number = ?")) {
            st.setString(1, PasswordEncryptionWithAes.encrypt(phoneNumber, newPassword));
            st.setString(2, phoneNumber);

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
    public int ADDProduct(product Product) {
    	try (Connection con = getConnection();
                PreparedStatement ss = con.prepareStatement("INSERT INTO makeup (Makeup_Name, Price,  product_image) VALUES (?, ?,  ?)")) {
    		ss.setString(1, Product.getProductName());
    		
    		ss.setDouble(2, Product.getPrice());
    		ss.setString(3, Product.getUserImageUrl());

    		ss.executeUpdate();
        } catch ( ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            return -1;
        }
		return 0;
		
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

    public int modifyPasswordValid(String phoneNumber, String oldPassword, String newPassword) {
        // First, validate the old password
        try (Connection con = getConnection();
             PreparedStatement st = con.prepareStatement("SELECT password FROM user WHERE phone_number = ?")) {
            
                
        	if (isUsernameExists(phoneNumber)) {
                // Username exists, update the password
                return modifyPassword(phoneNumber, newPassword);
            }else {
                    // Old password does not match, return -2 for incorrect old password
                    return -2;
                }
            
        } catch (SQLException | ClassNotFoundException ex) {
            ex.printStackTrace();
            return -3; // Error
        }
    }
    public boolean isUsernameExists(String phoneNumber) {
        try (Connection con = getConnection();
                PreparedStatement st = con.prepareStatement("SELECT COUNT(*) FROM user WHERE phone_number = ?")) {
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
}

