import animals.Cat;
import animals.Dog;
import animals.Plate;

import java.util.Random;

public class Lesson7_Animals {
    public static void main(String[] args) {
        Random random = new Random();
        String[] colors = {"Белый", "Серый", "Черный", "Смешанный", "Розовый"};

        System.out.println("Создали тарелку с едой и наполнили ее полностью. 10 единиц");
        Plate plate = new Plate(10, 10);
        System.out.println(plate);

        System.out.println("Создали голодных кошку (желудок 4) и собаку (желудок 8)");
        Cat cat = new Cat("Мурзик", random.nextInt(14) + 1, colors[random.nextInt(colors.length)], 4);
        Dog dog = new Dog("Бобик", random.nextInt(14) + 1, colors[random.nextInt(colors.length)], 8);
        cat.isWellFed();
        dog.isWellFed();

        System.out.println("Покормили кошку и собаку из тарелки");
        cat.eat(plate);
        cat.isWellFed();
        System.out.println(plate);
        dog.eat(plate);
        dog.isWellFed();
        System.out.println(plate);

        System.out.println("Наполнили тарелку большим объемом. Не вошло в тарелку корма: " + plate.increaseQty(15));

        System.out.println("Еще раз покормили собаку и кошку");
        dog.eat(plate);
        dog.isWellFed();
        System.out.println(plate);
        cat.eat(plate);
        cat.isWellFed();
        System.out.println(plate);
    }
}
