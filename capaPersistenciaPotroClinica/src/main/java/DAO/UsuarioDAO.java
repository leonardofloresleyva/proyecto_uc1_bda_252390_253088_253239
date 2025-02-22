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
 * Clase que contiene las operaciones de un Usuario.
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Uribe (253239)
 */
public class UsuarioDAO implements iUsuarioDAO {
    // Crear conexión
    iConexion conexion;
    private static final Logger logger = Logger.getLogger(PacienteDAO.class.getName());
    
    @Override
    public int registrarUsuario(Usuario usuario) throws PersistenciaException {
        String sentenciaSQL = "INSERT INTO USUARIOS (USUARIO, CONTRASENIA, ROL) VALUES (?, ?, ?)";
        int id = -1; // Variable que guarda el id del usuario
        
        try (Connection con = conexion.crearConexion();
                // Statement.RETURN_GENERATED_KEYS recupera el id del usuario
                PreparedStatement ps = con.prepareStatement(sentenciaSQL, Statement.RETURN_GENERATED_KEYS)) {
            
            // Crear al usuario
            ps.setString(1, usuario.getUsuario());
            ps.setString(2, usuario.getContrasenia());
            ps.setString(3, usuario.getRol());
            ps.executeUpdate();
            
            int filasAfectadas = ps.executeUpdate();
            if (filasAfectadas == 0) {
                throw new PersistenciaException("Error al registrar al usuario, no se generó ningún id");
            }
            
            // Obtener el id generado y verificar si se ha almacenado
            try (ResultSet rs = ps.getGeneratedKeys()) {
                if (!rs.next()) { // Si no lo tiene, se lanza una excepción
                    throw new PersistenciaException("No se registró el usuario");
                }
                id = rs.getInt(1); // Si lo encuentra, se guarda en la variable id
            }
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, null, ex);
            throw new PersistenciaException("Error al guardar el usuario");
        }
        
        return id; // Regresar id generado
    }
    
    
    
}