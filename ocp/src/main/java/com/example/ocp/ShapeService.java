package com.example.ocp;

import java.util.List;

public class ShapeService {
    void execute(){
    Circle circle = new Circle(2);
    Rectangle rectangle = new Rectangle(3, 4);
    List<Shape> shapes = List.of(circle, rectangle);
    double total = new AreaCalculator().totalArea(shapes);
    System.out.println("Total area: " + total);
    }
}
