package DAO;

import Entidades.Consulta;
import Entidades.Paciente;
import Excepciones.PersistenciaException;
import java.time.LocalDate;
import java.util.List;

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
    
    public Paciente iniciarSesionPaciente(String correo, String contrasenia) throws PersistenciaException;
    
    public boolean cambiarContrasenia(Paciente paciente) throws PersistenciaException;
    
    public boolean actualizarPaciente(Paciente paciente) throws PersistenciaException;
    
    public List<Consulta> consultarConsultasPorEspecialidad(String correo, String especialidad) throws PersistenciaException;
    
    public List<Consulta> consultarConsultasRangoDeFechas(String correo, LocalDate fechaInicio, LocalDate fechaFin) throws PersistenciaException;
    
}