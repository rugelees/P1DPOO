package modelo.usuarios;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import excepciones.AtraccionException;
import excepciones.EmpleadoException;
import modelo.atracciones.Atraccion;
import modelo.atracciones.AtraccionCultural;
import modelo.atracciones.AtraccionMecanica;
import modelo.atracciones.Espectaculo;
import modelo.empleados.Cajero;
import modelo.empleados.Cocinero;
import modelo.empleados.Empleado;
import modelo.lugares.Cafeteria;
import modelo.lugares.LugarServicio;
import modelo.tiquetes.Tiquete;
import modelo.util.Turno;

/**
 * Clase que representa al administrador del parque
 */
public class Administrador extends Usuario {
    private static final long serialVersionUID = 1L;
    
    private String nombre;
    private int id;
    private List<Atraccion> atracciones;
    private List<Empleado> empleados;
    private List<Espectaculo> espectaculos;
    private Map<Date, Map<String, Map<Empleado, Object>>> asignacionesEmpleados;
    
    /**
     * Constructor del Administrador
     * 
     * @param nombre Nombre del administrador
     * @param id Identificador único del administrador
     * @param email Correo electrónico del administrador
     * @param password Contraseña del administrador
     */
    public Administrador(String nombre, int id, String email, String password) {
        super(email, password);
        this.nombre = nombre;
        this.id = id;
        this.atracciones = new ArrayList<>();
        this.empleados = new ArrayList<>();
        this.espectaculos = new ArrayList<>();
        this.asignacionesEmpleados = new HashMap<>();
    }
    
    /**
     * Cambia la información de una atracción
     * 
     * @param atraccion La atracción a modificar
     * @throws AtraccionException Si hay un error al modificar la atracción
     */
    public void cambiarInfoAtraccion(Atraccion atraccion) throws AtraccionException {
        if (atraccion == null) {
            throw new AtraccionException("La atracción no puede ser nula");
        }
        
        // Buscar si la atracción ya existe
        for (int i = 0; i < atracciones.size(); i++) {
            if (atracciones.get(i).getNombre().equals(atraccion.getNombre())) {
                atracciones.set(i, atraccion);
                return;
            }
        }
        
        throw new AtraccionException("La atracción no existe en el sistema");
    }
    
    /**
     * Cambia la información de un empleado
     * 
     * @param empleado El empleado a modificar
     * @throws EmpleadoException Si hay un error al modificar el empleado
     */
    public void cambiarInfoEmpleado(Empleado empleado) throws EmpleadoException {
        if (empleado == null) {
            throw new EmpleadoException("El empleado no puede ser nulo");
        }
        
        // Buscar si el empleado ya existe
        for (int i = 0; i < empleados.size(); i++) {
            if (empleados.get(i).getId() == empleado.getId()) {
                empleados.set(i, empleado);
                return;
            }
        }
        
        throw new EmpleadoException("El empleado no existe en el sistema");
    }
    
    /**
     * Agrega una atracción al sistema
     * 
     * @param atraccion La atracción a agregar
     * @throws AtraccionException Si hay un error al agregar la atracción
     */
    public void agregarAtraccion(Atraccion atraccion) throws AtraccionException {
        if (atraccion == null) {
            throw new AtraccionException("La atracción no puede ser nula");
        }
        
        // Verificar que no exista otra atracción con el mismo nombre
        for (Atraccion a : atracciones) {
            if (a.getNombre().equals(atraccion.getNombre())) {
                throw new AtraccionException("Ya existe una atracción con ese nombre");
            }
        }
        
        atracciones.add(atraccion);
    }
    
    /**
     * Elimina una atracción del sistema
     * 
     * @param atraccion La atracción a eliminar
     * @throws AtraccionException Si hay un error al eliminar la atracción
     */
    public void eliminarAtraccion(Atraccion atraccion) throws AtraccionException {
        if (atraccion == null) {
            throw new AtraccionException("La atracción no puede ser nula");
        }
        
        if (!atracciones.remove(atraccion)) {
            throw new AtraccionException("La atracción no existe en el sistema");
        }
    }
    
