package ticTacToe;

import java.util.*;
import java.util.stream.Collectors;

import helper.Helper;

public class TicTacToe {
    private static char[][] map;
    private static int mapSize = 3;
    private static int dotsToWin = 3;
    private static int step = 0;
    private static int aiIqLevel = 3;
    private static List<Step> humanSteps = new ArrayList<>();
    private static List<Step> aiSteps = new ArrayList<>();
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';

    public static Scanner sc = new Scanner(System.in);
    public static Random rand = new Random();

    public static int getStep() {
        return step;
    }

    public static void setStep(int newStep) {
        step = newStep;
    }

    public static void addStep() {
        step++;
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

    public static void setHumanSteps(List<Step> newHumanSteps) {
        humanSteps = newHumanSteps;
    }

    public static List<Step> getAiSteps() {
        return aiSteps;
    }

    public static void setAiSteps(List<Step> newAiSteps) {
        aiSteps = newAiSteps;
    }

    public static int getMapSize() {
        return mapSize;
    }

    public static void setMapSize(int newMapSize) {
        mapSize = newMapSize;
    }

    public static int getDotsToWin() {
        return dotsToWin;
    }

    public static void setDotsToWin(int newDotsToWin) {
        dotsToWin = newDotsToWin;
    }

    public static int getAiIqLevel() {
        return aiIqLevel;
    }

    public static void setAiIqLevel(int aiIqLevel) {
        TicTacToe.aiIqLevel = aiIqLevel;
    }

    public static boolean checkWin() {
        boolean gameOver = true;
        for (int i = 0; i < getMapSize(); i++) {
            if (checkWinInSeries(getStepInRow(i, getHumanSteps()))
                    || checkWinInSeries(getStepInColumn(i, getHumanSteps()))) {
                System.out.println("Победил человек");
                gameOver = false;
            }
            if (checkWinInSeries(getStepInRow(i, getAiSteps()))
                    || checkWinInSeries(getStepInColumn(i, getAiSteps()))) {
                gameOver = false;
            }
        }
        for (int i = 0; i < getMapSize() * 2 - 1; i++) {
            if (checkWinInSeries(getStepInDiagonal1(i, getHumanSteps())) ||
                    checkWinInSeries(getStepInDiagonal2(i, getHumanSteps()))) {
                System.out.println("Победил человек");
                gameOver = false;
            }
            if (checkWinInSeries(getStepInDiagonal1(i, getAiSteps())) ||
                    checkWinInSeries(getStepInDiagonal2(i, getAiSteps()))) {
                System.out.println("Победил искусственный интеллект");
                gameOver = true;
            }
        }
        if (isMapFull()) {
            System.out.println("Ничья");
            gameOver = false;
        }
        return gameOver;
    }

    public static boolean checkWinInSeries(List<Step> seriesList) {
        if (seriesList.size() >= getDotsToWin()) {
            int stepsInSeries = 1;
            Iterator<Step> iterator = seriesList.iterator();
            Step firstStep = iterator.next();
            while (iterator.hasNext()) {
                Step nextStep = iterator.next();
                if ((firstStep.getRow() - nextStep.getRow() == 1 || firstStep.getRow() - nextStep.getRow() == -1)
                        || (firstStep.getColumn() - nextStep.getColumn() == 1 || firstStep.getColumn() - nextStep.getColumn() == -1)) {
                    stepsInSeries++;
                } else {
                    stepsInSeries = 1;
                }
                firstStep = nextStep;
                if (stepsInSeries == getDotsToWin()) {
                    return true;
                }
            }
        }
        return false;
    }

    public static List<Step> getStepInDiagonal1(int diagonal, List<Step> list) {
        List<Step> steps = new ArrayList<>();
        int row = diagonal;
        int column = 0;
        for (int i = 0; i < getMapSize(); i++) {
            if (list.contains(new Step(row, column))) {
                steps.add(new Step(row, column));
            }
            row--;
            column++;
        }
        steps.sort(Comparator.comparingInt(Step::getRow));
        return steps;
    }

    public static List<Step> getStepInDiagonal2(int diagonal, List<Step> list) {
        List<Step> steps = new ArrayList<>();
        int row = getMapSize() - 1;
        int column = diagonal;
        for (int i = 0; i < getMapSize(); i++) {
            if (list.contains(new Step(row, column))) {
                steps.add(new Step(row, column));
            }
            row--;
            column--;
        }
        steps.sort(Comparator.comparingInt(Step::getRow));
        return steps;
    }

    public static List<Step> getStepInRow(int row, List<Step> list) {
        return list.stream().filter(step -> step.getRow() == row).sorted(Comparator.comparingInt(Step::getColumn)).collect(Collectors.toList());
    }

    public static List<Step> getStepInColumn(int column, List<Step> list) {
        return list.stream().filter(step -> step.getColumn() == column).sorted(Comparator.comparingInt(Step::getRow)).collect(Collectors.toList());
    }

    public static boolean isMapFull() {
        return getStep() >= mapSize * mapSize;
    }

    public static void aiTurn() {
        int row, column;
        Step newStep = null;
        if (rand.nextInt(10) <= getAiIqLevel()) {
            List<Step> priorityStepsHuman = new ArrayList<>();
            List<Step> priorityStepsAi = new ArrayList<>();
            for (int i = 0; i < getMapSize(); i++) {
                List<Step> stepsHumanInRow = getStepInRow(i, getHumanSteps());
                List<Step> stepsAiInRow = getStepInRow(i, getAiSteps());
                if (stepsHumanInRow.size() > 1 && stepsHumanInRow.size() + stepsAiInRow.size() < getMapSize()) {
                    for (int j = 0; j < getMapSize(); j++) {
                        if (isCellValid(i, j, getMap())) {
                            if ((stepsHumanInRow.contains(new Step(i, j - 1)) && stepsHumanInRow.contains(new Step(i, j + 1)))) {
                                priorityStepsHuman.add(new Step(i, j));
                            }
                            if ((stepsAiInRow.contains(new Step(i, j - 1)) && stepsAiInRow.contains(new Step(i, j + 1)))
                                    || (stepsAiInRow.contains(new Step(i, j - 2)) && stepsAiInRow.contains(new Step(i, j - 1)))
                                    || (stepsAiInRow.contains(new Step(i, j + 2)) && stepsAiInRow.contains(new Step(i, j + 1)))) {
                                priorityStepsAi.add(new Step(i, j));
                            }
                        }
                    }
                }
                List<Step> stepsHumanInColumn = getStepInColumn(i, getHumanSteps());
                List<Step> stepsAiInColumn = getStepInColumn(i, getAiSteps());
                if (stepsHumanInColumn.size() > 1 && stepsHumanInColumn.size() + stepsAiInColumn.size() < getMapSize()) {
                    for (int j = 0; j < getMapSize(); j++) {
                        if (isCellValid(j, i, getMap())) {
                            if (stepsHumanInColumn.contains(new Step(j - 1, i)) && stepsHumanInColumn.contains(new Step(j + 1, i))) {
                                priorityStepsHuman.add(new Step(j, i));
                            }
                            if ((stepsAiInColumn.contains(new Step(j - 1, i)) && stepsAiInColumn.contains(new Step(j + 1, i)))
                                    || (stepsAiInColumn.contains(new Step( j - 2, i)) && stepsAiInColumn.contains(new Step( j - 1, i)))
                                    || (stepsAiInColumn.contains(new Step(j + 2, i)) && stepsAiInColumn.contains(new Step( j + 1, i)))) {
                                priorityStepsAi.add(new Step(j, i));
                            }
                        }
                    }
                }
            }
            if (priorityStepsAi.size() > 0) {
                    newStep = priorityStepsAi.get(rand.nextInt(priorityStepsAi.size()));
            }
            if (newStep == null && priorityStepsHuman.size() > 0) {
                    newStep = priorityStepsHuman.get(rand.nextInt(priorityStepsHuman.size()));
            }
            if (newStep == null) {
                newStep = getRandomStep();
            }
        } else {
            newStep = getRandomStep();
        }
        row = newStep.getRow();
        column = newStep.getColumn();
        System.out.printf("Компьютер походил в строку %d, столбец %d\n", (row + 1), (column + 1));
        getMap()[row][column] = DOT_O;
        fixStep(row, column);
        printMap(getMap(), getStep());
    }

    public static Step getRandomStep() {
        int row, column;
        do {
            row = rand.nextInt(mapSize);
            column = rand.nextInt(mapSize);
        } while (!isCellValid(row, column, getMap()));
        return new Step(row, column);
    }

    public static void humanTurn() {
        int row, column;
        do {
            row = Helper.getIntInRange("Введи строку: ", 1, getMapSize()) - 1;
            column = Helper.getIntInRange("Введи столбец: ", 1, getMapSize()) - 1;
        } while (!isCellValid(row, column, getMap()));
        getMap()[row][column] = DOT_X;
        fixStep(row, column);
        printMap(getMap(), getStep());
    }

    public static void fixStep(int row, int column) {
        if (isFirstPlayer()) {
            getHumanSteps().add(new Step(row, column));
        } else {
            getAiSteps().add(new Step(row, column));
        }
    }

    public static boolean isCellValid(int row, int column, char[][] map) {
        if (row < 0 || row >= map.length || column < 0 || column >= map.length) {
            return false;
        }
        return map[row][column] == DOT_EMPTY;
    }

    public static char[][] initMap(int size, char symbol) {
        char[][] map = new char[size][size];
        for (int row = 0; row < size; row++) {
            for (int column = 0; column < size; column++) {
                map[row][column] = symbol;
            }
        }
        return map;
    }

    public static void printMap(char[][] map, int step) {
        //new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor(); // очистка экрана в консоли Windows при запуске не из IDE
        if (step > 0) {
            System.out.printf("Ход %d из %d возможных\n", step, (map.length * map.length));
        }
        for (int i = 0; i <= map.length; i++) {
            System.out.print(i + " ");
        }
        System.out.println();
        for (int row = 0; row < map.length; row++) {
            System.out.print((row + 1) + " ");
            for (int column = 0; column < map.length; column++) {
                System.out.print(map[row][column] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
}
