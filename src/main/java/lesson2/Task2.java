package lesson2;

public class Task2 {
    public static void main(String[] args) {
        checkNumber(-1);
        checkNumber(0);
        checkNumber(1);
        }
    static void checkNumber(int number){
    if (number >= 0 ){
        System.out.println(number + " положительное число");
    }else{
        System.out.println(number + " отрицательное число");
        }
    }
}
