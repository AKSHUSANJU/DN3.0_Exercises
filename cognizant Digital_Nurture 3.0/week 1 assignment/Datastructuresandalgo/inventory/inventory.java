import java.util.HashMap;
import java.util.Map;

class Product {
    private String productId;
    private String productName;
    private int quantity;
    private double price;

    public Product(String productId, String productName, int quantity, double price) {
        this.productId = productId;
        this.productName = productName;
        this.quantity = quantity;
        this.price = price;
    }

    // Getters and Setters
    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName=" + productName + ", quantity=" + quantity + ", price=" + price + "]";
    }
}

class InventoryManagementSystem {
    private Map<String, Product> inventory;

    public InventoryManagementSystem() {
        this.inventory = new HashMap<>();
    }

    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
        System.out.println("Product added: " + product);
    }

    public void updateProduct(Product product) {
        if (inventory.containsKey(product.getProductId())) {
            inventory.put(product.getProductId(), product);
            System.out.println("Product updated: " + product);
        } else {
            System.out.println("Product not found: " + product.getProductId());
        }
    }

    public void deleteProduct(String productId) {
        Product removedProduct = inventory.remove(productId);
        if (removedProduct != null) {
            System.out.println("Product removed: " + removedProduct);
        } else {
            System.out.println("Product not found: " + productId);
        }
    }

    public void displayInventory() {
        for (Product product : inventory.values()) {
            System.out.println(product);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        InventoryManagementSystem ims = new InventoryManagementSystem();

        // Add products
        Product product1 = new Product("P001", "Product 1", 10, 100.0);
        Product product2 = new Product("P002", "Product 2", 20, 200.0);
        ims.addProduct(product1);
        ims.addProduct(product2);

        // Display inventory
        System.out.println("Current Inventory:");
        ims.displayInventory();

        // Update product
        Product updatedProduct1 = new Product("P001", "Updated Product 1", 15, 150.0);
        ims.updateProduct(updatedProduct1);

        // Display inventory after update
        System.out.println("Inventory after update:");
        ims.displayInventory();

        // Delete product
        ims.deleteProduct("P002");

        // Display inventory after deletion
        System.out.println("Inventory after deletion:");
        ims.displayInventory();
    }
}
