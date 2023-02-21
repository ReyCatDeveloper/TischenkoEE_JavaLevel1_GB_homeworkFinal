package lesson4;

import java.util.Random;
import java.util.Scanner;

public class TicTacToe {
    private static int SIZE;
    private static int winSIZE;

    private static final char DOT_EMPTY = '*';
    private static final char DOT_HUMAN = 'X';
    private static final char DOT_AI = '0';

    //массив игрового поля
    private static char[][] MAP = new char[SIZE][SIZE];


    //прием значений
    private static final Scanner in = new Scanner(System.in);
    private static final Random random = new Random();
    //------------------------------------------------------
    //используется для анализа "являлся ли этот ход выигрышным"
    private static int lastStepRow;
    private static int lastStepColumn;
    //------------------------------------------------------
    //сведения о ходах пользователя. Используется для анализа действий
    private static int bestUserSeriesCount = 0;
    private static int[] bestUserSeriesRules = new int[3];
    private static int[] bestUserStep = new int[2];
    static int currentUserSeriesCount = 0;
    static int[] currentUserSeriesRules = new int[3];
    //------------------------------------------------------
    //сведения о ходах самого компьютера. Используется для анализа действий
    private static int bestAISeriesCount = 0;
    private static int[] bestAISeriesRules = new int[2];
    private static int[] bestAIStep = new int[2];
    //------------------------------------------------------
    static int[][] shiftRules = new int[][]{
            //index[0] -1(back), 1(forward)
            //index[1] horizontalShift
            //index[2] verticalShift
            //горизонтальное смещение
            {-1,1,0},{1,1,0},
            //вертикальное смещение
            {-1,0,1},{1,0,1},
            //диагональное смещение
            {-1,1,1},{1,1,1},
            //обратное диагональное смещение
            {-1,-1,1},{1,-1,1}};

    //Инициализация. изначально все игровые поля должны быть заполнены символами "пустоты"

    public static void main (String[] args){
        initialization();
        initMap();
        printMap();
        playGame();
    }

    private static void initialization(){
        requestMapSize();
        initWinSize();
        initMap();
    }

    private static void initWinSize() {
        if(SIZE == 3){
            winSIZE = 3;
        }
        //на диапазоне 4-6 и последующих winSize увеличен специально,
        // т.к. играть не интересно - вне зависимости от хода компьютера пользователь выигрывает на третий ход
        if(SIZE >= 4 && SIZE <= 6){
            winSIZE = 4;
        }
        if(SIZE >= 7 && SIZE <= 10){
            winSIZE = 5;
        }
        //условие больше 10 было обозначенно на лекции (поэтому добавлено), однако здесь неприменимо
        if(SIZE >10){
            winSIZE = 6;
        }
    }

    private static void requestMapSize(){
        //запрос на размер карты
        do {
            System.out.print("Введите размер поля от 3 до 10: ");
            SIZE = in.nextInt();
        } while (SIZE < 3 || SIZE > 10);
        MAP = new char[SIZE][SIZE];
    }

    private static void initMap() {
        //инициализация карты/массива
        for (int i =0; i < SIZE; i++){
            for(int j = 0; j < SIZE; j++){
                MAP [i][j] = DOT_EMPTY;
            }
        }
    }
    private static void printMap(){
        //вывод первой строки
        System.out.print("@"+"  ");
        for (int i=0; i <SIZE; i++){
            System.out.print(i + 1 + " ");
        }
        System.out.println();
        //вывод основного поля
        for (int i = 0; i < SIZE;i++){
            if (i+1 < 10){
            System.out.print(i+1 + "  ");
            for (int j = 0; j < SIZE; j++){
                System.out.print(MAP[i][j] + " ");
                }
                System.out.println();
            }else {
                System.out.print(i + 1 + " ");
                for (int j = 0; j < SIZE; j++) {
                    System.out.print(MAP[i][j] + " ");
                }
                System.out.println();
            }
        }
    }
    private static void playGame(){
        while (true){
            humanTurn();
            printMap();
            if (checkEnd(DOT_HUMAN)){
                break;
            }
            aiTurn();
            printMap();
            if (checkEnd(DOT_AI)){
                break;
            }
        }
    }
    private static void humanTurn(){
        System.out.println("Ход человека");
        currentUserSeriesCount = 0;
        int rowNumber;
        int columnNumber;
        while (true){
            System.out.print("Введите координату строки");
            rowNumber = in.nextInt() - 1;
            System.out.print("Введите координату столбца");
            columnNumber = in.nextInt() - 1;
            if (isCellFree(rowNumber, columnNumber)) {
            break;
            }
            System.out.printf("Ошибка! Ячейка с координатами %s:%s уже используется%n",rowNumber+1,columnNumber+1);
            }
        lastStepRow = rowNumber;
        lastStepColumn = columnNumber;
        MAP[rowNumber][columnNumber] = DOT_HUMAN;
    }
    
