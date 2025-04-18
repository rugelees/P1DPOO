package modelo.lugares;

import java.util.Date;
import java.util.List;

import modelo.empleados.Cajero;
import modelo.empleados.Cocinero;
import modelo.empleados.Empleado;

/**
 * Clase que representa un lugar de servicio en el parque
 */
public class LugarServicio extends LugarTrabajo {
    private static final long serialVersionUID = 1L;
    
    private String tipoServicio;
    private boolean requiereCocinero;
    
    /**
     * Constructor de LugarServicio
     * 
     * @param id Identificador único del lugar de servicio
     * @param nombre Nombre del lugar de servicio
     * @param ubicacion Ubicación del lugar de servicio
     * @param tipoServicio Tipo de servicio ofrecido
     * @param requiereCocinero Indica si el lugar requiere cocinero
     */
    public LugarServicio(String id, String nombre, String ubicacion, String tipoServicio, boolean requiereCocinero) {
        super(id, nombre, ubicacion);
        this.tipoServicio = tipoServicio;
        this.requiereCocinero = requiereCocinero;
    }
    
    /**
     * Verifica si hay un cajero asignado para una fecha y turno específicos
     * 
     * @param fecha La fecha a verificar
     * @param turno El turno a verificar
     * @return true si hay un cajero asignado
     */
    public boolean tieneCajeroAsignado(Date fecha, String turno) {
        List<Empleado> empleados = getEmpleadosAsignados(fecha, turno);
        
        for (Empleado empleado : empleados) {
            if (empleado instanceof Cajero) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Verifica si hay un cocinero asignado para una fecha y turno específicos
     * 
     * @param fecha La fecha a verificar
     * @param turno El turno a verificar
     * @return true si hay un cocinero asignado
     */
    public boolean tieneCocineroAsignado(Date fecha, String turno) {
        if (!requiereCocinero) {
            return true; // Si no requiere cocinero, siempre retorna true
        }
        
        List<Empleado> empleados = getEmpleadosAsignados(fecha, turno);
        
        for (Empleado empleado : empleados) {
            if (empleado instanceof Cocinero) {
                return true;
            }
        }
        
        return false;
    }
    
    /**
     * Asigna un cajero a este lugar en una fecha y turno específicos
     * 
     * @param cajero El cajero a asignar
     * @param fecha La fecha de la asignación
     * @param turno El turno de la asignación
     * @return true si la asignación fue exitosa
     */
    public boolean asignarCajero(Cajero cajero, Date fecha, String turno) {
        return asignarEmpleado(cajero, fecha, turno);
    }
    
    /**
     * Asigna un cocinero a este lugar en una fecha y turno específicos
     * 
     * @param cocinero El cocinero a asignar
     * @param fecha La fecha de la asignación
     * @param turno El turno de la asignación
     * @return true si la asignación fue exitosa
     */
    public boolean asignarCocinero(Cocinero cocinero, Date fecha, String turno) {
        if (!requiereCocinero) {
            return false; // Si no requiere cocinero, no se puede asignar
        }
        
        return asignarEmpleado(cocinero, fecha, turno);
    }
    
    /**
     * Obtiene el tipo de servicio ofrecido
     * 
     * @return El tipo de servicio
     */
    public String getTipoServicio() {
        return tipoServicio;
    }
    
    /**
     * Establece el tipo de servicio ofrecido
     * 
     * @param tipoServicio El nuevo tipo de servicio
     */
    public void setTipoServicio(String tipoServicio) {
        this.tipoServicio = tipoServicio;
    }
    
    /**
     * Verifica si el lugar requiere cocinero
     * 
     * @return true si el lugar requiere cocinero
     */
    public boolean isRequiereCocinero() {
        return requiereCocinero;
    }
    
    /**
     * Establece si el lugar requiere cocinero
     * 
     * @param requiereCocinero Indica si el lugar requiere cocinero
     */
    public void setRequiereCocinero(boolean requiereCocinero) {
        this.requiereCocinero = requiereCocinero;
    }
    
    @Override
    public String toString() {
        return "LugarServicio [id=" + id + ", nombre=" + nombre + ", tipoServicio=" + tipoServicio + "]";
    }
}