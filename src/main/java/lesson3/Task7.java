package lesson3;

public class Task7 {
    public static void main(String[] args) {
        System.out.println(checkBalance(new int[]{1, 1}));
        System.out.println(checkBalance(new int[]{1, 2}));
        System.out.println(checkBalance(new int[]{1, 1, 2}));
        System.out.println(checkBalance(new int[]{1, 1, 1, 3}));
        System.out.println(checkBalance(new int[]{-1, 1,0}));
        System.out.println(checkBalance(new int[]{-1, 1, 5, 2, 5, 14}));

    }
    static boolean checkBalance (int[] array){
        if(array.length != 0){
            /*
            проверка четности суммы массива. Если сумма нечетная,
            то поделить массив на две равные части нацело - невозможно
            */
            int arraySum = 0;
            for(int i = 0; i < array.length;i++){
                arraySum = arraySum + array[i];
            }
            if(arraySum %2 == 0){
                //если сумма массива четная, то пытаемся найти середину
                int leftSum = 0;
                int rightSum = arraySum;
                for(int j = 0; j < array.length; j++){
                 leftSum = leftSum + array[j];
                 rightSum = rightSum - array[j];
                 if (leftSum == rightSum){
                     return true;
                 }
             }
            }
        }else {
            System.out.println("Массив пустой!");
        }
        return false;
    };
}