    public static void aiTurn(){
        System.out.println("Ход компьютера");
        int rowNumber;
        int columnNumber;
        int[] aiStep;
        if(bestAISeriesCount >= bestUserSeriesCount){
            //продлить свою серию
            //extendSeries();
            aiStep = extendAISeries();
            rowNumber = aiStep[0];
            columnNumber = aiStep[1];
        }else {
            //блокировать игрока
            aiStep = blockUserSeries();
            rowNumber = aiStep[0];
            columnNumber = aiStep[1];
        }
        lastStepRow = rowNumber;
        lastStepColumn = columnNumber;
        MAP[rowNumber][columnNumber] = DOT_AI;
    }

    private static int[] extendAISeries() {
        int[] aiStep = new int[2];


        if (validateStep(bestAIStep[0] + bestAISeriesRules[1], bestAIStep[1] + bestAISeriesRules[0]) == true) {
            if (isCellFree(bestAIStep[0] + bestAISeriesRules[1], bestAIStep[1] + bestAISeriesRules[0]) == true) {
                aiStep[0] = bestAIStep[0] + bestAISeriesRules[1];
                aiStep[1] = bestAIStep[1] + bestAISeriesRules[0];
                return aiStep;
            } else if (validateStep(bestAIStep[0] - bestAISeriesRules[1], bestAIStep[1] - bestAISeriesRules[0]) == true) {
                if (isCellFree(bestAIStep[0] - bestAISeriesRules[1], bestAIStep[1] - bestAISeriesRules[0]) == true) {
                    aiStep[0] = bestAIStep[0] - bestAISeriesRules[1];
                    aiStep[1] = bestAIStep[1] - bestAISeriesRules[0];
                    return aiStep;
                }
            }
        }
        //       System.out.println("продолжить серию не удалось");
        aiStep = blockUserSeries();
                /*do {
                    for (int i = 0; i < shiftRules.length; i++) {
                            if (validateStep(bestAIStep[0] + shiftRules[i][2], bestAIStep[1] + shiftRules[i][1]) == true){
                                if (isCellFree(bestAIStep[0] + shiftRules[i][2], bestAIStep[1] + shiftRules[i][1]) == true) {
                                    aiStep[0] = bestAIStep[0] + shiftRules[i][2];
                                    aiStep[1] = bestAIStep[1] + shiftRules[i][1];
                                    return aiStep;
                                } else if(validateStep(bestAIStep[0] - shiftRules[i][2], bestAIStep[1] - shiftRules[i][1]) == true){
                                    if (isCellFree(bestAIStep[0] - shiftRules[i][2], bestAIStep[1] - shiftRules[i][1]) == true) {
                                    aiStep[0] = bestAIStep[0] - shiftRules[i][2];
                                    aiStep[1] = bestAIStep[1] - shiftRules[i][1];
                                    return aiStep;
                                }
                            }
                        }
                    }
                }while (isCellFree(aiStep[0],aiStep[1]) == false);
        */


            return aiStep;
    }
    private static int[] extendCurrentAISeries(){
  //      System.out.println("начали продолжение текущей серии компьютера");
        int[] aiStep = new int[2];
        int counter = 0;
        do {
            for (int i = 0; i < shiftRules.length; i++) {
                if (validateStep(bestAIStep[0] + shiftRules[i][2], bestAIStep[1] + shiftRules[i][1]) == true){
                    if (isCellFree(bestAIStep[0] + shiftRules[i][2], bestAIStep[1] + shiftRules[i][1]) == true) {
                        aiStep[0] = bestAIStep[0] + shiftRules[i][2];
                        aiStep[1] = bestAIStep[1] + shiftRules[i][1];
                        return aiStep;
                    } else if(validateStep(bestAIStep[0] - shiftRules[i][2], bestAIStep[1] - shiftRules[i][1]) == true){
                        if (isCellFree(bestAIStep[0] - shiftRules[i][2], bestAIStep[1] - shiftRules[i][1]) == true) {
                            aiStep[0] = bestAIStep[0] - shiftRules[i][2];
                            aiStep[1] = bestAIStep[1] - shiftRules[i][1];
                            return aiStep;
                        }
                    }
                }
                counter = i;
 //               System.out.println(counter);

            }

        }while (isCellFree(aiStep[0],aiStep[1]) == false && (counter < (shiftRules.length-1)));
 //       System.out.println("запуск случайного генератора");
        do {
            aiStep[0] = random.nextInt(SIZE); //от 0 до 3
            aiStep[1] = random.nextInt(SIZE);
        }while (isCellFree(aiStep[0], aiStep[1]) != true);
        return aiStep;
    }

