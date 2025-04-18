package tests;

import static org.junit.Assert.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import org.junit.Before;
import org.junit.Test;
import modelo.empleados.Cajero;
import modelo.empleados.Cocinero;
import modelo.empleados.Empleado;
import modelo.lugares.Cafeteria;
import modelo.lugares.LugarServicio;
import modelo.lugares.LugarTrabajo;
import modelo.lugares.Taquilla;
import modelo.lugares.Tienda;
import modelo.tiquetes.TiqueteBasico;
import modelo.usuarios.Cliente;
import excepciones.TiqueteException;

public class TestLugarTrabajo {
    
    private LugarTrabajo lugarTrabajo;
    private Cafeteria cafeteria;
    private Taquilla taquilla;
    private Tienda tienda;
    
    private Cajero cajero;
    private Cocinero cocinero;
    
    private Date fechaActual;
    private String turnoApertura;
    private String turnoCierre;
    
    @Before
    public void setUp() {
        // Fechas y turnos
        fechaActual = new Date();
        turnoApertura = "Apertura";
        turnoCierre = "Cierre";
        
        // Crear lugares
        lugarTrabajo = new LugarTrabajo("LUG1", "Lugar de Trabajo Genérico", "Centro del parque");
        
        List<String> menu = new ArrayList<>();
        menu.add("Hamburguesas");
        menu.add("Papas fritas");
        menu.add("Refrescos");
        cafeteria = new Cafeteria("CAF1", "Cafetería Central", "Centro del parque", menu, 50);
        
        taquilla = new Taquilla("TAQ1", "Taquilla Principal", "Entrada del parque", "Efectivo y tarjeta");
        
        Map<String, Integer> inventario = new HashMap<>();
        inventario.put("Camisetas", 50);
        inventario.put("Llaveros", 100);
        inventario.put("Muñecos", 30);
        tienda = new Tienda("TIE1", "Tienda de Recuerdos", "Salida del parque", "Souvenirs", inventario);
        
        // Crear empleados
        cajero = new Cajero(
            "Cajero",
            "Pedro Gómez",
            3,
            false,
            "pedro@ejemplo.com",
            "clave123",
            false
        );
        
        cocinero = new Cocinero(
            true,  // capacitado
            "Cocinero",
            "Ana Martínez",
            4,
            false,
            "ana@ejemplo.com",
            "clave123",
            true  // horas extras
        );
    }
    
    @Test
    public void testLugarTrabajoBasico() {
        // Verificar propiedades básicas
        assertEquals("LUG1", lugarTrabajo.getId());
        assertEquals("Lugar de Trabajo Genérico", lugarTrabajo.getNombre());
        assertEquals("Centro del parque", lugarTrabajo.getUbicacion());
        
        // Verificar que inicialmente no hay empleados asignados
        List<Empleado> empleados = lugarTrabajo.getEmpleadosAsignados(fechaActual, turnoApertura);
        assertTrue(empleados.isEmpty());
        
        // Asignar un empleado
        assertTrue(lugarTrabajo.asignarEmpleado(cajero, fechaActual, turnoApertura));
        
        // Verificar que el empleado fue asignado
        empleados = lugarTrabajo.getEmpleadosAsignados(fechaActual, turnoApertura);
        assertEquals(1, empleados.size());
        assertEquals(cajero, empleados.get(0));
        
        // Verificar que el empleado no está asignado en otro turno
        empleados = lugarTrabajo.getEmpleadosAsignados(fechaActual, turnoCierre);
        assertTrue(empleados.isEmpty());
        
        // Por defecto, los lugares de trabajo no requieren capacitación
        assertFalse(lugarTrabajo.requiereCapacitacion(cajero));
    }
    
    @Test
    public void testCafeteria() {
        // Verificar propiedades específicas de cafetería
        assertEquals(50, cafeteria.getCapacidad());
        assertEquals(3, cafeteria.getMenu().size());
        assertTrue(cafeteria.getMenu().contains("Hamburguesas"));
        
        // Las cafeterías requieren un cajero y un cocinero
        assertFalse(cafeteria.verificarPersonalMinimo(fechaActual, turnoApertura));
        
        // Asignar empleados
        assertTrue(cafeteria.asignarCajero(cajero, fechaActual, turnoApertura));
        assertTrue(cafeteria.asignarCocinero(cocinero, fechaActual, turnoApertura));
        
        // Verificar que ahora tiene el personal mínimo
        assertTrue(cafeteria.verificarPersonalMinimo(fechaActual, turnoApertura));
        
        // Agregar y quitar platos del menú
        assertTrue(cafeteria.agregarPlato("Ensalada"));
        assertEquals(4, cafeteria.getMenu().size());
        
        assertTrue(cafeteria.removerPlato("Hamburguesas"));
        assertEquals(3, cafeteria.getMenu().size());
        assertFalse(cafeteria.getMenu().contains("Hamburguesas"));
    }
    
