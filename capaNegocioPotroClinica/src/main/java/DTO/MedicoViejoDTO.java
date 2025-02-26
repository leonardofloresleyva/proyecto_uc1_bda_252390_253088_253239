package DTO;

/**
 * 
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class MedicoViejoDTO extends UsuarioViejoDTO{
    
    private String 
                nombres, 
                apellidoPaterno, 
                apellidoMaterno, 
                especialidad, 
                estado;

    
    //Constructor por defecto de la clase MedicoViejoDTO.
    public MedicoViejoDTO() {}

    /**
     * Constructor con parámetros que inicializa un médico con identificador. 
     * @param id Identificador único del médico.
     * @param usuario Nombre de usuario del médico.
     * @param contrasenia Contraseña del médico.
     * @param nombres Nombre(s) del médico.
     * @param apellidoPaterno Apellido paterno del médico.
     * @param apellidoMaterno Apellido materno del médico.
     * @param especialidad Especialidad del médico.
     * @param estado Estado actual del médico.
     */
    public MedicoViejoDTO(int id, String usuario, String contrasenia, String nombres, String apellidoPaterno, String apellidoMaterno, String especialidad, String estado) {
        super(id, usuario, contrasenia, "Medico");
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.especialidad = especialidad;
        this.estado = estado;
    }

    /**
     * Constructor con parámetros que inicializa un médico sin identificador. 
     * @param usuario Nombre de usuario del médico.
     * @param contrasenia Contraseña del médico.
     * @param nombres Nombre(s) del médico.
     * @param apellidoPaterno Apellido paterno del médico.
     * @param apellidoMaterno Apellido materno del médico.
     * @param especialidad Especialidad del médico.
     * @param estado Estado actual del médico.
     */
    public MedicoViejoDTO(String usuario, String contrasenia, String nombres, String apellidoPaterno, String apellidoMaterno, String especialidad, String estado) {
        super(usuario, contrasenia, "Medico");
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.especialidad = especialidad;
        this.estado = estado;
    }

    /**
     * Establece el nombre del médico.
     * @param nombres Nombre(s) del médico.
     */
    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    /**
     * Establece el apellido paterno del médico. 
     * @param apellidoPaterno Apellido paterno del médico.
     */
    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    /**
     * Establece el apellido materno del médico.
     * @param apellidoMaterno Apellido materno del médico.
     */
    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    /**
     * Establece la especialidad del médico. 
     * @param especialidad Especialidad del médico.
     */
    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

     /**
     * Establece el estado actual del médico. 
     * @param estado Estado actual del médico.
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }

    /**
     * Obtiene el nombre del médico. 
     * @return Nombre(s) del médico.
     */
    public String getNombres() {
        return nombres;
    }

    /**
     * Obtiene el apellido paterno del médico.
     * @return Apellido paterno del médico.
     */
    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    /**
     * Obtiene el apellido materno del médico.
     * 
     * @return Apellido materno del médico.
     */
    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    /**
     * Obtiene la especialidad del médico.
     * @return Especialidad del médico.
     */
    public String getEspecialidad() {
        return especialidad;
    }

    /**
     * Obtiene el estado actual del médico. 
     * @return Estado actual del médico.
     */
    public String getEstado() {
        return estado;
    }

    /**
     * Devuelve una representación en cadena del objeto MedicoViejoDTO. 
     * @return Cadena con los valores de los atributos del médico.
     */
    @Override
    public String toString() {
        return "MedicoViejoDTO{" + "nombres=" + nombres + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", especialidad=" + especialidad + ", estado=" + estado + '}';
    }
}