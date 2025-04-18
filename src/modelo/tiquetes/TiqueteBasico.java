package modelo.tiquetes;

import java.util.Date;

import modelo.atracciones.Atraccion;
import modelo.util.NivelExclusividad;

/**
 * Clase que representa un tiquete básico para el parque
 */
public class TiqueteBasico extends Tiquete {
    private static final long serialVersionUID = 1L;
    
    private String categoria;
    
    /**
     * Constructor de TiqueteBasico
     * 
     * @param id Identificador único del tiquete
     * @param nombre Nombre del tiquete
     * @param numTiquetes Número de tiquetes
     * @param exclusividad Nivel de exclusividad del tiquete
     * @param fecha Fecha de compra o uso
     * @param estado Estado del tiquete
     * @param portalCompra Portal donde se compró el tiquete
     * @param categoria Categoría del tiquete
     * @param usado Indica si el tiquete ya fue usado
     */
    public TiqueteBasico(int id, String nombre, int numTiquetes, String exclusividad, Date fecha, String estado, 
            String portalCompra, String categoria, boolean usado) {
        super(id, nombre, numTiquetes, exclusividad, fecha, estado, portalCompra, usado);
        this.categoria = categoria;
    }
    
    @Override
    public boolean puedeAccederAtraccion(Atraccion atraccion) {
        if (atraccion == null) {
            return false;
        }
        
        // Verificar si el nivel de exclusividad del tiquete permite acceder a la atracción
        return NivelExclusividad.tieneAcceso(exclusividad, atraccion.getNivelExclusividad());
    }
    
    /**
     * Obtiene la categoría del tiquete
     * 
     * @return La categoría
     */
    public String getCategoria() {
        return categoria;
    }
    
    /**
     * Establece la categoría del tiquete
     * 
     * @param categoria La nueva categoría
     */
    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }
    
    @Override
    public String toString() {
        return "TiqueteBasico [id=" + id + ", nombre=" + nombre + ", exclusividad=" + exclusividad + ", categoria=" + categoria + ", estado=" + estado + ", usado=" + usado + "]";
    }
}