package lesson6.taskFrom1To2;
//В этой версии "неумение" котов плавать описано переопределение метода и изменением реакции
public class Cat extends Animal{
    public static Cat catZadira = new Cat("Задира");


    Cat(String name) {
        super(name);
        type = "кошка";
        runLimit = 200;
        swimLimit = 0;
    }
    void swim(int value){
        System.out.printf("Это же кошка! Вы слишком многово просите!\n");
    }
}
