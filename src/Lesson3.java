import java.util.Scanner;

/*
1. Написать программу, которая загадывает случайное число от 0 до 9 и пользователю дается 3 попытки угадать это число.
При каждой попытке компьютер должен сообщить, больше ли указанное пользователем число, чем загаданное, или меньше.
После победы или проигрыша выводится запрос – «Повторить игру еще раз? 1 – да / 0 – нет»(1 – повторить, 0 – нет).

2. * Создать массив из слов
String[] words = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic",
"grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple",
"pumpkin", "potato"}.

При запуске программы компьютер загадывает слово, запрашивает ответ у пользователя, сравнивает его с загаданным словом
и сообщает, правильно ли ответил пользователь. Если слово не угадано, компьютер показывает буквы, которые стоят на своих местах.
apple – загаданное
apricot - ответ игрока
ap############# (15 символов, чтобы пользователь не мог узнать длину слова)

Для сравнения двух слов посимвольно можно пользоваться:
String str = "apple";
char a = str.charAt(0); - метод, вернет char, который стоит в слове str на первой позиции
Играем до тех пор, пока игрок не отгадает слово.
Используем только маленькие буквы.
 */

public class Lesson3 {

    private static final Scanner in = new Scanner(System.in);

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Программы для запуска:\n" +
                "1. Угадай число от 0 до 9 \n" +
                "2. Угадай слово \n\n" +
                "Введи номер программы для запуска: ");
        int program = in.nextInt();

        switch (program) {
            case 1:
                runGameGuessNumber();
                break;
            case 2:
                runGameGuessWord();
                break;
            default:
                System.out.println("К сожалению, игру для вас еще не разработали");
        }
    }

    public static void runGameGuessNumber() {
        int FROM = 0;
        int TO = 9;
        int QTY_ATTEMPT = 3;

        do {
            int randomNumber = getRandomIntInRange(FROM, TO);
            int number;
            int attempt = 0;

            do {
                attempt++;
                number = getIntInRange("Введи целое число от %d до %d: ", FROM, TO);
                if (number > randomNumber) {
                    System.out.printf("Число %d больше загаданного\n", number);
                } else if (number < randomNumber) {
                    System.out.printf("Число %d меньше загаданного\n", number);
                }
            } while (number != randomNumber && attempt < QTY_ATTEMPT);

            if (number == randomNumber) {
                System.out.printf("Поздравляю! Ты выиграл! С %d попытки ты угадал число %d\n", attempt, randomNumber);
            } else {
                System.out.printf("Игра окончена! Ты проиграл. Загаданное число %d\n", randomNumber);
            }
            System.out.print("\nСыграем еще разок? Введи 1 чтобы продолжить, для выхода нажми - Enter: ");

        } while (in.nextLine().equals("1"));
    }

    public static void runGameGuessWord() {
        String[] WORDS = {"apple", "orange", "lemon", "banana", "apricot", "avocado", "broccoli", "carrot", "cherry", "garlic",
                "grape", "melon", "leak", "kiwi", "mango", "mushroom", "nut", "olive", "pea", "peanut", "pear", "pepper", "pineapple",
                "pumpkin", "potato"};

        do {
            int randomNumber = getRandomIntInRange(0, WORDS.length - 1);
            //System.out.println(WORDS[randomNumber]);
            String word;
            int attempt = 0;

            do {
                attempt++;
                System.out.printf("Попытка %d. Попробуй угадать как пишется загаданный овощь или фрукт по-английски: ", attempt);
                word = in.nextLine();
                if (!WORDS[randomNumber].equals(word.toLowerCase())) {
                    compareWords(WORDS[randomNumber], word);
                }
            } while (!WORDS[randomNumber].equals(word.toLowerCase()));

            if (WORDS[randomNumber].equals(word.toLowerCase())) {
                System.out.printf("Угадал! Ты молодец! С %d попытки угадать слово %s удается немногим\n", attempt, word);
            } else {
                System.out.printf("Нда... С догадливостью у тебя туго. С %d попытки не угадал слово было %s\n", attempt, WORDS[randomNumber]);
            }
            System.out.print("\nСыграем еще разок? Введи 1 чтобы продолжить, для выхода нажми - Enter: ");

        } while (in.nextLine().equals("1"));
    }

    public static void compareWords(String firstWord, String secondWord) {
        for (int i = 0; i < firstWord.length(); i++) {
            if (i < secondWord.length() && firstWord.charAt(i) == secondWord.charAt(i)) {
                System.out.print(firstWord.charAt(i));
            } else {
                System.out.print("_");
            }
        }
        for (int i = 0; i < 15 - firstWord.length(); i++) {
            System.out.print("_");
        }
        System.out.println();
    }

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
