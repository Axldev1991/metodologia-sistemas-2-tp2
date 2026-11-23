package ar.edu.utn.fra.metodologia2.patrones.heroes;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de la Combinación: Factory Method + Singleton + Facade (Punto 5)")
class GestorHeroesTest {

    private GestorCentralHeroes gestor;

    @BeforeEach
    void setUp() {
        gestor = GestorCentralHeroes.getInstancia();
        gestor.reiniciarParaPruebas();
    }

    @Test
    @DisplayName("Punto 2 (Singleton): Garantizar que todas las llamadas retornan la misma instancia única")
    void testGarantizarInstanciaUnicaSingleton() {
        GestorCentralHeroes instancia1 = GestorCentralHeroes.getInstancia();
        GestorCentralHeroes instancia2 = GestorCentralHeroes.getInstancia();

        assertSame(instancia1, instancia2, "Ambas referencias deben apuntar al mismo objeto en memoria");
    }

    @Test
    @DisplayName("Punto 1 (Factory Method) y Punto 3 (Facade): Reclutar héroes a través de la interfaz simple de la fachada")
    void testReclutarHeroesViaFachada() {
        GuerreroHeroe conan = gestor.reclutarGuerrero("Conan", "El Bárbaro", 50.0, 10, 5.0);
        MagoHeroe gandalf = gestor.reclutarMago("Gandalf", "El Gris", 100.0);

        assertEquals(2, gestor.getHeroesRegistrados().size());
        assertEquals("Conan", conan.getNombre());
        assertEquals(100.0, conan.calcularPoderTotal(), "Poder base (50) + 10 victorias * 5 bono = 100");

        assertEquals("Gandalf", gandalf.getNombre());
        assertEquals(150.0, gandalf.calcularPoderTotal(), "100 mana * 1.5 = 150");
    }

    @Test
    @DisplayName("Punto 3 (Facade): Consultar el mejor guerrero entre múltiples guerreros registrados")
    void testConsultarMejorGuerrero() {
        gestor.reclutarGuerrero("Aragorn", "Trancos", 40.0, 5, 2.0); // Poder = 50
        gestor.reclutarGuerrero("Gimli", "Hijo de Gloin", 60.0, 12, 3.0); // Poder = 96
        gestor.reclutarGuerrero("Boromir", "Capitán de Gondor", 45.0, 4, 2.0); // Poder = 53
        gestor.reclutarMago("Saruman", "El Sabio", 200.0); // No debe interferir por ser Mago

        GuerreroHeroe mejor = gestor.consultarMejorGuerrero();
        assertNotNull(mejor);
        assertEquals("Gimli", mejor.getNombre());
        assertEquals(96.0, mejor.calcularPoderTotal());
    }

    @Test
    @DisplayName("Punto 3 (Facade): Generar reporte consolidado de batalla sin exponer el modelo interno")
    void testGenerarReporteBatalla() {
        gestor.reclutarGuerrero("Thor", "Dios del Trueno", 100.0, 20, 10.0);
        gestor.reclutarMago("Dr. Strange", "Hechicero Supremo", 150.0);

        String reporte = gestor.generarReporteBatalla();
        assertNotNull(reporte);
        assertTrue(reporte.contains("Thor"));
        assertTrue(reporte.contains("Dr. Strange"));
        assertTrue(reporte.contains("Poder Total"));
    }
}

