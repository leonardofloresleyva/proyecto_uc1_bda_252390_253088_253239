package PruebasPersistencia;

import Conexion.Conexion;
import Conexion.iConexion;
import DAO.CitaDAO;
import DAO.ConsultaDAO;
import Entidades.Cita;
import Entidades.CitaEmergencia;
import Entidades.Consulta;
import Excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

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
        // ConsultaDAO consultaDAO = new ConsultaDAO(conexion);
//        CitaDAO citas = new CitaDAO(conexion);
//        List<Cita> citasPaciente = citas.consultarCitasRangoDeFechas(LocalDate.of(2024, 10, 7), LocalDate.of(2024, 12, 10));
//        for(Cita cita : citasPaciente){
//            System.out.println(cita.getFechaHora().toString());
//        }
        ConsultaDAO consultas = new ConsultaDAO(conexion);
        Cita cita = new CitaEmergencia(1, LocalDateTime.of(2025, 2, 22, 0, 34, 02), 9, 1, "No atendida", 12345678);
        Consulta consulta = new Consulta("Cancer", "El paciente tiene cancer", "100 quimioterapias al anio", cita);
        if(consultas.registrarConsulta(consulta))
            System.out.println("Registro exitoso.");
        else
            System.out.println("Error en registro de consulta");
        

        // PacienteDAO paciente = new PacienteDAO(conexion);
        
//        // Probar la conexion
//        try(Connection con = conexion.crearConexion()) {
//            if (con != null && !con.isClosed()) {
//                System.out.println("Conexion exitosa a la base de datos");
//            } else {
//                System.out.println("No se pudo establecer la conexion");
//            }
//        } catch (PersistenciaException e) {
//            System.err.println("Error en la conexión: " + e.getMessage());
//            e.printStackTrace();
//        }
//        
//        // Crear paciente
////         Paciente paciente2 = new Paciente("usuario@gmail.com", "876589", "Paciente", "Pepe", "Pecas", "Mendez", "55598888", LocalDate.of(2002, 5, 11), "Alta", "Ciudad Gótica", "Calle Pio", "9944");
////         boolean registrado = paciente.registrarPaciente(paciente2);
////        
////        if (registrado) {
////                System.out.println("Paciente registrado con éxito.");
////        } else {
////            System.out.println("Error al registrar el paciente.");
////        }
//        
//        // Crear cita 
//        Cita cita = new Cita(LocalDateTime.of(2025, 2, 26, 17, 0, 0), 5, 9, "Previa");
//        boolean registrada = citaDAO.registrarCita(cita);
//        
//        if (registrada) {
//            System.out.println("Cita registrada con exito");
//        } else {
//            System.out.println("Error al registrar la cita");
//        }
//
//        // Registrar una nueva consulta
//        Consulta consulta = new Consulta("Migraña", "Dolor de cabeza fuerte. Náuseas o vómito. Sensibilidad a la luz y al sonido", "Analgésicos comunes: Ibuprofeno o paracetamol.", 5);
//        boolean consultado = consultaDAO.registrarConsulta(consulta);
//        
//
//        if (consultado) {
//            System.out.println("Consulta registrada con exito");
//        } else {
//            System.out.println("Error al registrar la consulta");
//        }
//        
//        // Consultas historial de citas por especialidad del médico
//        List<Cita> citasEspecialidad = citaDAO.consultarCitasPorEspecialidad("Medicina General");
//        
//        if (citasEspecialidad.isEmpty()) {
//            System.out.println("No hay citas registrada.");
//        } else {
//            System.out.println("Citas registradas:");
//            for (Cita c: citasEspecialidad) {
//                System.out.println(c); // imprime todas las citas encontradas
//            }
//        }

    }
}