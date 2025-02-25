package Entidades;

import java.time.LocalDateTime;

/**
 * Clase constructora de una Cita.
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class Cita {
    // Atributos de la cita
    private int idCita;
    private LocalDateTime fechaHora;
    private Medico medico; // Recibe un objeto Médico 
    private Paciente paciente; // Recibe un objeto Paciente
    private String tipoCita;

    /**
     * Constructor vacío.
     */
    public Cita() {
    }

    /**
     * Constructor que recibe todos los parámetros de la cita.
     * @param idCita ID de la cita.
     * @param fechaHora Fecha y hora de la cita.
     * @param medico Objeto de tipo Médico con todos sus atributos.
     * @param paciente Objeto de tipo Paciente con todos sus atributos.
     * @param tipoCita Tipo de cita (Previa o Emergencia).
     */
    public Cita(int idCita, LocalDateTime fechaHora, Medico medico, Paciente paciente, String tipoCita) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.medico = medico;
        this.paciente = paciente;
        this.tipoCita = tipoCita;
    }

    /**
     * Constructor que recibe todos los parámetros de la cita, excepto el ID.
     * @param fechaHora Fecha y hora de la cita.
     * @param medico Objeto de tipo Médico con todos sus atributos.
     * @param paciente Objeto de tipo Paciente con todos sus atributos.
     * @param tipoCita Tipo de cita (Previa o Emergencia).
     */
    public Cita(LocalDateTime fechaHora, Medico medico, Paciente paciente, String tipoCita) {
        this.fechaHora = fechaHora;
        this.medico = medico;
        this.paciente = paciente;
        this.tipoCita = tipoCita;
    }

    /**
     * Establece el ID de Cita.
     * @param idCita Tipo de dato int, id de la cita.
     */
    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    /**
     * Establece la fecha y hora de Cita.
     * @param fechaHora Tipo de dato LocalDateTime, fecha y hora de la cita.
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Establece el médico al que se le asignará Cita.
     * @param medico Tipo de dato Medico, objeto médico.
     */
    public void setMedico(Medico medico) {
        this.medico = medico;
    }

    /**
     * Establece el paciente al que se le asignará Cita.
     * @param paciente Tipo de dato Paciente, objeto paciente.
     */
    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    /**
     * Establece el tipo de cita de Cita.
     * @param tipoCita Tipo de dato String, tipo de cita.
     */
    public void setTipoCita(String tipoCita) {
        this.tipoCita = tipoCita;
    }

    /**
     * Obtiene el ID de Cita.
     * @return ID de la cita.
     */
    public int getIdCita() {
        return idCita;
    }

    /**
     * Obtiene la fecha y hora de Cita.
     * @return Fecha y hora de la cita.
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Obtiene el médico asignado a Cita.
     * @return Objeto médico para la cita.
     */
    public Medico getMedico() {
        return medico;
    }

    /**
     * Obtiene al paciente asignado a Cita.
     * @return Objeto paciente para la cita.
     */
    public Paciente getPaciente() {
        return paciente;
    }

    /**
     * Obtiene el tipo de cita asignado a Cita.
     * @return Tipo de cita.
     */
    public String getTipoCita() {
        return tipoCita;
    }

    /**
     * Cadena de texto que imprime todos los parámetros de Cita en consola.
     * @return Cadena de texto de atributos de la cita, tipo String.
     */
    @Override
    public String toString() {
        return "Cita{" + "idCita=" + idCita + ", fechaHora=" + fechaHora + ", tipoCita=" + tipoCita + ", idMedico=" + medico.getId() + ", idPaciente=" + paciente.getId() + '}';
    }
}