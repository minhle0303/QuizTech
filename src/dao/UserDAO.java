/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

public class UserDAO {

    public static User authenticateUser(String username, String password) {

        User user = null;
        try {
            Connection conn = dbConnect.Connection();
            String sql = "SELECT * FROM tbUser WHERE username=? AND PASSWORD=?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1, username);
            ps.setString(2, password);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                user = new User();
                user.userID = rs.getInt("userID");
                user.name = rs.getString("name");
                user.username = rs.getString("username");
                user.email = rs.getString("email");
                user.gender = rs.getString("gender");
                user.bod = rs.getString("bod");
                user.role = rs.getString("role");
                user.phone = rs.getString("phone");
                user.password = rs.getString("PASSWORD");

            }
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();

        }

        return user;
    }

     public static User getbyid(int userID) {
        User user = null;
        try {
            Connection conn = dbConnect.Connection();
            String sql = "SELECT * FROM tbUser WHERE userID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, userID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                user = new User();
              
                    user.userID = rs.getInt("userID");
                    user.name = rs.getString("name");
                    user.phone = rs.getString("phone");
                    user.gender = rs.getString("gender");
                    user.bod = rs.getString("bod");
                    user.role = rs.getString("role");
                    user.username = rs.getString("username");
                    user.password = rs.getString("PASSWORD");
                    user.email = rs.getString("email");

            } else {
                System.out.println("No result found for ResultID: " + userID);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace(); // or log the exception
        }

        return user;
    }
    public static ArrayList<User> getByID(int id) {
        ArrayList<User> ds = new ArrayList<>();

        // 1. tao connect voi db"sem2-demo", neu thanh cong ket noi luu trong nien cn
        Connection cn = dbConnect.Connection();
        if (cn != null) {
            try {
                //2. tao doi tuong doi tuong Statment de thuc hien lenh select-sql
                Statement st = cn.createStatement();
//                
                String sql = "select * from tbUser where userid LIKE ?";

                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, id);

                ResultSet rs = pst.executeQuery();

                //4. duyet su lieu trong 'rs', luu vo 'ds'
                while (rs.next()) {
                    //doc dong hien tai, tao thanh 1 doi tuong User
                    User b = new User();
                    b.userID = rs.getInt("userID");
                    b.name = rs.getString("name");
                    b.phone = rs.getString("phone");
                    b.gender = rs.getString("gender");
                    b.bod = rs.getString("bod");
                    b.role = rs.getString("role");
                    b.username = rs.getString("username");
                    b.password = rs.getString("PASSWORD");
                    b.email = rs.getString("email");

                    ds.add(b);
                    System.out.println("");
                }
                //5. dong tai nguyen
                rs.close();
                st.close();
                cn.close();
            } catch (SQLException ex) {
                System.err.println(">> LOi:" + ex.getMessage());
            }

        }
        return ds;

    }

    public static ArrayList<User> get() {
        ArrayList<User> ds = new ArrayList<>();

        Connection cn = dbConnect.Connection();

        if (cn != null) {
            try {

                Statement st = cn.createStatement();

                String sql = " select * from tbUser";
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {

                    User e = new User();
                    e.userID = rs.getInt("userID");
                    e.name = rs.getString("name");
                    e.phone = rs.getString("phone");
                    e.gender = rs.getString("gender");
                    e.bod = rs.getString("bod");
                    e.role = rs.getString("role");
                    e.username = rs.getString("username");
                    e.password = rs.getString("password");
                    e.email = rs.getString("email");

                    ds.add(e);

                }
                //5. Dong tai nguyen
                rs.close();
                st.close();
                cn.close();

            } catch (SQLException ex) {
                System.err.println("LOI:" + ex.getMessage());
            }
        }

        return ds;

    }

    public void insertPlayer(String name, String phone, String gender, String bod, String role, String username, String PASSWORD, String email) {
        Connection cn = dbConnect.Connection();
        PreparedStatement ps;

        try {
            ps = cn.prepareStatement("insert into tbUser(name,phone,gender,bod,role,username,PASSWORD,email) values (?,?,?,?,?,?,?,?)");

            ps.setString(1, name);
            ps.setString(2, phone);
            ps.setString(3, gender);
            ps.setString(4, bod);
            ps.setString(5, role);
            ps.setString(6, username);
            ps.setString(7, PASSWORD);
            ps.setString(8, email);

            if (ps.executeUpdate() > 0) {
                JOptionPane.showMessageDialog(null, "New player");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    
}
