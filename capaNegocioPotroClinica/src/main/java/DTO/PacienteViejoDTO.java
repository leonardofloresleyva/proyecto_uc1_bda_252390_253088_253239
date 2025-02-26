package DTO;

import java.time.LocalDate;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class PacienteViejoDTO extends UsuarioViejoDTO{
    // Atributos del Paciente.
    private String nombres, apellidoPaterno, apellidoMaterno, telefono, estado;
    private LocalDate fechaNacimiento;
    // Atributos de la direccion del paciente.
    private String colonia, calle, numero;

    
    // Constructor por defecto de la clase PacienteViejoDTO.
    public PacienteViejoDTO() {}

    /**
     * Constructor que inicializa todos los atributos del paciente.
     * @param id Identificador del paciente.
     * @param usuario Nombre de usuario del paciente.
     * @param contrasenia Contraseña del paciente.
     * @param nombres Nombre(s) del paciente.
     * @param apellidoPaterno Apellido paterno del paciente.
     * @param apellidoMaterno Apellido materno del paciente.
     * @param telefono Teléfono de contacto del paciente.
     * @param fechaNacimiento Fecha de nacimiento del paciente.
     * @param estado Estado actual del paciente en el sistema.
     * @param colonia Colonia de residencia del paciente.
     * @param calle Calle de residencia del paciente.
     * @param numero Número de residencia del paciente.
     */
    public PacienteViejoDTO(
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
        this.estado = estado;
        this.fechaNacimiento = fechaNacimiento;
        this.colonia = colonia;
        this.calle = calle;
        this.numero = numero;
    }

    /**
     * Constructor que inicializa los atributos sin ID de usuario.
     * 
     * @param usuario Nombre de usuario del paciente.
     * @param contrasenia Contraseña del paciente.
     * @param nombres Nombre(s) del paciente.
     * @param apellidoPaterno Apellido paterno del paciente.
     * @param apellidoMaterno Apellido materno del paciente.
     * @param telefono Teléfono de contacto del paciente.
     * @param fechaNacimiento Fecha de nacimiento del paciente.
     * @param estado Estado actual del paciente en el sistema.
     * @param colonia Colonia de residencia del paciente.
     * @param calle Calle de residencia del paciente.
     * @param numero Número de residencia del paciente.
     */
    public PacienteViejoDTO(
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
        this.estado = estado;
        this.fechaNacimiento = fechaNacimiento;
        this.colonia = colonia;
        this.calle = calle;
        this.numero = numero;
    }
    
    /**
     * Constructor que inicializa los atributos básicos del paciente sin credenciales.
     * 
     * @param nombres Nombre(s) del paciente.
     * @param apellidoPaterno Apellido paterno del paciente.
     * @param apellidoMaterno Apellido materno del paciente.
     * @param telefono Teléfono de contacto del paciente.
     * @param fechaNacimiento Fecha de nacimiento del paciente.
     * @param colonia Colonia de residencia del paciente.
     * @param calle Calle de residencia del paciente.
     * @param numero Número de residencia del paciente.
     */
    public PacienteViejoDTO(
        String nombres, 
        String apellidoPaterno, 
        String apellidoMaterno, 
        String telefono, 
        LocalDate fechaNacimiento, 
        String colonia, 
        String calle, 
        String numero
    ) {
    
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.telefono = telefono;
        this.fechaNacimiento = fechaNacimiento;
        this.colonia = colonia;
        this.calle = calle;
        this.numero = numero;
    }

    // Métodos setters
    public void setNombres(String nombres) {this.nombres = nombres;}

    public void setApellidoPaterno(String apellidoPaterno) {this.apellidoPaterno = apellidoPaterno;}

    public void setApellidoMaterno(String apellidoMaterno) {this.apellidoMaterno = apellidoMaterno;}

    public void setTelefono(String telefono) {this.telefono = telefono;}

    public void setEstado(String estado) {this.estado = estado;}

    public void setFechaNacimiento(LocalDate fechaNacimiento) {this.fechaNacimiento = fechaNacimiento;}

    public void setColonia(String colonia) {this.colonia = colonia;}

    public void setCalle(String calle) {this.calle = calle;}

    public void setNumero(String numero) {this.numero = numero;}

    // Métodos getters
    public String getNombres() {return nombres;}

    public String getApellidoPaterno() {return apellidoPaterno;}

    public String getApellidoMaterno() {return apellidoMaterno;}

    public String getTelefono() {return telefono;}

    public String getEstado() {return estado;}

    public LocalDate getFechaNacimiento() {return fechaNacimiento;}

    public String getColonia() {return colonia;}

    public String getCalle() {return calle;}

    public String getNumero() {return numero;}

    /**
     * Devuelve una representación en cadena del objeto PacienteViejoDTO.
     * @return Cadena con los valores de los atributos del paciente.
     */
    @Override
    public String toString() {
        return "PacienteViejoDTO{" + 
                "nombres=" + nombres + 
                ", apellidoPaterno=" + apellidoPaterno + 
                ", apellidoMaterno=" + apellidoMaterno + 
                ", telefono=" + telefono + 
                ", estado=" + estado + 
                ", fechaNacimiento=" + fechaNacimiento + 
                ", colonia=" + colonia + 
                ", calle=" + calle + 
                ", numero=" + numero + '}';
    }
}