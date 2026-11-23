package ar.edu.utn.fra.metodologia2.patrones.heroes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Estereotipos: <<Singleton>> y <<Facade>>
 */
public class GestorCentralHeroes {

    private static volatile GestorCentralHeroes instancia;

    private final List<Heroe> heroesRegistrados;
    private final CreadorHeroe creadorGuerrero;
    private final CreadorHeroe creadorMago;

    private GestorCentralHeroes() {
        this.heroesRegistrados = new ArrayList<>();
        this.creadorGuerrero = new CreadorGuerrero();
        this.creadorMago = new CreadorMago();
    }

    public static GestorCentralHeroes getInstancia() {
        if (instancia == null) {
            synchronized (GestorCentralHeroes.class) {
                if (instancia == null) {
                    instancia = new GestorCentralHeroes();
                }
            }
        }
        return instancia;
    }

    public GuerreroHeroe reclutarGuerrero(String nombre, String apodo, double ataque, int victorias, double bono) {
        DatosCreacionHeroe datos = DatosCreacionHeroe.paraGuerrero(nombre, apodo, ataque, victorias, bono);
        GuerreroHeroe guerrero = (GuerreroHeroe) creadorGuerrero.crearHeroe(datos);
        heroesRegistrados.add(guerrero);
        return guerrero;
    }

    public MagoHeroe reclutarMago(String nombre, String apodo, double mana) {
        DatosCreacionHeroe datos = DatosCreacionHeroe.paraMago(nombre, apodo, mana);
        MagoHeroe mago = (MagoHeroe) creadorMago.crearHeroe(datos);
        heroesRegistrados.add(mago);
        return mago;
    }

    public GuerreroHeroe consultarMejorGuerrero() {
        return heroesRegistrados.stream()
                .filter(h -> h instanceof GuerreroHeroe)
                .map(h -> (GuerreroHeroe) h)
                .max(Comparator.comparingDouble(GuerreroHeroe::calcularPoderTotal))
                .orElse(null);
    }

    public String generarReporteBatalla() {
        StringBuilder sb = new StringBuilder("=== REPORTE DE HÉROES DISPONIBLES ===\n");
        for (Heroe h : heroesRegistrados) {
            sb.append("- ").append(h.getNombre())
              .append(" (\"").append(h.getApodo()).append("\") | ")
              .append(h.getDescripcionTipo())
              .append(" | Poder Total: ").append(h.calcularPoderTotal()).append("\n");
        }
        return sb.toString();
    }

    public List<Heroe> getHeroesRegistrados() {
        return Collections.unmodifiableList(heroesRegistrados);
    }

    public void reiniciarParaPruebas() {
        this.heroesRegistrados.clear();
    }
}
