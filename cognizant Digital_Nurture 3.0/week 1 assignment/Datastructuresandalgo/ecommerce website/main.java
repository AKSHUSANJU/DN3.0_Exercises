import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class Product {
    private String productId;
    private String productName;
    private String category;

    public Product(String productId, String productName, String category) {
        this.productId = productId;
        this.productName = productName;
        this.category = category;
    }

    // Getters
    public String getProductId() {
        return productId;
    }

    public String getProductName() {
        return productName;
    }

    public String getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product [productId=" + productId + ", productName=" + productName + ", category=" + category + "]";
    }
}

class SearchAlgorithms {

    // Linear Search
    public static int linearSearch(Product[] products, String targetId) {
        for (int i = 0; i < products.length; i++) {
            if (products[i].getProductId().equals(targetId)) {
                return i;
            }
        }
        return -1;
    }

    // Binary Search
    public static int binarySearch(Product[] products, String targetId) {
        int left = 0;
        int right = products.length - 1;
        while (left <= right) {
            int mid = left + (right - left) / 2;
            int comparison = products[mid].getProductId().compareTo(targetId);
            if (comparison == 0) {
                return mid;
            } else if (comparison < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}

public class Main {
    public static void main (String[] args) {
        Product[] products = {
                new Product("P001", "Product 1", "Category 1"),
                new Product("P002", "Product 2", "Category 2"),
                new Product("P003", "Product 3", "Category 3"),
                new Product("P004", "Product 4", "Category 4"),
                new Product("P005", "Product 5", "Category 5")
        };

        Scanner scanner = new Scanner(System.in);

        // Get user input for the product ID to be searched
        System.out.print("Enter the product ID to be searched: ");
        String targetId = scanner.nextLine();

        // Linear Search
        int index = SearchAlgorithms.linearSearch(products, targetId);
        if (index != -1) {
            System.out.println("Linear Search: Product found at index " + index + ": " + products[index]);
        } else {
            System.out.println("Linear Search: Product not found");
        }

        // Binary Search requires sorted array
        Arrays.sort(products, Comparator.comparing(Product::getProductId));
        index = SearchAlgorithms.binarySearch(products, targetId);
        if (index != -1) {
            System.out.println("Binary Search: Product found at index " + index + ": " + products[index]);
        } else {
            System.out.println("Binary Search: Product not found");
        }

        scanner.close();
    }
}
