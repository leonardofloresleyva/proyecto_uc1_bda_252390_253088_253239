package DAO;

import Entidades.Cita;
import Excepciones.PersistenciaException;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public interface iCitaDAO {
    
    public boolean registrarCita(Cita cita) throws PersistenciaException;
    
    public boolean cancelarCita(int idCita) throws PersistenciaException;
    
    public List<Cita> consultarCitasPorEspecialidad(String especialidad) throws PersistenciaException;
    
    public List<Cita> consultarCitasRangoDeFechas(LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;
}
