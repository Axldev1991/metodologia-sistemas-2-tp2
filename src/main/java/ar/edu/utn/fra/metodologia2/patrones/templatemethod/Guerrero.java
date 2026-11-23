package ar.edu.utn.fra.metodologia2.patrones.templatemethod;

/**
 * Estereotipo: <<ConcreteClass>>
 * Implementación concreta del combate para un Guerrero.
 * Secuencia: preparar arma, equipar armadura, acercarse al enemigo, ataque físico y evaluar resultado.
 */
public class Guerrero extends Personaje {

    public Guerrero(String nombre) {
        super(nombre);
    }

    @Override
    protected void prepararEquipamiento() {
        registrar(getNombre() + " prepara su espada y equipa la armadura pesada.");
    }

    @Override
    protected void posicionarse() {
        registrar(getNombre() + " se acerca corriendo cuerpo a cuerpo hacia el enemigo.");
    }

    @Override
    protected void ejecutarAtaque() {
        registrar(getNombre() + " ejecuta un potente ataque físico con su espada.");
    }

    @Override
    protected void celebrarVictoria() {
        registrar(getNombre() + " levanta su espada y lanza un grito de guerra triunfal.");
    }
}
