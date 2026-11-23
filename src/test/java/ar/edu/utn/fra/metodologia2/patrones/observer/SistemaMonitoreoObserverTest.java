package ar.edu.utn.fra.metodologia2.patrones.observer;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests del Patrón Observer - Monitoreo Ambiental")
class SistemaMonitoreoObserverTest {

    private Sensor sensorTemperatura;
    private Sensor sensorCO2;
    private PanelPrincipal panelPrincipal;
    private PanelAlertas panelAlertasCO2;
    private RegistroConsola consola;

    @BeforeEach
    void setUp() {
        sensorTemperatura = new Sensor("Temperatura", "°C", 22.0);
        sensorCO2 = new Sensor("CO2", "ppm", 400.0);

        panelPrincipal = new PanelPrincipal();
        panelAlertasCO2 = new PanelAlertas(1000.0); // Alerta si CO2 supera 1000 ppm
        consola = new RegistroConsola();
    }

    @Test
    @DisplayName("Debe notificar automáticamente a todos los paneles suscritos ante una nueva medición")
    void testNotificacionAutomatica() {
        sensorTemperatura.suscribir(panelPrincipal);
        sensorTemperatura.suscribir(consola);

        sensorTemperatura.registrarNuevoValor(28.5);

        assertEquals(28.5, panelPrincipal.getUltimaLectura("Temperatura"));
        assertEquals(1, consola.getLogs().size());
        assertTrue(consola.getLogs().get(0).contains("28.5 °C"));
    }

    @Test
    @DisplayName("Panel de alertas debe activarse únicamente cuando se supere el umbral configurado")
    void testDisparoDeAlertasPorUmbral() {
        sensorCO2.suscribir(panelAlertasCO2);

        // Lectura normal: no dispara alerta
        sensorCO2.registrarNuevoValor(600.0);
        assertTrue(panelAlertasCO2.getAlertasGeneradas().isEmpty());

        // Lectura crítica: dispara alerta
        sensorCO2.registrarNuevoValor(1250.0);
        assertEquals(1, panelAlertasCO2.getAlertasGeneradas().size());
        assertTrue(panelAlertasCO2.getAlertasGeneradas().get(0).contains("¡ALERTA CRÍTICA!"));
    }

    @Test
    @DisplayName("Requisito clave: Debe permitir activar y desactivar suscripciones en tiempo real")
    void testSuscripcionYDesuscripcionEnTiempoReal() {
        sensorTemperatura.suscribir(panelPrincipal);
        sensorTemperatura.suscribir(consola);

        sensorTemperatura.registrarNuevoValor(24.0);
        assertEquals(1, consola.getLogs().size());

        // El usuario desactiva el registro de consola para el sensor de temperatura
        sensorTemperatura.desuscribir(consola);

        sensorTemperatura.registrarNuevoValor(30.0);

        // El panel principal sigue recibiendo actualizaciones, pero la consola ya no
        assertEquals(30.0, panelPrincipal.getUltimaLectura("Temperatura"));
        assertEquals(1, consola.getLogs().size(), "La consola no debió recibir la segunda actualización");
    }
}

