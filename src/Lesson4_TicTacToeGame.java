import ticTacToe.TicTacToe;
import helper.Helper;

import java.io.IOException;

public class Lesson4_TicTacToeGame extends TicTacToe {
    public static void main(String[] args) throws IOException, InterruptedException {
        setMapSize(Helper.getIntInRange("Введи размерность игрового поля: ", 3, 10));
        setDotsToWin(Helper.getIntInRange("Введи размер победной серии: ", 3, getMapSize()));
        setAiIqLevel(Helper.getIntInRange("Введи уровень IQ компьютера (от 0 до 10): ", 0, 10));

        setMap(initMap(getMapSize(), DOT_EMPTY));
        printMap(getMap(), getStep());
        do {
            addStep();
            if (isFirstPlayer()) {
                humanTurn();
            } else {
                aiTurn();
            }
        } while (checkWin());
        System.out.println("Игра закончена");
    }
}
