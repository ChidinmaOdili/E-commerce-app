package dao;

import model.User;
import util.DatabaseConnection;

import java.sql.*;

public class LoginDao {
    User user = new User();
////    String sql = "SELECT * FROM user WHERE NAME=? and PASS=?";
////    public boolean checkLogin(String uname, String pass) throws SQLException{
//
//
//        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StoreDB","root","1234");
//
//            PreparedStatement stmt = con.prepareStatement(sql);
//            stmt.setString(1, uname);
//            stmt.setString(2, pass);
//            ResultSet rs = stmt.executeQuery();
//            rs = stmt.executeQuery(sql);
//
//            if (rs.next()) {
//                return true;
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return false;
//    }



    public User fetchLoginFromDB(String email, String password) throws SQLException{
        try {
//            Class.forName("com.mysql.cj.jdbc.Driver");
//           Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/StoreDB","root","1234");

            System.out.println("before connection");
            Connection con = DatabaseConnection.getConnection();
            System.out.println("after connection");

            PreparedStatement stmt = con.prepareStatement("SELECT * FROM tblcustomer WHERE email=? and password=?");
            stmt.setString(1, email);
            stmt.setString(2, password);
            System.out.println(stmt);
            ResultSet rs = stmt.executeQuery();
            System.out.println("before looping");
            if (rs.next()) {
                System.out.println("inside looping");
                String emailFromDb = rs.getString("email");
                String passwordFromDb = rs.getString("password");
                user = new User(emailFromDb, passwordFromDb);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return user;
    }
}
