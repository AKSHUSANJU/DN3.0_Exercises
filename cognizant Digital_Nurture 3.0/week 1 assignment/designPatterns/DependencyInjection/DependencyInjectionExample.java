// Step 2: Define Repository Interface
interface CustomerRepository {
    Customer findCustomerById(String id);
}

// Step 3: Implement Concrete Repository
class CustomerRepositoryImpl implements CustomerRepository {
    @Override
    public Customer findCustomerById(String id) {
        // Simulate finding a customer in a database
        return new Customer(id, "John Doe");
    }
}

// Step 4: Define Service Class
class CustomerService {
    private final CustomerRepository customerRepository;

    // Step 5: Implement Dependency Injection using constructor injection
    public CustomerService(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    public Customer getCustomerById(String id) {
        return customerRepository.findCustomerById(id);
    }
}

// Customer class to hold customer information
class Customer {
    private String id;
    private String name;

    public Customer(String id, String name) {
        this.id = id;
        this.name = name;
    }

    @Override
    public String toString() {
        return "Customer{id='" + id + "', name='" + name + "'}";
    }
}

// Step 6: Test the Dependency Injection Implementation
public class DependencyInjectionExample {
    public static void main(String[] args) {
        // Create the repository instance
        CustomerRepository customerRepository = new CustomerRepositoryImpl();

        // Inject the repository into the service
        CustomerService customerService = new CustomerService(customerRepository);

        // Use the service to find a customer
        Customer customer = customerService.getCustomerById("123");

        // Display the customer details
        System.out.println(customer);
    }
}

// output
// Customer{id='123', name='John Doe'}