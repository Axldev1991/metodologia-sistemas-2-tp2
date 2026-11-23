# Respuestas Teóricas — Domain-Driven Design (DDD)

> **Pauta de cátedra:** El desarrollo de las preguntas debe ser claro, conciso y no exceder las dos hojas.

---

### 1. ¿Qué es el lenguaje ubicuo y cómo contribuye a la alineación entre el equipo técnico y los expertos del dominio?

El **Lenguaje Ubicuo** (*Ubiquitous Language*) es el pilar central del Diseño Guiado por el Dominio formulado por Eric Evans. Se define como un **vocabulario riguroso, compartido, preciso y sin ambigüedades**, construido y utilizado de manera constante tanto por los desarrolladores de software como por los expertos del negocio.

#### Contribución a la alineación entre ambos equipos:
1. **Eliminación de la brecha de traducción mental:** Tradicionalmente, los expertos de negocio explican sus procesos en su jerga funcional, y los desarrolladores "traducen" mentalmente esos conceptos a tablas de bases de datos, algoritmos o patrones técnicos. Esta doble traducción es la fuente principal de malentendidos y deuda técnica. Con el Lenguaje Ubicuo, esa traducción desaparece: **el mismo término que utiliza el negocio es el que se usa en las conversaciones, en los diagramas de diseño y en el código fuente** (nombres de clases, métodos, variables y excepciones).
2. **Reflejo directo en el modelo de software:** Si el experto de negocio dice que *"un cliente cancela una reserva"*, en el código existirá explícitamente `reserva.cancelar()`, y no una operación genérica y anémica como `reserva.setEstado(3)`.
3. **Detección temprana de inconsistencias:** Al consensuar cada definición, se descubren contradicciones conceptuales en las reglas de negocio antes de implementar el software, garantizando que el sistema resuelva el problema real.

---

### 2. Diferenciá claramente una entidad de un value object en el modelo del dominio, ejemplifique.

En DDD, los objetos del modelo se clasifican principalmente según cómo se gestiona su identidad y su ciclo de vida:

| Criterio | Entidad (*Entity*) | Objeto de Valor (*Value Object*) |
| :--- | :--- | :--- |
| **Identidad** | Posee un **identificador único y persistente** (ID, CBU, UUID). La identidad se mantiene a lo largo de todo su ciclo de vida. | **Carece de identidad propia conceptual**. Se define pura y exclusivamente por el valor de sus atributos. |
| **Igualdad** | Dos entidades son iguales si y solo si comparten el **mismo identificador**, sin importar si el resto de sus atributos difieren. | Dos Value Objects son iguales si **todos sus atributos son idénticos** (igualdad estructural por valor). |
| **Mutabilidad** | **Mutable**. Su estado evoluciona y cambia en el tiempo mediante métodos de negocio, pero sigue siendo la misma entidad. | **Inmutable**. Una vez instanciado, su estado no puede cambiar. Si se necesita un valor distinto, se crea una nueva instancia. |
| **Reemplazabilidad**| No son intercambiables libremente; representan un individuo específico en el dominio. | Completamente intercambiables. Cualquier instancia con los mismos datos es indistinguible de otra. |

#### Ejemplos concretos:
* **Ejemplo de Entidad:** `Pedido`.
  Un pedido posee un número único (`numeroPedido: "PED-2026-9901"`). Cuando un pedido pasa de estado *"Pendiente"* a *"Pagado"* y luego a *"Enviado"*, sus atributos mutaron, pero sigue siendo exactamente el mismo pedido en el dominio debido a su identidad única.
* **Ejemplo de Value Object:** `Direccion` o `Dinero`.
  Un objeto `Dinero(monto: 100, moneda: "USD")` no tiene ID. Cualquier billete de 100 USD es intercambiable por otro de 100 USD. Si a un `Pedido` se le modifica la dirección de entrega, no se "muta" la calle del objeto existente; se descarta el Value Object anterior y se le asigna una nueva instancia inmutable de `Direccion(calle: "Mitre", numero: 750, codigoPostal: "1870")`.

