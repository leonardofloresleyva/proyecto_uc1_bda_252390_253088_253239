package DTO;

import java.time.LocalDate;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class PacienteNuevoDTO {
    
    // Atributos de Usuario.
    private String usuario, contrasenia, rol;
    // Atributos del paciente como tal.
    private String nombres, apellidoPaterno, apellidoMaterno, telefono, estado;
    private LocalDate fechaNacimiento;
    // Atributos de la direccion del paciente.
    private String colonia, calle, numero;

    public PacienteNuevoDTO() {}

    public PacienteNuevoDTO(
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
            String numero)
    {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.rol = rol;
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
    
    public PacienteNuevoDTO(
            String usuario,
            String contrasenia,
            String nombres, 
            String apellidoPaterno, 
            String apellidoMaterno, 
            String telefono, 
            LocalDate fechaNacimiento,  
            String colonia, 
            String calle, 
            String numero)
    {
        this.usuario = usuario;
        this.contrasenia = contrasenia;
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.colonia = colonia;
        this.calle = calle;
        this.numero = numero;
    }

    public void setUsuario(String usuario) {this.usuario = usuario;}

    public void setContrasenia(String contrasenia) {this.contrasenia = contrasenia;}

    public void setRol(String rol) {this.rol = rol;}

    public void setNombres(String nombres) {this.nombres = nombres;}

    public void setApellidoPaterno(String apellidoPaterno) {this.apellidoPaterno = apellidoPaterno;}

    public void setApellidoMaterno(String apellidoMaterno) {this.apellidoMaterno = apellidoMaterno;}

    public void setTelefono(String telefono) {this.telefono = telefono;}

    public void setFechaNacimiento(LocalDate fechaNacimiento) {this.fechaNacimiento = fechaNacimiento;}

    public void setEstado(String estado) {this.estado = estado;}

    public void setColonia(String colonia) {this.colonia = colonia;}

    public void setCalle(String calle) {this.calle = calle;}

    public void setNumero(String numero) {this.numero = numero;}

    public String getUsuario() {return usuario;}

    public String getContrasenia() {return contrasenia;}

    public String getRol() {return rol;}

    public String getNombres() {return nombres;}

    public String getApellidoPaterno() {return apellidoPaterno;}

    public String getApellidoMaterno() {return apellidoMaterno;}

    public String getTelefono() {return telefono;}

    public LocalDate getFechaNacimiento() {return fechaNacimiento;}

    public String getEstado() {return estado;}

    public String getColonia() {return colonia;}

    public String getCalle() {return calle;}
    
    public String getNumero() {return numero;}
    
    @Override
    public String toString() {
        return "PacienteNuevoDTO{" + "nombres=" + nombres + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", telefono=" + telefono + ", fechaNacimiento=" + fechaNacimiento + ", estado=" + estado + ", colonia=" + colonia + ", calle=" + calle + ", numero=" + numero + '}';
    }
}