package modelo.empleados;

import modelo.lugares.Cafeteria;

/**
 * Clase que representa a un cocinero del parque
 */
public class Cocinero extends Empleado {
    private static final long serialVersionUID = 1L;
    
    private boolean capacitado;
    private Cafeteria cafeteriaAsignada;
    
    /**
     * Constructor de Cocinero
     * 
     * @param capacitado Indica si el cocinero está capacitado
     * @param tipo Tipo de empleado
     * @param nombre Nombre del empleado
     * @param id Identificador único del empleado
     * @param servicioGeneral Indica si el empleado está asignado a servicio general
     * @param email Correo electrónico del empleado
     * @param password Contraseña del empleado
     * @param horasExtras Indica si el empleado realiza horas extras
     */
    public Cocinero(boolean capacitado, String tipo, String nombre, int id, boolean servicioGeneral, 
            String email, String password, boolean horasExtras) {
        super(tipo, nombre, id, servicioGeneral, email, password, horasExtras);
        this.capacitado = capacitado;
    }
    
    /**
     * Verifica si el cocinero ha preparado la comida correctamente
     * 
     * @return true si el cocinero ha preparado la comida correctamente
     */
    public boolean comidaPreparada() {
        // Método simplificado para el ejemplo
        // En una implementación real, se verificaría el estado de la comida
        return capacitado;
    }
    
    /**
     * Verifica si el cocinero puede trabajar como cajero
     * 
     * @return true si el cocinero puede trabajar como cajero
     */
    public boolean puedeTrabajarCaja() {
        // Todos los cocineros pueden trabajar como cajeros
        return true;
    }
    
    /**
     * Asigna una cafetería al cocinero
     * 
     * @param cafeteria La cafetería a asignar
     * @return true si la asignación fue exitosa
     */
    public boolean asignarCocina(Cafeteria cafeteria) {
        if (cafeteria == null) {
            return false;
        }
        
        // Verificar si el cocinero está capacitado
        if (!capacitado) {
            return false;
        }
        
        this.cafeteriaAsignada = cafeteria;
        return true;
    }
    
    /**
     * Verifica si el cocinero está capacitado
     * 
     * @return true si el cocinero está capacitado
     */
    public boolean isCapacitado() {
        return capacitado;
    }
    
    /**
     * Establece si el cocinero está capacitado
     * 
     * @param capacitado Indica si el cocinero está capacitado
     */
    public void setCapacitado(boolean capacitado) {
        this.capacitado = capacitado;
    }
    
    /**
     * Obtiene la cafetería asignada al cocinero
     * 
     * @return La cafetería asignada
     */
    public Cafeteria getCafeteriaAsignada() {
        return cafeteriaAsignada;
    }
    
    /**
     * Establece la cafetería asignada al cocinero
     * 
     * @param cafeteriaAsignada La nueva cafetería asignada
     */
    public void setCafeteriaAsignada(Cafeteria cafeteriaAsignada) {
        this.cafeteriaAsignada = cafeteriaAsignada;
    }
}