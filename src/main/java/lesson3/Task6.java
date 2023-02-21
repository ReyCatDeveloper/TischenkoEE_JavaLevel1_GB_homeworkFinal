package lesson3;

public class Task6 {
    static int[] array = {-7,2,8,1,6,5,9,2,1,-7,-9,9,-9,-9,-10};
    public static void main(String[] args) {
        findExtreme(array);
    }
    //Метод поиска минимумов и максимумов последовательности
    static void findExtreme(int[] array) throws NullPointerException{
        //стартовые условия
        int countMinimal = 1;
        int countMax = 1;
        int minValue = array[0];
        int maxValue = array[0];
        /*
        массивы для сохранения +/- экстремумов,
        если их у последовательности больше 1, но заранее их числонеизвестно
        */
        int[] minimal = new int[]{0};
        int[] max = new int[]{0};
        System.out.printf("Длина массива: %s элементов (max index %s)\n", array.length, array.length-1);
        System.out.println("массив: "+printArray(array));

        //обход массива: поиск минимумов
        for(int i=1; i < array.length; i++) {
            if(array[i] < minValue){
                minValue = array[i];
                countMinimal = 1;
                minimal = new int[]{i};
            } else if (minValue == array[i]) {
                //если найден второй минимум, то он также запоминается
                countMinimal++;
                /*
                в новый массив передаем все значения (номера минимумов)
                из прежнего массива, а последним - добавляем новое значение.
                Можно сделать проще через ArrayList, но по курсу мы его еще не проходили :)
                */
                minimal = expansionExtremeArray(minimal,countMinimal,i);
            }
            //обход массива: поиск максимумов
            if(array[i] > maxValue){
                maxValue = array[i];
                countMax = 1;
                max = new int[]{i};
            }else if (maxValue == array[i]) {
                countMax++;
                max = expansionExtremeArray(max,countMax,i);
            }
        }
        //импровизированный отчет, чтобы не захламлять общий код
        findExtremeReport (array, minValue, maxValue, countMinimal, countMax, minimal, max);
        }
    static int[] expansionExtremeArray(int[] sourceArray, int newSize, int newValue){
        int[] newArray = new int[newSize];
        for(int z = 0; z < newArray.length; z++){
            if(z <= (sourceArray.length-1)){
                newArray[z] = sourceArray [z];
            } else {
                newArray[z] = newValue;
            }
        }
        return newArray;
    }
    static void findExtremeReport (int[] array,int minValue,int maxValue,int countMinimal,int countMax,int[] minimal, int[] max){
        System.out.println("Минимум : "+minValue);
        System.out.println("Максимум: "+maxValue);
        System.out.println("Число минимумов:  "+countMinimal+"(index:"+printArray(minimal)+")");
        System.out.println("Число максимумов: "+countMax+"(index:"+printArray(max)+")");
    }
    static String printArray(int[] array){
        String printOutput = "";
        for(int i=0; i < array.length; i++){
            printOutput = printOutput+" "+array[i];
               }
        return printOutput;
    }


    }
