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
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class ConsultaDAO implements iConsultaDAO {
    iConexion conexion;
    // Logger para el registro de información importante.
    private static final Logger logger = Logger.getLogger(PacienteDAO.class.getName());

    public ConsultaDAO(iConexion conexion) {
        this.conexion = conexion;
    }
    
    @Override
    public boolean registrarConsulta(Consulta consulta) throws PersistenciaException {
        // Sentencia de MySQL
        String comandoSQL = "CALL REGISTRAR_CONSULTA(?, ?, ?, ?)";
        
        try (
                Connection con = conexion.crearConexion();
                CallableStatement cs = con.prepareCall(comandoSQL)
            ) {
                if("Previa".equals(consulta.getCita().getTipoCita())){
                    // Agregar valores a la tabla de consultas
                    cs.setString(1, consulta.getMotivo());
                    cs.setString(2, consulta.getDiagnostico());
                    cs.setString(3, consulta.getTratamiento());
                    cs.setInt(4, consulta.getCita().getIdCita());

                    cs.execute();
                    return true;
                } else{
                    CitaEmergencia citaEmergencia = (CitaEmergencia) consulta.getCita();
                    if(verificarFolio(citaEmergencia.getIdCita(), citaEmergencia.getFolio())){
                        // Agregar valores a la tabla de consultas
                        cs.setString(1, consulta.getMotivo());
                        cs.setString(2, consulta.getDiagnostico());
                        cs.setString(3, consulta.getTratamiento());
                        cs.setInt(4, citaEmergencia.getIdCita());

                        cs.execute();
                        return true;
                    } else
                        throw new PersistenciaException("El folio ingresado no coincide con ninguna cita.");
                }
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al registrar la cita en la base de datos.", ex);
            throw new PersistenciaException("Error al insertar una consulta", ex);
        }
    }

    private boolean verificarFolio(int idCita, int folio) throws PersistenciaException{
        String comandoSQL = "SELECT * FROM CITAS_EMERGENCIA WHERE ID_CITA_EMERGENCIA = ? AND FOLIO = ?;";
        try(
                Connection con = conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(comandoSQL);
            ){
                ps.setInt(1, idCita);
                ps.setInt(2, folio);
                ResultSet resultado = ps.executeQuery();
                return resultado.next();
            
        } catch(SQLException ex){
            logger.log(Level.SEVERE, "Error al verificar el folio de la cita en la base de datos.", ex);
            throw new PersistenciaException("Error al verificar el folio de la cita. Intentelo de nuevo mas tarde.", ex);
        }
    }
}