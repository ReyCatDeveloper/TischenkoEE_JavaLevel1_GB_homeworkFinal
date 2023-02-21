package lesson6.taskFrom3To4;

import static lesson6.taskFrom3To4.Barrier.Type.groundBarrier;
import static lesson6.taskFrom3To4.Barrier.Type.waterBarrier;

public abstract class Animal {
/**
    * В этой версии не все животные умеют плавать!!!
 *
 * */
    String name;
    protected int runLimit;
    protected int swimLimit;
    protected String type = "Зверь";
    public static int countAnimal = 0;

    Boolean runAbility;
    Boolean swimAbility;

    Animal(String name, Boolean runAbility, Boolean swimAbility){
        this.name = name;
        this.runAbility = runAbility;
        this.swimAbility = swimAbility;
        countAnimal++;
    }

    String getType(){
        return this.type;
    }
    void run(int value) {

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
            System.out.printf("%s столько не проплывет! Максимум %s! \n", type, swimLimit);
        }
    }
 //   abstract void swim();

    static Animal[] zoopark;

    static {
        zoopark = new Animal[]{
                new Dog("BestFriend"),
                new Dog("Killer"),
                new Cat("Murzik-mushelov"),
                new Cat("Objora"),
                new Cat("Lejeboka")
        };
    }

    static void countAnimal(Animal[] zoo){
        int animals = zoo.length;
        int dogs = 0;
        int cats = 0;
             for(int i = 0; i < zoo.length; i++){
            if(zoo[i] instanceof Dog){
                dogs++;
            } else if (zoo[i] instanceof Cat) {
                cats++;
            }
        }
        System.out.println();
        System.out.printf("Животных в зоопарке (массив zoopark): %s\n", animals);
        System.out.printf("Собак: %s\n", dogs);
        System.out.printf("Кошек: %s\n", cats);
    }

    public void barrierOvercoming(Barrier barrier){
        if(barrier.type == groundBarrier ){
            System.out.printf("%s забег на %s метров: ",this.type, barrier.distance);
            if (this.runAbility){
                this.run(barrier.distance);
            }else {
                System.out.println(impossibleMessage(this));
            }
        }
        if(barrier.type == waterBarrier ){
            System.out.printf("%s заплыв на %s метров: ",this.type, barrier.distance);
            if (this.swimAbility){
                this.swim(barrier.distance);
            }else {
                System.out.println(impossibleMessage(this));
            }
        }
    }

    public String impossibleMessage(Animal animal){
        String impossibleMessage = "Это же "+ animal.type +"! Это невозможно!\n";
        return impossibleMessage;
    }
    void animalInfo(){
        System.out.println(this.getClass());
        //System.out.println();
    }
}
