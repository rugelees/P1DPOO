package persistencia;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import excepciones.AtraccionException;
import excepciones.TiqueteException;
import modelo.atracciones.Atraccion;
import modelo.tiquetes.EnTemporada;
import modelo.tiquetes.FastPass;
import modelo.tiquetes.Individual;
import modelo.tiquetes.Tiquete;
import modelo.tiquetes.TiqueteBasico;

/**
 * Clase para la persistencia de tiquetes
 */
public class PersistenciaTiquetes {
    private static final String ARCHIVO_TIQUETES_BASICOS = "tiquetes_basicos.txt";
    private static final String ARCHIVO_TIQUETES_TEMPORADA = "tiquetes_temporada.txt";
    private static final String ARCHIVO_TIQUETES_INDIVIDUALES = "tiquetes_individuales.txt";
    private static final String ARCHIVO_FAST_PASSES = "fast_passes.txt";
    
    private static final SimpleDateFormat FORMATO_FECHA = new SimpleDateFormat("yyyy-MM-dd");
    
    /**
     * Guarda una lista de tiquetes básicos
     * 
     * @param tiquetes La lista a guardar
     * @throws TiqueteException Si hay un error al guardar los tiquetes
     */
    public void guardarTiquetesBasicos(List<TiqueteBasico> tiquetes) throws TiqueteException {
        try {
            List<String> lineas = new ArrayList<>();
            
            for (TiqueteBasico tiquete : tiquetes) {
                // Construir una línea con los datos del tiquete separados por |
                StringBuilder sb = new StringBuilder();
                sb.append(tiquete.getId()).append("|");
                sb.append(tiquete.getNombre()).append("|");
                sb.append(tiquete.getNumTiquetes()).append("|");
                sb.append(tiquete.getExclusividad()).append("|");
                sb.append(tiquete.getFecha() != null ? FORMATO_FECHA.format(tiquete.getFecha()) : "null").append("|");
                sb.append(tiquete.getEstado()).append("|");
                sb.append(tiquete.isDctoEmpleado()).append("|");
                sb.append(tiquete.getPortalCompra()).append("|");
                sb.append(tiquete.isUsado()).append("|");
                sb.append(tiquete.getCategoria());
                
                lineas.add(sb.toString());
            }
            
            ArchivoUtil.guardarLineas(lineas, ARCHIVO_TIQUETES_BASICOS);
            
        } catch (IOException e) {
            throw new TiqueteException("Error al guardar los tiquetes básicos", e);
        }
    }
    
    /**
     * Guarda una lista de tiquetes de temporada
     * 
     * @param tiquetes La lista a guardar
     * @throws TiqueteException Si hay un error al guardar los tiquetes
     */
    public void guardarTiquetesTemporada(List<EnTemporada> tiquetes) throws TiqueteException {
        try {
            List<String> lineas = new ArrayList<>();
            
            for (EnTemporada tiquete : tiquetes) {
                // Construir una línea con los datos del tiquete separados por |
                StringBuilder sb = new StringBuilder();
                sb.append(tiquete.getId()).append("|");
                sb.append(tiquete.getNombre()).append("|");
                sb.append(tiquete.getNumTiquetes()).append("|");
                sb.append(tiquete.getExclusividad()).append("|");
                sb.append(tiquete.getFecha() != null ? FORMATO_FECHA.format(tiquete.getFecha()) : "null").append("|");
                sb.append(tiquete.getEstado()).append("|");
                sb.append(tiquete.isDctoEmpleado()).append("|");
                sb.append(tiquete.getPortalCompra()).append("|");
                sb.append(tiquete.isUsado()).append("|");
                sb.append(tiquete.getFechaInicio() != null ? FORMATO_FECHA.format(tiquete.getFechaInicio()) : "null").append("|");
                sb.append(tiquete.getFechaFin() != null ? FORMATO_FECHA.format(tiquete.getFechaFin()) : "null").append("|");
                sb.append(tiquete.getTipoTemporada()).append("|");
                sb.append(tiquete.getCategoria());
                
                lineas.add(sb.toString());
            }
            
            ArchivoUtil.guardarLineas(lineas, ARCHIVO_TIQUETES_TEMPORADA);
            
        } catch (IOException e) {
            throw new TiqueteException("Error al guardar los tiquetes de temporada", e);
        }
    }
    
