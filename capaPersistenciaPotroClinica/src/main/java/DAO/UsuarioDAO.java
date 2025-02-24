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
    public boolean iniciarSesion (String usuario, String contrasenia) throws PersistenciaException {
        String consultaSQL = "SELECT * FROM USUARIOS WHERE CORREO = ? AND CONTRASENIA = ?";

        try (
                Connection con = conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL);) {
            ps.setString(1, usuario);
            ps.setString(2, contrasenia);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Si hay resultados, las credenciales son válidas
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al validar credenciales.", ex);
            throw new PersistenciaException("Error al validar las credenciales.", ex);
        }
    }
    
    
    
}