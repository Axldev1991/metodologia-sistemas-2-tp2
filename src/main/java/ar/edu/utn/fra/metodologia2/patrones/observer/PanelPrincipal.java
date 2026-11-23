package ar.edu.utn.fra.metodologia2.patrones.observer;

import java.util.HashMap;
import java.util.Map;

/**
 * Estereotipo: <<ConcreteObserver>>
 * Panel principal de la interfaz gráfica: muestra los datos generales consolidados.
 */
public class PanelPrincipal implements ObservadorSensor {
    private final Map<String, Double> ultimasLecturas = new HashMap<>();

    @Override
    public void actualizar(Sensor sensor, Medicion medicion) {
        ultimasLecturas.put(medicion.tipoSensor(), medicion.valor());
    }

    public Double getUltimaLectura(String tipoSensor) {
        return ultimasLecturas.get(tipoSensor);
    }

    public int getCantidadSensoresMonitoreados() {
        return ultimasLecturas.size();
    }
}

