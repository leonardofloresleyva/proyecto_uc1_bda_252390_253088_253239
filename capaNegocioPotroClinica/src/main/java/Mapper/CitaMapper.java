package Mapper;

import DTO.CitaDTO;
import DTO.CitaEmergenciaDTO;
import DTO.CitaPreviaDTO;
import Entidades.Cita;
import Entidades.CitaEmergencia;
import Entidades.CitaPrevia;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class CitaMapper {
    
    private final MedicoMapper medicoMapper;
    private final PacienteMapper pacienteMapper;

    public CitaMapper(MedicoMapper medicoMapper, PacienteMapper pacienteMapper) {
        this.medicoMapper = medicoMapper;
        this.pacienteMapper = pacienteMapper;
    }
    
    public Cita toEntity(CitaDTO citaDTO){
        Cita cita = new Cita(
                citaDTO.getIdCita(),
                citaDTO.getFechaHora(),
                medicoMapper.toEntity(citaDTO.getMedico()),
                pacienteMapper.toEntityViejo(citaDTO.getPaciente()),
                citaDTO.getTipoCita()
        );
        return cita;
    }
    
    public CitaDTO toDTO(Cita cita){
        CitaDTO citaDTO = new CitaDTO(
                cita.getIdCita(),
                cita.getFechaHora(),
                medicoMapper.toDTO(cita.getMedico()),
                pacienteMapper.toDTOViejo(cita.getPaciente()),
                cita.getTipoCita()
        );
        return citaDTO;
    }
    
    public CitaPrevia toEntityPrevia(CitaPreviaDTO citaPreviaDTO){
        CitaPrevia cita = new CitaPrevia(
                citaPreviaDTO.getIdCita(),
                citaPreviaDTO.getFechaHora(),
                medicoMapper.toEntity(citaPreviaDTO.getMedico()),
                pacienteMapper.toEntityViejo(citaPreviaDTO.getPaciente()),
                citaPreviaDTO.getEstado()
        );
        return cita;
    }
    
    public CitaPreviaDTO toDTOPrevia(CitaPrevia cita){
        CitaPreviaDTO citaPreviaDTO = new CitaPreviaDTO(
                cita.getIdCita(),
                cita.getFechaHora(),
                medicoMapper.toDTO(cita.getMedico()),
                pacienteMapper.toDTOViejo(cita.getPaciente()),
                cita.getTipoCita()
        );
        return citaPreviaDTO;
    }
    
    public CitaEmergencia toEntityEmergencia(CitaEmergenciaDTO citaEmergenciaDTO){
        CitaEmergencia citaEmergencia = new CitaEmergencia(
                citaEmergenciaDTO.getIdCita(),
                citaEmergenciaDTO.getFechaHora(),
                medicoMapper.toEntity(citaEmergenciaDTO.getMedico()),
                pacienteMapper.toEntityViejo(citaEmergenciaDTO.getPaciente()),
                citaEmergenciaDTO.getEstado(),
                citaEmergenciaDTO.getFolio()
        );
        return citaEmergencia;
    }
    
    public CitaEmergenciaDTO toDTOEmergencia(CitaEmergencia citaEmergencia){
        CitaEmergenciaDTO citaEmergenciaDTO = new CitaEmergenciaDTO(
                citaEmergencia.getIdCita(),
                citaEmergencia.getFechaHora(),
                medicoMapper.toDTO(citaEmergencia.getMedico()),
                pacienteMapper.toDTOViejo(citaEmergencia.getPaciente()),
                citaEmergencia.getEstado(),
                citaEmergencia.getFolio()
        );
        return citaEmergenciaDTO;
    }
    
    public List<CitaDTO> toListDTO(List<Cita> citas){
        if(citas == null || citas.isEmpty())
            return new ArrayList<>();
        List<CitaDTO> citasDTO = new ArrayList<>();
        for(Cita cita : citas){
            citasDTO.add(toDTO(cita));
        }
        return citasDTO;
    }
    
    public List<CitaEmergenciaDTO> toListDTOEmergencia(List<CitaEmergencia> listaCitasEmergencia){
        if(listaCitasEmergencia == null || listaCitasEmergencia.isEmpty())
            return new ArrayList<>();
        List<CitaEmergenciaDTO> listaCitasEmergenciaDTO = new ArrayList<>();
        for(CitaEmergencia citaEmergencia : listaCitasEmergencia){
            listaCitasEmergenciaDTO.add(toDTOEmergencia(citaEmergencia));
        }
        return listaCitasEmergenciaDTO;
    }
    
    public List<CitaPreviaDTO> toListDTOPrevia(List<CitaPrevia> listaCitaPrevia){
        if(listaCitaPrevia == null || listaCitaPrevia.isEmpty())
            return new ArrayList<>();
        List<CitaPreviaDTO> listaCitasPreviaDTO = new ArrayList<>();
        for(CitaPrevia citaPrevia : listaCitaPrevia){
            listaCitasPreviaDTO.add(toDTOPrevia(citaPrevia));
        }
        return listaCitasPreviaDTO;
    }
}