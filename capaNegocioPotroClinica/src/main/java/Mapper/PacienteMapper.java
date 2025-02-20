package Mapper;

import DTO.PacienteNuevoDTO; 

/**
 *
 * @author luis
 */
public class PacienteMapper {
    
    public Paciente toEntity (PacienteNuevoDTO paciNuevo){
        Paciente paciente = new Paciente(paciNuevo.getNombres(), paciNuevo.getApellidoPaterno(), paciNuevo.getApellidoMaterno(), paciNuevo.getTelefono(), paciNuevo.getFechaNacimiento(), paciNuevo.getEstado());
        return paciente;
    }
    
    public PacienteNuevoDTO toDTO(Paciente paciente){
        PacienteNuevoDTO pacienteDTO = new PacienteNuevoDTO(paciente.getNombres(), paciente.getApellidoPaterno(), paciente.getApellidoMaterno(), paciente.getTelefono(), paciente.getFechaNacimiento(), paciente.getEstado());
        return pacienteDTO;
    }
}
