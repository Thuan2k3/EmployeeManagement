/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package database;

import java.sql.*;


/**
 *
 * @author tranm
 */
public class DatabaseUtils {
    
    public static final String connectionUrl = "jdbc:sqlserver://THUAN\\SQLEXPRESS:1433;"
            +"databaseName=QuanLyNhanVienLibrary;user=sa;password=sa2019";
    
    public static Connection getDBConnect(){
        try{
            Connection con = null;
            //Dang ky trinh dieu khien JDBC
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
            //Thiet lap ket noi den CSDL
            con = DriverManager.getConnection(connectionUrl);
            return con;
        }catch(ClassNotFoundException ex){
            System.out.println("Where is Driver?");
            System.out.println("Error: "+ex.toString());
        }catch(SQLException ex){
            //Loi ket noi
            System.out.println("Error: "+ex.toString());
        }
        return null;
    }
}
