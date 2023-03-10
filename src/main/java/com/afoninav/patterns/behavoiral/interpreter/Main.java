package com.afoninav.patterns.behavoiral.interpreter;

/**
 * Пример Шаблона Интерпретатор.
 */
public class Main {
    public static void main(String[] args) {
        Expression ex1 = new TerminalExpression("Java");
        Expression ex2 = new TerminalExpression("JavaEE");

        Expression ex3 = new OrExpression(ex1, ex2);
        boolean result = ex3.interpret("Java");
        System.out.println(result);


    }
}

interface Expression {
    boolean interpret(String context);
}

class TerminalExpression implements Expression {
     private String data;

    TerminalExpression(String data) {
        this.data = data;
    }

    @Override
    public boolean interpret(String data) {
        return this.data.equalsIgnoreCase(data);
    }
}

class AndExpression implements Expression {
    private Expression ex1;
    private Expression ex2;

    public AndExpression(Expression ex1, Expression ex2) {
        this.ex1 = ex1;
        this.ex2 = ex2;
    }

    @Override
    public boolean interpret(String context) {
        return ex1.interpret(context) && ex2.interpret(context);
    }
}

class OrExpression implements Expression {
    private Expression ex1;
    private Expression ex2;

    OrExpression(Expression ex1, Expression ex2) {
        this.ex1 = ex1;
        this.ex2 = ex2;
    }

    @Override
    public boolean interpret(String context) {
        return ex1.interpret(context) || ex2.interpret(context);
    }
}
