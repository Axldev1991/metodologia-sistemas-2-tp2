package ar.edu.utn.fra.metodologia2.patrones.heroes;

import java.time.LocalDate;

/**
 * Estereotipo: <<ConcreteProduct>>
 * Héroe de tipo Guerrero con atributos específicos: poder de ataque, victorias y bono por victoria.
 */
public class GuerreroHeroe extends Heroe {
    private final double poderAtaque;
    private final int victorias;
    private final double bonoPorVictoria;

    public GuerreroHeroe(String nombre, String apodo, LocalDate fechaCreacion,
                         double poderAtaque, int victorias, double bonoPorVictoria) {
        super(nombre, apodo, fechaCreacion);
        this.poderAtaque = poderAtaque;
        this.victorias = victorias;
        this.bonoPorVictoria = bonoPorVictoria;
    }

    public double getPoderAtaque() {
        return poderAtaque;
    }

    public int getVictorias() {
        return victorias;
    }

    public double getBonoPorVictoria() {
        return bonoPorVictoria;
    }

    @Override
    public double calcularPoderTotal() {
        return poderAtaque + (victorias * bonoPorVictoria);
    }

    @Override
    public String getDescripcionTipo() {
        return "Guerrero [Ataque: " + poderAtaque + ", Victorias: " + victorias + "]";
    }
}
