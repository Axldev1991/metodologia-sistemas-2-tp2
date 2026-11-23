package ar.edu.utn.fra.metodologia2.patrones.strategy;

import java.math.BigDecimal;

/**
 * Estereotipo: <<ConcreteStrategy>>
 * Implementación de la operación de Depósito de dinero.
 * Secuencia: Recibe importe, verifica que sea válido y acredita en la cuenta.
 */
public class DepositoStrategy implements TransaccionStrategy {

    @Override
    public void ejecutar(BigDecimal monto, CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino) {
        if (cuentaOrigen == null) {
            throw new IllegalArgumentException("Se requiere una cuenta bancaria para efectuar el depósito.");
        }
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El importe a depositar debe ser mayor a cero.");
        }
        cuentaOrigen.acreditar(monto);
    }
}

