# Metodología de Sistemas II — Segundo Parcial (TP Integrador)

---

### **Información del Trabajo**
* **Institución:** Universidad Tecnológica Nacional (UTN) — Facultad Regional Avellaneda
* **Materia:** Metodología de Sistemas II
* **Curso / División:** C141
* **Grupo:** 5
* **Docente:** Lic. Edith Rocío Castillo Yauri
* **Fecha de Entrega:** Jueves 26/11 - 12:30 hs
* **Repositorio:** [metodologia-sistemas-2-tp2](https://github.com/Axldev1991/metodologia-sistemas-2-tp2)
* **Entrega Formal (PDF):** [entrega/Grupo5_C141TP2.pdf](entrega/Grupo5_C141TP2.pdf)
* **Dossier HTML:** [entrega/Grupo5_C141TP2.html](entrega/Grupo5_C141TP2.html)

### **Integrantes del Grupo 5**
* Castellano, Axel ([@Axldev1991](https://github.com/Axldev1991))
* Fernandez, Saulo ([@serenomoon](https://github.com/serenomoon))
* Rumie, Nicolás ([@nicolasrumie](https://github.com/nicolasrumie))
* Espósito, Franco ([@francoesposito](https://github.com/francoesposito))
* Cuenca, Francisco
* Salaberry, Thiago

---

## Estructura del Proyecto

```text
parcial-2/
├── docs/
│   ├── diagramas/
│   │   ├── 01-strategy/                         # Diagrama UML Consigna 1
│   │   ├── 02-adapter/                          # Diagrama UML Consigna 2
│   │   ├── 03-template-method/                  # Diagrama UML Consigna 3
│   │   ├── 04-observer/                         # Diagrama UML Consigna 4
│   │   ├── 05-heroes-factory-singleton-facade/  # Diagrama UML Consigna 5
│   │   └── 06-refactoring/                      # Diagrama UML Consigna 6 (Refactorizado)
│   └── respuestas-teoricas/
│       ├── 03-template-method-respuestas.md     # Respuestas teóricas Template Method (IoC y hooks)
│       ├── 06-refactoring-analisis.md           # Detección de bad smell y justificación
│       └── 07-ddd-respuestas.md                 # Preguntas de Domain-Driven Design (máx. 2 hojas)
├── entrega/
│   └── Grupo5_C141TP2.pdf                       # Documento formal consolidado para entrega
├── src/
│   ├── main/java/ar/edu/utn/fra/metodologia2/
│   │   ├── patrones/
│   │   │   ├── strategy/                        # Consigna 1: Transacciones bancarias
│   │   │   ├── adapter/                         # Consigna 2: Medios de pago de hotel
│   │   │   ├── templatemethod/                  # Consigna 3: Combate Guerrero y Mago
│   │   │   ├── observer/                        # Consigna 4: Monitoreo ambiental y paneles
│   │   │   └── heroes/                          # Consigna 5: Factory Method + Singleton + Facade
│   │   └── refactoring/
│   │       ├── original/                        # Código original provisto por la cátedra
│   │       └── refactored/                      # Código refactorizado y desacoplado
│   └── test/java/ar/edu/utn/fra/metodologia2/   # Pruebas unitarias de verificación
├── pom.xml                                      # Configuración Maven y dependencias
└── README.md
```

---

## Índice Navegable por Punto del TP

### Parte 1 — Patrones de Diseño (GoF)

| Punto | Tema / Patrón | Diagrama UML y Análisis | Código Fuente | Tests JUnit |
| :--- | :--- | :--- | :--- | :--- |
| **Punto 1** | **Strategy** — Transacciones bancarias | [README](docs/diagramas/01-strategy/README.md) · [PUML](docs/diagramas/01-strategy/diagrama_strategy.puml) · [PNG](entrega/img/01-strategy.png) | [patrones/strategy](src/main/java/ar/edu/utn/fra/metodologia2/patrones/strategy) | [GestorTransaccionesTest.java](src/test/java/ar/edu/utn/fra/metodologia2/patrones/strategy/GestorTransaccionesTest.java) |
| **Punto 2** | **Adapter** — Pagos de reservas de hotel | [README](docs/diagramas/02-adapter/README.md) · [PUML](docs/diagramas/02-adapter/diagrama_adapter.puml) · [PNG](entrega/img/02-adapter.png) | [patrones/adapter](src/main/java/ar/edu/utn/fra/metodologia2/patrones/adapter) | [ReservaPagoAdapterTest.java](src/test/java/ar/edu/utn/fra/metodologia2/patrones/adapter/ReservaPagoAdapterTest.java) |
| **Punto 3** | **Template Method** — Combate en videojuego | [README](docs/diagramas/03-template-method/README.md) · [PUML](docs/diagramas/03-template-method/diagrama_template_method.puml) · [PNG](entrega/img/03-template-method.png) · [Respuestas](docs/respuestas-teoricas/03-template-method-respuestas.md) | [patrones/templatemethod](src/main/java/ar/edu/utn/fra/metodologia2/patrones/templatemethod) | [PersonajeTemplateMethodTest.java](src/test/java/ar/edu/utn/fra/metodologia2/patrones/templatemethod/PersonajeTemplateMethodTest.java) |
| **Punto 4** | **Observer** — Monitoreo ambiental | [README](docs/diagramas/04-observer/README.md) · [PUML](docs/diagramas/04-observer/diagrama_observer.puml) · [PNG](entrega/img/04-observer.png) | [patrones/observer](src/main/java/ar/edu/utn/fra/metodologia2/patrones/observer) | [SistemaMonitoreoObserverTest.java](src/test/java/ar/edu/utn/fra/metodologia2/patrones/observer/SistemaMonitoreoObserverTest.java) |
| **Punto 5** | **Factory Method + Singleton + Facade** — Gremios y héroes | [README](docs/diagramas/05-heroes-factory-singleton-facade/README.md) · [PUML](docs/diagramas/05-heroes-factory-singleton-facade/diagrama_heroes.puml) · [PNG](entrega/img/05-heroes.png) | [patrones/heroes](src/main/java/ar/edu/utn/fra/metodologia2/patrones/heroes) | [GestorHeroesTest.java](src/test/java/ar/edu/utn/fra/metodologia2/patrones/heroes/GestorHeroesTest.java) |

### Parte 2 — Refactoring (Martin Fowler)

| Sección | Tema | Análisis y Diagrama UML | Código Original | Código Refactorizado | Tests JUnit |
| :--- | :--- | :--- | :--- | :--- | :--- |
| **Refactoring** | *Replace Conditional with Polymorphism* (`Videojuego`) | [Análisis teórico](docs/respuestas-teoricas/06-refactoring-analisis.md) · [README](docs/diagramas/06-refactoring/README.md) · [PUML](docs/diagramas/06-refactoring/diagrama_refactoring.puml) · [PNG](entrega/img/06-refactoring.png) | [VideojuegoOriginal.java](src/main/java/ar/edu/utn/fra/metodologia2/refactoring/original/VideojuegoOriginal.java) | [refactoring/refactored](src/main/java/ar/edu/utn/fra/metodologia2/refactoring/refactored) | [VideojuegoRefactoredTest.java](src/test/java/ar/edu/utn/fra/metodologia2/refactoring/VideojuegoRefactoredTest.java) |

### Parte 3 — Domain-Driven Design (DDD)

| Sección | Temas Cubiertos | Documento |
| :--- | :--- | :--- |
| **DDD** | Lenguaje Ubicuo, Entidad vs. Value Object, Bounded Context y rol de UML | [07-ddd-respuestas.md](docs/respuestas-teoricas/07-ddd-respuestas.md) |

