package lesson2;

public class Task1 {
    public static void main(String[] args) {

        System.out.println(checkSumFrom10to20(11,10));
    }
    static boolean checkSumFrom10to20(int number1, int number2){
        int sum = (number1+number2);
        if(sum >= 10 && sum <= 20){
            return true;
        }
        return false;
    }
}
