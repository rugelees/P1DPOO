package uniandes.dpoo.estructuras.logica;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class SandboxListas {
    private List<Integer> listaEnteros;
    private List<String> listaCadenas;

    public SandboxListas() {
        listaEnteros = new ArrayList<Integer>();
        listaCadenas = new LinkedList<String>();
    }

    public List<Integer> getCopiaEnteros() {
        List<Integer> copia = new ArrayList<Integer>();
        for (Integer num : listaEnteros) {
            copia.add(num);
        }
        return copia;
    }

    public List<String> getCopiaCadenas() {
        List<String> copia = new LinkedList<String>();
        for (String str : listaCadenas) {
            copia.add(str);
        }
        return copia;
    }

    public int[] getEnterosComoArreglo() {
        int[] arreglo = new int[listaEnteros.size()];
        for (int i = 0; i < listaEnteros.size(); i++) {
            arreglo[i] = listaEnteros.get(i);
        }
        return arreglo;
    }

    public int getCantidadEnteros() {
        return listaEnteros.size();
    }

    public int getCantidadCadenas() {
        return listaCadenas.size();
    }

    public void agregarEntero(int entero) {
        listaEnteros.add(entero);
    }

    public void agregarCadena(String cadena) {
        listaCadenas.add(cadena);
    }

    public void eliminarEntero(int valor) {
        int i = 0;
        while (i < listaEnteros.size()) {
            if (listaEnteros.get(i) == valor) {
                listaEnteros.remove(i);
            } else {
                i++;
            }
        }
    }

    public void eliminarCadena(String cadena) {
        int i = 0;
        while (i < listaCadenas.size()) {
            if (listaCadenas.get(i).equals(cadena)) {
                listaCadenas.remove(i);
            } else {
                i++;
            }
        }
    }

    public void insertarEntero(int entero, int posicion) {
        if (posicion < 0) {
            listaEnteros.add(0, entero);
        } else if (posicion >= listaEnteros.size()) {
            listaEnteros.add(entero);
        } else {
            listaEnteros.add(posicion, entero);
        }
    }

    public void eliminarEnteroPorPosicion(int posicion) {
        if (posicion >= 0 && posicion < listaEnteros.size()) {
            listaEnteros.remove(posicion);
        }
    }

    public void reiniciarArregloEnteros(double[] valores) {
        listaEnteros.clear();
        for (double valor : valores) {
            listaEnteros.add((int) valor);
        }
    }

    public void reiniciarArregloCadenas(List<Object> objetos) {
        listaCadenas.clear();
        for (Object obj : objetos) {
            listaCadenas.add(obj.toString());
        }
    }

    public void volverPositivos() {
        for (int i = 0; i < listaEnteros.size(); i++) {
            if (listaEnteros.get(i) < 0) {
                listaEnteros.set(i, -listaEnteros.get(i));
            }
        }
    }

    public void organizarEnteros() {
        for (int i = 0; i < listaEnteros.size() - 1; i++) {
            for (int j = 0; j < listaEnteros.size() - 1 - i; j++) {
                if (listaEnteros.get(j) < listaEnteros.get(j + 1)) {
                    int temp = listaEnteros.get(j);
                    listaEnteros.set(j, listaEnteros.get(j + 1));
                    listaEnteros.set(j + 1, temp);
                }
            }
        }
    }

    public void organizarCadenas() {
        for (int i = 0; i < listaCadenas.size() - 1; i++) {
            for (int j = 0; j < listaCadenas.size() - 1 - i; j++) {
                if (listaCadenas.get(j).compareTo(listaCadenas.get(j + 1)) > 0) {
                    String temp = listaCadenas.get(j);
                    listaCadenas.set(j, listaCadenas.get(j + 1));
                    listaCadenas.set(j + 1, temp);
                }
            }
        }
    }

    public int contarApariciones(int valor) {
        int contador = 0;
        for (Integer num : listaEnteros) {
            if (num == valor) {
                contador++;
            }
        }
        return contador;
    }

    public int contarApariciones(String cadena) {
        int contador = 0;
        for (String str : listaCadenas) {
            if (str.equalsIgnoreCase(cadena)) {
                contador++;
            }
        }
        return contador;
    }

    public int contarEnterosRepetidos() {
        int repetidos = 0;
        List<Integer> yaContados = new ArrayList<>();
        
        for (int i = 0; i < listaEnteros.size(); i++) {
            int numeroActual = listaEnteros.get(i);
            
            boolean yaContado = false;
            for (int num : yaContados) {
                if (num == numeroActual) {
                    yaContado = true;
                    break;
                }
            }
            if (yaContado) {
                continue;
            }
         
            for (int j = i + 1; j < listaEnteros.size(); j++) {
                if (numeroActual == listaEnteros.get(j)) {
                    repetidos++;
                    yaContados.add(numeroActual);
                    break;
                }
            }
        }
        
        return repetidos;
    }
    public boolean compararArregloEnteros(int[] otroArreglo) {
        if (otroArreglo == null || otroArreglo.length != listaEnteros.size()) {
            return false;
        }
        for (int i = 0; i < listaEnteros.size(); i++) {
            if (listaEnteros.get(i) != otroArreglo[i]) {
                return false;
            }
        }
        return true;
    }

    public void generarEnteros(int cantidad, int minimo, int maximo) {
        listaEnteros.clear();
        for (int i = 0; i < cantidad; i++) {
            int rango = maximo - minimo + 1;
            int aleatorio = (int) (Math.random() * rango) + minimo;
            listaEnteros.add(aleatorio);
        }
    }
}