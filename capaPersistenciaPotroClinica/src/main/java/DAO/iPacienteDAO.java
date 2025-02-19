package DAO;

import Entidades.Paciente;
import Excepciones.PersistenciaException;

/**
 * Interfaz del paciente
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Uribe (253239)
 */
public interface iPacienteDAO {
    
    public boolean registrarPaciente(Paciente paciente) throws PersistenciaException;
    
}