# Solid Principles in Object Oriented Programming

## Introduction

SOLID is an acronym that represents five principles of object-oriented programming and design, aimed at making software systems more maintainable and scalable. The SOLID principles are:

1. **Single Responsibility Principle (SRP)** - A class should have only one reason to change, meaning that a class should have only one responsibility.
2. **Open-Closed Principle (OCP)** - Software entities should be open for extension but closed for modification, meaning that a class should be easily extendable without modifying its existing code.
3. **Liskov Substitution Principle (LSP)** - Subtypes should be substitutable for their base types, meaning that objects of a derived class should be able to replace objects of the base class without affecting the correctness of the program.
4. **Interface Segregation Principle (ISP) -** Clients should not be forced to depend on interfaces they do not use, meaning that an interface should only include methods that are relevant to its clients.
5. **Dependency Inversion Principle (DIP)** - High-level modules should not depend on low-level modules. Both should depend on abstractions, meaning that high-level components should depend on abstractions, not on concrete implementations.

## Single Responsibility Principle (SRP)

Let there be a class `Employee` that represents an employee in an organization. Employees should have only one responsibility, which is to store and retrieve employee information. Additionally, if we add a responsibility for `logging `employee data changes, it would be in violation of the SRP

````java
public class Employee {
    private int id;
    private String name;
    private String address;
    
    public Employee(int id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }
    
    public int getId() {
        return id;
    }
    
    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getAddress() {
        return address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
}
````

The `Employee` class in this example has only one responsibility, which is to store and retrieve employee data, and it does not violate the SRP. We can create another class that is responsible for `logging` changes to employee data.

````java
public class EmployeeLogger {
    public void logEmployeeDataChange(Employee employee, String fieldName, String oldValue, String newValue) {
        System.out.println("Employee data changed: " + employee.getId() + ", Field: " + fieldName + ", Old value: " + oldValue + ", New value: " + newValue);
    }
}
````

By separating the responsibilities of storing and retrieving employee data from logging changes, we have made the code more flexible and maintainable.

## Open-Closed Principle (OCP)

Consider the case where we have a class `Shape` that represents a geometric shape, and we wish to calculate the area of different geometric shapes. It is possible to create subclasses for each shape and provide the implementation for each shape by overriding the `calculateArea` method.

````java
public abstract class Shape {
    public abstract double calculateArea();
}
````

Now, consider the following derived classes from `Shape`:

````java
public class Rectangle extends Shape {
    private double width;
    private double height;
    
    public Rectangle(double width, double height) {
        this.width = width;
        this.height = height;
    }
    
    public double getWidth() {
        return width;
    }
    
    public double getHeight() {
        return height;
    }
    
    @Override
    public double calculateArea() {
        return width * height;
    }
}

````

````java
public class Circle extends Shape {
    private double radius;
    
    public Circle(double radius) {
        this.radius = radius;
    }
    
    public double getRadius() {
        return radius;
    }
    
    @Override
    public double calculateArea() {
        return Math.PI * radius * radius;
    }
}

````

By creating new subclasses and implementing the `calculateArea` method, we can add new shapes without modifying the existing code. According to the OCP, the `Shape` class is closed for modification and open for extension. As a result, we are able to add new shapes without having to change the existing code, which makes the code more flexible and easier to maintain.

## Liskov Substitution Principle (LSP)

Let us assume that we have an interface `Rectangle` that defines a rectangular shape and a method `calculateArea` that calculates the area of that rectangle. Additionally, we have a class `Square`, which represents a square, which is a special case of a rectangle.

````java
public interface Rectangle {
    double getWidth();
    void setWidth(double width);
    double getHeight();
    void setHeight(double height);
    double calculateArea();
}
````

````java
public class Square implements Rectangle {
    private double side;
    
    public double getWidth() {
        return side;
    }
    
    public void setWidth(double width) {
        this.side = width;
    }
    
    public double getHeight() {
        return side;
    }
    
    public void setHeight(double height) {
        this.side = height;
    }
    
