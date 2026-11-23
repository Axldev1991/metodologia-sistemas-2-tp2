package ar.edu.utn.fra.metodologia2.patrones.strategy;

import java.math.BigDecimal;

/**
 * Estereotipo: <<Context>>
 * Sistema de gestión de transacciones bancarias.
 * Mantiene la referencia a la estrategia seleccionada y permite cambiarla
 * en cualquier momento antes de confirmar la transacción.
 */
public class GestorTransacciones {
    private TransaccionStrategy estrategia;
    private BigDecimal monto;
    private CuentaBancaria cuentaOrigen;
    private CuentaBancaria cuentaDestino;

    public GestorTransacciones() {
    }

    public GestorTransacciones(TransaccionStrategy estrategiaInicial) {
        this.estrategia = estrategiaInicial;
    }

    public void setEstrategia(TransaccionStrategy estrategia) {
        this.estrategia = estrategia;
    }

    public TransaccionStrategy getEstrategia() {
        return estrategia;
    }

    public void prepararTransaccion(BigDecimal monto, CuentaBancaria cuentaOrigen, CuentaBancaria cuentaDestino) {
        this.monto = monto;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
    }

    public void confirmarTransaccion() {
        if (this.estrategia == null) {
            throw new IllegalStateException("No se ha seleccionado ningún tipo de transacción.");
        }
        this.estrategia.ejecutar(this.monto, this.cuentaOrigen, this.cuentaDestino);
    }
}

