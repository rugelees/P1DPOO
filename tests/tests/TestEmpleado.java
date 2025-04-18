package tests;


import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import modelo.atracciones.AtraccionMecanica;
import modelo.empleados.AtraccionAlto;
import modelo.empleados.AtraccionMedio;
import modelo.empleados.Cajero;
import modelo.empleados.Cocinero;
import modelo.empleados.Regular;
import modelo.empleados.ServicioGeneral;
import modelo.lugares.Cafeteria;
import modelo.lugares.LugarServicio;
import modelo.lugares.Taquilla;
import excepciones.TiqueteException;
import modelo.tiquetes.TiqueteBasico;
import modelo.usuarios.Cliente;

public class TestEmpleado {
    
    private AtraccionAlto empleadoAtraccionAlto;
    private AtraccionMedio empleadoAtraccionMedio;
    private Cajero empleadoCajero;
    private Cocinero empleadoCocinero;
    private Regular empleadoRegular;
    private ServicioGeneral empleadoServicioGeneral;
    
    private AtraccionMecanica atraccionAlto;
    private AtraccionMecanica atraccionMedio;
    private Cafeteria cafeteria;
    private Taquilla taquilla;
    private LugarServicio tienda;
    
    private Date fechaActual;
    private Date fechaCapacitacion;
    private Date fechaVencimiento;
    
