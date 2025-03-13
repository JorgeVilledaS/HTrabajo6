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
    public static PokeMapa getPokeMapa(String tipo) {
        return switch (tipo.toLowerCase()) {
            case "1" -> new HashMapLibrary();
            case "2" -> new TreeMapLibrary();
            case "3" -> new LinkedHashMapLibrary();
            default -> throw new IllegalArgumentException("Tipo de mapa no válido");
        };
    }
}

