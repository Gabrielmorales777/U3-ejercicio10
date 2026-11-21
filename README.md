# U3-ejercicio10
# 💳 Billetera Virtual y Límites de Transferencia

Ejercicio de **Programación Orientada a Objetos en Java** que modela una billetera virtual aplicando el principio de **encapsulamiento**. El saldo, el límite diario de transferencia y el PIN de titularidad son privados: cada operación se valida internamente antes de aprobarse, y la configuración del límite solo es posible tras comprobar la titularidad del usuario.

## 🎯 Objetivo

Administrar una billetera digital restringiendo las transacciones según límites configurados de forma privada, garantizando que ninguna entidad externa pueda saltarse las reglas de seguridad.

## ✨ Características

- Atributos privados: `alias`, `saldo`, `limiteDiarioTransferencia` y un acumulado diario de lo transferido.
- `transferir(double monto)`: verifica a la vez que el monto no supere el saldo disponible ni rebase el límite diario.
- `configurarLimiteDiario(double nuevoLimite, String pin)`: actualiza el límite solo si se valida la titularidad.
- `iniciarNuevoDia(String pin)`: reinicia el acumulado diario, también protegido por PIN.
- Mensajes de rechazo que indican cuál regla falló (saldo, límite o ambas).
- Sin `setSaldo()` ni `getPin()`: el saldo solo cambia al transferir y el PIN nunca se expone.
## 📚 Conceptos aplicados

Encapsulamiento · Modificadores de acceso · Ocultación de información · Reglas de negocio · Validación de datos · Constructores · Métodos privados

![U3 ejercicio10](U3%20ejercicio10.png)
