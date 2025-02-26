package BO;

import Conexion.iConexion;
import DAO.MedicoDAO;
import DAO.iMedicoDAO;
import DTO.CitaDTO;
import DTO.ConsultaDTO;
import DTO.MedicoViejoDTO;
import Entidades.Medico;
import Excepciones.NegocioException;
import Excepciones.PersistenciaException;
import Mapper.CitaMapper;
import Mapper.ConsultaMapper;
import Mapper.MedicoMapper;
import Mapper.PacienteMapper;
//import at.favre.lib.crypto.bcrypt.BCrypt;
import java.util.List;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class MedicoBO {
    private final iMedicoDAO medicoDAO;
    private final MedicoMapper medicoMapper;
    private final CitaMapper citaMapper;
    private final ConsultaMapper consultaMapper;

    /**
     * Constructor de la clase MedicoBO.
     * @param conexion Objeto de conexión a la base de datos.
     */
    public MedicoBO(iConexion conexion) {
        this.medicoDAO = new MedicoDAO(conexion);
        this.medicoMapper = new MedicoMapper();
        this.citaMapper = new CitaMapper(medicoMapper, new PacienteMapper());
        this.consultaMapper = new ConsultaMapper(citaMapper);
    }
    
    /**
     * Permite a un médico iniciar sesión en el sistema.
     * @param cedula Cédula del médico.
     * @param contrasenia Contraseña del médico.
     * @return Un objeto MedicoViejoDTO con la información del médico.
     * @throws NegocioException Si los datos son inválidos o hay un error en la persistencia.
     */
    public MedicoViejoDTO iniciarSesion(String cedula, String contrasenia) throws NegocioException{
        validarUsuario(cedula, contrasenia);
        try{
            Medico medico = medicoDAO.iniciarSesionMedico(cedula,(contrasenia));
            return medicoMapper.toDTO(medico);
            
        }catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
    
     /**
     * Da de baja a un médico en el sistema.
     * @param id Identificador del médico.
     * @return true si el médico fue dado de baja correctamente, false en caso contrario.
     * @throws NegocioException Si el ID es inválido o hay un error en la persistencia.
     */
    public boolean darDeBaja(int id) throws NegocioException{
        validarID(id);
        
        try{
            return medicoDAO.darDeBajaMedico(id);
            
        }catch (PersistenciaException ex){
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Obtiene la agenda de un médico con sus citas programadas.
     * @param id Identificador del médico.
     * @return Lista de citas del médico.
     * @throws NegocioException Si el ID es inválido o hay un error en la persistencia.
     */
    public List<CitaDTO> agendaMedico(int id) throws NegocioException{
        validarID(id);
        
        try{
            return citaMapper.toListDTO(medicoDAO.mostrarAgendaMedico(id));
        }catch (PersistenciaException ex){
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Obtiene la lista de consultas realizadas por un médico.
     * @param id Identificador del médico.
     * @return Lista de consultas realizadas por el médico.
     * @throws NegocioException Si el ID es inválido o hay un error en la persistencia.
     */
    public List<ConsultaDTO> consultasMedico(int id) throws NegocioException{
        validarID(id);
        try{
            return consultaMapper.consultasDTO(medicoDAO.consultarConsultasMedico(id));
            
        } catch (PersistenciaException ex){
            
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Da de alta nuevamente a un médico en el sistema.
     * @param id Identificador del médico.
     * @return true si el médico fue dado de alta correctamente, false en caso contrario.
     * @throws NegocioException Si el ID es inválido o hay un error en la persistencia.
     */
    public boolean darDeAlta(int id) throws NegocioException{
        validarID(id);
        
        try{
            return medicoDAO.darDeAltaMedico(id);
            
        }catch (PersistenciaException ex){
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Valida que el ID sea mayor o igual a 1.
     * @param id Identificador a validar.
     * @throws NegocioException Si el ID es menor a 1 o inválido.
     */
    private void validarID(int id) throws NegocioException{
        if(id < 1)
            throw new NegocioException("No se permiten IDs menores a uno. Intentelo de nuevo.");

        if(id > Integer.MAX_VALUE)
            throw new NegocioException("Ingrese un id valido.");
    }
    
    /**
     * Valida las credenciales de un usuario antes de iniciar sesión.
     * @param cedula Cédula del médico.
     * @param password Contraseña del médico.
     * @throws NegocioException Si la cédula o la contraseña son inválidas.
     */
    private void validarUsuario(String cedula, String password) throws NegocioException{
        if (cedula == null || cedula.trim().isEmpty())
            throw new NegocioException("La cedula no puede estar vacio.");
        
        if (password == null || password.trim().isEmpty())
            throw new NegocioException("La contrasenia no puede estar vacia.");

    }
}
