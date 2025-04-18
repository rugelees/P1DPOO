package modelo.atracciones;

import java.util.Date;

import modelo.usuarios.Cliente;

/**
 * Clase que representa una atracción cultural en el parque
 */
public class AtraccionCultural extends Atraccion {
    private static final long serialVersionUID = 1L;
    
    private String ubicacion;
    private int cupoMaximo;
    private int edadMinima;
    
    /**
     * Constructor de AtraccionCultural
     * 
     * @param nombre Nombre de la atracción
     * @param restriccionClima Restricciones climáticas
     * @param deTemporada Indica si la atracción es de temporada
     * @param fechaInicio Fecha de inicio (si es de temporada)
     * @param fechaFin Fecha de fin (si es de temporada)
     * @param nivelExclusividad Nivel de exclusividad de la atracción
     * @param empleadosEncargados Número de empleados necesarios
     * @param ubicacion Ubicación de la atracción en el parque
     * @param cupoMaximo Capacidad máxima de personas
     * @param edadMinima Edad mínima permitida
     */
    public AtraccionCultural(String nombre, String restriccionClima, boolean deTemporada, Date fechaInicio, Date fechaFin,
            String nivelExclusividad, int empleadosEncargados, String ubicacion, int cupoMaximo, int edadMinima) {
        super(nombre, restriccionClima, deTemporada, fechaInicio, fechaFin, nivelExclusividad, empleadosEncargados);
        this.ubicacion = ubicacion;
        this.cupoMaximo = cupoMaximo;
        this.edadMinima = edadMinima;
    }
    
    /**
     * Verifica si un cliente cumple con la restricción de edad
     * 
     * @param cliente El cliente a verificar
     * @return true si el cliente cumple con la restricción, false de lo contrario
     */
    public boolean verificarRestriccionEdad(Cliente cliente) {
        if (cliente == null) {
            return false;
        }
        
        return cliente.getEdad() >= edadMinima;
    }
    
    @Override
    public String consultarInformacion() {
        StringBuilder info = new StringBuilder();
        info.append("Atracción Cultural: ").append(nombre).append("\n");
        info.append("Ubicación: ").append(ubicacion).append("\n");
        info.append("Nivel de Exclusividad: ").append(nivelExclusividad).append("\n");
        info.append("Cupo Máximo: ").append(cupoMaximo).append(" personas\n");
        
        if (edadMinima > 0) {
            info.append("Edad Mínima: ").append(edadMinima).append(" años\n");
        }
        
        if (restriccionClima != null && !restriccionClima.isEmpty()) {
            info.append("Restricciones Climáticas: ").append(restriccionClima).append("\n");
        }
        
        if (deTemporada) {
            info.append("Atracción de Temporada\n");
            info.append("Disponible desde: ").append(fechaInicio).append(" hasta: ").append(fechaFin).append("\n");
        }
        
        return info.toString();
    }
    
    /**
     * Obtiene la ubicación de la atracción
     * 
     * @return La ubicación
     */
    public String getUbicacion() {
        return ubicacion;
    }
    
    /**
     * Establece la ubicación de la atracción
     * 
     * @param ubicacion La nueva ubicación
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    /**
     * Obtiene el cupo máximo de la atracción
     * 
     * @return El cupo máximo
     */
    public int getCupoMaximo() {
        return cupoMaximo;
    }
    
    /**
     * Establece el cupo máximo de la atracción
     * 
     * @param cupoMaximo El nuevo cupo máximo
     */
    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }
    
    /**
     * Obtiene la edad mínima permitida
     * 
     * @return La edad mínima
     */
    public int getEdadMinima() {
        return edadMinima;
    }
    
    /**
     * Establece la edad mínima permitida
     * 
     * @param edadMinima La nueva edad mínima
     */
    public void setEdadMinima(int edadMinima) {
        this.edadMinima = edadMinima;
    }
}