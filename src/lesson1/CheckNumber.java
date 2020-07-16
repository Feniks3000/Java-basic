package lesson1;

import java.util.Scanner;

// 5. Написать метод, которому в качестве параметра передается целое число,
// метод должен напечатать в консоль положительное ли число передали, или отрицательное;
// Замечание: ноль считаем положительным числом.

// 6. Написать метод, которому в качестве параметра передается целое число,
// метод должен вернуть true, если число отрицательное;

public class CheckNumber {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Введи число: ");
        int val = in.nextInt();
        String result;
        if (numberIsNegative(val)) {
            result = "отрицательное";
        } else {
            result = "положительное";
        }
        System.out.printf("Число %d %s", val, result);
    }

    public static boolean numberIsNegative(int val) {
        return val < 0;
    }
}
