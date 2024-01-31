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

public class ResultDAO {

    public static ArrayList<Result> get() {
        ArrayList<Result> ds = new ArrayList<>();

        Connection cn = dbConnect.Connection();

        if (cn != null) {
            try {

                Statement st = cn.createStatement();

                String sql = " select * from tbResult";
                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {

                    Result e = new Result();
                    e.resultid = rs.getInt("resultid");
                    e.userID = rs.getInt("userID");
                    e.duration = rs.getString("duration");
                    e.date = rs.getString("date");
                    e.level = rs.getInt("level");
                    e.topic = rs.getString("topic");

                    ds.add(e);

                }
                rs.close();
                st.close();
                cn.close();

            } catch (SQLException ex) {
                System.err.println("LOI:" + ex.getMessage());
            }
        }

        return ds;

    }

    public static Result getByID(int ResultID) {
        Result result = null;
        try {
            Connection conn = dbConnect.Connection();
            String sql = "SELECT * FROM tbResult WHERE ResultID = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1, ResultID);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                result = new Result();
                result.resultid = rs.getInt("resultID");
                result.userID = rs.getInt("userID");
                result.score = rs.getInt("score");
                result.duration = rs.getString("duration");
                result.date = rs.getString("date");
                result.level = rs.getInt("level");
                result.topic = rs.getString("topic");
            } else {
                System.out.println("No result found for ResultID: " + ResultID);
            }

            conn.close();
        } catch (SQLException e) {
            e.printStackTrace(); // or log the exception
        }

        return result;
    }

    public static int create(Result b) {
        int r = 0;
        //1. tao connection vs db 'sem2_demo', luu vao bien cn
        Connection cn = dbConnect.Connection();

        if (cn != null) {
            //2. tao doi tuong prepare-statement chua lenh insert-SQL
            var sql = "INSERT INTO tbResult([resultID],[userID],[score],[duration],[date],[level],[topic]) VALUES (?,?,?,?,?,?,?)";
            try {
                PreparedStatement pst = cn.prepareStatement(sql);
                pst.setInt(1, b.resultid);
                pst.setInt(2, b.userID);
                pst.setInt(3, b.score);
                pst.setString(4, b.duration);
                pst.setString(5, b.date);
                pst.setInt(6, b.level);
                pst.setString(7, b.topic);

                //4. Thi hanh lenh insert
                r = pst.executeUpdate();

                //5. dong tai nguyen
                pst.close();
                cn.close();

            } catch (SQLException ex) {
                System.err.println("LOI:" + ex.getMessage());

            }

        }

        return r;

    }

    public static void main(String[] args) {

    }
}
