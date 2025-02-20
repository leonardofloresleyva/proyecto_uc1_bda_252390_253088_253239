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
public class CitaMedica extends Cita {
    
    private int idCitaMedica;

    public CitaMedica() {
    }

    public CitaMedica(int idCita, LocalDate fechaHora, int idMedico, int idPaciente, String estado, String tipoCita) {
        super(idCita, fechaHora, idMedico, idPaciente, estado, tipoCita);
    }
    
    public CitaMedica(LocalDate fechaHora, int idMedico, int idPaciente, String estado, String tipoCita) {
        super(fechaHora, idMedico, idPaciente, estado, tipoCita);
    }

    public int getIdCitaMedica() {
        return idCitaMedica;
    }

    public void setIdCitaMedica(int idCitaMedica) {
        this.idCitaMedica = idCitaMedica;
    }

    @Override
    public String toString() {
        return "CitaMedica{" + "idCitaMedica=" + idCitaMedica + '}';
    }
    
}
