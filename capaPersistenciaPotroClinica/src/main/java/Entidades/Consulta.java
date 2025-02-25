package Entidades;

/**
 * Clase constructora de una Consulta.
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class Consulta {
    private int idConsulta;
    private String motivo, diagnostico, tratamiento;
    private Cita cita; // Recibe objeto Cita

    /**
     * Constructor vacío.
     */
    public Consulta() {}

    /**
     * Constructor que recibe todos los parámetros de la consulta.
     * @param idConsulta ID de la consulta.
     * @param motivo Motivo de consulta.
     * @param diagnostico Diagnóstico de consulta.
     * @param tratamiento Tratamiento de consulta.
     * @param cita Objeto de tipo Cita con todos sus atributos.
     */
    public Consulta(int idConsulta, String motivo, String diagnostico, String tratamiento, Cita cita) {
        this.idConsulta = idConsulta;
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.cita = cita;
    }

    /**
     * Constructor que recibe todos los parámetros de consulta, excepto el ID.
     * @param motivo Motivo de consulta.
     * @param diagnostico Diagnóstico de consulta.
     * @param tratamiento Tratamiento de consulta.
     * @param cita Objeto de tipo Cita con todos sus atributos.
     */
    public Consulta(String motivo, String diagnostico, String tratamiento, Cita cita) {
        this.motivo = motivo;
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.cita = cita;
    }

    /**
     * Establece el ID de Consulta.
     * @param idConsulta Tipo de dato int, ID de la consulta.
     */
    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    /**
     * Establece el motivo de Consulta.
     * @param motivo Tipo de dato String, motivo de la consulta.
     */
    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    /**
     * Establece el diagnóstico de Consulta.
     * @param diagnostico Tipo de dato String, diagnóstico de la consulta.
     */
    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    /**
     * Establece el tratamiento de Consulta.
     * @param tratamiento Tipo de dato String, tratamiento de la consulta.
     */
    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    /**
     * Establece la cita a la que se le realizará Consulta.
     * @param cita Tipo de dato Cita, objeto cita.
     */
    public void setCita(Cita cita) {
        this.cita = cita;
    }

    /**
     * Obtiene el ID de Consulta.
     * @return ID de la consulta.
     */
    public int getIdConsulta() {
        return idConsulta;
    }

    /**
     * Obtiene el motivo de Consulta.
     * @return Motivo de la consulta
     */
    public String getMotivo() {
        return motivo;
    }

    /**
     * Obtiene el diagnóstico de Consulta.
     * @return Diagnóstico de la consulta.
     */
    public String getDiagnostico() {
        return diagnostico;
    }

    /**
     * Obtiene el tratamiento de Consulta.
     * @return Tratamiento de la consulta.
     */
    public String getTratamiento() {
        return tratamiento;
    }

    /**
     * Obtiene la cita asignada a Consulta.
     * @return Cita para la consulta.
     */
    public Cita getCita() {
        return cita;
    } 

    /**
     * Cadena de texto que imprime los atributos de Consulta en consola.
     * @return Cadena de texto con los parámetros de Consulta, tipo String.
     */
    @Override
    public String toString() {
        return "Consulta{" + "idConsulta=" + idConsulta + ", motivo=" + motivo + ", diagnostico=" + diagnostico + ", tratamiento=" + tratamiento + ", tipo_cita=" + cita.getTipoCita() + '}';
    }
}