package ar.edu.utn.fra.metodologia2.patrones.heroes;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * Estereotipos: <<Singleton>> y <<Facade>>
 * 
 * 1. Como Singleton: Garantiza una única instancia central en el sistema para todos los gremios.
 * 2. Como Facade: Proporciona una interfaz unificada y de alto nivel para reclutar,
 *    consultar el mejor guerrero y generar reportes sin que los gremios conozcan
 *    los detalles internos de creación y coordinación.
 */
public class GestorCentralHeroes {

    // Instancia única (Singleton)
    private static volatile GestorCentralHeroes instancia;

    // Subsistemas internos administrados
    private final List<Heroe> heroesRegistrados;
    private final CreadorHeroe creadorGuerrero;
    private final CreadorHeroe creadorMago;

    // Constructor privado para evitar instanciación externa
    private GestorCentralHeroes() {
        this.heroesRegistrados = new ArrayList<>();
        this.creadorGuerrero = new CreadorGuerrero();
        this.creadorMago = new CreadorMago();
    }

    /**
     * Punto de acceso global a la única instancia (Singleton con Double-Checked Locking).
     */
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

    // OPERACIONES DE FACHADA (Facade) PARA LOS GREMIOS

    /**
     * Recluta un Guerrero ocultando la complejidad del Factory Method al cliente.
     */
    public GuerreroHeroe reclutarGuerrero(String nombre, String apodo, double ataque, int victorias, double bono) {
        DatosCreacionHeroe datos = DatosCreacionHeroe.paraGuerrero(nombre, apodo, ataque, victorias, bono);
        GuerreroHeroe guerrero = (GuerreroHeroe) creadorGuerrero.crearHeroe(datos);
        heroesRegistrados.add(guerrero);
        return guerrero;
    }

    /**
     * Recluta un Mago ocultando la complejidad del Factory Method al cliente.
     */
    public MagoHeroe reclutarMago(String nombre, String apodo, double mana) {
        DatosCreacionHeroe datos = DatosCreacionHeroe.paraMago(nombre, apodo, mana);
        MagoHeroe mago = (MagoHeroe) creadorMago.crearHeroe(datos);
        heroesRegistrados.add(mago);
        return mago;
    }

    /**
     * Consulta el mejor guerrero basado en su poder total acumulado.
     */
    public GuerreroHeroe consultarMejorGuerrero() {
        return heroesRegistrados.stream()
                .filter(h -> h instanceof GuerreroHeroe)
                .map(h -> (GuerreroHeroe) h)
                .max(Comparator.comparingDouble(GuerreroHeroe::calcularPoderTotal))
                .orElse(null);
    }

    /**
     * Genera un reporte consolidado de batalla con todos los héroes del reino.
     */
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

    /**
     * Método auxiliar de utilidad para pruebas (limpia la lista de héroes).
     */
    public void reiniciarParaPruebas() {
        this.heroesRegistrados.clear();
    }
}