    /**
     * Guarda una lista de tiquetes individuales
     * 
     * @param tiquetes La lista a guardar
     * @throws TiqueteException Si hay un error al guardar los tiquetes
     */
    public void guardarTiquetesIndividuales(List<Individual> tiquetes) throws TiqueteException {
        try {
            List<String> lineas = new ArrayList<>();
            
            for (Individual tiquete : tiquetes) {
                // Construir una línea con los datos del tiquete separados por |
                StringBuilder sb = new StringBuilder();
                sb.append(tiquete.getId()).append("|");
                sb.append(tiquete.getNombre()).append("|");
                sb.append(tiquete.getNumTiquetes()).append("|");
                sb.append(tiquete.getExclusividad()).append("|");
                sb.append(tiquete.getFecha() != null ? FORMATO_FECHA.format(tiquete.getFecha()) : "null").append("|");
                sb.append(tiquete.getEstado()).append("|");
                sb.append(tiquete.isDctoEmpleado()).append("|");
                sb.append(tiquete.getPortalCompra()).append("|");
                sb.append(tiquete.isUsado()).append("|");
                
                // Guardar el nombre de la atracción asociada
                Atraccion atraccion = tiquete.getAtraccion();
                sb.append(atraccion != null ? atraccion.getNombre() : "null");
                
                lineas.add(sb.toString());
            }
            
            ArchivoUtil.guardarLineas(lineas, ARCHIVO_TIQUETES_INDIVIDUALES);
            
        } catch (IOException e) {
            throw new TiqueteException("Error al guardar los tiquetes individuales", e);
        }
    }
    
    /**
     * Guarda una lista de FastPasses
     * 
     * @param fastPasses La lista a guardar
     * @throws TiqueteException Si hay un error al guardar los FastPasses
     */
    public void guardarFastPasses(List<FastPass> fastPasses) throws TiqueteException {
        try {
            List<String> lineas = new ArrayList<>();
            
            for (FastPass fastPass : fastPasses) {
                // Construir una línea con los datos del FastPass separados por |
                StringBuilder sb = new StringBuilder();
                
                // Guardar el id del tiquete asociado
                Tiquete tiqueteAsociado = fastPass.getTiqueteAsociado();
                sb.append(tiqueteAsociado != null ? tiqueteAsociado.getId() : "null").append("|");
                
                sb.append(fastPass.getFechaValida() != null ? FORMATO_FECHA.format(fastPass.getFechaValida()) : "null").append("|");
                sb.append(fastPass.isUsado());
                
                lineas.add(sb.toString());
            }
            
            ArchivoUtil.guardarLineas(lineas, ARCHIVO_FAST_PASSES);
            
        } catch (IOException e) {
            throw new TiqueteException("Error al guardar los FastPasses", e);
        }
    }
    
