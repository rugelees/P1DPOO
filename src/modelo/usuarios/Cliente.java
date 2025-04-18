package modelo.usuarios;
import java.util.ArrayList;
import java.util.List;
import excepciones.TiqueteException;
import modelo.tiquetes.Tiquete;

/**
 * Clase que representa a un cliente del parque
 */
public class Cliente extends Usuario {
    private static final long serialVersionUID = 1L;
    
    private List<Tiquete> tiquetes;
    private String nombre;
    private int id;
    private float altura;
    private float peso;
    private int edad;
    private List<String> condicionesSalud;
    
    /**
     * Constructor de Cliente
     * 
     * @param nombre Nombre del cliente
     * @param id Identificador único del cliente
     * @param email Correo electrónico del cliente
     * @param password Contraseña del cliente
     */
    public Cliente(String nombre, int id, String email, String password) {
        super(email, password);
        this.nombre = nombre;
        this.id = id;
        this.tiquetes = new ArrayList<>();
        this.condicionesSalud = new ArrayList<>();
    }
    
    /**
     * Constructor completo de Cliente
     * 
     * @param nombre Nombre del cliente
     * @param id Identificador único del cliente
     * @param email Correo electrónico del cliente
     * @param password Contraseña del cliente
     * @param altura Altura del cliente en cm
     * @param peso Peso del cliente en kg
     * @param edad Edad del cliente en años
     */
    public Cliente(String nombre, int id, String email, String password, float altura, float peso, int edad) {
        this(nombre, id, email, password);
        this.altura = altura;
        this.peso = peso;
        this.edad = edad;
    }
    
    /**
     * Permite al cliente comprar un tiquete
     * 
     * @param tiquete El tiquete a comprar
     * @return true si la compra fue exitosa
     * @throws TiqueteException Si ocurre un error al comprar el tiquete
     */
    public boolean comprarTiquete(Tiquete tiquete) throws TiqueteException {
        if (tiquete == null) {
            throw new TiqueteException("El tiquete no puede ser nulo");
        }
        
        tiquetes.add(tiquete);
        return true;
    }
    
    /**
     * Agrega una condición de salud al cliente
     * 
     * @param condicion La condición de salud a agregar
     */
    public void agregarCondicionSalud(String condicion) {
        if (condicion != null && !condicion.isEmpty()) {
            condicionesSalud.add(condicion);
        }
    }
    
    /**
     * Obtiene la lista de tiquetes del cliente
     * 
     * @return La lista de tiquetes
     */
    public List<Tiquete> getTiquetes() {
        return new ArrayList<>(tiquetes);
    }
    
    /**
     * Obtiene el nombre del cliente
     * 
     * @return El nombre del cliente
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre del cliente
     * 
     * @param nombre El nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene el identificador del cliente
     * 
     * @return El identificador
     */
    public int getId() {
        return id;
    }
    
    /**
     * Obtiene la altura del cliente
     * 
     * @return La altura en cm
     */
    public float getAltura() {
        return altura;
    }
    
    /**
     * Establece la altura del cliente
     * 
     * @param altura La nueva altura en cm
     */
    public void setAltura(float altura) {
        this.altura = altura;
    }
    
    /**
     * Obtiene el peso del cliente
     * 
     * @return El peso en kg
     */
    public float getPeso() {
        return peso;
    }
    
    /**
     * Establece el peso del cliente
     * 
     * @param peso El nuevo peso en kg
     */
    public void setPeso(float peso) {
        this.peso = peso;
    }
    
    /**
     * Obtiene la edad del cliente
     * 
     * @return La edad en años
     */
    public int getEdad() {
        return edad;
    }
    
    /**
     * Establece la edad del cliente
     * 
     * @param edad La nueva edad en años
     */
    public void setEdad(int edad) {
        this.edad = edad;
    }
    
    /**
     * Obtiene las condiciones de salud del cliente
     * 
     * @return Lista de condiciones de salud
     */
    public List<String> getCondicionesSalud() {
        return new ArrayList<>(condicionesSalud);
    }
    
    /**
     * Verifica si el cliente tiene una condición de salud específica
     * 
     * @param condicion La condición a verificar
     * @return true si el cliente tiene la condición, false de lo contrario
     */
    public boolean tieneCondicionSalud(String condicion) {
        return condicionesSalud.contains(condicion);
    }
    
    @Override
    public String toString() {
        return "Cliente [id=" + id + ", nombre=" + nombre + ", email=" + email + "]";
    }
}
