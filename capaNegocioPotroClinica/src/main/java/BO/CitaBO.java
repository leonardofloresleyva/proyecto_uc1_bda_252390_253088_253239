package BO;

import Conexion.iConexion;
import DAO.CitaDAO;
import DTO.CitaDTO;
import Excepciones.NegocioException;
import Excepciones.PersistenciaException;
import Mapper.CitaMapper;
import Mapper.MedicoMapper;
import Mapper.PacienteMapper;
import java.time.LocalDateTime;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class CitaBO {
    
    private final CitaDAO citaDAO;
    private final CitaMapper citaMapper;

    public CitaBO(iConexion conexion) {
        this.citaDAO = new CitaDAO(conexion);
        this.citaMapper = new CitaMapper(new MedicoMapper(), new PacienteMapper());
    }
    
    public boolean registrarCita(CitaDTO cita) throws NegocioException{
        if(cita == null)
            throw new NegocioException("La cita no puede ser nula.");
        
        if(cita.getMedico() == null)
                throw new NegocioException("El medico no puede ser nulo.");
        
        if(cita.getPaciente() == null)
            throw new NegocioException("El paciente no puede ser nulo.");
        
        validarID(cita.getIdCita());
        validarID(cita.getMedico().getId());
        validarID(cita.getPaciente().getId());
        
        String tipoCita = cita.getTipoCita();
        LocalDateTime fechaCita = cita.getFechaHora();
        
        if(tipoCita == null || tipoCita.isEmpty())
            throw new NegocioException("El tipo de la cita no puede estar vacio.");
        
        if(!"Previa".equals(tipoCita) || "Emergencia".equalsIgnoreCase(tipoCita))
            throw new NegocioException("El tipo de la cita solo puede ser Previa o Emergencia.");
        
        if(fechaCita == null)
            throw new NegocioException("La fecha y hora de la cita no puede ser nula.");
        
        if(fechaCita.isBefore(LocalDateTime.now()))
            throw new NegocioException("La fecha y hora de la cita no puede ser inferior a la fecha y hora actual.");
        
        if((fechaCita.getMinute() != 30 || fechaCita.getMinute() != 0) || fechaCita.getSecond() != 0)
            throw new NegocioException("Las citas se programan en lapsos de media hora exactas.");
        
        try{
            return citaDAO.registrarCita(citaMapper.toEntity(cita));
            
        } catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage(), ex);
        }
                    
    }
    
    public boolean cancelarCita(int id) throws NegocioException{
        validarID(id);
        try{
            return citaDAO.cancelarCita(id);
        } catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
    
    private void validarID(int id) throws NegocioException{
        if(id < 1)
            throw new NegocioException("No se permiten IDs menores a uno. Intentelo de nuevo.");

        if(id > Integer.MAX_VALUE)
            throw new NegocioException("Ingrese un id valido.");
    }
    
}