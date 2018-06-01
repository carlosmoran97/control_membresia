/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import modelo.Cliente;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.ResultSet;
/**
 *
 * @author Rodrigo
 */
public class ClienteControlador {
    
    public static boolean insertarCliente(String dui, String nombre, String apellido)
    {
        int insertedRows = 0;
        try{
            String sql = "INSERT INTO CLIENTE (DUI, NOMBRE, APELLIDO) VALUES(?, ? , ?)";
            PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
            ps.setString(1, dui);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            
            insertedRows = ps.executeUpdate();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return insertedRows > 0;
    }
    
    public static Cliente obtenerCliente(String dui)
    {
        Cliente cliente = null;
        try{
            String sql = "SELECT * FROM CLIENTE WHERE DUI=?";
            PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
            ps.setString(1, dui);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                cliente = new Cliente();
                cliente.setDui(rs.getString("DUI"));
                cliente.setNombre(rs.getString("NOMBRE"));
                cliente.setApellido(rs.getString("APELLIDO"));
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return cliente;
    }
}
