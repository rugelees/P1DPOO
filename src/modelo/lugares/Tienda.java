package modelo.lugares;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Clase que representa una tienda en el parque
 */
public class Tienda extends LugarServicio {
    private static final long serialVersionUID = 1L;
    
    private String tipoProductos;
    private Map<String, Integer> inventario;
    
    /**
     * Constructor de Tienda
     * 
     * @param id Identificador único de la tienda
     * @param nombre Nombre de la tienda
     * @param ubicacion Ubicación de la tienda
     * @param tipoProductos Tipo de productos que vende
     * @param inventario Mapa con el inventario de productos
     */
    public Tienda(String id, String nombre, String ubicacion, String tipoProductos, Map<String, Integer> inventario) {
        super(id, nombre, ubicacion, "Tienda", false); // Las tiendas no requieren cocinero
        this.tipoProductos = tipoProductos;
        this.inventario = inventario != null ? new HashMap<>(inventario) : new HashMap<>();
    }
    
    /**
     * Verifica si un producto está disponible en la tienda
     * 
     * @param producto El producto a verificar
     * @return true si el producto está disponible
     */
    public boolean verificarDisponibilidadProducto(String producto) {
        if (producto == null || producto.isEmpty()) {
            return false;
        }
        
        // Verificar si el producto existe y si hay unidades disponibles
        return inventario.containsKey(producto) && inventario.get(producto) > 0;
    }
    
    /**
     * Agrega un producto al inventario
     * 
     * @param producto El producto a agregar
     * @param cantidad La cantidad a agregar
     * @return true si el producto fue agregado
     */
    public boolean agregarProducto(String producto, int cantidad) {
        if (producto == null || producto.isEmpty() || cantidad <= 0) {
            return false;
        }
        
        // Si el producto ya existe, sumar la cantidad
        if (inventario.containsKey(producto)) {
            int cantidadActual = inventario.get(producto);
            inventario.put(producto, cantidadActual + cantidad);
        } else {
            inventario.put(producto, cantidad);
        }
        
        return true;
    }
    
    /**
     * Remueve un producto del inventario
     * 
     * @param producto El producto a remover
     * @param cantidad La cantidad a remover
     * @return true si el producto fue removido
     */
    public boolean removerProducto(String producto, int cantidad) {
        if (producto == null || producto.isEmpty() || cantidad <= 0) {
            return false;
        }
        
        // Verificar si el producto existe y si hay suficientes unidades
        if (!inventario.containsKey(producto) || inventario.get(producto) < cantidad) {
            return false;
        }
        
        int cantidadActual = inventario.get(producto);
        int nuevaCantidad = cantidadActual - cantidad;
        
        if (nuevaCantidad > 0) {
            inventario.put(producto, nuevaCantidad);
        } else {
            inventario.remove(producto);
        }
        
        return true;
    }
    
    /**
     * Verifica si la tienda tiene el personal mínimo necesario
     * 
     * @param fecha La fecha a verificar
     * @param turno El turno a verificar
     * @return true si la tienda tiene el personal mínimo
     */
    public boolean verificarPersonalMinimo(Date fecha, String turno) {
        // Una tienda solo requiere un cajero
        return tieneCajeroAsignado(fecha, turno);
    }
    
    /**
     * Obtiene el tipo de productos que vende la tienda
     * 
     * @return El tipo de productos
     */
    public String getTipoProductos() {
        return tipoProductos;
    }
    
    /**
     * Establece el tipo de productos que vende la tienda
     * 
     * @param tipoProductos El nuevo tipo de productos
     */
    public void setTipoProductos(String tipoProductos) {
        this.tipoProductos = tipoProductos;
    }
    
    /**
     * Obtiene el inventario de la tienda
     * 
     * @return Mapa con el inventario
     */
    public Map<String, Integer> getInventario() {
        return new HashMap<>(inventario);
    }
    
    /**
     * Establece el inventario de la tienda
     * 
     * @param inventario El nuevo inventario
     */
    public void setInventario(Map<String, Integer> inventario) {
        this.inventario = inventario != null ? new HashMap<>(inventario) : new HashMap<>();
    }
    
    @Override
    public String toString() {
        return "Tienda [nombre=" + nombre + ", ubicacion=" + ubicacion + ", tipoProductos=" + tipoProductos + ", items en inventario=" + inventario.size() + "]";
    }
}