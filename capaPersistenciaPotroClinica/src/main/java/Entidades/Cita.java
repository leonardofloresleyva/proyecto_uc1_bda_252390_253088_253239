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
    private int idMedico;
    private int idPaciente;
    private String estado;
    private String tipoCita;

    public Cita() {
    }

    public Cita(int idCita, LocalDate fechaHora, int idMedico, int idPaciente, String estado, String tipoCita) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
        this.estado = estado;
        this.tipoCita = tipoCita;
    }

    public Cita(LocalDate fechaHora, int idMedico, int idPaciente, String estado, String tipoCita) {
        this.fechaHora = fechaHora;
        this.idMedico = idMedico;
        this.idPaciente = idPaciente;
        this.estado = estado;
        this.tipoCita = tipoCita;
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

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getTipoCita() {
        return tipoCita;
    }

    public void setTipoCita(String tipoCita) {
        this.tipoCita = tipoCita;
    }

    @Override
    public String toString() {
        return "Cita{" + "idCita=" + idCita + ", fechaHora=" + fechaHora + ", idMedico=" + idMedico + ", idPaciente=" + idPaciente + ", estado=" + estado + ", tipoCita=" + tipoCita + '}';
    }
    
    
}
