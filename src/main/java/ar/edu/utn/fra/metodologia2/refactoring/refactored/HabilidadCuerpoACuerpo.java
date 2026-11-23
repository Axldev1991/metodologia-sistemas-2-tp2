package ar.edu.utn.fra.metodologia2.refactoring.refactored;

public class HabilidadCuerpoACuerpo implements Habilidad {

    @Override
    public double calcularDaño(double dañoBase) {
        return dañoBase;
    }

    @Override
    public String getDescripcionTipo() {
        return "Habilidad tipo habilidad Cuerpo a cuerpo";
    }

    @Override
    public String getEfectoFuerza() {
        return "Este habilidad mantiene tu fuerza";
    }
}