    @Before
    public void setUp() {
        fechaActual = new Date();
        
        // Fecha de capacitación (hace un mes)
        fechaCapacitacion = new Date();
        fechaCapacitacion.setTime(fechaActual.getTime() - 30L * 24 * 60 * 60 * 1000);
        
        // Fecha de vencimiento (en un año)
        fechaVencimiento = new Date();
        fechaVencimiento.setTime(fechaActual.getTime() + 365L * 24 * 60 * 60 * 1000);
        
        // Crear atracción de riesgo alto
        atraccionAlto = new AtraccionMecanica(
            "Montaña Rusa",
            "No operar en caso de tormenta eléctrica",
            false,
            null,
            null,
            "Oro",
            2,
            "Zona Norte",
            50,
            120.0f,
            200.0f,
            40.0f,
            120.0f,
            "vértigo, problemas cardíacos",
            "alto"
        );
        
        // Crear atracción de riesgo medio
        atraccionMedio = new AtraccionMecanica(
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
        
        // Crear cafetería
        List<String> menu = new ArrayList<>();
        menu.add("Hamburguesas");
        menu.add("Papas fritas");
        menu.add("Refrescos");
        cafeteria = new Cafeteria("CAF1", "Cafetería Central", "Centro del parque", menu, 50);
        
        // Crear taquilla
        taquilla = new Taquilla("TAQ1", "Taquilla Principal", "Entrada del parque", "Efectivo y tarjeta");
        
        // Crear tienda
        tienda = new LugarServicio("TIE1", "Tienda de Recuerdos", "Salida del parque", "Tienda", false);
        
        // Crear empleados
        empleadoAtraccionAlto = new AtraccionAlto(
            "Operador de atracción alta",
            "Juan Pérez",
            1,
            false,
            "juan@ejemplo.com",
            "clave123",
            false,
            true  // capacitado
        );
        
        empleadoAtraccionMedio = new AtraccionMedio(
            fechaCapacitacion,
            fechaVencimiento,
            "Operador de atracción media",
            "María López",
            2,
            false,
            "maria@ejemplo.com",
            "clave123",
            true  // horas extras
        );
        
        empleadoCajero = new Cajero(
            "Cajero",
            "Pedro Gómez",
            3,
            false,
            "pedro@ejemplo.com",
            "clave123",
            false
        );
        
        empleadoCocinero = new Cocinero(
            true,  // capacitado
            "Cocinero",
            "Ana Martínez",
            4,
            false,
            "ana@ejemplo.com",
            "clave123",
            true  // horas extras
        );
        
        empleadoRegular = new Regular(
            true,  // puede ser cajero
            "Regular",
            "Carlos Rodríguez",
            5,
            false,
            "carlos@ejemplo.com",
            "clave123",
            false
        );
        
        List<String> zonas = new ArrayList<>();
        zonas.add("Zona Norte");
        zonas.add("Zona Central");
        empleadoServicioGeneral = new ServicioGeneral(
            zonas,
            "Servicio General",
            "Lucía Sánchez",
            6,
            "lucia@ejemplo.com",
            "clave123",
            false
        );
    }
    
    @Test
    public void testEmpleadoAtraccionAlto() {
        // Verificar si el empleado está capacitado para operar atracciones de riesgo alto
        assertTrue(empleadoAtraccionAlto.puedeOperarAtraccionRiesgoAlto());
        
        // Asignar una atracción de riesgo alto
        assertTrue(empleadoAtraccionAlto.asignarAtraccionAlta(atraccionAlto));
        
        // Verificar que la atracción fue asignada
        List<AtraccionMecanica> atraccionesAsignadas = empleadoAtraccionAlto.getAtraccionesEspecificas();
        assertEquals(1, atraccionesAsignadas.size());
        assertEquals(atraccionAlto, atraccionesAsignadas.get(0));
        
        // Verificar que el empleado está capacitado para esa atracción específica
        assertTrue(empleadoAtraccionAlto.estaCapacitadoParaAtraccion(atraccionAlto));
        
        // No debería poder asignar una atracción de riesgo medio como si fuera de riesgo alto
        assertFalse(empleadoAtraccionAlto.asignarAtraccionAlta(atraccionMedio));
        
        // Remover la atracción
        assertTrue(empleadoAtraccionAlto.removerAtraccionEspecifica(atraccionAlto));
        assertTrue(empleadoAtraccionAlto.getAtraccionesEspecificas().isEmpty());
    }
    
    @Test
    public void testEmpleadoAtraccionMedio() {
        // Verificar si el empleado está capacitado para operar atracciones de riesgo medio
        assertTrue(empleadoAtraccionMedio.puedeOperarAtraccionRiesgoMedio());
        
        // Asignar una atracción de riesgo medio
        assertTrue(empleadoAtraccionMedio.asignarAtraccionMedia(atraccionMedio));
        
        // Verificar que la atracción fue asignada
        List<AtraccionMecanica> atraccionesAsignadas = empleadoAtraccionMedio.getAtraccionesAsignadas();
        assertEquals(1, atraccionesAsignadas.size());
        assertEquals(atraccionMedio, atraccionesAsignadas.get(0));
        
        // No debería poder asignar una atracción de riesgo alto como si fuera de riesgo medio
        assertFalse(empleadoAtraccionMedio.asignarAtraccionMedia(atraccionAlto));
        
        // Simular que la capacitación ha vencido
        Date fechaVencida = new Date();
        fechaVencida.setTime(fechaActual.getTime() - 1); // Ya venció
        empleadoAtraccionMedio.setFechaVencimientoCapacitacion(fechaVencida);
        
        // Ahora no debería poder operar atracciones
        assertFalse(empleadoAtraccionMedio.puedeOperarAtraccionRiesgoMedio());
    }
    
    @Test
    public void testEmpleadoCajero() {
        // Asignar un lugar de servicio al cajero
        empleadoCajero.asignarLugarServicio(taquilla);
        
        // Verificar que el lugar fue asignado
        assertEquals(taquilla, empleadoCajero.getLugarAsignado());
        
        // Probar la venta de un tiquete
        Cliente cliente = new Cliente("Cliente Prueba", 100, "cliente@ejemplo.com", "clave123");
        Date fechaCompra = new Date();
        TiqueteBasico tiquete = new TiqueteBasico(1, "Tiquete Básico", 1, "Familiar", fechaCompra, "Activo", "Taquilla", "Adulto", false);
        
        try {
            // El cajero debería poder vender el tiquete
            assertTrue(empleadoCajero.venderTiquete(cliente, tiquete));
            
            // Verificar que el cliente tiene el tiquete
            assertEquals(1, cliente.getTiquetes().size());
            assertEquals(tiquete, cliente.getTiquetes().get(0));
        } catch (TiqueteException e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    @Test
    public void testEmpleadoCocinero() {
        // Verificar si el cocinero está capacitado
        assertTrue(empleadoCocinero.isCapacitado());
        assertTrue(empleadoCocinero.comidaPreparada());
        
        // Los cocineros pueden trabajar como cajeros
        assertTrue(empleadoCocinero.puedeTrabajarCaja());
        
        // Asignar una cafetería al cocinero
        assertTrue(empleadoCocinero.asignarCocina(cafeteria));
        
        // Verificar que la cafetería fue asignada
        assertEquals(cafeteria, empleadoCocinero.getCafeteriaAsignada());
        
        // Un cocinero no capacitado no debería poder ser asignado
        empleadoCocinero.setCapacitado(false);
        assertFalse(empleadoCocinero.asignarCocina(cafeteria));
    }
    
    @Test
    public void testEmpleadoRegular() {
        // Verificar si el empleado puede ser cajero
        assertTrue(empleadoRegular.isPuedeSerCajero());
        
        // Asignar como cajero
        assertTrue(empleadoRegular.asignarCajero(tienda));
        
        // Verificar que el lugar fue asignado
        assertEquals(tienda, empleadoRegular.getLugarAsignado());
        
        // Verificar que ya no está en servicio general
        assertFalse(empleadoRegular.esServicioGeneral());
        
        // Asignar a servicio general
        assertTrue(empleadoRegular.asignarServicioGeneral());
        
        // Verificar que ahora está en servicio general y no tiene lugar asignado
        assertTrue(empleadoRegular.esServicioGeneral());
        assertNull(empleadoRegular.getLugarAsignado());
        
        // Un empleado que no puede ser cajero no debería poder ser asignado como tal
        empleadoRegular.setPuedeSerCajero(false);
        assertFalse(empleadoRegular.asignarCajero(tienda));
    }
    
    @Test
    public void testEmpleadoServicioGeneral() {
        // Verificar que está en servicio general
        assertTrue(empleadoServicioGeneral.esServicioGeneral());
        
        // Verificar las zonas asignadas
        List<String> zonas = empleadoServicioGeneral.getZonasAsignadas();
        assertEquals(2, zonas.size());
        assertTrue(zonas.contains("Zona Norte"));
        assertTrue(zonas.contains("Zona Central"));
        
        // Verificar si tiene una zona específica
        assertTrue(empleadoServicioGeneral.tieneZonaAsignada("Zona Norte"));
        assertFalse(empleadoServicioGeneral.tieneZonaAsignada("Zona Sur"));
        
        // Asignar una nueva zona
        assertTrue(empleadoServicioGeneral.asignarZona("Zona Sur"));
        
        // Verificar que la zona fue asignada
        assertTrue(empleadoServicioGeneral.tieneZonaAsignada("Zona Sur"));
        
        // Remover una zona
        assertTrue(empleadoServicioGeneral.removerZona("Zona Norte"));
        assertFalse(empleadoServicioGeneral.tieneZonaAsignada("Zona Norte"));
    }
}