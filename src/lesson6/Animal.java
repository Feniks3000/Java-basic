package lesson6;

public abstract class Animal {
    public final int MAX_STOMACH_VOLUME;
    Integer limitRun;
    Integer limitSwim;
    Integer age;
    String name;
    String color;
    Integer satiety;

    public Animal(Integer limitRun, Integer limitSwim, String name, Integer age, String color, int MAX_STOMACH_VOLUME) {
        this.limitRun = limitRun;
        this.limitSwim = limitSwim;
        this.name = name;
        this.age = age;
        this.color = color;
        this.MAX_STOMACH_VOLUME = MAX_STOMACH_VOLUME;
        this.satiety = 0;
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

    public void eat(Plate plate) {
        int beforeSatety = satiety;
        while (satiety < MAX_STOMACH_VOLUME && plate.decreaseQty(1) == 0) {
            satiety += 1;
            System.out.printf("%s съел 1 единицу еды из тарелки и сыт на %d из %d\n", name, satiety, MAX_STOMACH_VOLUME);
        }
        System.out.printf("Всего %s съел %d единиц еды из тарелки и сыт на %d из %d\n", name, satiety - beforeSatety, satiety, MAX_STOMACH_VOLUME);
    }

    public boolean isWellFed() {
        if (satiety == MAX_STOMACH_VOLUME) {
            System.out.printf("%s полностью сыт (%d из %d)\n", name, satiety, MAX_STOMACH_VOLUME);
            return true;
        } else if (satiety > MAX_STOMACH_VOLUME / 2) {
            System.out.printf("%s в целом сыт, но десерт еще бы вошлел (%d из %d)\n", name, satiety, MAX_STOMACH_VOLUME);
            return true;
        } else {
            System.out.printf("%s на грани истощения, пора накормить животинку (%d из %d)\n", name, satiety, MAX_STOMACH_VOLUME);
            return false;
        }
    }

    public abstract void info();

    @Override
    public String toString() {
        return "Animal{" +
                "MAX_STOMACH_VOLUME=" + MAX_STOMACH_VOLUME +
                ", limitRun=" + limitRun +
                ", limitSwim=" + limitSwim +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", color='" + color + '\'' +
                ", satiety=" + satiety +
                '}';
    }
}
