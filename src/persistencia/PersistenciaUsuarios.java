package persistencia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import excepciones.UsuarioException;
import modelo.usuarios.Administrador;
import modelo.usuarios.Cliente;
import modelo.usuarios.Usuario;

/**
 * Clase para la persistencia de usuarios
 */
public class PersistenciaUsuarios {
    private static final String ARCHIVO_CLIENTES = "clientes.txt";
    private static final String ARCHIVO_ADMINISTRADORES = "administradores.txt";
    
    /**
     * Guarda una lista de clientes
     * 
     * @param clientes La lista a guardar
     * @throws UsuarioException Si hay un error al guardar los clientes
     */
    public void guardarClientes(List<Cliente> clientes) throws UsuarioException {
        try {
            List<String> lineas = new ArrayList<>();
            
            for (Cliente cliente : clientes) {
                // Construir una línea con los datos del cliente separados por |
                StringBuilder sb = new StringBuilder();
                sb.append(cliente.getNombre()).append("|");
                sb.append(cliente.getId()).append("|");
                sb.append(cliente.getEmail()).append("|");
                sb.append(cliente.getPassword()).append("|");
                sb.append(cliente.getAltura()).append("|");
                sb.append(cliente.getPeso()).append("|");
                sb.append(cliente.getEdad()).append("|");
                
                // Guardar condiciones de salud
                List<String> condiciones = cliente.getCondicionesSalud();
                if (condiciones.isEmpty()) {
                    sb.append("null");
                } else {
                    for (int i = 0; i < condiciones.size(); i++) {
                        if (i > 0) {
                            sb.append(",");
                        }
                        sb.append(condiciones.get(i));
                    }
                }
                
                // Nota: No guardamos los tiquetes aquí, ya que estos se manejan en PersistenciaTiquetes
                // y se asocian a los clientes cuando se cargan
                
                lineas.add(sb.toString());
            }
            
            ArchivoUtil.guardarLineas(lineas, ARCHIVO_CLIENTES);
            
        } catch (IOException e) {
            throw new UsuarioException("Error al guardar los clientes", e);
        }
    }
    
    /**
     * Guarda una lista de administradores
     * 
     * @param administradores La lista a guardar
     * @throws UsuarioException Si hay un error al guardar los administradores
     */
    public void guardarAdministradores(List<Administrador> administradores) throws UsuarioException {
        try {
            List<String> lineas = new ArrayList<>();
            
            for (Administrador admin : administradores) {
                // Construir una línea con los datos del administrador separados por |
                StringBuilder sb = new StringBuilder();
                sb.append(admin.getNombre()).append("|");
                sb.append(admin.getId()).append("|");
                sb.append(admin.getEmail()).append("|");
                sb.append(admin.getPassword());
                
                // Nota: No guardamos las atracciones, empleados, etc., ya que estos se manejan
                // en sus respectivas clases de persistencia
                
                lineas.add(sb.toString());
            }
            
            ArchivoUtil.guardarLineas(lineas, ARCHIVO_ADMINISTRADORES);
            
        } catch (IOException e) {
            throw new UsuarioException("Error al guardar los administradores", e);
        }
    }
    
    /**
     * Carga la lista de clientes
     * 
     * @return La lista de clientes
     * @throws UsuarioException Si hay un error al cargar los clientes
     */
    public List<Cliente> cargarClientes() throws UsuarioException {
        List<Cliente> clientes = new ArrayList<>();
        
        try {
            if (ArchivoUtil.existeArchivo(ARCHIVO_CLIENTES)) {
                List<String> lineas = ArchivoUtil.cargarLineas(ARCHIVO_CLIENTES);
                
                for (String linea : lineas) {
                    String[] partes = linea.split("\\|");
                    
                    if (partes.length >= 8) {
                        String nombre = partes[0];
                        int id = Integer.parseInt(partes[1]);
                        String email = partes[2];
                        String password = partes[3];
                        
                        Cliente cliente = new Cliente(nombre, id, email, password);
                        
                        // Cargar datos adicionales
                        float altura = Float.parseFloat(partes[4]);
                        float peso = Float.parseFloat(partes[5]);
                        int edad = Integer.parseInt(partes[6]);
                        
                        cliente.setAltura(altura);
                        cliente.setPeso(peso);
                        cliente.setEdad(edad);
                        
                        // Cargar condiciones de salud
                        if (!partes[7].equals("null")) {
                            String[] condiciones = partes[7].split(",");
                            for (String condicion : condiciones) {
                                cliente.agregarCondicionSalud(condicion);
                            }
                        }
                        
                        clientes.add(cliente);
                    }
                }
            }
        } catch (IOException e) {
            throw new UsuarioException("Error al cargar los clientes", e);
        }
        
        return clientes;
    }
    
