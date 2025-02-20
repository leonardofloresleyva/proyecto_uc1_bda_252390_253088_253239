package Entidades;

import java.time.LocalDate;

/**
 * Clase constructora de un Paciente
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Uribe (253239)
 */
public class Paciente extends Usuario {
    
    private int idPaciente;
    private String nombres;
    private String apellidoPaterno;
    private String apellidoMaterno;
    private String telefono;
    private LocalDate fechaNacimiento;
    private String estado;
    // Agregar atributos de dirección que se pasarán a tabla Dirección con un procedimiento almacenado
    private String colonia;
    private String calle;
    private String numero;

    public Paciente() {
    }

    public Paciente(int id, String usuario, String contrasenia, String rol, String nombres, String apellidoPaterno, String apellidoMaterno, String telefono, LocalDate fechaNacimiento, String estado, String colonia, String calle, String numero) {
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

    public Paciente(String usuario, String contrasenia, String rol, String nombres, String apellidoPaterno, String apellidoMaterno, String telefono, LocalDate fechaNacimiento, String estado, String colonia, String calle, String numero) {
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

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getColonia() {
        return colonia;
    }

    public void setColonia(String colonia) {
        this.colonia = colonia;
    }

    public String getCalle() {
        return calle;
    }

    public void setCalle(String calle) {
        this.calle = calle;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    @Override
    public String toString() {
        return "Paciente{" + "idPaciente=" + idPaciente + ", nombres=" + nombres
                + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno="
                + apellidoMaterno + ", telefono=" + telefono + ", fechaNacimiento="
                + fechaNacimiento + ", estado=" + estado + ", colonia=" + colonia
                + ", calle=" + calle + ", numero=" + numero + '}';
    }
   
}