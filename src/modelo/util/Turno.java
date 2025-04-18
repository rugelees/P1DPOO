package modelo.util;

/**
 * Clase de utilidad que define los turnos de trabajo en el parque
 */
public class Turno {
    public static final String APERTURA = "Apertura";
    public static final String CIERRE = "Cierre";
    
    /**
     * Verifica si un turno es válido
     * 
     * @param turno El turno a verificar
     * @return true si el turno es válido, false de lo contrario
     */
    public static boolean esValido(String turno) {
        return turno.equals(APERTURA) || turno.equals(CIERRE);
    }
}