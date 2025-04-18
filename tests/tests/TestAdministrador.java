package tests;



import static org.junit.Assert.*;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.ArrayList;
import org.junit.Before;
import org.junit.Test;
import excepciones.AtraccionException;
import excepciones.EmpleadoException;
import modelo.atracciones.Atraccion;
import modelo.atracciones.AtraccionCultural;
import modelo.atracciones.AtraccionMecanica;
import modelo.atracciones.Espectaculo;
import modelo.empleados.AtraccionAlto;
import modelo.empleados.AtraccionMedio;
import modelo.empleados.Cajero;
import modelo.empleados.Cocinero;
import modelo.empleados.Empleado;
import modelo.lugares.Cafeteria;
import modelo.lugares.LugarServicio;
import modelo.lugares.Taquilla;
import modelo.tiquetes.Tiquete;
import modelo.tiquetes.TiqueteBasico;
import modelo.usuarios.Administrador;
import modelo.util.Turno;

public class TestAdministrador {
    
    private Administrador admin;
    
    private AtraccionMecanica atraccionMecanica;
    private AtraccionCultural atraccionCultural;
    private Espectaculo espectaculo;
    
    private AtraccionAlto empleadoAtraccionAlto;
    private AtraccionMedio empleadoAtraccionMedio;
    private Cajero empleadoCajero;
    private Cocinero empleadoCocinero;
    
    private Cafeteria cafeteria;
    private Taquilla taquilla;
    
    private Date fechaActual;
    
