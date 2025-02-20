package PruebasPersistencia;

import Conexion.Conexion;
import Conexion.iConexion;
import DAO.CitaDAO;
import DAO.PacienteDAO;
import Entidades.Cita;
import Entidades.Paciente;
import Excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

/**
 * Pruebas de capa Persistencia.
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Uribe (253239)
 */
public class PruebasPersistencia {

    public static void main(String[] args) throws PersistenciaException, SQLException {
        // Establecer la conexión
        iConexion conexion = new Conexion();
        // PacienteDAO pacienteDAO = new PacienteDAO(conexion);
        CitaDAO citaDAO = new CitaDAO(conexion);
        
        // Probar la conexion
        try(Connection con = conexion.crearConexion()) {
            if (con != null && !con.isClosed()) {
                System.out.println("Conexion exitosa a la base de datos");
            } else {
                System.out.println("No se pudo establecer la conexion");
            }
        } catch (PersistenciaException e) {
            System.err.println("Error en la conexión: " + e.getMessage());
            e.printStackTrace();
        }
        
        // Crear paciente
        // Paciente paciente = new Paciente("usuario@gmail.com", "876589", "Paciente", "Pepe", "Pecas", "Mendez", "55598888", LocalDate.of(2002, 5, 11), "Alta", "Ciudad Gótica", "Calle Pio", "9944");
        // boolean registrado = pacienteDAO.registrarPaciente(paciente);
        
//        if (registrado) {
//                System.out.println("Paciente registrado con éxito.");
//        } else {
//            System.out.println("Error al registrar el paciente.");
//        }
        
        // Crear cita 
        Cita cita = new Cita(LocalDate.now(), 1, 1, "No atendida", "Emergencia");
        boolean registrada = citaDAO.registrarCita(cita);
        
        if (registrada) {
            System.out.println("Cita registrada con exito");
        } else {
            System.out.println("Error al registrar la cita");
        }

    }
}