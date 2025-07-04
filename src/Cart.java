import java.util.ArrayList;
import java.util.List;

public class Cart {
    private List<CartItems> items = new ArrayList<>();

    public void add(Product product, int quantity) throws Exception {
        if (quantity > product.getQuantity()) {
            throw new Exception("Not enough stock for: " + product.getName());
        }
        items.add(new CartItems(product, quantity));
    }

    public List<CartItems> getItems() {
        return items;
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