    /**
     * Agrega un empleado al sistema
     * 
     * @param empleado El empleado a agregar
     * @throws EmpleadoException Si hay un error al agregar el empleado
     */
    public void agregarEmpleado(Empleado empleado) throws EmpleadoException {
        if (empleado == null) {
            throw new EmpleadoException("El empleado no puede ser nulo");
        }
        
        // Verificar que no exista otro empleado con el mismo ID
        for (Empleado e : empleados) {
            if (e.getId() == empleado.getId()) {
                throw new EmpleadoException("Ya existe un empleado con ese ID");
            }
        }
        
        empleados.add(empleado);
    }
    
    /**
     * Elimina un empleado del sistema
     * 
     * @param empleado El empleado a eliminar
     * @throws EmpleadoException Si hay un error al eliminar el empleado
     */
    public void eliminarEmpleado(Empleado empleado) throws EmpleadoException {
        if (empleado == null) {
            throw new EmpleadoException("El empleado no puede ser nulo");
        }
        
        if (!empleados.remove(empleado)) {
            throw new EmpleadoException("El empleado no existe en el sistema");
        }
    }
    
    /**
     * Asigna un empleado a una atracción mecánica
     * 
     * @param empleado El empleado a asignar
     * @param atraccion La atracción a la que se asignará
     * @throws EmpleadoException Si hay un error al asignar el empleado
     */
    public void asignarEmpleadoAtraccion(Empleado empleado, AtraccionMecanica atraccion, Date fecha, String turno) 
            throws EmpleadoException {
        if (empleado == null || atraccion == null || fecha == null || turno == null) {
            throw new EmpleadoException("Los parámetros no pueden ser nulos");
        }
        
        if (!Turno.esValido(turno)) {
            throw new EmpleadoException("El turno no es válido");
        }
        
        // Verificar que el empleado esté en la lista de empleados
        if (!empleados.contains(empleado)) {
            throw new EmpleadoException("El empleado no existe en el sistema");
        }
        
        // Verificar que la atracción esté en la lista de atracciones
        if (!atracciones.contains(atraccion)) {
            throw new EmpleadoException("La atracción no existe en el sistema");
        }
        
        // Crear la estructura de asignaciones si no existe
        if (!asignacionesEmpleados.containsKey(fecha)) {
            asignacionesEmpleados.put(fecha, new HashMap<>());
        }
        
        Map<String, Map<Empleado, Object>> asignacionesFecha = asignacionesEmpleados.get(fecha);
        
        if (!asignacionesFecha.containsKey(turno)) {
            asignacionesFecha.put(turno, new HashMap<>());
        }
        
        Map<Empleado, Object> asignacionesTurno = asignacionesFecha.get(turno);
        
        // Verificar si el empleado ya está asignado en ese turno
        if (asignacionesTurno.containsKey(empleado)) {
            throw new EmpleadoException("El empleado ya está asignado en ese turno");
        }
        
        // Asignar el empleado a la atracción
        asignacionesTurno.put(empleado, atraccion);
    }
    
    /**
     * Asigna un cocinero a una cafetería
     * 
     * @param cocinero El cocinero a asignar
     * @param cafeteria La cafetería a la que se asignará
     * @param fecha La fecha de la asignación
     * @param turno El turno de la asignación
     * @throws EmpleadoException Si hay un error al asignar el cocinero
     */
    public void asignarCocineroACafeteria(Cocinero cocinero, Cafeteria cafeteria, Date fecha, String turno) 
            throws EmpleadoException {
        if (cocinero == null || cafeteria == null || fecha == null || turno == null) {
            throw new EmpleadoException("Los parámetros no pueden ser nulos");
        }
        
        if (!Turno.esValido(turno)) {
            throw new EmpleadoException("El turno no es válido");
        }
        
        // Verificar que el cocinero esté en la lista de empleados
        if (!empleados.contains(cocinero)) {
            throw new EmpleadoException("El cocinero no existe en el sistema");
        }
        
        // Verificar que el cocinero esté capacitado
        if (!cocinero.isCapacitado()) {
            throw new EmpleadoException("El cocinero no está capacitado");
        }
        
        // Crear la estructura de asignaciones si no existe
        if (!asignacionesEmpleados.containsKey(fecha)) {
            asignacionesEmpleados.put(fecha, new HashMap<>());
        }
        
        Map<String, Map<Empleado, Object>> asignacionesFecha = asignacionesEmpleados.get(fecha);
        
        if (!asignacionesFecha.containsKey(turno)) {
            asignacionesFecha.put(turno, new HashMap<>());
        }
        
        Map<Empleado, Object> asignacionesTurno = asignacionesFecha.get(turno);
        
        // Verificar si el cocinero ya está asignado en ese turno
        if (asignacionesTurno.containsKey(cocinero)) {
            throw new EmpleadoException("El cocinero ya está asignado en ese turno");
        }
        
        // Asignar el cocinero a la cafetería
        asignacionesTurno.put(cocinero, cafeteria);
    }
    
