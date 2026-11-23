package ar.edu.utn.fra.metodologia2.patrones.templatemethod;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests del Patrón Template Method - Combate en Videojuego")
class PersonajeTemplateMethodTest {

    @Test
    @DisplayName("Debe ejecutar la secuencia exacta de combate del Guerrero incluyendo su hook")
    void testCombateGuerrero() {
        Personaje conan = new Guerrero("Conan");
        conan.pelear();

        List<String> acciones = conan.getRegistroAcciones();
        assertEquals(5, acciones.size());
        assertTrue(acciones.get(0).contains("prepara su espada y equipa la armadura"));
        assertTrue(acciones.get(1).contains("se acerca corriendo"));
        assertTrue(acciones.get(2).contains("ataque físico"));
        assertTrue(acciones.get(3).contains("evalúa el resultado"));
        assertTrue(acciones.get(4).contains("grito de guerra triunfal"));
    }

    @Test
    @DisplayName("Debe ejecutar la secuencia exacta de combate del Mago respetando su decisión de hook")
    void testCombateMago() {
        Personaje gandalf = new Mago("Gandalf");
        gandalf.pelear();

        List<String> acciones = gandalf.getRegistroAcciones();
        assertEquals(4, acciones.size(), "El mago desactivó el hook de festejo, por lo que son 4 pasos");
        assertTrue(acciones.get(0).contains("selecciona un hechizo arcano"));
        assertTrue(acciones.get(1).contains("concentra energía mágica"));
        assertTrue(acciones.get(2).contains("ataque mágico"));
        assertTrue(acciones.get(3).contains("evalúa el resultado"));
    }
}

