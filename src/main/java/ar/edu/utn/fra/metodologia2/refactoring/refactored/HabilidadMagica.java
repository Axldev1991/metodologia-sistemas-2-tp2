package ar.edu.utn.fra.metodologia2.refactoring.refactored;

public class HabilidadMagica implements Habilidad {

    @Override
    public double calcularDaño(double dañoBase) {
        return dañoBase * 2.0; // Dobla la fuerza
    }

    @Override
    public String getDescripcionTipo() {
        return "Habilidad tipo habilidad mágica";
    }

    @Override
    public String getEfectoFuerza() {
        return "Esta habilidad dobla tu fuerza";
    }
}

