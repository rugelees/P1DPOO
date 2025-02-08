package uniandes.dpoo.estructuras.logica;

import java.util.Collection;
import java.util.List;
import java.util.NavigableSet;
import java.util.TreeSet;

public class SandboxConjuntos {
    private NavigableSet<String> arbolCadenas;

    public SandboxConjuntos() {
        arbolCadenas = new TreeSet<String>();
    }

    public List<String> getCadenasComoLista() {
        String[] array = arbolCadenas.toArray(new String[0]);
        return new java.util.AbstractList<String>() {
            @Override
            public String get(int index) {
                return array[index];
            }

            @Override
            public String set(int index, String element) {
                String old = array[index];
                array[index] = element;
                return old;
            }

            @Override
            public int size() {
                return array.length;
            }
        };
    }

    public List<String> getCadenasComoListaInvertida() {
        final String[] array = arbolCadenas.toArray(new String[0]);
        return new java.util.AbstractList<String>() {
            @Override
            public String get(int index) {
                return array[array.length - 1 - index];
            }

            @Override
            public String set(int index, String element) {
                int actualIndex = array.length - 1 - index;
                String old = array[actualIndex];
                array[actualIndex] = element;
                return old;
            }

            @Override
            public int size() {
                return array.length;
            }
        };
    }
    public String getPrimera() {
        return arbolCadenas.isEmpty() ? null : arbolCadenas.first();
    }

    public String getUltima() {
        return arbolCadenas.isEmpty() ? null : arbolCadenas.last();
    }

    public Collection<String> getSiguientes(String cadena) {
        return arbolCadenas.tailSet(cadena, true);
    }

    public int getCantidadCadenas() {
        return arbolCadenas.size();
    }

    public void agregarCadena(String cadena) {
        if (cadena != null) {
            arbolCadenas.add(cadena);
        }
    }

    public void eliminarCadena(String cadena) {
        arbolCadenas.remove(cadena);
    }

    public void eliminarCadenaSinMayusculasOMinusculas(String cadena) {
        if (cadena != null) {
            String cadenaMinusculas = cadena.toLowerCase();
            NavigableSet<String> temp = new TreeSet<>();
            for (String s : arbolCadenas) {
                if (!s.toLowerCase().equals(cadenaMinusculas)) {
                    temp.add(s);
                }
            }
            arbolCadenas = temp;
        }
    }

    public void eliminarPrimera() {
        if (!arbolCadenas.isEmpty()) {
            arbolCadenas.remove(arbolCadenas.first());
        }
    }

    public void reiniciarConjuntoCadenas(List<Object> objetos) {
        arbolCadenas.clear();
        if (objetos != null) {
            for (Object obj : objetos) {
                if (obj != null) {
                    arbolCadenas.add(obj.toString());
                }
            }
        }
    }

    public void volverMayusculas() {
        NavigableSet<String> temp = new TreeSet<>();
        for (String cadena : arbolCadenas) {
            temp.add(cadena.toUpperCase());
        }
        arbolCadenas = temp;
    }

    public TreeSet<String> invertirCadenas() {
        TreeSet<String> invertido = new TreeSet<>((a, b) -> b.compareTo(a));
        invertido.addAll(arbolCadenas);
        return invertido;
    }

    public boolean compararElementos(String[] otroArreglo) {
        if (otroArreglo == null) return false;
        
        for (String elemento : otroArreglo) {
            if (elemento == null || !arbolCadenas.contains(elemento)) {
                return false;
            }
        }
        return true;
    }
}