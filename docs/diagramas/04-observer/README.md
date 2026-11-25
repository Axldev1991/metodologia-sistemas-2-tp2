# Consigna 4 — Sistema de Monitoreo Ambiental en Edificio Inteligente

## 1. Identificación del Patrón de Diseño
* **Patrón detectado:** **Observer** (GoF — Patrón de Comportamiento).

## 2. Justificación Técnica
La consigna describe un sistema en el que múltiples paneles visuales y consolas deben reaccionar inmediatamente a los cambios de estado producidos en diversos sensores ambientales independientes (temperatura, humedad, CO2, luminosidad).

El requerimiento determinante es:
> *"Cada vez que un sensor registra un nuevo valor, las vistas o paneles asociados deben actualizarse automáticamente sin intervención del usuario. Los usuarios del sistema pueden activar o desactivar en tiempo real qué paneles o vistas desean que reciban las actualizaciones de cada sensor. Se debe considerar la posibilidad de incorporar nuevos tipos de sensores y nuevos tipos de paneles visuales sin modificar el código existente del sistema."*

* **Por qué Observer:**
  1. **Dependencia uno a muchos desacoplada:** Define una relación 1 a N entre el sujeto observable (`Sensor`) y múltiples observadores (`ObservadorSensor`) sin que el sensor conozca la clase concreta de los paneles.
  2. **Gestión dinámica de suscripciones:** Provee métodos `suscribir(...)` y `desuscribir(...)` que permiten asociar o desvincular vistas en tiempo de ejecución según el deseo del usuario.
  3. **Extensibilidad (Principio Open/Closed):** Permite añadir nuevos tipos de sensores o nuevos tipos de paneles (ej. panel móvil, registro en base de datos) simplemente implementando `ObservadorSensor` sin alterar las clases existentes.

---

## 3. Diagrama de Clases UML Formal

```mermaid
classDiagram
    direction TB

    class Sensor {
        <<Subject>>
        -tipo: String
        -unidad: String
        -valorActual: double
        +Sensor(tipo: String, unidad: String, valorInicial: double)
        +getTipo(): String
        +getUnidad(): String
        +getValorActual(): double
        +suscribir(observador: ObservadorSensor): void
        +desuscribir(observador: ObservadorSensor): void
        +registrarNuevoValor(nuevoValor: double): void
        #notificarObservadores(): void
    }

    class Medicion {
        <<ValueObject>>
        +tipoSensor: String
        +valor: double
        +unidad: String
    }

    class ObservadorSensor {
        <<interface>>
        <<Observer>>
        +actualizar(sensor: Sensor, medicion: Medicion)* void
    }

    class PanelPrincipal {
        <<ConcreteObserver>>
        -ultimasLecturas: Map~String, Double~
        +actualizar(sensor: Sensor, medicion: Medicion) void
        +getUltimaLectura(tipo: String): Double
    }

    class PanelAlertas {
        <<ConcreteObserver>>
        -umbralCritico: double
        -alertasGeneradas: List~String~
        +PanelAlertas(umbralCritico: double)
        +actualizar(sensor: Sensor, medicion: Medicion) void
        +getAlertasGeneradas(): List~String~
    }

    class RegistroConsola {
        <<ConcreteObserver>>
        -logs: List~String~
        +actualizar(sensor: Sensor, medicion: Medicion) void
        +getLogs(): List~String~
    }

    Sensor o--> "*" ObservadorSensor : -observadores
    Sensor ..> Medicion
    ObservadorSensor <|.. PanelPrincipal
    ObservadorSensor <|.. PanelAlertas
    ObservadorSensor <|.. RegistroConsola
```

---

## 4. Participantes y Estereotipos

* **`<<Subject>>` (`Sensor`):** Mantiene la lista de observadores suscritos y dispara `notificarObservadores()` ante cada cambio de valor en la variable física.
* **`<<Observer>>` (`ObservadorSensor`):** Interfaz común que define el contrato `actualizar(sensor, medicion)` que todo panel o suscriptor debe cumplir.
* **`<<ConcreteObserver>>` (`PanelPrincipal`, `PanelAlertas`, `RegistroConsola`):** Paneles y componentes gráficos que reaccionan de forma particular al recibir los nuevos valores.
* **`<<ValueObject>>` (`Medicion`):** Contenedor inmutable de datos que transporta la lectura (tipo, valor, unidad).

