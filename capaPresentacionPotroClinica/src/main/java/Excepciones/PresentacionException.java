package Excepciones;

/**
 *
 * @author multaslokas33
 */
public class PresentacionException extends Exception{

    public PresentacionException(String message) {
        super(message);
    }

    public PresentacionException(String message, Throwable cause) {
        super(message, cause);
    }
}