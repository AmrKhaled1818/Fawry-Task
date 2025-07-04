public class PerishableProduct extends Product implements Shippable {
    private boolean expired;
    private boolean shippable;
    private double weight;

    public PerishableProduct(String name, double price, int quantity, boolean expired, boolean shippable, double weight) {
        super(name, price, quantity);
        this.expired = expired;
        this.shippable = shippable;
        this.weight = weight;
    }

    @Override
    public boolean isExpired() {
        return expired;
    }

    @Override
    public boolean isShippable() {
        return shippable;
    }

    @Override
    public double getWeight() {
        return shippable ? weight : 0.0;
    }
}
