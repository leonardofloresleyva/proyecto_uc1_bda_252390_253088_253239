package BO;

import Conexion.iConexion;
import DAO.PacienteDAO;
import DAO.iPacienteDAO;
import DTO.PacienteNuevoDTO;
import Entidades.Paciente;
import Excepciones.NegocioException;
import Excepciones.PersistenciaException;
import Mapper.PacienteMapper;
import at.favre.lib.crypto.bcrypt.BCrypt;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.apache.commons.validator.routines.EmailValidator;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class PacienteBO {
    private final iPacienteDAO pacienteDAO;
    private final PacienteMapper pacienteMapper;
    
    public PacienteBO (iConexion conexion) {
        this.pacienteDAO = new PacienteDAO(conexion);
        this.pacienteMapper = new PacienteMapper();
    }
    
    public boolean registrarPaciente(PacienteNuevoDTO paciNuevo) throws NegocioException {
        
        if (paciNuevo == null) 
            throw new NegocioException("El paciente no puede ser nulo.");
         
        String correo = paciNuevo.getUsuario();
        String password = paciNuevo.getContrasenia();
        String rol = paciNuevo.getRol();
        String nombre = paciNuevo.getNombres();
        String apellidoP = paciNuevo.getApellidoPaterno();
        String apellidoM = paciNuevo.getApellidoMaterno();
        String telefono = paciNuevo.getTelefono();
        LocalDate fechaN = paciNuevo.getFechaNacimiento();
        
        if (correo == null || correo.trim().isEmpty())
            throw new NegocioException("El correo no puede estar vacio.");
        
        if (correo.length() > 150)
            throw new NegocioException("No se permiten correos con mas de 150 caracteres.");
        
        if (!EmailValidator.getInstance().isValid(correo))  
            throw new NegocioException("El correo ingresado no es válido.");
        
        if (correo.split("@")[0].length() < 2) 
            throw new NegocioException("El correo debe tener al menos dos caracteres antes del '@'");
        
        if (!Pattern.matches("^[^@\\s]+@[^@\\s]+\\.com$", correo)) 
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
        
        if (password.equalsIgnoreCase(correo) || password.equalsIgnoreCase(nombre)) 
            throw new NegocioException("La contraseña no puede ser igual al correo o al nombre");
        
        if (rol == null || rol.trim().isEmpty())
            throw new NegocioException("El rol no puede estar vacío.");
        
        if (!"Paciente".equals(rol))
            throw new NegocioException("El rol debe ser igual a \"Paciente\".");
        
        if (nombre == null || nombre.trim().isEmpty()) 
            throw new NegocioException("El nombre no puede estar vacio.");
        
        if (!Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", nombre)) 
            throw new NegocioException("No se admiten caracteres especiales en el nombre.");
        
        if (nombre.length() > 100)
            throw new NegocioException("No se permiten nombres con mas de 100 caracteres.");
        
        if (apellidoP == null || apellidoP.trim().isEmpty()) 
            throw new NegocioException("El apellido paterno no puede estar vacío.");
        
        if (!Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", apellidoP)) 
            throw new NegocioException("No se admiten caracteres especiales en el apellido paterno");
        
        if (apellidoP.length() > 100)
            throw new NegocioException("No se permiten apellidos paternos con mas de 100 caracteres.");
        
        if (apellidoM == null || apellidoM.trim().isEmpty()) 
            throw new NegocioException("El apellido materno no puede estar vacío.");
        
        if (!Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", apellidoM)) 
            throw new NegocioException("No se admiten caracteres especiales en el apellido materno");
        
        if (apellidoM.length() > 100)
            throw new NegocioException("No se permiten apellidos maternos con mas de 100 caracteres.");
        
        if (telefono == null || telefono.trim().isEmpty()) 
            throw new NegocioException("El teléfono no puede estar vacío.");
        
        if (telefono.length() > 20)
            throw new NegocioException("No se permiten telefonos con mas de 20 caracteres.");
        
        if (fechaN == null) 
            throw new NegocioException("La fecha de nacimiento no puede estar vacia.");
        
        if (fechaN.isAfter(LocalDate.now())) 
            throw new NegocioException("La fecha de nacimiento no puede estar después de la fecha actual.");
        
        int edad = LocalDate.now().getYear() - fechaN.getYear();
        if (edad < 0 || edad > 120) 
            throw new NegocioException("La edad debe estar entre 0 y 120 años");
        
        if (!paciNuevo.getCalle().matches("^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ #,-.]+$")) 
            throw new NegocioException("La dirección contiene caracteres inválidos");
        
        if (nombre.length() < 2 || nombre.length() > 50) 
            throw new NegocioException("El nombre debe tener entre 2 y 50 caracteres");
        
        if (!nombre.trim().equals(nombre)) 
            throw new NegocioException("El nombre no debe contener espacios al inicio o al final");
        
        String contraseniaEncriptada = encriptarContrasenia(password);
        System.out.println("Contraseña encriptada: " + contraseniaEncriptada);
        paciNuevo.setContrasenia(contraseniaEncriptada);
        
        Paciente paciente = pacienteMapper.toEntityNuevo(paciNuevo);
        
        try {
            pacienteDAO.registrarPaciente(paciente);
            return true;
        } catch (PersistenciaException ex) {
            Logger.getLogger(PacienteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Hubo un error al guardar en la base de datos", ex);
        }
    }
     
    private String encriptarContrasenia(String contrasenia) throws NegocioException {
        try {
            return BCrypt.withDefaults().hashToString(12, contrasenia.toCharArray());
        } catch (Exception e) {
            throw new NegocioException("Error al encriptar contraseña: " + e.getMessage());
        }
    }
    
    public boolean verificarContrasenia(String contraseniaIngresada, String contraseniaEncriptada) { 
        return BCrypt.verifyer().verify(contraseniaIngresada.toCharArray(), contraseniaEncriptada).verified;
    }   
}