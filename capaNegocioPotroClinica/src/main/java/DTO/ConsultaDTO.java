package DTO;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class ConsultaDTO {
    
    private int idConsulta;
    private String motivo, diagnostico, tratamiento;
    private CitaDTO cita;

    //Constructor por defecto de la clase ConsultaDTO.
    public ConsultaDTO() {}

    /**
     * Constructor con parámetros que inicializa una consulta con un identificador.
     * @param idConsulta Identificador único de la consulta.
     * @param motivo Motivo de la consulta.
     * @param diagnostico Diagnóstico determinado.
     * @param tratamiento Tratamiento prescrito.
     * @param cita Cita asociada a la consulta.
     */
    public ConsultaDTO(int idConsulta, String motivo, String diagnostico, String tratamiento, CitaDTO cita) {
        this.idConsulta = idConsulta;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.cita = cita;
    }

    /**
     * Constructor con parámetros que inicializa una consulta sin identificador. 
     * @param motivo Motivo de la consulta.
     * @param diagnostico Diagnóstico determinado.
     * @param tratamiento Tratamiento prescrito.
     * @param cita Cita asociada a la consulta.
     */
    public ConsultaDTO(String motivo, String diagnostico, String tratamiento, CitaDTO cita) {
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.cita = cita;
    }

    /**
     * Establece el identificador de la consulta. 
     * @param idConsulta Identificador único de la consulta.
     */
    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    /**
     * Establece el motivo de la consulta. 
     * @param motivo Motivo de la consulta.
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * Establece el diagnóstico de la consulta. 
     * @param diagnostico Diagnóstico determinado.
     */
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    /**
     * Establece el tratamiento de la consulta. 
     * @param tratamiento Tratamiento prescrito.
     */
    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    /**
     * Establece la cita asociada a la consulta.
     * @param cita Cita asociada a la consulta.
     */
    public void setCita(CitaDTO cita) {
        this.cita = cita;
    }
    
    /**
     * Obtiene el identificador de la consulta. 
     * @return Identificador único de la consulta.
     */
    public int getIdConsulta() {
        return idConsulta;
    }

    /**
     * Obtiene el motivo de la consulta. 
     * @return Motivo de la consulta.
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Obtiene el diagnóstico de la consulta. 
     * @return Diagnóstico determinado en la consulta.
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * Obtiene el tratamiento de la consulta. 
     * @return Tratamiento prescrito en la consulta.
     */
    public String getTratamiento() {
        return tratamiento;
    }

    /**
     * Obtiene la cita asociada a la consulta. 
     * @return Cita asociada a la consulta.
     */
    public CitaDTO getCita() {
        return cita;
    }

    /**
     * Devuelve una representación en cadena del objeto ConsultaDTO. 
     * @return Cadena con los valores de los atributos de la consulta.
     */
    @Override
    public String toString() {
        return "ConsultaDTO{" + "idConsulta=" + idConsulta + ", motivo=" + motivo + ", diagnostico=" + diagnostico + ", tratamiento=" + tratamiento + ", cita=" + cita + '}';
    }
}