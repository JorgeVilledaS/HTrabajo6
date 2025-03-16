/**
 * Universidad del Valle de Guatemala
 * Departamento de Ciencia de la Computación
 * Programación Orientada a Objetos
 * 
 * Autor: Jorge Andrés Villeda Solís - 24932 
 * Fecha: 16/03/2025
 * 
 * Clase que representa un Pokémon con sus características principales.
 */

import java.util.List;

public class Poke {

    // Atributos de un Pokémon
    private String nombre;
    private int noPokedex;
    private String tipo1;
    private String tipo2;
    private String clasificacion;
    private double altura; // en metros
    private double peso; // en kilogramos
    private List<String> habilidades;
    private int gen;
    private boolean esLegendario;

    /**
     * Constructor para inicializar un objeto Pokémon.
    * 
    * @param nombre          Nombre del Pokémon.
    * @param noPokedex       Número en la Pokédex.
    * @param tipo1           Primer tipo del Pokémon.
    * @param tipo2           Segundo tipo del Pokémon (puede ser null).
    * @param clasificacion   Clasificación del Pokémon.
    * @param altura          Altura en metros.
    * @param peso            Peso en kilogramos.
    * @param habilidades     Lista de habilidades que posee el Pokémon.
    * @param gen             Generación a la que pertenece.
    * @param esLegendario    Indica si el Pokémon es legendario.
    */
    public Poke(String nombre, int noPokedex, String tipo1, String tipo2, String clasificacion, 
                double altura, double peso, List<String> habilidades, int gen, boolean esLegendario) {
        this.nombre = nombre;
        this.noPokedex = noPokedex;
        this.tipo1 = tipo1;
        this.tipo2 = tipo2;
        this.clasificacion = clasificacion;
        this.altura = altura;
        this.peso = peso;
        this.habilidades = habilidades;
        this.gen = gen;
        this.esLegendario = esLegendario;
    }

    /**
     * Obtiene el nombre del Pokémon.
    * @return Nombre del Pokémon.
    */
    public String getnombre() {
        return nombre;
    }

    /**
     * Obtiene el número de la Pokédex.
    * @return Número de Pokédex.
    */
    public int getnoPokedex() {
        return noPokedex;
    }

    /**
     * Obtiene el primer tipo del Pokémon.
    * @return Primer tipo.
    */
    public String gettipo1() {
        return tipo1;
    }

    /**
     * Obtiene el segundo tipo del Pokémon.
    * @return Segundo tipo, puede ser null si no tiene.
    */
    public String gettipo2() {
        return tipo2;
    }

    /**
     * Obtiene la clasificación del Pokémon.
    * @return Clasificación.
    */
    public String getclasificacion() {
        return clasificacion;
    }

    /**
     * Obtiene la altura del Pokémon en metros.
    * @return Altura en metros.
    */
    public double getaltura() {
        return altura;
    }

    /**
     * Obtiene el peso del Pokémon en kilogramos.
    * @return Peso en kilogramos.
    */
    public double getpeso() {
        return peso;
    }

    /**
     * Obtiene la lista de habilidades del Pokémon.
    * @return Lista de habilidades.
    */
    public List<String> gethabilidades() {
        return habilidades;
    }

    /**
     * Obtiene la generación a la que pertenece el Pokémon.
    * @return Generación.
    */
    public int getgen() {
        return gen;
    }

    /**
     * Indica si el Pokémon es legendario.
    * @return true si es legendario, false en caso contrario.
    */
    public boolean isLegendary() {
        return esLegendario;
    }

    /**
     * Representación textual del Pokémon.
    * @return Cadena con los datos relevantes del Pokémon.
    */
    @Override
    public String toString() {
        return "Poke{" +
                "nombre='" + nombre + '\'' +
                ", noPokedex=" + noPokedex +
                ", tipo1='" + tipo1 + '\'' +
                ", tipo2='" + (tipo2 != null ? tipo2 : "None") + '\'' +
                ", clasificacion='" + clasificacion + '\'' +
                ", altura=" + altura + "m" +
                ", peso=" + peso + "kg" +
                ", habilidades=" + habilidades +
                ", gen=" + gen +
                ", legendary=" + esLegendario +
                '}';
    }
}
 