package DAO;

import Entidades.Cita;
import Entidades.Medico;
import Excepciones.PersistenciaException;
import java.time.LocalDateTime;
import java.util.List;

/**
 * Interfaz de la clase CitaDAO.
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public interface iCitaDAO {
    /**
     * Obtiene una lista de todos los médicos
     * que no tengan citas registradas en la hora recibida.
     * No verifica si dicha hora está dentro del horario
     * del médico; podría haber incluido eso agregado a
     * lo anterior como una vista dentro de la BD.
     * @param fechaHora Fecha y hora de la cita deseada.
     * @param especialidad Especialidad del médico.
     * @return Lista de médicos.
     * @throws PersistenciaException Excepción por si surge un error inesperado. 
     */
    public List<Medico> medicosDisponibles(LocalDateTime fechaHora, String especialidad) throws PersistenciaException;
    
    /**
     * Método que permite registrar una cita.
     * @param cita Objeto de tipo Cita.
     * @return True si se registra la cita, false en caso contrario.
     * @throws PersistenciaException Excepción por si surge un error inesperado. 
     */
    public boolean registrarCita(Cita cita) throws PersistenciaException;
    
    /**
     * Método que permite cancelar una cita.
     * @param idCita ID de la cita.
     * @return True si se canceló la cita, false en caso contrario.
     * @throws PersistenciaException Excepción por si surge un error inesperado.
     */
    public boolean cancelarCita(int idCita) throws PersistenciaException;
}