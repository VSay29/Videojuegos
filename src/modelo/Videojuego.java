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

    public Videojuego(String nombre, Genero genero, Clasificacion clasificacion, int precio, LocalDate fechaLanzamiento) {
        this.nombre = nombre;
        this.genero = genero;
        this.clasificacion = clasificacion;
        this.precio = precio;
        this.fechaLanzamiento = fechaLanzamiento;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Clasificacion getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(Clasificacion clasificacion) {
        this.clasificacion = clasificacion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public LocalDate getFechaLanzamiento() {
        return fechaLanzamiento;
    }

    public void setFechaLanzamiento(LocalDate fechaLanzamiento) {
        this.fechaLanzamiento = fechaLanzamiento;
    }
}
