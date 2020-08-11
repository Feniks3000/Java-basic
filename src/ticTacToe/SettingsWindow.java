package ticTacToe;

import javax.swing.*;
import java.awt.*;

public class SettingsWindow extends JFrame {
    private GameWindow gameWindow;

    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;

    static final int GAME_MODE_H_VS_A = 0;
    static final int GAME_MODE_H_VS_H = 1;

    private ButtonGroup bgGameMode;
    private JRadioButton jrbHumanVsAi;
    private JRadioButton jrbHumanVsHuman;

    private JSlider jsIqAiLevel;
    private JSlider jsMapSize;
    private JSlider jsWinningLength;

    public SettingsWindow(GameWindow gameWindow) throws HeadlessException {
        setBounds(0, 0, 400, 400);
        setLocationRelativeTo(null);
        setTitle("Настройки игры");

        setLayout(new GridLayout(9, 1));

        add(new JLabel("Режим игры:"));
        JPanel gameMode = new JPanel(new GridLayout(1, 2));
        jrbHumanVsAi = new JRadioButton("Человек - Компьютер", true);
        jrbHumanVsHuman = new JRadioButton("Человек - Человек");
        gameMode.add(jrbHumanVsAi);
        gameMode.add(jrbHumanVsHuman);
        add(gameMode);
        bgGameMode = new ButtonGroup();
        bgGameMode.add(jrbHumanVsAi);
        bgGameMode.add(jrbHumanVsHuman);

        add(new JLabel("Выберите IQ компьютера:"));
        jsIqAiLevel = new JSlider(0, 10, 10);
        jsIqAiLevel.setMajorTickSpacing(1);
        jsIqAiLevel.setPaintTicks(true);
        jsIqAiLevel.setPaintLabels(true);
        add(jsIqAiLevel);

        add(new JLabel("Выберите размер поля:"));
        jsMapSize = new JSlider(MIN_FIELD_SIZE, MAX_FIELD_SIZE, MIN_FIELD_SIZE);
        jsMapSize.setMajorTickSpacing(1);
        jsMapSize.setPaintTicks(true);
        jsMapSize.setPaintLabels(true);
        add(jsMapSize);

        add(new JLabel("Выберите размер победной серии:"));
        jsWinningLength = new JSlider(MIN_FIELD_SIZE, MIN_FIELD_SIZE, MIN_FIELD_SIZE);
        jsWinningLength.setMajorTickSpacing(1);
        jsWinningLength.setPaintTicks(true);
        jsWinningLength.setPaintLabels(true);
        add(jsWinningLength);

        jsMapSize.addChangeListener(e -> {
            jsWinningLength.setMaximum(jsMapSize.getValue());
        });

        JButton buttonStartGame = new JButton("Начать игру");
        add(buttonStartGame);

        buttonStartGame.addActionListener(e -> {
            int mode;
            if (jrbHumanVsAi.isSelected()) {
                mode = GAME_MODE_H_VS_A;
            } else {
                mode = GAME_MODE_H_VS_H;
            }

            int iqAiLevel = jsIqAiLevel.getValue();
            int mapSize = jsMapSize.getValue();
            int winningLength = jsWinningLength.getValue();

//            Logic.SIZE = mapSize;
//            Logic.DOTS_TO_WIN = winningLength;
//            Logic.initMap();
//            Logic.isFinished = false;

            gameWindow.startNewGame(mode, mapSize, winningLength, iqAiLevel);

            setVisible(false);
        });

        setVisible(false);
    }
}
