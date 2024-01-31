/*
tao thu vien ket noi giua ung dung java va csdl sqlserver
 */
package dao;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class dbConnect {

    public static String user = "sa";
    public static String password = "Password.1";
    public static String url = "jdbc:sqlserver://localhost:1433;encrypt=true;databaseName=dbQuiz;trustServerCertificate=true;";

    public static Connection Connection() {
        Connection cn = null;

        try {
            //1. nap driver jdbc 
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            //2.tao connection
            cn = DriverManager.getConnection(url, user, password);

        } catch (ClassNotFoundException | SQLException ex) {
            System.err.println("LOI:" + ex.getMessage());
        }
        
        return cn;

    }

    
    public static void main(String[] args) {
        dbConnect p = new dbConnect();
        p.Connection();
    }

}
