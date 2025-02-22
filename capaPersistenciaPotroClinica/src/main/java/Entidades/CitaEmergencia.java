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
public class CitaEmergencia extends Cita {
    private int idCitaEmergencia;
    private int folio;

    public CitaEmergencia() {
    }

    public CitaEmergencia(int idCita, LocalDateTime fechaHora, int idMedico, int idPaciente, String tipoCita, int folio) {
        super(idCita, fechaHora, idMedico, idPaciente, tipoCita);
        this.folio = folio;
    }
    
    public CitaEmergencia(LocalDateTime fechaHora, int idMedico, int idPaciente, String tipoCita, int folio) {
        super(fechaHora, idMedico, idPaciente, tipoCita);
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
