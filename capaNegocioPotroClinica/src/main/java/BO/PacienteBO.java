/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BO;

import DTO.PacienteNuevoDTO;
import Excepciones.NegocioException;
import Mapper.PacienteMapper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author luis
 */
public class PacienteBO {
    IPacienteDAO pacienteDAO;
    
    public PacienteBO (IConexionBD conexion) {
        this.pacienteDAO = new PacienteBO(conexion);
    }
    
    public boolean registrarPaciente (PacienteNuevoDTO pacienteNuevo)throws NegocioException {
    
        if (pacienteNuevo == null){
            return false; 
        }
        
        Paciente paciente = new Paciente();
        PacienteMapper convertidor = new PacienteMapper();
        paciente = convertidor.toEntity(pacienteNuevo);
        
        try {
            Paciente pacienteGuardado = pacienteDAO.registrarPaciente(paciente);
            return true;
        } catch (NegocioException ex) {
            Logger.getLogger(PacienteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Hubo un error al guardar en la base de datos", ex);
        }
    }
}