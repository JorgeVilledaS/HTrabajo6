package HTrabajo6;

import java.util.List;

public class Poke {
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

    public String getnombre() {
        return nombre;
    }

    public int getnoPokedex() {
        return noPokedex;
    }

    public String gettipo1() {
        return tipo1;
    }

    public String gettipo2() {
        return tipo2;
    }

    public String getclasificacion() {
        return clasificacion;
    }

    public double getaltura() {
        return altura;
    }

    public double getpeso() {
        return peso;
    }

    public List<String> gethabilidades() {
        return habilidades;
    }

    public int getgen() {
        return gen;
    }

    public boolean isLegendary() {
        return esLegendario;
    }

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


