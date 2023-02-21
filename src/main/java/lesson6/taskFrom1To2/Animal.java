package lesson6.taskFrom1To2;

public class Animal {
    //В этой версии "неумение" котов плавать описано переопределение метода и изменением реакции
    String name;
    static int runLimit;
    static int swimLimit;
    static String type = "Зверь";
    Animal(String name){
        this.name = name;
    }
    void run(int value){
        if(value <= runLimit){
            System.out.printf("%s %s пробежал %s метров\n", type, name, value);
        }else {
            System.out.printf("%s %s пробежал %s метров и очень устал - %s не сможет\n", type, name, runLimit, value);
        }
    }
    void swim(int value){
        if(value <= swimLimit){
            System.out.printf("%s %s проплыл %s метров\n", type, name, value);
        }else {
            System.out.printf("%s столько не проплывет! Максимум %s Вы в своём уме? \n", type, swimLimit);
        }
    }
}
