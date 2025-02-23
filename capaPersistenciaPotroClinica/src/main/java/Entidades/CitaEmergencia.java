package Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class CitaEmergencia extends Cita {
    private int folio;
    private String estado;

    public CitaEmergencia() {
    }

    public CitaEmergencia(int idCita, LocalDateTime fechaHora, int idMedico, int idPaciente, String estado, int folio) {
        super(idCita, fechaHora, idMedico, idPaciente, "Emergencia");
        this.estado = estado;
        this.folio = folio;
    }
    
    public CitaEmergencia(LocalDateTime fechaHora, int idMedico, int idPaciente, String estado, int folio) {
        super(fechaHora, idMedico, idPaciente, "Emergencia");
        this.estado = estado;
        this.folio = folio;
    }

    public void setFolio(int folio) {
        this.folio = folio;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
    public int getFolio() {
        return folio;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "CitaEmergencia{" + "idCitaEmergencia=" + super.getIdCita() + ", folio=" + folio + '}';
    }   
}