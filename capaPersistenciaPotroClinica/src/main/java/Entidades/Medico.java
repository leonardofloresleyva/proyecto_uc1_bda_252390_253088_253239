package Entidades;

/**
 * Clase constructora de un Médico, hereda de clase Usuario.
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class Medico extends Usuario{
    // Atributos de un médico
    private String 
                nombres, 
                apellidoPaterno, 
                apellidoMaterno, 
                especialidad, 
                estado;

    /**
     * Constructor vacío.
     */
    public Medico() {}

    /**
     * Constructor que contiene todos los atributos de un médico, y los que
     * hereda de Usuario.
     * @param id ID del Usuario, en este caso, Médico.
     * @param usuario Cédula del médico.
     * @param contrasenia Contraseña del médico.
     * @param nombres Nombres del médico.
     * @param apellidoPaterno Apellido paterno del médico.
     * @param apellidoMaterno Apellido materno del médico.
     * @param especialidad Especialidad del médico.
     * @param estado Estado del médico (Alta o Baja).
     */
    public Medico(int id, String usuario, String contrasenia, String nombres, String apellidoPaterno, String apellidoMaterno, String especialidad, String estado) {
        super(id, usuario, contrasenia, "Medico");
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.especialidad = especialidad;
        this.estado = estado;
    }

    /**
     * Constructor que recibe todos los atributos de un médico y los que
     * hereda de Usuario, excepto el ID.
     * @param usuario Cédula del médico.
     * @param contrasenia Contraseña del médico.
     * @param nombres Nombres del médico.
     * @param apellidoPaterno Apellido paterno del médico.
     * @param apellidoMaterno Apellido materno del médico.
     * @param especialidad Especialidad del médico.
     * @param estado Estado del médico (Alta o Baja).
     */
    public Medico(String usuario, String contrasenia, String nombres, String apellidoPaterno, String apellidoMaterno, String especialidad, String estado) {
        super(usuario, contrasenia, "Medico");
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.especialidad = especialidad;
        this.estado = estado;
    }

    /**
     * Establece el o los nombres de Médico.
     * @param nombres Tipo de dato String, nombre o nombres del médico.
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Establece el apellido paterno de Médico.
     * @param apellidoPaterno Tipo de dato String, apellido paterno del médico.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Establece el apellido materno de Médico.
     * @param apellidoMaterno Tipo de dato String, apellido materno del médico.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Establece la especialidad de Médico.
     * @param especialidad Tipo de dato String, especialidad del médico.
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    /**
     * Establece el estado de Médico.
     * @param estado Tipo de dato String, estado del médico.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el o los nombres de Médico.
     * @return Nombre o nombres del médico.
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Obtiene el apellido paterno de Médico.
     * @return Apellido paterno del médico.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Obtiene el apellido materno de Médico.
     * @return Apellido materno del médico.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Obtiene la especialidad de Médico.
     * @return Especialidad del médico.
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Obtiene el estado de Médico.
     * @return Estado del médico.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Cadena de texto que imprime todos los parámetros de Médico en consola.
     * @return Cadena de texto de los atributos de Médico, tipo String.
     */
    @Override
    public String toString() {
        return "Medico{" + "nombres=" + nombres + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", especialidad=" + especialidad + ", estado=" + estado + '}';
    }   
}