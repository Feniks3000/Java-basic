package lesson6;

public abstract class Animal {
    Integer limitRun;
    Integer limitSwim;
    Integer age;
    String name;
    String color;

    public Animal(Integer limitRun, Integer limitSwim, String name, Integer age, String color) {
        this.limitRun = limitRun;
        this.limitSwim = limitSwim;
        this.name = name;
        this.age = age;
        this.color = color;
    }

    public void run(int distance) {
        if (distance <= limitRun) {
            System.out.printf("%s пробежал %d метров\n", name, distance);
        } else {
            System.out.printf("%d метров - недопустимая дистанция бега %s. Максимум %d метров\n", distance, name, limitRun);
        }
    }

    public void swim(int distance) {
        if (distance <= limitSwim) {
            System.out.printf("%s пробежал %d метров\n", name, distance);
        } else {
            System.out.printf("%d метров - недопустимая дистанция плавания %s. Максимум %d метров\n", distance, name, limitSwim);
        }
    }

    public abstract void info();

    @Override
    public String toString() {
        return "Animal{" +
                "limitRun=" + limitRun +
                ", limitSwim=" + limitSwim +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                '}';
    }
}
