package com.afoninav.patterns.creational.AbstractFactory;

/**
 * Пример реализации паттерна Абстрактная фабрика.
 *
 * Реализзации Абстрактной фабрики создают объекты
 * типа Window и ScrollBar, которые гарантированно
 * должны быть совместимы между собой.
 *
 * С помощью абстрактной фабрики мы легко можем
 * поменять внешний вид с тёмного на светлый.
 *
 * Для этого нужно просто инстанцировать
 * Абстрактную фабрику ёё другой реализацией
 * и перезапустить программу.
 *
 */

public class Main {

    public static void main(String[] args) {

        AbstractFactory factory = new IntelliJLightThemFactory();
        Window window = factory.createWindow();
        ScrollBar scrollBar = factory.createScrollBar();
        window.view();
        scrollBar.view();
    }
}

interface Window {
    void view();
}

interface ScrollBar {
    void view();
}

interface AbstractFactory {
    Window createWindow();
    ScrollBar createScrollBar();
}

class DarculaThemFactory implements AbstractFactory {
    @Override
    public Window createWindow() {
        return new DarculaThemWindow();
    }

    @Override
    public ScrollBar createScrollBar() {
        return new DarculaThemScrollBar();
    }
}

class DarculaThemWindow implements Window {
    @Override
    public void view() {
        System.out.println("Darkula Window");
    }
}

class DarculaThemScrollBar implements ScrollBar {
    @Override
    public void view() {
        System.out.println("Darkula Window");
    }
}

class IntelliJLightThemFactory implements AbstractFactory {
    @Override
    public Window createWindow() {
        return new IntelliJLightThemWindow();
    }

    @Override
    public ScrollBar createScrollBar() {
        return new IntelliJLightThemScrollBar();
    }
}

class IntelliJLightThemWindow implements Window {
    @Override
    public void view() {
        System.out.println("IntelliJ Light Window");
    }
}

class IntelliJLightThemScrollBar implements ScrollBar {
    @Override
    public void view() {
        System.out.println("IntelliJ Light ScrollBar");
    }
}

