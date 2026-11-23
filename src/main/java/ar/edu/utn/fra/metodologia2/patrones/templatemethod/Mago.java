package ar.edu.utn.fra.metodologia2.patrones.templatemethod;

/**
 * Estereotipo: <<ConcreteClass>>
 * Implementación concreta del combate para un Mago.
 * Secuencia: seleccionar hechizo, preparar bastón, concentrar energía mágica, ataque mágico y evaluar resultado.
 */
public class Mago extends Personaje {

    public Mago(String nombre) {
        super(nombre);
    }

    @Override
    protected void prepararEquipamiento() {
        registrar(getNombre() + " selecciona un hechizo arcano y prepara su bastón mágico.");
    }

    @Override
    protected void posicionarse() {
        registrar(getNombre() + " mantiene la distancia y concentra energía mágica.");
    }

    @Override
    protected void ejecutarAtaque() {
        registrar(getNombre() + " ejecuta un ataque mágico invocando una bola de fuego.");
    }

    @Override
    protected boolean debeCelebrar() {
        // Ejemplo de personalización de hook: los magos sabios meditan en silencio en vez de festejar ruidosamente
        return false;
    }
}

