package modelo.util;

public class NivelExclusividad {
    public static final String FAMILIAR = "Familiar";
    public static final String ORO = "Oro";
    public static final String DIAMANTE = "Diamante";
    
    /**
     * Verifica si un nivel de exclusividad es válido
     * 
     * @param nivel El nivel a verificar
     * @return true si el nivel es válido, false de lo contrario
     */
    public static boolean esValido(String nivel) {
        return nivel.equals(FAMILIAR) || nivel.equals(ORO) || nivel.equals(DIAMANTE);
    }
    

    public static boolean tieneAcceso(String nivelTiquete, String nivelAtraccion) {
        if (nivelTiquete.equals(DIAMANTE)) {
            return true; // Diamante tiene acceso a todo
        } else if (nivelTiquete.equals(ORO)) {
            return nivelAtraccion.equals(FAMILIAR) || nivelAtraccion.equals(ORO);
        } else if (nivelTiquete.equals(FAMILIAR)) {
            return nivelAtraccion.equals(FAMILIAR);
        }
        return false;
    }
}