package lesson6.taskFrom1To2;

public class Dog extends Animal{
    Dog(String name) {
        super(name);
        type = "Собака";
        runLimit = 500;
        swimLimit = 10;
    }

    public static Dog dogGoodBarbos = new Dog("Добрый Барбос");
}
