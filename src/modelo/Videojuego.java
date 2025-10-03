package modelo;

import java.time.LocalDate;

public class Videojuego {

    public enum Genero {
        ACCION, ESTRATEGIA, ROL, DEPORTE, SIMULACION
    }
    public enum Clasificacion {
        AAA, INDIE
    }
    private String nombre;
    private Genero genero;
    private Clasificacion clasificacion;
    private int precio;
    private LocalDate fechaLanzamiento;

}
