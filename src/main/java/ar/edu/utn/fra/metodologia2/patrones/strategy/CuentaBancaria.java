package ar.edu.utn.fra.metodologia2.patrones.strategy;

import java.math.BigDecimal;

/**
 * Entidad de dominio que representa una cuenta bancaria.
 */
public class CuentaBancaria {
    private final String cbu;
    private final String titular;
    private BigDecimal saldo;

    public CuentaBancaria(String cbu, String titular, BigDecimal saldoInicial) {
        if (saldoInicial == null || saldoInicial.compareTo(BigDecimal.ZERO) < 0) {
            throw new IllegalArgumentException("El saldo inicial no puede ser negativo ni nulo.");
        }
        this.cbu = cbu;
        this.titular = titular;
        this.saldo = saldoInicial;
    }

    public String getCbu() {
        return cbu;
    }

    public String getTitular() {
        return titular;
    }

    public BigDecimal getSaldo() {
        return saldo;
    }

    public void acreditar(BigDecimal monto) {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a acreditar debe ser mayor a cero.");
        }
        this.saldo = this.saldo.add(monto);
    }

    public void debitar(BigDecimal monto) {
        if (monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto a debitar debe ser mayor a cero.");
        }
        if (this.saldo.compareTo(monto) < 0) {
            throw new IllegalStateException("Saldo insuficiente en la cuenta " + cbu);
        }
        this.saldo = this.saldo.subtract(monto);
    }
}

