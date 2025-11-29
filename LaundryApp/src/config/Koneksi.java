/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package config;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Faruq
 */
public class Koneksi {
    private static Connection connection;

    public static Connection getConnection() {
        if (connection == null) {
            try {
                String url = "jdbc:mysql://localhost:3306/laundry_pbo_db";
                String user = "root";
                String password = ""; // kalo pake xampp kosongin, ampps "mysql"

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
    
    // Main method untuk test jalan atau tidak
    public static void main(String[] args) {
        getConnection();
    }
}
