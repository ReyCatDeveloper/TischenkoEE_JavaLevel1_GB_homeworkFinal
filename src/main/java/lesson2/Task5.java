package lesson2;

public class Task5 {
    public static void main(String[] args) {
        for(int i = 2000; i <= 2040;i++){
            if (checkLeapYear(i)){
                        System.out.printf("%s - високосный\n",i);
                    }
            }
    }
    static boolean checkLeapYear(int yearNumber){

             if(yearNumber % 4 == 0){
            if(yearNumber % 100 != 0){
                return true;
            } else if (yearNumber % 400 == 0) {
                return true;
            }
        }
        return false;
    }
}
