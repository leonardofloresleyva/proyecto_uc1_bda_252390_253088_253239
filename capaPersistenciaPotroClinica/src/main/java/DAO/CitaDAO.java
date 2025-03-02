package DAO;

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
    // Objeto conexion para crear una conexión con la base de datos.
    iConexion conexion;
    // Logger para el registro de información importante.
    private static final Logger logger = Logger.getLogger(CitaDAO.class.getName());

    /**
     * Constructor para la clase CitaDAO.
     * @param conexion Objeto de la conexión a MySQL.
     */
    public CitaDAO(iConexion conexion) {this.conexion = conexion;}
    
    @Override
    public List<Medico> medicosDisponibles(LocalDateTime fechaHora, String especialidad) throws PersistenciaException{
        // Variable con la consulta SQL.
        String sentenciaSQL = """
                              SELECT DISTINCT
                                  M.ID_MEDICO,
                                  M.NOMBRES,
                                  M.APELLIDO_PATERNO,
                                  M.APELLIDO_MATERNO,
                                  M.ESPECIALIDAD,
                                  M.ESTADO
                              FROM MEDICOS AS M
                              INNER JOIN USUARIOS AS U
                              ON M.ID_MEDICO = U.ID
                              INNER JOIN CITAS AS C
                              ON C.ID_MEDICO = M.ID_MEDICO
                              WHERE C.FECHA_HORA != ?
                              AND ESPECIALIDAD = ?;""";
        try (
                Connection con = this.conexion.crearConexion(); // Se crea una conexión con la BD.
                PreparedStatement ps = con.prepareStatement(sentenciaSQL); // Se crea el PreparedStatement para ejecutar la consulta.
                )
        {
            ps.setObject(1, fechaHora); // Se inserta la fecha y hora de la cita deseada.
            ps.setString(2, especialidad); // Se inserta la especialidad.
            List<Medico> medicos = new ArrayList<>(); // Lista de médicos a regresar.
            ResultSet rs = ps.executeQuery(); // ResultSet con el resultado de la consulta.
            
            while(rs.next()){ // Mientras hayan registros de la consulta.
                Medico medico = new Medico( // Se crea una nueva entidad Médico por cada médico recibido.
                            rs.getInt("ID_MEDICO"),
                            "",
                            "",
                            rs.getString("NOMBRES"),
                            rs.getString("APELLIDO_PATERNO"),
                            rs.getString("APELLIDO_MATERNO"),
                            rs.getString("ESPECIALIDAD"),
                            rs.getString("ESTADO")
                    );
                medicos.add(medico); // Se añande cada médico a la lista.
            }
            
            return medicos; // Se regresa la lista de médicos.
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error en la consulta con la base de datos. Causa{0}", ex.getMessage());
            throw new PersistenciaException("Error en la consulta de medicos disponibles.");
        }
    }
    
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
            logger.log(Level.SEVERE, "Error al registrar cita. Causa: " + ex.getMessage(), ex);
            throw new PersistenciaException(ex.getMessage(), ex);
        } 
    }

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
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, "Error al cancelar cita. Causa: " + ex.getMessage(), ex);
            throw new PersistenciaException(ex.getMessage(), ex);
        }
    }
}