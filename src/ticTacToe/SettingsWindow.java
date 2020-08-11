package ticTacToe;

import javax.swing.*;
import java.awt.*;

public class SettingsWindow extends JFrame {

    private static final int MIN_FIELD_SIZE = 3;
    private static final int MAX_FIELD_SIZE = 10;

    private final JSlider jsIqAiLevel;
    private final JSlider jsMapSize;
    private final JSlider jsWinningLength;

    public SettingsWindow(GameWindow gameWindow) throws HeadlessException {
        setBounds(0, 0, 400, 300);
        setLocationRelativeTo(null);
        setTitle("Настройки игры");

        setLayout(new GridLayout(7, 1));

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
            int iqAiLevel = jsIqAiLevel.getValue();
            int mapSize = jsMapSize.getValue();
            int winningLength = jsWinningLength.getValue();

            gameWindow.startNewGame(mapSize, winningLength, iqAiLevel);

            setVisible(false);
        });

        setVisible(false);
    }
}
