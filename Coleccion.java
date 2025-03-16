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
 * Clase que representa la colección personal de Pokémon del usuario.
 * 
 * Permite almacenar, ordenar y mostrar los Pokémon usando diferentes 
 * estructuras de colección: ArrayList, HashSet o TreeSet.
 */
class Coleccion {
    
    // Colección de Pokémon, puede ser ArrayList, HashSet o TreeSet
    private Collection<Poke> Pokes;

    /**
     * Constructor que inicializa la colección según el tipo especificado.
     *
     * @param tipo El tipo de colección:
     *             "1" para ArrayList (permite duplicados y mantiene orden de inserción),
     *             "2" para HashSet (no permite duplicados, sin orden),
     *             "3" para TreeSet (sin duplicados, ordenado por nombre de Pokémon).
     * @throws IllegalArgumentException si el tipo no es válido.
     */
    public Coleccion(String tipo) {
        switch (tipo.toLowerCase()) {
            case "1" -> Pokes = new ArrayList<>();
            case "2" -> Pokes = new HashSet<>();
            case "3" -> Pokes = new TreeSet<>(Comparator.comparing(Poke::getnombre));
            default -> throw new IllegalArgumentException("Tipo de colección no válido");
        }
    }

    /**
     * Agrega un Pokémon a la colección si no está presente.
     * 
     * @param Poke El Pokémon que se desea agregar.
     */
    public void addPoke(Poke Poke) {
        if (!Pokes.contains(Poke)) {
            Pokes.add(Poke);
            System.out.println("Pokémon agregado: " + Poke);
        } else {
            System.out.println("Este Pokémon ya está en tu colección mano");
        }
    }

    /**
     * Muestra todos los Pokémon de la colección ordenados por su tipo primario.
     * 
     * Nota: Internamente se copian a una lista y se ordenan con Comparator.
     */
    public void showPokesByType() {
        List<Poke> ordenados = new ArrayList<>(Pokes);
        ordenados.sort(Comparator.comparing(Poke::gettipo1));

        for (Poke poke : ordenados) {
            System.out.println(poke.getnombre() + " - " + poke.gettipo1());
        }
    }

    /**
     * Muestra todos los Pokémon de la colección tal como están almacenados 
     * (sin orden adicional, depende de la estructura usada).
     */
    public void showPokes() {
        for (Poke Poke : Pokes) {
            System.out.println(Poke);
        }
    }
}


