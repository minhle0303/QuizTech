/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

public class QuestionDAO {

    public static ArrayList<Question> get() {
        ArrayList<Question> ds = new ArrayList<>();
        

        Connection cn = dbConnect.Connection();

        if (cn != null) {
            try {

                Statement st = cn.createStatement();

                String sql = " select * from tbQuestion";
                PreparedStatement pst = cn.prepareStatement(sql);

                ResultSet rs = st.executeQuery(sql);

                while (rs.next()) {

                    Question e = new Question();
                    e.questionID = rs.getInt("questionID");
                    e.content = rs.getString("content");
                    e.type = rs.getInt("type");
                    e.topic = rs.getString("topic");
                    e.answer = rs.getBoolean("answer");

                    ds.add(e);

                }
                //5. Dong tai nguyen
                rs.close();
                st.close();
                cn.close();
                

//                ds = new ArrayList<>(ds2);
            } catch (SQLException ex) {
                System.err.println("LOI:" + ex.getMessage());
            }
        }

        return ds;

    }

    public static List<Option> getoption(int questionID) {
        List<Option> arr = new ArrayList<>();

        //1. tao connection vs db 'sem2_demo', luu vao bien cn
        Connection cn = dbConnect.Connection();

        if (cn != null) {
            try {
                /*2. tao doi tuong statement de thuc hien lenh select-SQL-where */
                var sql = "SELECT * FROM tbOption WHERE questionID = ? ";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setInt(1, questionID);

                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    //doc dong hien tai, tao thanh 1 doi tuong Batch cat vao 'ds'
                    Option q = new Option();
                    q.optionID = rs.getInt("optionID");
                    q.questionID = rs.getInt("questionID");
                    q.content = rs.getString("content");//cột thứ 3 trong bảng Batch

                    q.answer = rs.getBoolean("answer");//cột thứ 3 trong bảng Batch
                    arr.add(q);
                }
                //5. Dong tai nguyen
                rs.close();
                st.close();
                cn.close();

            } catch (SQLException ex) {
                System.err.println("LOI:" + ex.getMessage());
            }
        }

        return arr;

    }

    public static List<Question> getz(int level, String topic) {
        ArrayList<Question> arr = new ArrayList<>();
        ArrayList<Question> arr2 = new ArrayList<>();
        //1. tao connection vs db 'sem2_demo', luu vao bien cn
        Connection cn = dbConnect.Connection();

        if (cn != null) {
            try {
                /*2. tao doi tuong statement de thuc hien lenh select-SQL-where */
                var sql = "SELECT * FROM tbquestion WHERE type = ? and topic = ?";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setInt(1, level);
                st.setString(2, topic);

                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    //doc dong hien tai, tao thanh 1 doi tuong Batch cat vao 'ds'
                    Question q = new Question();
                    q.questionID = rs.getInt(1);//ten phai dung voi bang Batch
                    q.content = rs.getString(2);
                    q.type = rs.getInt(3);
                    q.topic = rs.getString(4);
                    q.answer = rs.getBoolean(5);//cột thứ 3 trong bảng Batch
                    arr.add(q);
                }
                //5. Dong tai nguyen
                rs.close();
                st.close();
                cn.close();
                int max = 0;
                int size = arr.size();
                max = max > 10 ? 10 : size;

                ArrayList<Integer> list = new ArrayList<>();
                Random rd = new Random();
                int n = 0;
                while (list.size() < max) {
                    n = rd.nextInt(0, max);
                    if(list.contains(n)){
                    continue;
                    }
                    list.add(n);
//                    System.out.println("n: " + n);
//                    System.out.println("list:" + list);

                }
//                System.out.println("list:" + list);
                //int index=0;
                for (int i = 0; i < max; i++) {
                    //index = list.get(i);
                    arr2.add(arr.get(list.get(i)));
                }

            } catch (SQLException ex) {
                System.err.println("LOI:" + ex.getMessage());
            }
        }
        
        return arr2;

    }
//    public static void main(String[] args) {
//
//        // test ham BatchDAO.get()
//       
//    QuestionDAO.getOption().forEach(System.out::println);
//        
//    }

    public static ArrayList<Option> getOptions(String sIDs) {
        ArrayList<Option> arr = new ArrayList<>();

        Connection cn = dbConnect.Connection();

        if (cn != null) {
            try {
                /*2. tao doi tuong statement de thuc hien lenh select-SQL-where */
                var sql = "SELECT * FROM tbOption WHERE questionID in (" + sIDs + ") ";
                PreparedStatement st = cn.prepareStatement(sql);

                System.out.println("sql: " + sql);

                ResultSet rs = st.executeQuery();

                while (rs.next()) {
                    //doc dong hien tai, tao thanh 1 doi tuong Batch cat vao 'ds'
                    Option q = new Option();
                    q.optionID = rs.getInt("optionID");
                    q.questionID = rs.getInt("questionID");
                    q.content = rs.getString("content");//cột thứ 3 trong bảng Batch

                    q.answer = rs.getBoolean("answer");//cột thứ 3 trong bảng Batch
                    arr.add(q);
                    
                }
                //5. Dong tai nguyen
                rs.close();
                st.close();
                cn.close();

            } catch (SQLException ex) {
                System.err.println("LOI:" + ex.getMessage());
            }
        }

        return arr;
    }

}
