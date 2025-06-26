# Análisis Arquitectónico de la Aplicación

## 1. Componentes Funcionales y sus Relaciones
- La aplicación sigue una arquitectura monolítica en capas claramente definidas:
  - **Capa Web (`web/`)**: Controladores REST que manejan las peticiones HTTP
  - **Capa de Servicios (`service/`)**: Implementa la lógica de negocio
  - **Capa de Repositorios (`repository/`)**: Maneja el acceso a datos
  - **Capa de Dominio (`domain/`)**: Define las entidades y modelos de datos
- Las relaciones siguen un patrón jerárquico:
  - Los controladores inyectan y usan servicios
  - Los servicios inyectan y usan repositorios
  - Los repositorios manejan las entidades del dominio

## 2. Despliegue de Componentes
- La aplicación se despliega como un único artefacto JAR (definido en `pom.xml`)
- Utiliza PostgreSQL como base de datos (configurado en `application.properties`)
- Se puede desplegar en:
  - Contenedor Docker (hay configuración Docker disponible)
  - Servidor de aplicaciones Java tradicional
  - Servicios cloud que soporten Spring Boot

## 3. Interacción con Fuentes de Datos
- Utiliza Spring Data JPA para la persistencia
- Configuración de base de datos PostgreSQL:
  - URL: jdbc:postgresql://localhost:5432/demo
  - Esquema gestionado por Flyway (migraciones en `db/migration`)
- Los repositorios extienden `JpaRepository` para operaciones CRUD
- Uso de anotaciones JPA en las entidades (@Entity, @Table, etc.)

## 4. Patrones y Tácticas Arquitectónicas
- **Patrón de Capas**: Separación clara de responsabilidades
- **Inyección de Dependencias**: Uso extensivo a través de Spring
- **DTO Pattern**: Para transferencia de datos entre capas
- **Repository Pattern**: Abstracción del acceso a datos
- **Domain Model Pattern**: Entidades ricas en funcionalidad

## 5. Tecnologías y Frameworks
- **Spring Boot 2.2.2**: Framework principal
- **Spring Data JPA**: Persistencia de datos
- **Flyway**: Migración de base de datos
- **PostgreSQL**: Base de datos relacional
- **Swagger**: Documentación de API
- **Lombok**: Reducción de código boilerplate
- **Maven**: Gestión de dependencias y construcción

## 6. Módulos y Capas Principales
### Módulos de Dominio:
- Cart (Carritos)
- Order (Pedidos)
- Product (Productos)
- Customer (Clientes)
- Payment (Pagos)
- Review (Reseñas)
- Category (Categorías)

### Capas:
1. **Web**: Controladores REST y DTOs
2. **Service**: Lógica de negocio
3. **Repository**: Acceso a datos
4. **Domain**: Entidades
5. **Configuration**: Configuraciones de Spring

## 7. Dependencias entre Servicios
- No es una arquitectura de microservicios
- Dependencias internas entre componentes:
  - CartService → CustomerService
  - OrderService → CartService
  - ProductService → CategoryService
  - PaymentService → OrderService

## 8. Seguridad y Autenticación
- No se identificó implementación de seguridad explícita
- No hay uso visible de Spring Security
- Se recomienda implementar:
  - Autenticación
  - Autorización
  - Protección de endpoints

## 9. Escalabilidad y Balanceo
- Arquitectura monolítica permite escalado vertical
- Posible escalado horizontal mediante:
  - Replicación de la aplicación
  - Balanceador de carga externo
  - Sesiones distribuidas

## 10. Manejo de Errores y Resiliencia
- Manejo básico de excepciones con try-catch
- No se identificaron patrones de resiliencia avanzados
- Se recomienda implementar:
  - Circuit Breakers
  - Fallbacks
  - Retry patterns

## 11. Manejo de Capas
- Separación clara de responsabilidades
- Uso de interfaces y abstracciones
- Comunicación unidireccional entre capas
- DTOs para transferencia de datos

## 12. API y Comunicación
La aplicación expone una API REST bajo el prefijo `/api`. Principales endpoints:

### Productos
```
GET    /api/products     → Listar productos
POST   /api/products     → Crear producto
GET    /api/products/{id} → Obtener producto
DELETE /api/products/{id} → Eliminar producto
```

### Carritos
```
GET    /api/carts             → Listar carritos
GET    /api/carts/active      → Listar carritos activos
GET    /api/carts/{id}        → Obtener carrito
POST   /api/carts/customer/{id} → Crear carrito
DELETE /api/carts/{id}        → Eliminar carrito
```

### Pedidos
```
GET    /api/orders           → Listar pedidos
GET    /api/orders/{id}      → Obtener pedido
GET    /api/orders/customer/{id} → Pedidos por cliente
DELETE /api/orders/{id}      → Eliminar pedido
```

### Clientes
```
GET    /api/customers        → Listar clientes
GET    /api/customers/active → Clientes activos
POST   /api/customers        → Crear cliente
DELETE /api/customers/{id}   → Eliminar cliente
```

La documentación detallada de la API está disponible a través de Swagger UI cuando la aplicación está en ejecución.