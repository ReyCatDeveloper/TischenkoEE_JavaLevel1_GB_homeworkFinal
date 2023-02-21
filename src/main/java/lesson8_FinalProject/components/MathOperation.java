package lesson8_FinalProject.components;

import static lesson8_FinalProject.components.MathOperation.Priority.high;
import static lesson8_FinalProject.components.MathOperation.Priority.low;

public abstract class MathOperation extends Math{
    public static class Priority{
        static Priority high = new Priority();
        static Priority low = new Priority();
    }


    private String name;
    private Priority priority;
    MathOperation(String name, Priority priority){
        this.name = name;
        this.priority = priority;
    }

    abstract double execute(double a, double b);

    static class Addition extends MathOperation{
        Addition(String name) {
            super(name, low);
        }

        @Override
        public double execute(double a,double b){
             return a + b;
         }
        }
        static class Subtraction extends MathOperation{
            Subtraction(String name) {
                super(name, low);
            }

            @Override
            public    double execute(double a,double b){
                return a - b;
            }
        }
        static class Multiplication extends MathOperation{
            Multiplication(String name) {
                super(name, high);
            }

            @Override
            public    double execute(double a,double b){
                return a * b;
            }
        }
        static class Division extends MathOperation{
            Division(String name) {
                super(name,high);
            }

            @Override
            public    double execute(double a,double b){
                return a / b;
            }
        }

    public static final MathOperation addition = new Addition("addition");
    public static final MathOperation subtraction = new Subtraction("subtraction");
    public static final MathOperation multiplication = new Multiplication("multiplication");
    public static final MathOperation division = new Division("division");

    private static MathOperation[] allMathOperations = new MathOperation[]{addition,subtraction,multiplication,division};

    public static MathOperation[] getAllMathOperations() {
        return allMathOperations;
    }

    public String getName() {
        return name;
    }

    public Priority getPriority() {
        return priority;
    }
}
