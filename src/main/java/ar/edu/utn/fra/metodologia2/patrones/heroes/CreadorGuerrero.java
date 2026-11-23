package ar.edu.utn.fra.metodologia2.patrones.heroes;

/**
 * Estereotipo: <<ConcreteCreator>>
 * Factoría concreta para instanciar Guerreros.
 */
public class CreadorGuerrero extends CreadorHeroe {

    @Override
    public Heroe crearHeroe(DatosCreacionHeroe datos) {
        return new GuerreroHeroe(
                datos.nombre(),
                datos.apodo(),
                datos.fechaCreacion(),
                datos.poderAtaque(),
                datos.victorias(),
                datos.bonoPorVictoria()
        );
    }
}
