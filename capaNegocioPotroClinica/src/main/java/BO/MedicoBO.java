package BO;

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
import at.favre.lib.crypto.bcrypt.BCrypt;
import java.util.List;
import java.util.regex.Pattern;
import org.apache.commons.validator.routines.EmailValidator;

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

    public MedicoBO(iMedicoDAO medicoDAO) {
        this.medicoDAO = medicoDAO;
        this.medicoMapper = new MedicoMapper();
        this.citaMapper = new CitaMapper();
        this.consultaMapper = new ConsultaMapper();
    }
    
    public MedicoViejoDTO iniciarSesion(String cedula, String contrasenia) throws NegocioException{
        validarUsuario(cedula, contrasenia);
        try{
            Medico medico = medicoDAO.iniciarSesionMedico(cedula, encriptarContrasenia(contrasenia));
            return medicoMapper.toDTO(medico);
            
        }catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
    
    public boolean darDeBaja(int id) throws NegocioException{
        validarID(id);
        
        try{
            return medicoDAO.darDeBajaMedico(id);
            
        }catch (PersistenciaException ex){
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
    
    public List<CitaDTO> agendaMedico(int id) throws NegocioException{
        validarID(id);
        
        try{
            return citaMapper.toListDTO(medicoDAO.mostrarAgendaMedico(id));
        }catch (PersistenciaException ex){
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
    
    public List<ConsultaDTO> consultasMedico(int id) throws NegocioException{
        validarID(id);
        try{
            return consultaMapper.consultasDTO(medicoDAO.consultarConsultasMedico(id));
            
        } catch (PersistenciaException ex){
            
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
    
    public boolean darDeAlta(int id) throws NegocioException{
        validarID(id);
        
        try{
            return medicoDAO.darDeAltaMedico(id);
            
        }catch (PersistenciaException ex){
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
    
    private void validarID(int id) throws NegocioException{
        if(id < 1)
            throw new NegocioException("No se permiten IDs menores a uno. Intentelo de nuevo.");

        if(id > Integer.MAX_VALUE)
            throw new NegocioException("Ingrese un id valido.");
    }
    
    private String encriptarContrasenia(String contrasenia) throws NegocioException {
        try {
            return BCrypt.withDefaults().hashToString(12, contrasenia.toCharArray());
        } catch (Exception e) {
            throw new NegocioException("Error al encriptar contraseña: " + e.getMessage());
        }
    }
    
    private void validarUsuario(String cedula, String password) throws NegocioException{
        if (cedula == null || cedula.trim().isEmpty())
            throw new NegocioException("El correo no puede estar vacio.");
        
        if (cedula.length() > 150)
            throw new NegocioException("No se permiten correos con mas de 150 caracteres.");
        
        if (!EmailValidator.getInstance().isValid(cedula))  
            throw new NegocioException("El correo ingresado no es válido.");
        
        if (cedula.split("@")[0].length() < 2) 
            throw new NegocioException("El correo debe tener al menos dos caracteres antes del '@'");
        
        if (!Pattern.matches("^[^@\\s]+@[^@\\s]+\\.com$", cedula)) 
             throw new NegocioException("La cadena ingresada no es un correo electronico.");
        
        if (password == null || password.trim().isEmpty())
            throw new NegocioException("La contrasenia no puede estar vacia.");
        
        if (password.length() > 20)
            throw new NegocioException("No se permiten contrasenias con mas de 20 caracteres.");
        
        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) 
            throw new NegocioException("La contraseña debe contener al menos una mayúscula, una minúscula y un número");
        
        if (password.matches(".*(.)\\1{2,}.*")) 
            throw new NegocioException("La contraseña no puede contener secuencias repetitivas");
        
        if (password.contains(" ")) 
            throw new NegocioException("La contraseña no debe contener espacios en ninguna parte");
    }
}
