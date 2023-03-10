package com.afoninav.patterns.structural.flyweight;

import java.util.HashMap;

/**
 * Пример шаблона FlyWeight(Вес мухи).
 */
public class Main {
    public static void main(String[] args) {
        String[] colors = {"Red","Green","Blue"};
        ShapeFactory factory = new ShapeFactory();


        for (int i = 0; i < 10; i++) {
            System.out.println();
            Shape shape = factory.getFlyweight(colors[(int)(Math.random()*3)]);
            shape.draw(new Position());
        }
    }
}

/**
 * Интерфейс Shape - Легковес.
 * определяет методы для
 * взамиодействия с клиентским кодом
 */
interface Shape {
    void draw(Position context);
}

/**
 * Квадрат, у которого есть цвет.
 * В данном примере цвет является
 * внутренним уникальным состоянием,
 * а внешним контекстом является
 * координаты х у.
 */
class Square implements Shape {
    String color;

    Square(String color) {
        this.color = color;
    }

    @Override
    public void draw(Position pos) {
        System.out.printf("Square: hash=%d, color=%s, position: x=%d; y=%d", this.hashCode(), color, pos.x, pos.y);
    }
}

/**
 * Внешний контекст
 */
class Position {
    int x;
    int y;

    public Position() {
        this.x = (int) (Math.random()*100);
        this.y = (int) (Math.random()*100);
    }
}

/**
 * Что-то наподобии фабричного метода.
 * Класс предоставляет существующий экземпляр фигуры
 * или создает новый, если готового еще нет.
 */
class ShapeFactory {
    private HashMap<String, Shape> shapes = new HashMap<>();

    public Shape getFlyweight(String color) {
        Shape shape = shapes.get(color);
        if (shape == null) {
            shape = new Square(color);
            shapes.put(color, shape);
        }
        return shape;
    }
}

