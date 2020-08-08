package animals;

public class Plate {
    public final int MAX_VOLUME;
    private int qty;

    public Plate(int MAX_VOLUME) {
        this.MAX_VOLUME = MAX_VOLUME;
        qty = 0;
    }

    public Plate(int MAX_VOLUME, int qty) {
        this.MAX_VOLUME = MAX_VOLUME;
        this.qty = qty;
    }

    // Метод возвращает количество еды, которую не получилось съесть
    public int decreaseQty(int qty) {
        if (this.qty >= qty) {
            this.qty -= qty;
            return 0;
        } else {
            int deltaQty = qty - this.qty;
            this.qty = 0;
            return deltaQty;
        }
    }

    // Метод возвращает количество неиспользованного для заполнения корма
    public int increaseQty(int addQty) {
        int availableQty = MAX_VOLUME - qty;
        if (availableQty >= addQty) {
            qty += addQty;
            return 0;
        } else {
            qty += availableQty;
            return addQty - availableQty;
        }
    }

    public int getQty() {
        return qty;
    }

    @Override
    public String toString() {
        return "Plate{" +
                "MAX_VOLUME=" + MAX_VOLUME +
                ", qty=" + qty +
                '}';
    }
}
