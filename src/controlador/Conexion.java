/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author Rodrigo
 */
public class Conexion {
    private static Connection conn;
    private static String dbUrl = "jdbc:mysql://localhost:3306/control_membresia";
    private static String username = "root";
    private static String password = "1234";
    private static void conectar(){
        try
        {
            conn = DriverManager.getConnection(dbUrl, username, password);
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
    }
    
    public static Connection getConnection()
    {
        if(conn == null)
        {
            conectar();
        }
        
        return conn;
    }
}
