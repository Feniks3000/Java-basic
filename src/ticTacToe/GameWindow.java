package ticTacToe;

import javax.swing.*;
import java.awt.*;

public class GameWindow extends JFrame {
    private static final int WINDOW_POS_X = 0;
    private static final int WINDOW_POS_Y = 0;
    private static final int WINDOW_HEIGHT = 550;
    private static final int WINDOW_WIDTH = 500;

    private final SettingsWindow settingsWindow;
    private final BattleField battleField;

    public GameWindow() throws HeadlessException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setBounds(WINDOW_POS_X, WINDOW_POS_Y, WINDOW_WIDTH, WINDOW_HEIGHT);
        setLocationRelativeTo(null);
        setTitle("Крестики-нолики XOXOXOXOX");
        settingsWindow = new SettingsWindow(this);
        battleField = new BattleField(this);

        add(battleField, BorderLayout.CENTER);

        JPanel menu = new JPanel(new GridLayout(1, 2));
        JButton buttonStart = new JButton("Играть");
        menu.add(buttonStart);
        buttonStart.addActionListener(e -> settingsWindow.setVisible(true));

        JButton buttonExit = new JButton("Выйти");
        menu.add(buttonExit);
        buttonExit.addActionListener(e -> System.exit(0));

        add(menu, BorderLayout.SOUTH);

        setVisible(true);
    }

    public BattleField getBattleField() {
        return battleField;
    }

    public void startNewGame(int mapSize, int winningLength, int iqAiLevel){
        battleField.startNewGame(mapSize, winningLength, iqAiLevel);
    }
}
