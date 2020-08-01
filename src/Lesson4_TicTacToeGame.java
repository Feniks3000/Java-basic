import helper.Helper;
import ticTacToe.TicTacToe;

public class Lesson4_TicTacToeGame extends TicTacToe {
    public static void main(String[] args) {
        setMapSize(Helper.getIntInRange("Введи размерность игрового поля: ", 3, 10));
        setDotsToWin(Helper.getIntInRange("Введи размер победной серии: ", 3, getMapSize()));
        setAiIqLevel(Helper.getIntInRange("Введи уровень IQ компьютера (от 0 до 10): ", 0, 10));

        setMap(initMap(getMapSize(), DOT_EMPTY));
        printMap(getMap(), getStep());
        int result;
        do {
            addStep();
            if (isFirstPlayer()) {
                humanTurn();
            } else {
                aiTurn();
            }
            result = checkWin(getHumanSteps(), getAiSteps(), getDotsToWin());
        } while (result == GAME_IN_PROCESS);
        System.out.println("Игра закончена");

        switch (result) {
            case HUMAN_WIN:
                System.out.println("Победил человеческий разум");
                break;
            case AI_WIN:
                System.out.println("Победил искусственный интеллект");
                break;
            case DEAD_HEAT:
                System.out.println("Победила дружба. Ничья");
        }
    }
}
