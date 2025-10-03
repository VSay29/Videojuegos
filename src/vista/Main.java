package vista;

import modelo.Plataforma;
import modelo.Videojuego;

import java.time.LocalDate;

public class Main {

    public static void main(String[] args) {
        Plataforma p1 = new Plataforma("PlayStation 5", 50000, 16384);
        Videojuego v = new Videojuego("Elden Ring", Videojuego.Genero.ROL, Videojuego.Clasificacion.AAA, 3000, LocalDate.of(2022, 2,25));
        p1.addJuego(v);
        v = new Videojuego("Stardew Valley", Videojuego.Genero.ROL, Videojuego.Clasificacion.INDIE, 7000, LocalDate.of(2016, 2,25));
        p1.addJuego(v);
        v = new Videojuego("Final Fantasy X", Videojuego.Genero.ROL, Videojuego.Clasificacion.INDIE, 6000, LocalDate.of(2001, 7,18));
        p1.addJuego(v);
        Plataforma p2 = new Plataforma("PC", 76180, 32664);
        v = new Videojuego("EA Sports FC 26", Videojuego.Genero.DEPORTE, Videojuego.Clasificacion.AAA, 2000, LocalDate.of(2020, 6,12));
        p2.addJuego(v);
        v = new Videojuego("Los Sims 4", Videojuego.Genero.SIMULACION, Videojuego.Clasificacion.AAA, 2000, LocalDate.of(2014, 9,2));
        p2.addJuego(v);

    }

}
