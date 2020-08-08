import lesson6.Animal;
import lesson6.Cat;
import lesson6.Dog;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Lesson6 {
    public static void main(String[] args) {
        Random random = new Random();
        String[] colors = {"Белый", "Серый", "Черный", "Смешанный", "Розовый"};

        List<Animal> animals = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            animals.add(new Cat("Cat number " + (i + 1), random.nextInt(14) + 1, colors[random.nextInt(colors.length)]));
            animals.add(new Dog("Dog number " + (i + 1), random.nextInt(14) + 1, colors[random.nextInt(colors.length)]));
        }
        System.out.println("======================");
        for (Animal animal : animals) {
            animal.info();
            System.out.println(animal.toString());
        }
        System.out.println("======================");
        int countCats = 0;
        for (Animal animal : animals) {
            int distance = random.nextInt(501);
            animal.run(distance);
            animal.swim(distance);
            if (isCat(animal)) {
                countCats++;
            }
        }
        System.out.printf("Всего имеется котов - %d и собак - %d\n", countCats, animals.size() - countCats);
    }

    private static boolean isCat(Animal animal) {
        return animal instanceof Cat;
    }
}
