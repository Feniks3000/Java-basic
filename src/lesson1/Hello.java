package lesson1;

import java.util.Scanner;

// 7. Написать метод, которому в качестве параметра передается строка, обозначающая имя,
// метод должен вывести в консоль сообщение «Привет, указанное_имя!»;

public class Hello {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        System.out.print("Здравствуйте! \nКак вас зовут? \n> ");
        hello(in.nextLine());
    }

    public static void hello(String name) {
        System.out.printf("Приветствую вас, %s!", name);
    }
}
