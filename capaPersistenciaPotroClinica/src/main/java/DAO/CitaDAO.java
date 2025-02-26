package DAO;

import Conexion.Conexion;
import Conexion.iConexion;
import Entidades.Cita;
import Entidades.Medico;
import Excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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
    
    @Override
    public List<Medico> medicosDisponibles(LocalDateTime fechaHora, String especialidad) throws PersistenciaException{
        String sentenciaSQL = """
                              SELECT *
                              FROM MEDICOS AS M
                              INNER JOIN USUARIOS AS U
                              ON M.ID_MEDICO = U.ID
                              INNER JOIN CITAS AS C
                              ON C.ID_MEDICO = M.ID_MEDICO
                              WHERE C.FECHA_HORA != ?
                              AND ESPECIALIDAD = ?;""";
        try (
                Connection con = this.conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(sentenciaSQL);
                )
        {
            ps.setObject(1, fechaHora);
            ps.setString(2, especialidad);
            List<Medico> medicos = new ArrayList<>();
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Medico medico = new Medico(
                            rs.getInt("ID"),
                            "",
                            "",
                            rs.getString("NOMBRES"),
                            rs.getString("APELLIDO_PATERNO"),
                            rs.getString("APELLIDO_MATERNO"),
                            rs.getString("ESPECIALIDAD"),
                            rs.getString("ESTADO_MEDICO")
                    );
                medicos.add(medico);
            }
            return medicos;
        } catch (SQLException e) {
            throw new PersistenciaException("Error en la consulta de medicos disponibles");
        }
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