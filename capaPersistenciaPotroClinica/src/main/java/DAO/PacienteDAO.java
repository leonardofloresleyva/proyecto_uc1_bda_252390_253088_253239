package DAO;

import Conexion.iConexion;
import Entidades.Cita;
import Entidades.Consulta;
import Entidades.Medico;
import Entidades.Paciente;
import Excepciones.PersistenciaException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
public class PacienteDAO implements iPacienteDAO {

    iConexion conexion;
    // Logger para el registro de información importante.
    private static final Logger logger = Logger.getLogger(PacienteDAO.class.getName());
    
    public PacienteDAO(iConexion conexion) {
        this.conexion = conexion;
    }
    
    public Paciente iniciarSesion(String correo, String contrasenia){
        
    }
    
    @Override
    public boolean registrarPaciente(Paciente paciente) throws PersistenciaException {
        // Comando SQL para insertar un paciente
        String comandoSQL = "CALL REGISTRAR_PACIENTE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection con = conexion.crearConexion()) {
            try (CallableStatement cs = con.prepareCall(comandoSQL)) {

                // Agregar valores a tabla de usuarios y direcciones
                // Datos de tabla usuarios
                cs.setString(1, paciente.getUsuario());
                cs.setString(2, paciente.getContrasenia());
                // Datos de tabla pacientes
                cs.setString(3, paciente.getNombres());
                cs.setString(4, paciente.getApellidoPaterno());
                cs.setString(5, paciente.getApellidoMaterno());
                cs.setString(6, paciente.getTelefono());
                cs.setObject(7, paciente.getFechaNacimiento());
                // Datos de tabla direcciones
                cs.setString(8, paciente.getColonia());
                cs.setString(9, paciente.getCalle());
                cs.setString(10, paciente.getNumero());

                // Ejecutar y regresar verdadero
                cs.execute();   
                return true;
            }

        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
            throw new PersistenciaException("Error al registrar el paciente.", e);
        }
    }
    
    @Override
    public boolean actualizarPaciente(Paciente paciente) throws PersistenciaException{
        String sentenciaSQL = "CALL ACTUALIZAR_PACIENTE(?, ?, ?, ?, ?, ?, ?, ?, ?);";
        try(
                Connection con = conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(sentenciaSQL);
            ){
            if(verificarPaciente(paciente.getId())){
                ps.setString(1, paciente.getUsuario());
                ps.setString(2, paciente.getNombres());
                ps.setString(3, paciente.getApellidoPaterno());
                ps.setString(4, paciente.getApellidoMaterno());
                ps.setString(5, paciente.getTelefono());
                ps.setObject(6, paciente.getFechaNacimiento());
                ps.setString(7, paciente.getColonia());
                ps.setString(8, paciente.getCalle());
                ps.setString(9, paciente.getNumero());
                ps.executeUpdate();
                logger.log(Level.INFO, "Datos personales del paciente actualizados con exito en la base de datos.");
                return true;
            } else
                throw new PersistenciaException("El paciente no esta registrado en la potro clinica");
            
        } catch(SQLException ex){
            logger.log(Level.SEVERE, "Error al actualizar los datos del paciente en la base de datos.", ex);
            throw new PersistenciaException("Ha ocurrido un error al intentar actualizar los datos del paciente");
        }
    }
    
    @Override
    public boolean cambiarContrasenia(Paciente paciente) throws PersistenciaException{
        String sentenciaSQL = "";
        try(
                Connection con = conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(sentenciaSQL);
            )
        {
            ps.setString(1, paciente.getUsuario());
            ps.setString(2, paciente.getContrasenia());
            ps.executeUpdate();
            logger.log(Level.INFO, "Contrasenia actualizada con éxito en la base de datos.");
            return true;
            
        } catch(SQLException ex){
            logger.log(Level.SEVERE, "Error al cambiar la contrasenia.", ex);
            throw new PersistenciaException(ex.getMessage());
        }
        
    }
    
    @Override
    public List<Consulta> consultarConsultasPorEspecialidad(String correo, String especialidad) throws PersistenciaException {
       String comandoSQL = """
                           SELECT * FROM HISTORIAL_CONSULTAS_PACIENTES 
                           WHERE ESPECIALIDAD = ?
                           AND USUARIO = ?;""";
       List<Consulta> citasEspecialidad = new ArrayList<>();

       try (
               Connection con = conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(comandoSQL)
               ) {

                ps.setString(1, especialidad);
                ps.setString(2, correo);
                
                try (ResultSet rs = ps.executeQuery()) { // Ejecutamos la consulta y obtenemos los resultados.
                    while (rs.next()) {
                        // Se obtienen los datos relevantes del medico en una entidad.
                        Medico medico = new Medico(
                                "",
                                "",
                                "Medico",
                                rs.getString("NOMBRE_MEDICO"),
                                rs.getString("APELLIDO_PATERNO_MEDICO"),
                                rs.getString("APELLIDO_MATERNO_MEDICO"),
                                rs.getString("ESPECIALIDAD"),
                                rs.getString("ESTADO_MEDICO")
                        );
                        // Se crea una entidad paciente null, ya que no es relevante para la consulta.
                        Paciente paciente = new Paciente();
                        // Se almacenan los datos relevantes de la cita en una entidad cita.
                        Cita cita = new Cita(
                                rs.getTimestamp("FECHA_HORA").toLocalDateTime(),
                                medico,
                                paciente,
                                rs.getString("TIPO_CITA")
                        );
                        // Se almacenan los datos relevantes de la consulta en una entidad consulta.
                        Consulta consulta = new Consulta(rs.getString("MOTIVO"), rs.getString("DIAGNOSTICO"), rs.getString("TRATAMIENTO"), cita);

                        // Añadir cada consulta obtenida a la lista.
                        citasEspecialidad.add(consulta);
                    }
           }
        } catch (SQLException ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, "Error al consultar citas por especialidad", ex);
            throw new PersistenciaException("Error al consultar citas por especialidad.", ex);
        }
       // Regresar la lista de consultas obtenidas.
        return citasEspecialidad; 
    }

    @Override
    public List<Consulta> consultarConsultasRangoDeFechas(String correo, LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException {
        String comandoSQL = """
                            SELECT * FROM HISTORIAL_CONSULTAS_PACIENTES
                            WHERE DATE(FECHA_HORA) BETWEEN ? AND ? AND USUARIO = ? 
                            ORDER BY FECHA_HORA DESC;""";
        List<Consulta> citasRangoFechas = new ArrayList<>();
        
        try (Connection con = conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(comandoSQL)) {
            
            ps.setObject(1, fechaInicio);
            ps.setObject(2, fechaFin);
            ps.setString(3, correo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Se obtienen los datos relevantes del medico en una entidad.
                        Medico medico = new Medico(
                                "",
                                "",
                                "Medico",
                                rs.getString("NOMBRE_MEDICO"),
                                rs.getString("APELLIDO_PATERNO_MEDICO"),
                                rs.getString("APELLIDO_MATERNO_MEDICO"),
                                rs.getString("ESPECIALIDAD"),
                                rs.getString("ESTADO_MEDICO")
                        );
                        // Se crea una entidad paciente null, ya que no es relevante para la consulta.
                        Paciente paciente = new Paciente();
                        // Se almacenan los datos relevantes de la cita en una entidad cita.
                        Cita cita = new Cita(
                                rs.getTimestamp("FECHA_HORA").toLocalDateTime(),
                                medico,
                                paciente,
                                rs.getString("TIPO_CITA")
                        );
                        // Se almacenan los datos relevantes de la consulta en una entidad consulta.
                        Consulta consulta = new Consulta(rs.getString("MOTIVO"), rs.getString("DIAGNOSTICO"), rs.getString("TRATAMIENTO"), cita);
                    citasRangoFechas.add(consulta);
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(CitaDAO.class.getName()).log(Level.SEVERE, "Error al consultar citas por rango de fechas.", ex);
            throw new PersistenciaException("Error al consultar citas por rango de fechas entre: " + fechaInicio + " y " + fechaFin, ex);
        }
        // Regresar la lista generada.
        return citasRangoFechas;
    }
    
    private boolean verificarPaciente(int id) throws PersistenciaException{
        String SentenciaSQL = "SELECT * FROM PACIENTES WHERE ID_PACIENTE = ?";
        try(
                Connection con = conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(SentenciaSQL);
        ){
            ps.setInt(1, id);
            ResultSet resultado = ps.executeQuery();
            return resultado.next();
            
        } catch(SQLException ex){
            logger.log(Level.SEVERE, "Error al verificar el paciente en la base de datos.", ex);
            throw new PersistenciaException("Error al verificar el paciente. Inténtelo de nuevo mas tarde.");
        }
    }
}
