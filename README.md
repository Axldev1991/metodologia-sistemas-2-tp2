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

## Contenido a Desarrollar

1. **Patrones de Diseño:**
   - **Strategy:** Sistema de transacciones bancarias (depósito, extracción, transferencia) intercambiables antes de confirmar.
   - **Adapter:** Pagos en hotel con `TarjetaCredito` y `BilleteraVirtual` sin modificar clases existentes.
   - **Template Method:** Proceso de preparación y combate para `Guerrero` y `Mago` con hook e inversión de control.
   - **Observer:** Sensores ambientales en tiempo real notificando a paneles gráficos y consola dinámicamente.
   - **Factory Method + Singleton + Facade:** Creación desacoplada de héroes, instancia única de gestor e interfaz unificada para los gremios.

2. **Refactoring:**
   - Análisis de *bad smells* según Martin Fowler sobre la clase `Videojuego`.
   - Justificación de problemas de mantenibilidad/escalabilidad.
   - Aplicación de refactorización orientada a objetos (reemplazo de condicionales de tipo por polimorfismo).

3. **Domain-Driven Design (DDD):**
   - Lenguaje ubicuo.
   - Entidad vs. Objeto de Valor (Value Object).
   - Bounded Context y manejo de complejidad.
   - Aporte de diagramas UML al modelo de dominio.

