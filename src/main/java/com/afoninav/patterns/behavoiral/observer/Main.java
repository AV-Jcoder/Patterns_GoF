package com.afoninav.patterns.behavoiral.observer;

import java.util.LinkedList;

/**
 * Пример паттерна наблюдатель.
 *
 * У нас есть погодная станция, которая получает данные из вне
 * и затем отдаёт команду обновления данных на дисплеях,
 * которые настроены на эту станцию.
 */
public class Main {
    public static void main(String[] args) {
        WeatherStation weatherStation = new WeatherStation();
        WeatherDisplay weatherDisplay1 = new WeatherDisplay();
        WeatherDisplay weatherDisplay2 = new WeatherDisplay();
        WeatherDisplay weatherDisplay3 = new WeatherDisplay();

        weatherStation.addObserver(weatherDisplay1);
        weatherStation.addObserver(weatherDisplay2);
        weatherStation.addObserver(weatherDisplay3);

        weatherStation.setTemperature(22);
        weatherDisplay1.display();
        weatherDisplay2.display();
        weatherDisplay3.display();
        System.out.println(" ");
        weatherStation.setTemperature(23);
        weatherDisplay1.display();
        weatherDisplay2.display();
        weatherDisplay3.display();

    }
}


/**
 * Интерфейс - Наблюдаемый объект.
 */
interface Subject {
    void addObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

/**
 * Интерфейс - Наблюдатель
 */
interface Observer {
    void update(int parameter);
}

/**
 * Погодная станция, реализует наблюдаемый объект.
 */
class WeatherStation implements Subject {
    private int temperature;
    private LinkedList<Observer> observers = new LinkedList<>();

    public int getTemperature() {
        return this.temperature;
    }

    public synchronized void setTemperature(int temperature) {
        this.temperature = temperature;
        this.notifyObservers();
    }

    @Override
    public void addObserver(Observer observer) {
        this.observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        this.observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(this.temperature);
        }
    }
}

/**
 * Погодный дисплей. Реализует Наблюдателя.
 */
class WeatherDisplay implements Observer {
    private int temperature;

    public void display() {
        System.out.println(this.getClass().getSimpleName()+ "-"+ this.hashCode() + ": " + this.temperature + " C'");
    }

    @Override
    public void update(int actualTemperature) {
        this.temperature = actualTemperature;
    }
}