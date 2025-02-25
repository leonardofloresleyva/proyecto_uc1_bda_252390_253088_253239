package Entidades;

import java.time.LocalDateTime;

/**
 * Clase constructora de una Cita de Emergencia, hereda de clase Cita.
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class CitaEmergencia extends Cita {
    // Atributos de la cita de emergencia
    private int folio;
    private String estado;

    /**
     * Constructor vacío.
     */
    public CitaEmergencia() {
    }

    /**
     * Constructor que recibe todos los atributos de la cita de emergencia, y
     * los que hereda de la clase Cita.
     * @param idCita ID de la cita, en este caso, de Emergencia.
     * @param fechaHora Fecha y hora de la cita.
     * @param medico Objeto de tipo Medico con todos sus atributos. 
     * @param paciente Objeto de tipo Paciente con todos sus atributos.
     * @param estado Estado de la cita (Atendida o No Atendida).
     * @param folio Folio de emergencia generado.
     */
    public CitaEmergencia(int idCita, LocalDateTime fechaHora, Medico medico, Paciente paciente, String estado, int folio) {
        super(idCita, fechaHora, medico, paciente, "Emergencia");
        this.estado = estado;
        this.folio = folio;
    }
    
    /**
     * Constructor que recibe todos los atributos de la cita de emergencia, y
     * los que hereda de la clase Cita, excepto el ID.
     * @param fechaHora Fecha y hora de la cita.
     * @param medico Objeto de tipo Medico con todos sus atributos. 
     * @param paciente Objeto de tipo Paciente con todos sus atributos.
     * @param estado Estado de la cita (Atendida o No Atendida).
     * @param folio Folio de emergencia generado.
     */
    public CitaEmergencia(LocalDateTime fechaHora, Medico medico, Paciente paciente, String estado, int folio) {
        super(fechaHora, medico, paciente, "Emergencia");
        this.estado = estado;
        this.folio = folio;
    }

    /**
     * Establece el folio para Cita de Emergencia.
     * @param folio Tipo de dato int, folio de la cita de emergencia.
     */
    public void setFolio(int folio) {
        this.folio = folio;
    }

    /**
     * Establece el estado para Cita de Emergencia.
     * @param estado Tipo de dato String, estado de la cita de emergencia.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    /**
     * Obtiene el folio para Cita de Emergencia.
     * @return Folio de la cita de emergencia.
     */
    public int getFolio() {
        return folio;
    }

    /**
     * Obtiene el estado para Cita de Emergencia.
     * @return Estado de la cita de emergencia.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Cadena de texto que imprime los atributos de Cita de Emergencia en consola.
     * @return Cadena de texto de atributos de Cita de Emergencia, tipo String.
     */
    @Override
    public String toString() {
        return "CitaEmergencia{" + "idCitaEmergencia=" + super.getIdCita() + ", folio=" + folio + '}';
    }   
}