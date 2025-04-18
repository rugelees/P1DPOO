package modelo.tiquetes;

import java.io.Serializable;
import java.util.Date;

import modelo.atracciones.Atraccion;

/**
 * Clase abstracta que representa un tiquete en el parque
 */
public abstract class Tiquete implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected int id;
    protected String nombre;
    protected int numTiquetes;
    protected String exclusividad;
    protected Date fecha;
    protected String estado;
    protected boolean dctoEmpleado;
    protected String portalCompra;
    protected boolean usado;
    
    /**
     * Constructor de Tiquete
     * 
     * @param id Identificador único del tiquete
     * @param nombre Nombre del tiquete
     * @param numTiquetes Número de tiquetes
     * @param exclusividad Nivel de exclusividad del tiquete
     * @param fecha Fecha de compra o uso
     * @param estado Estado del tiquete
     * @param portalCompra Portal donde se compró el tiquete
     * @param usado Indica si el tiquete ya fue usado
     */
    public Tiquete(int id, String nombre, int numTiquetes, String exclusividad, Date fecha, String estado, 
            String portalCompra, boolean usado) {
        this.id = id;
        this.nombre = nombre;
        this.numTiquetes = numTiquetes;
        this.exclusividad = exclusividad;
        this.fecha = fecha;
        this.estado = estado;
        this.dctoEmpleado = false; // Por defecto, sin descuento de empleado
        this.portalCompra = portalCompra;
        this.usado = usado;
    }
    
    /**
     * Verifica si el tiquete ya fue usado
     * 
     * @return true si el tiquete ya fue usado
     */
    public boolean isUsado() {
        return usado;
    }
    
    /**
     * Marca el tiquete como usado
     */
    public void marcarComoUsado() {
        this.usado = true;
    }
    
    /**
     * Método abstracto para verificar si el tiquete puede acceder a una atracción
     * 
     * @param atraccion La atracción a verificar
     * @return true si el tiquete permite acceder a la atracción
     */
    public abstract boolean puedeAccederAtraccion(Atraccion atraccion);
    
    /**
     * Obtiene el identificador del tiquete
     * 
     * @return El identificador
     */
    public int getId() {
        return id;
    }
    
    /**
     * Obtiene el nombre del tiquete
     * 
     * @return El nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre del tiquete
     * 
     * @param nombre El nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene el número de tiquetes
     * 
     * @return El número de tiquetes
     */
    public int getNumTiquetes() {
        return numTiquetes;
    }
    
    /**
     * Establece el número de tiquetes
     * 
     * @param numTiquetes El nuevo número de tiquetes
     */
    public void setNumTiquetes(int numTiquetes) {
        this.numTiquetes = numTiquetes;
    }
    
    /**
     * Obtiene el nivel de exclusividad del tiquete
     * 
     * @return El nivel de exclusividad
     */
    public String getExclusividad() {
        return exclusividad;
    }
    
    /**
     * Establece el nivel de exclusividad del tiquete
     * 
     * @param exclusividad El nuevo nivel de exclusividad
     */
    public void setExclusividad(String exclusividad) {
        this.exclusividad = exclusividad;
    }
    
    /**
     * Obtiene la fecha del tiquete
     * 
     * @return La fecha
     */
    public Date getFecha() {
        return fecha != null ? new Date(fecha.getTime()) : null;
    }
    
    /**
     * Establece la fecha del tiquete
     * 
     * @param fecha La nueva fecha
     */
    public void setFecha(Date fecha) {
        this.fecha = fecha != null ? new Date(fecha.getTime()) : null;
    }
    
    /**
     * Obtiene el estado del tiquete
     * 
     * @return El estado
     */
    public String getEstado() {
        return estado;
    }
    
    /**
     * Establece el estado del tiquete
     * 
     * @param estado El nuevo estado
     */
    public void setEstado(String estado) {
        this.estado = estado;
    }
    
    /**
     * Verifica si el tiquete tiene descuento de empleado
     * 
     * @return true si el tiquete tiene descuento de empleado
     */
    public boolean isDctoEmpleado() {
        return dctoEmpleado;
    }
    
    /**
     * Establece si el tiquete tiene descuento de empleado
     * 
     * @param dctoEmpleado Indica si el tiquete tiene descuento de empleado
     */
    public void setDctoEmpleado(boolean dctoEmpleado) {
        this.dctoEmpleado = dctoEmpleado;
    }
    
    /**
     * Obtiene el portal de compra del tiquete
     * 
     * @return El portal de compra
     */
    public String getPortalCompra() {
        return portalCompra;
    }
    
    /**
     * Establece el portal de compra del tiquete
     * 
     * @param portalCompra El nuevo portal de compra
     */
    public void setPortalCompra(String portalCompra) {
        this.portalCompra = portalCompra;
    }
    
    /**
     * Establece si el tiquete fue usado
     * 
     * @param usado Indica si el tiquete fue usado
     */
    public void setUsado(boolean usado) {
        this.usado = usado;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Tiquete other = (Tiquete) obj;
        return id == other.id;
    }
    
    @Override
    public int hashCode() {
        return id;
    }
    
    @Override
    public String toString() {
        return "Tiquete [id=" + id + ", nombre=" + nombre + ", exclusividad=" + exclusividad + ", estado=" + estado + ", usado=" + usado + "]";
    }
}