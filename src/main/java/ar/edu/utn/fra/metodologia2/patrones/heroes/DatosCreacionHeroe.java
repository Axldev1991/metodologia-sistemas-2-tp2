package ar.edu.utn.fra.metodologia2.patrones.heroes;

import java.time.LocalDate;

/**
 * Parámetro contenedor para la creación de héroes desacoplada.
 */
public record DatosCreacionHeroe(
        String nombre,
        String apodo,
        LocalDate fechaCreacion,
        double poderAtaque,
        int victorias,
        double bonoPorVictoria,
        double reservaMana
) {
    public static DatosCreacionHeroe paraGuerrero(String nombre, String apodo, double ataque, int victorias, double bono) {
        return new DatosCreacionHeroe(nombre, apodo, LocalDate.now(), ataque, victorias, bono, 0.0);
    }

    public static DatosCreacionHeroe paraMago(String nombre, String apodo, double mana) {
        return new DatosCreacionHeroe(nombre, apodo, LocalDate.now(), 0.0, 0, 0.0, mana);
    }
}
