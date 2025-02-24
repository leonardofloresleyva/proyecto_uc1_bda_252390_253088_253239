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

    public MedicoViejoDTO() {}

    public MedicoViejoDTO(int id, String usuario, String contrasenia, String nombres, String apellidoPaterno, String apellidoMaterno, String especialidad, String estado) {
        super(id, usuario, contrasenia, "Medico");
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.especialidad = especialidad;
        this.estado = estado;
    }

    public MedicoViejoDTO(String usuario, String contrasenia, String nombres, String apellidoPaterno, String apellidoMaterno, String especialidad, String estado) {
        super(usuario, contrasenia, "Medico");
        this.nombres = nombres;
        this.apellidoPaterno = apellidoPaterno;
        this.apellidoMaterno = apellidoMaterno;
        this.especialidad = especialidad;
        this.estado = estado;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public void setApellidoPaterno(String apellidoPaterno) {
        this.apellidoPaterno = apellidoPaterno;
    }

    public void setApellidoMaterno(String apellidoMaterno) {
        this.apellidoMaterno = apellidoMaterno;
    }

    public void setEspecialidad(String especialidad) {
        this.especialidad = especialidad;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombres() {
        return nombres;
    }

    public String getApellidoPaterno() {
        return apellidoPaterno;
    }

    public String getApellidoMaterno() {
        return apellidoMaterno;
    }

    public String getEspecialidad() {
        return especialidad;
    }

    public String getEstado() {
        return estado;
    }

    @Override
    public String toString() {
        return "MedicoViejoDTO{" + "nombres=" + nombres + ", apellidoPaterno=" + apellidoPaterno + ", apellidoMaterno=" + apellidoMaterno + ", especialidad=" + especialidad + ", estado=" + estado + '}';
    }
}