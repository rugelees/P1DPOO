package modelo.empleados;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import modelo.atracciones.AtraccionMecanica;

/**
 * Clase que representa a un empleado capacitado para operar atracciones mecánicas de riesgo medio
 */
public class AtraccionMedio extends Empleado {
    private static final long serialVersionUID = 1L;
    
    private boolean capacitado;
    private Date fechaCapacitacion;
    private Date fechaVencimientoCapacitacion;
    private List<AtraccionMecanica> atraccionesAsignadas;
    
    /**
     * Constructor de AtraccionMedio
     * 
     * @param fechaCapacitacion Fecha en que recibió la capacitación
     * @param fechaVencimientoCapacitacion Fecha en que vence la capacitación
     * @param tipo Tipo de empleado
     * @param nombre Nombre del empleado
     * @param id Identificador único del empleado
     * @param servicioGeneral Indica si el empleado está asignado a servicio general
     * @param email Correo electrónico del empleado
     * @param password Contraseña del empleado
     * @param horasExtras Indica si el empleado realiza horas extras
     */
    public AtraccionMedio(Date fechaCapacitacion, Date fechaVencimientoCapacitacion, String tipo, String nombre, 
            int id, boolean servicioGeneral, String email, String password, boolean horasExtras) {
        super(tipo, nombre, id, servicioGeneral, email, password, horasExtras);
        this.fechaCapacitacion = fechaCapacitacion;
        this.fechaVencimientoCapacitacion = fechaVencimientoCapacitacion;
        this.capacitado = true; // Por defecto, un empleado con esta clase está capacitado
        this.atraccionesAsignadas = new ArrayList<>();
    }
    
    /**
     * Verifica si el empleado puede operar atracciones de riesgo medio
     * 
     * @return true si el empleado puede operar atracciones de riesgo medio
     */
    public boolean puedeOperarAtraccionRiesgoMedio() {
        // Verificar si la capacitación está vigente
        if (!capacitado) {
            return false;
        }
        
        // Verificar si la capacitación ha vencido
        Date ahora = new Date();
        return !ahora.after(fechaVencimientoCapacitacion);
    }
    
    /**
     * Asigna una atracción mecánica de riesgo medio al empleado
     * 
     * @param atraccion La atracción a asignar
     * @return true si la asignación fue exitosa
     */
    public boolean asignarAtraccionMedia(AtraccionMecanica atraccion) {
        if (atraccion == null || !atraccion.esRiesgoMedio()) {
            return false;
        }
        
        // Verificar si el empleado puede operar la atracción
        if (!puedeOperarAtraccionRiesgoMedio()) {
            return false;
        }
        
        // Verificar si la atracción ya está asignada
        if (atraccionesAsignadas.contains(atraccion)) {
            return true; // Ya está asignada, consideramos que la operación fue exitosa
        }
        
        atraccionesAsignadas.add(atraccion);
        return true;
    }
    
    /**
     * Verifica si el empleado está capacitado
     * 
     * @return true si el empleado está capacitado
     */
    public boolean isCapacitado() {
        return capacitado;
    }
    
    /**
     * Establece si el empleado está capacitado
     * 
     * @param capacitado Indica si el empleado está capacitado
     */
    public void setCapacitado(boolean capacitado) {
        this.capacitado = capacitado;
    }
    
    /**
     * Obtiene la fecha de capacitación
     * 
     * @return La fecha de capacitación
     */
    public Date getFechaCapacitacion() {
        return fechaCapacitacion != null ? new Date(fechaCapacitacion.getTime()) : null;
    }
    
    /**
     * Establece la fecha de capacitación
     * 
     * @param fechaCapacitacion La nueva fecha de capacitación
     */
    public void setFechaCapacitacion(Date fechaCapacitacion) {
        this.fechaCapacitacion = fechaCapacitacion != null ? new Date(fechaCapacitacion.getTime()) : null;
    }
    
    /**
     * Obtiene la fecha de vencimiento de la capacitación
     * 
     * @return La fecha de vencimiento
     */
    public Date getFechaVencimientoCapacitacion() {
        return fechaVencimientoCapacitacion != null ? new Date(fechaVencimientoCapacitacion.getTime()) : null;
    }
    
    /**
     * Establece la fecha de vencimiento de la capacitación
     * 
     * @param fechaVencimientoCapacitacion La nueva fecha de vencimiento
     */
    public void setFechaVencimientoCapacitacion(Date fechaVencimientoCapacitacion) {
        this.fechaVencimientoCapacitacion = fechaVencimientoCapacitacion != null ? 
                new Date(fechaVencimientoCapacitacion.getTime()) : null;
    }
    
    /**
     * Obtiene las atracciones asignadas al empleado
     * 
     * @return Lista de atracciones asignadas
     */
    public List<AtraccionMecanica> getAtraccionesAsignadas() {
        return new ArrayList<>(atraccionesAsignadas);
    }
}