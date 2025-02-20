package DAO;

import Conexion.iConexion;
import Entidades.Paciente;
import Entidades.Usuario;
import Excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Uribe (253239)
 */
public class PacienteDAO implements iPacienteDAO {
    iConexion conexion;

    public PacienteDAO(iConexion conexion) {
        this.conexion = conexion;
    }
    
    @Override
    public boolean registrarPaciente(Paciente paciente) throws PersistenciaException {
        // Comando SQL para insertar un paciente
        String comandoSQL = "INSERT INTO pacientes (NOMBRES, APELLIDO_PATERNO, APELLIDO_MATERNO, TELEFONO, FECHA_NACIMIENTO, ESTADO) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection con = conexion.crearConexion()) {
            con.setAutoCommit(false);
            
            try (PreparedStatement ps = con.prepareStatement(comandoSQL, Statement.RETURN_GENERATED_KEYS)) {
                ps.setInt(1, paciente.getId());
                ps.setString(1, paciente.getNombres());
                ps.setString(2, paciente.getApellidoPaterno());
                ps.setString(3, paciente.getApellidoMaterno());
                ps.setString(4, paciente.getTelefono());
                ps.setObject(5, paciente.getFechaNacimiento());
                ps.setString(6, paciente.getEstado());
                
                int filasAfectadas = ps.executeUpdate();
                if (filasAfectadas == 0) {
                    throw new PersistenciaException("No se pudo registrar al paciente");
                }
            }
            
            con.commit();
            return true;
            
        } catch (Exception e) {
            Logger.getLogger(PacienteDAO.class.getName()).log(Level.SEVERE, null, e);
            throw new PersistenciaException("Error al registrar el paciente.", e);
        }
        
    }   
}