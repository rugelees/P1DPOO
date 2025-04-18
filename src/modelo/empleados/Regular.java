package modelo.empleados;

import modelo.lugares.LugarServicio;

/**
 * Clase que representa a un empleado regular del parque
 */
public class Regular extends Empleado {
    private static final long serialVersionUID = 1L;
    
    private boolean puedeSerCajero;
    private LugarServicio lugarAsignado;
    
    /**
     * Constructor de Regular
     * 
     * @param puedeSerCajero Indica si el empleado puede ser cajero
     * @param tipo Tipo de empleado
     * @param nombre Nombre del empleado
     * @param id Identificador único del empleado
     * @param servicioGeneral Indica si el empleado está asignado a servicio general
     * @param email Correo electrónico del empleado
     * @param password Contraseña del empleado
     * @param horasExtras Indica si el empleado realiza horas extras
     */
    public Regular(boolean puedeSerCajero, String tipo, String nombre, int id, boolean servicioGeneral, 
            String email, String password, boolean horasExtras) {
        super(tipo, nombre, id, servicioGeneral, email, password, horasExtras);
        this.puedeSerCajero = puedeSerCajero;
    }
    
    /**
     * Asigna al empleado a servicio general
     * 
     * @return true si la asignación fue exitosa
     */
    public boolean asignarServicioGeneral() {
        this.servicioGeneral = true;
        this.lugarAsignado = null; // Al asignar a servicio general, no tiene lugar asignado
        return true;
    }
    
    /**
     * Asigna al empleado como cajero en un lugar de servicio
     * 
     * @param lugarServicio El lugar de servicio a asignar
     * @return true si la asignación fue exitosa
     */
    public boolean asignarCajero(LugarServicio lugarServicio) {
        if (lugarServicio == null) {
            return false;
        }
        
        // Verificar si el empleado puede ser cajero
        if (!puedeSerCajero) {
            return false;
        }
        
        this.lugarAsignado = lugarServicio;
        this.servicioGeneral = false; // Al asignar a un lugar, no está en servicio general
        return true;
    }
    
    /**
     * Registra un tiquete en el sistema
     */
    public void registrarTiquete() {
        // Método simplificado para el ejemplo
        // En una implementación real, se registraría el tiquete en un sistema de ventas
        // Solo si el empleado puede ser cajero y tiene un lugar asignado
        if (puedeSerCajero && lugarAsignado != null) {
            // Registrar el tiquete
        }
    }
    
    /**
     * Verifica si el empleado puede ser cajero
     * 
     * @return true si el empleado puede ser cajero
     */
    public boolean isPuedeSerCajero() {
        return puedeSerCajero;
    }
    
    /**
     * Establece si el empleado puede ser cajero
     * 
     * @param puedeSerCajero Indica si el empleado puede ser cajero
     */
    public void setPuedeSerCajero(boolean puedeSerCajero) {
        this.puedeSerCajero = puedeSerCajero;
    }
    
    /**
     * Obtiene el lugar asignado al empleado
     * 
     * @return El lugar asignado
     */
    public LugarServicio getLugarAsignado() {
        return lugarAsignado;
    }
    
    /**
     * Establece el lugar asignado al empleado
     * 
     * @param lugarAsignado El nuevo lugar asignado
     */
    public void setLugarAsignado(LugarServicio lugarAsignado) {
        this.lugarAsignado = lugarAsignado;
        
        // Si se le asigna un lugar, no está en servicio general
        if (lugarAsignado != null) {
            this.servicioGeneral = false;
        }
    }
}