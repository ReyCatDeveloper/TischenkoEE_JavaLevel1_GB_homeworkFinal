package lesson3;

public class Task4 {
    static int[][] twoDimensionalArray = new int[5][5];

    public static void main(String[] args) {
        showTwoDimensionalArray(twoDimensionalArray);
        System.out.println("");
        fillingArrayDiagonal(twoDimensionalArray);
        showTwoDimensionalArray(twoDimensionalArray);
    }

    static void fillingArrayDiagonal(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                if (i == j) {
                    array[i][j] = 1;
                } else if ((array.length-1) - i == j) {
                    array[i][j] = 1;
                }
            }
        }
    }
    //отображение массива (визуальная проверка)
    static void showTwoDimensionalArray(int[][] array) {
        for (int i = 0; i < array.length; i++) {
            for (int j = 0; j < array[i].length; j++) {
                System.out.print(array[i][j]);
            }
            System.out.println("");
        }
    }
}