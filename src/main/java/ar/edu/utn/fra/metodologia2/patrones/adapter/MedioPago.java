package ar.edu.utn.fra.metodologia2.patrones.adapter;

import java.math.BigDecimal;

/**
 * Estereotipo: <<Target>>
 * Interfaz esperada por el cliente (Reserva) para pagar de forma homogénea.
 */
public interface MedioPago {
    void procesarPago(Huesped huesped, BigDecimal monto);
}
