package modelo.empleados;

import java.util.ArrayList;
import java.util.List;

import modelo.atracciones.AtraccionMecanica;

/**
 * Clase que representa a un empleado capacitado para operar atracciones mecánicas de riesgo alto
 */
public class AtraccionAlto extends Empleado {
    private static final long serialVersionUID = 1L;
    
    private boolean capacitado;
    private List<AtraccionMecanica> atraccionesEspecificas;
    
    /**
     * Constructor de AtraccionAlto
     * 
     * @param tipo Tipo de empleado
     * @param nombre Nombre del empleado
     * @param id Identificador único del empleado
     * @param servicioGeneral Indica si el empleado está asignado a servicio general
     * @param email Correo electrónico del empleado
     * @param password Contraseña del empleado
     * @param horasExtras Indica si el empleado realiza horas extras
     * @param capacitado Indica si el empleado está capacitado
     */
    public AtraccionAlto(String tipo, String nombre, int id, boolean servicioGeneral, String email, 
            String password, boolean horasExtras, boolean capacitado) {
        super(tipo, nombre, id, servicioGeneral, email, password, horasExtras);
        this.capacitado = capacitado;
        this.atraccionesEspecificas = new ArrayList<>();
    }
    
    /**
     * Verifica si el empleado puede operar atracciones de riesgo alto
     * 
     * @return true si el empleado puede operar atracciones de riesgo alto
     */
    public boolean puedeOperarAtraccionRiesgoAlto() {
        return capacitado;
    }
    
    /**
     * Asigna una atracción mecánica de riesgo alto al empleado
     * 
     * @param atraccion La atracción a asignar
     * @return true si la asignación fue exitosa
     */
    public boolean asignarAtraccionAlta(AtraccionMecanica atraccion) {
        if (atraccion == null || !atraccion.esRiesgoAlto()) {
            return false;
        }
        
        // Verificar si el empleado puede operar la atracción
        if (!capacitado) {
            return false;
        }
        
        // Verificar si la atracción ya está asignada
        if (atraccionesEspecificas.contains(atraccion)) {
            return true; // Ya está asignada, consideramos que la operación fue exitosa
        }
        
        atraccionesEspecificas.add(atraccion);
        return true;
    }
    
    /**
     * Verifica si el empleado está capacitado para una atracción específica
     * 
     * @param atraccion La atracción a verificar
     * @return true si el empleado está capacitado para la atracción
     */
    public boolean estaCapacitadoParaAtraccion(AtraccionMecanica atraccion) {
        if (atraccion == null) {
            return false;
        }
        
        return atraccionesEspecificas.contains(atraccion);
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
     * Obtiene las atracciones específicas asignadas al empleado
     * 
     * @return Lista de atracciones específicas
     */
    public List<AtraccionMecanica> getAtraccionesEspecificas() {
        return new ArrayList<>(atraccionesEspecificas);
    }
    
    /**
     * Remueve una atracción específica de la lista del empleado
     * 
     * @param atraccion La atracción a remover
     * @return true si la atracción fue removida, false si no existía
     */
    public boolean removerAtraccionEspecifica(AtraccionMecanica atraccion) {
        return atraccionesEspecificas.remove(atraccion);
    }
}