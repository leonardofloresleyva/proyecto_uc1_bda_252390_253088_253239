/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package DAO;

import Conexion.iConexion;
import Entidades.Usuario;
import Excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Ximena
 */
public class UsuarioDAO implements iUsuarioDAO {
    iConexion conexion;
    
    @Override
    public int registrarUsuario(Usuario usuario) throws PersistenciaException {
        String sentenciaSQL = "INSERT INTO USUARIOS (USUARIO, CONTRASENIA, ROL) VALUES (?, ?, ?)";
        int id = -1;
        
        try (Connection con = conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS)) {
            
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getContrasenia());
            ps.setString(3, usuario.getRol());
            
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("La insercion del usuario falló");
            }
            
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (!rs.next()) {
                    throw new PersistenciaException("No se registró el usuario");
                }
                id = rs.getInt(1);
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(UsuarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Error al guardar el usuario");
        }
        
        return id;
    }
    
}
