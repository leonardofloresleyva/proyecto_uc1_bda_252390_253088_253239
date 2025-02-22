/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entidades;

import java.time.LocalDate;

/**
 *
 * @author Ximena
 */
public class Cita {
    private int idCita;
    private LocalDate fechaHora;
    private String tipoCita;
    private int idMedico;
    private int idPaciente;

    public Cita() {
    }

    public Cita(int idCita, LocalDate fechaHora, String tipoCita, int idMedico, int idPaciente) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.tipoCita = tipoCita;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
    }

    public Cita(LocalDate fechaHora, String tipoCita, int idMedico, int idPaciente) {
        this.fechaHora = fechaHora;
        this.tipoCita = tipoCita;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
    }

    public int getIdCita() {
        return idCita;
    }

    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    public LocalDate getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(LocalDate fechaHora) {
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
