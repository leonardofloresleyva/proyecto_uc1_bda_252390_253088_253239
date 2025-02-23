package Entidades;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class Consulta {
    private int idConsulta;
    private String motivo, diagnostico, tratamiento;
    private Cita cita;

    public Consulta() {}

    public Consulta(int idConsulta, String motivo, String diagnostico, String tratamiento, Cita cita) {
        this.idConsulta = idConsulta;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.cita = cita;
    }

    public Consulta(String motivo, String diagnostico, String tratamiento, Cita cita) {
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

    public void setCita(Cita cita) {
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

    public Cita getCita() {
        return cita;
    } 

    @Override
    public String toString() {
        return "Consulta{" + "idConsulta=" + idConsulta + ", motivo=" + motivo + ", diagnostico=" + diagnostico + ", tratamiento=" + tratamiento + ", tipo_cita=" + cita.getTipoCita() + '}';
    }
}