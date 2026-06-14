# E-Commerce Core en Java Puro

## Descripción:
TechLab CLI es una aplicación de consola desarrollada íntegramente en **Java Vanilla** (sin frameworks). Este proyecto implementa la lógica central de negocio para un sistema de gestión de e-commerce, carritos de compra y facturación.

Su propósito principal es demostrar un sólido dominio de la **Programación Orientada a Objetos (POO)**, la gestión de estructuras de datos en memoria, y el control de reglas de negocio antes de escalar a una arquitectura web.

## Funcionalidades Principales:
* **Gestión de Productos (CRUD):** Alta, baja, modificación y listado de productos en el catálogo interactivo.
* **Sistema de Pedidos:** Generación de órdenes de compra sumando subtotales por línea de pedido.
* **Control de Stock Inteligente:** Validación en tiempo real del inventario al momento de procesar un carrito, descontando unidades automáticamente.
* **Manejo de Excepciones Personalizadas:** Respuestas controladas ante escenarios de error en el negocio (ej. `NotEnoughStockException`, `ProductNotFoundException`).

## 🛠️ Tecnologías y Conceptos Aplicados
* **Lenguaje:** Java (JDK 17+)
* **Paradigma:** Programación Orientada a Objetos (Herencia, Polimorfismo, Encapsulamiento).
* **Almacenamiento:** Colecciones nativas (`ArrayList`) para manejo dinámico de datos en memoria.
