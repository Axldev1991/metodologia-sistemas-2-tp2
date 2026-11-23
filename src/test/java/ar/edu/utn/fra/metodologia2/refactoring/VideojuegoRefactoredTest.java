package ar.edu.utn.fra.metodologia2.refactoring;

import ar.edu.utn.fra.metodologia2.refactoring.refactored.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests de la Solución Refactorizada de Videojuego (Consigna Refactoring)")
class VideojuegoRefactoredTest {

    @Test
    @DisplayName("Debe calcular correctamente el daño efectivo polimórfico de cada habilidad")
    void testDañoEfectivo() {
        Personaje mago = new Personaje("Merlín", 50.0, new HabilidadMagica());
        Personaje guerrero = new Personaje("Arturo", 50.0, new HabilidadCuerpoACuerpo());

        assertEquals(100.0, mago.calcularDañoEfectivo());
        assertEquals(50.0, guerrero.calcularDañoEfectivo());
    }

    @Test
    @DisplayName("Debe encontrar el personaje con más daño efectivo considerando el multiplicador de su habilidad")
    void testPersonajeConMasDaño() {
        Videojuego juego = new Videojuego();
        Personaje p1 = new Personaje("Guerrero Débil", 30.0, new HabilidadCuerpoACuerpo());
        Personaje p2 = new Personaje("Mago Poderoso", 40.0, new HabilidadMagica());
        Personaje p3 = new Personaje("Guerrero Fuerte", 70.0, new HabilidadCuerpoACuerpo());

        juego.agregarPersonaje(p1);
        juego.agregarPersonaje(p2);
        juego.agregarPersonaje(p3);

        Personaje masPoderoso = juego.personajeConMasDaño();
        assertNotNull(masPoderoso);
        assertEquals("Mago Poderoso", masPoderoso.getNombre());
    }

    @Test
    @DisplayName("Debe imprimir la información polimórfica sin usar condicionales por tipo de clase")
    void testImprimirInfoPolimorfico() {
        Videojuego juego = new Videojuego();
        Personaje mago = new Personaje("Gandalf", 60.0, new HabilidadMagica());

        String info = juego.imprimirInfo(mago);
        assertTrue(info.contains("Gandalf"));
        assertTrue(info.contains("Habilidad tipo habilidad mágica"));
        assertTrue(info.contains("Esta habilidad dobla tu fuerza"));
    }
}
