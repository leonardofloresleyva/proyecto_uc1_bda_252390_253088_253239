package DAO;

import Entidades.Consulta;
import Entidades.Paciente;
import Excepciones.PersistenciaException;
import java.time.LocalDate;
import java.util.List;

/**
 * Interfaz de la clase PacienteDAO.
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Uribe (253239)
 */
public interface iPacienteDAO {
    
    /**
     * Método que registra un nuevo paciente.
     * @param paciente Objeto de tipo Paciente.
     * @return True si se registró el paciente, false en caso contrario.
     * @throws PersistenciaException
     */
    public boolean registrarPaciente(Paciente paciente) throws PersistenciaException;
    
    /**
     * Método que permite a un paciente inciar sesión en el sistema.
     * @param correo Correo del paciente de tipo String.
     * @param contrasenia Contraseña del paciente de tipo String.
     * @return Objeto Paciente con todos sus atributos que coincidan con el
     * correo y contraseña ingresados al parámetro.
     * @throws PersistenciaException
     */
    public Paciente iniciarSesionPaciente(String correo, String contrasenia) throws PersistenciaException;
    
    /**
     * Método que cambia la contraseña de un paciente.
     * @param paciente Objeto de tipo Paciente.
     * @return True si se pudo cambiar la contraseña, False en caso contrario.
     * @throws PersistenciaException 
     */
    public boolean cambiarContrasenia(Paciente paciente) throws PersistenciaException;
    
    /**
     * Método que actualiza los atributos de un paciente.
     * @param paciente Objeto de tipo Paciente.
     * @return True si se pudieron actualizar los datos del paciente, False en
     * caso contrario.
     * @throws PersistenciaException 
     */
    public boolean actualizarPaciente(Paciente paciente) throws PersistenciaException;
    
    /**
     * Método que regresa el historial de consultas de un paciente, filtradas
     * por la especialidad el médico que lo atendió.
     * @param correo Correo del paciente de tipo String.
     * @param especialidad Especialidad del médico de tipo String.
     * @return Lista de consultas filtradas por especialidad.
     * @throws PersistenciaException 
     */
    public List<Consulta> consultarConsultasPorEspecialidad(String correo, String especialidad) throws PersistenciaException;
    
    /**
     * Método que regresa el historial de consultas de un paciente, filtradas
     * por rango entre dos fechas.
     * @param correo Correo del paciente de tipo String.
     * @param fechaInicio Fecha de inicio de tipo LocalDate.
     * @param fechaFin Fecha de fin de tipo LocalDate.
     * @return Lista de consultas filtradas por rango de fecha.
     * @throws PersistenciaException 
     */
    public List<Consulta> consultarConsultasRangoDeFechas(String correo, LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;
    
}