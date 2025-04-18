package modelo.tiquetes;

import java.util.Date;

import modelo.atracciones.Atraccion;
import modelo.util.NivelExclusividad;

/**
 * Clase que representa un tiquete de temporada para el parque
 */
public class EnTemporada extends Tiquete {
    private static final long serialVersionUID = 1L;
    
    private Date fechaInicio;
    private Date fechaFin;
    private String tipoTemporada;
    private String categoria;
    
    /**
     * Constructor de EnTemporada
     * 
     * @param id Identificador único del tiquete
     * @param nombre Nombre del tiquete
     * @param numTiquetes Número de tiquetes
     * @param exclusividad Nivel de exclusividad del tiquete
     * @param fecha Fecha de compra
     * @param estado Estado del tiquete
     * @param portalCompra Portal donde se compró el tiquete
     * @param fechaInicio Fecha de inicio de validez
     * @param fechaFin Fecha de fin de validez
     * @param tipoTemporada Tipo de temporada (semanal, mensual, estacional, anual)
     * @param categoria Categoría del tiquete
     * @param usado Indica si el tiquete ya fue usado
     */
    public EnTemporada(int id, String nombre, int numTiquetes, String exclusividad, Date fecha, String estado, 
            String portalCompra, Date fechaInicio, Date fechaFin, String tipoTemporada, String categoria, boolean usado) {
        super(id, nombre, numTiquetes, exclusividad, fecha, estado, portalCompra, usado);
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipoTemporada = tipoTemporada;
        this.categoria = categoria;
    }
    
    /**
     * Verifica si el tiquete está vigente en una fecha específica
     * 
     * @param fecha La fecha a verificar
     * @return true si el tiquete está vigente
     */
    public boolean estaVigente(Date fecha) {
        if (fecha == null || fechaInicio == null || fechaFin == null) {
            return false;
        }
        
        return !fecha.before(fechaInicio) && !fecha.after(fechaFin);
    }
    
    @Override
    public boolean puedeAccederAtraccion(Atraccion atraccion) {
        if (atraccion == null) {
            return false;
        }
        
        // Verificar si el tiquete está vigente
        Date ahora = new Date();
        if (!estaVigente(ahora)) {
            return false;
        }
        
        // Verificar si el nivel de exclusividad del tiquete permite acceder a la atracción
        return NivelExclusividad.tieneAcceso(exclusividad, atraccion.getNivelExclusividad());
    }
    
    /**
     * Obtiene la fecha de inicio de validez
     * 
     * @return La fecha de inicio
     */
    public Date getFechaInicio() {
        return fechaInicio != null ? new Date(fechaInicio.getTime()) : null;
    }
    
    /**
     * Establece la fecha de inicio de validez
     * 
     * @param fechaInicio La nueva fecha de inicio
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio != null ? new Date(fechaInicio.getTime()) : null;
    }
    
    /**
     * Obtiene la fecha de fin de validez
     * 
     * @return La fecha de fin
     */
    public Date getFechaFin() {
        return fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }
    
    /**
     * Establece la fecha de fin de validez
     * 
     * @param fechaFin La nueva fecha de fin
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }
    
    /**
     * Obtiene el tipo de temporada
     * 
     * @return El tipo de temporada
     */
    public String getTipoTemporada() {
        return tipoTemporada;
    }
    
    /**
     * Establece el tipo de temporada
     * 
     * @param tipoTemporada El nuevo tipo de temporada
     */
    public void setTipoTemporada(String tipoTemporada) {
        this.tipoTemporada = tipoTemporada;
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
        return "EnTemporada [id=" + id + ", nombre=" + nombre + ", exclusividad=" + exclusividad + ", tipoTemporada=" + tipoTemporada 
                + ", vigencia=" + fechaInicio + " a " + fechaFin + ", estado=" + estado + ", usado=" + usado + "]";
    }
}