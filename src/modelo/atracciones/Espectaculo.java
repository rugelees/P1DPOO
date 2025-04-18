package modelo.atracciones;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa un espectáculo en el parque
 */
public class Espectaculo implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private String restriccionClima;
    private boolean deTemporada;
    private Date fechaInicio;
    private Date fechaFin;
    private String duracion;
    private String horario; // Cambiado a String en lugar de Time
    private int capacidad;
    private List<Date> funciones;
    
    /**
     * Constructor de Espectaculo
     * 
     * @param nombre Nombre del espectáculo
     * @param restriccionClima Restricciones climáticas
     * @param deTemporada Indica si el espectáculo es de temporada
     * @param fechaInicio Fecha de inicio (si es de temporada)
     * @param fechaFin Fecha de fin (si es de temporada)
     * @param duracion Duración del espectáculo
     * @param horario Horario del espectáculo (formato "HH:MM")
     * @param capacidad Capacidad máxima de personas
     */
    public Espectaculo(String nombre, String restriccionClima, boolean deTemporada, Date fechaInicio, Date fechaFin,
            String duracion, String horario, int capacidad) {
        this.nombre = nombre;
        this.restriccionClima = restriccionClima;
        this.deTemporada = deTemporada;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.duracion = duracion;
        this.horario = horario;
        this.capacidad = capacidad;
        this.funciones = new ArrayList<>();
    }
    
    /**
     * Agrega una función al espectáculo
     * 
     * @param fechaFuncion Fecha de la función
     */
    public void agregarFuncion(Date fechaFuncion) {
        if (fechaFuncion != null) {
            // Verificar si la fecha ya existe
            for (Date fecha : funciones) {
                if (mismodia(fecha, fechaFuncion)) {
                    return; // La función ya existe
                }
            }
            
            funciones.add(new Date(fechaFuncion.getTime()));
        }
    }
    
    /**
     * Cancela una función del espectáculo
     * 
     * @param fechaFuncion Fecha de la función a cancelar
     * @return true si la función fue cancelada, false si no existía
     */
    public boolean cancelarFuncion(Date fechaFuncion) {
        if (fechaFuncion != null) {
            // Buscar la función
            for (int i = 0; i < funciones.size(); i++) {
                if (mismodia(funciones.get(i), fechaFuncion)) {
                    funciones.remove(i);
                    return true;
                }
            }
        }
        return false;
    }
    
    /**
     * Verifica si el espectáculo está disponible en una fecha específica
     * 
     * @param fecha La fecha a verificar
     * @return true si el espectáculo está disponible, false de lo contrario
     */
    public boolean estaDisponible(Date fecha) {
        if (fecha == null) {
            return false;
        }
        
        // Si el espectáculo es de temporada, verificar si está dentro del rango
        if (deTemporada && (fecha.before(fechaInicio) || fecha.after(fechaFin))) {
            return false;
        }
        
        // Verificar si hay una función programada para esa fecha
        for (Date fechaFuncion : funciones) {
            if (mismodia(fechaFuncion, fecha)) {
                return true;
            }
        }
        
        return false;
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
     * Consulta información del espectáculo
     * 
     * @return Información del espectáculo
     */
    public String consultarInformacion() {
        StringBuilder info = new StringBuilder();
        info.append("Espectáculo: ").append(nombre).append("\n");
        info.append("Duración: ").append(duracion).append("\n");
        info.append("Horario: ").append(horario).append("\n");
        info.append("Capacidad: ").append(capacidad).append(" personas\n");
        
        if (restriccionClima != null && !restriccionClima.isEmpty()) {
            info.append("Restricciones Climáticas: ").append(restriccionClima).append("\n");
        }
        
        if (deTemporada) {
            info.append("Espectáculo de Temporada\n");
            info.append("Disponible desde: ").append(fechaInicio).append(" hasta: ").append(fechaFin).append("\n");
        }
        
        if (!funciones.isEmpty()) {
            info.append("Próximas funciones:\n");
            for (Date funcion : funciones) {
                info.append("- ").append(funcion).append("\n");
            }
        }
        
        return info.toString();
    }
    
    /**
     * Obtiene el nombre del espectáculo
     * 
     * @return El nombre del espectáculo
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre del espectáculo
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
     * Verifica si el espectáculo es de temporada
     * 
     * @return true si el espectáculo es de temporada
     */
    public boolean isDeTemporada() {
        return deTemporada;
    }
    
    /**
     * Establece si el espectáculo es de temporada
     * 
     * @param deTemporada Indica si el espectáculo es de temporada
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
     * Obtiene la duración del espectáculo
     * 
     * @return La duración
     */
    public String getDuracion() {
        return duracion;
    }
    
    /**
     * Establece la duración del espectáculo
     * 
     * @param duracion La nueva duración
     */
    public void setDuracion(String duracion) {
        this.duracion = duracion;
    }
    
    /**
     * Obtiene el horario del espectáculo
     * 
     * @return El horario
     */
    public String getHorario() {
        return horario;
    }
    
    /**
     * Establece el horario del espectáculo
     * 
     * @param horario El nuevo horario (formato "HH:MM")
     */
    public void setHorario(String horario) {
        this.horario = horario;
    }
    
    /**
     * Obtiene la capacidad del espectáculo
     * 
     * @return La capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }
    
    /**
     * Establece la capacidad del espectáculo
     * 
     * @param capacidad La nueva capacidad
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    /**
     * Obtiene las funciones programadas
     * 
     * @return Lista de fechas de funciones
     */
    public List<Date> getFunciones() {
        List<Date> copiaFunciones = new ArrayList<>();
        for (Date fecha : funciones) {
            copiaFunciones.add(new Date(fecha.getTime()));
        }
        return copiaFunciones;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Espectaculo other = (Espectaculo) obj;
        return nombre != null && nombre.equals(other.nombre);
    }
    
    @Override
    public int hashCode() {
        return nombre != null ? nombre.hashCode() : 0;
    }
    
    @Override
    public String toString() {
        return "Espectaculo [nombre=" + nombre + ", horario=" + horario + "]";
    }
}