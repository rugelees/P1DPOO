package modelo.empleados;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase que representa a un empleado de servicio general del parque
 */
public class ServicioGeneral extends Empleado {
    private static final long serialVersionUID = 1L;
    
    private List<String> zonasAsignadas;
    
    /**
     * Constructor de ServicioGeneral
     * 
     * @param zonasAsignadas Lista de zonas asignadas al empleado
     * @param tipo Tipo de empleado
     * @param nombre Nombre del empleado
     * @param id Identificador único del empleado
     * @param email Correo electrónico del empleado
     * @param password Contraseña del empleado
     * @param horasExtras Indica si el empleado realiza horas extras
     */
    public ServicioGeneral(List<String> zonasAsignadas, String tipo, String nombre, int id,
            String email, String password, boolean horasExtras) {
        super(tipo, nombre, id, true, email, password, horasExtras); // Siempre es servicio general
        this.zonasAsignadas = zonasAsignadas != null ? new ArrayList<>(zonasAsignadas) : new ArrayList<>();
    }
    
    /**
     * Asigna una zona al empleado
     * 
     * @param zona La zona a asignar
     * @return true si la asignación fue exitosa
     */
    public boolean asignarZona(String zona) {
        if (zona == null || zona.isEmpty()) {
            return false;
        }
        
        // Verificar si la zona ya está asignada
        if (zonasAsignadas.contains(zona)) {
            return true; // Ya está asignada, consideramos que la operación fue exitosa
        }
        
        zonasAsignadas.add(zona);
        return true;
    }
    
    /**
     * Remueve una zona asignada al empleado
     * 
     * @param zona La zona a remover
     * @return true si la zona fue removida, false si no existía
     */
    public boolean removerZona(String zona) {
        return zonasAsignadas.remove(zona);
    }
    
    /**
     * Obtiene las zonas asignadas al empleado
     * 
     * @return Lista de zonas asignadas
     */
    public List<String> getZonasAsignadas() {
        return new ArrayList<>(zonasAsignadas);
    }
    
    /**
     * Establece las zonas asignadas al empleado
     * 
     * @param zonasAsignadas Lista de zonas asignadas
     */
    public void setZonasAsignadas(List<String> zonasAsignadas) {
        this.zonasAsignadas = zonasAsignadas != null ? new ArrayList<>(zonasAsignadas) : new ArrayList<>();
    }
    
    /**
     * Verifica si el empleado tiene asignada una zona específica
     * 
     * @param zona La zona a verificar
     * @return true si el empleado tiene asignada la zona
     */
    public boolean tieneZonaAsignada(String zona) {
        return zonasAsignadas.contains(zona);
    }
    
    @Override
    public String toString() {
        return super.toString() + " - Zonas: " + zonasAsignadas;
    }
}