    /**
     * Carga la lista de tiquetes básicos
     * 
     * @return La lista de tiquetes
     * @throws TiqueteException Si hay un error al cargar los tiquetes
     */
    public List<TiqueteBasico> cargarTiquetesBasicos() throws TiqueteException {
        List<TiqueteBasico> tiquetes = new ArrayList<>();
        
        try {
            if (ArchivoUtil.existeArchivo(ARCHIVO_TIQUETES_BASICOS)) {
                List<String> lineas = ArchivoUtil.cargarLineas(ARCHIVO_TIQUETES_BASICOS);
                
                for (String linea : lineas) {
                    String[] partes = linea.split("\\|");
                    
                    if (partes.length >= 10) {
                        int id = Integer.parseInt(partes[0]);
                        String nombre = partes[1];
                        int numTiquetes = Integer.parseInt(partes[2]);
                        String exclusividad = partes[3];
                        
                        Date fecha = null;
                        if (!partes[4].equals("null")) {
                            fecha = FORMATO_FECHA.parse(partes[4]);
                        }
                        
                        String estado = partes[5];
                        boolean dctoEmpleado = Boolean.parseBoolean(partes[6]);
                        String portalCompra = partes[7];
                        boolean usado = Boolean.parseBoolean(partes[8]);
                        String categoria = partes[9];
                        
                        TiqueteBasico tiquete = new TiqueteBasico(id, nombre, numTiquetes, exclusividad, fecha, estado, portalCompra, categoria, usado);
                        tiquete.setDctoEmpleado(dctoEmpleado);
                        
                        tiquetes.add(tiquete);
                    }
                }
            }
        } catch (IOException | ParseException e) {
            throw new TiqueteException("Error al cargar los tiquetes básicos", e);
        }
        
        return tiquetes;
    }
    
    /**
     * Carga la lista de tiquetes de temporada
     * 
     * @return La lista de tiquetes
     * @throws TiqueteException Si hay un error al cargar los tiquetes
     */
    public List<EnTemporada> cargarTiquetesTemporada() throws TiqueteException {
        List<EnTemporada> tiquetes = new ArrayList<>();
        
        try {
            if (ArchivoUtil.existeArchivo(ARCHIVO_TIQUETES_TEMPORADA)) {
                List<String> lineas = ArchivoUtil.cargarLineas(ARCHIVO_TIQUETES_TEMPORADA);
                
                for (String linea : lineas) {
                    String[] partes = linea.split("\\|");
                    
                    if (partes.length >= 13) {
                        int id = Integer.parseInt(partes[0]);
                        String nombre = partes[1];
                        int numTiquetes = Integer.parseInt(partes[2]);
                        String exclusividad = partes[3];
                        
                        Date fecha = null;
                        if (!partes[4].equals("null")) {
                            fecha = FORMATO_FECHA.parse(partes[4]);
                        }
                        
                        String estado = partes[5];
                        boolean dctoEmpleado = Boolean.parseBoolean(partes[6]);
                        String portalCompra = partes[7];
                        boolean usado = Boolean.parseBoolean(partes[8]);
                        
                        Date fechaInicio = null;
                        if (!partes[9].equals("null")) {
                            fechaInicio = FORMATO_FECHA.parse(partes[9]);
                        }
                        
                        Date fechaFin = null;
                        if (!partes[10].equals("null")) {
                            fechaFin = FORMATO_FECHA.parse(partes[10]);
                        }
                        
                        String tipoTemporada = partes[11];
                        String categoria = partes[12];
                        
                        EnTemporada tiquete = new EnTemporada(id, nombre, numTiquetes, exclusividad, fecha, estado, portalCompra, fechaInicio, fechaFin, tipoTemporada, categoria, usado);
                        tiquete.setDctoEmpleado(dctoEmpleado);
                        
                        tiquetes.add(tiquete);
                    }
                }
            }
        } catch (IOException | ParseException e) {
            throw new TiqueteException("Error al cargar los tiquetes de temporada", e);
        }
        
        return tiquetes;
    }
    
