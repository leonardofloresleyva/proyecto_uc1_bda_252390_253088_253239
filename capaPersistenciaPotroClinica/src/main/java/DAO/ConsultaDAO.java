package DAO;

import Conexion.iConexion;
import Entidades.Cita;
import Entidades.CitaEmergencia;
import Entidades.Consulta;
import Entidades.Medico;
import Entidades.Paciente;
import Entidades.Usuario;
import Excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public List<Consulta> consultasPorEspecialidad(String especialidad) throws PersistenciaException {
        List<Consulta> consultasEspecialidad = new ArrayList<>();
        String comandoSQL = "SELECT * FROM HISTORIAL_CONSULTAS_PACIENTES WHERE ESPECIALIDAD = ?";
        
        try (Connection con = conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(comandoSQL)) {
            
            // Agregar parámetro a la llamada
            ps.setString(1, especialidad);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Crear objeto Medico
                    Medico medico = new Medico(
                        rs.getInt("ID"),
                        rs.getString("USUARIO"),
                        rs.getString("CONTRASENIA"),
                        rs.getString("ROL"),
                        rs.getString("NOMBRES"),
                        rs.getString("APELLIDO_PATERNO"),
                        rs.getString("APELLIDO_MATERNO"),
                        rs.getString("ESPECIALIDAD"),
                        rs.getString("ESTADO")
                    );
                    // Crear objeto Paciente
                    Paciente paciente = new Paciente(
                        rs.getInt("ID"),
                        rs.getString("USUARIO"),
                        rs.getString("CONTRASENIA"),
                        rs.getString("ROL"),
                        rs.getString("NOMBRES"),
                        rs.getString("APELLIDO_PATERNO"),
                        rs.getString("APELLIDO_MATERNO"),
                        rs.getString("TELEFONO"),
                        rs.getDate("FECHA_NACIMIENTO").toLocalDate(),
                        rs.getString("ESTADO"),
                        rs.getString("COLONIA"),
                        rs.getString("CALLE"),
                        rs.getString("NUMERO")
                    );
                    // Crear una cita con los datos necesarios
                    Cita cita = new Cita(
                            rs.getInt("ID_CITA"),
                            rs.getTimestamp("FECHA_HORA").toLocalDateTime(),
                            medico,
                            paciente,
                            rs.getString("TIPO_CITA")
                    );
                    // Crear la consulta y agregar a la lista
                    consultasEspecialidad.add(new Consulta(
                    rs.getInt("ID_CONSULTA"),
                    rs.getString("MOTIVO"),
                    rs.getString("DIAGNOSTICO"),
                    rs.getString("TRATAMIENTO"),
                    cita
                    ));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, "Error al obtener consultas por especialidad: " + especialidad, ex);
            throw new PersistenciaException("Error al obtener consultas por especialidad: " + especialidad, ex);
        }
        
        return consultasEspecialidad;
    }

    @Override
    public List<Consulta> consultasRangoDeFechas(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException {
        List<Consulta> consultasRangoFechas = new ArrayList<>();
        String comandoSQL = "SELECT * FROM HISTORIAL_CONSULTAS_PACIENTES WHERE FECHA_HORA BETWEEN ? AND ?";
        
        try (Connection con = conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(comandoSQL)) {
            // Agregar parámetro a la llamada
            ps.setObject(1, fechaInicio);
            ps.setObject(2, fechaFin);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Crear objeto Medico
                    Medico medico = new Medico(
                        rs.getInt("ID"),
                        rs.getString("USUARIO"),
                        rs.getString("CONTRASENIA"),
                        rs.getString("ROL"),
                        rs.getString("NOMBRES"),
                        rs.getString("APELLIDO_PATERNO"),
                        rs.getString("APELLIDO_MATERNO"),
                        rs.getString("ESPECIALIDAD"),
                        rs.getString("ESTADO")
                    );
                    // Crear objeto Paciente
                    Paciente paciente = new Paciente(
                        rs.getInt("ID"),
                        rs.getString("USUARIO"),
                        rs.getString("CONTRASENIA"),
                        rs.getString("ROL"),
                        rs.getString("NOMBRES"),
                        rs.getString("APELLIDO_PATERNO"),
                        rs.getString("APELLIDO_MATERNO"),
                        rs.getString("TELEFONO"),
                        rs.getDate("FECHA_NACIMIENTO").toLocalDate(),
                        rs.getString("ESTADO"),
                        rs.getString("COLONIA"),
                        rs.getString("CALLE"),
                        rs.getString("NUMERO")
                    );
                    // Crear una cita con los datos necesarios
                    Cita cita = new Cita(
                            rs.getInt("ID_CITA"),
                            rs.getTimestamp("FECHA_HORA").toLocalDateTime(),
                            medico,
                            paciente,
                            rs.getString("TIPO_CITA")
                    );
                    // Crear la consulta y agregar a la lista
                    consultasRangoFechas.add(new Consulta(
                    rs.getInt("ID_CONSULTA"),
                    rs.getString("MOTIVO"),
                    rs.getString("DIAGNOSTICO"),
                    rs.getString("TRATAMIENTO"),
                    cita
                    ));
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaDAO.class.getName()).log(Level.SEVERE, "Error al obtener consultas en el periodo de " + fechaInicio + " y " + fechaFin, ex);
            throw new PersistenciaException("Error al obtener consultas en el periodo de " + fechaInicio + " y " + fechaFin, ex);
        }
        
        return consultasRangoFechas;
    }
}