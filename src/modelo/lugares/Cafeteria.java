package modelo.lugares;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Clase que representa una cafetería en el parque
 */
public class Cafeteria extends LugarServicio {
    private static final long serialVersionUID = 1L;
    
    private List<String> menu;
    private int capacidad;
    
    /**
     * Constructor de Cafeteria
     * 
     * @param id Identificador único de la cafetería
     * @param nombre Nombre de la cafetería
     * @param ubicacion Ubicación de la cafetería
     * @param menu Lista de platos del menú
     * @param capacidad Capacidad máxima de personas
     */
    public Cafeteria(String id, String nombre, String ubicacion, List<String> menu, int capacidad) {
        super(id, nombre, ubicacion, "Cafeteria", true); // Las cafeterías siempre requieren cocinero
        this.menu = menu != null ? new ArrayList<>(menu) : new ArrayList<>();
        this.capacidad = capacidad;
    }
    
    /**
     * Verifica si la cafetería tiene el personal mínimo necesario
     * 
     * @param fecha La fecha a verificar
     * @param turno El turno a verificar
     * @return true si la cafetería tiene el personal mínimo
     */
    public boolean verificarPersonalMinimo(Date fecha, String turno) {
        // Una cafetería requiere al menos un cocinero y un cajero
        return tieneCajeroAsignado(fecha, turno) && tieneCocineroAsignado(fecha, turno);
    }
    
    /**
     * Agrega un plato al menú
     * 
     * @param plato El plato a agregar
     * @return true si el plato fue agregado
     */
    public boolean agregarPlato(String plato) {
        if (plato == null || plato.isEmpty()) {
            return false;
        }
        
        // Verificar si el plato ya existe
        if (menu.contains(plato)) {
            return true; // Ya existe, consideramos que la operación fue exitosa
        }
        
        menu.add(plato);
        return true;
    }
    
    /**
     * Remueve un plato del menú
     * 
     * @param plato El plato a remover
     * @return true si el plato fue removido, false si no existía
     */
    public boolean removerPlato(String plato) {
        return menu.remove(plato);
    }
    
    /**
     * Obtiene el menú de la cafetería
     * 
     * @return Lista de platos del menú
     */
    public List<String> getMenu() {
        return new ArrayList<>(menu);
    }
    
    /**
     * Establece el menú de la cafetería
     * 
     * @param menu Lista de platos del menú
     */
    public void setMenu(List<String> menu) {
        this.menu = menu != null ? new ArrayList<>(menu) : new ArrayList<>();
    }
    
    /**
     * Obtiene la capacidad de la cafetería
     * 
     * @return La capacidad
     */
    public int getCapacidad() {
        return capacidad;
    }
    
    /**
     * Establece la capacidad de la cafetería
     * 
     * @param capacidad La nueva capacidad
     */
    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
    @Override
    public String toString() {
        return "Cafeteria [nombre=" + nombre + ", ubicacion=" + ubicacion + ", capacidad=" + capacidad + ", items en menu=" + menu.size() + "]";
    }
}