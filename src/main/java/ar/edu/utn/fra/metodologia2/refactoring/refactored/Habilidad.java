package ar.edu.utn.fra.metodologia2.refactoring.refactored;

/**
 * Interfaz que abstrae cualquier habilidad de un personaje.
 * Resuelve el mal olor mediante Polimorfismo (Replace Conditional with Polymorphism).
 */
public interface Habilidad {
    double calcularDaño(double dañoBase);
    String getDescripcionTipo();
    String getEfectoFuerza();
}
