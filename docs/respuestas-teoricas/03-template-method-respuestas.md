# Respuestas Teóricas Obligatorias — Patrón Template Method (Punto 3)

### 1. ¿Dónde se define el esqueleto del algoritmo?
Se define en la **clase base abstracta** (`Personaje`), dentro del método plantilla (`pelear()`). En Java se declara con el modificador `final` (`public final void pelear()`) para asegurar que su secuencia sea invariante e inalterable por las subclases.

### 2. ¿Se puede redefinir el esqueleto?
**No.** La premisa central del patrón Template Method es que la estructura y el orden secuencial de los pasos permanezcan fijos. Si una subclase pudiera sobreescribir el método plantilla, destruiría el contrato de diseño y la garantía de ejecución estructurada que ofrece el patrón.

### 3. ¿Qué es lo que se puede redefinir?
Las subclases pueden redefinir:
* **Operaciones primitivas abstractas:** Pasos obligatorios declarados como `abstract` (`prepararEquipamiento()`, `posicionarse()`, `ejecutarAtaque()`). Cada subclase aporta su implementación particular.
* **Operaciones concretas sobreescribibles:** Métodos con comportamiento predeterminado que una subclase puede optar por especializar.
* **Operaciones de enganche (*hooks*):** Métodos que proveen puntos de extensión opcionales (por ejemplo `debeCelebrar()` o `celebrarVictoria()`).

### 4. ¿Qué es inversión de control (IoC)?
La **Inversión de Control** es un principio de diseño arquitectónico en el que se invierte el flujo tradicional de llamadas de un programa. En el diseño procedimental tradicional, el código que escribe el programador invoca a funciones de bibliotecas. Con IoC, es la abstracción o superclase quien tiene el control del flujo y llama al código específico del usuario cuando corresponde.
Este concepto se conoce formalmente en Orientación a Objetos como el **"Principio de Hollywood"**: *"No nos llames a nosotros, nosotros te llamaremos a ti"* (*"Don't call us, we'll call you"*).

### 5. ¿Para qué sirven los hooks? Si no se detecta ningún hook, agregue uno.
Los **hooks** (operaciones de enganche) sirven para habilitar **puntos de extensión opcionales** dentro del algoritmo sin romper el contrato ni obligar a todas las subclases a implementar código innecesario. Típicamente tienen una implementación por defecto (muchas veces vacía o con un valor booleano estándar) en la superclase.
* **Hook agregado en nuestra solución:** Se incorporó el método condicional `protected boolean debeCelebrar()` y el método de acción `protected void celebrarVictoria()`. El `Guerrero` festeja con un grito de guerra, mientras que el `Mago` sobreescribe el hook para meditar en silencio (`return false`), personalizando el flujo sin modificar la plantilla general.

### 6. ¿Por qué se considera que el patrón Template Method aplica inversión de control? Explicá quién tiene el control del flujo de ejecución y qué responsabilidad tienen las subclases.
Se considera un ejemplo clásico de IoC porque:
* **Quién tiene el control del flujo:** La **superclase abstracta** (`Personaje`). Ella es el árbitro supremo: decide rígidamente cuándo arranca el combate, qué paso se ejecuta primero, cuál sigue después, cuándo se evalúa la condición del hook y cuándo termina.
* **Qué responsabilidad tienen las subclases:** Las subclases (`Guerrero`, `Mago`) quedan relegadas a un rol subordinado o reactivo. No pueden alterar la secuencia ni decidir cuándo pelear; su única responsabilidad es suministrar la lógica concreta de cada paso individual cuando la superclase las invoque.
