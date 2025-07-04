import java.util.*;

public class Customer {
    private String name;
    private double balance;

    public Customer(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public void checkout(Cart cart) throws Exception {
        if (cart.isEmpty()) throw new Exception("Cart is empty");

        double subtotal = 0;
        List<Shippable> shippables = new ArrayList<>();

        for (CartItems item : cart.getItems()) {
            Product p = item.product;

            if (item.quantity > p.getQuantity())
                throw new Exception("Out of stock: " + p.getName());
            if (p.isExpired())
                throw new Exception("Expired: " + p.getName());

            subtotal += p.getPrice() * item.quantity;

            if (p.isShippable()) {
                for (int i = 0; i < item.quantity; i++) {
                    shippables.add((Shippable) p);
                }
            }
        }

        double shipping = shippables.size() * 10; // Flat fee per item
        double total = subtotal + shipping;

        if (total > balance)
            throw new Exception("Insufficient balance");

        // Shipment notice
        if (!shippables.isEmpty()) {
            System.out.println("** Shipment notice **");

            Map<String, Double> groupedWeights = new LinkedHashMap<>();
            Map<String, Integer> counts = new LinkedHashMap<>();

            for (Shippable s : shippables) {
                groupedWeights.put(s.getName(), groupedWeights.getOrDefault(s.getName(), 0.0) + s.getWeight());
                counts.put(s.getName(), counts.getOrDefault(s.getName(), 0) + 1);
            }

            double totalWeight = 0;
            for (String name : groupedWeights.keySet()) {
                double weight = groupedWeights.get(name);
                int count = counts.get(name);
                System.out.printf("%dx %-12s %.0fg\n", count, name, weight * 1000);
                totalWeight += weight;
            }
            System.out.printf("Total package weight %.1fkg\n\n", totalWeight);
        }

        // Receipt
        System.out.println("** Checkout receipt **");
        for (CartItems item : cart.getItems()) {
            System.out.printf("%dx %-12s %.0f\n", item.quantity, item.product.getName(),
                    item.product.getPrice() * item.quantity);
            item.product.reduceQuantity(item.quantity);
        }

        System.out.println("----------------------");
        System.out.printf("Subtotal         %.0f\n", subtotal);
        System.out.printf("Shipping         %.0f\n", shipping);
        System.out.printf("Amount           %.0f\n", total);

        balance -= total;
        System.out.printf("Remaining Balance: %.0f\n", balance);
    }
}
