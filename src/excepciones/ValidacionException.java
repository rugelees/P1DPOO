package excepciones;

/**
 * Excepción para errores de validación de datos
 */
public class ValidacionException extends Exception {
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor con mensaje de error
     * 
     * @param mensaje Mensaje que describe el error
     */
    public ValidacionException(String mensaje) {
        super(mensaje);
    }
    
    /**
     * Constructor con mensaje y causa
     * 
     * @param mensaje Mensaje que describe el error
     * @param causa Causa original del error
     */
    public ValidacionException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}