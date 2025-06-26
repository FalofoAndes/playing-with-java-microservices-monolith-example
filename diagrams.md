# Estructura de Carpetas del Proyecto Backend

## Estructura Principal

```
/Users/rojo/MISO/playing-with-java-microservices-monolith-example
│
├── architecture.md  # Análisis de la arquitectura del proyecto.
├── pom.xml           # Archivo de configuración de Maven.
├── README.md         # Documentación general del proyecto.
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   └── com/targa/labs/myboutique/
│   │   │       ├── MyboutiqueApplication.java  # Clase principal para iniciar la aplicación Spring Boot.
│   │   │       ├── common/                     # Clases comunes y utilitarias.
│   │   │       ├── configuration/             # Configuraciones de la aplicación.
│   │   │       │   └── SwaggerConfiguration.java  # Configuración de Swagger para la documentación de la API.
│   │   │       ├── domain/                    # Entidades del modelo de datos.
│   │   │       │   ├── AbstractEntity.java    # Clase base para todas las entidades con atributos comunes.
│   │   │       │   ├── Address.java           # Entidad que representa una dirección.
│   │   │       │   ├── Cart.java              # Entidad que representa un carrito de compras.
│   │   │       │   ├── Category.java          # Entidad que representa una categoría de productos.
│   │   │       │   ├── Customer.java          # Entidad que representa un cliente.
│   │   │       │   ├── Order.java             # Entidad que representa un pedido.
│   │   │       │   ├── OrderItem.java         # Entidad que representa un ítem dentro de un pedido.
│   │   │       │   ├── Payment.java           # Entidad que representa un pago.
│   │   │       │   ├── Product.java           # Entidad que representa un producto.
│   │   │       │   ├── Review.java            # Entidad que representa una reseña de producto.
│   │   │       │   └── enumeration/           # Enumeraciones utilizadas en las entidades.
│   │   │       ├── repository/                # Repositorios para acceso a datos.
│   │   │       │   ├── CartRepository.java    # Repositorio para la entidad Cart.
│   │   │       │   ├── CategoryRepository.java # Repositorio para la entidad Category.
│   │   │       │   ├── CustomerRepository.java # Repositorio para la entidad Customer.
│   │   │       │   ├── OrderItemRepository.java # Repositorio para la entidad OrderItem.
│   │   │       │   ├── OrderRepository.java   # Repositorio para la entidad Order.
│   │   │       │   ├── PaymentRepository.java # Repositorio para la entidad Payment.
│   │   │       │   ├── ProductRepository.java # Repositorio para la entidad Product.
│   │   │       │   └── ReviewRepository.java  # Repositorio para la entidad Review.
│   │   │       ├── service/                   # Servicios con la lógica de negocio.
│   │   │       │   ├── AddressService.java    # Servicio para gestionar direcciones.
│   │   │       │   ├── CartService.java       # Servicio para gestionar carritos de compras.
│   │   │       │   ├── CategoryService.java   # Servicio para gestionar categorías.
│   │   │       │   ├── CustomerService.java   # Servicio para gestionar clientes.
│   │   │       │   ├── OrderItemService.java  # Servicio para gestionar ítems de pedidos.
│   │   │       │   ├── OrderService.java      # Servicio para gestionar pedidos.
│   │   │       │   ├── PaymentService.java    # Servicio para gestionar pagos.
│   │   │       │   ├── ProductService.java    # Servicio para gestionar productos.
│   │   │       │   └── ReviewService.java     # Servicio para gestionar reseñas.
│   │   │       ├── web/                       # Controladores para la API REST.
│   │   │       │   ├── CartResource.java      # Controlador para gestionar carritos de compras.
│   │   │       │   ├── CategoryResource.java  # Controlador para gestionar categorías.
│   │   │       │   ├── CustomerResource.java  # Controlador para gestionar clientes.
│   │   │       │   ├── OrderItemResource.java # Controlador para gestionar ítems de pedidos.
│   │   │       │   ├── OrderResource.java     # Controlador para gestionar pedidos.
│   │   │       │   ├── PaymentResource.java   # Controlador para gestionar pagos.
│   │   │       │   ├── ProductResource.java   # Controlador para gestionar productos.
│   │   │       │   ├── ReviewResource.java    # Controlador para gestionar reseñas.
│   │   │       │   └── dto/                   # Objetos de transferencia de datos.
│   │   ├── resources/
│   │   │   ├── application.properties         # Configuración de la aplicación.
│   │   │   └── db/migration/
│   │   │       └── V1__init_app.sql           # Script de inicialización de la base de datos.
│   ├── test/
│   │   ├── java/
│   │   │   └── com/targa/labs/myboutique/
│   │   │       ├── domain/                    # Pruebas para las entidades del dominio.
│   │   │       └── service/                   # Pruebas para los servicios.
│   │   │           └── AddressServiceTest.java # Prueba unitaria para AddressService.
├── target/                                    # Archivos generados por Maven.
```

## Descripción de Carpetas y Archivos

