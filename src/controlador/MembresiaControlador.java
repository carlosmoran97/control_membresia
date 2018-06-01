/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Membresia;
import java.sql.Date;

/**
 *
 * @author Rodrigo
 */
public class MembresiaControlador {
    public static boolean insertarMembresia(String codigo_membresia, String dui, Date fechaAdquisicion, int puntos)
    {
        int insertedRows = 0;
        try{
            String sql = "INSERT INTO MEMBRESIA (CODIGO_MEMBRESIA, DUI, FECHAADQUISICION, PUNTOS) VALUES(?, ? , ?, ?)";
            PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
            ps.setString(1, codigo_membresia);
            ps.setString(2, dui);
            ps.setDate(3, fechaAdquisicion);
            ps.setInt(4, puntos);
            
            insertedRows = ps.executeUpdate();
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return insertedRows > 0;
    }
    
    public static Membresia obtenerMembresia(String codigo_membresia)
    {
        Membresia membresia = null;
        try{
            String sql = "SELECT * FROM MEMBRESIA WHERE CODIGO_MEMBRESIA=?";
            PreparedStatement ps = Conexion.getConnection().prepareStatement(sql);
            ps.setString(1, codigo_membresia);
            
            ResultSet rs = ps.executeQuery();
            if(rs.next())
            {
                membresia = new Membresia();
                membresia.setCodigo(rs.getString("CODIGO_MEMBRESIA"));
                membresia.setCliente(ClienteControlador.obtenerCliente(rs.getString("DUI")));
                membresia.setFechaAdquisicion(rs.getDate("FECHAADQUISICION"));
                membresia.setPuntos(rs.getInt("PUNTOS"));
            }
        }
        catch(SQLException ex)
        {
            ex.printStackTrace();
        }
        
        return membresia;
    }
}
