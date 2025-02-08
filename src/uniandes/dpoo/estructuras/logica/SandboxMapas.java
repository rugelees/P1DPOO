package uniandes.dpoo.estructuras.logica;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class SandboxMapas {
    private Map<String, String> mapaCadenas;

    public SandboxMapas() {
        mapaCadenas = new HashMap<String, String>();
    }

    public List<String> getValoresComoLista() {
        Collection<String> valores = mapaCadenas.values();
        List<String> lista = new java.util.ArrayList<>(valores);
        java.util.Collections.sort(lista);
        return lista;
    }

    public List<String> getLlavesComoListaInvertida() {
        Collection<String> llaves = mapaCadenas.keySet();
        List<String> lista = new java.util.ArrayList<>(llaves);
        java.util.Collections.sort(lista);
        java.util.Collections.reverse(lista);
        return lista;
    }

    public String getPrimera() {
        if (mapaCadenas.isEmpty()) {
            return null;
        }
        String primera = null;
        for (String llave : mapaCadenas.keySet()) {
            if (primera == null || llave.compareTo(primera) < 0) {
                primera = llave;
            }
        }
        return primera;
    }

    public String getUltima() {
        if (mapaCadenas.isEmpty()) {
            return null;
        }
        String ultima = null;
        for (String valor : mapaCadenas.values()) {
            if (ultima == null || valor.compareTo(ultima) > 0) {
                ultima = valor;
            }
        }
        return ultima;
    }

    public Collection<String> getLlaves() {
        Map<String, String> mayusculas = new HashMap<>();
        for (String llave : mapaCadenas.keySet()) {
            mayusculas.put(llave.toUpperCase(), mapaCadenas.get(llave));
        }
        return mayusculas.keySet();
    }

    public int getCantidadCadenasDiferentes() {
        Map<String, Integer> diferentes = new HashMap<>();
        for (String valor : mapaCadenas.values()) {
            diferentes.put(valor, 1);
        }
        return diferentes.size();
    }

    public void agregarCadena(String cadena) {
        StringBuilder sb = new StringBuilder(cadena);
        String llave = sb.reverse().toString();
        mapaCadenas.put(llave, cadena);
    }

    public void eliminarCadenaConLLave(String llave) {
        mapaCadenas.remove(llave);
    }

    public void eliminarCadenaConValor(String valor) {
        StringBuilder sb = new StringBuilder(valor);
        String llave = sb.reverse().toString();
        mapaCadenas.remove(llave);
    }

    public void reiniciarMapaCadenas(List<Object> objetos) {
        mapaCadenas.clear();
        for (Object obj : objetos) {
            String valor = obj.toString();
            StringBuilder sb = new StringBuilder(valor);
            String llave = sb.reverse().toString();
            mapaCadenas.put(llave, valor);
        }
    }

    public void volverMayusculas() {
        Map<String, String> nuevoMapa = new HashMap<>();
        for (Map.Entry<String, String> entry : mapaCadenas.entrySet()) {
            nuevoMapa.put(entry.getKey().toUpperCase(), entry.getValue());
        }
        mapaCadenas = nuevoMapa;
    }

    public boolean compararValores(String[] otroArreglo) {
        Collection<String> valores = mapaCadenas.values();
        for (String elemento : otroArreglo) {
            if (!valores.contains(elemento)) {
                return false;
            }
        }
        return true;
    }
}