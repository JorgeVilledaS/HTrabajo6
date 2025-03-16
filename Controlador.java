package HTrabajo6;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.*;

public class Controlador {
    private Map<String, Poke> mapaPokemon;
    private List<Poke> coleccionUsuario;

    public Controlador() {
        coleccionUsuario = new ArrayList<>();
    }

    public void inicializarMapa(int opcionMapa) {
        switch (opcionMapa) { 
            case 1 -> mapaPokemon = new HashMap<>();
            case 2 -> mapaPokemon = new TreeMap<>();
            case 3 -> mapaPokemon = new LinkedHashMap<>();
            default -> mapaPokemon = new HashMap<>();
        }
    }

    public int cargarDatosPokemon() {
    int contador = 0;
    // Definimos el nombre del archivo directamente (sin ruta)
    String nombreArchivo = "pokemon_data_pokeapis.csv";
    
    try {
        // Intenta cargar como archivo desde el directorio actual
        BufferedReader br = new BufferedReader(new FileReader(nombreArchivo));
        String line = br.readLine(); // Leer encabezados si existen
            
        while ((line = br.readLine()) != null) {
            Poke pokemon = procesarDatosPokemon(line);
            if (pokemon != null) {
                mapaPokemon.put(pokemon.getnombre(), pokemon);
                contador++;
            }
        }
        br.close();
        System.out.println("Se cargaron " + contador + " Pokémon del archivo " + nombreArchivo);
        return contador;
    } catch (IOException e) {
        System.err.println("Error al leer el archivo: " + e.getMessage());
        System.err.println("Ruta actual: " + System.getProperty("user.dir"));
        return -1;
    }
    }

    private Poke procesarDatosPokemon(String datos) {
        try {
            String[] campos = parseCsvLine(datos);
            if (campos.length < 10) {
                System.err.println("Registro incompleto: " + datos);
                return null;
            }

            String nombre = campos[0].trim();
            int noPokedex = Integer.parseInt(campos[1].trim());
            String tipo1 = campos[2].trim();
            String tipo2 = campos[3].trim().isEmpty() ? null : campos[3].trim();
            String clasificacion = campos[4].trim();
            double altura = Double.parseDouble(campos[5].trim());
            double peso = Double.parseDouble(campos[6].trim());
            
            List<String> habilidades = new ArrayList<>(Arrays.asList(campos[7].replace("\"", "").split(", ")));
            int gen = Integer.parseInt(campos[8].trim());
            boolean esLegendario = campos[9].trim().equalsIgnoreCase("Yes");

            return new Poke(nombre, noPokedex, tipo1, tipo2, clasificacion, altura, peso, habilidades, gen, esLegendario);
        } catch (Exception e) {
            System.err.println("Error al procesar: " + datos + " - " + e.getMessage());
            return null;
        }
    }
    
    /**
     * Parsea una línea de texto CSV en un array de valores.
     * Maneja correctamente campos entrecomillados y comas dentro de los campos.
     * 
     * @param line Línea de texto CSV a parsear
     * @return Array con los valores extraídos de la línea
     */
    private String[] parseCsvLine(String line) {
        if (line == null) return new String[0];
        
        // Manejar campos entrecomillados y comas dentro de los campos
        boolean inQuotes = false;
        StringBuilder field = new StringBuilder();
        List<String> fields = new ArrayList<>();
        
        for (int i = 0; i < line.length(); i++) {
            char c = line.charAt(i);
            
            if (c == '"') {
                inQuotes = !inQuotes;
            } else if (c == ';' && !inQuotes) { // Cambiado a ';' para coincidir con el formato original
                fields.add(field.toString());
                field = new StringBuilder();
            } else {
                field.append(c);
            }
        }
        
        fields.add(field.toString());
        return fields.toArray(new String[0]);
    }
    
    /**
     * Agrega un pokemon a la colección del usuario
     * @param nombreAgregar Nombre del pokemon a agregar
     * @return true si se agregó, false si hubo un error
     */
    public boolean agregarPokemonAUsuario(String nombreAgregar) {
        Poke pokemon = mapaPokemon.get(nombreAgregar);
        if (pokemon == null) {
            System.out.println("Error: El Pokémon " + nombreAgregar + " no existe en la base de datos.");
            return false;
        }
        
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
        
        List<Poke> pokemonOrdenados = new ArrayList<>(coleccionUsuario);
        pokemonOrdenados.sort(Comparator.comparing(Poke::gettipo1));
        
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
        
        List<Poke> pokemonOrdenados = new ArrayList<>(mapaPokemon.values());
        pokemonOrdenados.sort(Comparator.comparing(Poke::gettipo1));
        
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