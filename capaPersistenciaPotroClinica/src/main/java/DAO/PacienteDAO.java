package DAO;

import Conexion.iConexion;
import Entidades.Paciente;
import Excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Uribe (253239)
 */
public class PacienteDAO implements iPacienteDAO {
    iConexion conexion;
    iUsuarioDAO usuarioDAO;

    public PacienteDAO(iUsuarioDAO usuarioDAO) {
        this.usuarioDAO = usuarioDAO;
    }
    
    @Override
    public boolean registrarPaciente(Paciente paciente) throws PersistenciaException {
        String comandoSQL = "INSERT INTO pacientes (NOMBRES, APELLIDO_PATERNO, APELLIDO_MATERNO, TELEFONO, FECHA_NACIMIENTO, ESTADO) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection con = conexion.crearConexion()) {
            con.setAutoCommit(false);
            
            
        } catch (Exception e) {
            
        }
    }
    
}