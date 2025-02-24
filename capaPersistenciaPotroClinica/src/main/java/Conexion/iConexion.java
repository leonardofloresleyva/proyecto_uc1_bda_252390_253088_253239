package Conexion;

import Excepciones.PersistenciaException;
import java.sql.Connection;

/**
 * Interfaz de la conexión.
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Eduardo Uribe Vega (253239)
 */
public interface iConexion {
    
    /**
     * Método que crea la conexión con la base de datos MySQL.
     * @return Conexión creada
     * @throws PersistenciaException Si no se pudo crear la conexión
     */
    public Connection crearConexion() throws PersistenciaException;
    
}