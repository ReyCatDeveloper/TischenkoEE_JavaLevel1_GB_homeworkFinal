package lesson3;

public class Task2 {
    public static void main(String[] args) {
        fillingArray(array);
        showArray(array);
    }
    //объявение массива
    static int[] array = new int[100];
    //заполнение массива
    static void fillingArray(int[] array){
        for(int i=0; i < array.length; i++){
            array[i] = i+1;
        }
    }
    //отображение массива (визуальная проверка)
    static void showArray(int[] array){
        for(int i=0; i<array.length; i++){
            System.out.print(array[i]+" ");
        }
    }
}
