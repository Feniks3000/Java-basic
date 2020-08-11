package ticTacToe;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class BattleField extends JPanel {
    private GameWindow gameWindow;
    private int mode;
    private int iqAiLevel;
    private int mapSize;
    private int winningLength;

    private boolean isInit;

    private int cellWidth;
    private int cellHeight;


    public BattleField(GameWindow gameWindow) {
        this.gameWindow = gameWindow;
        setBackground(Color.LIGHT_GRAY);
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

        TicTacToe.getMap()[row][column] = TicTacToe.DOT_X;
        //TicTacToe.fixStep(column - 1, row - 1);

//        if(!Logic.isFinished){
//            Logic.humanTurn(column, row);
//        }

        repaint();
    }


    public void startNewGame(int mode, int mapSize, int winningLength, int iqAiLevel) {
        this.mode = mode;
        this.iqAiLevel = iqAiLevel;
        this.mapSize = mapSize;
        this.winningLength = winningLength;
        TicTacToe.setMapSize(mapSize);
        TicTacToe.setDotsToWin(winningLength);
        TicTacToe.setAiIqLevel(iqAiLevel);
        TicTacToe.setMap(TicTacToe.initMap(TicTacToe.getMapSize(), TicTacToe.DOT_EMPTY));

        isInit = true;
        repaint();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        if (!isInit) {
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
    }

    private void drawX(Graphics g, int x, int y) {
        ((Graphics2D) g).setStroke(new BasicStroke(5));
        g.setColor(Color.RED);
        g.drawLine(cellWidth * x, cellHeight * y, cellWidth * (x + 1), cellHeight * (y + 1));
    }

    private void drawO(Graphics g, int x, int y) {
        g.setColor(Color.BLUE);
        g.drawLine(cellWidth * x, cellHeight * y, cellWidth * (x + 1), cellHeight * (y + 1));
    }
}
