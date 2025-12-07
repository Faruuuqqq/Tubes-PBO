/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Faruq
 */
public class Koneksi {
    private static Connection connection;
    private static Statement state;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/laundry_pbo_db";
                String user = "root";
                String password = "mysql"; // kalo pake xampp kosongin, ampps "mysql"

                // Register Driver
                DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
                connection = DriverManager.getConnection(url, user, password);
                System.out.println("Koneksi Berhasil!");
            } catch (SQLException e) {
                System.out.println("Error Koneksi: " + e.getMessage());
                Logger.getLogger(Koneksi.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return connection;
    }
    
    public boolean query(String stringQuery) {
        Connection conn = getConnection();

        try {
            state = conn.createStatement();
            int rows = state.executeUpdate(stringQuery);

            if (rows > 0) {
                System.out.println("Query executed (Rows affected: " + rows + "): " + stringQuery);
                return true;
            } else {
                System.err.println("Query executed but NO ROWS affected: " + stringQuery);
                return false;
            }

        } catch (SQLException e) {
            System.err.println("Query Error: " + e.getMessage());
            System.err.println("Query: " + stringQuery);
            return false;
        }
    }

    
    public ResultSet getData(String SQLString) {
        Connection conn = getConnection();
        
        ResultSet rs = null;
        try {
            state = conn.createStatement();
            rs = state.executeQuery(SQLString);
            System.out.println("Query executed: " + SQLString);

        } catch (SQLException e) {
            System.err.println("Query Error: " + e.getMessage());
            System.err.println("Query: " + SQLString);
        }
        return rs;
    }
    // Main method untuk test jalan atau tidak
    public static void main(String[] args) {
        getConnection();
    }
}