    /**
     * Asigna un cajero a un lugar de servicio
     * 
     * @param cajero El cajero a asignar
     * @param lugarServicio El lugar de servicio al que se asignará
     * @param fecha La fecha de la asignación
     * @param turno El turno de la asignación
     * @throws EmpleadoException Si hay un error al asignar el cajero
     */
    public void asignarCajeroALugarServicio(Cajero cajero, LugarServicio lugarServicio, Date fecha, String turno) 
            throws EmpleadoException {
        if (cajero == null || lugarServicio == null || fecha == null || turno == null) {
            throw new EmpleadoException("Los parámetros no pueden ser nulos");
        }
        
        if (!Turno.esValido(turno)) {
            throw new EmpleadoException("El turno no es válido");
        }
        
        // Verificar que el cajero esté en la lista de empleados
        if (!empleados.contains(cajero)) {
            throw new EmpleadoException("El cajero no existe en el sistema");
        }
        
        // Crear la estructura de asignaciones si no existe
        if (!asignacionesEmpleados.containsKey(fecha)) {
            asignacionesEmpleados.put(fecha, new HashMap<>());
        }
        
        Map<String, Map<Empleado, Object>> asignacionesFecha = asignacionesEmpleados.get(fecha);
        
        if (!asignacionesFecha.containsKey(turno)) {
            asignacionesFecha.put(turno, new HashMap<>());
        }
        
        Map<Empleado, Object> asignacionesTurno = asignacionesFecha.get(turno);
        
        // Verificar si el cajero ya está asignado en ese turno
        if (asignacionesTurno.containsKey(cajero)) {
            throw new EmpleadoException("El cajero ya está asignado en ese turno");
        }
        
        // Asignar el cajero al lugar de servicio
        asignacionesTurno.put(cajero, lugarServicio);
    }
    
    /**
     * Asigna un empleado al servicio general
     * 
     * @param empleado El empleado a asignar
     * @param zonas Las zonas asignadas al empleado
     * @param fecha La fecha de la asignación
     * @param turno El turno de la asignación
     * @throws EmpleadoException Si hay un error al asignar el empleado
     */
    public void asignarEmpleadoServicioGeneral(Empleado empleado, String[] zonas, Date fecha, String turno) 
            throws EmpleadoException {
        if (empleado == null || zonas == null || zonas.length == 0 || fecha == null || turno == null) {
            throw new EmpleadoException("Los parámetros no pueden ser nulos o vacíos");
        }
        
        if (!Turno.esValido(turno)) {
            throw new EmpleadoException("El turno no es válido");
        }
        
        // Verificar que el empleado esté en la lista de empleados
        if (!empleados.contains(empleado)) {
            throw new EmpleadoException("El empleado no existe en el sistema");
        }
        
        // Crear la estructura de asignaciones si no existe
        if (!asignacionesEmpleados.containsKey(fecha)) {
            asignacionesEmpleados.put(fecha, new HashMap<>());
        }
        
        Map<String, Map<Empleado, Object>> asignacionesFecha = asignacionesEmpleados.get(fecha);
        
        if (!asignacionesFecha.containsKey(turno)) {
            asignacionesFecha.put(turno, new HashMap<>());
        }
        
        Map<Empleado, Object> asignacionesTurno = asignacionesFecha.get(turno);
        
        // Verificar si el empleado ya está asignado en ese turno
        if (asignacionesTurno.containsKey(empleado)) {
            throw new EmpleadoException("El empleado ya está asignado en ese turno");
        }
        
        // Asignar el empleado al servicio general
        asignacionesTurno.put(empleado, zonas);
    }
    
