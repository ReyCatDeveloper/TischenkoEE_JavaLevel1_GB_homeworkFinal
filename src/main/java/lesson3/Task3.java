package lesson3;

public class Task3 {
    //объявление массива
    static int[] array = {1,5,3,2,11,4,5,2,4,8,9,1};
    public static void main(String[] args) {
        showArray(array);
        editArray(array);
        showArray(array);
    }
    //изменение массива
    static void editArray(int[] array){
        for (int i=0; i < array.length; i++){
            if (array[i] < 6){
                array[i]=array[i]*6;
            }
        }
    }
    //отображение массива (визуальная проверка)
    static void showArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("");
    }

}
