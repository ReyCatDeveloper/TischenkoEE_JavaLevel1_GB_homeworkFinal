package lesson7;

import static lesson7.Cat.teamEating;
import static lesson7.Cat.teamInfo;

public class Main {
    public static void main(String[] args) {
//-----ОБЖОРА И МИСКА----------------------------------------
        Cat objoraCat = new Cat("Обжора", 20);
        Plate plate = new Plate(50);
        plate.info();

        objoraCat.eat(plate);
        plate.info();

        objoraCat.eat(plate);
        plate.info();
//-----КОМАНДА ОБЖОРЫ И ТАРЕЛКА----------------------------------------
        System.out.println();
        Cat[] allCats = new Cat[4];
            allCats[0] = objoraCat;
            allCats[1] = new Cat("Брат Обжоры",15);
            allCats[2] = new Cat("1й другОбжоры",12);
            allCats[3] = new Cat("2й другОбжоры",14);
        plate.setFood(40);
        teamEating(allCats,plate);
        teamInfo(allCats);

//-----КОМАНДА ОБЖОРЫ И ДОБАВКА----------------------------------------
        plate.additiveFood(20);
        teamEating(allCats,plate);
        teamInfo(allCats);
    }
}
