package DAO;

import Entidades.Cita;
import Excepciones.PersistenciaException;

/**
 * Interfaz de la clase CitaDAO.
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public interface iCitaDAO {
    
    /**
     * Método que permite registrar una cita.
     * @param cita Objeto de tipo Cita.
     * @return True si se registra la cita, false en caso contrario.
     * @throws PersistenciaException 
     */
    public boolean registrarCita(Cita cita) throws PersistenciaException;
    
    /**
     * Método que permite cancelar una cita.
     * @param idCita ID de la cita.
     * @return True si se canceló la cita, false en caso contrario.
     * @throws PersistenciaException 
     */
    public boolean cancelarCita(int idCita) throws PersistenciaException;
}
