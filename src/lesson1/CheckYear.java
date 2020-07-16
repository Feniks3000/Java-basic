package lesson1;

import java.util.Scanner;

// 8. * Написать метод, который определяет является ли год високосным, и выводит сообщение в консоль.
// Каждый 4-й год является високосным, кроме каждого 100-го, при этом каждый 400-й – високосный.

public class CheckYear {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введи год: ");
        int year = in.nextInt();
        String result;
        if (yearIsLeap(year)) {
            result = "високосным";
        } else {
            result = "невисокосным";
        }
        System.out.printf("Год %d является %s", year, result);
    }

    public static boolean yearIsLeap(int year) {
        if (year % 4 == 0) {
            return year % 100 != 0 || year % 400 == 0;
        } else {
            return false;
        }
    }
}
