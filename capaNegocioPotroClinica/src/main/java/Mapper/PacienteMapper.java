/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
