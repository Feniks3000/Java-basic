package lesson6;

public class Cat extends Animal {
    public Cat(String name, Integer age, String color, int maxStomachVolume) {
        super(200, 0, name, age, color, maxStomachVolume);
    }

    @Override
    public void swim(int distance) {
        System.out.printf("%s кот и не умеет плавать\n", name);
    }

    @Override
    public void info() {
        System.out.printf("Кот по кличке %s может пробежать %d метров, а плавать не умеет\n", name, limitRun);
    }
}
