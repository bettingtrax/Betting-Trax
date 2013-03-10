/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package btrax;

 import java.sql.*;
 import java.util.*;

/**
 *
 * @author AtweLu01
 */
public class ConnectionManager {
    
    static Connection con;
    static String url;
    
    public static Connection getConnection() {
        
        try {
            String url = "jdbc:" + "mysql://betting-trax.com:3306/bettrax_betting_trax";
            // assuming "DataSource" is your DataSource name
            Class.forName("com.mysql.jdbc.Driver");
        
            try {
                con = DriverManager.getConnection(url,"bettrax","Obsolete82");
                // assuming your SQL Server's username is "username"
                // and password is "password"
            }
            
            catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
        
        catch(ClassNotFoundException e) {
            System.out.println(e);
        }
        
        return con;
    }
}