    /**
     * Carga la lista de tiquetes individuales
     * 
     * @param controladorAtracciones Controlador para buscar las atracciones por nombre
     * @return La lista de tiquetes
     * @throws TiqueteException Si hay un error al cargar los tiquetes
     */
    public List<Individual> cargarTiquetesIndividuales() throws TiqueteException {
        List<Individual> tiquetes = new ArrayList<>();

        try {
            if (ArchivoUtil.existeArchivo(ARCHIVO_TIQUETES_INDIVIDUALES)) {
                List<String> lineas = ArchivoUtil.cargarLineas(ARCHIVO_TIQUETES_INDIVIDUALES);

                for (String linea : lineas) {
                    String[] partes = linea.split("\\|");

                    if (partes.length >= 10) {
                        int id = Integer.parseInt(partes[0]);
                        String nombre = partes[1];
                        int numTiquetes = Integer.parseInt(partes[2]);
                        String exclusividad = partes[3];

                        Date fecha = null;
                        if (!partes[4].equals("null")) {
                            fecha = FORMATO_FECHA.parse(partes[4]);
                        }

                        String estado = partes[5];
                        boolean dctoEmpleado = Boolean.parseBoolean(partes[6]);
                        String portalCompra = partes[7];
                        boolean usado = Boolean.parseBoolean(partes[8]);

                        // NO se resuelve la atracción aquí (se puede hacer después)
                        Individual tiquete = new Individual(null, id, nombre, numTiquetes, exclusividad, fecha, estado, portalCompra, usado);
                        tiquete.setDctoEmpleado(dctoEmpleado);

                        tiquetes.add(tiquete);
                    }
                }
            }
        } catch (IOException | ParseException e) {
            throw new TiqueteException("Error al cargar los tiquetes individuales", e);
        }

        return tiquetes;
    }

    /**
     * Carga la lista de FastPasses
     * 
     * @param tiquetes Lista de todos los tiquetes ya cargados (para buscar por ID)
     * @return La lista de FastPasses
     * @throws TiqueteException Si hay un error al cargar los FastPasses
     */
    public List<FastPass> cargarFastPasses(List<Tiquete> tiquetes) throws TiqueteException {
        List<FastPass> fastPasses = new ArrayList<>();
        
        try {
            if (ArchivoUtil.existeArchivo(ARCHIVO_FAST_PASSES)) {
                List<String> lineas = ArchivoUtil.cargarLineas(ARCHIVO_FAST_PASSES);
                
                for (String linea : lineas) {
                    String[] partes = linea.split("\\|");
                    
                    if (partes.length >= 3) {
                        // Buscar el tiquete por ID
                        int idTiquete = Integer.parseInt(partes[0]);
                        Tiquete tiqueteAsociado = null;
                        
                        for (Tiquete tiquete : tiquetes) {
                            if (tiquete.getId() == idTiquete) {
                                tiqueteAsociado = tiquete;
                                break;
                            }
                        }
                        
                        // Si no se encuentra el tiquete, no se puede cargar este FastPass
                        if (tiqueteAsociado == null) {
                            continue;
                        }
                        
                        Date fechaValida = null;
                        if (!partes[1].equals("null")) {
                            fechaValida = FORMATO_FECHA.parse(partes[1]);
                        }
                        
                        boolean usado = Boolean.parseBoolean(partes[2]);
                        
                        FastPass fastPass = new FastPass(tiqueteAsociado, fechaValida);
                        fastPass.setUsado(usado);
                        
                        fastPasses.add(fastPass);
                    }
                }
            }
        } catch (IOException | ParseException e) {
            throw new TiqueteException("Error al cargar los FastPasses", e);
        }
        
        return fastPasses;
    }
    
    /**
     * Carga la lista de tiquetes individuales (versión simplificada sin asociar atracciones)
     * 
     * @return La lista de tiquetes
     * @throws TiqueteException Si hay un error al cargar los tiquetes
     */

