package lesson7;

public class Cat {
    private String name;
    private int appetite;
    private Boolean satiety;

    public Cat(String name, int appetite) {
        this.name = name;
        this.appetite = appetite;
        this.satiety = false;
    }

    public String getName() {
        return name;
    }

    public int getAppetite() {
        return appetite;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAppetite(int appetite) {
        this.appetite = appetite;
    }

    public void eat(Plate plate) {
        if (!satiety) {
            if (plate.getFood() > appetite) {
                plate.decreaseFood(this.appetite);
                satiety = true;
                System.out.printf("%s вдоволь поел\n", name);
            } else {
                System.out.printf("Еды слишком мало в миске. %s не смог поесть\n", name);
            }
        }else {
            System.out.printf("%s уже сыт\n", name);
        }
    }

    public static void teamEating(Cat[] cats, Plate plate){
        System.out.println("Команда котов опустошает миску: ");
        for(int i = 0; i < cats.length; i++){
            cats[i].eat(plate);
        }
        System.out.println();
    }

    public void info(){
        if(satiety){
            System.out.printf("Кот %s сыт\n", name);
        }else {
            System.out.printf("Кот %s голоден\n", name);

        }
    }
    public static void teamInfo(Cat[] cats){
        System.out.println("Сытость кошачьей команды: ");
        for(int i = 0; i < cats.length; i++){
            cats[i].info();
        }
        System.out.println();
    }

}
