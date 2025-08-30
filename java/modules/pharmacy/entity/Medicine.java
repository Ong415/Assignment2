package modules.pharmacy.entity;

import utils.Registry;

public class Medicine implements Registry.Identifiable<String> {
    private final String id;
    private final String name;
    private final double price;

    @Override
    public String key() {
        return id;
    }

    public Medicine(String id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }
}