    /**
     * Carga la lista de FastPasses (versión simplificada sin asociar tiquetes)
     * 
     * @return La lista de FastPasses
     * @throws TiqueteException Si hay un error al cargar los FastPasses
     */
    public List<FastPass> cargarFastPasses() throws TiqueteException {
        List<FastPass> fastPasses = new ArrayList<>();
        
        try {
            if (ArchivoUtil.existeArchivo(ARCHIVO_FAST_PASSES)) {
                List<String> lineas = ArchivoUtil.cargarLineas(ARCHIVO_FAST_PASSES);
                
                for (String linea : lineas) {
                    String[] partes = linea.split("\\|");
                    
                    if (partes.length >= 3) {
                        // Por simplificación, creamos un tiquete básico temporal
                        int idTiquete = Integer.parseInt(partes[0]);
                        TiqueteBasico tiqueteTemp = new TiqueteBasico(idTiquete, "Temporal", 1, "Familiar", new Date(), "Activo", "Sistema", "Adulto", false);
                        
                        Date fechaValida = null;
                        if (!partes[1].equals("null")) {
                            fechaValida = FORMATO_FECHA.parse(partes[1]);
                        }
                        
                        boolean usado = Boolean.parseBoolean(partes[2]);
                        
                        FastPass fastPass = new FastPass(tiqueteTemp, fechaValida);
                        fastPass.setUsado(usado);
                        
                        fastPasses.add(fastPass);
                    }
                }
            }
        } catch (IOException | ParseException e) {
            throw new TiqueteException("Error al cargar los FastPasses", e);
        }
        
        return fastPasses;
    }
    
    /**
     * Carga todos los tiquetes
     * 
     * @return Lista con todos los tiquetes
     * @throws TiqueteException Si hay un error al cargar los tiquetes
     */
    public List<Tiquete> cargarTodosTiquetes() throws TiqueteException {
        List<Tiquete> tiquetes = new ArrayList<>();
        tiquetes.addAll(cargarTiquetesBasicos());
        tiquetes.addAll(cargarTiquetesTemporada());
        tiquetes.addAll(cargarTiquetesIndividuales());
        return tiquetes;
    }
    
    /**
     * Asocia los tiquetes individuales con sus atracciones correspondientes
     * 
     * @param controladorAtracciones Controlador para buscar las atracciones
     * @throws TiqueteException Si hay un error al asociar las atracciones
     */
    public void asociarAtraccionesATiquetes(Map<String, Atraccion> atraccionesPorNombre) throws TiqueteException {
        List<Individual> tiquetes = cargarTiquetesIndividuales();

        for (Individual tiquete : tiquetes) {
            String nombreAtraccion = tiquete.getAtraccion() != null ? tiquete.getAtraccion().getNombre() : null;

            if (nombreAtraccion != null && atraccionesPorNombre.containsKey(nombreAtraccion)) {
                tiquete.setAtraccion(atraccionesPorNombre.get(nombreAtraccion));
            }
        }
    }

    /**
     * Asocia los FastPass con sus tiquetes correspondientes
     * 
     * @param tiquetes Lista de todos los tiquetes
     * @throws TiqueteException Si hay un error al asociar los tiquetes
     */
    public void asociarTiquetesAFastPasses(List<Tiquete> tiquetes) throws TiqueteException {
        try {
            if (ArchivoUtil.existeArchivo(ARCHIVO_FAST_PASSES)) {
                List<String> lineas = ArchivoUtil.cargarLineas(ARCHIVO_FAST_PASSES);
                List<FastPass> fastPasses = cargarFastPasses();
                
                for (int i = 0; i < lineas.size() && i < fastPasses.size(); i++) {
                    String linea = lineas.get(i);
                    FastPass fastPass = fastPasses.get(i);
                    
                    String[] partes = linea.split("\\|");
                    
                    if (partes.length >= 3) {
                        int idTiquete = Integer.parseInt(partes[0]);
                        
                        // Buscar el tiquete por ID
                        for (Tiquete tiquete : tiquetes) {
                            if (tiquete.getId() == idTiquete) {
                                // Asignar el tiquete al FastPass
                                fastPass.setTiqueteAsociado(tiquete);
                                break;
                            }
                        }
                    }
                }
            }
        } catch (IOException e) {
            throw new TiqueteException("Error al asociar tiquetes a FastPasses", e);
        }
    }
}