package com.example.ocp;

import java.util.List;

public class AreaCalculator {
    public double totalArea(List<Shape> shapes) {
        return shapes.stream()
                     .mapToDouble(Shape::area)
                     .sum();
    }
}