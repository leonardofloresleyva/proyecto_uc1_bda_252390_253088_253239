package Mapper;

import DTO.MedicoViejoDTO;
import Entidades.Medico;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class MedicoMapper {
    
    public Medico toEntity(MedicoViejoDTO medicoViejo){
        Medico medico = new Medico(
                medicoViejo.getId(),
                medicoViejo.getUsuario(),
                medicoViejo.getContrasenia(),
                medicoViejo.getNombres(),
                medicoViejo.getApellidoPaterno(),
                medicoViejo.getApellidoMaterno(),
                medicoViejo.getEspecialidad(),
                medicoViejo.getEstado()
        );
        return medico;
    }
    
    public MedicoViejoDTO toDTO(Medico medico){
        MedicoViejoDTO medicoViejo = new MedicoViejoDTO(
                medico.getId(),
                medico.getUsuario(),
                medico.getContrasenia(),
                medico.getNombres(),
                medico.getApellidoPaterno(),
                medico.getApellidoMaterno(),
                medico.getEspecialidad(),
                medico.getEstado()
        );
        return medicoViejo;
    }
    
    public List<MedicoViejoDTO> toListDTO(List<Medico> medicos){
        if(medicos == null || medicos.isEmpty())
            return new ArrayList<>();
        List<MedicoViejoDTO> medicosDTO = new ArrayList<>();
        for(Medico medico : medicos){
            medicosDTO.add(toDTO(medico));
        }
        return medicosDTO;
    }
}