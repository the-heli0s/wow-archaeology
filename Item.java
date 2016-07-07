package wowArchaelogy;

/**
 * Created by Лиззард on 07.07.2016.
 */
public class Item {

    private int id;
    private String name;
    private double price;

    public Item() {
        this.id = 0;
        this.name = "";
        this.price = 0.00;
    }

    public Item(int id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
