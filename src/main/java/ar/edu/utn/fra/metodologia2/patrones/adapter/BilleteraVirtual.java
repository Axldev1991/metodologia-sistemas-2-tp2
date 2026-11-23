package ar.edu.utn.fra.metodologia2.patrones.adapter;

import java.math.BigDecimal;

/**
 * Estereotipo: <<Adaptee>>
 * Clase existente en el sistema. NO puede modificarse según la consigna.
 * Interfaz específica: procesa el pago usando directamente el email y el monto.
 */
public class BilleteraVirtual {
    private boolean ultimoPagoExitoso;

    public boolean pagarConEmail(String email, BigDecimal monto) {
        if (email == null || !email.contains("@") || monto == null || monto.compareTo(BigDecimal.ZERO) <= 0) {
            this.ultimoPagoExitoso = false;
            return false;
        }
        this.ultimoPagoExitoso = true;
        return true;
    }

    public boolean isUltimoPagoExitoso() {
        return ultimoPagoExitoso;
    }
}
