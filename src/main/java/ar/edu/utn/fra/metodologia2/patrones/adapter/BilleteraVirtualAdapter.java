package ar.edu.utn.fra.metodologia2.patrones.adapter;

import java.math.BigDecimal;

/**
 * Estereotipo: <<Adapter>>
 * Adapta la clase existente BilleteraVirtual a la interfaz MedioPago.
 */
public class BilleteraVirtualAdapter implements MedioPago {
    private final BilleteraVirtual billeteraVirtual;

    public BilleteraVirtualAdapter(BilleteraVirtual billeteraVirtual) {
        if (billeteraVirtual == null) {
            throw new IllegalArgumentException("La billetera virtual no puede ser nula.");
        }
        this.billeteraVirtual = billeteraVirtual;
    }

    @Override
    public void procesarPago(Huesped huesped, BigDecimal monto) {
        boolean resultado = billeteraVirtual.pagarConEmail(huesped.getEmail(), monto);
        if (!resultado) {
            throw new IllegalStateException("Fallo en el procesamiento del pago con billetera virtual para email: " + huesped.getEmail());
        }
    }
}

