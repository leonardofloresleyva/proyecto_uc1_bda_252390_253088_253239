package Entidades;

import java.time.LocalDateTime;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class CitaPrevia extends Cita {
    private String estado;

    public CitaPrevia() {
    }

    public CitaPrevia(int idCita, LocalDateTime fechaHora, int idMedico, int idPaciente, String estado) {
        super(idCita, fechaHora, idMedico, idPaciente, "Previa");
        this.estado = estado;
    }

    public CitaPrevia(LocalDateTime fechaHora, int idMedico, int idPaciente, String estado) {
        super(fechaHora, idMedico, idPaciente, "Previa");
        this.estado = estado;
    }
    
    public void setEstado(String estado) {
        this.estado = estado;
    }
    

    public String getEstado() {
        return estado;
    }
    
    @Override
    public String toString() {
        return "CitaPrevia{" + "idCitaPrevia=" + super.getIdCita() + '}';
    }   
}