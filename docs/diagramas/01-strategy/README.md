# Consigna 1 — Sistema de Gestión de Transacciones Bancarias

## 1. Identificación del Patrón de Diseño
* **Patrón detectado:** **Strategy** (GoF — Patrón de Comportamiento).

## 2. Justificación Técnica
El problema requiere soportar tres operaciones bancarias distintas (**depósito**, **extracción** y **transferencia**), cada una con una secuencia específica de pasos y validaciones.

El requisito determinante es:
> *"El sistema debe permitir seleccionar y ejecutar cualquiera de los tipos de transacciones disponibles, así como cambiar el tipo de operación en cualquier momento antes de confirmar la transacción."*

* **Por qué Strategy:**
  1. **Familia de algoritmos intercambiables:** Encapsula cada operación en su propia clase concreta que implementa una interfaz común (`TransaccionStrategy`).
  2. **Intercambio en tiempo de ejecución:** El objeto contexto (`GestorTransacciones`) mantiene una referencia por composición/agregación a la estrategia activa. Gracias al polimorfismo y a un método setter (`setEstrategia`), el cliente puede conmutar la operación elegida dinámicamente sin acoplarse a condicionales rígidos (`if / switch`).
  3. **Principio Open/Closed (OCP):** Permite incorporar nuevas transacciones en el futuro (ej. pago de servicios, plazo fijo) creando una nueva clase concreta sin modificar el gestor existente ni las otras operaciones.

---

## 3. Diagrama de Clases UML Formal

```mermaid
classDiagram
    direction TB

    class GestorTransacciones {
        <<Context>>
        -monto: BigDecimal
        +GestorTransacciones()
        +GestorTransacciones(estrategiaInicial: TransaccionStrategy)
        +setEstrategia(estrategia: TransaccionStrategy): void
        +getEstrategia(): TransaccionStrategy
        +prepararTransaccion(monto: BigDecimal, origen: CuentaBancaria, destino: CuentaBancaria): void
        +confirmarTransaccion(): void
    }

    class CuentaBancaria {
        -cbu: String
        -titular: String
        -saldo: BigDecimal
        +CuentaBancaria(cbu: String, titular: String, saldoInicial: BigDecimal)
        +getCbu(): String
        +getTitular(): String
        +getSaldo(): BigDecimal
        +acreditar(monto: BigDecimal): void
        +debitar(monto: BigDecimal): void
    }

    class TransaccionStrategy {
        <<interface>>
        <<Strategy>>
        +ejecutar(monto: BigDecimal, cuentaOrigen: CuentaBancaria, cuentaDestino: CuentaBancaria)* void
    }

    class DepositoStrategy {
        <<ConcreteStrategy>>
        +ejecutar(monto: BigDecimal, cuentaOrigen: CuentaBancaria, cuentaDestino: CuentaBancaria) void
    }

    class ExtraccionStrategy {
        <<ConcreteStrategy>>
        +ejecutar(monto: BigDecimal, cuentaOrigen: CuentaBancaria, cuentaDestino: CuentaBancaria) void
    }

    class TransferenciaStrategy {
        <<ConcreteStrategy>>
        +ejecutar(monto: BigDecimal, cuentaOrigen: CuentaBancaria, cuentaDestino: CuentaBancaria) void
    }

    GestorTransacciones -right-> "1\n-estrategia" TransaccionStrategy
    GestorTransacciones -down-> "1\n-cuentaOrigen" CuentaBancaria
    GestorTransacciones -down-> "0..1\n-cuentaDestino" CuentaBancaria
    TransaccionStrategy <|.. DepositoStrategy
    TransaccionStrategy <|.. ExtraccionStrategy
    TransaccionStrategy <|.. TransferenciaStrategy
```

---

## 4. Participantes y Estereotipos

* **`<<Context>>` (`GestorTransacciones`):** Es el punto de contacto con el cliente. Almacena los parámetros de la operación y delega la ejecución del algoritmo a la instancia de `TransaccionStrategy` que tenga configurada en ese momento.
* **`<<Strategy>>` (`TransaccionStrategy`):** Interfaz que declara la firma del método común `ejecutar(...)` que toda operación bancaria debe implementar.
* **`<<ConcreteStrategy>>` (`DepositoStrategy`, `ExtraccionStrategy`, `TransferenciaStrategy`):** Implementan las reglas de negocio y secuencias de validación específicas de cada transacción.
* **Elemento de Dominio (`CuentaBancaria`):** Encapsula el estado (saldo, CBU, titular) y garantiza la consistencia del dinero mediante métodos controlados `acreditar` y `debitar`.
