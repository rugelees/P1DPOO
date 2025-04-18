package tests;

import static org.junit.Assert.*;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import modelo.atracciones.Espectaculo;

public class TestEspectaculo {
    
    private Espectaculo espectaculo;
    private Date fechaActual;
    private Date fechaFutura;
    private Date fechaPasada;
    
    @Before
    public void setUp() {
        // Fechas para pruebas
        fechaActual = new Date();
        
        // Fecha futura (un mes después)
        fechaFutura = new Date();
        fechaFutura.setTime(fechaActual.getTime() + 30L * 24 * 60 * 60 * 1000);
        
        // Fecha pasada (un mes antes)
        fechaPasada = new Date();
        fechaPasada.setTime(fechaActual.getTime() - 30L * 24 * 60 * 60 * 1000);
        
        // Crear espectáculo
        espectaculo = new Espectaculo(
            "Show de Delfines",
            "No realizar en caso de lluvia",
            true,  // es de temporada
            fechaActual,
            fechaFutura,
            "45 minutos",
            "15:00",
            100
        );
    }
    
    @Test
    public void testAgregarFuncion() {
        // El espectáculo no debería tener funciones inicialmente
        assertTrue(espectaculo.getFunciones().isEmpty());
        
        // Agregar una función
        Date fechaFuncion = new Date(fechaActual.getTime() + 7L * 24 * 60 * 60 * 1000); // una semana después
        espectaculo.agregarFuncion(fechaFuncion);
        
        // Verificar que se agregó la función
        assertEquals(1, espectaculo.getFunciones().size());
        
        // Intentar agregar la misma función de nuevo (no debería duplicarse)
        espectaculo.agregarFuncion(fechaFuncion);
        assertEquals(1, espectaculo.getFunciones().size());
        
        // Agregar otra función
        Date otraFechaFuncion = new Date(fechaActual.getTime() + 14L * 24 * 60 * 60 * 1000); // dos semanas después
        espectaculo.agregarFuncion(otraFechaFuncion);
        assertEquals(2, espectaculo.getFunciones().size());
    }
    
    @Test
    public void testCancelarFuncion() {
        // Agregar dos funciones
        Date fechaFuncion1 = new Date(fechaActual.getTime() + 7L * 24 * 60 * 60 * 1000);
        Date fechaFuncion2 = new Date(fechaActual.getTime() + 14L * 24 * 60 * 60 * 1000);
        espectaculo.agregarFuncion(fechaFuncion1);
        espectaculo.agregarFuncion(fechaFuncion2);
        
        // Verificar que se agregaron las funciones
        assertEquals(2, espectaculo.getFunciones().size());
        
        // Cancelar una función
        boolean resultado = espectaculo.cancelarFuncion(fechaFuncion1);
        assertTrue(resultado);
        assertEquals(1, espectaculo.getFunciones().size());
        
        // Intentar cancelar una función que no existe
        Date fechaNoExistente = new Date(fechaActual.getTime() + 21L * 24 * 60 * 60 * 1000);
        resultado = espectaculo.cancelarFuncion(fechaNoExistente);
        assertFalse(resultado);
        assertEquals(1, espectaculo.getFunciones().size());
    }
    
    @Test
    public void testEstaDisponible() {
        // El espectáculo no tiene funciones, por lo que no está disponible
        assertFalse(espectaculo.estaDisponible(fechaActual));
        
        // Agregar una función
        Date fechaFuncion = new Date(fechaActual.getTime() + 7L * 24 * 60 * 60 * 1000);
        espectaculo.agregarFuncion(fechaFuncion);
        
        // Ahora el espectáculo está disponible en la fecha de la función
        assertTrue(espectaculo.estaDisponible(fechaFuncion));
        
        // Pero no está disponible en otras fechas
        assertFalse(espectaculo.estaDisponible(fechaActual));
        
        // Verificar que el espectáculo no está disponible fuera de su temporada
        Date fechaFueraDeLaTemporada = new Date(fechaFutura.getTime() + 24 * 60 * 60 * 1000);
        espectaculo.agregarFuncion(fechaFueraDeLaTemporada); // Intentar agregar una función fuera de la temporada
        assertFalse(espectaculo.estaDisponible(fechaFueraDeLaTemporada));
    }
    
    @Test
    public void testConsultarInformacion() {
        // Verificar que la información incluye datos clave
        String info = espectaculo.consultarInformacion();
        assertTrue(info.contains("Show de Delfines"));
        assertTrue(info.contains("45 minutos"));
        assertTrue(info.contains("15:00"));
        assertTrue(info.contains("100 personas"));
        assertTrue(info.contains("No realizar en caso de lluvia"));
        assertTrue(info.contains("Espectáculo de Temporada"));
    }
    
    @Test
    public void testEspectaculoNoDeTemporada() {
        // Crear un espectáculo que no es de temporada
        Espectaculo espectaculoNoTemporada = new Espectaculo(
            "Circo",
            "Ninguna",
            false,  // no es de temporada
            null,
            null,
            "2 horas",
            "19:00",
            200
        );
        
        // Agregar una función
        Date fechaFuncion = new Date(fechaActual.getTime() + 7L * 24 * 60 * 60 * 1000);
        espectaculoNoTemporada.agregarFuncion(fechaFuncion);
        
        // Verificar que está disponible en la fecha de la función
        assertTrue(espectaculoNoTemporada.estaDisponible(fechaFuncion));
        
        // Y no está disponible en otras fechas
        assertFalse(espectaculoNoTemporada.estaDisponible(fechaActual));
    }
}