    private static int[] blockUserSeries() {
 //       System.out.println("НАчата блокировка");
        int[] aiStep = new int[2];
        do {
            aiStep[0] = random.nextInt(SIZE); //от 0 до 3
            aiStep[1] = random.nextInt(SIZE);
        }while (isCellFree(aiStep[0], aiStep[1]) != true);

            //блокировать серию игрока. Нужно - последний ход игрока и правило
        if(validateStep(bestUserStep[0]+bestUserSeriesRules[2], bestUserStep[1]+bestUserSeriesRules[1]) == true ){
            if(isCellFree((bestUserStep[0]+bestUserSeriesRules[2]), (bestUserStep[1]+bestUserSeriesRules[1])) == true) {
                //к строке должно прибавляться смещение по вертикали
                //к столбцу прибавляется правило смещения по горизонтали
                aiStep[0] = bestUserStep[0] + bestUserSeriesRules[2];
                aiStep[1] = bestUserStep[1] + bestUserSeriesRules[1];
                return aiStep;
            }else if(validateStep(bestUserStep[0]-bestUserSeriesRules[2], bestUserStep[1]-bestUserSeriesRules[1]) == true) {
                if (isCellFree((bestUserStep[0] - bestUserSeriesRules[2]), (bestUserStep[1] - bestUserSeriesRules[1]))) {
                    aiStep[0] = bestUserStep[0] - bestUserSeriesRules[2];
                    aiStep[1] = bestUserStep[1] - bestUserSeriesRules[1];
                return aiStep;
                }
            }
        }
        int[] bestSeriesBlockShifter = bestUserSeriesRules;
        while (validateStep(bestUserStep[0]+bestSeriesBlockShifter[0]*bestSeriesBlockShifter[2], bestUserStep[1]+bestSeriesBlockShifter[0]*bestSeriesBlockShifter[1]) == true ){
            if (isCellFree(bestUserStep[0]+bestSeriesBlockShifter[0]*bestSeriesBlockShifter[2], bestUserStep[1]+bestSeriesBlockShifter[0]*bestSeriesBlockShifter[1]) == true){
                aiStep[0] =bestUserStep[0]+bestSeriesBlockShifter[0]*bestSeriesBlockShifter[2];
                aiStep[1] =bestUserStep[1]+bestSeriesBlockShifter[0]*bestSeriesBlockShifter[1];
                return aiStep;
            }
            bestSeriesBlockShifter[1] = shiftIncrement(bestSeriesBlockShifter[1]);
            bestSeriesBlockShifter[2] = shiftIncrement(bestSeriesBlockShifter[2]);
        }



        //если последний ход лучшей серии пользователя блокировать не получается, тогда будем блокировать текущую,
        // если она меньше текущей серии
        if (currentUserSeriesCount <= bestUserSeriesCount) {
            int i = currentUserSeriesRules[2];
            int j = currentUserSeriesRules[1];
            while (validateStep(lastStepRow + i, lastStepColumn + j) == true) {

                if (isCellFree(lastStepRow +i, lastStepColumn + j) == true) {
                    //к строке должно прибавляться смещение по вертикали
                    //к столбцу прибавляется правило смещения по горизонтали
                    aiStep[0] = lastStepRow + i;
                    aiStep[1] = lastStepColumn + j;
                    return aiStep;
                }
                i = shiftIncrement(i);
                j = shiftIncrement(j);
            }

            /*if (validateStep(lastStepRow + currentUserSeriesRules[2], lastStepColumn + currentUserSeriesRules[1]) == true) {

                if (isCellFree(lastStepRow + currentUserSeriesRules[2], lastStepColumn + currentUserSeriesRules[1]) == true) {
                    //к строке должно прибавляться смещение по вертикали
                    //к столбцу прибавляется правило смещения по горизонтали
                    aiStep[0] = lastStepRow + currentUserSeriesRules[2];
                    aiStep[1] = lastStepColumn + currentUserSeriesRules[1];
                    return aiStep;
                */
            i = currentUserSeriesRules[2];
            j = currentUserSeriesRules[1];
            while (validateStep(lastStepRow - (i), lastStepColumn - (j)) == true) {
                if (isCellFree(lastStepRow - (i), lastStepColumn - (j)) == true) {
                    aiStep[0] = lastStepRow - (i);
                    aiStep[1] = lastStepColumn - (j);
                    return aiStep;
                }
                i = shiftIncrement(i);
                j = shiftIncrement(j);
            }

            /*if (validateStep(lastStepRow - currentUserSeriesRules[2], lastStepColumn - currentUserSeriesRules[1]) == true) {
                if (isCellFree(lastStepRow - currentUserSeriesRules[2], lastStepColumn - currentUserSeriesRules[1]) == true) {
                    aiStep[0] = lastStepRow - currentUserSeriesRules[2];
                    aiStep[1] = lastStepColumn - currentUserSeriesRules[1];
                    return aiStep;
                }
            }*/

        }
            aiStep = extendCurrentAISeries();
            return aiStep;

    }

