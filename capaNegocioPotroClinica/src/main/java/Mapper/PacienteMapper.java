package Mapper;

import DTO.PacienteNuevoDTO;
import DTO.PacienteViejoDTO;
import Entidades.Paciente;

/**
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class PacienteMapper {
    
    public Paciente toEntityNuevo (PacienteNuevoDTO paciNuevo){
        Paciente paciente = new Paciente(
                paciNuevo.getUsuario(),
                paciNuevo.getContrasenia(),
                paciNuevo.getNumero(),
                paciNuevo.getNombres(), 
                paciNuevo.getApellidoPaterno(), 
                paciNuevo.getApellidoMaterno(), 
                paciNuevo.getTelefono(), 
                paciNuevo.getFechaNacimiento(), 
                paciNuevo.getEstado(), 
                paciNuevo.getColonia(), 
                paciNuevo.getCalle(), 
                paciNuevo.getNumero()
        );
        return paciente;
    }
    
    public PacienteNuevoDTO toDTONuevo(Paciente paciViejo){
        PacienteNuevoDTO pacienteDTO = new PacienteNuevoDTO(
                paciViejo.getUsuario(),
                paciViejo.getContrasenia(),
                paciViejo.getNumero(),
                paciViejo.getNombres(), 
                paciViejo.getApellidoPaterno(), 
                paciViejo.getApellidoMaterno(), 
                paciViejo.getTelefono(), 
                paciViejo.getFechaNacimiento(), 
                paciViejo.getEstado(), 
                paciViejo.getColonia(), 
                paciViejo.getCalle(), 
                paciViejo.getNumero()
        );
        return pacienteDTO;
    }
    
    public Paciente toEntityViejo(PacienteViejoDTO paciViejo){
        Paciente paciente = new Paciente(
                paciViejo.getId(),
                paciViejo.getUsuario(),
                paciViejo.getContrasenia(),
                paciViejo.getRol(),
                paciViejo.getNombres(), 
                paciViejo.getApellidoPaterno(), 
                paciViejo.getApellidoMaterno(), 
                paciViejo.getTelefono(), 
                paciViejo.getFechaNacimiento(), 
                paciViejo.getEstado(), 
                paciViejo.getColonia(), 
                paciViejo.getCalle(), 
                paciViejo.getNumero()
        );
        return paciente;
    }
    
    public PacienteViejoDTO toDTOViejo(Paciente paciNuevo){
        PacienteViejoDTO pacienteDTO = new PacienteViejoDTO(
                paciNuevo.getId(),
                paciNuevo.getUsuario(),
                paciNuevo.getContrasenia(),
                paciNuevo.getRol(),
                paciNuevo.getNombres(), 
                paciNuevo.getApellidoPaterno(), 
                paciNuevo.getApellidoMaterno(), 
                paciNuevo.getTelefono(), 
                paciNuevo.getFechaNacimiento(),  
                paciNuevo.getEstado(),
                paciNuevo.getColonia(), 
                paciNuevo.getCalle(), 
                paciNuevo.getNumero()
        );
        return pacienteDTO;
    }
    
}