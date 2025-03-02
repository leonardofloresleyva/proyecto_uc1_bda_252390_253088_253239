package BO;

import Conexion.iConexion;
import DAO.CitaDAO;
import DTO.CitaDTO;
import DTO.MedicoViejoDTO;
import Excepciones.NegocioException;
import Excepciones.PersistenciaException;
import Mapper.CitaMapper;
import Mapper.MedicoMapper;
import Mapper.PacienteMapper;
import java.time.LocalDateTime;
import java.util.List;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class CitaBO {
    
    private final CitaDAO citaDAO;
    private final CitaMapper citaMapper;
    private final MedicoMapper medicoMapper;

    /**
     * Constructor de la clase CitaBO.
     * @param conexion Objeto de conexión a la base de datos.
     */
    public CitaBO(iConexion conexion) {
        this.citaDAO = new CitaDAO(conexion);
        this.citaMapper = new CitaMapper(new MedicoMapper(), new PacienteMapper());
        this.medicoMapper = new MedicoMapper();
    }
    
    public List<MedicoViejoDTO> medicosDisponibles(LocalDateTime fechaHora, String especialidad) throws NegocioException{
        
        if(fechaHora == null)
            throw new NegocioException("La fecha y hora de la cita no puede ser nula.");
        
        if(fechaHora.isBefore(LocalDateTime.now()))
            throw new NegocioException("La fecha y hora de la cita no puede ser inferior a la fecha y hora actual.");
        
        if((fechaHora.getMinute() != 30 && fechaHora.getMinute() != 0) || fechaHora.getSecond() != 0)
            throw new NegocioException("Las citas se programan en lapsos de media hora exactas.");
        
        if (especialidad == null || especialidad.trim().isEmpty())
            throw new NegocioException("La cedula no puede estar vacio.");
        
        try {
            return medicoMapper.toListDTO(citaDAO.medicosDisponibles(fechaHora, especialidad));
        } catch (PersistenciaException ex) {
            throw new NegocioException(ex.getMessage(), ex);
        }
        
    }
    
    /**
     * Registra una nueva cita médica en el sistema.
     * @param cita Objeto DTO que contiene la información de la cita.
     * @return true si la cita fue registrada correctamente, false en caso contrario.
     * @throws NegocioException Si hay problemas en la validación de los datos o en la persistencia.
     */
    public boolean registrarCita(CitaDTO cita) throws NegocioException{
        //Todas las validaciones al momento de registrar una cita
        if(cita == null)
            throw new NegocioException("La cita no puede ser nula.");
        
        if(cita.getMedico() == null)
                throw new NegocioException("El medico no puede ser nulo.");
        
        if(cita.getPaciente() == null)
            throw new NegocioException("El paciente no puede ser nulo.");
        
        
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
        
        if((fechaCita.getMinute() != 30 && fechaCita.getMinute() != 0) || fechaCita.getSecond() != 0)
            throw new NegocioException("Las citas se programan en lapsos de media hora exactas.");
        
        try{
            return citaDAO.registrarCita(citaMapper.toEntity(cita));
            
        } catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage(), ex);
        }
                    
    }
    
    /**
     * Cancela una cita médica registrada en el sistema.
     * @param id Identificador único de la cita a cancelar.
     * @return true si la cita fue cancelada correctamente, false en caso contrario.
     * @throws NegocioException Si hay problemas en la validación del ID o en la persistencia.
     */
    public boolean cancelarCita(int id) throws NegocioException{
        validarID(id);
        try{
            return citaDAO.cancelarCita(id);
        } catch(PersistenciaException ex){
            throw new NegocioException(ex.getMessage(), ex);
        }
    }
    
    /**
     * Valida que el ID de la cita sea un número válido.
     * @param id Identificador a validar.
     * @throws NegocioException Si el ID es menor a 1 o inválido.
     */
    private void validarID(int id) throws NegocioException{
        if(id < 1)
            throw new NegocioException("No se permiten IDs menores a uno. Intentelo de nuevo.");

        if(id > Integer.MAX_VALUE)
            throw new NegocioException("Ingrese un id valido.");
    }
    
}