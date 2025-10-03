package modelo;

import java.util.HashSet;
import java.util.Set;

public class Plataforma {

    private String nombre;
    private int precio;
    private int RAM; // en MiB
    private Set<Videojuego> catalogo;

    public Plataforma(String nombre, int precio, int ram) {
        this.nombre = nombre;
        this.precio = precio;
        this.RAM = ram;
        this.catalogo = new HashSet<Videojuego>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public int getRAM() {
        return RAM;
    }

    public void setRAM(int ram) {
        this.RAM = ram;
    }

    public void addJuego(Videojuego v) {
        this.catalogo.add(v);
    }

    public Set<Videojuego> getCatalogo() {
        return this.catalogo;
    }
}
