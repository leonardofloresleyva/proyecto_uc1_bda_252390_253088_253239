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
     * @return Objeto médico con todos sus atributos que con la cédula y
     * contraseña ingresados en el parámetro.
     * @throws PersistenciaException 
     */
    public Medico iniciarSesionMedico(String cedula, String contrasenia) throws PersistenciaException;
    
    public boolean darDeBajaMedico(int id) throws PersistenciaException;
    
    public boolean darDeAltaMedico(int id) throws PersistenciaException;
    
    public List<Cita> mostrarAgendaMedico(int id) throws PersistenciaException;
    
    public List<Consulta> consultarConsultasMedico(int id) throws PersistenciaException;
    
}