    public double calculateArea() {
        return side * side;
    }
}
````

For the purpose of this example, the `Square` class implements the `Rectangle` interface and implements all the methods defined by the `Rectangle` interface. Due to its conformance to the LSP, the Square class can be used wherever a `Rectangle` is required, since its subtypes must be substitutable for their base types.  As a result, a method that expects a `Rectangle` can also accept a `Square` as an argument without affecting the program's correctness.

## Interface Segregation Principle (ISP) 

Let us suppose that we have an interface `Printer` that defines methods for printing different types of documents, such as text documents, image files, and PDF files. Additionally, we have two classes that implement the `Printer` interface, `TextPrinter` and `ImagePrinter` defined as follows:

````java
public interface Printer {
    void printTextDocument(String text);
    void printImageDocument(Image image);
    void printPdfDocument(Pdf pdf);
}
````

````java
public class TextPrinter implements Printer {
    public void printTextDocument(String text) {
        // Code to print text document
    }
    
    public void printImageDocument(Image image) {
        throw new UnsupportedOperationException("TextPrinter cannot print image documents");
    }
    
    public void printPdfDocument(Pdf pdf) {
        throw new UnsupportedOperationException("TextPrinter cannot print PDF documents");
    }
}
````

````java
public class ImagePrinter implements Printer {
    public void printTextDocument(String text) {
        throw new UnsupportedOperationException("ImagePrinter cannot print text documents");
    }
    
    public void printImageDocument(Image image) {
        // Code to print image document
    }
    
    public void printPdfDocument(Pdf pdf) {
        throw new UnsupportedOperationException("ImagePrinter cannot print PDF documents");
    }
}
````

There are too many methods in the `Printer` interface in this example which are not relevant for all implementations. According to the ISP, a client should not be forced to rely on interfaces that are not used by them. We may be able to resolve this issue by splitting the `Printer` interface into multiple smaller interfaces, each of which defines a specific type of document as follows:

````java
public interface TextPrinter {
    void printTextDocument(String text);
}

public interface ImagePrinter {
    void printImageDocument(Image image);
}

public interface PdfPrinter {
    void printPdfDocument(Pdf pdf);
}
````

As a result of this design, the `TextPrinter` and `ImagePrinter` classes will be able to implement only the interfaces they need, which makes the code easier to maintain and less prone to errors.

## Dependency Inversion Principle (DIP)

Consider two modules: `HighLevelModule` and `LowLevelModule`. In order to perform some functions, `HighLevelModule` relies on `LowLevelModule`.

```java
public class LowLevelModule {
    public void performWork() {
        // Code to perform some low-level work
    }
}
```

````java
public class HighLevelModule {
    private LowLevelModule lowLevelModule;
    
    public HighLevelModule(LowLevelModule lowLevelModule) {
        this.lowLevelModule = lowLevelModule;
    }
    
    public void performHighLevelWork() {
        lowLevelModule.performWork();
    }
}
````

Due to the dependency between `HighLevelModule` and `LowLevelModule`, this design violates the DIP. As outlined in the DIP, high-level modules should not depend on low-level modules, but both should be dependent on abstractions. As a solution to this problem, we can introduce an interface `WorkModule` that defines the work that is to be performed, and both `HighLevelModule` and `LowLevelModule` classes can implement the following interface as:

````java
public interface WorkModule {
    void performWork();
}

public class LowLevelModule implements WorkModule {
    public void performWork() {
        // Code to perform some low-level work
    }
}

public class HighLevelModule implements WorkModule {
    private WorkModule workModule;
    
    public HighLevelModule(WorkModule workModule) {
        this.workModule = workModule;
    }
    
    public void performWork() {
        workModule.performWork();
    }
}
````

By using this design, both `HighLevelModule` and `LowLevelModule` classes are dependent on the abstraction `WorkModule`, which makes the code more maintainable. There is no effect on the `LowLevelModule` class when changes are made to the `HighLevelModule` class, and vice versa.

## Web References

- sarcar2022, Sarcar V., Java Design Patterns: A hands-on experience with Real-World Examples, Chapter 1, pp.3-64,  APress, 2022.
- weisfeld2019, Weisfeld M., Object-Oriented Thought Process, 5th Ed, Chapter 12, Addison-Wesley, 2019.
- joshi2016, Joshi B., Beginning SOLID Principles and Design Patterns for ASP.NET Developers, APress, 2016.
- martin2008, Martin R., Clean Code: A Handbook of Agile Software Craftsmanship, Pearson, 2008

