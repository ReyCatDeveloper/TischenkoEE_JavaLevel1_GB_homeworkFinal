package lesson3;

public class Task8 {
    public static void main(String[] args) {
        System.out.println("Нечетные массивы");
        arrayShift(new int[]{0,1,2,3,4},1);
        arrayShift(new int[]{0,1,2,3,4},2);
        arrayShift(new int[]{0,1,2,3,4},5);
        arrayShift(new int[]{0,1,2,3,4},6);

        System.out.println("Четные массивы");
        arrayShift(new int[]{0,1,2,3},1);
        arrayShift(new int[]{0,1,2,3},2);
        arrayShift(new int[]{0,1,2,3},4);
        arrayShift(new int[]{0,1,2,3},5);

        System.out.println("Нечетные массивы с отрицательным смещением");
        arrayShift(new int[]{0,1,2,3,4},-1);
        arrayShift(new int[]{0,1,2,3,4},-2);
        arrayShift(new int[]{0,1,2,3,4},-5);
        arrayShift(new int[]{0,1,2,3,4},-6);

        System.out.println("Четные массивы с отрицательным смещением");
        arrayShift(new int[]{0,1,2,3},-1);
        arrayShift(new int[]{0,1,2,3},-2);
        arrayShift(new int[]{0,1,2,3},-4);
        arrayShift(new int[]{0,1,2,3},-5);
    }

    //отображение массива
    static void showArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            System.out.print(array[i] + " ");
        }
        System.out.print("\n");
    }

    //метод смещения массива
    static void arrayShift (int[] array, int shift){
        //отображение смещения
        System.out.println("shift: "+shift+" (" + (((shift % array.length) + array.length) % array.length) + ")");

        //упрощение на отрицательное смещение
        if (shift < 0) {
            shift = (shift % array.length) + array.length; // -6 + 4
        }

        //упрощение, если смещение превышает длину массива
        if (shift > array.length) {
            shift = (shift % array.length) ; // -6 + 4
        }
        showArray(array);

        //начало обхода массива
        int startingIndex = 0;
        //значение при начале обхода
        int currentValue = array[startingIndex];
        //значение, которое замещается новым при смещении.
        // необходимо запомнить и перенести согласно смещению в следующем шаге.
        int bufferValue;

        //цикл обхода со смещением
        for(int counter = 0; counter < array.length; counter++){
        //подстановка чисел идет не по порядку, а в соответствии с шагом смещения. Один Элемент замещает другой с шагом смещения.

            //поправка на четные массивы с четным смещением.
            // При обходе первой четной половины четного массива - происходит смещение "запоминания" на "нечетную"
            if((startingIndex + shift) % array.length == 0 && counter != 0) {
            bufferValue = array[(startingIndex + shift + 1) % array.length];}else{
            bufferValue = array[(startingIndex + shift) % array.length];}
            //System.out.println("Запомним: " +bufferValue);

            //перемещение значения
            array[(startingIndex + shift) % array.length] = currentValue;
           // System.out.print("Результат : "); showArray(array);
           // System.out.println("");

            //поправка на четные массивы с четным смещением.
            // При обходе первой четной половины четного массива - происходит смещение индекса на "нечетную"
            if((startingIndex + shift) % array.length == 0 && counter != 0) {
                startingIndex = (startingIndex + shift + 1) % array.length;}else{
                startingIndex = (startingIndex + shift) % array.length;}

            //начало нового цикла. теперь перемещаемое число равно прежнему буферному
            currentValue = bufferValue;

            //КОНТРОЛЬ!!!!!
            //showArray(array);
            //System.out.println(bufferValue);
            //System.out.print("\n");

            }
        showArray(array);
        System.out.println("");
            //для просмотра результатов по итерациям
            //System.out.println("");
            //showArray(array);
            //System.out.println("");
        }

    //СТАРЫЙ ВАРИАНТ ПРОСЧЕТА НОВОГО МЕСТА В МАССИВЕ ДЛЯ ПОСЛЕДНЕГО ИНДЕКСА
    /*
    static int calculationLastIndex(int[] array, int shift){
        //showArray(array);
        //System.out.println("длина массива: " + L);
        //System.out.println("макс индекс: " + maxIndex);
        //System.out.println("смещение: " + shift);

            //ДЛЯ ПРОВЕРКИ и СРАВНЕНИЯ ПРИ СБОЕ!
            //int maxIndex = L-1;
            //int newIndexInaccuracy = maxIndex-(L- dr);
            //int logicalShiftOperator = maxIndex- dr;
            //int logicalShiftOperator1 = logicalShiftOperator/ maxIndex;
            //int logicalShiftOperator2 = L - maxIndex - logicalShiftOperator1;
            //int controlVar = newIndexInaccuracy * logicalShiftOperator2 + maxIndex * logicalShiftOperator1;

        int L = array.length; // переменная - длина массива
        int dr = shift % array.length; //переменная - остаток от деления длины массива на смещение

        //данная формула получена в мучениях через исходные числа
        // с применением упрощения и некоторой комбинаторики. Идеально описывает
        // зависимость нового индекса последнего элемента массива, исходя из длины массива и кратности сдвига
        int newIndex = (-1 +dr)*(1-(L-1-dr)/(L-1))+(L-1)*((L-1-dr)/(L-1));

        System.out.println("");
        return newIndex;
    }
    */
}
