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
 * Clase que implementa los métodos de iPacienteDAO.
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class PacienteDAO implements iPacienteDAO {
    // Objeto de la interfaz iConexión.
    iConexion conexion;
    // Logger para el registro de información importante.
    private static final Logger logger = Logger.getLogger(PacienteDAO.class.getName());

    /**
     * Constructor para la clase PacienteDAO.
     * @param conexion Objeto de la conexión a MySQL.
     */
    public PacienteDAO(iConexion conexion) {
        this.conexion = conexion;
    }

    /**
     * Método que permite iniciar sesión al paciente.
     * @param correo Correo del paciente.
     * @param contrasenia Contraseña del paciente.
     * @return Objeto paciente obtenido.
     * @throws PersistenciaException 
     */
    @Override
    public Paciente iniciarSesionPaciente(String correo, String contrasenia) throws PersistenciaException {
        // Declarar consulta de MySQL para obtener al paciente que coincida con los parámetros.
        String consultaSQL = """
                         SELECT ID, NOMBRES, APELLIDO_PATERNO, APELLIDO_MATERNO, TELEFONO,
                         FECHA_NACIMIENTO, ESTADO, COLONIA, CALLE, NUMERO
                         FROM DATOS_PACIENTE
                         WHERE USUARIO = ? AND CONTRASENIA = ?;""";
        
        // Crear la conexión a la base de datos. Ejecutar la consulta SQL con PreparedStatement.
        try (Connection con = conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL)) {
            // Establecer los parámetros de la consulta
            ps.setString(1, correo);
            ps.setString(2, contrasenia);
            ResultSet resultado = ps.executeQuery(); // Obtener el resultado de la llamada al procedimiento almacenado
            
            // Si se encuentran coincidencias en la base de datos, devuelve al Paciente y sus atributos encontrados que coinciden con los parámetros.
            if (resultado.next()) {
                return new Paciente(
                        resultado.getInt("ID"),
                        correo,
                        contrasenia, // Ahora la contraseña se compara directamente
                        resultado.getString("NOMBRES"),
                        resultado.getString("APELLIDO_PATERNO"),
                        resultado.getString("APELLIDO_MATERNO"),
                        resultado.getString("TELEFONO"),
                        resultado.getObject("FECHA_NACIMIENTO", LocalDate.class),
                        resultado.getString("ESTADO"),
                        resultado.getString("COLONIA"),
                        resultado.getString("CALLE"),
                        resultado.getString("NUMERO")
                );
            // Si no se encuentran coincidencias, se asume que el correo o la contraseña introducidos son incorrectos.
            } else {
                throw new PersistenciaException("Correo o contraseña incorrectos.");
            }
        // Lanzar excepción si hubo un error al inciar sesión.
        } catch (SQLException ex) {
            throw new PersistenciaException("Error al iniciar sesión.", ex);
        }
    }

    /**
     * Método que registra un nuevo paciente.
     * @param paciente Objeto de tipo Paciente.
     * @return True si se registró el paciente, false en caso contrario.
     * @throws PersistenciaException 
     */
    @Override
    public boolean registrarPaciente(Paciente paciente) throws PersistenciaException {
        // Comando SQL para insertar un paciente
        String comandoSQL = "CALL REGISTRAR_PACIENTE(?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        // Crear conexión a la base de datos.
        try (Connection con = conexion.crearConexion()) {
            // Usar CallableStatement ya que se está llamando a un procedimiento almacenado de la base de datos.
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
        // Lanzar excepción si no se registró el paciente.
        } catch (Exception e) {
            logger.log(Level.SEVERE, null, e);
            throw new PersistenciaException("Error al registrar el paciente.", e);
        }
    }

        /**
         * Método que actualiza los datos de un paciente.
         * @param paciente Objeto de tipo Paciente.
         * @return True si se actualizó el paciente, false en caso contrario.
         * @throws PersistenciaException 
         */
        @Override
        public boolean actualizarPaciente(Paciente paciente) throws PersistenciaException {
            // Comando SQL para actualizar los datos de un paciente.
            String sentenciaSQL = "UPDATE PACIENTES SET nombres = ?, apellido_paterno = ?, apellido_materno = ?, telefono = ?, fecha_nacimiento = ? WHERE id_paciente = ?;";
            try (
                    // Crear conexión. Ejecutar la consulta SQL con PreparedStatement.
                    Connection con = conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(sentenciaSQL);) {
                // Se llama al método privado para verificar al paciente según su correo y contraseña, si este logra ejecutarse, se actualizan los datos del paciente.
                if (verificarPaciente(paciente.getUsuario(), paciente.getContrasenia())) {
                    ps.setString(1, paciente.getNombres());
                    ps.setString(2, paciente.getApellidoPaterno());
                    ps.setString(3, paciente.getApellidoMaterno());
                    ps.setString(4, paciente.getTelefono());
                    ps.setObject(5, paciente.getFechaNacimiento());
                    ps.setInt(6, paciente.getId()); // Asignar id del paciente.
                    ps.executeUpdate(); // Guardar los datos.
                    // Regresar verdadero al actualizar el paciente.
                    logger.log(Level.INFO, "Datos del paciente actualizados con éxito en la base de datos.");
                    return true;
                // En caso contrario, se indica que no se encontró al paciente dentro de la base de datos.
                } else {
                    throw new PersistenciaException("El paciente no está registrado en la Potro Clínica");
                }
            // Lanzar excepción si no se pudieron actualizar los datos.
            } catch (SQLException ex) {
                logger.log(Level.SEVERE, "Error al actualizar los datos del paciente en la base de datos.", ex);
                throw new PersistenciaException("Ha ocurrido un error al intentar actualizar los datos del paciente");
            }
        }

    /**
     * Método que permite cambiar la contraseña de un paciente.
     * @param paciente Objeto de tipo Paciente.
     * @return True si se pudo cambiar la contraseña, false en caso contrario.
     * @throws PersistenciaException 
     */
    @Override
    public boolean cambiarContrasenia(Paciente paciente) throws PersistenciaException {
        // Comando SQL para actualizar la contraseña.
        String sentenciaSQL = "UPDATE USUARIOS SET CONTRASENIA = ? WHERE USUARIO = ?";
        try (
                // Crear conexión. Ejecutar la sentencia con PreparedStatement.
                Connection con = conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(sentenciaSQL);) {
            // Obtener los parámetros.
            ps.setString(1, paciente.getUsuario());
            ps.setString(2, paciente.getContrasenia());
            ps.executeUpdate(); // Guardar la nueva contraseña.
            logger.log(Level.INFO, "Contrasenia actualizada con éxito en la base de datos.");
            return true;
        // Lanzar excepción si no se pudo cambiar la contraseña.
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al cambiar la contrasenia.", ex);
            throw new PersistenciaException(ex.getMessage());
        }
    }

    /**
     * Método que regresa el historial de consultas de un paciente, filtradas
     * por la especialidad el médico que lo atendió.
     * @param correo Correo del paciente.
     * @param especialidad Especialidad del médico.
     * @return Lista de las consultas filtradas.
     * @throws PersistenciaException 
     */
    @Override
    public List<Consulta> consultarConsultasPorEspecialidad(String correo, String especialidad) throws PersistenciaException {
        String comandoSQL = """
                           SELECT * FROM HISTORIAL_CONSULTAS_PACIENTES 
                           WHERE ESPECIALIDAD = ?
                           AND USUARIO = ?;""";
        List<Consulta> citasEspecialidad = new ArrayList<>();

        try (
                Connection con = conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(comandoSQL)) {

            ps.setString(1, especialidad);
            ps.setString(2, correo);

            try (ResultSet rs = ps.executeQuery()) { // Ejecutamos la consulta y obtenemos los resultados.
                while (rs.next()) {
                    // Se obtienen los datos relevantes del medico en una entidad.
                    Medico medico = new Medico(
                            "",
                            "",
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

    /**
     * Método que regresa el historial de consultas de un paciente, filtradas
     * por rango entre dos fechas.
     * @param correo Correo del paciente.
     * @param fechaInicio Fecha de inicio.
     * @param fechaFin Fecha de fin.
     * @return Lista de consultas filtradas.
     * @throws PersistenciaException 
     */
    @Override
    public List<Consulta> consultarConsultasRangoDeFechas(String correo, LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException {
        String comandoSQL = """
                            SELECT * FROM HISTORIAL_CONSULTAS_PACIENTES
                            WHERE DATE(FECHA_HORA) BETWEEN ? AND ? AND USUARIO = ? 
                            ORDER BY FECHA_HORA DESC;""";
        List<Consulta> citasRangoFechas = new ArrayList<>();

        try (Connection con = conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(comandoSQL)) {

            ps.setObject(1, fechaInicio);
            ps.setObject(2, fechaFin);
            ps.setString(3, correo);
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    // Se obtienen los datos relevantes del medico en una entidad.
                    Medico medico = new Medico(
                            "",
                            "",
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

    /**
     * Método privado que verifica el correo y contraseña del paciente.
     * @param usuario Correo del paciente.
     * @param contrasenia Contraseña del paciente.
     * @return True si los parámetros son válidos, false en caso contrario.
     * @throws PersistenciaException 
     */
    private boolean verificarPaciente(String usuario, String contrasenia) throws PersistenciaException {
        String consultaSQL = "SELECT * FROM USUARIOS WHERE USUARIO = ? AND CONTRASENIA = ?";

        try (
                Connection con = conexion.crearConexion(); PreparedStatement ps = con.prepareStatement(consultaSQL);) {
            // Agregar parámetros a la sentencia.
            ps.setString(1, usuario);
            ps.setString(2, contrasenia);

            try (ResultSet rs = ps.executeQuery()) {
                return rs.next(); // Si hay resultados, las credenciales son válidas
            }
        // Lanzar excepción si no se validaron los datos del paciente.
        } catch (SQLException ex) {
            logger.log(Level.SEVERE, "Error al validar paciente en la base de datos.", ex);
            throw new PersistenciaException("Error al validar paciente.", ex);
        }
    }
}