package excepciones;

/**
 * Excepción para errores relacionados con empleados
 */
public class EmpleadoException extends Exception {
    private static final long serialVersionUID = 1L;
    
    /**
     * Constructor con mensaje de error
     * 
     * @param mensaje Mensaje que describe el error
     */
    public EmpleadoException(String mensaje) {
        super(mensaje);
    }
    
    /**
     * Constructor con mensaje y causa
     * 
     * @param mensaje Mensaje que describe el error
     * @param causa Causa original del error
     */
    public EmpleadoException(String mensaje, Throwable causa) {
        super(mensaje, causa);
    }
}
