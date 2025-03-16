package HTrabajo6;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Controlador controlador = new Controlador();

        // Selección del tipo de Map
        System.out.println("Ingrese el numero de la implementación de Map a utilizar:");
        System.out.println("1) HashMap");
        System.out.println("2) TreeMap");
        System.out.println("3) LinkedHashMap");
        int opcionMapa = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        // Inicializar el Map usando el Factory
        controlador.inicializarMapa(opcionMapa);

        // Cargar datos desde el archivo CSV automáticamente
        System.out.println("Cargando datos de Pokémon desde el archivo predefinido...");
        int pokemonCargados = controlador.cargarDatosPokemon();
        
        if (pokemonCargados <= 0) {
            System.out.println("Error al cargar los datos. El programa se cerrará.");
            scanner.close();
            return;
        }

        // Menú de opciones
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
                    System.out.print("Ingrese el nombre del Pokémon a agregar: ");
                    String nombreAgregar = scanner.nextLine();
                    controlador.agregarPokemonAUsuario(nombreAgregar);
                    break;
                case 2:
                    System.out.print("Ingrese el nombre del Pokémon: ");
                    String nombreBuscar = scanner.nextLine();
                    System.out.println(controlador.mostrarDatosPokemon(nombreBuscar));
                    break;
                case 3:
                    System.out.println("Pokémon en la colección del usuario ordenados por tipo1:");
                    controlador.mostrarColeccionUsuarioOrdenadaPorTipo()
                               .forEach(System.out::println);
                    break;
                case 4:
                    System.out.println("Todos los Pokémon ordenados por tipo1:");
                    controlador.mostrarTodosOrdenadosPorTipo()
                               .forEach(System.out::println);
                    break;
                case 5:
                    System.out.print("Ingrese la habilidad a buscar: ");
                    String habilidad = scanner.nextLine();
                    System.out.println("Pokémon con la habilidad " + habilidad + ":");
                    controlador.buscarPokemonPorHabilidad(habilidad)
                               .forEach(System.out::println);
                    break;
                case 6:
                    salir = true;
                    System.out.println("Saliendo del programa...");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        }

        scanner.close();
    }
}

