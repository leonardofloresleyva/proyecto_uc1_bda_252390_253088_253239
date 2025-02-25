package Entidades;

import java.time.LocalDate;

/**
 * Clase constructora de un Paciente, hereda de clase Usuario.
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class Paciente extends Usuario {
    // Atributos de un Paciente.
    private String nombres, apellidoPaterno, apellidoMaterno, telefono, estado;
    private LocalDate fechaNacimiento;
    // Atributos de la dirección que se pasan a tabla Dirección a través de un procedimiento almacenado.
    private String colonia, calle, numero;

    /**
     * Constructor vacío.
     */
    public Paciente() {}

    /**
     * Constructor que contiene todos los atributos de un paciente, y los que
     * hereda de Usuario.
     * @param id ID del Usuario, en este caso, Paciente.
     * @param usuario Correo electrónico del paciente.
     * @param contrasenia Contraseña del paciente.
     * @param nombres Nombres del paciente.
     * @param apellidoPaterno Apellido paterno del paciente.
     * @param apellidoMaterno Apellido materno del paciente.
     * @param telefono Teléfono del paciente.
     * @param fechaNacimiento Fecha de nacimiento del paciente.
     * @param estado Estado del paciente (Alta o Baja).
     * @param colonia Colonia, parte de Dirección del paciente.
     * @param calle Calle, parte de Dirección del paciente.
     * @param numero Número, parte de Dirección del paciente.
     */
    public Paciente(
            int id, 
            String usuario, 
            String contrasenia,
            String nombres, 
            String apellidoPaterno, 
            String apellidoMaterno, 
            String telefono, 
            LocalDate fechaNacimiento, 
            String estado, 
            String colonia, 
            String calle, 
            String numero
    ) {
        super(id, usuario, contrasenia, "Paciente");
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
        this.colonia = colonia;
        this.calle = calle;
        this.numero = numero;
    }

    /**
     * Constructor que contiene todos los atributos de un paciente y los que
     * hereda de Usuario, excepto el ID.
     * @param usuario Correo electrónico del paciente.
     * @param contrasenia Contraseña del paciente.
     * @param nombres Nombres del paciente.
     * @param apellidoPaterno Apellido paterno del paciente.
     * @param apellidoMaterno Apellido materno del paciente.
     * @param telefono Teléfono del paciente.
     * @param fechaNacimiento Fecha de nacimiento del paciente.
     * @param estado Estado del paciente (Alta o Baja).
     * @param colonia Colonia, parte de Dirección del paciente.
     * @param calle Calle, parte de Dirección del paciente.
     * @param numero Número, parte de Dirección del paciente. 
     */
    public Paciente(
            String usuario, 
            String contrasenia,
            String nombres, 
            String apellidoPaterno, 
            String apellidoMaterno, 
            String telefono, 
            LocalDate fechaNacimiento, 
            String estado, 
            String colonia, 
            String calle, 
            String numero
    ) {
        super(usuario, contrasenia, "Paciente");
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.estado = estado;
        this.colonia = colonia;
        this.calle = calle;
        this.numero = numero;
    }

    /**
     * Establece el nombre o nombres de Paciente.
     * @param nombres Tipo de dato String, el o los nombres del paciente.
     */
    public void setNombres(String nombres) {this.nombres = nombres;}

    /**
     * Establece el apellido paterno de Paciente.
     * @param apellidoPaterno Tipo de dato String, apellido paterno del paciente.
     */
    public void setApellidoPaterno(String apellidoPaterno) {this.apellidoPaterno = apellidoPaterno;}

    /**
     * Establece el apellido materno de Paciente.
     * @param apellidoMaterno Tipo de dato String, apellido materno del paciente.
     */
    public void setApellidoMaterno(String apellidoMaterno) {this.apellidoMaterno = apellidoMaterno;}

    /**
     * Establece el teléfono de Paciente.
     * @param telefono Tipo de dato String, teléfono del paciente.
     */
    public void setTelefono(String telefono) {this.telefono = telefono;}

    /**
     * Establece el estado de Paciente.
     * @param estado Tipo de dato String, estado del paciente
     */
    public void setEstado(String estado) {this.estado = estado;}

    /**
     * Establece la fecha de nacimiento de Paciente.
     * @param fechaNacimiento Tipo de dato LocalDate, fecha de nacimiento.
     */
    public void setFechaNacimiento(LocalDate fechaNacimiento) {this.fechaNacimiento = fechaNacimiento;}

    /**
     * Establece la colonia para la dirección de Paciente.
     * @param colonia Tipo de dato String, colonia del paciente.
     */
    public void setColonia(String colonia) {this.colonia = colonia;}

    /**
     * Establece la calle para la dirección de Paciente.
     * @param calle Tipo de dato String, calle del paciente.
     */
    public void setCalle(String calle) {this.calle = calle;}

    /**
     * Establece el número para la dirección de Paciente.
     * @param numero Tipo de dato String, número (de casa) del paciente.
     */
    public void setNumero(String numero) {this.numero = numero;}

    /**
     * Obtiene el o los nombres de Paciente.
     * @return Nombre o nombres del paciente.
     */
    public String getNombres() {return nombres;}

    /**
     * Obtiene el apellido paterno de Paciente.
     * @return Apellido paterno del paciente.
     */
    public String getApellidoPaterno() {return apellidoPaterno;}

    /**
     * Obtiene el apellido materno de Paciente.
     * @return Apellido materno del paciente.
     */
    public String getApellidoMaterno() {return apellidoMaterno;}

    /**
     * Obtiene el teléfono de Paciente.
     * @return Teléfono del paciente.
     */
    public String getTelefono() {return telefono;}

    /**
     * Obtiene el estado de Paciente.
     * @return Estado del paciente.
     */
    public String getEstado() {return estado;}

    /**
     * Obtiene la fecha de nacimiento de Paciente.
     * @return Fecha de nacimiento del paciente.
     */
    public LocalDate getFechaNacimiento() {return fechaNacimiento;}

    /**
     * Obtiene la colonia de la dirección de Paciente.
     * @return Colonia de la dirección del paciente.
     */
    public String getColonia() {return colonia;}

    /**
     * Obtiene la calle de la dirección de Paciente.
     * @return Calle de la dirección del paciente.
     */
    public String getCalle() {return calle;}

    /**
     * Obtiene el número de casa de la dirección de Paciente.
     * @return Número de casa de la dirección del paciente.
     */
    public String getNumero() {return numero;}

    /**
     * Cadena de texto que imprime todos los parámetros de Paciente en consola.
     * @return Cadena de texto de los datos del paciente, tipo String.
     */
    @Override
    public String toString() {
        return "Paciente{" + "nombres=" + nombres
                + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno="
                + apellidoMaterno + ", telefono=" + telefono + ", fechaNacimiento="
                + fechaNacimiento + ", estado=" + estado + ", colonia=" + colonia
                + ", calle=" + calle + ", numero=" + numero + '}';
    }  
}