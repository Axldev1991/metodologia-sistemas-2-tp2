package ar.edu.utn.fra.metodologia2.patrones.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Estereotipo: <<ConcreteObserver>>
 * Panel de alertas: notifica cuando un valor supera un umbral crítico establecido.
 */
public class PanelAlertas implements ObservadorSensor {
    private final double umbralCritico;
    private final List<String> alertasGeneradas = new ArrayList<>();

    public PanelAlertas(double umbralCritico) {
        this.umbralCritico = umbralCritico;
    }

    @Override
    public void actualizar(Sensor sensor, Medicion medicion) {
        if (medicion.valor() > umbralCritico) {
            alertasGeneradas.add("¡ALERTA CRÍTICA! " + medicion.tipoSensor() + ": " + medicion.valor() + " " + medicion.unidad());
        }
    }

    public List<String> getAlertasGeneradas() {
        return List.copyOf(alertasGeneradas);
    }
}
