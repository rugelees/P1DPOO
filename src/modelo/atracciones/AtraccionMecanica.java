package modelo.atracciones;

import java.util.Date;

import modelo.usuarios.Cliente;

/**
 * Clase que representa una atracción mecánica en el parque
 */
public class AtraccionMecanica extends Atraccion {
    private static final long serialVersionUID = 1L;
    
    private String ubicacion;
    private int cupoMaximo;
    private float alturaMinima;
    private float alturaMaxima;
    private float pesoMinimo;
    private float pesoMaximo;
    private String restriccionesSalud;
    private String nivelRiesgo;
    
    /**
     * Constructor de AtraccionMecanica
     * 
     * @param nombre Nombre de la atracción
     * @param restriccionClima Restricciones climáticas
     * @param deTemporada Indica si la atracción es de temporada
     * @param fechaInicio Fecha de inicio (si es de temporada)
     * @param fechaFin Fecha de fin (si es de temporada)
     * @param nivelExclusividad Nivel de exclusividad de la atracción
     * @param empleadosEncargados Número de empleados necesarios
     * @param ubicacion Ubicación de la atracción en el parque
     * @param cupoMaximo Capacidad máxima de personas
     * @param alturaMinima Altura mínima permitida
     * @param alturaMaxima Altura máxima permitida
     * @param pesoMinimo Peso mínimo permitido
     * @param pesoMaximo Peso máximo permitido
     * @param restriccionesSalud Restricciones de salud
     * @param nivelRiesgo Nivel de riesgo (medio o alto)
     */
    public AtraccionMecanica(String nombre, String restriccionClima, boolean deTemporada, Date fechaInicio, Date fechaFin,
            String nivelExclusividad, int empleadosEncargados, String ubicacion, int cupoMaximo, float alturaMinima,
            float alturaMaxima, float pesoMinimo, float pesoMaximo, String restriccionesSalud, String nivelRiesgo) {
        super(nombre, restriccionClima, deTemporada, fechaInicio, fechaFin, nivelExclusividad, empleadosEncargados);
        this.ubicacion = ubicacion;
        this.cupoMaximo = cupoMaximo;
        this.alturaMinima = alturaMinima;
        this.alturaMaxima = alturaMaxima;
        this.pesoMinimo = pesoMinimo;
        this.pesoMaximo = pesoMaximo;
        this.restriccionesSalud = restriccionesSalud;
        this.nivelRiesgo = nivelRiesgo;
    }
    
    /**
     * Verifica si un cliente cumple con las restricciones físicas
     * 
     * @param cliente El cliente a verificar
     * @return true si el cliente cumple con las restricciones, false de lo contrario
     */
    public boolean verificarRestriccionesFisicas(Cliente cliente) {
        if (cliente == null) {
            return false;
        }
        
        float altura = cliente.getAltura();
        float peso = cliente.getPeso();
        
        // Verificar restricciones de altura
        if (altura < alturaMinima || altura > alturaMaxima) {
            return false;
        }
        
        // Verificar restricciones de peso
        if (peso < pesoMinimo || peso > pesoMaximo) {
            return false;
        }
        
        return true;
    }
    
    /**
     * Verifica si un cliente tiene contraindicaciones para la atracción
     * 
     * @param cliente El cliente a verificar
     * @return true si el cliente no tiene contraindicaciones, false si tiene alguna
     */
    public boolean verificarContraindicaciones(Cliente cliente) {
        if (cliente == null || restriccionesSalud == null || restriccionesSalud.isEmpty()) {
            return true; // Si no hay restricciones, cualquier cliente puede entrar
        }
        
        // Dividir las restricciones en un array
        String[] restricciones = restriccionesSalud.split(",");
        
        // Verificar si el cliente tiene alguna de las condiciones de salud restringidas
        for (String restriccion : restricciones) {
            if (cliente.tieneCondicionSalud(restriccion.trim())) {
                return false;
            }
        }
        
        return true;
    }
    
