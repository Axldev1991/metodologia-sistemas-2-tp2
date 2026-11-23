package ar.edu.utn.fra.metodologia2.patrones.adapter;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests del Patrón Adapter - Pagos de Hotel")
class ReservaPagoAdapterTest {

    private Huesped huesped;
    private Reserva reserva;

    @BeforeEach
    void setUp() {
        huesped = new Huesped("Axel", "Castellano", "axel@utn.edu.ar", "4509-1234-5678-9012");
        reserva = new Reserva(huesped, new BigDecimal("45000.00"));
    }

    @Test
    @DisplayName("Debe pagar la reserva exitosamente usando TarjetaCreditoAdapter cuando hay límite")
    void testPagoConTarjetaCreditoExitoso() {
        TarjetaCredito tarjetaExistente = new TarjetaCredito(new BigDecimal("100000.00"));
        MedioPago adapter = new TarjetaCreditoAdapter(tarjetaExistente);

        assertFalse(reserva.isPagada());
        reserva.pagarCon(adapter);
        assertTrue(reserva.isPagada());
    }

    @Test
    @DisplayName("Debe fallar el pago con tarjeta si el límite disponible es menor al monto de la reserva")
    void testPagoConTarjetaLimiteInsuficiente() {
        TarjetaCredito tarjetaSinLimite = new TarjetaCredito(new BigDecimal("20000.00"));
        MedioPago adapter = new TarjetaCreditoAdapter(tarjetaSinLimite);

        assertThrows(IllegalStateException.class, () -> reserva.pagarCon(adapter));
        assertFalse(reserva.isPagada());
    }

    @Test
    @DisplayName("Debe pagar la reserva exitosamente usando BilleteraVirtualAdapter con email del huésped")
    void testPagoConBilleteraVirtualExitoso() {
        BilleteraVirtual billeteraExistente = new BilleteraVirtual();
        MedioPago adapter = new BilleteraVirtualAdapter(billeteraExistente);

        assertFalse(reserva.isPagada());
        reserva.pagarCon(adapter);

        assertTrue(reserva.isPagada());
        assertTrue(billeteraExistente.isUltimoPagoExitoso());
    }
}
