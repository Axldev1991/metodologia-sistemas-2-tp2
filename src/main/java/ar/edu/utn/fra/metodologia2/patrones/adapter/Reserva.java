package ar.edu.utn.fra.metodologia2.patrones.adapter;

import java.math.BigDecimal;

/**
 * Estereotipo: <<Client>>
 * Representa la reserva de hotel solicitada en la consigna.
 */
public class Reserva {
    private final Huesped huesped;
    private final BigDecimal montoTotal;
    private boolean pagada;

    public Reserva(Huesped huesped, BigDecimal montoTotal) {
        if (huesped == null) {
            throw new IllegalArgumentException("La reserva debe tener un huésped asignado.");
        }
        if (montoTotal == null || montoTotal.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("El monto total de la reserva debe ser mayor a cero.");
        }
        this.huesped = huesped;
        this.montoTotal = montoTotal;
        this.pagada = false;
    }

    public Huesped getHuesped() {
        return huesped;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public boolean isPagada() {
        return pagada;
    }

    public void pagarCon(MedioPago medio) {
        if (medio == null) {
            throw new IllegalArgumentException("Debe especificarse un medio de pago válido.");
        }
        medio.procesarPago(this.huesped, this.montoTotal);
        this.pagada = true;
    }
}
