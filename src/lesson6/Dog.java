package lesson6;

public class Dog extends Animal {
    public Dog(String name, Integer age, String color, int maxStomachVolume) {
        super(500, 10, name, age, color, maxStomachVolume);
    }

    @Override
    public void info() {
        System.out.printf("Собака по кличке %s может пробежать %d метров и проплыть %d метров\n", name, limitRun, limitSwim);
    }
}