    @Test
    public void testTaquilla() {
        // Verificar propiedades específicas de taquilla
        assertEquals("Efectivo y tarjeta", taquilla.getMetodoPago());
        assertTrue(taquilla.getTiquetesDisponibles().isEmpty());
        
        // Las taquillas solo requieren un cajero
        assertFalse(taquilla.verificarPersonalMinimo(fechaActual, turnoApertura));
        
        assertTrue(taquilla.asignarCajero(cajero, fechaActual, turnoApertura));
        assertTrue(taquilla.verificarPersonalMinimo(fechaActual, turnoApertura));
        
        // Agregar tiquetes disponibles
        TiqueteBasico tiquete = new TiqueteBasico(1, "Tiquete Básico", 1, "Familiar", fechaActual, "Activo", "Taquilla", "Adulto", false);
        assertTrue(taquilla.agregarTiquete(tiquete));
        assertEquals(1, taquilla.getTiquetesDisponibles().size());
        
        // Vender un tiquete
        Cliente cliente = new Cliente("Cliente Prueba", 100, "cliente@ejemplo.com", "clave123");
        
        try {
            TiqueteBasico tiqueteVendido = (TiqueteBasico) taquilla.venderTiquete("Tiquete Básico", cliente);
            assertNotNull(tiqueteVendido);
            assertEquals(tiquete, tiqueteVendido);
            
            // El tiquete ya no debe estar disponible en la taquilla
            assertTrue(taquilla.getTiquetesDisponibles().isEmpty());
            
            // El cliente debe tener el tiquete
            assertEquals(1, cliente.getTiquetes().size());
            assertEquals(tiquete, cliente.getTiquetes().get(0));
        } catch (TiqueteException e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    @Test
    public void testTienda() {
        // Verificar propiedades específicas de tienda
        assertEquals("Souvenirs", tienda.getTipoProductos());
        assertEquals(3, tienda.getInventario().size());
        
        // Verificar disponibilidad de productos
        assertTrue(tienda.verificarDisponibilidadProducto("Camisetas"));
        assertFalse(tienda.verificarDisponibilidadProducto("Sombreros"));
        
        // Agregar y remover productos
        assertTrue(tienda.agregarProducto("Sombreros", 20));
        assertTrue(tienda.verificarDisponibilidadProducto("Sombreros"));
        assertEquals(4, tienda.getInventario().size());
        assertEquals(Integer.valueOf(20), tienda.getInventario().get("Sombreros"));
        
        // Agregar más unidades a un producto existente
        assertTrue(tienda.agregarProducto("Camisetas", 30));
        assertEquals(Integer.valueOf(80), tienda.getInventario().get("Camisetas")); // 50 + 30
        
        // Remover algunas unidades
        assertTrue(tienda.removerProducto("Camisetas", 20));
        assertEquals(Integer.valueOf(60), tienda.getInventario().get("Camisetas")); // 80 - 20
        
        // Remover todas las unidades de un producto
        assertTrue(tienda.removerProducto("Sombreros", 20));
        assertFalse(tienda.getInventario().containsKey("Sombreros"));
        
        // No se puede remover más unidades de las que hay
        assertFalse(tienda.removerProducto("Camisetas", 100));
        assertEquals(Integer.valueOf(60), tienda.getInventario().get("Camisetas")); // Sin cambios
    }
    
    @Test
    public void testLugarServicio() {
        // Verificar propiedades del lugar de servicio
        assertEquals("Cafeteria", cafeteria.getTipoServicio());
        assertTrue(cafeteria.isRequiereCocinero());
        
        assertEquals("Tienda", tienda.getTipoServicio());
        assertFalse(tienda.isRequiereCocinero());
        
        // Verificar asignación de cajeros y cocineros
        assertFalse(cafeteria.tieneCajeroAsignado(fechaActual, turnoApertura));
        assertFalse(cafeteria.tieneCocineroAsignado(fechaActual, turnoApertura));
        
        assertTrue(cafeteria.asignarCajero(cajero, fechaActual, turnoApertura));
        assertTrue(cafeteria.tieneCajeroAsignado(fechaActual, turnoApertura));
        
        assertTrue(cafeteria.asignarCocinero(cocinero, fechaActual, turnoApertura));
        assertTrue(cafeteria.tieneCocineroAsignado(fechaActual, turnoApertura));
        
        // La tienda no requiere cocinero, así que siempre tiene "cocinero asignado"
        assertTrue(tienda.tieneCocineroAsignado(fechaActual, turnoApertura));
        
        // La tienda no debería permitir asignar un cocinero
        assertFalse(tienda.asignarCocinero(cocinero, fechaActual, turnoApertura));
    }
}
