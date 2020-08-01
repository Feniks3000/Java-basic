package helper;

import java.util.Scanner;

public class Helper {
    private static final Scanner in = new Scanner(System.in);

    public static int getRandomIntInRange(int from, int to) {
        return (int) (from + Math.random() * (to - from));
    }

    public static int getIntInRange(String message, int from, int to) {
        Integer result = null;
        do {
            System.out.printf(message, from, to);
            String str_number = in.nextLine();
            try {
                result = Integer.parseInt(str_number);
                if (!numberInRange(result, from, to)) {
                    System.out.printf("Введено число не из диапазона от %d до %d. Попробуй еще раз \n", from, to);
                }
            } catch (Exception ex) {
                System.out.println("Ты ввел что-то, но это явно не число. Попробуй еще раз");
            }
        } while (result == null || !numberInRange(result, from, to));
        return result;
    }

    private static boolean numberInRange(int number, int from, int to) {
        return number >= from && number <= to;
    }
}
