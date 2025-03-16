/**
 * Universidad del Valle de Guatemala
 * Departamento de Ciencia de la Computación
 * Programación Orientada a Objetos
 * 
 * Autor: Jorge Andrés Villeda Solís - 24932 
 * Fecha: 16/03/2025 
 */

import java.util.*;

/**
 * Interfaz que define la creación de un mapa para almacenar Pokémon.
 */
interface PokeMapa {
    /**
     * Crea y retorna un nuevo mapa para almacenar Pokémon.
     * 
     * @return Un mapa con clave String (nombre del Pokémon) y valor Poke (objeto Pokémon).
     */
    Map<String, Poke> createMap();
}

/**
 * Implementación de PokeMapa que utiliza un HashMap.
 * HashMap no mantiene ningún orden específico.
 */
class HashMapLibrary implements PokeMapa {
    @Override
    public Map<String, Poke> createMap() {
        return new HashMap<>();
    }
}

/**
 * Implementación de PokeMapa que utiliza un TreeMap.
 * TreeMap mantiene los elementos ordenados de acuerdo a las claves (orden natural o comparador).
 */
class TreeMapLibrary implements PokeMapa {
    @Override
    public Map<String, Poke> createMap() {
        return new TreeMap<>();
    }
}

/**
 * Implementación de PokeMapa que utiliza un LinkedHashMap.
 * LinkedHashMap mantiene el orden de inserción de los elementos.
 */
class LinkedHashMapLibrary implements PokeMapa {
    @Override
    public Map<String, Poke> createMap() {
        return new LinkedHashMap<>();
    }
}

/**
 * Fábrica que proporciona la implementación adecuada de PokeMapa 
 * según el tipo de mapa especificado por el usuario.
 */
class PokeMapaFactory {

    /**
     * Devuelve una implementación concreta de PokeMapa basada en el tipo seleccionado.
     * 
     * @param tipo Tipo de mapa a crear: "1" para HashMap, "2" para TreeMap, "3" para LinkedHashMap.
     * @return Una implementación concreta de la interfaz PokeMapa.
     * @throws IllegalArgumentException Si el tipo no es válido.
     */
    public static PokeMapa getPokeMapa(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "1" -> new HashMapLibrary();
            case "2" -> new TreeMapLibrary();
            case "3" -> new LinkedHashMapLibrary();
            default -> throw new IllegalArgumentException("Tipo de mapa no válido");
        };
    }
}

