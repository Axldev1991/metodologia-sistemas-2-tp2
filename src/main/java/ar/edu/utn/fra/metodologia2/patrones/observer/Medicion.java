package ar.edu.utn.fra.metodologia2.patrones.observer;

/**
 * Objeto de valor que representa una lectura enviada por un sensor ambiental.
 */
public record Medicion(String tipoSensor, double valor, String unidad) {
}
