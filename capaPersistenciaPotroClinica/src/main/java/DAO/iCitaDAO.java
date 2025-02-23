package DAO;

import Entidades.Cita;
import Excepciones.PersistenciaException;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public interface iCitaDAO {
    
    public boolean registrarCita(Cita cita) throws PersistenciaException;
    
    public boolean cancelarCita(int idCita) throws PersistenciaException;
}
