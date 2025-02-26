package DTO;

import java.time.LocalDateTime;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class CitaDTO {
    
    private int idCita;
    private LocalDateTime fechaHora;
    private MedicoViejoDTO medico;
    private PacienteViejoDTO paciente;
    private String tipoCita;

    // Constructor vacío de la clase CitaDTO.
    public CitaDTO() {}

    /**
     * Constructor que inicializa todos los atributos de la cita.
     * @param idCita Identificador único de la cita.
     * @param fechaHora Fecha y hora de la cita.
     * @param medico Médico asignado a la cita.
     * @param paciente Paciente que asistirá a la cita.
     * @param tipoCita Tipo de cita (ej. consulta general, especialidad, etc.).
     */
    public CitaDTO(int idCita, LocalDateTime fechaHora, MedicoViejoDTO medico, PacienteViejoDTO paciente, String tipoCita) {
        this.idCita = idCita;
        this.fechaHora = fechaHora;
        this.medico = medico;
        this.paciente = paciente;
        this.tipoCita = tipoCita;
    }

    /**
     * Constructor sin el identificador de la cita.
     * Se usa cuando aún no se ha asignado un ID en la base de datos.
     * @param fechaHora Fecha y hora de la cita.
     * @param medico Médico asignado a la cita.
     * @param paciente Paciente que asistirá a la cita.
     * @param tipoCita Tipo de cita (ej. consulta general, especialidad, etc.).
     */
    public CitaDTO(LocalDateTime fechaHora, MedicoViejoDTO medico, PacienteViejoDTO paciente, String tipoCita) {
        this.fechaHora = fechaHora;
        this.medico = medico;
        this.paciente = paciente;
        this.tipoCita = tipoCita;
    }

    /**
     * Establece el ID de la cita.
     * @param idCita Identificador único de la cita.
     */
    public void setIdCita(int idCita) {
        this.idCita = idCita;
    }

    /**
     * Establece la fecha y hora de la cita.
     * @param fechaHora Fecha y hora de la cita.
     */
    public void setFechaHora(LocalDateTime fechaHora) {
        this.fechaHora = fechaHora;
    }

    /**
     * Asigna el médico a la cita.
     * @param medico Médico asignado a la cita.
     */
    public void setMedico(MedicoViejoDTO medico) {
        this.medico = medico;
    }

    /**
     * Asigna el paciente a la cita.
     * @param paciente Paciente que asistirá a la cita.
     */
    public void setPaciente(PacienteViejoDTO paciente) {
        this.paciente = paciente;
    }

    /**
     * Establece el tipo de cita.
     * @param tipoCita Tipo de cita (ej. consulta general, especialidad, etc.).
     */
    public void setTipoCita(String tipoCita) {
        this.tipoCita = tipoCita;
    }

    /**
     * Obtiene el ID de la cita.
     * @return Identificador único de la cita.
     */
    public int getIdCita() {
        return idCita;
    }

    /**
     * Obtiene la fecha y hora de la cita.
     * @return Fecha y hora de la cita.
     */
    public LocalDateTime getFechaHora() {
        return fechaHora;
    }

    /**
     * Obtiene el médico asignado a la cita.
     * @return Médico asignado a la cita.
     */
    public MedicoViejoDTO getMedico() {
        return medico;
    }

    /**
     * Obtiene el paciente que asistirá a la cita.
     * @return Paciente de la cita.
     */
    public PacienteViejoDTO getPaciente() {
        return paciente;
    }

    /**
     * Obtiene el tipo de cita.
     * @return Tipo de cita
     */
    public String getTipoCita() {
        return tipoCita;
    }

    /**
     * Representación en cadena de la cita.
     * @return Cadena con la información de la cita.
     */
    @Override
    public String toString() {
        return "CitaDTO{" + "idCita=" + idCita + ", fechaHora=" + fechaHora + ", medico=" + medico + ", paciente=" + paciente + ", tipoCita=" + tipoCita + '}';
    }
}