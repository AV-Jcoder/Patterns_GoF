package com.afoninav.patterns.structural.brige;

/**
 * Пример шаблона Bridge.
 * В данном примере явно демонстрируется
 * приемущество композиции перед наследованием.
 * Так как если бы мы наследовались, то пришлось бы делать
 * целых девять реализаций классов!Например:
 * 1) Квадрат Красный
 * 2) Квадрат Зеленый
 * 3) Квадрат Голубой
 * ...
 * Тоесть количество реализаций = m*n
 * Но мы обошлись всего шестью, а это линейная сложность!
 * И это круто!
 */
public class Main {
    public static void main(String[] args) {

        Shape circle1 = new Circle(new Red());
        System.out.println(circle1.getShape());

        Shape circle2 = new Circle(new Green());
        System.out.println(circle2.getShape());

        Shape circle3 = new Circle(new Blue());
        System.out.println(circle3.getShape());

    }
}

/**
 * Абстрактный класс фигуры, содержит информацию о её цвете.
 */
abstract class Shape {
    private String shapeName;
    private Color color;

    protected Shape(Color color) {
        this.color = color;
        this.shapeName = getClass().getSimpleName();
    }

    public String getShape() {
        return "" + shapeName + " " + color.getColor();
    }
}

/**
 * Круг
 */
class Circle extends Shape {
    public Circle(Color color) {
        super(color);
    }
}

/**
 * Квадрат
 */
class Square extends Shape {
    public Square(Color color) {
        super(color);
    }
}

/**
 * Треугольник
 */
class Triangle extends Shape {
    public Triangle(Color color) {
        super(color);
    }
}

/**
 * Интерфейс, предоставляет информацию о цвете.
 */
interface Color {
    String getColor();

}

/**
 * Красный
 */
class Red implements Color {
    @Override
    public String getColor() {
        return getClass().getSimpleName();
    }
}

/**
 * Зеленый
 */
class Green implements Color {
    @Override
    public String getColor() {
        return getClass().getSimpleName();
    }
}

/**
 * Голубой
 */
class Blue implements Color {
    @Override
    public String getColor() {
        return getClass().getSimpleName();
    }
}