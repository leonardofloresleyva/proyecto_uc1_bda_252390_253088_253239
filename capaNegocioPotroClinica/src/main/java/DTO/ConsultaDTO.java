package DTO;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class ConsultaDTO {
    
    private int idConsulta;
    private String motivo, diagnostico, tratamiento;
    private CitaDTO cita;

    public ConsultaDTO() {}

    public ConsultaDTO(int idConsulta, String motivo, String diagnostico, String tratamiento, CitaDTO cita) {
        this.idConsulta = idConsulta;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.cita = cita;
    }

    public ConsultaDTO(String motivo, String diagnostico, String tratamiento, CitaDTO cita) {
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.cita = cita;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public void setCita(CitaDTO cita) {
        this.cita = cita;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public String getMotivo() {
        return motivo;
    }

    public String getDiagnostico() {
        return diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public CitaDTO getCita() {
        return cita;
    }

    @Override
    public String toString() {
        return "ConsultaDTO{" + "idConsulta=" + idConsulta + ", motivo=" + motivo + ", diagnostico=" + diagnostico + ", tratamiento=" + tratamiento + ", cita=" + cita + '}';
    }
}