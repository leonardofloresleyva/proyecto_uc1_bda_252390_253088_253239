package PruebasNegocio;

import BO.PacienteBO;
import DTO.PacienteNuevoDTO;
import Excepciones.NegocioException;
import java.time.LocalDate;
/**
 *
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public class CapaNegocioPotroClinica {

    public static void main(String[] args) {
        PacienteBO pacienteBO = new PacienteBO(null);

        probarRegistro(null, "Paciente nulo", pacienteBO);
        probarRegistro(new PacienteNuevoDTO("", "password", "Paciente", "Juan", "Perez", "Lopez", "1234567890", LocalDate.of(2000, 1, 1), "Estado", "Colonia", "Calle", "123"), "Correo vacío", pacienteBO);
        probarRegistro(new PacienteNuevoDTO("correo_demasiado_largo@ejemplo.commmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmmm.com", "password", "Paciente", "Juan", "Perez", "Lopez", "1234567890", LocalDate.of(2000, 1, 1), "Estado", "Colonia", "Calle", "123"), "Correo demasiado largo", pacienteBO);
        probarRegistro(new PacienteNuevoDTO("correo_invalido", "password", "Paciente", "Juan", "Perez", "Lopez", "1234567890", LocalDate.of(2000, 1, 1), "Estado", "Colonia", "Calle", "123"), "Formato de correo inválido", pacienteBO);
        probarRegistro(new PacienteNuevoDTO("correo@ejemplo.com", "", "Paciente", "Juan", "Perez", "Lopez", "1234567890", LocalDate.of(2000, 1, 1), "Estado", "Colonia", "Calle", "123"), "Contraseña vacía", pacienteBO);
        probarRegistro(new PacienteNuevoDTO("correo@ejemplo.com", "password_demasiado_larga12345678901234567890", "Paciente", "Juan", "Perez", "Lopez", "1234567890", LocalDate.of(2000, 1, 1), "Estado", "Colonia", "Calle", "123"), "Contraseña demasiado larga", pacienteBO);
        probarRegistro(new PacienteNuevoDTO("correo@ejemplo.com", "password", "", "Juan", "Perez", "Lopez", "1234567890", LocalDate.of(2000, 1, 1), "Estado", "Colonia", "Calle", "123"), "Rol vacío", pacienteBO);
        probarRegistro(new PacienteNuevoDTO("correo@ejemplo.com", "password", "Admin", "Juan", "Perez", "Lopez", "1234567890", LocalDate.of(2000, 1, 1), "Estado", "Colonia", "Calle", "123"), "Rol incorrecto", pacienteBO);
        probarRegistro(new PacienteNuevoDTO("correo@ejemplo.com", "password", "Paciente", "", "Perez", "Lopez", "1234567890", LocalDate.of(2000, 1, 1), "Estado", "Colonia", "Calle", "123"), "Nombre vacío", pacienteBO);
        probarRegistro(new PacienteNuevoDTO("correo@ejemplo.com", "password", "Paciente", "Juan@123", "Perez", "Lopez", "1234567890", LocalDate.of(2000, 1, 1), "Estado", "Colonia", "Calle", "123"), "Nombre con caracteres especiales", pacienteBO);
        probarRegistro(new PacienteNuevoDTO("correo@ejemplo.com", "password", "Paciente", "Juan", "Perez", "Lopez", "1234567890", null, "Estado", "Colonia", "Calle", "123"), "Fecha de nacimiento vacía", pacienteBO);
        probarRegistro(new PacienteNuevoDTO("correo@ejemplo.com", "password", "Paciente", "Juan", "Perez", "Lopez", "1234567890", LocalDate.of(2100, 1, 1), "Estado", "Colonia", "Calle", "123"), "Fecha de nacimiento futura", pacienteBO);
    }

    private static void probarRegistro(PacienteNuevoDTO paciente, String caso, PacienteBO pacienteBO) {
        try {
            pacienteBO.registrarPaciente(paciente);
            System.out.println("Error: " + caso + " no lanzó excepción");
        } catch (NegocioException e) {
            System.out.println("Éxito: " + caso + " lanzó excepción: " + e.getMessage());
        }
    }
}