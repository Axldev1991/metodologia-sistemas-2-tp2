package ar.edu.utn.fra.metodologia2.patrones.templatemethod;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * Estereotipo: <<AbstractClass>>
 * Define el método plantilla (template method) con el esqueleto del algoritmo de combate.
 */
public abstract class Personaje {
    private final String nombre;
    private final List<String> registroAcciones;

    public Personaje(String nombre) {
        this.nombre = nombre;
        this.registroAcciones = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public List<String> getRegistroAcciones() {
        return Collections.unmodifiableList(registroAcciones);
    }

    protected void registrar(String accion) {
        this.registroAcciones.add(accion);
    }

    /**
     * MÉTODO PLANTILLA (Template Method).
     * Es 'final' para garantizar que las subclases NO puedan redefinir el esqueleto del algoritmo.
     * Aplica Inversión de Control: la superclase dirige el flujo de ejecución paso a paso.
     */
    public final void pelear() {
        prepararEquipamiento();
        posicionarse();
        ejecutarAtaque();
        evaluarResultadoCombate();
        if (debeCelebrar()) { // Operación de enganche (Hook)
            celebrarVictoria();
        }
    }

    // Pasos primitivos abstractos (obligatorios para las subclases)
    protected abstract void prepararEquipamiento();
    protected abstract void posicionarse();
    protected abstract void ejecutarAtaque();

    // Paso común con comportamiento por defecto
    protected void evaluarResultadoCombate() {
        registrar(nombre + " evalúa el resultado del combate.");
    }

    // OPERACIONES DE ENGANCHE (HOOKS)
    protected boolean debeCelebrar() {
        return true;
    }

    protected void celebrarVictoria() {
        registrar(nombre + " festeja la victoria en el combate.");
    }
}