    /**
     * Verifica si una atracción tiene el personal mínimo necesario
     * 
     * @param atraccion La atracción a verificar
     * @param fecha La fecha a verificar
     * @param turno El turno a verificar
     * @return true si la atracción tiene el personal mínimo, false de lo contrario
     */
    public boolean verificarPersonalMinimo(Atraccion atraccion, Date fecha, String turno) {
        if (atraccion == null || fecha == null || !Turno.esValido(turno)) {
            return false;
        }
        
        // Si no hay asignaciones para esa fecha o ese turno, no hay personal suficiente
        if (!asignacionesEmpleados.containsKey(fecha)) {
            return false;
        }
        
        Map<String, Map<Empleado, Object>> asignacionesFecha = asignacionesEmpleados.get(fecha);
        
        if (!asignacionesFecha.containsKey(turno)) {
            return false;
        }
        
        Map<Empleado, Object> asignacionesTurno = asignacionesFecha.get(turno);
        
        // Contar cuántos empleados están asignados a esta atracción
        int empleadosAsignados = 0;
        for (Map.Entry<Empleado, Object> entry : asignacionesTurno.entrySet()) {
            if (entry.getValue() == atraccion) {
                empleadosAsignados++;
            }
        }
        
        // Verificar si el número de empleados asignados es suficiente
        return empleadosAsignados >= atraccion.getEmpleadosEncargados();
    }
    
    /**
     * Programa el mantenimiento de una atracción mecánica
     * 
     * @param atraccion La atracción a la que se le programará mantenimiento
     * @param fechaInicio La fecha de inicio del mantenimiento
     * @param fechaFin La fecha de fin del mantenimiento
     * @throws AtraccionException Si hay un error al programar el mantenimiento
     */
    public void gestionarMantenimientoAtracciones(AtraccionMecanica atraccion, Date fechaInicio, Date fechaFin) 
            throws AtraccionException {
        if (atraccion == null || fechaInicio == null || fechaFin == null) {
            throw new AtraccionException("La atracción y las fechas no pueden ser nulas");
        }
        
        if (fechaInicio.after(fechaFin)) {
            throw new AtraccionException("La fecha de inicio debe ser anterior a la fecha de fin");
        }
        
        // Verificar que la atracción esté en la lista de atracciones
        if (!atracciones.contains(atraccion)) {
            throw new AtraccionException("La atracción no existe en el sistema");
        }
        
        // Programar el mantenimiento de la atracción
        atraccion.programarMantenimiento(fechaInicio, fechaFin);
    }
    
    /**
     * Crea un nuevo espectáculo en el sistema
     * 
     * @param espectaculo El espectáculo a crear
     * @throws AtraccionException Si hay un error al crear el espectáculo
     */
    public void crearEspectaculo(Espectaculo espectaculo) throws AtraccionException {
        if (espectaculo == null) {
            throw new AtraccionException("El espectáculo no puede ser nulo");
        }
        
        // Verificar que no exista otro espectáculo con el mismo nombre
        for (Espectaculo e : espectaculos) {
            if (e.getNombre().equals(espectaculo.getNombre())) {
                throw new AtraccionException("Ya existe un espectáculo con ese nombre");
            }
        }
        
        espectaculos.add(espectaculo);
    }
    
    /**
     * Modifica un espectáculo existente
     * 
     * @param espectaculo El espectáculo modificado
     * @throws AtraccionException Si hay un error al modificar el espectáculo
     */
    public void modificarEspectaculo(Espectaculo espectaculo) throws AtraccionException {
        if (espectaculo == null) {
            throw new AtraccionException("El espectáculo no puede ser nulo");
        }
        
        // Buscar si el espectáculo ya existe
        for (int i = 0; i < espectaculos.size(); i++) {
            if (espectaculos.get(i).getNombre().equals(espectaculo.getNombre())) {
                espectaculos.set(i, espectaculo);
                return;
            }
        }
        
        throw new AtraccionException("El espectáculo no existe en el sistema");
    }
    