    private static boolean validateStep(int row, int column) {
        if(row <= SIZE-1 && row >= 0 && column <= SIZE-1 && column >= 0){
            return true;
        }
        return false;
    }

    private static boolean checkEnd(char symbol){
        if (checkWin(symbol) == true){
            if(symbol == DOT_HUMAN){
                System.out.println("Ура! Вы победили!");
            }else{
                System.out.println("Восстание близко...Скайнет победил...");
            }
            return true;
        }
        
        if (checkDraw()){
            System.out.println("Ничья!");
            return true;
        }
        return false;
    }

    static boolean checkWinForLine(int[] currentRules, char symb){
        int lastStepCoordinate;
        int seriesCount;
        // = 0;

        //if(currentRules[0] == -1){
        //    if(lastStepCoordinate > 0){
                //(lastStepRow > 0) && (lastStepColumn < SIZE-1)
                seriesCount = winCounter("back",symb,currentRules[1],currentRules[2]) + 1;
                saveWinSeriesCount(symb, seriesCount, currentRules);
                if (symb == DOT_HUMAN){
                    //currentUserSeriesCount = seriesCount;
                    saveCurrentSeriesCount(symb, seriesCount, currentRules);
                }

                if(seriesCount >= winSIZE){
                    return true;
              }
        //    }
        //} else if (currentRules[0] == 1) {
        //    if (lastStepCoordinate < SIZE-1){
                seriesCount = seriesCount + winCounter("forward",symb,currentRules[1],currentRules[2]);
                saveWinSeriesCount(symb, seriesCount, currentRules);
                if(symb == DOT_HUMAN){
                    if(seriesCount >= currentUserSeriesCount){
                        //currentUserSeriesCount = seriesCount;
                        saveCurrentSeriesCount(symb, seriesCount, currentRules);
                        //currentUserSeriesRules = new int[]{0,currentRules[1],currentRules[2]};
                    }
                }
                if(seriesCount >= winSIZE){
                    return true;
                }else{
                    //winCount = 1;
                }
        //    }
        //}
        return false;
    }



    public static boolean checkWin(char symb) {
        //int winCount = 1;
        //TODO:задать проверку и правила  общим циклом
        for(int i = 0; i < shiftRules.length; i++){
            if (checkWinForLine(shiftRules[i],symb) == true){
                return true;
            }
        }
/*
        //проверка горизонтали
            if(lastStepColumn > 0){
                winCount = winCount + winCounter("back",symb,1,0);
                saveWinSeriesCount(symb, winCount);
                if(winCount >= winSIZE){
                    return true;
                }
            }
            if (lastStepColumn < SIZE-1){
                winCount = winCount + winCounter("forward",symb,1,0);
                if(winCount >= winSIZE){
                    return true;
                }else{
                    winCount = 1;
                }
            }
            //проверка по вертикали
            if(lastStepRow > 0){
                winCount = winCount + winCounter("back",symb,0,1);
                if(winCount >= winSIZE){
                    return true;
                }
            }
            if (lastStepRow < SIZE-1){
                winCount = winCount + winCounter("forward",symb,0,1);
                if(winCount >= winSIZE){
                    return true;
                }else{
                    winCount = 1;
                }
            }
            //проверка по диагонали
        if(lastStepRow > 0){
            winCount = winCount + winCounter("back",symb,1,1);
            if(winCount >= winSIZE){
                return true;
            }
        }
        if (lastStepRow < SIZE-1){
            winCount = winCount + winCounter("forward",symb,1,1);
            if(winCount >= winSIZE){
                return true;
            }else{
                winCount = 1;
            }
        }

         //TODO
        //проверка по обратной диагонали
        if(((lastStepRow > 0) && (lastStepColumn < SIZE-1)) == true){
            winCount = winCount + winCounter("back",symb,-1,1);
            if(winCount >= winSIZE){
                return true;
            }
        }
        if ((lastStepRow < SIZE-1 && lastStepColumn > 0 && lastStepRow >= 0) == true){    //раньше было lastStepRow > 0
            winCount = winCount + winCounter("forward",symb,-1,1);
            if(winCount >= winSIZE){
                return true;
            }else{
                winCount = 1;
            }
        }
*/
        //СТАРЫЙ ВАРИАНТ ПРОВЕРКИ ПОБЕДЫ - из лекции и методички
        /*if(MAP[0][0] == symb && MAP[0][1] == symb && MAP[0][2] == symb) return
                true;
        if(MAP[1][0] == symb && MAP[1][1] == symb && MAP[1][2] == symb) return
                true;
        if (MAP[2][0] == symb && MAP[2][1] == symb && MAP[2][2] == symb) return
                true;
        if (MAP[0][0] == symb && MAP[1][0] == symb && MAP[2][0] == symb) return
                true;
        if (MAP[0][1] == symb && MAP[1][1] == symb && MAP[2][1] == symb) return
                true;
        if (MAP[0][2] == symb && MAP[1][2] == symb && MAP[2][2] == symb) return

        true;
        if (MAP[0][0] == symb && MAP[1][1] == symb && MAP[2][2] == symb) return
                true;
        if (MAP[2][0] == symb && MAP[1][1] == symb && MAP[0][2] == symb) return
                true;*/
        return false;
    }

