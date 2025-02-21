package Conexion;

import Excepciones.PersistenciaException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clase que implementa la interfaz de conexión
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Uribe (253239)
 */
public class Conexion implements iConexion {
    
    final String URL = "jdbc:mysql://localhost:3306/potro_clinica";
    final String USER = "root";
    final String PASS = "itson";

    /**
     * Método que crea la conexión con MySQL
     * @return Conexión creada
     * @throws PersistenciaException Si no se pudo crear la conexión
     */
    @Override
    public Connection crearConexion() throws PersistenciaException {
        try {
            Connection conexion = DriverManager.getConnection(URL, USER, PASS);
            return conexion;
        } catch (SQLException e) {
            Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, e);
            throw new PersistenciaException("Error en la conexion");
        }
    }
     
}