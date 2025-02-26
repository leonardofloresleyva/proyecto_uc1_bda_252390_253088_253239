package DAO;

import Conexion.iConexion;
import Entidades.CitaEmergencia;
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
<<<<<<< Updated upstream
            // Si el tipo de cita es "Previa", se asignan los valores de la consulta
                if("Previa".equals(consulta.getCita().getTipoCita())){
                    // Agregar valores a la tabla de consultas
=======
                // Agregar valores a la tabla de consultas
>>>>>>> Stashed changes
                    cs.setString(1, consulta.getMotivo());
                    cs.setString(2, consulta.getDiagnostico());
                    cs.setString(3, consulta.getTratamiento());
                    cs.setInt(4, consulta.getCita().getIdCita());
                    // Ejecutar y regresar verdadero.
                    cs.execute();
                    return true;
<<<<<<< Updated upstream
                } else{
                // Si el tipo de cita es "Emergencia", se asignan valores de la consulta
                    CitaEmergencia citaEmergencia = (CitaEmergencia) consulta.getCita();
                    // Se llama al método privado que verifica el folio de la cita. Si es válido y coincide, se agrega la consulta.
                    if(verificarFolio(citaEmergencia.getIdCita(), citaEmergencia.getFolio())){
                        // Agregar valores a la tabla de consultas
                        cs.setString(1, consulta.getMotivo());
                        cs.setString(2, consulta.getDiagnostico());
                        cs.setString(3, consulta.getTratamiento());
                        cs.setInt(4, citaEmergencia.getIdCita());
                        // Ejecutar y regresar verdadero.
                        cs.execute();
                        return true;
                    // Si el folio no coincide, se lanza una excepción.
                    } else
                        throw new PersistenciaException("El folio ingresado no coincide con ninguna cita.");
                }
        // Lanzar una excepción si no se pudo registrar la consulta.
=======
            
>>>>>>> Stashed changes
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al registrar la consulta en la base de datos.", ex);
            throw new PersistenciaException("Error al insertar una consulta", ex);
        }
    }

<<<<<<< Updated upstream
    /**
     * Método privado que verifica el folio de la cita de emergencia.
     * @param idCita ID de la cita de emergencia.
     * @param folio Folio de la cita.
     * @return True si el folio coincide, false en caso contrario.
     * @throws PersistenciaException 
     */
    private boolean verificarFolio(int idCita, int folio) throws PersistenciaException{
=======
    public boolean verificarFolio(int idCita, int folio) throws PersistenciaException{
>>>>>>> Stashed changes
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