package ar.edu.utn.fra.metodologia2.refactoring.refactored;

public class Personaje {
    private final String nombre;
    private final double daño;
    private final Habilidad tipoHabilidad;

    public Personaje(String nombre, double daño, Habilidad tipoHabilidad) {
        this.nombre = nombre;
        this.daño = daño;
        this.tipoHabilidad = tipoHabilidad;
    }

    public String getNombre() {
        return nombre;
    }

    public double getDaño() {
        return daño;
    }

    public Habilidad getTipoHabilidad() {
        return tipoHabilidad;
    }

    public double calcularDañoEfectivo() {
        return tipoHabilidad.calcularDaño(daño);
    }
}

