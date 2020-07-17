package lesson1;

import java.util.Locale;
import java.util.Scanner;

// 2. Написать метод вычисляющий выражение a * (b + (c / d)) и
// возвращающий результат,где a, b, c, d – входные параметры этого метода;

public class Calculate {
    public static Scanner in = new Scanner(System.in);

    public static void runCalculate() throws Exception {
        in.useLocale(Locale.US);
        float a = getFloat("Введи a: ");
        float b = getFloat("Введи b: ");
        float c = getFloat("Введи c: ");
        float d = getFloat("Введи d: ");

        calculate(a, b, c, d);
    }

    public static void calculate(float a, float b, float c, float d) {
        if (Math.round(d) != 0) {
            System.out.printf("Пункт 2. %.2f * (%.2f + (%.2f / %.2f)) = %f", a, b, c, d, (a * (b + (c / d))));
        } else {
            System.out.printf("Пункт 2. %.2f * (%.2f+ (%.2f / %.2f)) = %s", a, b, c, d, "Не могу поделить на 0");
        }
    }

    public static float getFloat(String message) throws Exception {
        System.out.print(message);
        if (in.hasNextFloat()) {
            return in.nextFloat();
        } else {
            throw new Exception("Введено некорректное числовое значение");
        }
    }
}
