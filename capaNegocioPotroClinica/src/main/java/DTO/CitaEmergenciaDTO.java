package DTO;

import java.time.LocalDateTime;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class CitaEmergenciaDTO extends CitaDTO{
    
    // Número de folio de la cita de emergencia
    private int folio;
    
    //Estado actual de la cita de emergencia
    private String estado;

    // Constructor por defecto de la clase CitaEmergenciaDTO.
    public CitaEmergenciaDTO() {}

     /**
     * Constructor con parámetros que inicializa una cita de emergencia con un identificador de cita.
     * @param idCita Identificador único de la cita.
     * @param fechaHora Fecha y hora de la cita.
     * @param medico Médico asignado a la cita.
     * @param paciente Paciente que asistirá a la cita.
     * @param estado Estado actual de la cita.
     * @param folio Número de folio de la cita.
     */
    public CitaEmergenciaDTO(int idCita, LocalDateTime fechaHora, MedicoViejoDTO medico, PacienteViejoDTO paciente, String estado, int folio) {
        super(idCita, fechaHora, medico, paciente, "Emergencia");
        this.folio = folio;
        this.estado = estado;
    }

    /**
     * Constructor con parámetros que inicializa una cita de emergencia sin identificador de cita.
     * @param fechaHora Fecha y hora de la cita.
     * @param medico Médico asignado a la cita.
     * @param paciente Paciente que asistirá a la cita.
     * @param estado Estado actual de la cita.
     * @param folio Número de folio de la cita.
     */
    public CitaEmergenciaDTO(LocalDateTime fechaHora, MedicoViejoDTO medico, PacienteViejoDTO paciente, String estado, int folio) {
        super(fechaHora, medico, paciente, "Emergencia");
        this.folio = folio;
        this.estado = estado;
    }

    /**
     * Establece el número de folio de la cita.
     * @param folio Número de folio de la cita.
     */
    public void setFolio(int folio) {
        this.folio = folio;
    }

    /**
     * Establece el estado de la cita.
     * @param estado Estado actual de la cita.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el número de folio de la cita.
     * @return Número de folio de la cita.
     */
    public int getFolio() {
        return folio;
    }

    /**
     * Obtiene el estado actual de la cita.
     * @return Estado de la cita.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Devuelve una representación en cadena del objeto CitaEmergenciaDTO.
     * @return Cadena con los valores de los atributos de la cita de emergencia.
     */
    @Override
    public String toString() {
        return "CitaEmergenciaDTO{" + "folio=" + folio + ", estado=" + estado + '}';
    }
}