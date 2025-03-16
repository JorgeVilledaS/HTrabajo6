/**
 * Universidad del Valle de Guatemala
 * Departamento de Ciencia de la Computación
 * Programación Orientada a Objetos
 * 
 * Autor: Jorge Andrés Villeda Solís - 24932 
 * Fecha: 16/03/2025 
 */

import java.util.Scanner;

/**
 * Clase principal que contiene el método main para ejecutar la aplicación de gestión de Pokémon.
 * 
 * Permite al usuario seleccionar una implementación de Map (HashMap, TreeMap, LinkedHashMap),
 * cargar datos de Pokémon desde un archivo CSV y realizar diversas operaciones como agregar Pokémon
 * a su colección, consultar información, ordenar por tipo o buscar por habilidad.
 */
public class Main {

    /**
     * Método principal. Controla el flujo del programa, mostrando el menú y gestionando las opciones del usuario.
     * 
     * @param args Argumentos de línea de comandos (no utilizados).
     */
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controlador controlador = new Controlador();

        // Selección del tipo de Map que se usará para almacenar Pokémon
        System.out.println("Ingrese el número de la implementación de Map a utilizar:");
        System.out.println("1) HashMap");
        System.out.println("2) TreeMap");
        System.out.println("3) LinkedHashMap");
        int opcionMapa = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer de entrada

        // Inicialización del Map usando el patrón Factory
        controlador.inicializarMapa(opcionMapa);

        // Cargar datos desde el archivo CSV automáticamente
        System.out.println("Cargando datos de Pokémon desde el archivo predefinido...");
        int pokemonCargados = controlador.cargarDatosPokemon();
        
        if (pokemonCargados <= 0) {
            System.out.println("Error al cargar los datos. El programa se cerrará.");
            scanner.close();
            return;
        }

        // Menú de opciones principales
        boolean salir = false;
        while (!salir) {
            System.out.println("\nMenú de opciones:");
            System.out.println("1. Agregar un Pokémon a la colección");
            System.out.println("2. Mostrar los datos de un Pokémon");
            System.out.println("3. Mostrar la colección del usuario ordenada por tipo1");
            System.out.println("4. Mostrar todos los Pokémon existentes ordenados por tipo1");
            System.out.println("5. Buscar Pokémon por habilidad");
            System.out.println("6. Salir");
            System.out.print("Seleccione una opción: ");

            int opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    // Agregar Pokémon a la colección del usuario
                    System.out.print("Ingrese el nombre del Pokémon a agregar: ");
                    String nombreAgregar = scanner.nextLine();
                    controlador.agregarPokemonAUsuario(nombreAgregar);
                    break;
                case 2:
                    // Mostrar datos detallados de un Pokémon específico
                    System.out.print("Ingrese el nombre del Pokémon: ");
                    String nombreBuscar = scanner.nextLine();
                    System.out.println(controlador.mostrarDatosPokemon(nombreBuscar));
                    break;
                case 3:
                    // Mostrar colección del usuario ordenada por tipo1
                    System.out.println("Pokémon en la colección del usuario ordenados por tipo1:");
                    controlador.mostrarColeccionUsuarioOrdenadaPorTipo()
                               .forEach(System.out::println);
                    break;
                case 4:
                    // Mostrar todos los Pokémon existentes ordenados por tipo1
                    System.out.println("Todos los Pokémon ordenados por tipo1:");
                    controlador.mostrarTodosOrdenadosPorTipo()
                               .forEach(System.out::println);
                    break;
                case 5:
                    // Buscar Pokémon por habilidad específica
                    System.out.print("Ingrese la habilidad a buscar: ");
                    String habilidad = scanner.nextLine();
                    System.out.println("Pokémon con la habilidad " + habilidad + ":");
                    controlador.buscarPokemonPorHabilidad(habilidad)
                               .forEach(System.out::println);
                    break;
                case 6:
                    // Salir del programa
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    // Opción no válida
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        scanner.close();
    }
}


