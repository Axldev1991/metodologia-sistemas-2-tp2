package ar.edu.utn.fra.metodologia2.patrones.heroes;

/**
 * Estereotipo: <<Creator>>
 * Define el método fábrica abstracto (Factory Method).
 */
public abstract class CreadorHeroe {
    public abstract Heroe crearHeroe(DatosCreacionHeroe datos);
}