    private static void saveWinSeriesCount(char symb, int winCount, int[] currentRules) {
        if (symb == DOT_AI){

            if(winCount >= bestAISeriesCount){
            bestAISeriesCount = winCount;
            bestAIStep[0] = lastStepRow;
            bestAIStep[1] = lastStepColumn;
            bestAISeriesRules = currentRules;
            }
        }
        if (symb == DOT_HUMAN) {
            if (winCount >= bestUserSeriesCount) {
                bestUserSeriesCount = winCount;
                bestUserStep[0] = lastStepRow;
                bestUserStep[1] = lastStepColumn;
                bestUserSeriesRules = currentRules;
            }
        }
    }

    private static void saveCurrentSeriesCount(char symb, int seriesCount, int[] currentRules) {
        if (symb == DOT_HUMAN) {
            if (seriesCount >= currentUserSeriesCount &&
                    ((validateStep(lastStepRow+currentRules[2],lastStepColumn+currentRules[1]) == true) || (validateStep(lastStepRow-currentRules[2],lastStepColumn-currentRules[1]) == true)) == true) {
                currentUserSeriesCount = seriesCount;
                //bestUserStep[0] = lastStepRow;
                //bestUserStep[1] = lastStepColumn;
                currentUserSeriesRules = currentRules;
            }
        }
    }

    static int winCounter(String move, char symb, int horizontalShift, int verticalShift){
        int series = 0;

        switch (move){
            case("back"):
                while (lastStepColumn - horizontalShift >= 0 && lastStepRow - verticalShift >= 0 && lastStepColumn - horizontalShift <= SIZE - 1){
                    if(MAP[lastStepRow - verticalShift][lastStepColumn - horizontalShift] == symb){
                        series++;
                        horizontalShift = shiftIncrement(horizontalShift);
                        verticalShift = shiftIncrement(verticalShift);
                    }else{
                        break;
                    }
                }
                break;

            case("forward"):
                while ((lastStepColumn + horizontalShift) <= (SIZE-1) && (lastStepRow + verticalShift) <= (SIZE-1) && (lastStepColumn + horizontalShift) >= 0)  {
                    if(MAP[lastStepRow + verticalShift][lastStepColumn + horizontalShift] == symb){
                        series++;
                        horizontalShift = shiftIncrement(horizontalShift);
                        verticalShift = shiftIncrement(verticalShift);
                    }else {
                        break;
                    }
                }
                break;
        }
        return series;
    }

    private static int shiftIncrement(int shift) {
        if(shift == 0){
            return 0;
        }else if(shift > 0){
            shift++;
        } else if (shift < 0) {
            shift --;
        }
        return shift;
    }

    private static boolean checkDraw(){
        for (char[] chars : MAP){
            for (char symbol : chars){
                if (symbol == DOT_EMPTY){
                    return false;
                }
            }
        }
        
        return true;
    }
        
    private static boolean isCellFree(int rowNumber, int columnNumber){
        return MAP[rowNumber][columnNumber]==DOT_EMPTY;
    }
}

