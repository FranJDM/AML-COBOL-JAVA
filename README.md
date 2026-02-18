# Nexus Core AML - Integración Java-COBOL 🏦

Este proyecto es un sistema híbrido de **Core Bancario** que integra una capa moderna de microservicios en **Java 17** con un motor transaccional de alto rendimiento en **GnuCOBOL**.

## 🚀 Características
- **Interoperabilidad Nativa:** Comunicación bidireccional mediante JNI (Java Native Interface) y C.
- **Motor AML (Anti-Money Laundering):** Interceptor en Java que valida reglas de fraude antes de procesar en el Core.
- **Arquitectura Legacy Modernizada:** Demostración de cómo mantener sistemas críticos (Mainframe) bajo interfaces modernas.

## 🛠️ Desafíos Técnicos Superados
- **Alineación de Memoria:** Implementación de empaquetado de estructuras en C (`#pragma pack`) para coincidir con la `LINKAGE SECTION` de COBOL.
- **Arquitectura de 64 bits:** Compilación y enlazado de librerías dinámicas en entornos Windows mediante MSYS2/UCRT64.
- **Data Mapping:** Sincronización de tipos de datos complejos como `double` (Java) a `COMP-2` (COBOL).

## 📋 Requisitos
- GnuCOBOL 3.x
- JDK 17+
- GCC (MinGW-w64 / MSYS2)

---
*Desarrollado como prueba de concepto de modernización de sistemas financieros.*
