package ar.edu.utn.fra.metodologia2.patrones.adapter;

import java.math.BigDecimal;

/**
 * Estereotipo: <<Adaptee>>
 * Clase existente en el sistema. NO puede modificarse según la consigna.
 * Interfaz específica: requiere validar límite y luego autorizar cargo.
 */
public class TarjetaCredito {
    private final BigDecimal limiteDisponible;

    public TarjetaCredito(BigDecimal limiteDisponible) {
        this.limiteDisponible = limiteDisponible;
    }

    public boolean validarLimite(String numeroTarjeta, BigDecimal monto) {
        return numeroTarjeta != null && !numeroTarjeta.isBlank() && limiteDisponible.compareTo(monto) >= 0;
    }

    public boolean autorizarCargo(String numeroTarjeta, BigDecimal monto) {
        // Simula la llamada a la red de tarjetas (Visa/Mastercard)
        return true;
    }
}

