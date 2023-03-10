package com.afoninav.patterns.structural.decorator;

/**
 * Пример применения шаблона Decorator(Wrapper).
 */

public class Main {
    public static void main(String[] args) {
        Task task = new SimpleTask(5);

        TaskDiagramDecorator decorator1 = new TaskDiagramDecorator();
        decorator1.setTask(task);

        ResponsibilityStaffDecorator decorator2 = new ResponsibilityStaffDecorator();
        decorator2.setTask(decorator1);
        decorator2.setStaff("Ivanov Ivan");
        decorator2.display();
    }
}

/**
 * Основной интерфейс.
 */
interface Task {
    int getTime();
    void display();
}

/**
 * Простая задача.
 */
class SimpleTask implements Task {
    private int time;

    SimpleTask(int time) {
        this.time = time;
    }

    @Override
    public int getTime() {
        return this.time;
    }

    @Override
    public void display() {
        System.out.println("Время выполнения задачи: " + this.time);
    }
}

/**
 * Декоратор.
 */
abstract class Decorator implements Task {
    protected Task decorateTask;

    void setTask(Task decorateTask) {
        this.decorateTask = decorateTask;
    }

    @Override
    public int getTime() {
        return decorateTask.getTime();
    }

    @Override
    public void display() {
        decorateTask.display();
    }
}

/**
 * Реализация декоратора, который добавляет диаграмму.
 */
class TaskDiagramDecorator extends Decorator {
    @Override
    public void display() {
        super.display();
        // Рисуем диаграмму
        System.out.println("Временная диаграмма: ");
        int time = decorateTask.getTime();
        for (int i = 0; i < time; i++) {
            for (int j = 0; j < time; j++) {
                if (j < time - i-1) {
                    System.out.print(' ');
                } else {
                    System.out.print('#');
                }
            }
            System.out.println(" ");
        }
    }
}

/**
 * Реализация Декторатора, который добавляет
 * ответственных сотрудников за выполнение.
 */
class ResponsibilityStaffDecorator extends Decorator {
    private String name;

    public void setStaff(String name) {
        this.name = name;
    }

    @Override
    public void display() {
        super.display();
        System.out.println("Ответственный за выполнение: " + this.name);
    }
}
