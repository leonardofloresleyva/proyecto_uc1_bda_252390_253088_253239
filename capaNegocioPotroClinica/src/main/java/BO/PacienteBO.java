package BO;

import DTO.PacienteNuevoDTO;
import Excepciones.NegocioException;
import Mapper.PacienteMapper;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Uribe (253239)
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