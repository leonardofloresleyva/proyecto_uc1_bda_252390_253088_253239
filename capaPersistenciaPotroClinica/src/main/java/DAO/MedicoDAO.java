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
 * Clase que implementa los métodos de iMedicoDAO.
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class MedicoDAO implements iMedicoDAO {
    iConexion conexion;
    // Logger para el registro de información importante.
    private static final Logger logger = Logger.getLogger(PacienteDAO.class.getName());
    
    /**
     * Constructor para la clase MedicoDAO.
     * @param conexion Objeto de la conexión a MySQL.
     */
    public MedicoDAO(iConexion conexion) {
        this.conexion = conexion;
    }
    
    /**
     * Método que permite a un médico iniciar sesión en el sistema.
     * @param cedula Cédula del médico de tipo String.
     * @param contrasenia Contraseña del médico de tipo String.
     * @return Objeto médico.
     * @throws PersistenciaException 
     */
    @Override
    public Medico iniciarSesionMedico(String cedula, String contrasenia) throws PersistenciaException{
        // Declarar consulta de MySQL para obtener al médico que coincida con los parámetros.
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
        // Llamar al método privado para verificar al médico por su cedula y contraseña. Si se encontró, se crea la conexión y se ejecuta PreparedStatement.
        if(verificarMedico(cedula, contrasenia)){
            try(
                    Connection con = conexion.crearConexion();
                    PreparedStatement ps = con.prepareStatement(sentenciaSQL);
                ){
                // Asignar valores a los parámetros.
                    ps.setString(1, cedula);
                    ps.setString(2, contrasenia);
                    ResultSet resultado = ps.executeQuery(); // Ejecutar para obtener los datos.
                    // Si se encontró coincidencia, regresa al médico con sus atributos encontrados.
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
                    // Si no se encontró, se lanza excepción.
                    } else
                        throw new PersistenciaException("Medico no encontrado.");
            // Lanzar excepción si ocurrió un error al iniciar sesión.
            } catch(SQLException ex){
                logger.log(Level.SEVERE, "Error al iniciar sesion en la base de datos.", ex);
                throw new PersistenciaException("Error al iniciar sesion. Intentelo de nuevo mas tarde.", ex);
            }
        } else
            throw new PersistenciaException("Medico no encontrado.");
    }
    
    /**
     * Método que permite dar de baja a un médico.
     * @param id ID del médico.
     * @return True si se dio de baja el médico, false en caso contrario.
     * @throws PersistenciaException
     */
    @Override
    public boolean darDeBajaMedico(int id) throws PersistenciaException {
        // Comando SQL para llamar al procedimiento almacenado.
        String comandoSQL = "CALL DAR_BAJA_MEDICO(?)";
        
        try (Connection con = conexion.crearConexion()) {
            try (CallableStatement cs = con.prepareCall(comandoSQL)) {
                // Agregar parámetro a la llamada
                cs.setInt(1, id);
                
                // Ejecutar y regresar verdadero
                cs.execute();
                return true;
            }
        // Lanzar excepción si no se pudo dar de baja el médico.
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al dar de baja al médico.", ex);
            throw new PersistenciaException("Error al dar de baja al médico.", ex);
        }
    }

    /**
     * Método que permite dar de alta a un médico.
     * @param id ID del médico.
     * @return True si se dio de alta el médico, false en caso contrario.
     * @throws PersistenciaException 
     */
    @Override
    public boolean darDeAltaMedico(int id) throws PersistenciaException {
        // Comando SQL para llamar al procedimiento almacenado.
        String comandoSQL = "CALL DAR_ALTA_MEDICO(?)";
        
        try (Connection con = conexion.crearConexion()) {
            try (CallableStatement cs = con.prepareCall(comandoSQL)) {
                // Agregar parámetro a la llamada
                cs.setInt(1, id);
                
                // Ejecutar y regresar verdadero
                cs.execute();
                return true;
            }
        // Lanzar excepción si no se pudo dar de alta el médico.
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al dar de alta al médico.", ex);
            throw new PersistenciaException("Error al dar de alta al médico.", ex);
        }
    }

    /**
     * Método que muestra la agenda del médico.
     * @param id ID del médico.
     * @return Lista de citas que el médico tiene programadas.
     * @throws PersistenciaException 
     */
    @Override
    public List<Cita> mostrarAgendaMedico(int id) throws PersistenciaException {
        // Crear lista para almacenar las citas encontradas para el médico.
        List<Cita> agendaMedico = new ArrayList<>();
        String comandoSQL = "SELECT * FROM AGENDA_CITAS_MEDICO WHERE ID_MEDICO = ?";
        
        try (Connection con = conexion.crearConexion();
                PreparedStatement ps = con.prepareStatement(comandoSQL)) {
            // Agregar parámetro a la llamada
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) { // Ejecutar para obtener los resultados
                while (rs.next()) {
                    // Crear entidad Medico null porque no es relevante para la agenda
                    Medico medico = new Medico(
                            id,
                            "",
                            "",
                            "",
                            "",
                            "",
                            "",
                            ""
                    );
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
        // Lanzar excepción si no se encontraron coincidencias para el médico.
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al consultar agenda del médico.", ex);
            throw new PersistenciaException("Error al consultar agenda del médico.", ex);
        }
        // Regresar la agenda del médico.
        return agendaMedico;
    }

    /**
     * Método que muestra el historial de consultas del médico.
     * @param id ID del médico.
     * @return Lista de consultas que el médico ha realizado.
     * @throws PersistenciaException 
     */
    @Override
    public List<Consulta> consultarConsultasMedico(int id) throws PersistenciaException {
        String comandoSQL = """
                            SELECT * FROM HISTORIAL_CONSULTAS_PACIENTES
                            WHERE ID_MEDICO = ? 
                            ORDER BY NOMBRE_PACIENTE DESC;""";
        // Crear lista para almacenar las consultas del médico.
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
        // Lanzar excepción si no se encontraron coincidencias para el médico.   
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al obtener consultas.", ex);
            throw new PersistenciaException("Error al obtener consultas.", ex);
        }
        // Regresar lista
        return consultasMedico;
    }
    
    /**
     * Método privado que verifica la cédula y contraseña del médico.
     * @param cedula Cédula del médico.
     * @param contrasenia Contraseña del médico.
     * @return True si los parámetros son válidos, false en caso contrario.
     * @throws PersistenciaException 
     */
    private boolean verificarMedico(String cedula, String contrasenia) throws PersistenciaException{
        String consultaSQL = "SELECT * FROM USUARIOS WHERE USUARIO = ? AND CONTRASENIA = ?";

        try (
                Connection con = conexion.crearConexion(); 
                PreparedStatement ps = con.prepareStatement(consultaSQL);
            ) {
            // Agregar parámetros a la sentencia.
            ps.setString(1, cedula);
            ps.setString(2, contrasenia);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Si hay resultados, las credenciales son válidas
            }
        // Lanzar excepción si no se validaron los datos del médico.
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al validar paciente en la base de datos.", ex);
            throw new PersistenciaException("Error al validar paciente.", ex);
        }
    }
}