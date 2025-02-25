package Entidades;

/**
 * Clase que contiene los atributos de Usuario
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public abstract class Usuario {
    // Atributos de un Usuario.
    private int id;
    private String usuario, contrasenia, rol;

    /**
     * Constructor vacío.
     */
    public Usuario() {}

    /**
     * Constructor que recibe todos los atributos del usuario.
     * @param id ID del usuario.
     * @param usuario Usuario que puede variar según el rol.
     * @param contrasenia Contraseña del usuario.
     * @param rol Rol asignado a Usuario (Paciente o Médico).
     */
    public Usuario(int id, String usuario, String contrasenia, String rol) {
        this.id = id;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    /**
     * Constructor que recibe todos los atributos del usuario, excepto el ID.
     * @param usuario Usuario que puede variar según el rol.
     * @param contrasenia Contraseña del usuario.
     * @param rol Rol asignado a Usuario (Paciente o Médico).
     */
    public Usuario(String usuario, String contrasenia, String rol) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    /**
     * Obtiene el ID de Usuario.
     * @return ID del usuario.
     */
    public int getId() {return id;}

    /**
     * Establece el ID de Usuario.
     * @param id Tipo de dato int, ID del usuario.
     */
    public void setId(int id) {this.id = id;}

    /**
     * Obtiene el usuario de Usuario.
     * @return Usuario que varía según el rol.
     */
    public String getUsuario() {return usuario;}

    /**
     * Establece el usuario de Usuario.
     * @param usuario Tipo de dato String, usuario que varía según el rol.
     */
    public void setUsuario(String usuario) {this.usuario = usuario;}

    /**
     * Obtiene la contraseña de Usuario.
     * @return Contraseña del usuario.
     */
    public String getContrasenia() {return contrasenia;}

    /**
     * Establece la contraseña de Usuario.
     * @param contrasenia Tipo de dato String, contraseña del usuario.
     */
    public void setContrasenia(String contrasenia) {this.contrasenia = contrasenia;}

    /**
     * Obtiene el rol de Usuario.
     * @return Rol del usuario (Paciente o Médico).
     */
    public String getRol() {return rol;}

    /**
     * Establece el rol de Usuario.
     * @param rol Tipo de dato String, rol del usuario (Paciente o Médico).
     */
    public void setRol(String rol) {this.rol = rol;}

    /**
     * Cadena de texto que imprime todos los parámetros de Usuario en consola.
     * @return Cadena de texto de los atributos del usuario, tipo String.
     */
    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", usuario=" + usuario + ", contrasenia=" + contrasenia + ", rol=" + rol + '}';
    }
}