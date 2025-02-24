package DTO;

import java.time.LocalDateTime;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class CitaEmergenciaDTO extends CitaDTO{
    
    private int folio;
    private String estado;

    public CitaEmergenciaDTO() {}

    public CitaEmergenciaDTO(int idCita, LocalDateTime fechaHora, MedicoViejoDTO medico, PacienteViejoDTO paciente, String estado, int folio) {
        super(idCita, fechaHora, medico, paciente, "Emergencia");
        this.folio = folio;
        this.estado = estado;
    }

    public CitaEmergenciaDTO(LocalDateTime fechaHora, MedicoViejoDTO medico, PacienteViejoDTO paciente, String estado, int folio) {
        super(fechaHora, medico, paciente, "Emergencia");
        this.folio = folio;
        this.estado = estado;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public int getFolio() {
        return folio;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "CitaEmergenciaDTO{" + "folio=" + folio + ", estado=" + estado + '}';
    }
}