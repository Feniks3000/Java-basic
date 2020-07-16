package lesson1;

import java.util.Scanner;

// 2. Написать метод вычисляющий выражение a * (b + (c / d)) и
// возвращающий результат,где a, b, c, d – входные параметры этого метода;

public class Calculate {
    public static void runCalculate() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введи a: ");
        int a = in.nextInt();
        System.out.print("Введи b: ");
        int b = in.nextInt();
        System.out.print("Введи c: ");
        int c = in.nextInt();
        System.out.print("Введи d: ");
        int d = in.nextInt();

        calculate(a, b, c, d);
    }

    public static void calculate(int a, int b, int c, int d) {
        if (d != 0) {
            System.out.printf("Пункт 2. %d * (%d + (%d / %d)) = %f", a, b, c, d, (double) a * (b + (c / d)));
        } else {
            System.out.printf("Пункт 2. %d * (%d + (%d / %d)) = %s", a, b, c, d, "Не могу поделить на 0");
        }
    }
}
