package tests;


import static org.junit.Assert.*;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import modelo.atracciones.Atraccion;
import modelo.atracciones.AtraccionCultural;
import modelo.atracciones.AtraccionMecanica;
import modelo.tiquetes.EnTemporada;
import modelo.tiquetes.FastPass;
import modelo.tiquetes.Individual;
import modelo.tiquetes.TiqueteBasico;
import modelo.util.NivelExclusividad;

public class TestTiquete {
    
    private TiqueteBasico tiqueteBasicoFamiliar;
    private TiqueteBasico tiqueteBasicoOro;
    private TiqueteBasico tiqueteBasicoDiamante;
    private EnTemporada tiqueteTemporada;
    private Individual tiqueteIndividual;
    private FastPass fastPass;
    
    private AtraccionMecanica atraccionFamiliar;
    private AtraccionCultural atraccionOro;
    private AtraccionMecanica atraccionDiamante;
    
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
        
        // Crear atracciones con diferentes niveles de exclusividad
        atraccionFamiliar = new AtraccionMecanica(
            "Carrusel",
            "Ninguna",
            false,
            null,
            null,
            NivelExclusividad.FAMILIAR,
            1,
            "Zona Infantil",
            30,
            90.0f,
            200.0f,
            0.0f,
            100.0f,
            "",
            "medio"
        );
        
        atraccionOro = new AtraccionCultural(
            "Teatro 4D",
            "Ninguna",
            false,
            null,
            null,
            NivelExclusividad.ORO,
            1,
            "Zona Central",
            50,
            8
        );
        
        atraccionDiamante = new AtraccionMecanica(
            "Montaña Rusa Extrema",
            "No operar en caso de tormenta eléctrica",
            false,
            null,
            null,
            NivelExclusividad.DIAMANTE,
            2,
            "Zona Extrema",
            50,
            120.0f,
            200.0f,
            40.0f,
            120.0f,
            "vértigo, problemas cardíacos",
            "alto"
        );
        
        // Crear tiquetes básicos con diferentes niveles de exclusividad
        tiqueteBasicoFamiliar = new TiqueteBasico(1, "Tiquete Familiar", 1, NivelExclusividad.FAMILIAR, fechaActual, "Activo", "Taquilla", "Adulto", false);
        tiqueteBasicoOro = new TiqueteBasico(2, "Tiquete Oro", 1, NivelExclusividad.ORO, fechaActual, "Activo", "Taquilla", "Adulto", false);
        tiqueteBasicoDiamante = new TiqueteBasico(3, "Tiquete Diamante", 1, NivelExclusividad.DIAMANTE, fechaActual, "Activo", "Taquilla", "Adulto", false);
        
        // Crear tiquete de temporada
        tiqueteTemporada = new EnTemporada(4, "Pase Mensual", 1, NivelExclusividad.ORO, fechaActual, "Activo", "Online", fechaActual, fechaFutura, "Mensual", "Adulto", false);
        
        // Crear tiquete individual
        tiqueteIndividual = new Individual(atraccionDiamante, 5, "Entrada Montaña Rusa", 1, NivelExclusividad.FAMILIAR, fechaActual, "Activo", "Taquilla", false);
        
