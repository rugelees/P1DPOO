package persistencia;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase utilitaria para la persistencia de datos en archivos
 */
public class ArchivoUtil {
    private static final String RUTA_DATOS = "data/";
    
    /**
     * Guarda un texto en un archivo
     * 
     * @param contenido El contenido a guardar
     * @param nombreArchivo El nombre del archivo
     * @throws IOException Si hay un error al guardar el archivo
     */
    public static void guardarTexto(String contenido, String nombreArchivo) throws IOException {
        File carpeta = new File(RUTA_DATOS);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        
        File archivo = new File(RUTA_DATOS + nombreArchivo);
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(archivo)))) {
            writer.print(contenido);
        }
    }
    
    /**
     * Carga el contenido de un archivo de texto
     * 
     * @param nombreArchivo El nombre del archivo
     * @return El contenido del archivo
     * @throws IOException Si hay un error al cargar el archivo
     */
    public static String cargarTexto(String nombreArchivo) throws IOException {
        File archivo = new File(RUTA_DATOS + nombreArchivo);
        if (!archivo.exists()) {
            return "";
        }
        
        StringBuilder contenido = new StringBuilder();
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                contenido.append(linea).append("\n");
            }
        }
        
        return contenido.toString();
    }
    
    /**
     * Guarda una lista de líneas en un archivo
     * 
     * @param lineas La lista de líneas a guardar
     * @param nombreArchivo El nombre del archivo
     * @throws IOException Si hay un error al guardar el archivo
     */
    public static void guardarLineas(List<String> lineas, String nombreArchivo) throws IOException {
        File carpeta = new File(RUTA_DATOS);
        if (!carpeta.exists()) {
            carpeta.mkdirs();
        }
        
        File archivo = new File(RUTA_DATOS + nombreArchivo);
        try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(archivo)))) {
            for (String linea : lineas) {
                writer.println(linea);
            }
        }
    }
    
    /**
     * Carga una lista de líneas desde un archivo
     * 
     * @param nombreArchivo El nombre del archivo
     * @return La lista de líneas cargadas
     * @throws IOException Si hay un error al cargar el archivo
     */
    public static List<String> cargarLineas(String nombreArchivo) throws IOException {
        File archivo = new File(RUTA_DATOS + nombreArchivo);
        List<String> lineas = new ArrayList<>();
        
        if (!archivo.exists()) {
            return lineas;
        }
        
        try (BufferedReader reader = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                lineas.add(linea);
            }
        }
        
        return lineas;
    }
    
    /**
     * Verifica si un archivo existe
     * 
     * @param nombreArchivo El nombre del archivo
     * @return true si el archivo existe
     */
    public static boolean existeArchivo(String nombreArchivo) {
        File archivo = new File(RUTA_DATOS + nombreArchivo);
        return archivo.exists();
    }
    
    /**
     * Elimina un archivo
     * 
     * @param nombreArchivo El nombre del archivo
     * @return true si el archivo fue eliminado
     */
    public static boolean eliminarArchivo(String nombreArchivo) {
        File archivo = new File(RUTA_DATOS + nombreArchivo);
        return archivo.delete();
    }
}