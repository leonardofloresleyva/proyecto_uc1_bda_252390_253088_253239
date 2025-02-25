package Entidades;

import java.time.LocalDateTime;

/**
 * Clase constructora de una Cita Previa, hereda de Cita.
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class CitaPrevia extends Cita {
    // Atributos para Cita Previa.
    private String estado;

    /**
     * Constructor vacío.
     */
    public CitaPrevia() {
    }

    /**
     * Constructor que recibe todos los parámetro de cita previa, y los que
     * hereda de la clase Cita.
     * @param idCita ID de Cita, en este caso, cita previa.
     * @param fechaHora Fecha y hora de la cita.
     * @param medico Objeto de tipo Medico con todos sus atributos. 
     * @param paciente Objeto de tipo Paciente con todos sus atributos.
     * @param estado Estado de la cita (Atendida, Cancelada o No Atendida).
     */
    public CitaPrevia(int idCita, LocalDateTime fechaHora, Medico medico, Paciente paciente, String estado) {
        super(idCita, fechaHora, medico, paciente, "Previa");
        this.estado = estado;
    }

    /**
     * Constructor que recibe todos los parámetro de cita previa, y los que
     * hereda de la clase Cita, excepto el ID.
     * @param fechaHora Fecha y hora de la cita.
     * @param medico Objeto de tipo Medico con todos sus atributos. 
     * @param paciente Objeto de tipo Paciente con todos sus atributos.
     * @param estado Estado de la cita (Atendida, Cancelada o No Atendida).
     */
    public CitaPrevia(LocalDateTime fechaHora, Medico medico, Paciente paciente, String estado) {
        super(fechaHora, medico, paciente, "Previa");
        this.estado = estado;
    }
    
    /**
     * Establece el estado de Cita Previa.
     * @param estado Tipo de dato String, estado de la cita previa.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    /**
     * Obtiene el estado de Cita Previa.
     * @return Estado de la cita previa.
     */
    public String getEstado() {
        return estado;
    }
    
    /**
     * Cadena de texto que imprime los atributos de Cita Previa en consola.
     * @return Cadena de texto de los atributos de Cita Previa, tipo String.
     */
    @Override
    public String toString() {
        return "CitaPrevia{" + "idCitaPrevia=" + super.getIdCita() + '}';
    }   
}