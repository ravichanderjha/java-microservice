Service discovery and registration are essential components in a microservices architecture. They enable dynamic communication between microservices by allowing them to find and register themselves without hardcoded endpoints. Service discovery tools like Eureka or Consul provide a centralized registry where microservices can register and discover each other. Below, I'll describe how service discovery works and provide example programs using Eureka as the service discovery tool.

Service Discovery:
Service discovery is a mechanism that allows microservices to locate and communicate with each other without requiring fixed IP addresses or explicit configuration. In this approach, microservices register themselves with a service registry when they start up, providing information about their location and available functionalities. Other microservices can then query the service registry to discover and communicate with the desired services.

Using Eureka for Service Discovery:
Eureka is a popular service discovery tool from Netflix. It uses a RESTful API and a web-based user interface for registering and discovering services. Let's see how to set up a basic example with Eureka and two microservices: a "ProductService" and an "OrderService."

1. Eureka Server:
   First, we need to set up the Eureka server, which will act as the registry for microservices.

```java
// EurekaServerApplication.java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class EurekaServerApplication {
    public static void main(String[] args) {
        SpringApplication.run(EurekaServerApplication.class, args);
    }
}
```

2. ProductService:
```java
// ProductServiceApplication.java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ProductServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ProductServiceApplication.class, args);
    }
}
```

```java
// ProductController.java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

    @GetMapping
    public String getAllProducts() {
        return "List of all products";
    }
}
```

3. OrderService:
```java
// OrderServiceApplication.java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class OrderServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(OrderServiceApplication.class, args);
    }
}
```

```java
// OrderController.java
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/orders")
public class OrderController {

    @GetMapping
    public String getAllOrders() {
        return "List of all orders";
    }
}
```

Running the Example:
1. Start the Eureka Server by running the `EurekaServerApplication`.
2. Start the `ProductService` by running the `ProductServiceApplication`.
3. Start the `OrderService` by running the `OrderServiceApplication`.

Now, both microservices are registered with the Eureka server and can be discovered by other services.

4. Client Service:
```java
// ClientServiceApplication.java
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@SpringBootApplication
@EnableDiscoveryClient
public class ClientServiceApplication {
    public static void main(String[] args) {
        SpringApplication.run(ClientServiceApplication.class, args);
    }
}
```

```java
// ClientController.java
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.List;

@RestController
public class ClientController {

    private final DiscoveryClient discoveryClient;

    @Autowired
    public ClientController(DiscoveryClient discoveryClient) {
        this.discoveryClient = discoveryClient;
    }

    @GetMapping("/services")
    public List<String> getAllServices() {
        return discoveryClient.getServices();
    }

    @GetMapping("/products")
    public String getProducts() {
        List<ServiceInstance> instances = discoveryClient.getInstances("product-service");
        if (!instances.isEmpty()) {
            ServiceInstance productService = instances.get(0);
            String productServiceUrl = productService.getUri().toString();
            // Use productServiceUrl to communicate with ProductService API
            return "Fetching products from ProductService...";
        }
        return "ProductService is not available.";
    }

    @GetMapping("/orders")
    public String getOrders() {
        List<ServiceInstance> instances = discoveryClient.getInstances("order-service");
        if (!instances.isEmpty()) {
            ServiceInstance orderService = instances.get(0);
            String orderServiceUrl = orderService.getUri().toString();
            // Use orderServiceUrl to communicate with OrderService API
            return "Fetching orders from OrderService...";
        }
        return "OrderService is not available.";
    }
}
```

Running the Client Service:
Start the `ClientService` by running the `ClientServiceApplication`. The `ClientService` can now discover the `ProductService` and `OrderService` using the Eureka server and communicate with them dynamically.

This example demonstrates how microservices can dynamically register and discover each other using Eureka for service discovery. It allows for flexible and scalable communication between microservices, making it easier to build and manage a distributed system.