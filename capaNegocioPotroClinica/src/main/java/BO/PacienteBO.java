/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DTO.PacienteNuevoDTO;
import Excepciones.NegocioException;
import Mapper.PacienteMapper;
import java.lang.System.Logger.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;

/**
 *
 * @author multaslokas33
 */
public class PacienteBO {
    iPacienteDAO pacienteDAO;
    
    public PacienteBO (iConexion conexion) {
        this.pacienteDAO = new PacienteBO(conexion);
    }
    
    private PacienteMapper mapper = new PacienteMapper();

    public Paciente guardarPaciente(PacienteNuevoDTO paciNuevo) throws NegocioException {
        validarDatos(paciNuevo);  
        Paciente paciente = mapper.toEntity(paciNuevo);
        return paciente;
    }

    private void validarDatos(PacienteNuevoDTO paciNuevo) throws NegocioException {
        if (paciNuevo == null) {
            throw new NegocioException("El paciente no puede ser nulo.");
        }
        if (paciNuevo.getNombres() == null || paciNuevo.getNombres().trim().isEmpty()) {
            throw new NegocioException("El nombre no puede estar vacío.");
        }
        if (paciNuevo.getApellidoPaterno() == null || paciNuevo.getApellidoPaterno().trim().isEmpty()) {
            throw new NegocioException("El apellido paterno no puede estar vacío.");
        }
        if (paciNuevo.getTelefono() == null || paciNuevo.getTelefono().trim().isEmpty()) {
            throw new NegocioException("El teléfono no puede estar vacío.");
        }
        if (paciNuevo.getFechaNacimiento() == null) {
            throw new NegocioException("La fecha de nacimiento es obligatoria.");
        }
        if (!Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", paciNuevo.getNombres())) {
            throw new NegocioException("No se admiten caracteres especiales en el nombre");
        }
    }
}