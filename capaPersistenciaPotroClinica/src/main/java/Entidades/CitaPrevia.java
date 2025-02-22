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
public class CitaPrevia extends Cita {
    private int idCitaPrevia;

    public CitaPrevia() {
    }

    public CitaPrevia(int idCita, LocalDate fechaHora, String tipoCita, int idMedico, int idPaciente) {
        super(idCita, fechaHora, tipoCita, idMedico, idPaciente);
    }

    public CitaPrevia(LocalDate fechaHora, String tipoCita, int idMedico, int idPaciente) {
        super(fechaHora, tipoCita, idMedico, idPaciente);
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
