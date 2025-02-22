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
    
    /**
     * Método que registra un nuevo paciente
     * @param paciente Objeto de tipo paciente
     * @return True si se registró el paciente, false en caso contrario
     * @throws PersistenciaException 
     */
    public boolean registrarPaciente(Paciente paciente) throws PersistenciaException;
    
    public boolean cambiarContrasenia(Paciente paciente) throws PersistenciaException;
    
    public boolean actualizarPaciente(Paciente paciente) throws PersistenciaException;
}