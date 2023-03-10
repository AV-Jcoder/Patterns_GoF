package com.afoninav.patterns.behavoiral.visitor;

import java.util.LinkedList;
import java.util.List;

/**
 * Пример Шаблона Visitor.
 * Строящееся здание, которое
 * будет сотоять из:
 * Фундамент 1 шт.
 * Стена 4 шт.
 * Крыша 1 шт.
 * Требуется произвести расчёт:
 * 1. Общего времени на возведение.
 * 2. Итоговой стоимости возведения.
 */
public class VisitorRunner {
    public static void main(String[] args) {
        Building building = new Building();
        building.addElement(new Foundation());
        building.addElement(new Wall());
        building.addElement(new Wall());
        building.addElement(new Wall());
        building.addElement(new Wall());
        building.addElement(new Roof());

        TimeCalculatorVisitor timeVisitor = new TimeCalculatorVisitor();
        MoneyCostsVisitor moneyVisitor = new MoneyCostsVisitor();
        building.accept(timeVisitor);
        building.accept(moneyVisitor);
        System.out.println("Для изготовления здания необходимо:");
        System.out.println(timeVisitor.getTotalTimeInDays() + " дней.");
        System.out.println(moneyVisitor.getTotalCostsInDollars() + " долларов.");
    }
}

/**
 * Интерфейс определяет методы для
 * каждой реализации класса Element.
 */
interface Visitor {
    void visit(Building building);
    void visit(Wall wall);
    void visit(Foundation foundation);
    void visit(Roof roof);
}

/**
 * Реализация Visitor. Производит расчёт
 * времени для возведения элементов.
 */
class TimeCalculatorVisitor implements Visitor {
    private int totalTime = 0;

    public int getTotalTimeInDays() {
        return this.totalTime;
    }

    @Override
    public void visit(Building building) {
        List<Element> elements = building.getAllElements();
        for (Element e : elements) {
            e.accept(this);
        }
    }

    @Override
    public void visit(Wall wall) {
        totalTime += wall.getBuildTimeDays();
    }

    @Override
    public void visit(Foundation foundation) {
        totalTime += foundation.getBuildTimeDays();
    }

    @Override
    public void visit(Roof roof) {
        totalTime += roof.getBuildTimeDays();
    }
}

/**
 * Реализация Visitor для рассчёта стоимости
 * возведения здания.
 */
class MoneyCostsVisitor implements Visitor {
    private int totalCostsInDollars;

    public int getTotalCostsInDollars() {
        return totalCostsInDollars;
    }

    @Override
    public void visit(Building building) {
        List<Element> elements = building.getAllElements();
        for (Element ele : elements) {
            ele.accept(this);
        }
    }

    @Override
    public void visit(Wall wall) {
        totalCostsInDollars += wall.getMoneyCosts();
    }

    @Override
    public void visit(Foundation foundation) {
        totalCostsInDollars += foundation.getMoneyCosts();
    }

    @Override
    public void visit(Roof roof) {
        totalCostsInDollars += roof.getMoneyCosts();
    }
}

/**
 * Интерфейс, определяет объект и методы,
 * которые будет использовать объект
 * типа Visitor.
 */
interface Element {
    void accept(Visitor visitor);
}

/**
 * Главный элемент - Здание.
 * Состоит из фундамена, стен, крышы.
 */
class Building implements Element {
    private final List<Element> elements = new LinkedList<>();

    public void addElement(Element element) {
        elements.add(element);
    }

    public List<Element> getAllElements() {
        return elements;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

/**
 * Элемент. Стена.
 */
class Wall implements Element {
    private final int moneyCosts;
    private final int buildTimeDays;

    public Wall() {
        this.moneyCosts = 500;
        this.buildTimeDays = 10;
    }

    public int getMoneyCosts() {
        return this.moneyCosts;
    }

    public int getBuildTimeDays() {
        return this.buildTimeDays;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

/**
 * Элемент. Фундамент.
 */
class Foundation implements Element {
    private final int moneyCosts;
    private final int buildTimeDays;

    public Foundation() {
        this.moneyCosts = 4000;
        this.buildTimeDays = 60;
    }

    public int getMoneyCosts() {
        return this.moneyCosts;
    }

    public int getBuildTimeDays() {
        return this.buildTimeDays;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}

/**
 * Элемент. Крыша.
 */
class Roof implements Element {
    private final int moneyCosts;
    private final int buildTimeDays;

    public Roof() {
        this.moneyCosts = 2000;
        this.buildTimeDays = 30;
    }

    public int getMoneyCosts() {
        return this.moneyCosts;
    }

    public int getBuildTimeDays() {
        return this.buildTimeDays;
    }

    @Override
    public void accept(Visitor visitor) {
        visitor.visit(this);
    }
}
