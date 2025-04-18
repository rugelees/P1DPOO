package modelo.empleados;

import excepciones.TiqueteException;
import modelo.lugares.LugarServicio;
import modelo.tiquetes.Tiquete;
import modelo.usuarios.Cliente;

/**
 * Clase que representa a un cajero del parque
 */
public class Cajero extends Empleado {
    private static final long serialVersionUID = 1L;
    
    private LugarServicio lugarAsignado;
    
    /**
     * Constructor de Cajero
     * 
     * @param tipo Tipo de empleado
     * @param nombre Nombre del empleado
     * @param id Identificador único del empleado
     * @param servicioGeneral Indica si el empleado está asignado a servicio general
     * @param email Correo electrónico del empleado
     * @param password Contraseña del empleado
     * @param horasExtras Indica si el empleado realiza horas extras
     */
    public Cajero(String tipo, String nombre, int id, boolean servicioGeneral, 
            String email, String password, boolean horasExtras) {
        super(tipo, nombre, id, servicioGeneral, email, password, horasExtras);
    }
    
    /**
     * Vende un tiquete a un cliente
     * 
     * @param cliente El cliente que compra el tiquete
     * @param tiquete El tiquete a vender
     * @return true si la venta fue exitosa
     * @throws TiqueteException Si hay un error al vender el tiquete
     */
    public boolean venderTiquete(Cliente cliente, Tiquete tiquete) throws TiqueteException {
        if (cliente == null || tiquete == null) {
            throw new TiqueteException("El cliente y el tiquete no pueden ser nulos");
        }
        
        // Aquí podríamos agregar lógica adicional para la venta del tiquete
        
        // Registrar el tiquete para el cliente
        return cliente.comprarTiquete(tiquete);
    }
    
    /**
     * Registra un tiquete en el sistema
     */
    public void registrarTiquete() {
        // Método simplificado para el ejemplo
        // En una implementación real, se registraría el tiquete en un sistema de ventas
    }
    
    /**
     * Asigna un lugar de servicio al cajero
     * 
     * @param lugarServicio El lugar de servicio a asignar
     */
    public void asignarLugarServicio(LugarServicio lugarServicio) {
        this.lugarAsignado = lugarServicio;
    }
    
    /**
     * Obtiene el lugar asignado al cajero
     * 
     * @return El lugar asignado
     */
    public LugarServicio getLugarAsignado() {
        return lugarAsignado;
    }
    
    /**
     * Establece el lugar asignado al cajero
     * 
     * @param lugarAsignado El nuevo lugar asignado
     */
    public void setLugarAsignado(LugarServicio lugarAsignado) {
        this.lugarAsignado = lugarAsignado;
    }
}