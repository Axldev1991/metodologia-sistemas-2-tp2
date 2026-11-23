package ar.edu.utn.fra.metodologia2.patrones.strategy;

import java.math.BigDecimal;

/**
 * Estereotipo: <<ConcreteStrategy>>
 * Implementación de la operación de Transferencia entre cuentas.
 * Secuencia: Valida importe, verifica saldo de cuenta origen, debita de origen y acredita en destino.
 */
public class TransferenciaStrategy implements TransaccionStrategy {

    @Override
    public void ejecutar(BigDecimal monto, CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino) {
        if (cuentaOrigen == null || cuentaDestino == null) {
            throw new IllegalArgumentException("Se requieren cuentas de origen y destino para transferir.");
        }
        if (cuentaOrigen.getCbu().equals(cuentaDestino.getCbu())) {
            throw new IllegalArgumentException("No se puede transferir a la misma cuenta de origen.");
        }
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El importe a transferir debe ser mayor a cero.");
        }
        if (cuentaOrigen.getSaldo().compareTo(monto) < 0) {
            throw new IllegalStateException("Saldo insuficiente en la cuenta de origen para transferir.");
        }
        cuentaOrigen.debitar(monto);
        cuentaDestino.acreditar(monto);
    }
}
