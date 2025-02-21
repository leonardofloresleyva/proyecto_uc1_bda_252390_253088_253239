package Entidades;

/**
 * Clase que contiene los atributos del usuario
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public abstract class Usuario {
    // Atributos de un Usuario.
    private int id;
    private String usuario, contrasenia, rol;

    public Usuario() {}

    public Usuario(int id, String usuario, String contrasenia, String rol) {
        this.id = id;
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public Usuario(String usuario, String contrasenia, String rol) {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }

    public int getId() {return id;}

    public void setId(int id) {this.id = id;}

    public String getUsuario() {return usuario;}

    public void setUsuario(String usuario) {this.usuario = usuario;}

    public String getContrasenia() {return contrasenia;}

    public void setContrasenia(String contrasenia) {this.contrasenia = contrasenia;}

    public String getRol() {return rol;}

    public void setRol(String rol) {this.rol = rol;}

    @Override
    public String toString() {
        return "Usuario{" + "id=" + id + ", usuario=" + usuario + ", contrasenia=" + contrasenia + ", rol=" + rol + '}';
    }
}