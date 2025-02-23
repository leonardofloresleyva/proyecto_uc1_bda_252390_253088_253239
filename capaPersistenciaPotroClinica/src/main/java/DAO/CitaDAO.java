package DAO;

import Conexion.iConexion;
import Entidades.Cita;
import Excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class CitaDAO implements iCitaDAO {

    iConexion conexion;

    public CitaDAO(iConexion conexion) {
        this.conexion = conexion;
    }
    
    @Override
    public boolean registrarCita(Cita cita) throws PersistenciaException {
        // Sentencia sql
        String sentenciaSQL = "CALL REGISTRAR_CITA(?, ?, ?, ?)";
        
        try (Connection con = conexion.crearConexion()) {
            // Llamar al procedimiento almacenado
            try (CallableStatement cs = con.prepareCall(sentenciaSQL)) {
                
                // Agregar los parámetros del procedimiento
                cs.setObject(1, cita.getFechaHora());
                cs.setInt(2, cita.getMedico().getId());
                cs.setInt(3, cita.getPaciente().getId());
                cs.setString(4, cita.getTipoCita());
                
                // Ejecutar y regresar verdadero
                cs.execute();
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, "Error al registrar cita.", ex);
            throw new PersistenciaException("Error al registrar cita.", ex);
        } 
    }

    @Override
    public boolean cancelarCita(int idCita) throws PersistenciaException {
        String consultaSQL = "CALL CANCELAR_CITA(?)";
        
        try (Connection con = conexion.crearConexion()) {
            try (CallableStatement cs = con.prepareCall(consultaSQL)) {
                // Agregar parámetro a la llamada
                cs.setInt(1, idCita);
                
                // Ejecutar y regresar verdadero
                cs.execute();
                return true;
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, "Error al cancelar cita.", ex);
            throw new PersistenciaException("Error al cancelar cita.", ex);
        }
    }
    
}