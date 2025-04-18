package modelo.usuarios;

import java.io.Serializable;

/**
 * Clase abstracta que representa un usuario del sistema
 */
public abstract class Usuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    protected String email;
    protected String password;
    
    /**
     * Constructor de Usuario
     * 
     * @param email Correo electrónico del usuario
     * @param password Contraseña del usuario
     */
    public Usuario(String email, String password) {
        this.email = email;
        this.password = password;
    }
    
    /**
     * Verifica si las credenciales proporcionadas son correctas
     * 
     * @param email Correo electrónico a verificar
     * @param password Contraseña a verificar
     * @return true si las credenciales son correctas, false de lo contrario
     */
    public boolean verificarCredenciales(String email, String password) {
        return this.email.equals(email) && this.password.equals(password);
    }
    
    /**
     * Obtiene el correo electrónico del usuario
     * 
     * @return El correo electrónico
     */
    public String getEmail() {
        return email;
    }
    
    /**
     * Establece el correo electrónico del usuario
     * 
     * @param email El nuevo correo electrónico
     */
    public void setEmail(String email) {
        this.email = email;
    }
    
    /**
     * Obtiene la contraseña del usuario
     * 
     * @return La contraseña
     */
    public String getPassword() {
        return password;
    }
    
    /**
     * Establece la contraseña del usuario
     * 
     * @param password La nueva contraseña
     */
    public void setPassword(String password) {
        this.password = password;
    }
}