package ar.edu.utn.fra.metodologia2.patrones.heroes;

import java.time.LocalDate;

/**
 * Estereotipo: <<ConcreteProduct>>
 * Héroe de tipo Mago con atributo específico: reserva de maná.
 */
public class MagoHeroe extends Heroe {
    private final double reservaMana;

    public MagoHeroe(String nombre, String apodo, LocalDate fechaCreacion, double reservaMana) {
        super(nombre, apodo, fechaCreacion);
        this.reservaMana = reservaMana;
    }

    public double getReservaMana() {
        return reservaMana;
    }

    @Override
    public double calcularPoderTotal() {
        return reservaMana * 1.5;
    }

    @Override
    public String getDescripcionTipo() {
        return "Mago [Reserva Maná: " + reservaMana + "]";
    }
}
