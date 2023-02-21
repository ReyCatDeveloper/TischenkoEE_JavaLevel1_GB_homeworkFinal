package lesson4;

import java.util.Random;
import java.util.Scanner;

import static lesson4.TicTacToe2.Game.*;
import static lesson4.TicTacToe2.Player.PlayerAI.aiSymbol;
import static lesson4.TicTacToe2.Player.PlayerAI.playerAI;
import static lesson4.TicTacToe2.Player.PlayerUser.playerUser;
import static lesson4.TicTacToe2.Player.PlayerUser.userSymbol;

public class TicTacToe2 {
    private static final Scanner in = new Scanner(System.in);
    private static final Random random = new Random();
    //метод майн: запуск и управление игрой
    public static void main(String[] args) {
        welcome();
        requestMapSize();
        initializationPlayerSymbols();
        initWinSeries();
        initializationMAP();
        showAndRefreshMap();
        startGame();

    }

    //Вложенный класс игра: основные настройки игры и общеигровые методы/операции
    public static class Game{
        static int SIZE = 3;
        static final String emptyMAP = "*";
        static String MAP[][] = new String[SIZE][SIZE];
        static int winSeries;
        static void startGame(){
            while(true){
                playerUser.step();
                if(checkEndGame(userSymbol, playerUser.lastStep) == true){
                    break;
                }
                playerAI.step();
                if(checkEndGame(aiSymbol, playerAI.lastStep) == true){
                    break;
                }
            }
        }
        //------------------------------
        //ПРИВЕТСТВИЕ ИГРОКА_ПОЛЬЗОВАТЕЛЯ
        public static void welcome(){
            System.out.println("|--------Игра Крестики-Нолики--------|");
            System.out.println("Привет! тебе предстоит сразиться с компьютером");

        }
        //------------------------------
        //Выбор и инициализация символа игрока: крестик или нолик
        //компьютер автоматически выберет другой символ
        public static void initializationPlayerSymbols(){
            do {
                System.out.print("Выбери X или O:");
                userSymbol = in.next();
                userSymbol = userSymbol.intern().toLowerCase();
                if (userSymbol.intern() == "x" || userSymbol.intern() == "х") {
                    userSymbol = "X";
                }
                if (userSymbol.intern() == "o" || userSymbol.intern() == "о" || userSymbol.intern() == "0") {
                    userSymbol = "O";
                }
            }while (userSymbol.intern() != "X" && userSymbol.intern() != "O");

            switch (userSymbol){
                case ("X"):
                    Player.PlayerAI.aiSymbol = "O";
                    break;
                case ("O"):
                    Player.PlayerAI.aiSymbol = "X";
                    break;
            }
        }
        //ЗАПРОС РАЗМЕРА ИГРОВОГО ПОЛЯ
        static void requestMapSize(){
            //запрос на размер карты
            do {
                System.out.print("Введите размер поля от 3 до 10: ");
                SIZE = in.nextInt();
            } while (SIZE < 3 || SIZE > 10);
            MAP = new String[SIZE][SIZE];
        }
        //------------------------------
        //ИНИЦИАЛИЗАЦИЯ РАЗМЕРА ПОБЕДНОЙ СЕРИИ
        static void initWinSeries() {
            if(SIZE >= 3 && SIZE <= 6){
                winSeries = 3;
            }
            if(SIZE >= 7 && SIZE <= 10){
                winSeries = 4;
            }
            //условие больше 10 было обозначенно на лекции (поэтому добавлено), однако здесь неприменимо
            if(SIZE >10){
                winSeries = 5;
            }
        }
        //------------------------------
        //ИНИЦИАЛИЗАЦИЯ ИГРОВОГО ПОЛЯ
        public static void initializationMAP(){
            for (int i = 0; i < SIZE; i++){
                for(int j = 0; j < SIZE; j++){
                    MAP[i][j] = emptyMAP ;
                }
            }
        }
        //------------------------------
        //ПОКАЗ/ОБНОВЛЕНИЕ ИГРОВОГО ПОЛЯ
        public static void showAndRefreshMap() {
           //ВЫВОД ПЕРВОЙ СТРОКИ
            System.out.print("@"+" ");
            for (int i = 1; i <= SIZE; i++) {
                System.out.print("  " + i);
            }
            System.out.println();
            //ВЫВОД ОСНОВНОГО ПОЛЯ
            for (int i = 0; i < SIZE; i++) {
                if (i <= 8 ){
                    System.out.print(i+1 + " ");
                }else {
                    System.out.print(i+1);
                }
                for (int j = 0; j < SIZE; j++) {
                        System.out.print("  " + MAP[i][j]);
                }
                System.out.println();
            }
        }
        //-------------------
        //ПРОВЕРКА СВОБОДНА ЛИ ЯЧЕЙКА
        static boolean isCellFree(int rowNumber, int columnNumber){
            if(MAP[rowNumber][columnNumber].intern() == emptyMAP ){
                return true;
            }
            return false;
        }
        //-------------------
        //ПРОВЕРКА НА КОНЕЦ ИГРЫ
        private static boolean checkEndGame(String playerSymbol, int[] playerStep ) {
            //проверка победы
            if(isWin(playerSymbol, playerStep) == true){
                victoryMessage(playerSymbol);
                return true;
            }else {
                //проверка ничьи
                if(isDraw() == true){
                    return true;
                }
            }
            return false;
        }
        //-------------------
        //ПРОВЕРКА НА ПОБЕДУ
        private static boolean isWin(String playerSymbol, int[] playerStep) {
            int winSeriesCount = 0;
            //проверка горизонтали
            {
                for (int i = 0; i < winSeries; i++) {
                    if (MAP[playerStep[0]][i].intern() == playerSymbol.intern()) {
                        winSeriesCount++;
                        if (winSeriesCount >= winSeries){
                            return true;
                        }
                        }else {
                            winSeriesCount = 0;
                        }
                    }
                }
            //проверка вертикали
            {
                for (int i = 0; i < winSeries; i++) {
                    if (MAP[i][playerStep[1]].intern() == playerSymbol.intern()) {
                        winSeriesCount++;
                        if (winSeriesCount >= winSeries){
                            return true;
                        }
                    }else {
                        winSeriesCount = 0;
                    }
                }

                if (winSeriesCount >= winSeries) {
                    return true;
                } else if (0 < winSeriesCount && winSeriesCount < winSeries) {
                    winSeriesCount = 0;
                    if (winSeriesCount >= winSeries){
                        return true;
                    }
                }
            }
            //проверка диагонали
            {
                int horizontalShift = 0;
                int verticalShift = 0;
                if(playerStep[0] > playerStep[1]){
                    verticalShift = playerStep[0] - playerStep[1];
                } else if (playerStep[1] > playerStep[0]) {
                    horizontalShift = playerStep[1] - playerStep[0];
                }
                for (int i = 0; i+verticalShift < SIZE && i+horizontalShift < SIZE; i++) {
                    if (MAP[i+verticalShift][i+horizontalShift].intern() == playerSymbol.intern()) {
                        winSeriesCount++;
                        if (winSeriesCount >= winSeries){
                            return true;
                        }
                    }else {
                        winSeriesCount = 0;
                    }
                }
            }
            //проверка обратной диагонали
            {
                int horizontalShift = 0;
                int verticalShift = 0;
                if(playerStep[0]+playerStep[1] > SIZE -1){
                    verticalShift = (playerStep[0]+playerStep[1]) - (SIZE-1);
                } else if (playerStep[0]+playerStep[1] < SIZE -1) {
                    horizontalShift = (playerStep[0]+playerStep[1]) - (SIZE -1) ;
                }
                for (int i = 0; i + verticalShift <= SIZE -1 && i + horizontalShift >= 0; i++) {
                    if (MAP[i + verticalShift][(SIZE-1 + horizontalShift) - i].intern() == playerSymbol.intern()) {
                        winSeriesCount++;
                        if (winSeriesCount >= winSeries){
                            return true;
                        }
                    }else {
                        winSeriesCount = 0;
                    }
                }
            }
            return false;
        }
        //------------------------------
        //ПРОВЕРКА НА НИЧЬЮ
        private static boolean isDraw() {
            for(int i = 0; i < SIZE; i++) {
                for (int j = 0; j < SIZE; j++) {
                    if (MAP[i][j].intern() == emptyMAP.intern()){
                        return false;
                    }
                }
            }
            System.out.println("Ничья! Человек и компьютер равны!!");
            System.out.println("Игра закончена");
            return true;
        }
        //------------------------------
        //ПОБЕДНОЕ СООБЩЕНИЕ
        private static void victoryMessage(String playerSymbol) {
            if(playerSymbol.intern() == userSymbol.intern()){
                System.out.println("Поздравляем! Вы Выиграли");
                System.out.println("Игра закончена");
            }else {
                System.out.println("Компьютер превзошел Вас...");
                System.out.println("Игра закончена");
            }
        }
    }

