package modelo.tiquetes;

import java.util.Date;

import modelo.atracciones.Atraccion;

/**
 * Clase que representa un tiquete individual para una atracción específica
 */
public class Individual extends Tiquete {
    private static final long serialVersionUID = 1L;
    
    private Atraccion atraccion;
    
    /**
     * Constructor de Individual
     * 
     * @param atraccion La atracción asociada al tiquete
     * @param id Identificador único del tiquete
     * @param nombre Nombre del tiquete
     * @param numTiquetes Número de tiquetes
     * @param exclusividad Nivel de exclusividad del tiquete
     * @param fecha Fecha de compra o uso
     * @param estado Estado del tiquete
     * @param portalCompra Portal donde se compró el tiquete
     * @param usado Indica si el tiquete ya fue usado
     */
    public Individual(Atraccion atraccion, int id, String nombre, int numTiquetes, String exclusividad, Date fecha, 
            String estado, String portalCompra, boolean usado) {
        super(id, nombre, numTiquetes, exclusividad, fecha, estado, portalCompra, usado);
        this.atraccion = atraccion;
    }
    
    @Override
    public boolean puedeAccederAtraccion(Atraccion atraccion) {
        if (atraccion == null || this.atraccion == null) {
            return false;
        }
        
        // Verificar si el tiquete corresponde a la atracción solicitada
        return this.atraccion.equals(atraccion) && !usado;
    }
    
    /**
     * Obtiene la atracción asociada al tiquete
     * 
     * @return La atracción
     */
    public Atraccion getAtraccion() {
        return atraccion;
    }
    
    /**
     * Establece la atracción asociada al tiquete
     * 
     * @param atraccion La nueva atracción
     */
    public void setAtraccion(Atraccion atraccion) {
        this.atraccion = atraccion;
    }
    
    @Override
    public String toString() {
        return "Individual [id=" + id + ", nombre=" + nombre + ", atraccion=" + (atraccion != null ? atraccion.getNombre() : "N/A") 
                + ", estado=" + estado + ", usado=" + usado + "]";
    }
}