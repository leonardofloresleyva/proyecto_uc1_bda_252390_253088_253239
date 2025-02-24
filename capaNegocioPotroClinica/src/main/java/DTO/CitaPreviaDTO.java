package DTO;

import java.time.LocalDateTime;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class CitaPreviaDTO extends CitaDTO{
    
    private String estado;

    public CitaPreviaDTO() {
    }

    public CitaPreviaDTO(int idCita, LocalDateTime fechaHora, MedicoViejoDTO medico, PacienteViejoDTO paciente, String estado) {
        super(idCita, fechaHora, medico, paciente, "Previa");
        this.estado = estado;
    }

    public CitaPreviaDTO(LocalDateTime fechaHora, MedicoViejoDTO medico, PacienteViejoDTO paciente, String estado) {
        super(fechaHora, medico, paciente, "Previa");
        this.estado = estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "CitaPreviaDTO{" + "estado=" + estado + '}';
    }
}