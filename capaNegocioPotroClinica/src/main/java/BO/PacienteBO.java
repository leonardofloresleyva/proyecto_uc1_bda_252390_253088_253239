/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
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
 * @author multaslokas33
 */
public class PacienteBO {
    iPacienteDAO pacienteDAO;
    
    public PacienteBO (iConexion conexion) {
        this.pacienteDAO = new PacienteDAO(conexion);
    }
    
     public boolean registrarPaciente(PacienteNuevoDTO paciNuevo) throws NegocioException {
        
        String nombre = paciNuevo.getNombres();
        String telefono = paciNuevo.getTelefono();
        String apellidoPaterno = paciNuevo.getApellidoPaterno();
        
        if (paciNuevo == null) {
            throw new NegocioException("El paciente no puede ser nulo.");
        }
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NegocioException("El nombre no puede estar vacío.");
        }
        if (apellidoPaterno == null || apellidoPaterno.trim().isEmpty()) {
            throw new NegocioException("El apellido paterno no puede estar vacío.");
        }
        if (telefono == null || telefono.trim().isEmpty()) {
            throw new NegocioException("El teléfono no puede estar vacío.");
        }
        if (telefono.length()>20){
        throw new NegocioException("No se pueden más de 20 caracteres en el telefono");
        }
        if (paciNuevo.getFechaNacimiento() == null) {
            throw new NegocioException("La fecha de nacimiento es obligatoria.");
        }
         if (paciNuevo.getFechaNacimiento().isAfter(LocalDate.now())) {
            throw new NegocioException("La fecha de nacimiento no puede ser futura");
        }
        if (!Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", nombre)) {
            throw new NegocioException("No se admiten caracteres especiales en el nombre");
        }
        /**
        if (paciNuevo.getContrasenia().length() > 20) {
            throw new NegocioException("No se pueden más de 20 caracteres en la contraseña");
        }*/
        
        PacienteMapper convertidor = new PacienteMapper();
        Paciente paciente = convertidor.ToEntity(paciNuevo);

        try {
            pacienteDAO.registrarPaciente(paciente);
            return true;
        } catch (PersistenciaException ex) {
            Logger.getLogger(PacienteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Hubo un error al guardar en la base de datos", ex);
        }
    }
}