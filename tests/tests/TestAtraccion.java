package tests;

import static org.junit.Assert.*;
import java.util.Date;
import org.junit.Before;
import org.junit.Test;
import modelo.atracciones.AtraccionMecanica;
import modelo.atracciones.AtraccionCultural;
import modelo.usuarios.Cliente;

public class TestAtraccion {
    
    private AtraccionMecanica atraccionMecanica;
    private AtraccionCultural atraccionCultural;
    private Cliente clienteValido;
    private Cliente clienteInvalido;
    private Date fechaActual;
    private Date fechaFutura;
    private Date fechaPasada;
    
    @Before
    public void setUp() {
        // Fechas para pruebas
        fechaActual = new Date();
        
        // Fecha futura (un año después)
        fechaFutura = new Date();
        fechaFutura.setTime(fechaActual.getTime() + 365L * 24 * 60 * 60 * 1000);
        
        // Fecha pasada (un año antes)
        fechaPasada = new Date();
        fechaPasada.setTime(fechaActual.getTime() - 365L * 24 * 60 * 60 * 1000);
        
        // Crear atracción mecánica
        atraccionMecanica = new AtraccionMecanica(
            "Montaña Rusa",
            "No operar en caso de tormenta eléctrica",
            false,
            null,
            null,
            "Oro",
            2,
            "Zona Norte",
            50,
            120.0f,  // altura mínima 120 cm
            200.0f,  // altura máxima 200 cm
            40.0f,   // peso mínimo 40 kg
            120.0f,  // peso máximo 120 kg
            "vértigo, problemas cardíacos",
            "alto"
        );
        
        // Crear atracción cultural
        atraccionCultural = new AtraccionCultural(
            "Casa del Terror",
            "Ninguna",
            false,
            null,
            null,
            "Familiar",
            1,
            "Zona Sur",
            30,
            12    // edad mínima 12 años
        );
        
        // Crear cliente válido para ambas atracciones
        clienteValido = new Cliente("Juan Pérez", 1, "juan@ejemplo.com", "clave123", 170.0f, 70.0f, 25);
        
        // Crear cliente que no cumple los requisitos
        clienteInvalido = new Cliente("Niño Pequeño", 2, "niño@ejemplo.com", "clave123", 110.0f, 30.0f, 8);
        clienteInvalido.agregarCondicionSalud("vértigo");
    }
    
    @Test
    public void testVerificarRestriccionesFisicas() {
        // Cliente válido debe pasar las restricciones físicas
        assertTrue(atraccionMecanica.verificarRestriccionesFisicas(clienteValido));
        
        // Cliente inválido no debe pasar las restricciones físicas
        assertFalse(atraccionMecanica.verificarRestriccionesFisicas(clienteInvalido));
    }
    
    @Test
    public void testVerificarContraindicaciones() {
        // Cliente válido no tiene contraindicaciones
        assertTrue(atraccionMecanica.verificarContraindicaciones(clienteValido));
        
        // Cliente inválido tiene vértigo
        assertFalse(atraccionMecanica.verificarContraindicaciones(clienteInvalido));
    }
    
    @Test
    public void testVerificarRestriccionEdad() {
        // Cliente válido es mayor de 12 años
        assertTrue(atraccionCultural.verificarRestriccionEdad(clienteValido));
        
        // Cliente inválido es menor de 12 años
        assertFalse(atraccionCultural.verificarRestriccionEdad(clienteInvalido));
    }
    
    @Test
    public void testEstaDisponible() {
        // Por defecto, las atracciones no son de temporada, así que deben estar disponibles
        assertTrue(atraccionMecanica.estaDisponible(fechaActual));
        
        // Programar un mantenimiento para hoy
        atraccionMecanica.programarMantenimiento(fechaActual, fechaActual);
        
        // Ahora no debe estar disponible
        assertFalse(atraccionMecanica.estaDisponible(fechaActual));
        
        // Pero debe estar disponible mañana
        Date mañana = new Date(fechaActual.getTime() + 24 * 60 * 60 * 1000);
        assertTrue(atraccionMecanica.estaDisponible(mañana));
        
        // Ahora configurar la atracción para que sea de temporada
        AtraccionMecanica atraccionTemporada = new AtraccionMecanica(
            "Montaña Acuática",
            "No operar en caso de tormenta eléctrica",
            true,  // es de temporada
            fechaActual,
            fechaFutura,
            "Familiar",
            2,
            "Zona Acuática",
            100,
            120.0f,
            200.0f,
            40.0f,
            120.0f,
            "",
            "medio"
        );
        
        // Debe estar disponible entre la fecha actual y la fecha futura
        assertTrue(atraccionTemporada.estaDisponible(fechaActual));
        assertTrue(atraccionTemporada.estaDisponible(new Date(fechaActual.getTime() + 30L * 24 * 60 * 60 * 1000)));
        
        // Pero no debe estar disponible en una fecha pasada o después de la fecha de fin
        assertFalse(atraccionTemporada.estaDisponible(fechaPasada));
        assertFalse(atraccionTemporada.estaDisponible(new Date(fechaFutura.getTime() + 24 * 60 * 60 * 1000)));
    }
    
    @Test
    public void testConsultarInformacion() {
        // Verificar que la información incluye datos clave
        String infoMecanica = atraccionMecanica.consultarInformacion();
        assertTrue(infoMecanica.contains("Montaña Rusa"));
        assertTrue(infoMecanica.contains("Zona Norte"));
        assertTrue(infoMecanica.contains("Oro"));
        assertTrue(infoMecanica.contains("120.0 cm - 200.0 cm"));
        assertTrue(infoMecanica.contains("40.0 kg - 120.0 kg"));
        assertTrue(infoMecanica.contains("alto"));
        
        String infoCultural = atraccionCultural.consultarInformacion();
        assertTrue(infoCultural.contains("Casa del Terror"));
        assertTrue(infoCultural.contains("Zona Sur"));
        assertTrue(infoCultural.contains("Familiar"));
        assertTrue(infoCultural.contains("12 años"));
    }
    
    @Test
    public void testEsRiesgoAltoMedio() {
        // La montaña rusa es de riesgo alto
        assertTrue(atraccionMecanica.esRiesgoAlto());
        assertFalse(atraccionMecanica.esRiesgoMedio());
        
        // Crear una atracción de riesgo medio
        AtraccionMecanica atraccionRiesgoMedio = new AtraccionMecanica(
            "Carrusel",
            "Ninguna",
            false,
            null,
            null,
            "Familiar",
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
        
        assertTrue(atraccionRiesgoMedio.esRiesgoMedio());
        assertFalse(atraccionRiesgoMedio.esRiesgoAlto());
    }
}
