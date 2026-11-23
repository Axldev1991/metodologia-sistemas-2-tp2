package ar.edu.utn.fra.metodologia2.patrones.strategy;

import java.math.BigDecimal;

/**
 * Estereotipo: <<Strategy>>
 * Interfaz común para todas las operaciones bancarias disponibles en el sistema.
 */
public interface TransaccionStrategy {
    void ejecutar(BigDecimal monto, CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino);
}

