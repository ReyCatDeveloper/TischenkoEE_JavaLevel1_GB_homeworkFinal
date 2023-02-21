package lesson3;

public class Task1 {
    public static void main(String[] args) {
        //для проверки
        checkingTask();
    }

    static int[] intArray = {1,0,1,1,1,0,1,1,1,0,0,0,1};
    static void changeArray(int[] intArray){
        for(int i=0; i<intArray.length; i++){
            if(intArray[i] == 0){
                intArray[i] = 1;
            } else if (intArray[i] == 1) {
                intArray[i] = 0;
            }
        }
    }

    //метод для проверки
    static void checkingTask(){
        for(int i=0; i<intArray.length; i++){
            System.out.print(intArray[i]);
        }
        System.out.println("");
        changeArray(intArray);
        for(int i=0; i<intArray.length; i++){
            System.out.print(intArray[i]);
        }
    }
}
