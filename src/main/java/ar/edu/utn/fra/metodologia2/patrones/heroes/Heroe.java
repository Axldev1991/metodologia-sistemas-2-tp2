package ar.edu.utn.fra.metodologia2.patrones.heroes;

import java.time.LocalDate;

/**
 * Estereotipo: <<Product>>
 * Clase base para todos los héroes del videojuego.
 */
public abstract class Heroe {
    private final String nombre;
    private final String apodo;
    private final LocalDate fechaCreacion;

    public Heroe(String nombre, String apodo, LocalDate fechaCreacion) {
        this.nombre = nombre;
        this.apodo = apodo;
        this.fechaCreacion = fechaCreacion != null ? fechaCreacion : LocalDate.now();
    }

    public String getNombre() {
        return nombre;
    }

    public String getApodo() {
        return apodo;
    }

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public abstract double calcularPoderTotal();
    public abstract String getDescripcionTipo();
}

