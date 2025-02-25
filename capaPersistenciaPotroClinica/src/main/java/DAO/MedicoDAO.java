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
public class MedicoDAO implements iMedicoDAO {
    iConexion conexion;
    // Logger para el registro de información importante.
    private static final Logger logger = Logger.getLogger(PacienteDAO.class.getName());
    
    public MedicoDAO(iConexion conexion) {
        this.conexion = conexion;
    }
    
    @Override
    public Medico iniciarSesionMedico(String cedula, String contrasenia) throws PersistenciaException{
        String sentenciaSQL = """
                              SELECT
                                ID,
                                NOMBRES,
                                APELLIDO_PATERNO,
                                APELLIDO_MATERNO,
                                ESPECIALIDAD,
                                ESTADO
                              FROM DATOS_MEDICO
                              WHERE USUARIO = ? AND CONTRASENIA = ?;""";
        
        if(verificarMedico(cedula, contrasenia)){
            try(
                    Connection con = conexion.crearConexion();
                    PreparedStatement ps = con.prepareStatement(sentenciaSQL);
                ){
                    ps.setString(1, cedula);
                    ps.setString(2, contrasenia);
                    ResultSet resultado = ps.executeQuery();
                    if(resultado.next()){
                        return new Medico(
                                resultado.getInt("ID"),
                                cedula,
                                contrasenia,
                                resultado.getString("NOMBRES"),
                                resultado.getString("APELLIDO_PATERNO"),
                                resultado.getString("APELLIDO_MATERNO"),
                                resultado.getString("ESPECIALIDAD"),
                                resultado.getString("ESTADO")
                        );
                    } else
                        throw new PersistenciaException("Medico no encontrado.");

            } catch(SQLException ex){
                logger.log(Level.SEVERE, "Error al iniciar sesion en la base de datos.", ex);
                throw new PersistenciaException("Error al iniciar sesion. Intentelo de nuevo mas tarde.", ex);
            }
        } else
            throw new PersistenciaException("Medico no encontrado.");
    }
    
    @Override
    public boolean darDeBajaMedico(int id) throws PersistenciaException {
        String comandoSQL = "CALL DAR_BAJA_MEDICO(?)";
        
        try (Connection con = conexion.crearConexion()) {
            try (CallableStatement cs = con.prepareCall(comandoSQL)) {
                // Agregar parámetro a la llamada
                cs.setInt(1, id);
                
                // Ejecutar y regresar verdadero
                cs.execute();
                return true;
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al dar de baja al médico.", ex);
            throw new PersistenciaException("Error al dar de baja al médico.", ex);
        }
    }

    @Override
    public boolean darDeAltaMedico(int id) throws PersistenciaException {
        String comandoSQL = "CALL DAR_ALTA_MEDICO(?)";
        
        try (Connection con = conexion.crearConexion()) {
            try (CallableStatement cs = con.prepareCall(comandoSQL)) {
                // Agregar parámetro a la llamada
                cs.setInt(1, id);
                
                // Ejecutar y regresar verdadero
                cs.execute();
                return true;
            }
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al dar de alta al médico.", ex);
            throw new PersistenciaException("Error al dar de alta al médico.", ex);
        }
    }

    @Override
    public List<Cita> mostrarAgendaMedico(int id) throws PersistenciaException {
        List<Cita> agendaMedico = new ArrayList<>();
        String comandoSQL = "SELECT * FROM AGENDA_CITAS_MEDICO WHERE ID_MEDICO = ?";
        
        try (Connection con = conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(comandoSQL)) {
            // Agregar parámetro a la llamada
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { // Ejecutar para obtener los resultados
                while (rs.next()) {
                    // Crear entidad Medico null porque no es relevante para la agenda
                    Medico medico = new Medico();
                    // Obtener datos relevantes del paciente
                    Paciente paciente = new Paciente(
                            rs.getInt("ID_PACIENTE"),
                            "",
                            "",
                            rs.getString("NOMBRES_PACIENTE"),
                            rs.getString("APELLIDO_PATERNO_PACIENTE"),
                            rs.getString("APELLIDO_MATERNO_PACIENTE"),
                            "",
                            rs.getDate("FECHA_NACIMIENTO_PACIENTE").toLocalDate(),
                            rs.getString("ESTADO_PACIENTE"),
                            "",
                            "",
                            ""
                    );
                    // Almacenar los datos relevantes de la cita
                    Cita cita = new Cita(
                            rs.getInt("ID_CITA"),
                            rs.getTimestamp("FECHA_HORA").toLocalDateTime(),
                            medico,
                            paciente,
                            rs.getString("TIPO_CITA")
                    );
                    // Añadir objeto cita a la lista creada
                    agendaMedico.add(cita);
                }
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al consultar agenda del médico.", ex);
            throw new PersistenciaException("Error al consultar agenda del médico.", ex);
        }
        
        return agendaMedico;
    }

    @Override
    public List<Consulta> consultarConsultasMedico(int id) throws PersistenciaException {
        String comandoSQL = """
                            SELECT * FROM HISTORIAL_CONSULTAS_PACIENTES
                            WHERE ID_MEDICO = ? 
                            ORDER BY NOMBRE_PACIENTE DESC;""";
        List<Consulta> consultasMedico = new ArrayList<>();
        
        try (Connection con = conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(comandoSQL)) {
            ps.setInt(1, id);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Se obtienen los datos relevantes del medico en una entidad.
                    Medico medico = new Medico();
                    // Se crea una entidad paciente null, ya que no es relevante para la consulta.
                    Paciente paciente = new Paciente(
                            "",
                            "",
                            rs.getString("NOMBRE_PACIENTE"),
                            rs.getString("APELLIDO_PATERNO_PACIENTE"),
                            rs.getString("APELLIDO_MATERNO_PACIENTE"),
                            "",
                            rs.getObject("FECHA_NACIMIENTO", LocalDate.class),
                            "",
                            "",
                            "",
                            ""
                    );
                    // Se almacenan los datos relevantes de la cita en una entidad cita.
                    Cita cita = new Cita(
                            rs.getTimestamp("FECHA_HORA").toLocalDateTime(),
                            medico,
                            paciente,
                            rs.getString("TIPO_CITA")
                    );
                    // Se almacenan los datos relevantes de la consulta en una entidad consulta.
                    Consulta consulta = new Consulta(rs.getString("MOTIVO"), rs.getString("DIAGNOSTICO"), rs.getString("TRATAMIENTO"), cita);
                    // Añadir consulta a la lista
                    consultasMedico.add(consulta);
                }
            }
            
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al obtener consultas.", ex);
            throw new PersistenciaException("Error al obtener consultas.", ex);
        }
        // Regresar lista
        return consultasMedico;
    }
    
    private boolean verificarMedico(String cedula, String contrasenia) throws PersistenciaException{
        String consultaSQL = "SELECT * FROM USUARIOS WHERE USUARIO = ? AND CONTRASENIA = ?";

        try (
                Connection con = conexion.crearConexion(); 
                PreparedStatement ps = con.prepareStatement(consultaSQL);
            ) {
            ps.setString(1, cedula);
            ps.setString(2, contrasenia);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Si hay resultados, las credenciales son válidas
            }
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al validar paciente en la base de datos.", ex);
            throw new PersistenciaException("Error al validar paciente.", ex);
        }
    }
    
}