    /**
     * Elimina un espectáculo del sistema
     * 
     * @param espectaculo El espectáculo a eliminar
     * @throws AtraccionException Si hay un error al eliminar el espectáculo
     */
    public void eliminarEspectaculo(Espectaculo espectaculo) throws AtraccionException {
        if (espectaculo == null) {
            throw new AtraccionException("El espectáculo no puede ser nulo");
        }
        
        if (!espectaculos.remove(espectaculo)) {
            throw new AtraccionException("El espectáculo no existe en el sistema");
        }
    }
    
    /**
     * Gestiona si una atracción es de temporada
     * 
     * @param atraccion La atracción a modificar
     * @param deTemporada Indica si la atracción es de temporada
     * @param fechaInicio La fecha de inicio de la temporada
     * @param fechaFin La fecha de fin de la temporada
     * @throws AtraccionException Si hay un error al gestionar la atracción
     */
    public void gestionarAtraccionesTemporada(Atraccion atraccion, boolean deTemporada, Date fechaInicio, Date fechaFin) 
            throws AtraccionException {
        if (atraccion == null) {
            throw new AtraccionException("La atracción no puede ser nula");
        }
        
        if (deTemporada && (fechaInicio == null || fechaFin == null)) {
            throw new AtraccionException("Si la atracción es de temporada, las fechas no pueden ser nulas");
        }
        
        if (deTemporada && fechaInicio.after(fechaFin)) {
            throw new AtraccionException("La fecha de inicio debe ser anterior a la fecha de fin");
        }
        
        // Verificar que la atracción esté en la lista de atracciones
        if (!atracciones.contains(atraccion)) {
            throw new AtraccionException("La atracción no existe en el sistema");
        }
        
        // Establecer si la atracción es de temporada
        atraccion.setDeTemporada(deTemporada);
        
        if (deTemporada) {
            atraccion.setFechaInicio(fechaInicio);
            atraccion.setFechaFin(fechaFin);
        }
    }
    
    /**
     * Consulta estadísticas de ventas por tipo de tiquete
     * 
     * @return Un mapa con las estadísticas de ventas
     */
    public Map<String, Integer> consultarEstadisticasVentas() {
        // Este método debería obtener datos del sistema de ventas
        Map<String, Integer> estadisticas = new HashMap<>();
        
        // Contar los tipos de tiquetes vendidos
        // En una implementación real, esto se obtendría de la base de datos
        
        return estadisticas;
    }
    
    /**
     * Genera un reporte de ocupación de atracciones
     * 
     * @return Un mapa con la ocupación de cada atracción
     */
    public Map<Atraccion, Double> generarReporteOcupacionAtracciones() {
        Map<Atraccion, Double> reporte = new HashMap<>();
        
        // Calcular la ocupación para cada atracción
        for (Atraccion atraccion : atracciones) {
            // En una implementación real, se calcularía la ocupación basada en datos históricos
            double ocupacion = 0.0; // Ocupación como porcentaje (0-100%)
            reporte.put(atraccion, ocupacion);
        }
        
        return reporte;
    }
    
    /**
     * Cambia el nivel de exclusividad de una atracción
     * 
     * @param atraccion La atracción a modificar
     * @param nivelExclusividad El nuevo nivel de exclusividad
     * @throws AtraccionException Si hay un error al cambiar el nivel de exclusividad
     */
    public void cambiarNivelExclusividadAtraccion(Atraccion atraccion, String nivelExclusividad) 
            throws AtraccionException {
        if (atraccion == null || nivelExclusividad == null || nivelExclusividad.isEmpty()) {
            throw new AtraccionException("La atracción y el nivel de exclusividad no pueden ser nulos o vacíos");
        }
        
        // Verificar que la atracción esté en la lista de atracciones
        if (!atracciones.contains(atraccion)) {
            throw new AtraccionException("La atracción no existe en el sistema");
        }
        
        // Verificar que el nivel de exclusividad sea válido
        if (!modelo.util.NivelExclusividad.esValido(nivelExclusividad)) {
            throw new AtraccionException("El nivel de exclusividad no es válido");
        }
        
        // Cambiar el nivel de exclusividad
        atraccion.setNivelExclusividad(nivelExclusividad);
    }
    
