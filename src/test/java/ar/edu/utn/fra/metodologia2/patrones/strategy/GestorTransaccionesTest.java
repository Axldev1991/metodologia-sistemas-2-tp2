package ar.edu.utn.fra.metodologia2.patrones.strategy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests del Patrón Strategy - Transacciones Bancarias")
class GestorTransaccionesTest {

    private CuentaBancaria cuentaA;
    private CuentaBancaria cuentaB;
    private GestorTransacciones gestor;

    @BeforeEach
    void setUp() {
        cuentaA = new CuentaBancaria("CBU-001", "Axel Castellano", new BigDecimal("10000.00"));
        cuentaB = new CuentaBancaria("CBU-002", "Saulo Fernandez", new BigDecimal("5000.00"));
        gestor = new GestorTransacciones();
    }

    @Test
    @DisplayName("Debe ejecutar un depósito acreditando el monto correctamente")
    void testDepositoExitoso() {
        gestor.setEstrategia(new DepositoStrategy());
        gestor.prepararTransaccion(new BigDecimal("2500.00"), cuentaA, null);
        gestor.confirmarTransaccion();

        assertEquals(new BigDecimal("12500.00"), cuentaA.getSaldo());
    }

    @Test
    @DisplayName("Debe ejecutar una extracción debitando el saldo si es suficiente")
    void testExtraccionExitosa() {
        gestor.setEstrategia(new ExtraccionStrategy());
        gestor.prepararTransaccion(new BigDecimal("4000.00"), cuentaA, null);
        gestor.confirmarTransaccion();

        assertEquals(new BigDecimal("6000.00"), cuentaA.getSaldo());
    }

    @Test
    @DisplayName("Debe lanzar excepción si se intenta extraer más saldo del disponible")
    void testExtraccionSaldoInsuficiente() {
        gestor.setEstrategia(new ExtraccionStrategy());
        gestor.prepararTransaccion(new BigDecimal("15000.00"), cuentaA, null);

        assertThrows(IllegalStateException.class, () -> gestor.confirmarTransaccion());
        assertEquals(new BigDecimal("10000.00"), cuentaA.getSaldo(), "El saldo no debe verse afectado ante un fallo");
    }

    @Test
    @DisplayName("Debe ejecutar una transferencia entre cuentas debitando origen y acreditando destino")
    void testTransferenciaExitosa() {
        gestor.setEstrategia(new TransferenciaStrategy());
        gestor.prepararTransaccion(new BigDecimal("3000.00"), cuentaA, cuentaB);
        gestor.confirmarTransaccion();

        assertEquals(new BigDecimal("7000.00"), cuentaA.getSaldo());
        assertEquals(new BigDecimal("8000.00"), cuentaB.getSaldo());
    }

    @Test
    @DisplayName("Requisito clave: Debe permitir cambiar de estrategia en tiempo de ejecución antes de confirmar")
    void testCambioDeEstrategiaAntesDeConfirmar() {
        // El usuario inicialmente selecciona Extracción
        gestor.setEstrategia(new ExtraccionStrategy());
        gestor.prepararTransaccion(new BigDecimal("2000.00"), cuentaA, cuentaB);

        // Antes de confirmar, cambia de opinión y selecciona Transferencia
        gestor.setEstrategia(new TransferenciaStrategy());
        gestor.confirmarTransaccion();

        // Se verifica que se ejecutó la transferencia y NO la extracción simple
        assertEquals(new BigDecimal("8000.00"), cuentaA.getSaldo(), "Se debitó el origen");
        assertEquals(new BigDecimal("7000.00"), cuentaB.getSaldo(), "Se acreditó en destino gracias al cambio de estrategia");
    }
}

