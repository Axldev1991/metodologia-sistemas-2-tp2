package ar.edu.utn.fra.metodologia2.patrones.heroes;

/**
 * Estereotipo: <<ConcreteCreator>>
 * Factoría concreta para instanciar Magos.
 */
public class CreadorMago extends CreadorHeroe {

    @Override
    public Heroe crearHeroe(DatosCreacionHeroe datos) {
        return new MagoHeroe(
                datos.nombre(),
                datos.apodo(),
                datos.fechaCreacion(),
                datos.reservaMana()
        );
    }
}

