package Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
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
