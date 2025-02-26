package DAO;

import Conexion.iConexion;
import Entidades.Consulta;
import Excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa los métodos de iConsultaDAO.
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class ConsultaDAO implements iConsultaDAO {
    iConexion conexion;
    // Logger para el registro de información importante.
    private static final Logger logger = Logger.getLogger(PacienteDAO.class.getName());

    /**
     * Constructor para la clase ConsultaDAO.
     * @param conexion Objeto conexión a MySQL.
     */
    public ConsultaDAO(iConexion conexion) {
        this.conexion = conexion;
    }
    
    /**
     * Método que registra una consulta.
     * @param consulta Objeto de tipo Consulta.
     * @return True si se registra la consulta, false en caso contrario.
     * @throws PersistenciaException 
     */
    @Override
    public boolean registrarConsulta(Consulta consulta) throws PersistenciaException {
        // Sentencia de MySQL
        String comandoSQL = "CALL REGISTRAR_CONSULTA(?, ?, ?, ?)";
        
        try (
                Connection con = conexion.crearConexion();
                CallableStatement cs = con.prepareCall(comandoSQL)
            ) {

                cs.setString(1, consulta.getMotivo());
                cs.setString(2, consulta.getDiagnostico());
                cs.setString(3, consulta.getTratamiento());
                cs.setInt(4, consulta.getCita().getIdCita());
                // Ejecutar y regresar verdadero.
                cs.execute();
                return true;

        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al registrar la consulta en la base de datos.", ex);
            throw new PersistenciaException("Error al insertar una consulta", ex);
        }
    }


    /**
     * Método privado que verifica el folio de la cita de emergencia.
     * @param idCita ID de la cita de emergencia.
     * @param folio Folio de la cita.
     * @return True si el folio coincide, false en caso contrario.
     * @throws PersistenciaException 
     */
    public boolean verificarFolio(int idCita, int folio) throws PersistenciaException{

        String comandoSQL = "SELECT * FROM CITAS_EMERGENCIA WHERE ID_CITA_EMERGENCIA = ? AND FOLIO = ?;";
        try(
                Connection con = conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(comandoSQL);
            ){
                // Asignar parámetros a la sentencia.
                ps.setInt(1, idCita);
                ps.setInt(2, folio);
                // Ejecutar y regresar la coincidencia encontrada
                ResultSet resultado = ps.executeQuery();
                return resultado.next();
        // Lanzar excepción si no se pudo verificar el folio de la cita.
        } catch(SQLException ex){
            logger.log(Level.SEVERE, "Error al verificar el folio de la cita en la base de datos.", ex);
            throw new PersistenciaException("Error al verificar el folio de la cita. Intentelo de nuevo mas tarde.", ex);
        }
    }

}