package modelo.empleados;

import java.io.Serializable;

import modelo.usuarios.Usuario;

/**
 * Clase abstracta que representa a un empleado del parque
 */
public abstract class Empleado extends Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected String tipo;
    protected String nombre;
    protected int id;
    protected boolean servicioGeneral;
    protected boolean horasExtras;
    
    /**
     * Constructor de Empleado
     * 
     * @param tipo Tipo de empleado
     * @param nombre Nombre del empleado
     * @param id Identificador único del empleado
     * @param servicioGeneral Indica si el empleado está asignado a servicio general
     * @param email Correo electrónico del empleado
     * @param password Contraseña del empleado
     * @param horasExtras Indica si el empleado realiza horas extras
     */
    public Empleado(String tipo, String nombre, int id, boolean servicioGeneral, String email, String password, boolean horasExtras) {
        super(email, password);
        this.tipo = tipo;
        this.nombre = nombre;
        this.id = id;
        this.servicioGeneral = servicioGeneral;
        this.horasExtras = horasExtras;
    }
    
    /**
     * Verifica si el empleado está asignado a servicio general
     * 
     * @return true si el empleado está asignado a servicio general
     */
    public boolean esServicioGeneral() {
        return servicioGeneral;
    }
    
    /**
     * Obtiene el tipo de empleado
     * 
     * @return El tipo de empleado
     */
    public String getTipo() {
        return tipo;
    }
    
    /**
     * Establece el tipo de empleado
     * 
     * @param tipo El nuevo tipo
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    
    /**
     * Obtiene el nombre del empleado
     * 
     * @return El nombre del empleado
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre del empleado
     * 
     * @param nombre El nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene el identificador del empleado
     * 
     * @return El identificador
     */
    public int getId() {
        return id;
    }
    
    /**
     * Establece si el empleado está asignado a servicio general
     * 
     * @param servicioGeneral Indica si el empleado está asignado a servicio general
     */
    public void setServicioGeneral(boolean servicioGeneral) {
        this.servicioGeneral = servicioGeneral;
    }
    
    /**
     * Verifica si el empleado realiza horas extras
     * 
     * @return true si el empleado realiza horas extras
     */
    public boolean isHorasExtras() {
        return horasExtras;
    }
    
    /**
     * Establece si el empleado realiza horas extras
     * 
     * @param horasExtras Indica si el empleado realiza horas extras
     */
    public void setHorasExtras(boolean horasExtras) {
        this.horasExtras = horasExtras;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Empleado other = (Empleado) obj;
        return id == other.id;
    }
    
    @Override
    public int hashCode() {
        return id;
    }
    
    @Override
    public String toString() {
        return "Empleado [tipo=" + tipo + ", nombre=" + nombre + ", id=" + id + "]";
    }
}