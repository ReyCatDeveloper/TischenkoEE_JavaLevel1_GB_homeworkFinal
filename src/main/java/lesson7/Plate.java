package lesson7;

public class Plate {
    private int food;

    public Plate(int food) {
        this.food = food;
    }

    public int getFood() {
        return food;
    }

    public void setFood(int food) {
        this.food = food;
    }

    public void info(){
        System.out.println("plate: " + food);
    }

    public void decreaseFood(int decrease){
        food -= decrease;
    }

    public void additiveFood(int value){
        System.out.println("В миску добавили еды");
        food += value;
    }
}
