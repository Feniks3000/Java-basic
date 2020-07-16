import lesson1.*;

import java.util.Scanner;

public class Lesson1 {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Программы для запуска:\n" +
                "1. Вычисление по формуле a * (b + (c / d)) \n" +
                "2. Проверка суммы чисел на принадлежность диапазону\n" +
                "3. Проверка числа на отрицательность\n" +
                "4. Приветствие\n" +
                "5. Проверка года на високосность\n\n" +
                "Введи номер программы для запуска: ");
        int program = in.nextInt();

        switch (program) {
            case 1:
                Calculate.runCalculate();
                break;
            case 2:
                CheckSum.runCheckSum();
                break;
            case 3:
                CheckNumber.runCheckNumber();
                break;
            case 4:
                Hello.runHello();
                break;
            case 5:
                CheckYear.runCheckYear();
                break;
            default:
                System.out.println("К сожалению, программу для вас еще не разработали");
        }
    }
}
