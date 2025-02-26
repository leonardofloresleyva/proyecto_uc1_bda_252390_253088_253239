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
 * Clase que implementa los métodos de iCitaDAO.
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class CitaDAO implements iCitaDAO {
    iConexion conexion;

    /**
     * Constructor para la clase CitaDAO.
     * @param conexion Objeto de la conexión a MySQL.
     */
    public CitaDAO(iConexion conexion) {
        this.conexion = conexion;
    }
    
    /**
     * Método que permite registrar una cita.
     * @param cita Objeto de tipo Cita.
     * @return True si se registra la cita, false en caso contrario.
     * @throws PersistenciaException 
     */
    @Override
    public boolean registrarCita(Cita cita) throws PersistenciaException {
        // Sentencia sql que llama al procedimiento almacenado.
        String sentenciaSQL = "CALL REGISTRAR_CITA(?, ?, ?, ?)";
        
        try (Connection con = conexion.crearConexion()) {
            // Llamar al procedimiento almacenado.
            try (CallableStatement cs = con.prepareCall(sentenciaSQL)) {
                
                // Agregar los parámetros del procedimiento.
                cs.setObject(1, cita.getFechaHora());
                cs.setInt(2, cita.getMedico().getId());
                cs.setInt(3, cita.getPaciente().getId());
                cs.setString(4, cita.getTipoCita());
                
                // Ejecutar y regresar verdadero.
                cs.execute();
                return true;
            }
        // Lanzar excepción si hubo un error al registrar la cita.
        } catch (SQLException ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, "Error al registrar cita.", ex);
            throw new PersistenciaException("Error al registrar cita.", ex);
        } 
    }

    /**
     * Método que permite cancelar una cita.
     * @param idCita ID de la cita.
     * @return True si se canceló la cita, false en caso contrario.
     * @throws PersistenciaException 
     */
    @Override
    public boolean cancelarCita(int idCita) throws PersistenciaException {
        // Sentencia SQL que llama al procedimiento almacenado.
        String consultaSQL = "CALL CANCELAR_CITA(?)";
        
        try (Connection con = conexion.crearConexion()) {
            try (CallableStatement cs = con.prepareCall(consultaSQL)) {
                // Agregar parámetro a la llamada.
                cs.setInt(1, idCita);
                
                // Ejecutar y regresar verdadero.
                cs.execute();
                return true;
            }
        // Lanzar excepción si no se pudo cancelar la cita.
        } catch (SQLException ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, "Error al cancelar cita.", ex);
            throw new PersistenciaException("Error al cancelar cita.", ex);
        }
    }
    
}