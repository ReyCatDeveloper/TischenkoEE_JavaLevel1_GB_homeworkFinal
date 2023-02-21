package lesson6.taskFrom3To4;

public class Cat extends Animal {
    public static int countCat;// = 0;
    Cat(String name) {
        super(name, true,false);
        this.type = "кошка";
        runLimit = 200;
        countCat++;
    }
    public static Cat catZadira = new Cat("Задира");
    @Override
    void swim(int value){
        System.out.println("Кошки не плавают");
    }

}
