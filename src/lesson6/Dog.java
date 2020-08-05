package lesson6;

public class Dog extends Animal {
    public Dog(String name) {
        super(500, 10, name);
    }

    @Override
    public void info() {
        System.out.printf("Собака по кличке %s может пробежать %d метров и проплыть %d метров\n", name, limitRun, limitSwim);
    }
}
