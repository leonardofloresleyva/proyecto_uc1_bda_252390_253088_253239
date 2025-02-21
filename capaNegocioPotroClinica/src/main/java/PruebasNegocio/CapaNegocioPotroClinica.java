package PruebasNegocio;

import BO.PacienteBO;
import Conexion.Conexion;
import Conexion.iConexion;
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

    public static void main(String[] args) throws NegocioException {
        iConexion conexion = new Conexion();
        PacienteBO pacienteBO = new PacienteBO(conexion);
        pacienteBO.registrarPaciente(new PacienteNuevoDTO(
                "josehernandez@gmail.com",
                "1234Jose",
                "Paciente",
                "Jose",
                "Hernandez",
                "Casarez",
                "6441352678",
                LocalDate.of(2005, 2, 15),
                "Alta",
                "Jecopaco",
                "5 de mayo",
                "32"
        ));
        
    }
  
    private static void probarRegistro(PacienteNuevoDTO paciente, String caso, PacienteBO pacienteBO) {
        try {
            boolean registroExitoso = pacienteBO.registrarPaciente(paciente);

            if (registroExitoso) {
                System.out.println("Éxito: " + caso + " - paciente registrado correctamente.");

                // Verificar si la contraseña se encriptó correctamente
                boolean esCorrecta = pacienteBO.verificarContrasenia("password", paciente.getContrasenia());
                System.out.println("Verificación de contraseña: " + (esCorrecta ? "✅ Correcta" : "❌ Incorrecta"));
            } else {
                System.out.println("Error: " + caso + " no lanzó excepción pero no se registró.");
            }

        } catch (NegocioException e) {
            System.out.println("Éxito: " + caso + " lanzó excepción: " + e.getMessage());
        }
    }
}
