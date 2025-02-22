/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author Ximena
 */
public class Cita {
    private int idCita;
    private LocalDateTime fechaHora;
    private int idMedico;
    private int idPaciente;
    private String tipoCita;

    public Cita() {
    }

    public Cita(int idCita, LocalDateTime fechaHora, int idMedico, int idPaciente, String tipoCita) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
        this.tipoCita = tipoCita;
    }

    public Cita(LocalDateTime fechaHora, int idMedico, int idPaciente, String tipoCita) {
        this.fechaHora = fechaHora;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
        this.tipoCita = tipoCita;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    public String getTipoCita() {
        return tipoCita;
    }

    public void setTipoCita(String tipoCita) {
        this.tipoCita = tipoCita;
    }

        public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    @Override
    public String toString() {
        return "Cita{" + "idCita=" + idCita + ", fechaHora=" + fechaHora + ", tipoCita=" + tipoCita + ", idMedico=" + idMedico + ", idPaciente=" + idPaciente + '}';
    }
    
    
}