        // Crear FastPass
        fastPass = new FastPass(tiqueteBasicoFamiliar, fechaActual);
    }
    
    @Test
    public void testPuedeAccederAtraccionTiqueteBasico() {
        // El tiquete familiar solo debe permitir acceso a atracciones familiares
        assertTrue(tiqueteBasicoFamiliar.puedeAccederAtraccion(atraccionFamiliar));
        assertFalse(tiqueteBasicoFamiliar.puedeAccederAtraccion(atraccionOro));
        assertFalse(tiqueteBasicoFamiliar.puedeAccederAtraccion(atraccionDiamante));
        
        // El tiquete oro debe permitir acceso a atracciones familiares y oro
        assertTrue(tiqueteBasicoOro.puedeAccederAtraccion(atraccionFamiliar));
        assertTrue(tiqueteBasicoOro.puedeAccederAtraccion(atraccionOro));
        assertFalse(tiqueteBasicoOro.puedeAccederAtraccion(atraccionDiamante));
        
        // El tiquete diamante debe permitir acceso a todas las atracciones
        assertTrue(tiqueteBasicoDiamante.puedeAccederAtraccion(atraccionFamiliar));
        assertTrue(tiqueteBasicoDiamante.puedeAccederAtraccion(atraccionOro));
        assertTrue(tiqueteBasicoDiamante.puedeAccederAtraccion(atraccionDiamante));
    }
    
    @Test
    public void testPuedeAccederAtraccionTiqueteTemporada() {
        // Verificar que el tiquete de temporada respeta su nivel de exclusividad (Oro)
        assertTrue(tiqueteTemporada.puedeAccederAtraccion(atraccionFamiliar));
        assertTrue(tiqueteTemporada.puedeAccederAtraccion(atraccionOro));
        assertFalse(tiqueteTemporada.puedeAccederAtraccion(atraccionDiamante));
        
        // Verificar que el tiquete está vigente
        assertTrue(tiqueteTemporada.estaVigente(fechaActual));
        assertTrue(tiqueteTemporada.estaVigente(new Date(fechaActual.getTime() + 15L * 24 * 60 * 60 * 1000))); // 15 días después
        
        // Verificar que el tiquete no está vigente fuera de su periodo
        assertFalse(tiqueteTemporada.estaVigente(fechaPasada));
        assertFalse(tiqueteTemporada.estaVigente(new Date(fechaFutura.getTime() + 24 * 60 * 60 * 1000))); // Un día después de vencer
        
        // Un tiquete de temporada que no está vigente no debería permitir acceso
        tiqueteTemporada.setFechaFin(fechaPasada); // Hacer que el tiquete haya vencido
        assertFalse(tiqueteTemporada.puedeAccederAtraccion(atraccionFamiliar));
        assertFalse(tiqueteTemporada.puedeAccederAtraccion(atraccionOro));
    }
    
    @Test
    public void testPuedeAccederAtraccionTiqueteIndividual() {
        // El tiquete individual solo debe permitir acceso a la atracción específica
        assertFalse(tiqueteIndividual.puedeAccederAtraccion(atraccionFamiliar));
        assertFalse(tiqueteIndividual.puedeAccederAtraccion(atraccionOro));
        assertTrue(tiqueteIndividual.puedeAccederAtraccion(atraccionDiamante));
        
        // Si el tiquete está marcado como usado, no debe permitir acceso
        tiqueteIndividual.marcarComoUsado();
        assertFalse(tiqueteIndividual.puedeAccederAtraccion(atraccionDiamante));
    }
    
    @Test
    public void testFastPass() {
        // El FastPass debería ser válido para la fecha actual
        assertTrue(fastPass.esValido(fechaActual));
        
        // No debería ser válido para otras fechas
        assertFalse(fastPass.esValido(fechaPasada));
        assertFalse(fastPass.esValido(fechaFutura));
        
        // Si se marca como usado, ya no debería ser válido
        fastPass.marcarComoUsado();
        assertFalse(fastPass.esValido(fechaActual));
        
        // Verificar que el tiquete asociado es correcto
        assertEquals(tiqueteBasicoFamiliar, fastPass.getTiqueteAsociado());
    }
    
    @Test
    public void testNivelExclusividad() {
        // Verificar la utilidad para comprobar niveles de exclusividad
        assertTrue(NivelExclusividad.tieneAcceso(NivelExclusividad.FAMILIAR, NivelExclusividad.FAMILIAR));
        assertFalse(NivelExclusividad.tieneAcceso(NivelExclusividad.FAMILIAR, NivelExclusividad.ORO));
        assertFalse(NivelExclusividad.tieneAcceso(NivelExclusividad.FAMILIAR, NivelExclusividad.DIAMANTE));
        
        assertTrue(NivelExclusividad.tieneAcceso(NivelExclusividad.ORO, NivelExclusividad.FAMILIAR));
        assertTrue(NivelExclusividad.tieneAcceso(NivelExclusividad.ORO, NivelExclusividad.ORO));
        assertFalse(NivelExclusividad.tieneAcceso(NivelExclusividad.ORO, NivelExclusividad.DIAMANTE));
        
        assertTrue(NivelExclusividad.tieneAcceso(NivelExclusividad.DIAMANTE, NivelExclusividad.FAMILIAR));
        assertTrue(NivelExclusividad.tieneAcceso(NivelExclusividad.DIAMANTE, NivelExclusividad.ORO));
        assertTrue(NivelExclusividad.tieneAcceso(NivelExclusividad.DIAMANTE, NivelExclusividad.DIAMANTE));
    }
    
    @Test
    public void testDescuentoEmpleado() {
        // Verificar que por defecto no hay descuento de empleado
        assertFalse(tiqueteBasicoFamiliar.isDctoEmpleado());
        
        // Aplicar descuento de empleado
        tiqueteBasicoFamiliar.setDctoEmpleado(true);
        assertTrue(tiqueteBasicoFamiliar.isDctoEmpleado());
    }
}
