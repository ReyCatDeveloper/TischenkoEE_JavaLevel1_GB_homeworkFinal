package lesson1;

public class Task3 {
    public static void main(String[] args) {
        checkSumSign();
    }
    static void checkSumSign() {
        int a = +12;
        int b = -12;
        if (a + b >= 0) {
            System.out.println("Сумма положительная");
        } else {
            System.out.println("Сумма отрицательная");
        }
    }
}
