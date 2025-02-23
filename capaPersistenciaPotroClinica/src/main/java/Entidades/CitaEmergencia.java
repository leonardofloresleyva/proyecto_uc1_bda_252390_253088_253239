package Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
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