- **architecture.md:** Contiene el análisis de la arquitectura del proyecto.
- **pom.xml:** Archivo de configuración de Maven para gestionar dependencias y plugins.
- **README.md:** Documentación general del proyecto.
- **src/main/java:** Código fuente principal de la aplicación.
  - **MyboutiqueApplication.java:** Clase principal para iniciar la aplicación Spring Boot.
  - **common:** Clases comunes y utilitarias.
  - **configuration:** Configuraciones de la aplicación, como Swagger.
  - **domain:** Entidades del modelo de datos.
  - **repository:** Repositorios para acceso a datos.
  - **service:** Servicios con la lógica de negocio.
  - **web:** Controladores y DTOs para la API REST.
- **src/main/resources:** Recursos de la aplicación, como configuración y scripts de base de datos.
- **src/test/java:** Pruebas unitarias y de integración.
- **target:** Archivos generados por Maven, como clases compiladas y recursos empaquetados.

# Diagramas de Arquitectura

## Diagrama de Componentes
```mermaid
flowchart TD
    Web[Web Layer] -->|Calls| Service[Service Layer]
    Service -->|Interacts with| Repository[Repository Layer]
    Repository -->|Accesses| Database[(Database)]
    Web -->|Exposes| API[REST API]
```

## Diagrama de Despliegue
```mermaid
flowchart TD
    User[User] -->|Sends Requests| LoadBalancer[Load Balancer]
    LoadBalancer -->|Distributes Traffic| AppServer[Application Server]
    AppServer -->|Queries| Database[(Database)]
```

## Diagrama de Flujo de Datos
```mermaid
sequenceDiagram
    participant User
    participant Web
    participant Service
    participant Repository
    participant Database

    User->>Web: HTTP Request
    Web->>Service: Calls Service Method
    Service->>Repository: Fetch Data
    Repository->>Database: Query
    Database-->>Repository: Result
    Repository-->>Service: Data
    Service-->>Web: Response
    Web-->>User: HTTP Response
```

## Diagrama de Clases
```mermaid
classDiagram
    class AbstractEntity {
        +Long id
        +Date createdAt
        +Date updatedAt
    }

    class Address {
        +String street
        +String city
        +String state
        +String zipCode
    }

    class Cart {
        +List~OrderItem~ items
        +Customer customer
    }

    class Customer {
        +String name
        +String email
    }

    class Order {
        +List~OrderItem~ items
        +Payment payment
    }

    class Product {
        +String name
        +Double price
    }

    AbstractEntity <|-- Address
    AbstractEntity <|-- Cart
    AbstractEntity <|-- Customer
    AbstractEntity <|-- Order
    AbstractEntity <|-- Product
    Order o-- OrderItem
    Cart o-- OrderItem
    OrderItem o-- Product
    Order o-- Payment
```

## Diagrama de Paquetes
```mermaid
flowchart TD
    com.targa.labs.myboutique -->|Contains| common
    com.targa.labs.myboutique -->|Contains| configuration
    com.targa.labs.myboutique -->|Contains| domain
    com.targa.labs.myboutique -->|Contains| repository
    com.targa.labs.myboutique -->|Contains| service
    com.targa.labs.myboutique -->|Contains| web
```

## Diagramas de Clases por Carpeta

### Carpeta `domain`
```mermaid
classDiagram
    class AbstractEntity {
        +Long id
        +Date createdAt
        +Date updatedAt
    }

    class Address {
        +String street
        +String city
        +String state
        +String zipCode
    }

    class Cart {
        +List~OrderItem~ items
        +Customer customer
    }

    class Customer {
        +String name
        +String email
    }

    class Order {
        +List~OrderItem~ items
        +Payment payment
    }

    class Product {
        +String name
        +Double price
    }

    AbstractEntity <|-- Address
    AbstractEntity <|-- Cart
    AbstractEntity <|-- Customer
    AbstractEntity <|-- Order
    AbstractEntity <|-- Product
    Order o-- OrderItem
    Cart o-- OrderItem
    OrderItem o-- Product
    Order o-- Payment
```

### Carpeta `service`
```mermaid
classDiagram
    class AddressService {
        +Address getAddress(Long id)
        +List~Address~ getAllAddresses()
    }

    class CartService {
        +Cart getCart(Long id)
        +void addItemToCart(Long cartId, OrderItem item)
    }

    class CustomerService {
        +Customer getCustomer(Long id)
        +List~Customer~ getAllCustomers()
    }

    AddressService --> Address
    CartService --> Cart
    CustomerService --> Customer
```

### Carpeta `web`
```mermaid
classDiagram
    class CartResource {
        +ResponseEntity~Cart~ getCart(Long id)
        +ResponseEntity~Void~ addItemToCart(Long cartId, OrderItem item)
    }

    class CustomerResource {
        +ResponseEntity~Customer~ getCustomer(Long id)
        +ResponseEntity~List~Customer~~ getAllCustomers()
    }

    CartResource --> CartService
    CustomerResource --> CustomerService
```

### Carpeta `repository`
```mermaid
classDiagram
    class CartRepository {
        +Cart findById(Long id)
        +List~Cart~ findAll()
    }

    class CustomerRepository {
        +Customer findById(Long id)
        +List~Customer~ findAll()
    }

    CartRepository --> Cart
    CustomerRepository --> Customer
```