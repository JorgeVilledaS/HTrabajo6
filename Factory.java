package HTrabajo6;

import java.util.*;

interface PokeMapa {
    Map<String, Poke> createMap();
}

class HashMapLibrary implements PokeMapa {
    @Override
    public Map<String, Poke> createMap() {
        return new HashMap<>();
    }
}

class TreeMapLibrary implements PokeMapa {
    @Override
    public Map<String, Poke> createMap() {
        return new TreeMap<>();
    }
}

class LinkedHashMapLibrary implements PokeMapa {
    @Override
    public Map<String, Poke> createMap() {
        return new LinkedHashMap<>();
    }
}

class PokeMapaFactory {
    public static PokeMapa getPokeMapa(String type) {
        return switch (type.toLowerCase()) {
            case "hashmap" -> new HashMapLibrary();
            case "treemap" -> new TreeMapLibrary();
            case "linkedhashmap" -> new LinkedHashMapLibrary();
            default -> throw new IllegalArgumentException("Tipo de mapa no válido");
        };
    }
}

