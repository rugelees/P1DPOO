package tests;

import static org.junit.Assert.*;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import excepciones.TiqueteException;
import modelo.tiquetes.TiqueteBasico;
import modelo.usuarios.Cliente;

public class TestCliente {
    
    private Cliente cliente;
    private TiqueteBasico tiquete;
    private Date fechaActual;
    
    @Before
    public void setUp() {
        fechaActual = new Date();
        
        // Crear cliente
        cliente = new Cliente("Juan Pérez", 1, "juan@ejemplo.com", "clave123", 170.0f, 70.0f, 25);
        
        // Crear tiquete
        tiquete = new TiqueteBasico(1, "Tiquete Básico", 1, "Familiar", fechaActual, "Activo", "Taquilla", "Adulto", false);
    }
    
    @Test
    public void testPropiedadesCliente() {
        // Verificar propiedades básicas
        assertEquals("Juan Pérez", cliente.getNombre());
        assertEquals(1, cliente.getId());
        assertEquals("juan@ejemplo.com", cliente.getEmail());
        assertEquals("clave123", cliente.getPassword());
        
        // Verificar propiedades físicas
        assertEquals(170.0f, cliente.getAltura(), 0.01);
        assertEquals(70.0f, cliente.getPeso(), 0.01);
        assertEquals(25, cliente.getEdad());
        
        // Inicialmente no debe tener condiciones de salud
        assertTrue(cliente.getCondicionesSalud().isEmpty());
        
        // Agregar una condición de salud
        cliente.agregarCondicionSalud("vértigo");
        assertEquals(1, cliente.getCondicionesSalud().size());
        assertTrue(cliente.tieneCondicionSalud("vértigo"));
        assertFalse(cliente.tieneCondicionSalud("problemas cardíacos"));
    }
    
    @Test
    public void testComprarTiquete() {
        // Inicialmente el cliente no debe tener tiquetes
        assertTrue(cliente.getTiquetes().isEmpty());
        
        try {
            // Comprar un tiquete
            assertTrue(cliente.comprarTiquete(tiquete));
            
            // Verificar que el cliente tiene el tiquete
            List<TiqueteBasico> tiquetes = (List) cliente.getTiquetes();
            assertEquals(1, tiquetes.size());
            assertEquals(tiquete, tiquetes.get(0));
            
            // Comprar otro tiquete
            TiqueteBasico otroTiquete = new TiqueteBasico(2, "Tiquete Oro", 1, "Oro", fechaActual, "Activo", "Online", "Adulto", false);
            assertTrue(cliente.comprarTiquete(otroTiquete));
            
            // Verificar que ahora tiene dos tiquetes
            assertEquals(2, cliente.getTiquetes().size());
            assertTrue(cliente.getTiquetes().contains(tiquete));
            assertTrue(cliente.getTiquetes().contains(otroTiquete));
            
        } catch (TiqueteException e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    @Test(expected = TiqueteException.class)
    public void testComprarTiqueteNulo() throws TiqueteException {
        // Intentar comprar un tiquete nulo debería lanzar excepción
        cliente.comprarTiquete(null);
    }
    
    @Test
    public void testVerificarCredenciales() {
        // Verificar con credenciales correctas
        assertTrue(cliente.verificarCredenciales("juan@ejemplo.com", "clave123"));
        
        // Verificar con email incorrecto
        assertFalse(cliente.verificarCredenciales("otro@ejemplo.com", "clave123"));
        
        // Verificar con password incorrecto
        assertFalse(cliente.verificarCredenciales("juan@ejemplo.com", "otraClave"));
        
        // Cambiar el email y verificar
        cliente.setEmail("nuevo@ejemplo.com");
        assertTrue(cliente.verificarCredenciales("nuevo@ejemplo.com", "clave123"));
        assertFalse(cliente.verificarCredenciales("juan@ejemplo.com", "clave123"));
        
        // Cambiar el password y verificar
        cliente.setPassword("nuevaClave");
        assertTrue(cliente.verificarCredenciales("nuevo@ejemplo.com", "nuevaClave"));
        assertFalse(cliente.verificarCredenciales("nuevo@ejemplo.com", "clave123"));
    }
}