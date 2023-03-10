package com.afoninav.patterns.structural.composite;

import java.util.LinkedList;

/**
 * Пример использования шаблона Composite.
 * В нём создаётся Сложная задача, которая
 * может состоять как из простых, так и других
 * сложных задач.
 *
 */

public class Main {
    public static void main(String[] args) {
        CompositeTask component1 = new CompositeTask();
        CompositeTask component2 = new CompositeTask();
        component1.addComponent(component2);
        component1.addComponent(new SimpleTask(2));
        component1.addComponent(new SimpleTask(4));
        component2.addComponent(new SimpleTask(8));
        component2.addComponent(new SimpleTask(15));

        component1.display();
        System.out.println("Время на выполнение задачи: " + component1.calculate());

    }
}

/**
 * Базовый компонент - Task.
 * Определяет задачу, которую можно вывести
 * на дисплей и посчитать время её исполнения.
 */
interface Task {
    void display();
    int calculate();
}

/**
 * Листовой элемент.
 *
 * Класс представляет из себя простую задачу,
 * у которой есть время на её решение.
 */
class SimpleTask implements Task {
    private final int time;

    SimpleTask(int time) {
        this.time = time;
    }

    @Override
    public void display() {
        System.out.println("Простая задача, время выполнения: " + this.time);
    }

    @Override
    public int calculate() {
        return this.time;
    }
}

/**
 * Композитный элемент.
 *
 * Класс представляет из себя сложную задачу,
 * которая состоит из множества подзадач,
 * как простых, так и других сложных.
 */
class CompositeTask implements Task {
    private LinkedList<Task> components = new LinkedList<>();

    public void addComponent(Task component){
        this.components.add(component);
    }

    public boolean deleteComponent(Task component) {
        return this.components.remove(component);
    }

    @Override
    public void display() {
        System.out.println("Очень сложная задача, состоит из: ");
        for (Task component : components) {
            component.display();
        }
    }

    @Override
    public int calculate() {
        int time = 0;
        for (Task component : components) {
            time += component.calculate();
        }
        return time;
    }
}