    @Before
    public void setUp() {
        fechaActual = new Date();
        
        // Crear administrador
        admin = new Administrador("Admin Principal", 1000, "admin@parque.com", "admin123");
        
        // Crear atracciones
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
            120.0f,
            200.0f,
            40.0f,
            120.0f,
            "vértigo, problemas cardíacos",
            "alto"
        );
        
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
            12
        );
        
        espectaculo = new Espectaculo(
            "Show de Delfines",
            "No realizar en caso de lluvia",
            true,
            fechaActual,
            new Date(fechaActual.getTime() + 30L * 24 * 60 * 60 * 1000),
            "45 minutos",
            "15:00",
            100
        );
        
        // Crear empleados
        empleadoAtraccionAlto = new AtraccionAlto(
            "Operador de atracción alta",
            "Juan Pérez",
            1,
            false,
            "juan@ejemplo.com",
            "clave123",
            false,
            true
        );
        
        empleadoAtraccionMedio = new AtraccionMedio(
            new Date(fechaActual.getTime() - 30L * 24 * 60 * 60 * 1000),
            new Date(fechaActual.getTime() + 365L * 24 * 60 * 60 * 1000),
            "Operador de atracción media",
            "María López",
            2,
            false,
            "maria@ejemplo.com",
            "clave123",
            true
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
            true,
            "Cocinero",
            "Ana Martínez",
            4,
            false,
            "ana@ejemplo.com",
            "clave123",
            true
        );
        
        // Crear lugares
        List<String> menu = new ArrayList<>();
        menu.add("Hamburguesas");
        menu.add("Papas fritas");
        menu.add("Refrescos");
        cafeteria = new Cafeteria("CAF1", "Cafetería Central", "Centro del parque", menu, 50);
        
        taquilla = new Taquilla("TAQ1", "Taquilla Principal", "Entrada del parque", "Efectivo y tarjeta");
    }
    
    @Test
    public void testGestionAtracciones() {
        // Verificar que inicialmente no hay atracciones
        assertTrue(admin.getAtracciones().isEmpty());
        
        // Agregar atracciones
        try {
            admin.agregarAtraccion(atraccionMecanica);
            admin.agregarAtraccion(atraccionCultural);
            
            // Verificar que se agregaron
            List<Atraccion> atracciones = admin.getAtracciones();
            assertEquals(2, atracciones.size());
            assertTrue(atracciones.contains(atraccionMecanica));
            assertTrue(atracciones.contains(atraccionCultural));
            
            // Cambiar información de una atracción
            atraccionMecanica.setNivelExclusividad("Diamante");
            admin.cambiarInfoAtraccion(atraccionMecanica);
            
            // Verificar que se cambió
            assertEquals("Diamante", admin.getAtracciones().get(0).getNivelExclusividad());
            
            // Eliminar una atracción
            admin.eliminarAtraccion(atraccionCultural);
            
            // Verificar que se eliminó
            atracciones = admin.getAtracciones();
            assertEquals(1, atracciones.size());
            assertFalse(atracciones.contains(atraccionCultural));
            
        } catch (AtraccionException e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
        
        // Verificar que no se puede agregar una atracción nula
        try {
            admin.agregarAtraccion(null);
            fail("Debería lanzar excepción");
        } catch (AtraccionException e) {
            // Correcto, debe lanzar excepción
        }
    }
    
    @Test
    public void testGestionEmpleados() {
        // Verificar que inicialmente no hay empleados
        assertTrue(admin.getEmpleados().isEmpty());
        
        // Agregar empleados
        try {
            admin.agregarEmpleado(empleadoAtraccionAlto);
            admin.agregarEmpleado(empleadoAtraccionMedio);
            admin.agregarEmpleado(empleadoCajero);
            admin.agregarEmpleado(empleadoCocinero);
            
            // Verificar que se agregaron
            List<Empleado> empleados = admin.getEmpleados();
            assertEquals(4, empleados.size());
            assertTrue(empleados.contains(empleadoAtraccionAlto));
            assertTrue(empleados.contains(empleadoCocinero));
            
            // Cambiar información de un empleado
            empleadoCajero.setHorasExtras(true);
            admin.cambiarInfoEmpleado(empleadoCajero);
            
            // Verificar que se cambió
            for (Empleado empleado : admin.getEmpleados()) {
                if (empleado.equals(empleadoCajero)) {
                    assertTrue(empleado.isHorasExtras());
                }
            }
            
            // Eliminar un empleado
            admin.eliminarEmpleado(empleadoCocinero);
            
            // Verificar que se eliminó
            empleados = admin.getEmpleados();
            assertEquals(3, empleados.size());
            assertFalse(empleados.contains(empleadoCocinero));
            
        } catch (EmpleadoException e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
        
        // Verificar que no se puede agregar un empleado nulo
        try {
            admin.agregarEmpleado(null);
            fail("Debería lanzar excepción");
        } catch (EmpleadoException e) {
            // Correcto, debe lanzar excepción
        }
    }
    
    @Test
    public void testGestionEspectaculos() {
        // Verificar que inicialmente no hay espectáculos
        assertTrue(admin.getEspectaculos().isEmpty());
        
        // Agregar espectáculo
        try {
            admin.crearEspectaculo(espectaculo);
            
            // Verificar que se agregó
            List<Espectaculo> espectaculos = admin.getEspectaculos();
            assertEquals(1, espectaculos.size());
            assertEquals(espectaculo, espectaculos.get(0));
            
            // Modificar espectáculo
            espectaculo.setCapacidad(150);
            admin.modificarEspectaculo(espectaculo);
            
            // Verificar que se modificó
            assertEquals(150, admin.getEspectaculos().get(0).getCapacidad());
            
            // Eliminar espectáculo
            admin.eliminarEspectaculo(espectaculo);
            
            // Verificar que se eliminó
            assertTrue(admin.getEspectaculos().isEmpty());
            
        } catch (AtraccionException e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    @Test
    public void testAsignacionEmpleados() {
        // Agregar empleados y atracciones
        try {
            admin.agregarEmpleado(empleadoAtraccionAlto);
            admin.agregarEmpleado(empleadoAtraccionMedio);
            admin.agregarEmpleado(empleadoCajero);
            admin.agregarEmpleado(empleadoCocinero);
            
            admin.agregarAtraccion(atraccionMecanica);
            
            // Asignar empleado a atracción
            admin.asignarEmpleadoAtraccion(empleadoAtraccionAlto, atraccionMecanica, fechaActual, Turno.APERTURA);
            
            // Verificar que está asignado
            assertTrue(admin.estaEmpleadoAsignado(empleadoAtraccionAlto, fechaActual, Turno.APERTURA));
            assertEquals(atraccionMecanica, admin.obtenerLugarAsignado(empleadoAtraccionAlto, fechaActual, Turno.APERTURA));
            
            // Obtener todos los empleados asignados en ese turno
            List<Empleado> empleadosAsignados = admin.obtenerEmpleadosAsignadosTurno(fechaActual, Turno.APERTURA);
            assertEquals(1, empleadosAsignados.size());
            assertTrue(empleadosAsignados.contains(empleadoAtraccionAlto));
            
            // Asignar otro empleado
            admin.asignarEmpleadoAtraccion(empleadoAtraccionMedio, atraccionMecanica, fechaActual, Turno.APERTURA);
            
            // Verificar personal mínimo
            assertTrue(admin.verificarPersonalMinimo(atraccionMecanica, fechaActual, Turno.APERTURA));
            
            // Liberar un empleado
            admin.liberarAsignacion(empleadoAtraccionAlto, fechaActual, Turno.APERTURA);
            
            // Verificar que fue liberado
            assertFalse(admin.estaEmpleadoAsignado(empleadoAtraccionAlto, fechaActual, Turno.APERTURA));
            
            // Verificar que ya no hay personal mínimo
            assertFalse(admin.verificarPersonalMinimo(atraccionMecanica, fechaActual, Turno.APERTURA));
            
        } catch (EmpleadoException | AtraccionException e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    @Test
    public void testGestionCocinerosCajeros() {
        try {
            admin.agregarEmpleado(empleadoCocinero);
            admin.agregarEmpleado(empleadoCajero);
            
            // Asignar cocinero a cafetería
            admin.asignarCocineroACafeteria(empleadoCocinero, cafeteria, fechaActual, Turno.APERTURA);
            
            // Verificar asignación
            assertTrue(admin.estaEmpleadoAsignado(empleadoCocinero, fechaActual, Turno.APERTURA));
            assertEquals(cafeteria, admin.obtenerLugarAsignado(empleadoCocinero, fechaActual, Turno.APERTURA));
            
            // Asignar cajero a taquilla
            admin.asignarCajeroALugarServicio(empleadoCajero, taquilla, fechaActual, Turno.APERTURA);
            
            // Verificar asignación
            assertTrue(admin.estaEmpleadoAsignado(empleadoCajero, fechaActual, Turno.APERTURA));
            assertEquals(taquilla, admin.obtenerLugarAsignado(empleadoCajero, fechaActual, Turno.APERTURA));
            
        } catch (EmpleadoException e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    @Test
    public void testGestionServicioGeneral() {
        try {
            admin.agregarEmpleado(empleadoAtraccionAlto);
            
            // Asignar a servicio general
            String[] zonas = {"Zona Norte", "Zona Central"};
            admin.asignarEmpleadoServicioGeneral(empleadoAtraccionAlto, zonas, fechaActual, Turno.APERTURA);
            
            // Verificar asignación
            assertTrue(admin.estaEmpleadoAsignado(empleadoAtraccionAlto, fechaActual, Turno.APERTURA));
            Object asignacion = admin.obtenerLugarAsignado(empleadoAtraccionAlto, fechaActual, Turno.APERTURA);
            assertTrue(asignacion instanceof String[]);
            String[] zonasAsignadas = (String[]) asignacion;
            assertEquals(2, zonasAsignadas.length);
            
        } catch (EmpleadoException e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    @Test
    public void testGestionAtraccionesTemporada() {
        try {
            admin.agregarAtraccion(atraccionMecanica);
            
            // Por defecto no es de temporada
            assertFalse(atraccionMecanica.isDeTemporada());
            
            // Configurar como atracción de temporada
            Date fechaInicio = fechaActual;
            Date fechaFin = new Date(fechaActual.getTime() + 90L * 24 * 60 * 60 * 1000); // 90 días después
            
            admin.gestionarAtraccionesTemporada(atraccionMecanica, true, fechaInicio, fechaFin);
            
            // Verificar configuración
            assertTrue(atraccionMecanica.isDeTemporada());
            assertEquals(fechaInicio, atraccionMecanica.getFechaInicio());
            assertEquals(fechaFin, atraccionMecanica.getFechaFin());
            
            // Verificar disponibilidad
            assertTrue(admin.verificarDisponibilidadAtraccion(atraccionMecanica, fechaActual));
            assertTrue(admin.verificarDisponibilidadAtraccion(atraccionMecanica, new Date(fechaActual.getTime() + 30L * 24 * 60 * 60 * 1000)));
            assertFalse(admin.verificarDisponibilidadAtraccion(atraccionMecanica, new Date(fechaFin.getTime() + 24 * 60 * 60 * 1000)));
            
        } catch (AtraccionException e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    @Test
    public void testGestionMantenimiento() {
        try {
            admin.agregarAtraccion(atraccionMecanica);
            
            // Verificar disponibilidad inicial
            assertTrue(admin.verificarDisponibilidadAtraccion(atraccionMecanica, fechaActual));
            
            // Programar mantenimiento
            Date fechaInicio = fechaActual;
            Date fechaFin = new Date(fechaActual.getTime() + 7L * 24 * 60 * 60 * 1000); // 7 días después
            
            admin.gestionarMantenimientoAtracciones(atraccionMecanica, fechaInicio, fechaFin);
            
            // Verificar disponibilidad durante mantenimiento
            assertFalse(admin.verificarDisponibilidadAtraccion(atraccionMecanica, fechaActual));
            assertFalse(admin.verificarDisponibilidadAtraccion(atraccionMecanica, new Date(fechaActual.getTime() + 3L * 24 * 60 * 60 * 1000)));
            
            // Verificar disponibilidad después del mantenimiento
            assertTrue(admin.verificarDisponibilidadAtraccion(atraccionMecanica, new Date(fechaFin.getTime() + 24 * 60 * 60 * 1000)));
            
        } catch (AtraccionException e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    @Test
    public void testCambiarNivelExclusividad() {
        try {
            admin.agregarAtraccion(atraccionMecanica);
            
            // Nivel inicial
            assertEquals("Oro", atraccionMecanica.getNivelExclusividad());
            
            // Cambiar nivel de exclusividad
            admin.cambiarNivelExclusividadAtraccion(atraccionMecanica, "Diamante");
            
            // Verificar cambio
            assertEquals("Diamante", atraccionMecanica.getNivelExclusividad());
            
            // Intentar cambiar a un nivel inválido
            try {
                admin.cambiarNivelExclusividadAtraccion(atraccionMecanica, "SuperDiamante");
                fail("Debería lanzar excepción");
            } catch (AtraccionException e) {
                // Correcto, debe lanzar excepción
            }
            
        } catch (AtraccionException e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
    
    @Test
    public void testDescuentoEmpleado() {
        try {
            admin.agregarEmpleado(empleadoCajero);
            
            // Crear tiquete
            TiqueteBasico tiquete = new TiqueteBasico(1, "Tiquete Básico", 1, "Familiar", fechaActual, "Activo", "Taquilla", "Adulto", false);
            
            // Verificar que inicialmente no tiene descuento
            assertFalse(tiquete.isDctoEmpleado());
            
            // Aplicar descuento para empleado
            admin.gestionarDescuentoEmpleado(tiquete, empleadoCajero);
            
            // Verificar que ahora tiene descuento
            assertTrue(tiquete.isDctoEmpleado());
            
        } catch (EmpleadoException e) {
            fail("No debería lanzar excepción: " + e.getMessage());
        }
    }
}