    /**
     * Verifica si una atracción está disponible en una fecha específica
     * 
     * @param atraccion La atracción a verificar
     * @param fecha La fecha a verificar
     * @return true si la atracción está disponible, false de lo contrario
     */
    public boolean verificarDisponibilidadAtraccion(Atraccion atraccion, Date fecha) {
        if (atraccion == null || fecha == null) {
            return false;
        }
        
        // Verificar que la atracción esté en la lista de atracciones
        if (!atracciones.contains(atraccion)) {
            return false;
        }
        
        // Verificar si la atracción está disponible en esa fecha
        return atraccion.estaDisponible(fecha);
    }
    
    /**
     * Consulta los empleados capacitados para un tipo de capacitación
     * 
     * @param tipoCapacitacion El tipo de capacitación a consultar
     * @return Una lista con los empleados capacitados
     */
    public List<Empleado> consultarEmpleadosCapacitados(String tipoCapacitacion) {
        List<Empleado> empleadosCapacitados = new ArrayList<>();
        
        // Buscar empleados capacitados según el tipo de capacitación
        for (Empleado empleado : empleados) {
            // En una implementación real, se verificaría si el empleado tiene la capacitación específica
            // Por ahora, sólo retornamos una lista vacía
        }
        
        return empleadosCapacitados;
    }
    
    /**
     * Asigna un turno a un empleado
     * 
     * @param empleado El empleado al que se le asignará el turno
     * @param fecha La fecha del turno
     * @param turno El turno a asignar
     * @throws EmpleadoException Si hay un error al asignar el turno
     */
    public void asignarTurno(Empleado empleado, Date fecha, String turno) throws EmpleadoException {
        if (empleado == null || fecha == null || turno == null) {
            throw new EmpleadoException("El empleado, la fecha y el turno no pueden ser nulos");
        }
        
        if (!Turno.esValido(turno)) {
            throw new EmpleadoException("El turno no es válido");
        }
        
        // Verificar que el empleado esté en la lista de empleados
        if (!empleados.contains(empleado)) {
            throw new EmpleadoException("El empleado no existe en el sistema");
        }
        
        // Crear la estructura de asignaciones si no existe
        if (!asignacionesEmpleados.containsKey(fecha)) {
            asignacionesEmpleados.put(fecha, new HashMap<>());
        }
        
        Map<String, Map<Empleado, Object>> asignacionesFecha = asignacionesEmpleados.get(fecha);
        
        if (!asignacionesFecha.containsKey(turno)) {
            asignacionesFecha.put(turno, new HashMap<>());
        }
        
        // No asignamos ningún lugar de trabajo específico aquí,
        // solo marcamos que el empleado está disponible para ese turno
    }
    
    /**
     * Consulta el calendario de atracciones
     * 
     * @return Un mapa con las fechas disponibles para cada atracción
     */
    public Map<Atraccion, List<Date>> consultarCalendarioAtracciones() {
        Map<Atraccion, List<Date>> calendario = new HashMap<>();
        
        // Crear el calendario para cada atracción
        for (Atraccion atraccion : atracciones) {
            List<Date> fechasDisponibles = new ArrayList<>();
            // En una implementación real, se calcularían las fechas disponibles
            calendario.put(atraccion, fechasDisponibles);
        }
        
        return calendario;
    }
    
    /**
     * Obtiene la lista de todos los empleados asignados a un turno específico
     * 
     * @param fecha La fecha del turno
     * @param turno El turno a consultar
     * @return Lista de empleados asignados
     */
    public List<Empleado> obtenerEmpleadosAsignadosTurno(Date fecha, String turno) {
        List<Empleado> empleadosAsignados = new ArrayList<>();
        
        if (fecha == null || turno == null || !Turno.esValido(turno)) {
            return empleadosAsignados;
        }
        
        // Verificar si hay asignaciones para esa fecha y turno
        if (!asignacionesEmpleados.containsKey(fecha)) {
            return empleadosAsignados;
        }
        
        Map<String, Map<Empleado, Object>> asignacionesFecha = asignacionesEmpleados.get(fecha);
        
        if (!asignacionesFecha.containsKey(turno)) {
            return empleadosAsignados;
        }
        
        // Obtener todos los empleados asignados a ese turno
        Map<Empleado, Object> asignacionesTurno = asignacionesFecha.get(turno);
        empleadosAsignados.addAll(asignacionesTurno.keySet());
        
        return empleadosAsignados;
    }
    
