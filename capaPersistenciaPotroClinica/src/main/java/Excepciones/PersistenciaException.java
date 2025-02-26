package Excepciones;

/**
 * Clase de excepción para capa de persistencia.
 * @author Leonardo Flores Leyva (252390)
 * @author Ximena Rosales Panduro (253088)
 * @author Luis Uribe (253239)
 */
public class PersistenciaException extends Exception {

    /**
     * Método que construye una excepción de persistencia.
     * @param mensaje Mensaje de error.
     */
    public PersistenciaException(String mensaje) {
        super(mensaje);
    }

    /**
     * Método que construye una excepción de persistencia junto con la causa.
     * @param mensaje Mensaje de error.
     * @param causa Causa del error.
     */
    public PersistenciaException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
    
}