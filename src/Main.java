public class Main {
    public static void main(String[] args) {
        // Normal purchase scenario
        try {
            Product cheese = new PerishableProduct("Cheese", 100, 10, false, true, 0.2);
            Product biscuits = new PerishableProduct("Biscuits", 150, 5, false, true, 0.7);
            Product tv = new ShippableProduct("TV", 3000, 2, 5.0);
            Product scratchCard = new Product("ScratchCard", 50, 20) {};

            Customer ali = new Customer("Ali", 1000);
            Cart cart1 = new Cart();

            cart1.add(cheese, 2);
            cart1.add(biscuits, 1);
            cart1.add(scratchCard, 1);

            System.out.println("\n--- Case 1: Successful Checkout ---");
            ali.checkout(cart1);

        } catch (Exception e) {
            System.out.println("Something went wrong in the normal case: " + e.getMessage());
        }
        // Trying to buy more than stock
        try {
            Product cheese = new PerishableProduct("Cheese", 100, 1, false, true, 0.2);
            Cart cart2 = new Cart();
            System.out.println("\n--- Case 2: Stock Check Failure ---");
            cart2.add(cheese, 2); // Requesting 2, but only 1 in stock
            Customer user = new Customer("TestUser", 500);
            user.checkout(cart2);
        } catch (Exception e) {
            System.out.println("Stock check failed as expected: " + e.getMessage());
        }
        // Buying expired item
        try {
            Product expiredItem = new PerishableProduct("Old Biscuits", 150, 3, true, true, 0.7);
            Cart cart3 = new Cart();
            cart3.add(expiredItem, 1);

            System.out.println("\n--- Case 3: Expired Product ---");
            Customer user = new Customer("Ali", 600);
            user.checkout(cart3);
        } catch (Exception e) {
            System.out.println("Expired product case caught: " + e.getMessage());
        }
        // Not enough money
        try {
            Product tv = new ShippableProduct("TV", 3000, 2, 5.0);
            Customer lowBalanceCustomer = new Customer("Student", 200);
            Cart cart4 = new Cart();
            cart4.add(tv, 1);

            System.out.println("\n--- Case 4: Insufficient Balance ---");
            lowBalanceCustomer.checkout(cart4);
        } catch (Exception e) {
            System.out.println("Balance error handled: " + e.getMessage());
        }
        // Trying to check out with an empty cart
        try {
            System.out.println("\n--- Case 5: Empty Cart ---");
            Customer customer = new Customer("Ali", 1000);
            Cart emptyCart = new Cart();
            customer.checkout(emptyCart);
        } catch (Exception e) {
            System.out.println("Empty cart case: " + e.getMessage());
        }
    }
}
