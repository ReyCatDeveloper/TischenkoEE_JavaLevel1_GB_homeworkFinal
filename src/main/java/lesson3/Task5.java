package lesson3;

public class Task5 {
    public static void main(String[] args) {
        //отображение созданных массивов
        showArray(fillingArray(1,20));
        showArray(fillingArray(5,1));
        showArray(fillingArray(10,8));

    }
    //метод создания и заполнения массива
    static int[] fillingArray(int len,int initialValue){
        int[] array = new int[len];
        for(int i = 0; i < array.length; i++){
            array[i] = initialValue;
        }
        return array;
    }
    //отображение массива (визуальная проверка)
    static void showArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.println("");
    }
}