    //ВЛОЖЕННЫЙ КЛАСС: ИГРОК. Описание и действия пользователей
    public static abstract class Player{
        public abstract void step();
        //Player playerUser = new Player();
        int[] lastStep;

        //ИГРОК-ЧЕЛОВЕК
        public static class PlayerUser extends Player{
           public static PlayerUser playerUser = new PlayerUser();
            static String userSymbol;
            {lastStep = new int[2];}
            @Override
            public void step() {
                System.out.println("Ход человека");
                int rowNumber;
                int columnNumber;
                do {
                    do {
                        System.out.print("Введите номер строки: ");
                        rowNumber = in.nextInt() - 1;
                    }while (rowNumber < 0 || rowNumber > SIZE-1);
                    do {
                        System.out.print("Введите номер столбца: ");
                        columnNumber = in.nextInt() - 1;
                    }while (columnNumber < 0 || columnNumber > SIZE-1);
                //    boolean check = isCellFree(rowNumber, columnNumber);
                //System.out.println(check);

                    if (isCellFree(rowNumber, columnNumber) == true) {
                        MAP[rowNumber][columnNumber] = userSymbol;
                        break;
                    }
                } while (true);
                lastStep[0] = rowNumber;
                lastStep[1] = columnNumber;
                showAndRefreshMap();
            }
        }

        //ИГРОК-КОМПЬЮТЕР
        public static class PlayerAI extends Player{
            public static PlayerAI playerAI = new PlayerAI();
            {lastStep = new int[2];}
            static String aiSymbol;
            //@Override
            public void step(){
                System.out.println("Ход компьютера");
                int rowNumber;
                int columnNumber;
                do {
                rowNumber = random.nextInt(SIZE);
                columnNumber = random.nextInt(SIZE);
                    //boolean check = isCellFree(rowNumber, columnNumber);
                    //System.out.println(check);

                    if (isCellFree(rowNumber, columnNumber) == true) {
                        MAP[rowNumber][columnNumber] = aiSymbol;
                        break;
                    }
                } while (true);
                System.out.printf("Компьютер ходит: %s:%s\n",rowNumber+1,columnNumber+1);
                lastStep[0] = rowNumber;
                lastStep[1] = columnNumber;
                showAndRefreshMap();

            }
        }
    }
}
