package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class adminDAO {

    public class UserAuthentication {

        public static String authenticateUser(String username, String password) {
            try {
                Connection conn = dbConnect.Connection();
                String sql = "SELECT role FROM tbUser WHERE username=? AND password=?";
                PreparedStatement ps = conn.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                ResultSet rs = ps.executeQuery();
                if (rs.next()) {
                    return rs.getString("role");
                }
                return null;
            } catch (Exception e) {
                e.printStackTrace();
                return null;
            }
        }
    }

   
}
