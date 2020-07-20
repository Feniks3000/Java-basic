import java.util.Arrays;
import java.util.Scanner;

/*
1. Задать целочисленный массив, состоящий из элементов 0 и 1. Например: [ 1, 1, 0, 0, 1, 0, 1, 1, 0, 0 ].
    С помощью цикла и условия заменить 0 на 1, 1 на 0;

2. Задать пустой целочисленный массив размером 8. С помощью цикла заполнить его значениями 0 3 6 9 12 15 18 21;

3. Задать массив [ 1, 5, 3, 2, 11, 4, 5, 2, 4, 8, 9, 1 ] пройти по нему циклом, и числа меньшие 6 умножить на 2;

4. Создать квадратный двумерный целочисленный массив (количество строк и столбцов одинаковое),
    и с помощью цикла(-ов) заполнить его диагональные элементы единицами;

5. ** Задать одномерный массив и найти в нем минимальный и максимальный элементы (без помощи интернета);

6. ** Написать метод, в который передается не пустой одномерный целочисленный массив,
    метод должен вернуть true, если в массиве есть место, в котором сумма левой и правой части массива равны.
    Примеры: checkBalance([2, 2, 2, 1, 2, 2, || 10, 1]) → true, checkBalance([1, 1, 1, || 2, 1]) → true,
    граница показана символами ||, эти символы в массив не входят.

7. **** Написать метод, которому на вход подается одномерный массив и
    число n (может быть положительным, или отрицательным), при этом метод должен сместить все элементымассива на n позиций.
    Для усложнения задачи нельзя пользоваться вспомогательными массивами.
 */

