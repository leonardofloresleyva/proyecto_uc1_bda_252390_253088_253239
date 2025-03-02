package DAO;

import Entidades.Consulta;
import Excepciones.PersistenciaException;

/**
 * Interfaz de la clase ConsultaDAO.
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public interface iConsultaDAO {
    /**
     * Método que registra una consulta.
     * @param consulta Objeto de tipo Consulta.
     * @return True si se registra la consulta, false en caso contrario.
     * @throws PersistenciaException Excepción por si surge un error inesperado. 
     */
    public boolean registrarConsulta(Consulta consulta) throws PersistenciaException;
    
    /**
     * Método privado que verifica el folio de la cita de emergencia.
     * @param idCita ID de la cita de emergencia.
     * @param folio Folio de la cita.
     * @return True si el folio coincide, false en caso contrario.
     * @throws PersistenciaException Excepción por si surge un error inesperado. 
     */
    public boolean verificarFolio(int idCita, int folio) throws PersistenciaException;
}