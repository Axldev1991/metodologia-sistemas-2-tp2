package ar.edu.utn.fra.metodologia2.patrones.observer;

import java.util.ArrayList;
import java.util.List;

/**
 * Estereotipo: <<Subject>>
 * Sujeto observable que mide una variable ambiental (temperatura, humedad, CO2, luminosidad)
 * y notifica automáticamente a los observadores suscritos ante cada cambio de valor.
 */
public class Sensor {
    private final String tipo;
    private final String unidad;
    private double valorActual;
    private final List<ObservadorSensor> observadores;

    public Sensor(String tipo, String unidad, double valorInicial) {
        this.tipo = tipo;
        this.unidad = unidad;
        this.valorActual = valorInicial;
        this.observadores = new ArrayList<>();
    }

    public String getTipo() {
        return tipo;
    }

    public String getUnidad() {
        return unidad;
    }

    public double getValorActual() {
        return valorActual;
    }

    public void suscribir(ObservadorSensor observador) {
        if (observador != null && !observadores.contains(observador)) {
            observadores.add(observador);
        }
    }

    public void desuscribir(ObservadorSensor observador) {
        observadores.remove(observador);
    }

    public void registrarNuevoValor(double nuevoValor) {
        this.valorActual = nuevoValor;
        notificarObservadores();
    }

    protected void notificarObservadores() {
        Medicion medicion = new Medicion(this.tipo, this.valorActual, this.unidad);
        for (ObservadorSensor obs : new ArrayList<>(observadores)) {
            obs.actualizar(this, medicion);
        }
    }

    public int getCantidadObservadores() {
        return observadores.size();
    }
}

