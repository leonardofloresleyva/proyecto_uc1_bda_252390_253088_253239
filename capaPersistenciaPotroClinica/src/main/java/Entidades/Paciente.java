package Entidades;

import java.time.LocalDate;

/**
 * Clase constructora de un Paciente
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Uribe (253239)
 */
public class Paciente extends Usuario {
    // Atributos de un Paciente.
    private String nombres, apellidoPaterno, apellidoMaterno, telefono, estado;
    private LocalDate fechaNacimiento;
    // Atributos de la dirección que se pasan a tabla Dirección a través de un procedimiento almacenado.
    private String colonia, calle, numero;

    public Paciente() {}

    public Paciente(
            int id, 
            String usuario, 
            String contrasenia, 
            String rol, 
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
        super(id, usuario, contrasenia, rol);
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

    public Paciente(
            String usuario, 
            String contrasenia, 
            String rol, 
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
        super(usuario, contrasenia, rol);
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

    public void setNombres(String nombres) {this.nombres = nombres;}

    public void setApellidoPaterno(String apellidoPaterno) {this.apellidoPaterno = apellidoPaterno;}

    public void setApellidoMaterno(String apellidoMaterno) {this.apellidoMaterno = apellidoMaterno;}

    public void setTelefono(String telefono) {this.telefono = telefono;}

    public void setEstado(String estado) {this.estado = estado;}

    public void setFechaNacimiento(LocalDate fechaNacimiento) {this.fechaNacimiento = fechaNacimiento;}

    public void setColonia(String colonia) {this.colonia = colonia;}

    public void setCalle(String calle) {this.calle = calle;}

    public void setNumero(String numero) {this.numero = numero;}

    public String getNombres() {return nombres;}

    public String getApellidoPaterno() {return apellidoPaterno;}

    public String getApellidoMaterno() {return apellidoMaterno;}

    public String getTelefono() {return telefono;}

    public String getEstado() {return estado;}

    public LocalDate getFechaNacimiento() {return fechaNacimiento;}

    public String getColonia() {return colonia;}

    public String getCalle() {return calle;}

    public String getNumero() {return numero;}

    @Override
    public String toString() {
        return "Paciente{" + "nombres=" + nombres
                + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno="
                + apellidoMaterno + ", telefono=" + telefono + ", fechaNacimiento="
                + fechaNacimiento + ", estado=" + estado + ", colonia=" + colonia
                + ", calle=" + calle + ", numero=" + numero + '}';
    }  
}