public class Lesson2 {

    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) throws Exception {
        Scanner in = new Scanner(System.in);
        System.out.print("Программы для запуска:\n" +
                "1. Заполнить массив 0 и 1, инвертировать значения \n" +
                "2. Заполнить массив радом чисел, начиная с 0 с шагом 3\n" +
                "3. Заполнить массив числами, увеличить числа меньше 6 в два раза\n" +
                "4. Заполнить диагонали квадратного двумерного массива единицами\n" +
                "5. Найти min и max элементы одномерного массива\n" +
                "6. Проерить, есть ли в массиве точка баланса левой и правой половин\n" +
                "7. Сдвинуть значения в массиве на N позиций\n\n" +
                "Введи номер программы для запуска: ");
        int program = in.nextInt();

        switch (program) {
            case 1:
                runGenerateAndInvertArray();
                break;
            case 2:
                runFillArrayWithStep3();
                break;
            case 3:
                runIncreaseInHalfSomeNumbers();
                break;
            case 4:
                runGenerateSquareArrayAndFill();
                break;
            case 5:
                runFindMinAndMaxElements();
                break;
            case 6:
                runFindBalance();
                break;
            case 7:
                runOffsetElements();
                break;
            default:
                System.out.println("К сожалению, программу для вас еще не разработали");
        }
    }

    public static void runGenerateAndInvertArray() throws Exception {
        System.out.print("Введи размер генерируемого массива: ");
        int arraySize = in.nextInt();
        int[] array = getArray(arraySize, new int[]{0, 1});
        printArrayWithMessage("Сгенерирован массив: ", array);
        printArrayWithMessage("Значения в массиве инвертированы: ", invertArray(array));
    }

    public static void runFillArrayWithStep3() throws Exception {
        System.out.print("Введи размер генерируемого массива: ");
        int arraySize = in.nextInt();
        System.out.print("Введи первое значение ряда: ");
        int from = in.nextInt();
        System.out.print("Введи шаг генерациия ряда: ");
        int step = in.nextInt();
        int[] array = getArray(arraySize, from, step);
        printArrayWithMessage(
                String.format("Сгенерирован массив с рядом чисел от %d с шагом %s: ", from, step), array);
    }

    public static void runIncreaseInHalfSomeNumbers() throws Exception {
        System.out.print("Введи размер генерируемого массива: ");
        int arraySize = in.nextInt();
        System.out.print("Введи число, значения меньше которого будут увеличены вдвое: ");
        int limit = in.nextInt();
        int[] array = getRandomArray(arraySize, limit * 3);
        printArrayWithMessage("Сгенерирован массив: ", array);
        printArrayWithMessage(
                String.format("Увеличены вдвое элементы со значениями меньше %d: ", limit),
                increaseInHalfNumbersLessLimit(array, limit));
    }

    public static void runGenerateSquareArrayAndFill() throws Exception {
        System.out.print("Введи размерность генерируемого двумерного массива: ");
        int arraySize = in.nextInt();
        int[][] array = getSquareArray(arraySize);
        printArrayWithMessage("Сгенерирован массив: ", array);
        printArrayWithMessage("Диагонали заполнены единицами: ", fillDiagonals(array, 1));
    }

    public static void runFindMinAndMaxElements() throws Exception {
        System.out.print("Введи размерность генерируемого массива: ");
        int arraySize = in.nextInt();
        int[] array = getRandomArray(arraySize, arraySize * 3);
        printArrayWithMessage("Сгенерирован массив: ", array);

        int minElement = findMinElement(array);
        int maxElement = findMaxElement(array);
        System.out.printf("Min элемент массива %dй со значением %d\n", minElement + 1, array[minElement]);
        System.out.printf("Max элемент массива %dй со значением %d", maxElement + 1, array[maxElement]);
    }

    public static void runFindBalance() throws Exception {
        int[] array = new int[]{2, 2, 2, 1, 2, 2, 10, 1};
        printArrayWithMessage("Массив для поиска баланса: ", array);
        int balance = checkBalance(array);
        if (balance == 0) {
            System.out.print("Массив несбалансирован, в нем нет позиции равенства сумм левой и правой частей");
        } else {
            System.out.printf("Массив сбалансирован, позиция баланса между %d и %d элементами, с суммой частей массива %d и %d",
                    balance - 1, balance, sumFirstElements(array, balance), sumLastElements(array, array.length - balance));
        }
    }

    public static void runOffsetElements() throws Exception {
        System.out.print("Введи размерность генерируемого массива: ");
        int arraySize = in.nextInt();
        System.out.print("Введи шаг смещения элементов в массиве: ");
        int offset = in.nextInt();

        int[] array = getRandomArray(arraySize, arraySize * 3);
        printArrayWithMessage("Сгенерирован массив: ", array);
        printArrayWithMessage(String.format("Смещенный на %d элементов массив: ", offset), offsetElements(array, offset));
    }

    public static int[] getArray(int count, int[] numbers) throws Exception {
        if (count < 1 || numbers.length == 0) {
            throw new Exception("Неверно задан размер массива или не задан массив элементов для заполнения");
        }
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = getRendomElement(numbers);
        }
        return result;
    }

    public static int[] getArray(int count, int from, int step) throws Exception {
        if (count < 1) {
            throw new Exception("Неверно задан размер массива");
        }
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = from + (i * step);
        }
        return result;
    }

    public static int[] getRandomArray(int count, int uBound) throws Exception {
        if (count < 1) {
            throw new Exception("Неверно задан размер массива");
        }
        int[] result = new int[count];
        for (int i = 0; i < count; i++) {
            result[i] = (int) (Math.random() * uBound);
        }
        return result;
    }

    public static int[][] getSquareArray(int count) throws Exception {
        if (count < 1) {
            throw new Exception("Неверно задан размер массива");
        }
        return new int[count][count];
    }

    public static int getRendomElement(int[] array) {
        int position = (int) (Math.random() * array.length);
        return array[position];
    }

    public static void printArray(int[] array) {
        System.out.println(Arrays.toString(array));
    }

    public static void printArray(int[][] array) {
        for (int[] ints : array) {
            for (int anInt : ints) {
                System.out.printf("%s\t", anInt);
            }
            System.out.print("\n");
        }
    }

    public static void printArrayWithMessage(String message, int[] array) {
        System.out.println(message);
        printArray(array);
    }

    public static void printArrayWithMessage(String message, int[][] array) {
        System.out.println(message);
        printArray(array);
    }

    public static int[] invertArray(int[] array) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] == 0) {
                array[i] = 1;
            } else {
                array[i] = 0;
            }
        }
        return array;
    }

    public static int[] increaseInHalfNumbersLessLimit(int[] array, int limit) {
        for (int i = 0; i < array.length; i++) {
            if (array[i] < limit) {
                array[i] *= 2;
            }
        }
        return array;
    }

    public static int[][] fillDiagonals(int[][] array, int value) {
        for (int i = 0; i < array.length; i++) {
            array[i][i] = value;
            array[array.length - 1 - i][i] = value;
        }
        return array;
    }

    public static int findMinElement(int[] array) {
        int minElement = 0;
        int minValue = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] < minValue) {
                minValue = array[i];
                minElement = i;
            }
        }
        return minElement;
    }

    public static int findMaxElement(int[] array) {
        int maxElement = 0;
        int maxValue = array[0];
        for (int i = 0; i < array.length; i++) {
            if (array[i] > maxValue) {
                maxValue = array[i];
                maxElement = i;
            }
        }
        return maxElement;
    }

    public static int checkBalance(int[] array) throws Exception {
        if (array.length < 2) {
            throw new Exception("Размер массива не позволяет искать баланс сумм левой и правой частей");
        }
        int balance = 0;
        for (int i = 1; i < array.length - 1; i++)
            if (sumFirstElements(array, i) == sumLastElements(array, array.length - i)) {
                return i;
            }
        return balance;
    }

    public static int sumFirstElements(int[] array, int count) {
        int sum = 0;
        for (int i = 0; i < count; i++) {
            sum += array[i];
        }
        //System.out.printf("sum first %d = %d \n", count, sum);
        return sum;
    }

    public static int sumLastElements(int[] array, int count) {
        int sum = 0;
        for (int i = 1; i <= count; i++) {
            sum += array[array.length - i];
        }
        //System.out.printf("sum last %d = %d \n", count, sum);
        return sum;
    }

    public static int[] offsetElements(int[] array, int offset) {
        int[] arrayWithOffset = new int[array.length];
        int targetPosition;
        for (int currentPosition = 0; currentPosition < array.length; currentPosition++) {
            if (currentPosition + offset >= array.length) {
                targetPosition = currentPosition + offset - array.length;
            } else if (currentPosition + offset < 0) {
                targetPosition = currentPosition + offset + array.length;
            } else {
                targetPosition = currentPosition + offset;
            }
            arrayWithOffset[targetPosition] = array[currentPosition];
        }
        return arrayWithOffset;
    }
}
