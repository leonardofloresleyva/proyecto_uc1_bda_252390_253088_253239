package Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author Ximena
 */
public class Cita {
    private int idCita;
    private LocalDateTime fechaHora;
    private Medico medico;
    private Paciente paciente;
    private String tipoCita;

    public Cita() {
    }

    public Cita(int idCita, LocalDateTime fechaHora, Medico medico, Paciente paciente, String tipoCita) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.medico = medico;
        this.paciente = paciente;
        this.tipoCita = tipoCita;
    }

    public Cita(LocalDateTime fechaHora, Medico medico, Paciente paciente, String tipoCita) {
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

    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    public void setPaciente(Paciente paciente) {
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

    public Medico getMedico() {
        return medico;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public String getTipoCita() {
        return tipoCita;
    }

    @Override
    public String toString() {
        return "Cita{" + "idCita=" + idCita + ", fechaHora=" + fechaHora + ", tipoCita=" + tipoCita + ", idMedico=" + medico.getId() + ", idPaciente=" + paciente.getId() + '}';
    }
}