---

### 3. ¿Qué es un bounded context y explica por qué su correcta delimitación es clave para manejar la complejidad y la evolución de sistemas grandes?

Un **Contexto Delimitado** (*Bounded Context*) es el **límite explícito y conceptual** dentro del cual un modelo de dominio particular y su correspondiente Lenguaje Ubicuo tienen plena validez y consistencia semántica.

#### Importancia clave en sistemas de gran escala:
1. **Resolución de la polisemia (múltiples significados de una misma palabra):**
   En organizaciones complejas, intentar crear un único modelo unificado para toda la empresa (un "modelo canónico universal") es inviable. La palabra `Producto` significa cosas completamente distintas según el área:
   * En el **Contexto de Ventas/Catálogo:** un `Producto` tiene nombre comercial, descripción, fotos y precio de venta.
   * En el **Contexto de Logística/Depósito:** ese mismo `Producto` se define por peso, volumen, fragilidad y ubicación en estantería.
   * En el **Contexto de Facturación/Contabilidad:** se define por código arancelario, alícuota de IVA y centro de costos.
   El Bounded Context permite que cada subsistema modele su propia versión de `Producto` con alta cohesión, sin sobrecargar una única clase monolítica con decenas de atributos innecesarios para otros contextos.
2. **Aislamiento de la complejidad:** Al delimitar fronteras claras, los equipos pueden evolucionar, refactorizar o incluso reescribir un módulo/microservicio sin generar un efecto dominó que rompa el resto del ecosistema.
3. **Autonomía de equipos:** Permite que distintos equipos trabajen en paralelo sobre contextos separados, comunicándose a través de interfaces formales e integraciones explícitas (Context Maps, APIs o eventos de dominio).

---

### 4. ¿Cómo ayudan los diagramas UML (como diagramas de clases, secuencia y casos de uso) a definir y comunicar el modelo del dominio? ¿De qué manera favorecen el lenguaje común entre desarrolladores y expertos del negocio?

Los diagramas UML no deben concebirse como mero papeleo burocrático, sino como un **lenguaje visual neutral de modelado** que actúa como puente conceptual entre el mundo técnico y el mundo del negocio:

1. **Aporte específico de cada tipo de diagrama:**
   * **Diagramas de Casos de Uso:** Delimitan la frontera del sistema e identifican las metas que los distintos actores del negocio necesitan alcanzar. Permiten validar con los usuarios el *alcance funcional* sin entrar en detalles prematuros de implementación.
   * **Diagramas de Clases (a nivel de Dominio):** Mapean visualmente la estructura conceptual del negocio: identifican las Entidades, los Value Objects, los Agregados y las relaciones estructurales (asociaciones, agregaciones). Permite verificar si las responsabilidades están correctamente asignadas antes de codificar.
   * **Diagramas de Secuencia:** Representan la coreografía temporal de mensajes. Muestran cómo colaboran los objetos del dominio para satisfacer un caso de uso concreto en el tiempo, haciendo visibles las interacciones y validaciones del negocio.

2. **Favorecimiento del lenguaje común entre desarrolladores y expertos:**
   * **Medio de validación visual accesible:** Un experto de dominio no necesita saber programar en Java para entender un diagrama de clases conceptual o una secuencia. Puede mirar el diagrama y corregir al equipo técnico: *"No, un cliente no aprueba el crédito directamente; el límite lo valida el Analista de Riesgos"*.
   * **Evita el sesgo técnico:** Al utilizar exclusivamente los términos del Lenguaje Ubicuo en los diagramas (en lugar de conceptos de infraestructura como tablas, endpoints HTTP o cursores SQL), se mantiene la conversación centrada en el valor de negocio, garantizando que el software refleje fielmente el problema a resolver.

