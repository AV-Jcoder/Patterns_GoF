package com.afoninav.patterns.creational.FactoryMethod;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {

        BufferedReader reader = new BufferedReader( new InputStreamReader(System.in));
        String clientRequest = new String(reader.readLine());
        reader.close();

        Creator productFactory = new ConcreteCreator();
        Product product = productFactory.createProduct(clientRequest);
        System.out.println(product);
    }
}
class Product {
    private final String title;
    protected Product() {
        this.title = "Product is "+ this.getClass().getName();
    }

    @Override
    public String toString() {
        return this.title;
    }
}

class ProductA extends Product {}
class ProductB extends Product {}
class ProductC extends Product {}
class ProductD extends Product {}
class ProductE extends Product {}
class ProductF extends Product {}

abstract class Creator {
     public abstract Product createProduct(String arg);
}

class ConcreteCreator extends Creator {
    public Product createProduct(String clientRequest) {
        Product product = null;
        if (clientRequest.equals("A")) {
            product = new ProductA();
        } else if (clientRequest.equals("B")) {
            product = new ProductB();
        } else if (clientRequest.equals("C")) {
            product = new ProductC();
        } else if (clientRequest.equals("D")) {
            product = new ProductD();
        } else if (clientRequest.equals("E")) {
            product = new ProductE();
        } else if (clientRequest.equals("F")) {
            product = new ProductF();
        }
        return product;
    }
}
