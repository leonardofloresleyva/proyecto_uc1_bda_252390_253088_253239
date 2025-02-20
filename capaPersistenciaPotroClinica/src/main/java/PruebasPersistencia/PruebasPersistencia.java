package PruebasPersistencia;

import Conexion.Conexion;
import Conexion.iConexion;
import DAO.PacienteDAO;
import Entidades.Paciente;
import Excepciones.PersistenciaException;
import java.sql.Connection;
import java.util.Date;
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
        PacienteDAO pacienteDAO = new PacienteDAO(conexion);
        
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
        
        // Crear paciente con el ID de usuario
        Paciente paciente = new Paciente("usuario12@gmail.com", "12344", "Paciente", "Juan", "Pérez", "Gómez", "555-1234", LocalDate.of(2005, 2, 12), "Alta", "Urbi Villa", "De las Espadas", "3322");
        boolean registrado = pacienteDAO.registrarPaciente(paciente);
        
        if (registrado) {
                System.out.println("Paciente registrado con éxito.");
        } else {
            System.out.println("Error al registrar el paciente.");
        }

    }
}