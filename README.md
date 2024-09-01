# Spring Boot MQ

## Overview

A Spring Boot application integrating with RabbitMQ for message queuing, featuring REST endpoints for notifications and orders.

## Getting Started

### Prerequisites

- Java 17+
- Maven
- Docker (for RabbitMQ)

### Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/youssefGamalMohamed/spring-boot-mq.git
   cd spring-boot-mq
   ```

2. **Build and run the application:**

   ```bash
   mvn clean install
   mvn spring-boot:run
   ```

### RabbitMQ with Docker

1. **Run RabbitMQ container:**

   ```bash
   docker run -d --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:management
   ```

2. **Access RabbitMQ UI:**

   Open [http://localhost:15672](http://localhost:15672) and log in with:
   - **Username**: `guest`
   - **Password**: `guest`

### Configuration

The application configuration is specified in `src/main/resources/application.yml`:

```yaml
spring:
  application:
    name: mq-examples
  rabbitmq:
    host: localhost
    port: 5672
    username: guest
    password: guest

server:
  port: 9090

logging:
  level:
    org.springframework.amqp: INFO
    org.springframework.amqp.rabbit: INFO
    org.springframework.amqp.rabbit.connection: INFO

rabbitmq:
  queues-names:
    order_inventory_service_queue: order_inventory_service_queue
    notification_system_user1_queue: notification_system_user1_queue
    notification_system_user2_queue: notification_system_user2_queue
  exchange-names:
    ecommerce_exchange: ecommerce_exchange
    notification_topic_exchange: notification_topic_exchange
  routing-keys:
    order_inventory_service_routing_key: order_inventory_service_routing_key
    notification_service_routing_default_key: notification_service_routing_default_key
```

### REST Endpoints

#### Notifications

- **Endpoint**: `/api/v1/notifications`
- **Method**: `POST`
- **Request Body**: `NotificationDto`
  
  ```json
  {
    "title": "string",
    "content": "string"
  }
  ```

- **Description**: Sends a notification message to the RabbitMQ notification queue.

#### Orders

- **Endpoint**: `/api/v1/orders`
- **Method**: `POST`
- **Request Body**: `OrderDto`
  
  ```json
  {
    "cartItems": [
      {
        "product": {
          "name": "string",
          "quantity": 0,
          "price": 0.0
        },
        "orderedQuantityFromProduct": 0
      }
    ]
  }
  ```

- **Description**: Sends an order message to the RabbitMQ order queue.

### Data Models

- **CartItem**

  ```java
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public class CartItem implements Serializable {
      private ProductDto product;
      private Integer orderedQuantityFromProduct;
  }
  ```

- **NotificationDto**

  ```java
  @Data
  @AllArgsConstructor
  @Builder
  public class NotificationDto implements Serializable {
      private String title;
      private String content;
  }
  ```

- **ProductDto**

  ```java
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public class ProductDto implements Serializable {
      private String name;
      private Integer quantity;
      private Double price;
  }
  ```

- **OrderDto**

  ```java
  @Data
  @Builder
  @AllArgsConstructor
  @NoArgsConstructor
  public class OrderDto implements Serializable {
      private Set<CartItem> cartItems;
  }
  ```
