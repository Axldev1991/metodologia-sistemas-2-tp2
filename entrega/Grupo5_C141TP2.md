# METODOLOGÍA DE SISTEMAS II — SEGUNDO PARCIAL (TRABAJO PRÁCTICO INTEGRADOR)

---

### **Información Formal del Trabajo**
* **Institución:** Universidad Tecnológica Nacional (UTN) — Facultad Regional Avellaneda
* **Carrera:** Tecnicatura Universitaria en Programación
* **Cátedra:** Metodología de Sistemas II
* **Curso / División:** C141
* **Número de Grupo:** 5
* **Docente:** Lic. Edith Rocío Castillo Yauri
* **Fecha de Entrega:** Jueves 26/11 — 12:30 hs
* **Repositorio Público en GitHub:** [https://github.com/Axldev1991/metodologia-sistemas-2-tp2](https://github.com/Axldev1991/metodologia-sistemas-2-tp2)

### **Integrantes del Grupo 5**
* Castellano, Axel ([@Axldev1991](https://github.com/Axldev1991))
* Fernandez, Saulo ([@serenomoon](https://github.com/serenomoon))
* Rumie, Nicolás ([@nicolasrumie](https://github.com/nicolasrumie))
* Espósito, Franco ([@francoesposito](https://github.com/francoesposito))
* Cuenca, Francisco
* Salaberry, Thiago

---

# PARTE 1: PATRONES DE DISEÑO

## 1. Consigna 1 — Sistema de Transacciones Bancarias
* **Patrón detectado:** **Strategy** (GoF — Comportamiento).
* **Justificación:** Encapsula las operaciones (`DepositoStrategy`, `ExtraccionStrategy`, `TransferenciaStrategy`) bajo la interfaz `TransaccionStrategy`. El contexto (`GestorTransacciones`) permite alternar libremente la operación en tiempo de ejecución mediante `setEstrategia(...)` antes de confirmar la transacción, cumpliendo con el principio Open/Closed.
* **Diagrama UML:** Ver archivo PlantUML en `docs/diagramas/01-strategy/diagrama_strategy.puml`.
* **Código Java:** Implementado en `src/main/java/.../patrones/strategy/` y validado con tests unitarios en `GestorTransaccionesTest.java`.

---

## 2. Consigna 2 — Sistema de Pagos de Hotel
* **Patrón detectado:** **Adapter** (GoF — Estructural de Objetos).
* **Justificación:** La entidad `Reserva` requiere pagar a través de la interfaz común `MedioPago`. Como las clases preexistentes (`TarjetaCredito` y `BilleteraVirtual`) no pueden modificarse y poseen firmas y protocolos incompatibles, se crearon los adaptadores `TarjetaCreditoAdapter` y `BilleteraVirtualAdapter` que envuelven las clases legadas y traducen las llamadas de forma transparente.
* **Diagrama UML:** Ver archivo PlantUML en `docs/diagramas/02-adapter/diagrama_adapter.puml`.
* **Código Java:** Implementado en `src/main/java/.../patrones/adapter/` y validado con tests unitarios en `ReservaPagoAdapterTest.java`.

---

## 3. Consigna 3 — Sistema de Combate en Videojuego
* **Patrón detectado:** **Template Method** (GoF — Comportamiento).
* **Justificación:** Se definió el esqueleto invariante del combate en el método `public final void pelear()` de la clase abstracta `Personaje`, delegando los pasos específicos (`prepararEquipamiento`, `posicionarse`, `ejecutarAtaque`) en `Guerrero` y `Mago`. Se incorporó un *hook* (`debeCelebrar` / `celebrarVictoria`) para permitir extensiones opcionales.
* **Respuestas teóricas obligatorias:**
  1. *Esqueleto del algoritmo:* Se define en la superclase abstracta `Personaje`, en el método plantilla `pelear()` declarado como `final`.
  2. *Redefinición del esqueleto:* No se puede redefinir; rompería la invariante de ejecución.
  3. *Qué se puede redefinir:* Operaciones primitivas abstractas, métodos concretos no finales y operaciones de enganche (*hooks*).
  4. *Inversión de Control (IoC):* Principio ("Principio de Hollywood") donde el flujo lo gobierna la superclase y llama a las subclases cuando corresponde.
  5. *Hooks:* Puntos de extensión opcionales con comportamiento por defecto. Se agregó `debeCelebrar()` y `celebrarVictoria()`.
  6. *Por qué aplica IoC:* La superclase tiene el control exclusivo del flujo de ejecución; las subclases solo rellenan los detalles individuales al ser invocadas.
* **Diagrama UML:** Ver `docs/diagramas/03-template-method/diagrama_template_method.puml`.
* **Código Java:** Implementado en `src/main/java/.../patrones/templatemethod/` y validado con tests en `PersonajeTemplateMethodTest.java`.

---

## 4. Consigna 4 — Monitoreo Ambiental en Edificio Inteligente
* **Patrón detectado:** **Observer** (GoF — Comportamiento).
* **Justificación:** Relación 1 a N desacoplada entre el sujeto observable `Sensor` (temperatura, humedad, CO2, luminosidad) y sus observadores `ObservadorSensor`. Permite suscribir y desuscribir paneles (`PanelPrincipal`, `PanelAlertas`, `RegistroConsola`) dinámicamente en tiempo de ejecución sin alterar el código existente.
* **Diagrama UML:** Ver `docs/diagramas/04-observer/diagrama_observer.puml`.
* **Código Java:** Implementado en `src/main/java/.../patrones/observer/` y validado con tests en `SistemaMonitoreoObserverTest.java`.

---

## 5. Consigna 5 — Sistema de Reclutamiento de Héroes
* **Patrones combinados:**
  1. **Factory Method:** La jerarquía `CreadorHeroe` (`CreadorGuerrero`, `CreadorMago`) encapsula la creación de `GuerreroHeroe` y `MagoHeroe`, permitiendo extender nuevos personajes en el futuro sin modificar el código que solicita su creación.
  2. **Singleton:** `GestorCentralHeroes` garantiza mediante constructor privado y `getInstancia()` una única instancia central compartida para todos los gremios.
  3. **Facade:** `GestorCentralHeroes` ofrece una interfaz sencilla de alto nivel (`reclutarGuerrero`, `reclutarMago`, `consultarMejorGuerrero`, `generarReporteBatalla`) que oculta la complejidad del subsistema interno.
* **Diagrama UML:** Ver `docs/diagramas/05-heroes-factory-singleton-facade/diagrama_heroes.puml`.
* **Código Java:** Implementado en `src/main/java/.../patrones/heroes/` y validado con tests en `GestorHeroesTest.java`.

---

# PARTE 2: REFACTORING

## Análisis sobre la clase Videojuego (Martin Fowler)
1. **Mal Olor Detectado:** **Switch Statements / Conditional Complexity on Type** y **Feature Envy**.
   * Ubicado en `imprimirInfo` al consultar `p.getTipoHabilidad().getClass() == HabilidadMagica.class`.
2. **Impacto en Mantenibilidad y Escalabilidad:** Viola el Principio Open/Closed (OCP), propaga duplicación de código (*Repeated Switches*), incrementa la fragilidad del sistema y acopla indebidamente a `Videojuego` con las clases concretas de habilidades.
3. **Refactoring Recomendado:** **Replace Conditional with Polymorphism** (junto con *Tell, Don't Ask* y *Move Method*).
4. **Solución Implementada:**
   * Se creó la interfaz `Habilidad` con métodos polimórficos `calcularDaño(dañoBase)`, `getDescripcionTipo()` y `getEfectoFuerza()`.
   * Se eliminaron todos los `if/else` en `Videojuego`, delegando el comportamiento en cada clase concreta (`HabilidadMagica` y `HabilidadCuerpoACuerpo`).
* **Diagrama UML:** Ver `docs/diagramas/06-refactoring/diagrama_refactoring.puml`.
* **Código Java:** Implementado en `src/main/java/.../refactoring/refactored/` y probado en `VideojuegoRefactoredTest.java`.

---

# PARTE 3: DOMAIN-DRIVEN DESIGN (DDD)

### 1. Lenguaje Ubicuo
Vocabulario riguroso, compartido y sin ambigüedades entre desarrolladores y expertos de negocio. Elimina la traducción mental errónea entre especificación y código: los mismos términos del negocio nombran clases, métodos y excepciones en el software.

### 2. Entidad vs. Value Object
* **Entidad:** Posee **identidad única y continua** (ID/UUID). Es mutable; su estado cambia pero sigue siendo el mismo objeto en el dominio (Ej: `Pedido`).
* **Value Object:** **Carece de identidad propia**; se define exclusivamente por el valor conjunto de sus atributos. Es **inmutable** y libremente intercambiable (Ej: `Direccion`, `Dinero`).

### 3. Bounded Context (Contexto Delimitado)
Frontera explícita donde un modelo y su lenguaje ubicuo son semánticamente consistentes. En sistemas grandes evita la sobrecarga y contradicción de conceptos polisérmicos (ej. un `Producto` en Ventas tiene precio; en Logística tiene peso y estantería), permitiendo que equipos y microservicios evolucionen de forma autónoma.

### 4. Rol de los Diagramas UML en DDD
UML actúa como puente visual neutral:
* **Casos de Uso:** Definen el alcance e interacción de los actores del negocio.
* **Diagramas de Clases:** Mapean la estructura conceptual del dominio (Entidades, Value Objects, Agregados).
* **Diagramas de Secuencia:** Visualizan la colaboración temporal de los objetos para resolver reglas de negocio.
Favorecen el lenguaje común porque permiten validar con los expertos si el modelo refleja fielmente la realidad antes de codificar, utilizando términos de negocio en lugar de conceptos técnicos de infraestructura.
