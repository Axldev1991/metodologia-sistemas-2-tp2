package ar.edu.utn.fra.metodologia2.patrones.adapter;

import java.math.BigDecimal;

/**
 * Estereotipo: <<Adapter>>
 * Adapta la clase existente TarjetaCredito a la interfaz MedioPago.
 */
public class TarjetaCreditoAdapter implements MedioPago {
    private final TarjetaCredito tarjetaCredito;

    public TarjetaCreditoAdapter(TarjetaCredito tarjetaCredito) {
        if (tarjetaCredito == null) {
            throw new IllegalArgumentException("La tarjeta de crédito no puede ser nula.");
        }
        this.tarjetaCredito = tarjetaCredito;
    }

    @Override
    public void procesarPago(Huesped huesped, BigDecimal monto) {
        if (!tarjetaCredito.validarLimite(huesped.getNumeroTarjeta(), monto)) {
            throw new IllegalStateException("Límite insuficiente en la tarjeta de crédito para el huésped: " + huesped.getNombre());
        }
        if (!tarjetaCredito.autorizarCargo(huesped.getNumeroTarjeta(), monto)) {
            throw new IllegalStateException("No se pudo autorizar el cargo en la tarjeta de crédito.");
        }
    }
}

