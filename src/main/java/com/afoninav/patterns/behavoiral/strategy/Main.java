package com.afoninav.patterns.behavoiral.strategy;

/**
 * Пример паттерна стратегия.
 */
public class Main {
    public static void main(String[] args) {
        SportsMan sportsMan = new Runner();
        sportsMan.doMove();
        sportsMan.setBehavior(new JumpBehavior());
        sportsMan.doMove();
    }
}


/**
 * Интерфейс - стратегия.
 * определяет каким образом
 * спортсмен может двигаться.
 */
interface MoveBehavior {
    void move();
}

/**
 * Имплементация стратегии.
 */
class RunBehavior implements MoveBehavior {
    @Override
    public void move() {
        System.out.println("Run");
    }
}

/**
 * Еще одна имплементация стратегии.
 */
class JumpBehavior implements MoveBehavior {
    @Override
    public void move() {
        System.out.println("Jump");
    }
}

/**
 * Базовый класс.
 * Содержит переменную типа MoveBehavior,
 * отвечающую за поведение.
 */
abstract class SportsMan {
    private MoveBehavior behavior;

    protected SportsMan(MoveBehavior behavior) {
        this.behavior = behavior;
    }

    void doMove() {
        behavior.move();
    }

    void setBehavior(MoveBehavior behavior) {
        this.behavior = behavior;
    }
}

/**
 * Класс создающий конкретного спортсмена
 * с конкретным поведением.
 * В данный момент это прыгун.
 */
class Jumper extends SportsMan {
    Jumper() {
        super(new JumpBehavior());
    }
}

/**
 * Класс создающий конкретного спортсмена
 * с конкретным поведением.
 * В данный момент это бегун.
 */
class Runner extends SportsMan {
    Runner() {
        super(new RunBehavior());
    }
}