    /**
     * Verifica si un empleado está asignado a un turno específico
     * 
     * @param empleado El empleado a verificar
     * @param fecha La fecha del turno
     * @param turno El turno a verificar
     * @return true si el empleado está asignado, false de lo contrario
     */
    public boolean estaEmpleadoAsignado(Empleado empleado, Date fecha, String turno) {
        if (empleado == null || fecha == null || turno == null || !Turno.esValido(turno)) {
            return false;
        }
        
        // Verificar si hay asignaciones para esa fecha y turno
        if (!asignacionesEmpleados.containsKey(fecha)) {
            return false;
        }
        
        Map<String, Map<Empleado, Object>> asignacionesFecha = asignacionesEmpleados.get(fecha);
        
        if (!asignacionesFecha.containsKey(turno)) {
            return false;
        }
        
        // Verificar si el empleado está asignado a ese turno
        Map<Empleado, Object> asignacionesTurno = asignacionesFecha.get(turno);
        return asignacionesTurno.containsKey(empleado);
    }
    
    /**
     * Obtiene el lugar de trabajo asignado a un empleado en un turno específico
     * 
     * @param empleado El empleado a consultar
     * @param fecha La fecha del turno
     * @param turno El turno a consultar
     * @return El lugar de trabajo asignado, o null si no está asignado
     */
    public Object obtenerLugarAsignado(Empleado empleado, Date fecha, String turno) {
        if (empleado == null || fecha == null || turno == null || !Turno.esValido(turno)) {
            return null;
        }
        
        // Verificar si hay asignaciones para esa fecha y turno
        if (!asignacionesEmpleados.containsKey(fecha)) {
            return null;
        }
        
        Map<String, Map<Empleado, Object>> asignacionesFecha = asignacionesEmpleados.get(fecha);
        
        if (!asignacionesFecha.containsKey(turno)) {
            return null;
        }
        
        // Obtener el lugar asignado al empleado
        Map<Empleado, Object> asignacionesTurno = asignacionesFecha.get(turno);
        return asignacionesTurno.get(empleado);
    }
    
    /**
     * Libera la asignación de un empleado en un turno específico
     * 
     * @param empleado El empleado a liberar
     * @param fecha La fecha del turno
     * @param turno El turno a liberar
     * @return true si se liberó la asignación, false si no estaba asignado
     * @throws EmpleadoException Si hay un error al liberar la asignación
     */
    public boolean liberarAsignacion(Empleado empleado, Date fecha, String turno) throws EmpleadoException {
        if (empleado == null || fecha == null || turno == null) {
            throw new EmpleadoException("El empleado, la fecha y el turno no pueden ser nulos");
        }
        
        if (!Turno.esValido(turno)) {
            throw new EmpleadoException("El turno no es válido");
        }
        
        // Verificar si hay asignaciones para esa fecha y turno
        if (!asignacionesEmpleados.containsKey(fecha)) {
            return false;
        }
        
        Map<String, Map<Empleado, Object>> asignacionesFecha = asignacionesEmpleados.get(fecha);
        
        if (!asignacionesFecha.containsKey(turno)) {
            return false;
        }
        
        // Liberar la asignación del empleado
        Map<Empleado, Object> asignacionesTurno = asignacionesFecha.get(turno);
        return asignacionesTurno.remove(empleado) != null;
    }
    
    /**
     * Obtiene el nombre del administrador
     * 
     * @return El nombre del administrador
     */
    public String getNombre() {
        return nombre;
    }
    
    /**
     * Establece el nombre del administrador
     * 
     * @param nombre El nuevo nombre
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    /**
     * Obtiene el identificador del administrador
     * 
     * @return El identificador del administrador
     */
    public int getId() {
        return id;
    }
    
    /**
     * Obtiene la lista de atracciones del parque
     * 
     * @return La lista de atracciones
     */
    public List<Atraccion> getAtracciones() {
        return new ArrayList<>(atracciones);
    }
    
    /**
     * Obtiene la lista de empleados del parque
     * 
     * @return La lista de empleados
     */
    public List<Empleado> getEmpleados() {
        return new ArrayList<>(empleados);
    }
    
