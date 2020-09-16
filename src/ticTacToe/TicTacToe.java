package ticTacToe;

import java.util.*;
import java.util.stream.Collectors;

import helper.Helper;

import javax.swing.*;

public class TicTacToe {
    private static char[][] map;
    private static int mapSize = 3;
    private static int dotsToWin = 3;
    private static int step = 0;
    private static int aiIqLevel = 3;
    private static boolean isFinished;
    private static List<Step> humanSteps = new ArrayList<>();
    private static List<Step> aiSteps = new ArrayList<>();
    public static final char DOT_EMPTY = '•';
    public static final char DOT_X = 'X';
    public static final char DOT_O = 'O';
    public static final int GAME_IN_PROCESS = 0;
    public static final int HUMAN_WIN = 1;
    public static final int AI_WIN = 2;
    public static final int DEAD_HEAT = 3;
    private static int winner;

    private static GameWindow gameWindow;

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

    public static boolean isFinished() {
        return isFinished;
    }

    public static void setIsFinished(boolean isFinished) {
        TicTacToe.isFinished = isFinished;
    }

    public static int getWinner() {
        return winner;
    }

    public static int checkWin(List<Step> humanSteps, List<Step> aiSteps, int dotsToWin) {
        for (int i = 0; i < getMapSize(); i++) {
            if (checkWinInSeries(getStepInRow(i, humanSteps), dotsToWin)
                    || checkWinInSeries(getStepInColumn(i, humanSteps), dotsToWin)) {
                return HUMAN_WIN;
            }
            if (checkWinInSeries(getStepInRow(i, aiSteps), dotsToWin)
                    || checkWinInSeries(getStepInColumn(i, aiSteps), dotsToWin)) {
                return AI_WIN;
            }
        }
        for (int i = 0; i < getMapSize() * 2 - 1; i++) {
            if (checkWinInSeries(getStepInDiagonal1(i, humanSteps), dotsToWin) ||
                    checkWinInSeries(getStepInDiagonal2(i, humanSteps), dotsToWin)) {
                return HUMAN_WIN;
            }
            if (checkWinInSeries(getStepInDiagonal1(i, aiSteps), dotsToWin) ||
                    checkWinInSeries(getStepInDiagonal2(i, aiSteps), dotsToWin)) {
                return AI_WIN;
            }
        }
        if (isMapFull()) {
            return DEAD_HEAT;
        }
        return GAME_IN_PROCESS;
    }

    public static boolean checkWinInSeries(List<Step> seriesList, int dotsToWin) {
        if (seriesList.size() >= dotsToWin) {
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
                if (stepsInSeries == dotsToWin) {
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
        List<Step> tempHumanSteps = new ArrayList<>(getHumanSteps());
        List<Step> tempAiSteps = new ArrayList<>(getAiSteps());

        Step newStep = findWinAiStep(tempHumanSteps, tempAiSteps);   // Подбор победного хода компьютера

        if (newStep == null) {
            newStep = findWinHumanStep(tempHumanSteps, tempAiSteps, getDotsToWin());    // Предотвращение победной серии человека
        }

        if (newStep == null) {  // Расширенная логика компьютера по поиску победных серий, зависит от IQ компьютера
            if (rand.nextInt(10) <= getAiIqLevel()) {
                newStep = findWinHumanStep(tempHumanSteps, tempAiSteps, getDotsToWin() - 1); // Предотвращение начала победной серии человека
            }
        }

        if (newStep == null) {
                newStep = getRandomStep();
        }

        System.out.printf("Компьютер походил в строку %d, столбец %d\n", (newStep.getRow() + 1), (newStep.getColumn() + 1));
        getMap()[newStep.getRow()][newStep.getColumn()] = DOT_O;
        fixStep(newStep.getRow(), newStep.getColumn());
        printMap(getMap(), getStep());
    }

    private static Step findWinHumanStep(List<Step> tempHumanSteps, List<Step> tempAiSteps, int dotsToWin) {
        for (int row = 0; row < getMapSize(); row++) {
            for (int column = 0; column < getMapSize(); column++) {
                Step newStep = new Step(row, column);
                if (!tempHumanSteps.contains(newStep) && !tempAiSteps.contains(newStep)) {
                    tempHumanSteps.add(newStep);
                    if (checkWin(tempHumanSteps, tempAiSteps, dotsToWin) == HUMAN_WIN) {
                        return newStep;
                    } else {
                        tempHumanSteps.remove(newStep);
                    }
                }
            }
        }
        return null;
    }

    private static Step findWinAiStep(List<Step> tempHumanSteps, List<Step> tempAiSteps) {
        for (int row = 0; row < getMapSize(); row++) {
            for (int column = 0; column < getMapSize(); column++) {
                Step newStep = new Step(row, column);
                if (!tempAiSteps.contains(newStep) && !tempHumanSteps.contains(newStep)) {
                    tempAiSteps.add(newStep);
                    if (checkWin(tempHumanSteps, tempAiSteps, getDotsToWin()) == AI_WIN) {
                        return newStep;
                    } else {
                        tempAiSteps.remove(newStep);
                    }
                }
            }
        }
        return null;
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

    public static void humanTurn(int row, int column) {
        if (isCellValid(row, column, getMap())) {
            addStep();
            getMap()[row][column] = DOT_X;
            fixStep(row, column);
            printMap(getMap(), getStep());
            oneRound();
        }
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

    public static void oneRound() {
        setIsFinished(true);
        winner = checkWin(getHumanSteps(), getAiSteps(), getDotsToWin());
        if (winner != GAME_IN_PROCESS) {
            return;
        }
        addStep();
        aiTurn();
        winner = checkWin(getHumanSteps(), getAiSteps(), getDotsToWin());
        if (winner != GAME_IN_PROCESS) {
            return;
        }
        setIsFinished(false);
    }

    public static void initGame(int mapSize, int winningLength, int iqAiLevel, GameWindow window) {
        setStep(0);
        setHumanSteps(new ArrayList<>());
        setAiSteps(new ArrayList<>());
        setMapSize(mapSize);
        setDotsToWin(winningLength);
        setAiIqLevel(iqAiLevel);
        setMap(initMap(mapSize, DOT_EMPTY));
        setIsFinished(false);
        gameWindow = window;
    }
}
