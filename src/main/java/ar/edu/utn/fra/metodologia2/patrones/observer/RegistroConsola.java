package ar.edu.utn.fra.metodologia2.patrones.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Estereotipo: <<ConcreteObserver>>
 * Registro de consola: imprime y almacena el historial de todos los cambios detectados.
 */
public class RegistroConsola implements ObservadorSensor {
    private final List<String> logs = new ArrayList<>();

    @Override
    public void actualizar(Sensor sensor, Medicion medicion) {
        logs.add("[LOG CONSOLA] " + medicion.tipoSensor() + " actualizado a " + medicion.valor() + " " + medicion.unidad());
    }

    public List<String> getLogs() {
        return List.copyOf(logs);
    }
}

