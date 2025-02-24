package Mapper;

import DTO.CitaEmergenciaDTO;
import DTO.ConsultaDTO;
import Entidades.Consulta;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class ConsultaMapper {
    
    private CitaMapper citaMapper;
    
    public Consulta toEntity(ConsultaDTO consultaDTO){
        Consulta consulta = new Consulta(
                consultaDTO.getIdConsulta(),
                consultaDTO.getMotivo(),
                consultaDTO.getDiagnostico(),
                consultaDTO.getTratamiento(),
                citaMapper.toEntityEmergencia((CitaEmergenciaDTO) consultaDTO.getCita())
        );
        return consulta;
    }
    
    public ConsultaDTO toDTO(Consulta consulta){
        ConsultaDTO consultaDTO = new ConsultaDTO(
                consulta.getMotivo(),
                consulta.getDiagnostico(),
                consulta.getTratamiento(),
                citaMapper.toDTO(consulta.getCita())
        );
        return consultaDTO;
    }
    
    public List<ConsultaDTO> consultasDTO(List<Consulta> consultas){
        if(consultas == null || consultas.isEmpty())
            return new ArrayList<>();
        List<ConsultaDTO> consultasDTO = new ArrayList<>();
        for(Consulta consulta : consultas){
            consultasDTO.add(toDTO(consulta));
        }
        return consultasDTO;
    }
}