package ar.edu.utn.fra.metodologia2.patrones.observer;

/**
 * Estereotipo: <<Observer>>
 * Interfaz que deben implementar todos los paneles visuales y registradores
 * para recibir notificaciones automáticas de los sensores.
 */
public interface ObservadorSensor {
    void actualizar(Sensor sensor, Medicion medicion);
}

