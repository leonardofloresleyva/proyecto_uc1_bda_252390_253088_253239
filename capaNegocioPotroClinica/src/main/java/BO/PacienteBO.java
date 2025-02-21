package BO;

import Conexion.iConexion;
import DAO.PacienteDAO;
import DAO.iPacienteDAO;
import DTO.PacienteNuevoDTO;
import Entidades.Paciente;
import Excepciones.NegocioException;
import Excepciones.PersistenciaException;
import Mapper.PacienteMapper;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

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
        
        String correo = paciNuevo.getUsuario();
        String password = paciNuevo.getContrasenia();
        String rol = paciNuevo.getRol();
        String nombre = paciNuevo.getNombres();
        String apellidoP = paciNuevo.getApellidoPaterno();
        String apellidoM = paciNuevo.getApellidoMaterno();
        String telefono = paciNuevo.getTelefono();
        LocalDate fechaN = paciNuevo.getFechaNacimiento();
        
        if (paciNuevo == null) 
            throw new NegocioException("El paciente no puede ser nulo.");
        
        if(correo == null || correo.trim().isEmpty())
            throw new NegocioException("El correo no puede estar vacio.");
        
        if(correo.length() > 150)
            throw new NegocioException("No se permiten correos con mas de 150 caracteres.");
        
        if (!Pattern.matches("^[^@\\s]+@[^@\\s]+\\.com$", correo)) 
             throw new NegocioException("La cadena ingresada no es un correo electronico.");
        
        if (password == null || password.trim().isEmpty()) 
            throw new NegocioException("La contrasenia no puede estar vacia.");
        
        if(password.length() > 20)
            throw new NegocioException("No se permiten contrasenias con mas de 20 caracteres.");
        
        if(rol == null || rol.trim().isEmpty())
            throw new NegocioException("El rol no puede estar vacío.");
        
        if(!"Paciente".equals(rol))
            throw new NegocioException("El rol debe ser igual a \"Paciente\".");
        
        if (nombre == null || nombre.trim().isEmpty()) 
            throw new NegocioException("El nombre no puede estar vacio.");
        
        if (!Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", nombre)) 
            throw new NegocioException("No se admiten caracteres especiales en el nombre.");
        
        if(nombre.length() > 100)
            throw new NegocioException("No se permiten nombres con mas de 100 caracteres.");
        
        if (apellidoP == null || apellidoP.trim().isEmpty()) 
            throw new NegocioException("El apellido paterno no puede estar vacío.");
        
        if (!Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", apellidoP)) 
            throw new NegocioException("No se admiten caracteres especiales en el apellido paterno");
        
        if(apellidoP.length() > 100)
            throw new NegocioException("No se permiten apellidos paternos con mas de 100 caracteres.");
        
        if (apellidoM == null || apellidoM.trim().isEmpty()) 
            throw new NegocioException("El apellido materno no puede estar vacío.");
        
        if (!Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", apellidoM)) 
            throw new NegocioException("No se admiten caracteres especiales en el apellido materno");
        
        if(apellidoM.length() > 100)
            throw new NegocioException("No se permiten apellidos maternos con mas de 100 caracteres.");
        
        if (telefono == null || telefono.trim().isEmpty()) 
            throw new NegocioException("El teléfono no puede estar vacío.");
        
        if (telefono.length() > 20)
            throw new NegocioException("No se permiten telefonos con mas de 20 caracteres.");
        
        if (fechaN == null) 
            throw new NegocioException("La fecha de nacimiento no puede estar vacia.");
        
        if (fechaN.isAfter(LocalDate.now())) 
            throw new NegocioException("La fecha de nacimiento no puede estar después de la fecha actual.");
        
        Paciente paciente = pacienteMapper.toEntityNuevo(paciNuevo);
        
        try {
            pacienteDAO.registrarPaciente(paciente);
            return true;
        } catch (PersistenciaException ex) {
            Logger.getLogger(PacienteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Hubo un error al guardar en la base de datos", ex);
        }
    }
}