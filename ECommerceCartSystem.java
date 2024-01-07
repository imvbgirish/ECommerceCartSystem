import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


// Product class representing the base product
class Product {
    private String name;
    private double price;
    private boolean available;

    public Product(String name, double price, boolean available) {
        this.name = name;
        this.price = price;
        this.available = available;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public boolean isAvailable() {
        return available;
    }
}

// CartItem class representing items in the shopping cart
class CartItem {
    private Product product;
    private int quantity;

    public CartItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }
}

// ShoppingCart class representing the shopping cart
class ShoppingCart {
    private List<CartItem> cartItems = new ArrayList<>();

    public void addToCart(Product product, int quantity) {
        // Prototype Pattern: Cloning the product when adding to the cart
        Product clonedProduct = new Product(product.getName(), product.getPrice(), product.isAvailable());
        cartItems.add(new CartItem(clonedProduct, quantity));
    }

    public void updateQuantity(String productName, int newQuantity) {
        for (CartItem item : cartItems) {
            if (item.getProduct().getName().equals(productName)) {
                item = new CartItem(item.getProduct(), newQuantity);
                break;
            }
        }
    }

    public void removeFromCart(String productName) {
        cartItems.removeIf(item -> item.getProduct().getName().equals(productName));
    }

    public double calculateTotalBill() {
        double total = 0;
        for (CartItem item : cartItems) {
            total += item.getProduct().getPrice() * item.getQuantity();
        }
        return total;
    }

    public String getCartItemsInfo() {
        Map<String, Integer> productQuantities = new HashMap<>();
        for (CartItem item : cartItems) {
            productQuantities.put(item.getProduct().getName(), item.getQuantity());
        }

        StringBuilder info = new StringBuilder("You have ");
        for (Map.Entry<String, Integer> entry : productQuantities.entrySet()) {
            info.append(entry.getValue()).append(" ").append(entry.getKey()).append(entry.getValue() > 1 ? "s" : "").append(" and ");
        }
        info.delete(info.length() - 5, info.length()); // Removing the trailing "and"
        info.append(" in your cart.");
        return info.toString();
    }
}

public class ECommerceCartSystem {
    public static void main(String[] args) {
        // Initialize products
        Product laptop = new Product("Laptop", 1000, true);
        Product headphones = new Product("Headphones", 50, true);

        // Create a shopping cart
        ShoppingCart shoppingCart = new ShoppingCart();

        // Add products to the cart
        shoppingCart.addToCart(laptop, 1);
        shoppingCart.addToCart(headphones, 1);

        // Update quantity of a product in the cart
        shoppingCart.updateQuantity("Laptop", 2);

        // Remove a product from the cart
        shoppingCart.removeFromCart("Headphones");

        // Display cart items and total bill
        System.out.println(shoppingCart.getCartItemsInfo());
        System.out.println("Your total bill is $" + shoppingCart.calculateTotalBill() + ".");
    }
}
