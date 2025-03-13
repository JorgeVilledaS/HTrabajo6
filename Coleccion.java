package HTrabajo6;

import java.util.*;

class Coleccion {
    private Collection<Poke> Pokes;

    public Coleccion(String tipo) {
        switch (tipo.toLowerCase()) {
            case "1" -> Pokes = new ArrayList<>();
            case "2" -> Pokes = new HashSet<>();
            case "3" -> Pokes = new TreeSet<>(Comparator.comparing(Poke::getnombre));
            default -> throw new IllegalArgumentException("Tipo de colección no válido");
        }
    }

    public void addPoke(Poke Poke) {
        if (!Pokes.contains(Poke)) {
            Pokes.add(Poke);
            System.out.println("Pokémon agregado: " + Poke);
        } else {
            System.out.println("Este Pokémon ya está en tu colección mano");
        }
    }

    public void showPokes() {
        for (Poke Poke : Pokes) {
            System.out.println(Poke);
        }
    }
}

