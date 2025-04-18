package modelo.lugares;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import excepciones.TiqueteException;
import modelo.tiquetes.Tiquete;
import modelo.usuarios.Cliente;

/**
 * Clase que representa una taquilla en el parque
 */
public class Taquilla extends LugarServicio {
    private static final long serialVersionUID = 1L;
    
    private String metodoPago;
    private List<Tiquete> tiquetesDisponibles;
    
    /**
     * Constructor de Taquilla
     * 
     * @param id Identificador único de la taquilla
     * @param nombre Nombre de la taquilla
     * @param ubicacion Ubicación de la taquilla
     * @param metodoPago Método de pago aceptado
     */
    public Taquilla(String id, String nombre, String ubicacion, String metodoPago) {
        super(id, nombre, ubicacion, "Taquilla", false); // Las taquillas no requieren cocinero
        this.metodoPago = metodoPago;
        this.tiquetesDisponibles = new ArrayList<>();
    }
    
    /**
     * Vende un tiquete a un cliente
     * 
     * @param tipo Tipo de tiquete a vender
     * @param cliente El cliente que compra el tiquete
     * @return El tiquete vendido, o null si no se pudo vender
     * @throws TiqueteException Si hay un error al vender el tiquete
     */
    public Tiquete venderTiquete(String tipo, Cliente cliente) throws TiqueteException {
        if (tipo == null || tipo.isEmpty() || cliente == null) {
            throw new TiqueteException("El tipo de tiquete y el cliente no pueden ser nulos");
        }
        
        // Buscar un tiquete del tipo solicitado
        Tiquete tiqueteVendido = null;
        for (Tiquete tiquete : tiquetesDisponibles) {
            if (tiquete.getNombre().equals(tipo) && !tiquete.isUsado()) {
                tiqueteVendido = tiquete;
                break;
            }
        }
        
        if (tiqueteVendido == null) {
            throw new TiqueteException("No hay tiquetes disponibles del tipo solicitado");
        }
        
        // Registrar la compra
        if (cliente.comprarTiquete(tiqueteVendido)) {
            tiquetesDisponibles.remove(tiqueteVendido);
            return tiqueteVendido;
        } else {
            throw new TiqueteException("Error al asignar el tiquete al cliente");
        }
    }
    
    /**
     * Agrega un tiquete a la lista de tiquetes disponibles
     * 
     * @param tiquete El tiquete a agregar
     * @return true si el tiquete fue agregado
     */
    public boolean agregarTiquete(Tiquete tiquete) {
        if (tiquete == null) {
            return false;
        }
        
        tiquetesDisponibles.add(tiquete);
        return true;
    }
    
    /**
     * Verifica si la taquilla tiene el personal mínimo necesario
     * 
     * @param fecha La fecha a verificar
     * @param turno El turno a verificar
     * @return true si la taquilla tiene el personal mínimo
     */
    public boolean verificarPersonalMinimo(Date fecha, String turno) {
        // Una taquilla solo requiere un cajero
        return tieneCajeroAsignado(fecha, turno);
    }
    
    /**
     * Obtiene el método de pago aceptado
     * 
     * @return El método de pago
     */
    public String getMetodoPago() {
        return metodoPago;
    }
    
    /**
     * Establece el método de pago aceptado
     * 
     * @param metodoPago El nuevo método de pago
     */
    public void setMetodoPago(String metodoPago) {
        this.metodoPago = metodoPago;
    }
    
    /**
     * Obtiene los tiquetes disponibles
     * 
     * @return Lista de tiquetes disponibles
     */
    public List<Tiquete> getTiquetesDisponibles() {
        return new ArrayList<>(tiquetesDisponibles);
    }
    
    /**
     * Establece los tiquetes disponibles
     * 
     * @param tiquetesDisponibles Lista de tiquetes disponibles
     */
    public void setTiquetesDisponibles(List<Tiquete> tiquetesDisponibles) {
        this.tiquetesDisponibles = tiquetesDisponibles != null ? new ArrayList<>(tiquetesDisponibles) : new ArrayList<>();
    }
    
    @Override
    public String toString() {
        return "Taquilla [nombre=" + nombre + ", ubicacion=" + ubicacion + ", metodoPago=" + metodoPago + ", tiquetes disponibles=" + tiquetesDisponibles.size() + "]";
    }
}