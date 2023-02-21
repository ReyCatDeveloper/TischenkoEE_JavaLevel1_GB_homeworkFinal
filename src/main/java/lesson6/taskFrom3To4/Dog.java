package lesson6.taskFrom3To4;

public class Dog extends Animal {
    public static int countDog;// = 0;
    Dog(String name) {
        super(name,true,true);
        this.type = "cобака";
        runLimit = 500;
        swimLimit = 10;
        countDog++;
    }
    public static Dog dogGoodBarbos = new Dog("Добрый Барбос");


}