    /**
     * Obtiene la lista de espectáculos del parque
     * 
     * @return La lista de espectáculos
     */
    public List<Espectaculo> getEspectaculos() {
        return new ArrayList<>(espectaculos);
    }
    
    /**
     * Reparte turnos a los empleados
     * 
     * @param empleado El empleado al que se le asignarán turnos
     * @throws EmpleadoException Si hay un error al repartir los turnos
     */
    public void repartirTurnos(Empleado empleado) throws EmpleadoException {
        if (empleado == null) {
            throw new EmpleadoException("El empleado no puede ser nulo");
        }
        
        // Verificar que el empleado esté en la lista de empleados
        if (!empleados.contains(empleado)) {
            throw new EmpleadoException("El empleado no existe en el sistema");
        }
        
        // En una implementación real, aquí se asignarían los turnos al empleado
        // según la disponibilidad y las necesidades del parque
    }
    
    /**
     * Gestiona los descuentos para empleados en la compra de tiquetes
     * 
     * @param tiquete El tiquete al que se le aplicará el descuento
     * @param empleado El empleado que compra el tiquete
     * @throws EmpleadoException Si hay un error al aplicar el descuento
     */
    public void gestionarDescuentoEmpleado(Tiquete tiquete, Empleado empleado) throws EmpleadoException {
        if (tiquete == null || empleado == null) {
            throw new EmpleadoException("El tiquete y el empleado no pueden ser nulos");
        }
        
        // Verificar que el empleado esté en la lista de empleados
        if (!empleados.contains(empleado)) {
            throw new EmpleadoException("El empleado no existe en el sistema");
        }
        
        // Aplicar el descuento de empleado al tiquete
        tiquete.setDctoEmpleado(true);
    }
    
    /**
     * Gestiona la capacitación de empleados para atracciones
     * 
     * @param empleado El empleado a capacitar
     * @param tipoCapacitacion El tipo de capacitación
     * @throws EmpleadoException Si hay un error al capacitar al empleado
     */
    public void gestionarCapacitacionEmpleado(Empleado empleado, String tipoCapacitacion) throws EmpleadoException {
        if (empleado == null || tipoCapacitacion == null || tipoCapacitacion.isEmpty()) {
            throw new EmpleadoException("El empleado y el tipo de capacitación no pueden ser nulos o vacíos");
        }
        
        // Verificar que el empleado esté en la lista de empleados
        if (!empleados.contains(empleado)) {
            throw new EmpleadoException("El empleado no existe en el sistema");
        }
        
        // En una implementación real, aquí se actualizaría la capacitación del empleado
        // según el tipo de capacitación recibido
    }
    
    /**
     * Genera un reporte de ventas por período
     * 
     * @param fechaInicio Fecha de inicio del período
     * @param fechaFin Fecha de fin del período
     * @return Un mapa con las ventas por día
     */
    public Map<Date, Integer> generarReporteVentasPorPeriodo(Date fechaInicio, Date fechaFin) {
        Map<Date, Integer> reporteVentas = new HashMap<>();
        
        if (fechaInicio == null || fechaFin == null || fechaInicio.after(fechaFin)) {
            return reporteVentas;
        }
        
        // En una implementación real, aquí se calcularían las ventas por día
        // en el período especificado
        
        return reporteVentas;
    }
    
    /**
     * Genera un reporte de afluencia de visitantes por atracción
     * 
     * @param fecha La fecha para el reporte
     * @return Un mapa con la afluencia por atracción
     */
    public Map<Atraccion, Integer> generarReporteAfluenciaPorAtraccion(Date fecha) {
        Map<Atraccion, Integer> reporteAfluencia = new HashMap<>();
        
        if (fecha == null) {
            return reporteAfluencia;
        }
        
        // En una implementación real, aquí se calcularía la afluencia por atracción
        // en la fecha especificada
        
        return reporteAfluencia;
    }
    
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Administrador other = (Administrador) obj;
        return id == other.id;
    }
    
    @Override
    public int hashCode() {
        return id;
    }
    
    @Override
    public String toString() {
        return "Administrador [id=" + id + ", nombre=" + nombre + ", email=" + email + "]";
    }
}