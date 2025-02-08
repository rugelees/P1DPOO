package uniandes.dpoo.estructuras.logica;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

public class SandboxArreglos {
    private int[] arregloEnteros;
    private String[] arregloCadenas;

    public SandboxArreglos() {
        arregloEnteros = new int[0];
        arregloCadenas = new String[0];
    }

    public int[] getCopiaEnteros() {
        int[] copia = new int[arregloEnteros.length];
        for (int i = 0; i < arregloEnteros.length; i++) {
            copia[i] = arregloEnteros[i];
        }
        return copia;
    }

    public String[] getCopiaCadenas() {
        String[] copia = new String[arregloCadenas.length];
        for (int i = 0; i < arregloCadenas.length; i++) {
            copia[i] = arregloCadenas[i];
        }
        return copia;
    }

    public int getCantidadEnteros() {
        return arregloEnteros.length;
    }

    public int getCantidadCadenas() {
        return arregloCadenas.length;
    }

    public void agregarEntero(int entero) {
        int[] nuevo = new int[arregloEnteros.length + 1];
        for (int i = 0; i < arregloEnteros.length; i++) {
            nuevo[i] = arregloEnteros[i];
        }
        nuevo[arregloEnteros.length] = entero;
        arregloEnteros = nuevo;
    }

    public void agregarCadena(String cadena) {
        String[] nuevo = new String[arregloCadenas.length + 1];
        for (int i = 0; i < arregloCadenas.length; i++) {
            nuevo[i] = arregloCadenas[i];
        }
        nuevo[arregloCadenas.length] = cadena;
        arregloCadenas = nuevo;
    }

    public void eliminarEntero(int valor) {
        int count = 0;
        for (int i = 0; i < arregloEnteros.length; i++) {
            if (arregloEnteros[i] == valor) {
                count++;
            }
        }
        int[] nuevo = new int[arregloEnteros.length - count];
        int index = 0;
        for (int i = 0; i < arregloEnteros.length; i++) {
            if (arregloEnteros[i] != valor) {
                nuevo[index++] = arregloEnteros[i];
            }
        }
        arregloEnteros = nuevo;
    }

    public void eliminarCadena(String cadena) {
        int count = 0;
        for (int i = 0; i < arregloCadenas.length; i++) {
            if (arregloCadenas[i].equals(cadena)) {
                count++;
            }
        }
        String[] nuevo = new String[arregloCadenas.length - count];
        int index = 0;
        for (int i = 0; i < arregloCadenas.length; i++) {
            if (!arregloCadenas[i].equals(cadena)) {
                nuevo[index++] = arregloCadenas[i];
            }
        }
        arregloCadenas = nuevo;
    }

    public void insertarEntero(int entero, int posicion) {
        posicion = Math.max(0, Math.min(posicion, arregloEnteros.length));
        int[] nuevo = new int[arregloEnteros.length + 1];
        for (int i = 0; i < posicion; i++) {
            nuevo[i] = arregloEnteros[i];
        }
        nuevo[posicion] = entero;
        for (int i = posicion; i < arregloEnteros.length; i++) {
            nuevo[i + 1] = arregloEnteros[i];
        }
        arregloEnteros = nuevo;
    }

    public void eliminarEnteroPorPosicion(int posicion) {
        if (posicion < 0 || posicion >= arregloEnteros.length) return;
        int[] nuevo = new int[arregloEnteros.length - 1];
        for (int i = 0; i < posicion; i++) {
            nuevo[i] = arregloEnteros[i];
        }
        for (int i = posicion + 1; i < arregloEnteros.length; i++) {
            nuevo[i - 1] = arregloEnteros[i];
        }
        arregloEnteros = nuevo;
    }

    public void reiniciarArregloEnteros(double[] valores) {
        arregloEnteros = new int[valores.length];
        for (int i = 0; i < valores.length; i++) {
            arregloEnteros[i] = (int) valores[i];
        }
    }

