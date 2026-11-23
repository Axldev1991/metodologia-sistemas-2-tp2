package ar.edu.utn.fra.metodologia2.patrones.strategy;

import java.math.BigDecimal;

/**
 * Estereotipo: <<ConcreteStrategy>>
 * Implementación de la operación de Extracción de dinero.
 * Secuencia: Valida importe, verifica saldo suficiente y debita.
 */
public class ExtraccionStrategy implements TransaccionStrategy {

    @Override
    public void ejecutar(BigDecimal monto, CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino) {
        if (cuentaOrigen == null) {
            throw new IllegalArgumentException("Se requiere una cuenta bancaria para efectuar la extracción.");
        }
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El importe solicitado debe ser mayor a cero.");
        }
        if (cuentaOrigen.getSaldo().compareTo(monto) < 0) {
            throw new IllegalStateException("Saldo insuficiente para realizar la extracción solicitada.");
        }
        cuentaOrigen.debitar(monto);
    }
}

