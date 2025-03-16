/**
 * Universidad del Valle de Guatemala
 * Departamento de Ciencia de la Computación
 * Programación Orientada a Objetos
 * 
 * Autor: Jorge Andrés Villeda Solís - 24932 
 * Fecha: 16/03/2025 
 */

import org.junit.*;
import static org.junit.Assert.*;
import java.util.List;

/**
 * Clase que contiene pruebas unitarias para verificar el correcto funcionamiento 
 * del controlador del sistema de gestión de Pokémon.
 * 
 * Se realizan pruebas sobre:
 * - La carga correcta de datos desde un archivo.
 * - La correcta adición de Pokémon a la colección del usuario.
 */
public class PruebasUnitarias {
    
    // Instancia del controlador sobre el que se realizan las pruebas
    private Controlador controlador;
    
    /**
     * Método que se ejecuta antes de cada prueba.
     * Inicializa el controlador y el mapa usando HashMap.
     */
    @Before
    public void setUp() {
        controlador = new Controlador();
        controlador.inicializarMapa(1); // Usé HashMap para estas pruebas
    }
    
    /**
     * Prueba que verifica la correcta lectura del archivo de Pokémon 
     * y el almacenamiento de los datos en el mapa.
     */
    @Test 
    public void testLecturaArchivo() {
        // Cargamos los datos de Pokémon
        int cantidadPokemon = controlador.cargarDatosPokemon();
        
        // Verificamos que se haya cargado al menos un Pokémon
        assertTrue("Debería cargar al menos un Pokémon", cantidadPokemon > 0);
        
        // Verificamos que ciertos Pokémon específicos existan en el mapa
        assertNotNull("Debería existir Metagross", controlador.mostrarDatosPokemon("Metagross"));
        assertNotNull("Debería existir Snom", controlador.mostrarDatosPokemon("Snom"));
        assertNotNull("Debería existir Blitzle", controlador.mostrarDatosPokemon("Blitzle"));
        
        // Verificamos que un Pokémon inexistente no se encuentre en el mapa
        assertTrue("Un Pokémon inexistente no debería estar en el mapa", 
                controlador.mostrarDatosPokemon("Arturito").contains("no existe"));
    }
    
    /**
     * Prueba que verifica la correcta adición de Pokémon a la colección del usuario.
     */
    @Test
    public void testAgregarPokemonAColeccion() {
        // Cargamos los datos de Pokémon para que haya pokémon disponibles
        controlador.cargarDatosPokemon();
        
        // Intentamos agregar un Pokémon válido a la colección del usuario
        boolean resultado1 = controlador.agregarPokemonAUsuario("Xerneas");
        assertTrue("Debería poder agregar Xerneas", resultado1);
        
        // Verificamos que el Pokémon fue efectivamente agregado revisando la colección
        List<String> coleccion = controlador.mostrarColeccionUsuarioOrdenadaPorTipo();
        boolean encontrado = false;
        for (String pokemon : coleccion) {
            if (pokemon.contains("Xerneas")) {
                encontrado = true;
                break;
            }
        }
        assertTrue("Xerneas debería estar en la colección", encontrado);
        
        // Intentamos agregar un Pokémon que no existe
        boolean resultado2 = controlador.agregarPokemonAUsuario("PokemonInexistente");
        assertFalse("No debería poder agregar un Pokémon inexistente", resultado2);
        
        // Verificamos que solo un Pokémon fue agregado a la colección
        int tamanoEsperado = 1; // Solo Xerneas debería estar
        assertEquals("Debería haber solo un Pokémon en la colección", 
                tamanoEsperado, coleccion.size());
    }
}
