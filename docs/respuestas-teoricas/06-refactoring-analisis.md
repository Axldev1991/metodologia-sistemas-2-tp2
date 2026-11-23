# Análisis y Respuestas — Consigna de Refactoring

## 1. Identificación y Nombre del Mal Olor (Bad Smell) según Martin Fowler
El código presenta los siguientes malos olores tipificados en el catálogo de Martin Fowler:
1. **Switch Statements / Conditional Complexity on Type (Sentencias condicionales por tipo):**
   * **Ubicación exacta:** En el método `imprimirInfo(Personaje p)`:
     ```java
     if (p.getTipoHabilidad().getClass() == HabilidadMagica.class) {
         System.out.println("Habilidad tipo habilidad mágica");
         System.out.println("Esta habilidad dobla tu fuerza");
     } else {
         System.out.println("Habilidad tipo habilidad Cuerpo a cuerpo");
         System.out.println("Este habilidad mantiene tu fuerza");
     }
     ```
   * **Descripción:** Comprobar la clase concreta mediante reflexión (`getClass() == HabilidadMagica.class`) o condicionales de tipo es la manifestación clásica de este mal olor. En lugar de aprovechar el polimorfismo de objetos, se fuerza un flujo estructurado con bifurcaciones.
2. **Feature Envy (Envidia de Atributos):**
   * El método `imprimirInfo` de la clase `Videojuego` interroga intensamente a `Personaje` y a `Habilidad` para inspeccionar sus datos internos y decidir qué texto imprimir sobre la habilidad. Esa responsabilidad le pertenece a la jerarquía de `Habilidad` (*Tell, Don't Ask*).

---

## 2. ¿Por qué representan un problema para la mantenibilidad y escalabilidad del software?
1. **Violación directa del Principio Open/Closed (OCP):** Cada vez que el juego incorpore una nueva habilidad (por ejemplo, `HabilidadADistancia`, `HabilidadDefensiva` o `HabilidadCurativa`), estamos obligados a entrar a modificar la clase `Videojuego`, alterando el `if/else`. El software no está cerrado a la modificación ni abierto a la extensión.
2. **Fragilidad y Propagación de Errores:** Si la descripción o el cálculo de fuerza de una habilidad cambia, o si esa misma lógica se necesita en otras pantallas o reportes del sistema, los condicionales se duplicarán (*Repeated Switches*). Modificar una regla en un lugar y olvidar otro provocará inconsistencias silenciosas en tiempo de ejecución.
3. **Alto Acoplamiento y Pérdida de Cohesión:** `Videojuego` queda rígidamente acoplado a las implementaciones concretas (`HabilidadMagica.class`) en vez de interactuar con una interfaz abstracta. Además, asume responsabilidades de formateo de reglas de combate que no le competen.

---

## 3. Tipo de Refactoring que se recomienda aplicar para corregirlo
Según el catálogo de Martin Fowler, la técnica canónica a aplicar es:
* **Replace Conditional with Polymorphism (Reemplazar condicional con polimorfismo):**
  1. Definir los métodos abstractos en la interfaz común `Habilidad`:
     - `String getDescripcionTipo()`
     - `String getEfectoFuerza()`
     - `double calcularDaño(double dañoBase)`
  2. Implementar cada comportamiento específico dentro de las clases concretas (`HabilidadMagica` y `HabilidadCuerpoACuerpo`).
  3. Eliminar la estructura condicional de `Videojuego.imprimirInfo(...)`, delegando directamente en `p.getTipoHabilidad().getDescripcionTipo()` y `p.getTipoHabilidad().getEfectoFuerza()`.
* **Move Method (Mover Método) / Tell, Don't Ask:** Se encapsula el cálculo del daño efectivo dentro de `Personaje` (`p.calcularDañoEfectivo()`), evitando que `Videojuego` navegue los atributos internos del personaje.

---

## 4. Corrección del Código y Modelo UML
El código corregido fue implementado en `src/main/java/ar/edu/utn/fra/metodologia2/refactoring/refactored/` y validado con tests unitarios en `VideojuegoRefactoredTest.java`.
