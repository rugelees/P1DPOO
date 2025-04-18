package modelo.atracciones;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public abstract class Atraccion {
  
    
    protected String nombre;
    protected String restriccionClima;
    protected boolean deTemporada;
    protected Date fechaInicio;
    protected Date fechaFin;
    protected String nivelExclusividad;
    protected int empleadosEncargados;
    protected List<Date> fechasMantenimiento;
    
    /**
     * Constructor de Atraccion
     * 
     * @param nombre Nombre de la atracción
     * @param restriccionClima Restricciones climáticas
     * @param deTemporada Indica si la atracción es de temporada
     * @param fechaInicio Fecha de inicio (si es de temporada)
     * @param fechaFin Fecha de fin (si es de temporada)
     * @param nivelExclusividad Nivel de exclusividad de la atracción
     * @param empleadosEncargados Número de empleados necesarios
     */
    public Atraccion(String nombre, String restriccionClima, boolean deTemporada, Date fechaInicio, Date fechaFin, 
            String nivelExclusividad, int empleadosEncargados) {
        this.nombre = nombre;
        this.restriccionClima = restriccionClima;
        this.deTemporada = deTemporada;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.nivelExclusividad = nivelExclusividad;
        this.empleadosEncargados = empleadosEncargados;
        this.fechasMantenimiento = new ArrayList<>();
    }
    
    /**
     * Método abstracto para consultar información de la atracción
     * 
     * @return Información de la atracción
     */
    public abstract String consultarInformacion();
    
    /**
     * Programa mantenimiento para la atracción
     * 
     * @param fechaInicio Fecha de inicio del mantenimiento
     * @param fechaFin Fecha de fin del mantenimiento
     */
    public void programarMantenimiento(Date fechaInicio, Date fechaFin) {
        // En una implementación real, se deberían almacenar rangos de fechas
        // Por simplicidad, aquí solo se añaden las fechas individuales
        Date fecha = new Date(fechaInicio.getTime());
        while (!fecha.after(fechaFin)) {
            fechasMantenimiento.add(new Date(fecha.getTime()));
            // Avanzar un día
            fecha.setTime(fecha.getTime() + 24 * 60 * 60 * 1000);
        }
    }
    
    /**
     * Verifica si la atracción está disponible en una fecha específica
     * 
     * @param fecha La fecha a verificar
     * @return true si la atracción está disponible, false de lo contrario
     */
    public boolean estaDisponible(Date fecha) {
        // Si la atracción está en mantenimiento, no está disponible
        for (Date fechaMantenimiento : fechasMantenimiento) {
            if (mismodia(fechaMantenimiento, fecha)) {
                return false;
            }
        }
        
        // Si la atracción es de temporada, verificar si está dentro del rango
        if (deTemporada) {
            return !fecha.before(fechaInicio) && !fecha.after(fechaFin);
        }
        
        // Si no es de temporada, está disponible todos los días (excepto en mantenimiento)
        return true;
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
     * Obtiene el nombre de la atracción
     * 
     * @return El nombre de la atracción
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre de la atracción
     * 
     * @param nombre El nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene las restricciones climáticas
     * 
     * @return Las restricciones climáticas
     */
    public String getRestriccionClima() {
        return restriccionClima;
    }
    
    /**
     * Establece las restricciones climáticas
     * 
     * @param restriccionClima Las nuevas restricciones
     */
    public void setRestriccionClima(String restriccionClima) {
        this.restriccionClima = restriccionClima;
    }
    
    /**
     * Verifica si la atracción es de temporada
     * 
     * @return true si la atracción es de temporada
     */
    public boolean isDeTemporada() {
        return deTemporada;
    }
    
    /**
     * Establece si la atracción es de temporada
     * 
     * @param deTemporada Indica si la atracción es de temporada
     */
    public void setDeTemporada(boolean deTemporada) {
        this.deTemporada = deTemporada;
    }
    
    /**
     * Obtiene la fecha de inicio de la temporada
     * 
     * @return La fecha de inicio
     */
    public Date getFechaInicio() {
        return fechaInicio != null ? new Date(fechaInicio.getTime()) : null;
    }
    
    /**
     * Establece la fecha de inicio de la temporada
     * 
     * @param fechaInicio La nueva fecha de inicio
     */
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio != null ? new Date(fechaInicio.getTime()) : null;
    }
    
    /**
     * Obtiene la fecha de fin de la temporada
     * 
     * @return La fecha de fin
     */
    public Date getFechaFin() {
        return fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }
    
    /**
     * Establece la fecha de fin de la temporada
     * 
     * @param fechaFin La nueva fecha de fin
     */
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin != null ? new Date(fechaFin.getTime()) : null;
    }
    
    /**
     * Obtiene el nivel de exclusividad de la atracción
     * 
     * @return El nivel de exclusividad
     */
    public String getNivelExclusividad() {
        return nivelExclusividad;
    }
    
    /**
     * Establece el nivel de exclusividad de la atracción
     * 
     * @param nivelExclusividad El nuevo nivel de exclusividad
     */
    public void setNivelExclusividad(String nivelExclusividad) {
        this.nivelExclusividad = nivelExclusividad;
    }
    
    /**
     * Obtiene el número de empleados encargados
     * 
     * @return El número de empleados
     */
    public int getEmpleadosEncargados() {
        return empleadosEncargados;
    }
    
    /**
     * Establece el número de empleados encargados
     * 
     * @param empleadosEncargados El nuevo número de empleados
     */
    public void setEmpleadosEncargados(int empleadosEncargados) {
        this.empleadosEncargados = empleadosEncargados;
    }
    
    /**
     * Obtiene las fechas de mantenimiento
     * 
     * @return Lista de fechas de mantenimiento
     */
    public List<Date> getFechasMantenimiento() {
        List<Date> copiaFechas = new ArrayList<>();
        for (Date fecha : fechasMantenimiento) {
            copiaFechas.add(new Date(fecha.getTime()));
        }
        return copiaFechas;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Atraccion other = (Atraccion) obj;
        return nombre != null && nombre.equals(other.nombre);
    }
    
    @Override
    public int hashCode() {
        return nombre != null ? nombre.hashCode() : 0;
    }
    
    @Override
    public String toString() {
        return "Atraccion [nombre=" + nombre + ", nivelExclusividad=" + nivelExclusividad + "]";
    }
}
