package BO;

import Conexion.iConexion;
import DAO.PacienteDAO;
import DAO.iPacienteDAO;
import DTO.ConsultaDTO;
import DTO.PacienteNuevoDTO;
import DTO.PacienteViejoDTO;
import Entidades.Paciente;
import Excepciones.NegocioException;
import Excepciones.PersistenciaException;
import Mapper.CitaMapper;
import Mapper.ConsultaMapper;
import Mapper.MedicoMapper;
import Mapper.PacienteMapper;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Pattern;
import org.apache.commons.validator.routines.EmailValidator;

/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class PacienteBO {

    private final iPacienteDAO pacienteDAO;
    private final PacienteMapper pacienteMapper;
    private final ConsultaMapper consultaMapper;

    /**
     * Constructor que inicializa los mappers y el DAO.
     * @param conexion Objeto de conexión a la base de datos.
     */
    public PacienteBO(iConexion conexion) {
        this.pacienteDAO = new PacienteDAO(conexion);
        this.pacienteMapper = new PacienteMapper();
        this.consultaMapper = new ConsultaMapper(new CitaMapper(new MedicoMapper(), pacienteMapper));
    }

    /**
     * Inicia sesión para un paciente.
     * @param correo Correo electrónico del paciente.
     * @param contrasenia Contraseña del paciente.
     * @return Objeto DTO con los datos del paciente.
     * @throws NegocioException Si ocurre un error en la validación o acceso a datos.
     */
    public PacienteViejoDTO iniciarSesion(String correo, String contrasenia) throws NegocioException {
        validarUsuario(correo, contrasenia); // Validamos los datos antes de continuar

        try {
            Paciente paciente = pacienteDAO.iniciarSesionPaciente(correo, contrasenia);
            return pacienteMapper.toDTOViejo(paciente);
        } catch (PersistenciaException ex) {
            throw new NegocioException("Error al iniciar sesión: " + ex.getMessage(), ex);
        }
    }

    /**
     * Registra un nuevo paciente en el sistema.
     * @param paciNuevo Datos del nuevo paciente.
     * @return true si el registro fue exitoso.
     * @throws NegocioException Si hay errores en los datos o en la persistencia.
     */
    public boolean registrarPaciente(PacienteNuevoDTO paciNuevo) throws NegocioException {

        if (paciNuevo == null) {
            throw new NegocioException("El paciente no puede ser nulo.");
        }

        // Extracción de datos del DTO
        String correo = paciNuevo.getUsuario(),
                password = paciNuevo.getContrasenia(),
                nombre = paciNuevo.getNombres(),
                apellidoP = paciNuevo.getApellidoPaterno(),
                apellidoM = paciNuevo.getApellidoMaterno(),
                telefono = paciNuevo.getTelefono();

        LocalDate fechaN = paciNuevo.getFechaNacimiento();

        String colonia = paciNuevo.getColonia(),
                calle = paciNuevo.getCalle(),
                numero = paciNuevo.getNumero();

        validarUsuario(correo, password);

        validarDatosPaciente(
                nombre,
                apellidoP,
                apellidoM,
                telefono,
                fechaN,
                colonia,
                calle,
                numero
        );

        if (password.equalsIgnoreCase(correo) || password.equalsIgnoreCase(nombre)) {
            throw new NegocioException("La contraseña no puede ser igual al correo o al nombre");
        }

        //String contraseniaEncriptada = encriptarContrasenia(password);
        //paciNuevo.setContrasenia(contraseniaEncriptada);
        Paciente paciente = pacienteMapper.toEntityNuevo(paciNuevo);

        try {
            pacienteDAO.registrarPaciente(paciente);
            return true;
        } catch (PersistenciaException ex) {
            Logger.getLogger(PacienteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Hubo un error al guardar en la base de datos", ex);
        }
    }

    /**
     * Actualiza los datos de un paciente existente.
     * @param paciViejo Objeto DTO con los datos actualizados del paciente.
     * @return true si la actualización fue exitosa.
     * @throws NegocioException Si los datos son inválidos o hay un error en la persistencia.
     */
    public boolean actualizarDatosPaciente(PacienteViejoDTO paciViejo) throws NegocioException {
        validarDatosPaciente(
                paciViejo.getNombres(),
                paciViejo.getApellidoPaterno(),
                paciViejo.getApellidoMaterno(),
                paciViejo.getTelefono(),
                paciViejo.getFechaNacimiento(),
                paciViejo.getColonia(),
                paciViejo.getCalle(),
                paciViejo.getNumero()
        );
        Paciente paciente = pacienteMapper.toEntityViejo(paciViejo);
        try {
            return pacienteDAO.actualizarPaciente(paciente);
        } catch (PersistenciaException ex) {
            Logger.getLogger(PacienteBO.class.getName()).log(Level.SEVERE, null, ex);
            throw new NegocioException("Error: " + ex.getMessage());
        }
    }

    /**
     * Obtiene la lista de consultas de un paciente según la especialidad.
     * @param correo Correo del paciente.
     * @param especialidad Especialidad médica de las consultas.
     * @return Lista de consultas en formato DTO.
     * @throws NegocioException Si los datos son inválidos o hay un error en la consulta.
     */
    public List<ConsultaDTO> consultasEspecialidad(String correo, String especialidad) throws NegocioException {
        if (correo == null || correo.trim().isEmpty()) {
            throw new NegocioException("El correo no puede estar vacio.");
        }

        if (correo.length() > 150) {
            throw new NegocioException("No se permiten correos con mas de 150 caracteres.");
        }

        if (!EmailValidator.getInstance().isValid(correo)) {
            throw new NegocioException("El correo ingresado no es válido.");
        }

        if (correo.split("@")[0].length() < 2) {
            throw new NegocioException("El correo debe tener al menos dos caracteres antes del '@'");
        }

        if (!Pattern.matches("^[^@\\s]+@[^@\\s]+\\.com$", correo)) {
            throw new NegocioException("La cadena ingresada no es un correo electronico.");
        }

        if (especialidad == null || especialidad.trim().isEmpty()) {
            throw new NegocioException("La especialidad no puede estar vacia.");
        }

        if (especialidad.length() > 50) {
            throw new NegocioException("La especialidad no puede tener mas de 50 caracteres.");
        }

        try {
            return consultaMapper.consultasDTO(pacienteDAO.consultarConsultasPorEspecialidad(correo, especialidad));

        } catch (PersistenciaException ex) {

            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    /**
     * Obtiene la lista de consultas de un paciente en un rango de fechas.
     * @param correo Correo del paciente.
     * @param fechaInicio Fecha de inicio del rango.
     * @param fechaFinal Fecha final del rango.
     * @return Lista de consultas en formato DTO.
     * @throws NegocioException Si los datos son inválidos o hay un error en la consulta.
     */
    public List<ConsultaDTO> consultaFechas(String correo, LocalDate fechaInicio, LocalDate fechaFinal) throws NegocioException {
        if (correo == null || correo.trim().isEmpty()) {
            throw new NegocioException("El correo no puede estar vacio.");
        }

        if (correo.length() > 150) {
            throw new NegocioException("No se permiten correos con mas de 150 caracteres.");
        }

        if (!EmailValidator.getInstance().isValid(correo)) {
            throw new NegocioException("El correo ingresado no es válido.");
        }

        if (correo.split("@")[0].length() < 2) {
            throw new NegocioException("El correo debe tener al menos dos caracteres antes del '@'");
        }

        if (!Pattern.matches("^[^@\\s]+@[^@\\s]+\\.com$", correo)) {
            throw new NegocioException("La cadena ingresada no es un correo electronico.");
        }

        if (fechaInicio == null) {
            throw new NegocioException("La fecha de inicio no puede estar vacia.");
        }

        if (fechaFinal == null) {
            throw new NegocioException("La fecha final no puede estar vacia.");
        }

        if (fechaFinal.isAfter(LocalDate.now())) {
            throw new NegocioException("La fecha final no puede ser mayor a la fecha actual.");
        }

        if (fechaInicio.isAfter(LocalDate.now())) {
            throw new NegocioException("La fecha de inicio no puede ser mayor a la fecha actual.");
        }

        if (fechaInicio.isEqual(fechaFinal) || fechaInicio.isAfter(fechaFinal)) {
            throw new NegocioException("La fecha de inicio no puede ser igual o menor a la fecha final.");
        }

        try {
            return consultaMapper.consultasDTO(pacienteDAO.consultarConsultasRangoDeFechas(correo, fechaInicio, fechaFinal));

        } catch (PersistenciaException ex) {

            throw new NegocioException(ex.getMessage(), ex);
        }
    }

    /**
     * Valida el correo y la contraseña de un usuario.
     * @param correo Correo del usuario.
     * @param password Contraseña del usuario.
     * @throws NegocioException Si los datos no cumplen con los requisitos.
     */
    public void validarUsuario(String correo, String password) throws NegocioException {
        //Todas las validaciones al validar los datos del usuario
        if (correo == null || correo.trim().isEmpty()) {
            throw new NegocioException("El correo no puede estar vacio.");
        }

        if (correo.length() > 150) {
            throw new NegocioException("No se permiten correos con mas de 150 caracteres.");
        }

        if (!EmailValidator.getInstance().isValid(correo)) {
            throw new NegocioException("El correo ingresado no es válido.");
        }

        if (correo.split("@")[0].length() < 2) {
            throw new NegocioException("El correo debe tener al menos dos caracteres antes del '@'");
        }

        if (!Pattern.matches("^[^@\\s]+@[^@\\s]+\\.com$", correo)) {
            throw new NegocioException("La cadena ingresada no es un correo electronico.");
        }

        if (password == null || password.trim().isEmpty()) {
            throw new NegocioException("La contrasenia no puede estar vacia.");
        }

        if (password.length() > 20) {
            throw new NegocioException("No se permiten contrasenias con mas de 20 caracteres.");
        }

        if (!password.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d).+$")) {
            throw new NegocioException("La contraseña debe contener al menos una mayúscula, una minúscula y un número");
        }

        if (password.matches(".*(.)\\1{2,}.*")) {
            throw new NegocioException("La contraseña no puede contener secuencias repetitivas");
        }

        if (password.contains(" ")) {
            throw new NegocioException("La contraseña no debe contener espacios en ninguna parte");
        }

    }

    
    //Valida los datos de un paciente antes de registrarlo o actualizarlo.
    private void validarDatosPaciente(
            String nombre,
            String apellidoP,
            String apellidoM,
            String telefono,
            LocalDate fechaN,
            String colonia,
            String calle,
            String numero
    ) throws NegocioException {
        //Todas las validaciones al validar los datos del paciente
        if (nombre == null || nombre.trim().isEmpty()) {
            throw new NegocioException("El nombre no puede estar vacio.");
        }

        if (!Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", nombre)) {
            throw new NegocioException("No se admiten caracteres especiales en el nombre.");
        }

        if (nombre.length() > 100) {
            throw new NegocioException("No se permiten nombres con mas de 100 caracteres.");
        }

        if (nombre.length() < 2 || nombre.length() > 50) {
            throw new NegocioException("El nombre debe tener entre 2 y 50 caracteres");
        }

        if (!nombre.trim().equals(nombre)) {
            throw new NegocioException("El nombre no debe contener espacios al inicio o al final");
        }

        if (apellidoP == null || apellidoP.trim().isEmpty()) {
            throw new NegocioException("El apellido paterno no puede estar vacío.");
        }

        if (!Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", apellidoP)) {
            throw new NegocioException("No se admiten caracteres especiales en el apellido paterno");
        }

        if (apellidoP.length() > 100) {
            throw new NegocioException("No se permiten apellidos paternos con mas de 100 caracteres.");
        }

        if (apellidoM == null || apellidoM.trim().isEmpty()) {
            throw new NegocioException("El apellido materno no puede estar vacío.");
        }

        if (!Pattern.matches("^[a-zA-ZáéíóúÁÉÍÓÚñÑ ]+$", apellidoM)) {
            throw new NegocioException("No se admiten caracteres especiales en el apellido materno");
        }

        if (apellidoM.length() > 100) {
            throw new NegocioException("No se permiten apellidos maternos con mas de 100 caracteres.");
        }

        if (telefono == null || telefono.trim().isEmpty()) {
            throw new NegocioException("El teléfono no puede estar vacío.");
        }

        if (telefono.length() > 20) {
            throw new NegocioException("No se permiten telefonos con mas de 20 caracteres.");
        }

        if (fechaN == null) {
            throw new NegocioException("La fecha de nacimiento no puede estar vacia.");
        }

        if (fechaN.isAfter(LocalDate.now())) {
            throw new NegocioException("La fecha de nacimiento no puede estar después de la fecha actual.");
        }

        int edad = LocalDate.now().getYear() - fechaN.getYear();
        if (edad < 0 || edad > 120) {
            throw new NegocioException("La edad debe estar entre 0 y 120 años.");
        }

        if (colonia == null) {
            throw new NegocioException("La colonia no puede estar vacía.");
        }

        if (colonia.length() > 100) {
            throw new NegocioException("La colonia no puede tener mas de 100 caracteres.");
        }

        if (!colonia.matches("^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ #,-.]+$")) {
            throw new NegocioException("La colonia contiene caracteres inválidos.");
        }

        if (calle == null) {
            throw new NegocioException("La calle no puede estar vacía.");
        }

        if (calle.length() > 100) {
            throw new NegocioException("La calle no puede tener mas de 100 caracteres.");
        }

        if (!calle.matches("^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ #,-.]+$")) {
            throw new NegocioException("La calle contiene caracteres inválidos.");
        }

        if (numero == null) {
            throw new NegocioException("El numero no puede estar vacío.");
        }

        if (numero.length() > 20) {
            throw new NegocioException("El numero no puede tener mas de 20 caracteres.");
        }

        if (!numero.matches("^[a-zA-Z0-9áéíóúÁÉÍÓÚñÑ #,-.]+$")) {
            throw new NegocioException("El numero contiene caracteres inválidos.");
        }
    }
}
