import java.io.IOException;
import java.util.*;

public class Lesson4_TicTacToeGame {
    public static final int SIZE = 3;
    private static char[][] map;
    private static int step = 0;
    private static List<Step> humanSteps = new ArrayList<>();
    private static List<Step> aiSteps = new ArrayList<>();
    public static int DOTS_TO_WIN = 3;
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();


    public static void main(String[] args) throws IOException, InterruptedException {
        map = initMap(SIZE, DOT_EMPTY);
        printMap(map, step);
        do {
            step++;
            if (isFirstPlayer()) {
                humanTurn();
            } else {
                aiTurn();
            }
        } while (checkWin(isFirstPlayer() ? DOT_X : DOT_O));
        System.out.println("Игра закончена");
    }

    private static class Step {
        private int x;
        private int y;

        public Step(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }
    }


    public static int getStep() {
        return step;
    }

    public static void setStep(int newStep) {
        step = newStep;
    }

    public static boolean isFirstPlayer() {
        return getStep() % 2 == 1;
    }

    public static char[][] getMap() {
        return map;
    }

    public static void setMap(char[][] newMap) {
        map = newMap;
    }

    public static List<Step> getHumanSteps() {
        return humanSteps;
    }

    public static void setHumanSteps(List<Step> humanSteps) {
        Lesson4_TicTacToeGame.humanSteps = humanSteps;
    }

    public static List<Step> getAiSteps() {
        return aiSteps;
    }

    public static void setAiSteps(List<Step> aiSteps) {
        Lesson4_TicTacToeGame.aiSteps = aiSteps;
    }

    public static boolean checkWin(char symb) {
        boolean gameOver = false;
        if (map[0][0] == symb && map[0][1] == symb && map[0][2] == symb) gameOver = true;
        if (map[1][0] == symb && map[1][1] == symb && map[1][2] == symb) gameOver = true;
        if (map[2][0] == symb && map[2][1] == symb && map[2][2] == symb) gameOver = true;
        if (map[0][0] == symb && map[1][0] == symb && map[2][0] == symb) gameOver = true;
        if (map[0][1] == symb && map[1][1] == symb && map[2][1] == symb) gameOver = true;
        if (map[0][2] == symb && map[1][2] == symb && map[2][2] == symb) gameOver = true;
        if (map[0][0] == symb && map[1][1] == symb && map[2][2] == symb) gameOver = true;
        if (map[2][0] == symb && map[1][1] == symb && map[0][2] == symb) gameOver = true;
        if (isMapFull()) {
            gameOver = true;
            System.out.println("Ничья");
        }
        return !gameOver;
    }

    public static boolean isMapFull() {
        return getStep() > SIZE * SIZE;
    }

    public static void aiTurn() throws IOException, InterruptedException {
        int x, y;
        do {
            x = rand.nextInt(SIZE);
            y = rand.nextInt(SIZE);
        } while (!isCellValid(x, y, getMap()));
        System.out.println("Компьютер походил в точку " + (x + 1) + " " + (y + 1));
        getMap()[y][x] = DOT_O;
        fixStep(y, x);
        printMap(getMap(), getStep());
        System.out.println(getAiSteps().size());
        checkWin(DOT_O);
    }

    public static void humanTurn() throws IOException, InterruptedException {
        int x, y;
        do {
            x = Lesson3.getIntInRange("Введи координату X: ", 1, map.length) - 1;
            y = Lesson3.getIntInRange("Введи координату Y: ", 1, map.length) - 1;
        } while (!isCellValid(x, y, getMap()));
        getMap()[y][x] = DOT_X;
        fixStep(y, x);
        printMap(getMap(), getStep());
        System.out.println(getHumanSteps().size());
        checkWin(DOT_X);
    }

    public static void fixStep(int y, int x) {
        if (isFirstPlayer()) {
            getHumanSteps().add(new Step(x, y));
        } else {
            getAiSteps().add(new Step(x, y));
        }
    }

    public static boolean isCellValid(int x, int y, char[][] map) {
        if (x < 0 || x >= map.length || y < 0 || y >= map.length) {
            return false;
        }
        return map[y][x] == DOT_EMPTY;
    }

    public static char[][] initMap(int size, char symbol) {
        char[][] map = new char[size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                map[i][j] = symbol;
            }
        }
        return map;
    }

    public static void printMap(char[][] map, int step) throws IOException, InterruptedException {
        new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // очистка экрана в консоли Windows при запуске не из IDE
        if (step > 0) {
            System.out.printf("Ход %d из %d возможных\n", step, (map.length * map.length));
        }
        for (int i = 0; i <= map.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int i = 0; i < map.length; i++) {
            System.out.print((i + 1) + " ");
            for (int j = 0; j < map.length; j++) {
                System.out.print(map[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
