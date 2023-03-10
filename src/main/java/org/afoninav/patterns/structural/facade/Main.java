package com.afoninav.patterns.structural.facade;


/**
 * Пример Шаблона фасад.
 */
public class Main {
    public static void main(String[] args) {
        DriverControlInterface driveInterface = new DriverControlInterface(new ElectricCar());
        driveInterface.powerOn();
        driveInterface.setOffRoadMod();
        driveInterface.gas();
        driveInterface.brake();
    }
}

/**
 * Фасад. Предоставляет простые методы для пользователя,
 * для управления сложными внутренними процессами.
 */
class DriverControlInterface {
    ElectricCar car;

    DriverControlInterface(ElectricCar car) {
        this.car = car;
    }

    void powerOn() {
        car.battery.checkStatus();
        car.battery.powerOn();
        car.coolant.checkStatus();
    }

    void powerOff() {
        if(!car.isMove){
            car.battery.powerOff();
        }
    }

    void gas() {
        car.frontEngine.checkError();
        car.rearEngine.checkError();
        car.frontEngine.rotate();
    }

    void brake() {
        car.frontEngine.stopRotate();
        car.rearEngine.stopRotate();
        car.frontEngine.recuperationOn();
        car.rearEngine.recuperationOn();
    }

    void setOffRoadMod() {
        car.synchronizationEngines(true);
        car.frontAxis.block();
        car.rearAxis.block();
    }

    void setCityDriveMode() {
        car.rearAxis.unblock();
        car.frontAxis.unblock();
        car.synchronizationEngines(false);
    }
}

/**
 * Полноприводный электрический автомобиль,
 * у которого есть два двигателя, по одному для каждой оси.
 * Блокировка осей и синхронизация двигателей осуществляется,
 * с помощью выбора режима вождения.
 */
class ElectricCar {
    boolean isMove;
    Engine frontEngine = new Engine();
    Engine rearEngine = new Engine();

    Axis frontAxis = new Axis();
    Axis rearAxis = new Axis();
    Battery battery = new Battery();
    Coolant coolant = new Coolant();


    void synchronizationEngines(boolean synchronization){
        System.out.println("Engines synchronization is " + synchronization);
    }
}

class Engine {
    void checkError(){
        System.out.println("Engine is OK");
    }
    void rotate(){
        System.out.println("Engine Rotate");
    }
    void stopRotate(){
        System.out.println("Engine Stop");
    }
    void recuperationOn(){
        System.out.println("Recuperation ON");
    }
}

class Axis {
    void block(){
        System.out.println("Axis is Block");
    }
    void unblock(){
        System.out.println("Axis is Unblock");
    }
}

class Battery {
    void powerOn(){
        System.out.println("Battery ON");
    }
    void powerOff(){
        System.out.println("Battery OFF");
    }
    void checkStatus(){
        System.out.println("Battery OK");
    }
}

class Coolant {
    void checkStatus(){
        System.out.println("Coolant: OK");
    }
}



