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


public class OptionDAO {
    
    
   public static List<Option> get(int id) {
         ArrayList<Option> arr = new ArrayList<>();
        

        //1. tao connection vs db 'sem2_demo', luu vao bien cn
        Connection cn = dbConnect.Connection();

        if (cn != null) {
            try {
                /*2. tao doi tuong statement de thuc hien lenh select-SQL-where */
                var sql ="SELECT * FROM tbOption WHERE questionID = ? ";
                PreparedStatement st = cn.prepareStatement(sql);
                st.setInt(1,id);
              

                
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
    
    public static void main(String[] args) {

        // test ham BatchDAO.get()
       
    OptionDAO.get(8).forEach(System.out::println);
        
    }
    
}
