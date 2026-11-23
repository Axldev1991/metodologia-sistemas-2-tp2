package ar.edu.utn.fra.metodologia2.refactoring.refactored;

import java.util.ArrayList;
import java.util.List;

/**
 * Clase refactorizada libre de malos olores (bad smells).
 * Aplica polimorfismo, respetando el principio Open/Closed y Tell, Don't Ask.
 */
public class Videojuego {
    private final List<Personaje> personajes;

    public Videojuego() {
        this.personajes = new ArrayList<>();
    }

    public void agregarPersonaje(Personaje p) {
        this.personajes.add(p);
    }

    public Personaje personajeConMasDaño() {
        Personaje temp = null;
        double max = 0;

        for (Personaje p : personajes) {
            double daño = p.calcularDañoEfectivo();
            if (daño > max) {
                temp = p;
                max = daño;
            }
        }
        return temp;
    }

    public String imprimirInfo(Personaje p) {
        StringBuilder sb = new StringBuilder();
        sb.append(p.getNombre()).append(" tiene como daño ").append(p.getDaño()).append("\n");
        // POLIMORFISMO PURO: Sin un solo if/else inspeccionando clases
        sb.append(p.getTipoHabilidad().getDescripcionTipo()).append("\n");
        sb.append(p.getTipoHabilidad().getEfectoFuerza());
        return sb.toString();
    }
}

