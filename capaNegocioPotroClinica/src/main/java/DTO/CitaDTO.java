package DTO;

import java.time.LocalDateTime;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class CitaDTO {
    
    private int idCita;
    private LocalDateTime fechaHora;
    private MedicoViejoDTO medico;
    private PacienteViejoDTO paciente;
    private String tipoCita;

    public CitaDTO() {}

    public CitaDTO(int idCita, LocalDateTime fechaHora, MedicoViejoDTO medico, PacienteViejoDTO paciente, String tipoCita) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.medico = medico;
        this.paciente = paciente;
        this.tipoCita = tipoCita;
    }

    public CitaDTO(LocalDateTime fechaHora, MedicoViejoDTO medico, PacienteViejoDTO paciente, String tipoCita) {
        this.fechaHora = fechaHora;
        this.medico = medico;
        this.paciente = paciente;
        this.tipoCita = tipoCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public void setMedico(MedicoViejoDTO medico) {
        this.medico = medico;
    }

    public void setPaciente(PacienteViejoDTO paciente) {
        this.paciente = paciente;
    }

    public void setTipoCita(String tipoCita) {
        this.tipoCita = tipoCita;
    }

    public int getIdCita() {
        return idCita;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public MedicoViejoDTO getMedico() {
        return medico;
    }

    public PacienteViejoDTO getPaciente() {
        return paciente;
    }

    public String getTipoCita() {
        return tipoCita;
    }

    @Override
    public String toString() {
        return "CitaDTO{" + "idCita=" + idCita + ", fechaHora=" + fechaHora + ", medico=" + medico + ", paciente=" + paciente + ", tipoCita=" + tipoCita + '}';
    }
}