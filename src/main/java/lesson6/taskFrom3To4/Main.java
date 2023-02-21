package lesson6.taskFrom3To4;

import static lesson6.taskFrom3To4.Animal.countAnimal;
import static lesson6.taskFrom3To4.Animal.zoopark;
import static lesson6.taskFrom3To4.Barrier.Type.groundBarrier;
import static lesson6.taskFrom3To4.Barrier.Type.waterBarrier;
import static lesson6.taskFrom3To4.Cat.catZadira;
import static lesson6.taskFrom3To4.Cat.countCat;
import static lesson6.taskFrom3To4.Dog.countDog;
import static lesson6.taskFrom3To4.Dog.dogGoodBarbos;

public class Main {
    public static void main(String[] args) {
        System.out.println("Действия животных:");

        catZadira.barrierOvercoming(new Barrier(groundBarrier,170));
        catZadira.barrierOvercoming(new Barrier(groundBarrier,270));
        catZadira.barrierOvercoming(new Barrier(waterBarrier,5));

        dogGoodBarbos.barrierOvercoming(new Barrier(groundBarrier,450));
        dogGoodBarbos.barrierOvercoming(new Barrier(groundBarrier,750));
        dogGoodBarbos.barrierOvercoming(new Barrier(waterBarrier,8));
        dogGoodBarbos.barrierOvercoming(new Barrier(waterBarrier,58));
        System.out.println();

        System.out.println("Подсчет животных:");
        System.out.printf("Всего: %s\nСобак: %s\nКошек: %s\n", countAnimal,countDog,countCat);
        countAnimal(zoopark);

    }
}