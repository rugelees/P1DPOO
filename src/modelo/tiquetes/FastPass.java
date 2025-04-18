package modelo.tiquetes;

import java.io.Serializable;
import java.util.Date;

/**
 * Clase que representa un FastPass para saltarse las filas
 */
public class FastPass implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Tiquete tiqueteAsociado;
    private Date fechaValida;
    private boolean usado;
    
    /**
     * Constructor de FastPass
     * 
     * @param tiqueteAsociado El tiquete asociado al FastPass
     * @param fechaValida La fecha en que es válido el FastPass
     */
    public FastPass(Tiquete tiqueteAsociado, Date fechaValida) {
        this.tiqueteAsociado = tiqueteAsociado;
        this.fechaValida = fechaValida;
        this.usado = false;
    }
    
    /**
     * Verifica si el FastPass es válido en una fecha específica
     * 
     * @param fecha La fecha a verificar
     * @return true si el FastPass es válido
     */
    public boolean esValido(Date fecha) {
        if (fecha == null || fechaValida == null) {
            return false;
        }
        
        if (usado) {
            return false;
        }
        
        // Verificar si la fecha corresponde al mismo día
        return mismodia(fecha, fechaValida);
    }
    
    /**
     * Marca el FastPass como usado
     */
    public void marcarComoUsado() {
        this.usado = true;
    }
    
    /**
     * Verifica si dos fechas corresponden al mismo día
     * 
     * @param fecha1 Primera fecha
     * @param fecha2 Segunda fecha
     * @return true si las fechas corresponden al mismo día
     */
    private boolean mismodia(Date fecha1, Date fecha2) {
        // Simplificación para el ejemplo
        // En una implementación real, se debería usar Calendar o LocalDate
        long milisEnDia = 24 * 60 * 60 * 1000;
        long dia1 = fecha1.getTime() / milisEnDia;
        long dia2 = fecha2.getTime() / milisEnDia;
        return dia1 == dia2;
    }
    
    /**
     * Obtiene el tiquete asociado al FastPass
     * 
     * @return El tiquete asociado
     */
    public Tiquete getTiqueteAsociado() {
        return tiqueteAsociado;
    }
    
    /**
     * Establece el tiquete asociado al FastPass
     * 
     * @param tiqueteAsociado El nuevo tiquete asociado
     */
    public void setTiqueteAsociado(Tiquete tiqueteAsociado) {
        this.tiqueteAsociado = tiqueteAsociado;
    }
    
    /**
     * Obtiene la fecha válida del FastPass
     * 
     * @return La fecha válida
     */
    public Date getFechaValida() {
        return fechaValida != null ? new Date(fechaValida.getTime()) : null;
    }
    
    /**
     * Establece la fecha válida del FastPass
     * 
     * @param fechaValida La nueva fecha válida
     */
    public void setFechaValida(Date fechaValida) {
        this.fechaValida = fechaValida != null ? new Date(fechaValida.getTime()) : null;
    }
    
    /**
     * Verifica si el FastPass ya fue usado
     * 
     * @return true si el FastPass ya fue usado
     */
    public boolean isUsado() {
        return usado;
    }
    
    /**
     * Establece si el FastPass fue usado
     * 
     * @param usado Indica si el FastPass fue usado
     */
    public void setUsado(boolean usado) {
        this.usado = usado;
    }
    
    @Override
    public String toString() {
        return "FastPass [tiqueteAsociado=" + (tiqueteAsociado != null ? tiqueteAsociado.getId() : "N/A") 
                + ", fechaValida=" + fechaValida + ", usado=" + usado + "]";
    }
}