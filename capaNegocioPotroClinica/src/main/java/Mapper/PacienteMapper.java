package Mapper;

import DTO.PacienteNuevoDTO;
import Entidades.Paciente;

/**
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class PacienteMapper {
    
    //aggregar usuario() y quitar super
    public Paciente ToEntity (PacienteNuevoDTO paciNuevo){
        Paciente paciente = new Paciente(paciNuevo.getNombres(), paciNuevo.getApellidoPaterno(), paciNuevo.getApellidoMaterno(), paciNuevo.getTelefono(), paciNuevo.getFechaNacimiento(), paciNuevo.getEstado(), paciNuevo.getColonia(), paciNuevo.getCalle(), paciNuevo.getNumero());
        return paciente;
    }
    
    public PacienteNuevoDTO toDTO(Paciente paciente){
        PacienteNuevoDTO pacienteDTO = new PacienteNuevoDTO(paciente.getNombres(), paciente.getApellidoPaterno(), paciente.getApellidoMaterno(), paciente.getTelefono(), paciente.getFechaNacimiento(), paciente.getEstado(), paciente.getColonia(), paciente.getCalle(), paciente.getNumero());
        return pacienteDTO;
    }
}
