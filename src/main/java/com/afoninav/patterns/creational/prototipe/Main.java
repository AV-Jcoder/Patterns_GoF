package com.afoninav.patterns.creational.prototipe;

/**
 * Шаблон Prototype, включает в себя интерфейс Copyable и класс Human,
 * реализующий этот интерфейс. А так же HumanFactory, объект, который
 * манипулирует прототипом.
 */
public class Main {
    public static void main(String[] args) {
        Human oleg = new Human("Oleg", 22);
        System.out.println(oleg);

        Human olegCopy = (Human) oleg.copy();
        System.out.println(olegCopy);

        HumanFactory factory = new HumanFactory(olegCopy);
        Human secondOlegCopy = factory.makeCopy();
        System.out.println(secondOlegCopy);

        factory.setHumanPrototype(new Human("Inokentij", 43));
        Human inokentijCopy = factory.makeCopy();
        System.out.println(inokentijCopy);
    }
}

/**
 * Интерфейс-Прототип объявляет метод copy(), который
 * возвращает копию экземпляра.
 */
interface Copyable {
    Object copy();
}

/**
 * Класс-Прототип, реализующий Copyable.
 * метод copy() возвращает точную
 * копию текущего экземпляра.
 */
class Human implements Copyable {
    private String name;
    private int age;

    public Human(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public Object copy() {
        return new Human(this.name, this.age);
    }

    @Override
    public String toString() {
        return "Human{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}

/**
 * Фабрика по производству клонов.
 * Создает новый объект, обращаясь к
 * прототипу с запросом клонировать себя
 */
class HumanFactory {
    private Human human;

    public HumanFactory(Human human) {
        this.human = human;
    }

    public void setHumanPrototype(Human human) {
        this.human = human;
    }

    public Human makeCopy() {
        return (Human) human.copy();
    }
}