    /**
     * Carga la lista de administradores
     * 
     * @return La lista de administradores
     * @throws UsuarioException Si hay un error al cargar los administradores
     */
    public List<Administrador> cargarAdministradores() throws UsuarioException {
        List<Administrador> administradores = new ArrayList<>();
        
        try {
            if (ArchivoUtil.existeArchivo(ARCHIVO_ADMINISTRADORES)) {
                List<String> lineas = ArchivoUtil.cargarLineas(ARCHIVO_ADMINISTRADORES);
                
                for (String linea : lineas) {
                    String[] partes = linea.split("\\|");
                    
                    if (partes.length >= 4) {
                        String nombre = partes[0];
                        int id = Integer.parseInt(partes[1]);
                        String email = partes[2];
                        String password = partes[3];
                        
                        Administrador admin = new Administrador(nombre, id, email, password);
                        
                        administradores.add(admin);
                    }
                }
            }
        } catch (IOException e) {
            throw new UsuarioException("Error al cargar los administradores", e);
        }
        
        return administradores;
    }
    
    /**
     * Carga todos los usuarios
     * 
     * @return Lista con todos los usuarios
     * @throws UsuarioException Si hay un error al cargar los usuarios
     */
    public List<Usuario> cargarTodosUsuarios() throws UsuarioException {
        List<Usuario> usuarios = new ArrayList<>();
        usuarios.addAll(cargarClientes());
        usuarios.addAll(cargarAdministradores());
        return usuarios;
    }
    
    /**
     * Busca un usuario por su correo electrónico
     * 
     * @param email El correo electrónico a buscar
     * @return El usuario encontrado, o null si no existe
     * @throws UsuarioException Si hay un error al buscar el usuario
     */
    public Usuario buscarUsuarioPorEmail(String email) throws UsuarioException {
        if (email == null || email.isEmpty()) {
            return null;
        }
        
        List<Usuario> usuarios = cargarTodosUsuarios();
        
        for (Usuario usuario : usuarios) {
            if (usuario.getEmail().equals(email)) {
                return usuario;
            }
        }
        
        return null;
    }
    
    /**
     * Autentica a un usuario con su correo electrónico y contraseña
     * 
     * @param email El correo electrónico
     * @param password La contraseña
     * @return El usuario autenticado, o null si las credenciales son incorrectas
     * @throws UsuarioException Si hay un error al autenticar al usuario
     */
    public Usuario autenticarUsuario(String email, String password) throws UsuarioException {
        if (email == null || email.isEmpty() || password == null) {
            return null;
        }
        
        Usuario usuario = buscarUsuarioPorEmail(email);
        
        if (usuario != null && usuario.verificarCredenciales(email, password)) {
            return usuario;
        }
        
        return null;
    }
    
    /**
     * Asocia tiquetes a sus clientes correspondientes
     * 
     * @param clientes Lista de clientes
     * @param tiquetes Lista de tiquetes
     * @throws UsuarioException Si hay un error al asociar los tiquetes
     */
    public void asociarTiquetesAClientes(List<Cliente> clientes, List<modelo.tiquetes.Tiquete> tiquetes) throws UsuarioException {
        // En una implementación real, aquí debería haber un archivo o tabla que relacione
        // los IDs de los clientes con los IDs de los tiquetes que han comprado
        
        // Por simplicidad, este método no hace nada en esta implementación
        // En un sistema real, esta relación debería guardarse y cargarse de forma similar
        // a como se maneja el resto de los datos
    }
}