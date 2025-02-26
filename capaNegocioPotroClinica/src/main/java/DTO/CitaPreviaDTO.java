package DTO;

import java.time.LocalDateTime;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class CitaPreviaDTO extends CitaDTO{
    
    //Estado actual de la cita previa
    private String estado;

    //Constructor por defecto de la clase CitaPreviaDTO.
    public CitaPreviaDTO() {
    }

    /**
     * Constructor con parámetros que inicializa una cita previa con un identificador de cita.
     * @param idCita Identificador único de la cita.
     * @param fechaHora Fecha y hora de la cita.
     * @param medico Médico asignado a la cita.
     * @param paciente Paciente que asistirá a la cita.
     * @param estado Estado actual de la cita.
     */
    public CitaPreviaDTO(int idCita, LocalDateTime fechaHora, MedicoViejoDTO medico, PacienteViejoDTO paciente, String estado) {
        super(idCita, fechaHora, medico, paciente, "Previa");
        this.estado = estado;
    }

    /**
     * Constructor con parámetros que inicializa una cita previa sin identificador de cita.
     * @param fechaHora Fecha y hora de la cita.
     * @param medico Médico asignado a la cita.
     * @param paciente Paciente que asistirá a la cita.
     * @param estado Estado actual de la cita.
     */
    public CitaPreviaDTO(LocalDateTime fechaHora, MedicoViejoDTO medico, PacienteViejoDTO paciente, String estado) {
        super(fechaHora, medico, paciente, "Previa");
        this.estado = estado;
    }

    /**
     * Establece el estado de la cita. 
     * @param estado Estado actual de la cita.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el estado actual de la cita.
     * @return Estado de la cita.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Devuelve una representación en cadena del objeto CitaPreviaDTO.
     * @return Cadena con los valores del atributo estado de la cita previa.
     */
    @Override
    public String toString() {
        return "CitaPreviaDTO{" + "estado=" + estado + '}';
    }
}