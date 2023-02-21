package lesson1;

public class Task4 {
    public static void main(String[] args) {
        printColor();
     }
    static void printColor(){
        int value = 101;
        if(value <= 0){
            System.out.println("Красный");
        }else if (value > 0 && value <= 100){
            System.out.println("Жёлтый");
        } else if (value > 100) {
            System.out.println("Зеленый");
        }
    }
}
