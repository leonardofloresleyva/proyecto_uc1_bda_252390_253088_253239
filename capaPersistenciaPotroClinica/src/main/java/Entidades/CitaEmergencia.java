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
public class CitaEmergencia extends Cita {
    private int idCitaEmergencia;
    private int folio;

    public CitaEmergencia() {
    }

    public CitaEmergencia(int idCita, LocalDate fechaHora, String tipoCita, int idMedico, int idPaciente, int folio) {
        super(idCita, fechaHora, tipoCita, idMedico, idPaciente);
        this.folio = folio;
    }
    
    public CitaEmergencia(LocalDate fechaHora, String tipoCita, int idMedico, int idPaciente, int folio) {
        super(fechaHora, tipoCita, idMedico, idPaciente);
        this.folio = folio;
    }

    public int getIdCitaEmergencia() {
        return idCitaEmergencia;
    }

    public void setIdCitaEmergencia(int idCitaEmergencia) {
        this.idCitaEmergencia = idCitaEmergencia;
    }

    public int getFolio() {
        return folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    @Override
    public String toString() {
        return "CitaEmergencia{" + "idCitaEmergencia=" + idCitaEmergencia + ", folio=" + folio + '}';
    }
    
    
}
