# Princípios de Design de Software em Java

Este repositório demonstra a aplicação de quatro princípios de design de software (de um conjunto de sete) em um único projeto Java modular, com exemplos de código e explicações detalhadas.

## Índice

1. [Single Responsibility Principle (SRP)](https://chatgpt.com/c/6835204f-1a14-8005-8035-93f8543ad87d#single-responsibility-principle)

2. [Open-Closed Principle (OCP)](https://chatgpt.com/c/6835204f-1a14-8005-8035-93f8543ad87d#open-closed-principle)

3. [Prefira Composição a Herança](https://chatgpt.com/c/6835204f-1a14-8005-8035-93f8543ad87d#composition-over-inheritance)

4. [Dependency Inversion Principle (DIP)](https://chatgpt.com/c/6835204f-1a14-8005-8035-93f8543ad87d#dependency-inversion-principle)

---

## Estrutura do Projeto

Este é um projeto Maven multi-módulos chamado `design-principles`, com quatro submódulos.

```
design-principles/          (projeto raiz)
├─ pom.xml                  (pom pai indicando módulos)
│
├─ srp/                     (Single Responsibility Principle)
│   ├─ pom.xml
│   └─ src/main/java/com/example/srp/
│       ├─ Invoice.java
│       ├─ InvoicePrinter.java
│       └─ InvoiceRepository.java
│
├─ ocp/                     (Open-Closed Principle)
│   ├─ pom.xml
│   └─ src/main/java/com/example/ocp/
│       ├─ Shape.java
│       ├─ Circle.java
│       ├─ Rectangle.java
│       └─ AreaCalculator.java
│
├─ composition/             (Composição ao invés de Herança)
│   ├─ pom.xml
│   └─ src/main/java/com/example/composition/
│       ├─ Notifier.java
│       ├─ EmailNotifier.java
│       ├─ SmsNotifier.java
│       └─ UserService.java
│
└─ dip/                     (Dependency Inversion Principle)
    ├─ pom.xml
    └─ src/main/java/com/example/dip/
        ├─ IEmailService.java
        ├─ SmtpEmailService.java
        ├─ OrderProcessor.java
        └─ App.java          (injeção manual)
```

Cada módulo possui seu próprio `pom.xml` e código isolado, mas todos compilam juntos a partir do projeto raiz.

---

## 1. Single Responsibility Principle (SRP)

**O que é? Para que serve?**

O SRP estabelece que cada classe deve ter apenas uma razão para mudar, ou seja, uma única responsabilidade. Isso facilita manutenção, testes e reduz acoplamento.

### Código (Java)

```java
// Invoice.java
package com.example.srp;

public class Invoice {
    private final double amount;
    public Invoice(double amount) { this.amount = amount; }
    public double getAmount() { return amount; }
}

// InvoicePrinter.java
package com.example.srp;

public class InvoicePrinter {
    public void print(Invoice invoice) {
        System.out.println("Invoice amount: " + invoice.getAmount());
    }
}

// InvoiceRepository.java
package com.example.srp;

import java.util.*;

public class InvoiceRepository {
    private final List<Invoice> storage = new ArrayList<>();
    public void save(Invoice invoice) { storage.add(invoice); }
    public List<Invoice> findAll() { return Collections.unmodifiableList(storage); }
}
```

**Exemplo de uso**

```java
Invoice invoice = new Invoice(250.0);
new InvoicePrinter().print(invoice);
new InvoiceRepository().save(invoice);
```

**Onde o princípio está sendo usado?**

- `Invoice` modela os dados.

- `InvoicePrinter` cuida apenas de imprimir.

- `InvoiceRepository` gerencia persistência em memória.

Cada classe tem responsabilidade única e independente.

---

## 2. Open-Closed Principle (OCP)

**O que é? Para que serve?**

O OCP determina que classes devem estar abertas para extensão, mas fechadas para modificação. Novos comportamentos são adicionados sem alterar o código existente.

### Código (Java)

```java
// Shape.java
package com.example.ocp;

public interface Shape {
    double area();
}

// Circle.java
package com.example.ocp;

public class Circle implements Shape {
    private final double radius;
    public Circle(double radius) { this.radius = radius; }
    public double area() { return Math.PI * radius * radius; }
}

// Rectangle.java
package com.example.ocp;

public class Rectangle implements Shape {
    private final double width, height;
    public Rectangle(double width, double height) {
        this.width = width; this.height = height;
    }
    public double area() { return width * height; }
}

// AreaCalculator.java
package com.example.ocp;

import java.util.List;

public class AreaCalculator {
    public double totalArea(List<Shape> shapes) {
        return shapes.stream()
                     .mapToDouble(Shape::area)
                     .sum();
    }
}
```

**Exemplo de uso**

```java
List<Shape> shapes = List.of(new Circle(2), new Rectangle(3, 4));
double total = new AreaCalculator().totalArea(shapes);
System.out.println("Total area: " + total);
```

**Onde o princípio está sendo usado?**

- `AreaCalculator` não precisa mudar ao adicionar novos `Shape`.

- Se criarmos `Triangle implements Shape`, basta passar a instância à lista.

---

## 3. Prefira Composição a Herança

**O que é? Para que serve?**

Em vez de estender classes, delegue responsabilidades a objetos internos via composição. Isso reduz acoplamento e aumenta flexibilidade.

### Código (Java)

```java
// Notifier.java
package com.example.composition;

public interface Notifier {
    void send(String message);
}

// EmailNotifier.java
package com.example.composition;

public class EmailNotifier implements Notifier {
    public void send(String message) {
        System.out.println("Enviando email: " + message);
    }
}

// SmsNotifier.java
package com.example.composition;

public class SmsNotifier implements Notifier {
    public void send(String message) {
        System.out.println("Enviando SMS: " + message);
    }
}

// UserService.java
package com.example.composition;

public class UserService {
    private final Notifier notifier;
    public UserService(Notifier notifier) {
        this.notifier = notifier;
    }
    public void notifyUser(int userId) {
        // lógica de negócio...
        notifier.send("Usuário " + userId + " notificado.");
    }
}
```

**Exemplo de uso**

```java
UserService service = new UserService(new EmailNotifier());
service.notifyUser(42);
```

**Onde o princípio está sendo usado?**

- `UserService` não herda de `Notifier`, mas recebe por composição.

- Para alterar o canal, basta instanciar `SmsNotifier`.

---

## 4. Dependency Inversion Principle (DIP)

**O que é? Para que serve?**

O DIP define que módulos de alto nível não devem depender de módulos de baixo nível; ambos devem depender de abstrações (interfaces). Detalhes implementacionais dependem de abstrações.

### Código (Java)

```java
// IEmailService.java
package com.example.dip;

public interface IEmailService {
    void sendEmail(String to, String subject, String body);
}

// SmtpEmailService.java
package com.example.dip;

public class SmtpEmailService implements IEmailService {
    public void sendEmail(String to, String subject, String body) {
        // lógica SMTP...
        System.out.println("Email enviado para " + to);
    }
}

// OrderProcessor.java
package com.example.dip;

public class OrderProcessor {
    private final IEmailService emailService;
    public OrderProcessor(IEmailService emailService) {
        this.emailService = emailService;
    }
    public void process(int orderId) {
        // lógica de processamento...
        emailService.sendEmail(
            "cliente@exemplo.com",
            "Pedido processado",
            "Pedido " + orderId + " concluído.");
    }
}

// App.java (inicialização)
package com.example.dip;

public class App {
    public static void main(String[] args) {
        IEmailService emailService = new SmtpEmailService();
        OrderProcessor processor = new OrderProcessor(emailService);
        processor.process(1001);
    }
}
```

**Exemplo de uso**

```sh
mvn -q compile exec:java -pl dip -Dexec.mainClass="com.example.dip.App"
```

**Onde o princípio está sendo usado?**

- `OrderProcessor` depende de `IEmailService`, não da implementação.

- Podemos criar outra classe que implemente `IEmailService` sem mudar `OrderProcessor`.
