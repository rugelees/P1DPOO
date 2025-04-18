package modelo.lugares;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import modelo.empleados.Empleado;

/**
 * Clase que representa un lugar de trabajo en el parque
 */
public class LugarTrabajo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected String id;
    protected String nombre;
    protected String ubicacion;
    protected Map<Date, Map<String, List<Empleado>>> empleadosAsignados;
    
    /**
     * Constructor de LugarTrabajo
     * 
     * @param id Identificador único del lugar de trabajo
     * @param nombre Nombre del lugar de trabajo
     * @param ubicacion Ubicación del lugar de trabajo
     */
    public LugarTrabajo(String id, String nombre, String ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.ubicacion = ubicacion;
        this.empleadosAsignados = new HashMap<>();
    }
    
    /**
     * Verifica si un empleado requiere capacitación para trabajar en este lugar
     * 
     * @param empleado El empleado a verificar
     * @return true si el empleado requiere capacitación
     */
    public boolean requiereCapacitacion(Empleado empleado) {
        // Por defecto, un lugar de trabajo no requiere capacitación especial
        return false;
    }
    
    /**
     * Obtiene los empleados asignados a este lugar en una fecha y turno específicos
     * 
     * @param fecha La fecha a consultar
     * @param turno El turno a consultar
     * @return Lista de empleados asignados
     */
    public List<Empleado> getEmpleadosAsignados(Date fecha, String turno) {
        if (fecha == null || turno == null) {
            return new ArrayList<>();
        }
        
        // Buscar los empleados asignados para la fecha y turno
        if (!empleadosAsignados.containsKey(fecha)) {
            return new ArrayList<>();
        }
        
        Map<String, List<Empleado>> asignacionesFecha = empleadosAsignados.get(fecha);
        
        if (!asignacionesFecha.containsKey(turno)) {
            return new ArrayList<>();
        }
        
        return new ArrayList<>(asignacionesFecha.get(turno));
    }
    
    /**
     * Asigna un empleado a este lugar en una fecha y turno específicos
     * 
     * @param empleado El empleado a asignar
     * @param fecha La fecha de la asignación
     * @param turno El turno de la asignación
     * @return true si la asignación fue exitosa
     */
    public boolean asignarEmpleado(Empleado empleado, Date fecha, String turno) {
        if (empleado == null || fecha == null || turno == null) {
            return false;
        }
        
        // Crear la estructura de asignaciones si no existe
        if (!empleadosAsignados.containsKey(fecha)) {
            empleadosAsignados.put(fecha, new HashMap<>());
        }
        
        Map<String, List<Empleado>> asignacionesFecha = empleadosAsignados.get(fecha);
        
        if (!asignacionesFecha.containsKey(turno)) {
            asignacionesFecha.put(turno, new ArrayList<>());
        }
        
        List<Empleado> asignacionesTurno = asignacionesFecha.get(turno);
        
        // Verificar si el empleado ya está asignado
        if (asignacionesTurno.contains(empleado)) {
            return true; // Ya está asignado, consideramos que la operación fue exitosa
        }
        
        asignacionesTurno.add(empleado);
        return true;
    }
    
    /**
     * Obtiene el identificador del lugar de trabajo
     * 
     * @return El identificador
     */
    public String getId() {
        return id;
    }
    
    /**
     * Establece el identificador del lugar de trabajo
     * 
     * @param id El nuevo identificador
     */
    public void setId(String id) {
        this.id = id;
    }
    
    /**
     * Obtiene el nombre del lugar de trabajo
     * 
     * @return El nombre
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre del lugar de trabajo
     * 
     * @param nombre El nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene la ubicación del lugar de trabajo
     * 
     * @return La ubicación
     */
    public String getUbicacion() {
        return ubicacion;
    }
    
    /**
     * Establece la ubicación del lugar de trabajo
     * 
     * @param ubicacion La nueva ubicación
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        LugarTrabajo other = (LugarTrabajo) obj;
        return id != null && id.equals(other.id);
    }
    
    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
    
    @Override
    public String toString() {
        return "LugarTrabajo [id=" + id + ", nombre=" + nombre + ", ubicacion=" + ubicacion + "]";
    }}