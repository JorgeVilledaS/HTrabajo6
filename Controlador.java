package HTrabajo6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

public class Controlador {
    // Atributos
    private Map<String, Poke> mapaPokemon; // Mapa principal con todos los pokemon
    private List<Poke> coleccionUsuario; // Colección de pokemon del usuario

    // Constructor
    public Controlador() {
        // Inicializamos la colección del usuario por defecto como ArrayList
        coleccionUsuario = new ArrayList<>();
    }

    /**
     * Inicializa el mapa según la opción seleccionada por el usuario
     * @param opcionMapa 1 para HashMap, 2 para TreeMap, 3 para LinkedHashMap
     */
    public void inicializarMapa(int opcionMapa) {
        switch (opcionMapa) {
            case 1:
                mapaPokemon = new HashMap<>();
                break;
            case 2:
                mapaPokemon = new TreeMap<>();
                break;
            case 3:
                mapaPokemon = new LinkedHashMap<>();
                break;
            default:
                mapaPokemon = new HashMap<>(); // Valor por defecto
                break;
        }
    }

    /**
     * Lee los datos de pokemon desde un archivo y los almacena en el mapa
     * @param rutaArchivo ruta al archivo CSV con los datos
     * @return número de pokemon cargados o -1 si hubo error
     */
    public int leerArchivo(String rutaArchivo) {
        int contador = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(rutaArchivo))) {
            String linea;
            boolean primeraLinea = true;
            
            while ((linea = br.readLine()) != null) {
                // Saltar encabezados
                if (primeraLinea) {
                    primeraLinea = false;
                    continue;
                }
                
                // Procesar datos y crear objeto Poke
                Poke pokemon = crearPokemonDesdeCSV(linea);
                if (pokemon != null) {
                    mapaPokemon.put(pokemon.getnombre(), pokemon);
                    contador++;
                }
            }
            
            return contador;
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + e.getMessage());
            return -1;
        }
    }
    
    /**
     * Crea un objeto Poke a partir de una línea CSV
     * @param linea Línea del archivo CSV
     * @return Objeto Poke o null si hay error
     */
    private Poke crearPokemonDesdeCSV(String linea) {
        try {
            String[] datos = linea.split(",");
            // Validar que haya suficientes campos
            if (datos.length < 10) {
                return null;
            }
            
            // Extraer datos según el formato del CSV
            String nombre = datos[0].trim();
            int noPokedex = Integer.parseInt(datos[1].trim());
            String tipo1 = datos[2].trim();
            String tipo2 = datos[3].trim().equals("") ? null : datos[3].trim();
            String clasificacion = datos[4].trim();
            double altura = Double.parseDouble(datos[5].trim());
            double peso = Double.parseDouble(datos[6].trim());
            
            // Procesar habilidades
            List<String> habilidades = new ArrayList<>();
            if (datos.length > 7 && !datos[7].trim().isEmpty()) {
                String[] habilidadesArray = datos[7].trim().split(";");
                habilidades.addAll(Arrays.asList(habilidadesArray));
            }
            
            int gen = Integer.parseInt(datos[8].trim());
            boolean esLegendario = Boolean.parseBoolean(datos[9].trim());
            
            return new Poke(nombre, noPokedex, tipo1, tipo2, clasificacion, 
                    altura, peso, habilidades, gen, esLegendario);
        } catch (Exception e) {
            System.err.println("Error al procesar línea: " + linea + " - " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Agrega un pokemon a la colección del usuario
     * @param nombreAgregar Nombre del pokemon a agregar
     * @return true si se agregó, false si hubo un error
     */
    public boolean agregarPokemonAUsuario(String nombreAgregar) {
        // Verificar si existe en el mapa principal
        Poke pokemon = mapaPokemon.get(nombreAgregar);
        if (pokemon == null) {
            System.out.println("Error: El Pokémon " + nombreAgregar + " no existe en la base de datos.");
            return false;
        }
        
        // Agregar a la colección del usuario
        coleccionUsuario.add(pokemon);
        System.out.println("Pokémon " + nombreAgregar + " agregado a tu colección.");
        return true;
    }
    
    /**
     * Muestra los datos de un pokemon específico
     * @param nombreBuscar Nombre del pokemon a buscar
     * @return String con la información o mensaje de error
     */
    public String mostrarDatosPokemon(String nombreBuscar) {
        Poke pokemon = mapaPokemon.get(nombreBuscar);
        if (pokemon == null) {
            return "El Pokémon " + nombreBuscar + " no existe en la base de datos.";
        }
        
        return pokemon.toString();
    }
    
    /**
     * Muestra los pokemon del usuario ordenados por tipo1
     * @return Lista de strings con información de pokemon ordenados por tipo1
     */
    public List<String> mostrarColeccionUsuarioOrdenadaPorTipo() {
        List<String> resultado = new ArrayList<>();
        
        // Ordenar la colección por tipo1
        List<Poke> pokemonOrdenados = new ArrayList<>(coleccionUsuario);
        pokemonOrdenados.sort(Comparator.comparing(Poke::gettipo1));
        
        // Convertir a lista de strings para mostrar
        for (Poke pokemon : pokemonOrdenados) {
            resultado.add(pokemon.getnombre() + " - " + pokemon.gettipo1());
        }
        
        return resultado;
    }
    
    /**
     * Muestra todos los pokemon ordenados por tipo1
     * @return Lista de strings con información de pokemon ordenados por tipo1
     */
    public List<String> mostrarTodosOrdenadosPorTipo() {
        List<String> resultado = new ArrayList<>();
        
        // Convertir el mapa a lista para ordenar
        List<Poke> pokemonOrdenados = new ArrayList<>(mapaPokemon.values());
        pokemonOrdenados.sort(Comparator.comparing(Poke::gettipo1));
        
        // Convertir a lista de strings para mostrar
        for (Poke pokemon : pokemonOrdenados) {
            resultado.add(pokemon.getnombre() + " - " + pokemon.gettipo1());
        }
        
        return resultado;
    }
    
    /**
     * Busca pokemon que tienen una habilidad específica
     * @param habilidad Habilidad a buscar
     * @return Lista de nombres de pokemon que tienen la habilidad
     */
    public List<String> buscarPokemonPorHabilidad(String habilidad) {
        List<String> resultado = new ArrayList<>();
        
        for (Poke pokemon : mapaPokemon.values()) {
            if (pokemon.gethabilidades().contains(habilidad)) {
                resultado.add(pokemon.getnombre());
            }
        }
        
        return resultado;
    }
}