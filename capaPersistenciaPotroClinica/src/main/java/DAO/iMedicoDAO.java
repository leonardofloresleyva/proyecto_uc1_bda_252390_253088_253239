package DAO;

import Entidades.Cita;
import Entidades.Consulta;
import Entidades.Medico;
import Excepciones.PersistenciaException;
import java.util.List;

/**
 * Interfaz de la clase MedicoDAO.
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public interface iMedicoDAO {
    
    /**
     * Método que permite a un médico iniciar sesión en el sistema.
     * @param cedula Cédula del médico de tipo String.
     * @param contrasenia Contraseña del médico de tipo String.
     * @return Objeto médico.
     * @throws PersistenciaException 
     */
    public Medico iniciarSesionMedico(String cedula, String contrasenia) throws PersistenciaException;
    
    /**
     * Método que permite dar de baja a un médico.
     * @param id ID del médico.
     * @return True si se dio de baja el médico, false en caso contrario.
     * @throws PersistenciaException 
     */
    public boolean darDeBajaMedico(int id) throws PersistenciaException;
    
    /**
     * Método que permite dar de alta a un médico.
     * @param id ID del médico.
     * @return True si se dio de alta el médico, false en caso contrario.
     * @throws PersistenciaException 
     */
    public boolean darDeAltaMedico(int id) throws PersistenciaException;
    
    /**
     * Método que muestra la agenda del médico.
     * @param id ID del médico.
     * @return Lista de citas que el médico tiene programadas.
     * @throws PersistenciaException 
     */
    public List<Cita> mostrarAgendaMedico(int id) throws PersistenciaException;
    
    /**
     * Método que muestra el historial de consultas del médico.
     * @param id ID del médico.
     * @return Lista de consultas que el médico ha realizado.
     * @throws PersistenciaException 
     */
    public List<Consulta> consultarConsultasMedico(int id) throws PersistenciaException;
    
}