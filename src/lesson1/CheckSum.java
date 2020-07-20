package lesson1;

import java.util.Scanner;

// 3. Написать метод, принимающий на вход два числа, и проверяющий что их сумма лежит в пределах от 10 до 20(включительно),
// если да – вернуть true, в противном случае – false;

public class CheckSum {
    public static void runCheckSum() {
        Scanner in = new Scanner(System.in);
        System.out.print("Введи первое число: ");
        int first = in.nextInt();
        System.out.print("Введи второе число: ");
        int second = in.nextInt();
        String result;
        if (checkSum(first, second)) {
            result = "входит";
        } else {
            result = "не входит";
        }
        System.out.printf("Сумма чисел %d и %d %s в диапазон от 10 до 20", first, second, result);
    }

    public static boolean checkSum(int first, int second) {
        int sum = first + second;
        return sum >= 10 && sum <= 20;
    }
}
