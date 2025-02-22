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
public class CitaPrevia extends Cita {
    private int idCitaPrevia;

    public CitaPrevia() {
    }

    public CitaPrevia(int idCita, LocalDateTime fechaHora, int idMedico, int idPaciente, String tipoCita) {
        super(idCita, fechaHora, idMedico, idPaciente, tipoCita);
    }

    public CitaPrevia(LocalDateTime fechaHora, int idMedico, int idPaciente, String tipoCita) {
        super(fechaHora, idMedico, idPaciente, tipoCita);
    }

    public int getIdCitaPrevia() {
        return idCitaPrevia;
    }

    public void setIdCitaPrevia(int idCitaPrevia) {
        this.idCitaPrevia = idCitaPrevia;
    }

    @Override
    public String toString() {
        return "CitaPrevia{" + "idCitaPrevia=" + idCitaPrevia + '}';
    }
    
}