    public void reiniciarArregloCadenas(Object[] objetos) {
        arregloCadenas = new String[objetos.length];
        for (int i = 0; i < objetos.length; i++) {
            arregloCadenas[i] = objetos[i].toString();
        }
    }

    public void volverPositivos() {
        for (int i = 0; i < arregloEnteros.length; i++) {
            arregloEnteros[i] = Math.abs(arregloEnteros[i]);
        }
    }

    public void organizarEnteros() {
        Arrays.sort(arregloEnteros);
    }

    public void organizarCadenas() {
        Arrays.sort(arregloCadenas);
    }

    public int contarApariciones(int valor) {
        int count = 0;
        for (int i = 0; i < arregloEnteros.length; i++) {
            if (arregloEnteros[i] == valor) {
                count++;
            }
        }
        return count;
    }

    public int contarApariciones(String cadena) {
        int count = 0;
        for (int i = 0; i < arregloCadenas.length; i++) {
            if (arregloCadenas[i].equalsIgnoreCase(cadena)) {
                count++;
            }
        }
        return count;
    }

    public int[] buscarEntero( int valor )
    {
    	int contador = 0;
        for (int i = 0; i < arregloEnteros.length; i++) {
            if (arregloEnteros[i] == valor) {
                contador++;
            }
        }
        
        int[] posiciones = new int[contador];
        int pos = 0;
        
        for (int i = 0; i < arregloEnteros.length; i++) {
            if (arregloEnteros[i] == valor) {
                posiciones[pos] = i;
                pos++;
            }
        }
        
        return posiciones;
    }

    public int[] calcularRangoEnteros() {
        if (arregloEnteros.length == 0) return new int[0];
        int min = arregloEnteros[0];
        int max = arregloEnteros[0];
        for (int i = 1; i < arregloEnteros.length; i++) {
            if (arregloEnteros[i] < min) min = arregloEnteros[i];
            if (arregloEnteros[i] > max) max = arregloEnteros[i];
        }
        return new int[]{min, max};
    }

    public HashMap<Integer, Integer> calcularHistograma() {
        HashMap<Integer, Integer> histograma = new HashMap<>();
        for (int num : arregloEnteros) {
            if (histograma.containsKey(num)) {
                histograma.put(num, histograma.get(num) + 1);
            } else {
                histograma.put(num, 1);
            }
        }
        return histograma;
    }

    public int contarEnterosRepetidos() {
        HashMap<Integer, Integer> conteo = new HashMap<>();
        int count = 0;
        for (int num : arregloEnteros) {
            if (conteo.containsKey(num)) {
                conteo.put(num, conteo.get(num) + 1);
                if (conteo.get(num) == 2) {
                    count++;
                }
            } else {
                conteo.put(num, 1);
            }
        }
        return count;
    }

    public boolean compararArregloEnteros(int[] otroArreglo) {
        if (arregloEnteros.length != otroArreglo.length) return false;
        for (int i = 0; i < arregloEnteros.length; i++) {
            if (arregloEnteros[i] != otroArreglo[i]) return false;
        }
        return true;
    }

    public boolean mismosEnteros(int[] otroArreglo) {
        if (arregloEnteros.length != otroArreglo.length) return false;
        int[] copia1 = getCopiaEnteros();
        int[] copia2 = Arrays.copyOf(otroArreglo, otroArreglo.length);
        Arrays.sort(copia1);
        Arrays.sort(copia2);
        return compararArregloEnteros(copia1, copia2);
    }

    private boolean compararArregloEnteros(int[] arreglo1, int[] arreglo2) {
        for (int i = 0; i < arreglo1.length; i++) {
            if (arreglo1[i] != arreglo2[i]) return false;
        }
        return true;
    }

    public void generarEnteros(int cantidad, int minimo, int maximo) {
        Random rand = new Random();
        arregloEnteros = new int[cantidad];
        for (int i = 0; i < cantidad; i++) {
            arregloEnteros[i] = rand.nextInt(maximo - minimo + 1) + minimo;
        }
    }
}