    @Override
    public String consultarInformacion() {
        StringBuilder info = new StringBuilder();
        info.append("Atracción Mecánica: ").append(nombre).append("\n");
        info.append("Ubicación: ").append(ubicacion).append("\n");
        info.append("Nivel de Exclusividad: ").append(nivelExclusividad).append("\n");
        info.append("Cupo Máximo: ").append(cupoMaximo).append(" personas\n");
        info.append("Restricciones de Altura: ").append(alturaMinima).append(" cm - ").append(alturaMaxima).append(" cm\n");
        info.append("Restricciones de Peso: ").append(pesoMinimo).append(" kg - ").append(pesoMaximo).append(" kg\n");
        info.append("Nivel de Riesgo: ").append(nivelRiesgo).append("\n");
        
        if (restriccionesSalud != null && !restriccionesSalud.isEmpty()) {
            info.append("Contraindicaciones: ").append(restriccionesSalud).append("\n");
        }
        
        if (restriccionClima != null && !restriccionClima.isEmpty()) {
            info.append("Restricciones Climáticas: ").append(restriccionClima).append("\n");
        }
        
        if (deTemporada) {
            info.append("Atracción de Temporada\n");
            info.append("Disponible desde: ").append(fechaInicio).append(" hasta: ").append(fechaFin).append("\n");
        }
        
        return info.toString();
    }
    
    /**
     * Obtiene la ubicación de la atracción
     * 
     * @return La ubicación
     */
    public String getUbicacion() {
        return ubicacion;
    }
    
    /**
     * Establece la ubicación de la atracción
     * 
     * @param ubicacion La nueva ubicación
     */
    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }
    
    /**
     * Obtiene el cupo máximo de la atracción
     * 
     * @return El cupo máximo
     */
    public int getCupoMaximo() {
        return cupoMaximo;
    }
    
    /**
     * Establece el cupo máximo de la atracción
     * 
     * @param cupoMaximo El nuevo cupo máximo
     */
    public void setCupoMaximo(int cupoMaximo) {
        this.cupoMaximo = cupoMaximo;
    }
    
    /**
     * Obtiene la altura mínima permitida
     * 
     * @return La altura mínima
     */
    public float getAlturaMinima() {
        return alturaMinima;
    }
    
    /**
     * Establece la altura mínima permitida
     * 
     * @param alturaMinima La nueva altura mínima
     */
    public void setAlturaMinima(float alturaMinima) {
        this.alturaMinima = alturaMinima;
    }
    
    /**
     * Obtiene la altura máxima permitida
     * 
     * @return La altura máxima
     */
    public float getAlturaMaxima() {
        return alturaMaxima;
    }
    
    /**
     * Establece la altura máxima permitida
     * 
     * @param alturaMaxima La nueva altura máxima
     */
    public void setAlturaMaxima(float alturaMaxima) {
        this.alturaMaxima = alturaMaxima;
    }
    
    /**
     * Obtiene el peso mínimo permitido
     * 
     * @return El peso mínimo
     */
    public float getPesoMinimo() {
        return pesoMinimo;
    }
    
    /**
     * Establece el peso mínimo permitido
     * 
     * @param pesoMinimo El nuevo peso mínimo
     */
    public void setPesoMinimo(float pesoMinimo) {
        this.pesoMinimo = pesoMinimo;
    }
    
    /**
     * Obtiene el peso máximo permitido
     * 
     * @return El peso máximo
     */
    public float getPesoMaximo() {
        return pesoMaximo;
    }
    
    /**
     * Establece el peso máximo permitido
     * 
     * @param pesoMaximo El nuevo peso máximo
     */
    public void setPesoMaximo(float pesoMaximo) {
        this.pesoMaximo = pesoMaximo;
    }
    
    /**
     * Obtiene las restricciones de salud
     * 
     * @return Las restricciones de salud
     */
    public String getRestriccionesSalud() {
        return restriccionesSalud;
    }
    
    /**
     * Establece las restricciones de salud
     * 
     * @param restriccionesSalud Las nuevas restricciones
     */
    public void setRestriccionesSalud(String restriccionesSalud) {
        this.restriccionesSalud = restriccionesSalud;
    }
    
    /**
     * Obtiene el nivel de riesgo
     * 
     * @return El nivel de riesgo
     */
    public String getNivelRiesgo() {
        return nivelRiesgo;
    }
    
    /**
     * Establece el nivel de riesgo
     * 
     * @param nivelRiesgo El nuevo nivel de riesgo
     */
    public void setNivelRiesgo(String nivelRiesgo) {
        this.nivelRiesgo = nivelRiesgo;
    }
    
    /**
     * Verifica si la atracción es de riesgo alto
     * 
     * @return true si la atracción es de riesgo alto
     */
    public boolean esRiesgoAlto() {
        return "alto".equalsIgnoreCase(nivelRiesgo);
    }
    
    /**
     * Verifica si la atracción es de riesgo medio
     * 
     * @return true si la atracción es de riesgo medio
     */
    public boolean esRiesgoMedio() {
        return "medio".equalsIgnoreCase(nivelRiesgo);
    }
}