package DAO;

import Conexion.iConexion;
import Entidades.Paciente;
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
 * @author Luis Eduardo Uribe Vega (253239)
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
        String comandoPro = "CALL REGISTRAR_PACIENTE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        
        try (Connection con = conexion.crearConexion()) {
            con.setAutoCommit(false);
            
            try (PreparedStatement ps = con.prepareStatement(comandoSQL, Statement.RETURN_GENERATED_KEYS);
                    PreparedStatement psp = con.prepareStatement(comandoPro)) {
                // Agregar datos del paciente
                ps.setString(1, paciente.getNombres());
                ps.setString(2, paciente.getApellidoPaterno());
                ps.setString(3, paciente.getApellidoMaterno());
                ps.setString(4, paciente.getTelefono());
                ps.setObject(5, paciente.getFechaNacimiento());
                ps.setString(6, paciente.getEstado());
                
                // Agregar valores a tabla de usuarios y direcciones
                psp.setString(1, paciente.getUsuario());
                psp.setString(2, paciente.getContrasenia());
                psp.setString(3, paciente.getRol());
                psp.setString(4, paciente.getNombres());
                psp.setString(5, paciente.getApellidoPaterno());
                psp.setString(6, paciente.getApellidoMaterno());
                psp.setString(7, paciente.getTelefono());
                psp.setObject(8, paciente.getFechaNacimiento());
                psp.setString(9, paciente.getEstado());
                psp.setString(10, paciente.getColonia());
                psp.setString(11, paciente.getCalle());
                psp.setString(12, paciente.getNumero());
                
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