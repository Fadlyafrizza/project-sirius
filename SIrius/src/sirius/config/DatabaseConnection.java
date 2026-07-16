/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package sirius.config;


import com.mysql.cj.jdbc.MysqlDataSource;
import java.sql.SQLException;
import java.sql.Connection;

/**
 *
 * @author afrizza
 */
public class DatabaseConnection {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/project_sirius";
    private static final String DB_USERNAME = "root";
    private static final String DB_PASSWORD = "password";
    private static Connection koneksi;
    
    public static Connection getKoneksi() throws SQLException{
        if (koneksi == null) {
            MysqlDataSource mysqlDS = new MysqlDataSource();
            mysqlDS.setURL(DB_URL);
            mysqlDS.setUser(DB_USERNAME);
            mysqlDS.setPassword(DB_PASSWORD);
            
            koneksi = mysqlDS.getConnection();
        }

        
        return koneksi;
    }
}
