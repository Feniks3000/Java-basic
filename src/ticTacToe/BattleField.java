package ticTacToe;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class BattleField extends JPanel {
    private GameWindow gameWindow;
    BufferedImage image = null;

    private int mapSize;

    private boolean isInit;

    private int cellWidth;
    private int cellHeight;


    public BattleField(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setBackground(Color.LIGHT_GRAY);
        try {
            image = ImageIO.read(BattleField.class.getResource("/image.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }

        isInit = false;

        addMouseListener(new MouseAdapter() {
            @Override
            public void mouseReleased(MouseEvent e) {
                if (isInit) {
                    clickBattleField(e);
                }
            }
        });
    }

    private void clickBattleField(MouseEvent e) {
        int row = e.getY() / cellHeight;
        int column = e.getX() / cellWidth;

        if(!TicTacToe.isFinished()){
            TicTacToe.humanTurn(row, column);
        }

        repaint();
    }


    public void startNewGame(int mapSize, int winningLength, int iqAiLevel) {
        this.mapSize = mapSize;
        TicTacToe.initGame(mapSize, winningLength, iqAiLevel, gameWindow);
        isInit = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!isInit) {
            g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
            return;
        }

        int panelWidth = getWidth();
        int panelHeight = getHeight();

        cellHeight = panelHeight / mapSize;
        cellWidth = panelWidth / mapSize;

        g.setColor(Color.BLACK);

        for (int i = 0; i < mapSize; i++) {
            int y = i * cellHeight;
            g.drawLine(0, y, panelWidth, y);
        }

        for (int i = 0; i < mapSize; i++) {
            int x = i * cellWidth;
            g.drawLine(x, 0, x, panelHeight);
        }

        for (int row = 0; row < mapSize; row++) {
            for (int column = 0; column < mapSize; column++) {
                if (TicTacToe.getMap()[row][column] == TicTacToe.DOT_X) {
                    drawX(g, column, row);
                }
                if (TicTacToe.getMap()[row][column] == TicTacToe.DOT_O) {
                    drawO(g, column, row);
                }
            }
        }

        if (TicTacToe.isFinished()) {
            drawWinner(g, TicTacToe.getWinner());
        }
    }

    private void drawX(Graphics g, int x, int y) {
        ((Graphics2D) g).setStroke(new BasicStroke(5));
        g.setColor(Color.RED);
        g.drawLine(cellWidth * x, cellHeight * y, cellWidth * (x + 1), cellHeight * (y + 1));
        g.drawLine(cellWidth * (x + 1), cellHeight * y, cellWidth * x, cellHeight * (y + 1));
    }

    private void drawO(Graphics g, int x, int y) {
        ((Graphics2D) g).setStroke(new BasicStroke(5));
        g.setColor(Color.BLUE);
        g.drawOval(cellWidth * x, cellHeight * y, cellWidth, cellHeight);
    }

    private void drawWinner(Graphics g, int winner) {
        g.setColor(Color.BLACK);
        g.setFont(new Font("Arial", Font.BOLD, gameWindow.getWidth() / 12));
        switch (winner) {
            case TicTacToe.HUMAN_WIN:
                g.drawString("Победил человек!", 30, gameWindow.getHeight() / 2);
                break;
            case TicTacToe.AI_WIN:
                g.drawString("Победил компьютер!", 30, gameWindow.getHeight() / 2);
                break;
            case TicTacToe.DEAD_HEAT:
                g.drawString("Победила дружба!", 30, gameWindow.getHeight() / 2);
        }
    }
}
