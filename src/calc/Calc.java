package calc;

import helper.Helper;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import java.awt.*;

public class Calc extends JFrame {
    private JTextField firstValue = new JTextField();
    private JTextField secondValue = new JTextField();
    private JTextField resultValue = new JTextField();
    private JTextArea textArea;
    private JScrollPane scroll;
    private String lastOperation;

    public Calc() throws HeadlessException {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Закрытие приложения по кнопке X
        setBounds(0, 0, 500, 200);           // Размер формы
        setLocationRelativeTo(null);                             // Позиция формы по центру экрана
        setTitle("Калькулятор");
        setLayout(new BorderLayout());

        Font font = new Font("Arial", Font.PLAIN, 14);

        JPanel calcPanelWithHistory = new JPanel(new GridLayout(1,2));
        JPanel calcPanel = new JPanel(new GridLayout(5,1));
        calcPanelWithHistory.add(calcPanel);

        firstValue.setFont(font);
        calcPanel.add(firstValue);

        secondValue.setFont(font);
        calcPanel.add(secondValue);

        JPanel panelOperations = new JPanel(new GridLayout(1,4));
        JButton additionButton = new JButton("+");
        additionButton.setFont(font);
        additionButton.addActionListener(actionEvent -> additionOperation(getFirstValue(), getSecondValue()));
        panelOperations.add(additionButton);

        JButton subtractionButton = new JButton("-");
        subtractionButton.setFont(font);
        subtractionButton.addActionListener(actionEvent -> subtractionOperation(getFirstValue(), getSecondValue()));
        panelOperations.add(subtractionButton);

        JButton multiplicationButton = new JButton("*");
        multiplicationButton.setFont(font);
        multiplicationButton.addActionListener(actionEvent -> multiplicationOperation(getFirstValue(), getSecondValue()));
        panelOperations.add(multiplicationButton);

        JButton divisionButton = new JButton("/");
        divisionButton.setFont(font);
        divisionButton.addActionListener(actionEvent -> divisionOperation(getFirstValue(), getSecondValue()));
        panelOperations.add(divisionButton);

        calcPanel.add(panelOperations);

        JPanel panelOperations2 = new JPanel(new GridLayout(1,4));
        JButton specButton1 = new JButton("1/x");
        specButton1.setFont(font);
        specButton1.addActionListener(actionEvent -> specOperation1(getFirstValue()));
        panelOperations2.add(specButton1);

        JButton specButton2 = new JButton("x^2");
        specButton2.setFont(font);
        specButton2.addActionListener(actionEvent -> specOperation2(getFirstValue()));
        panelOperations2.add(specButton2);

        JButton specButton3 = new JButton("sqrt(x)");
        specButton3.setFont(font);
        specButton3.addActionListener(actionEvent -> specOperation3(getFirstValue()));
        panelOperations2.add(specButton3);

        JButton specButton4 = new JButton("x!");
        specButton4.setFont(font);
        specButton4.addActionListener(actionEvent -> specOperation4(getFirstValue()));
        panelOperations2.add(specButton4);

        calcPanel.add(panelOperations2);

        resultValue.setFont(font);
        resultValue.setEditable(false);
        resultValue.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent documentEvent) {
                textArea.append(
                        String.format("%s %s %s = %s\n", firstValue.getText(), lastOperation, secondValue.getText(), resultValue.getText()));
            }

            @Override
            public void removeUpdate(DocumentEvent documentEvent) {

            }

            @Override
            public void changedUpdate(DocumentEvent documentEvent) {

            }
        });
        calcPanel.add(resultValue);

        textArea = new JTextArea(5, 10);
        scroll = new JScrollPane(textArea);
        textArea.setFont(font);
        calcPanelWithHistory.add(scroll);

        add(calcPanelWithHistory);

        setVisible(true);
    }

    private void additionOperation(Double firstValue, Double secondValue) {
        if (firstValue != null && secondValue != null) {
            lastOperation = "+";
            resultValue.setText(String.format("%.4f", firstValue + secondValue));
        }
    }
    
    private void subtractionOperation(Double firstValue, Double secondValue) {
        if (firstValue != null && secondValue != null) {
            lastOperation = "-";
            resultValue.setText(String.format("%.4f", firstValue - secondValue));
        }
    }

    private void multiplicationOperation(Double firstValue, Double secondValue) {
        if (firstValue != null && secondValue != null) {
            lastOperation = "*";
            resultValue.setText(String.format("%.4f", firstValue * secondValue));
        }
    }

    private void divisionOperation(Double firstValue, Double secondValue) {
        if (firstValue != null && secondValue != null) {
            lastOperation = "/";
            resultValue.setText(String.format("%.4f", firstValue / secondValue));
        }
    }

    private void specOperation1(Double firstValue) {
        if (firstValue != null) {
            lastOperation = "=> 1/x";
            secondValue.setText("");
            resultValue.setText(String.format("%.4f", 1 / firstValue));
        }
    }

    private void specOperation2(Double firstValue) {
        if (firstValue != null) {
            lastOperation = "^2";
            secondValue.setText("");
            resultValue.setText(String.format("%.4f", Math.pow(firstValue, 2)));
        }
    }

    private void specOperation3(Double firstValue) {
        if (firstValue != null) {
            lastOperation = "^0.5";
            secondValue.setText("");
            resultValue.setText(String.format("%.4f", Math.sqrt(firstValue)));
        }
    }

    private void specOperation4(Double firstValue) {
        if (firstValue != null) {
            lastOperation = "!";
            secondValue.setText("");
            if (firstValue < 0 || firstValue > 35) {
                resultValue.setText("Недопустимое значение");
            } else {
                resultValue.setText(String.format("%d", Helper.factorial(firstValue.intValue())));
            }
        }
    }

    private Double getFirstValue() {
        return firstValue.getText().equals("") ? null : preparationValue(firstValue.getText());
    }

    private Double getSecondValue() {
        return secondValue.getText().equals("") ? null : preparationValue(secondValue.getText());
    }

    private Double preparationValue(String value) {
        return Double.parseDouble(value.replace(",", "."));
    }
}
