package com.afoninav.patterns.creational.singleton;

/**
 * Пример шаблона Singleton
 */
public class Main {
    public static void main(String[] args) {

        Thread thread2 = new Thread(() -> System.out.println(Singleton.getInstance()));
        thread2.start();

        Singleton singleton2 = Singleton.getInstance();
        System.out.println(singleton2);

        Singleton singleton3 = Singleton.getInstance();
        System.out.println(singleton3);
    }
}

class Singleton {
    private int a;
    private int b;
    private int c;

    // volatile даёт ганатию happens-before,
    // тем самым исключая возможность получить
    // другим потоком частично инстанцируемый объект.
    private static volatile Singleton instance;

    private Singleton(){
        this.a = 5;
        this.b = 10;
        this.c = 15;
    }

    public static Singleton getInstance() {
        if (instance == null) {
            synchronized (Singleton.class) {
                if (instance == null) {
                    instance = new Singleton();
                }
            }
        }
        return instance;
    }

    @Override
    public String toString() {
        return String.format("i=%s, k=%s, m=%s, hash=%s", a, b, c, instance.hashCode());
    }
}
