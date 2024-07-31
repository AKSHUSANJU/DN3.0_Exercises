// Step 2: Define Strategy Interface
interface PaymentStrategy {
    void pay(double amount);
}

// Step 3: Implement Concrete Strategies
class CreditCardPayment implements PaymentStrategy {
    private String cardNumber;
    private String cardHolderName;

    public CreditCardPayment(String cardNumber, String cardHolderName) {
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using Credit Card.");
    }
}

class PayPalPayment implements PaymentStrategy {
    private String email;

    public PayPalPayment(String email) {
        this.email = email;
    }

    @Override
    public void pay(double amount) {
        System.out.println("Paid $" + amount + " using PayPal.");
    }
}

// Step 4: Implement Context Class
class PaymentContext {
    private PaymentStrategy paymentStrategy;

    public void setPaymentStrategy(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executePayment(double amount) {
        if (paymentStrategy == null) {
            throw new IllegalStateException("Payment strategy not set.");
        }
        paymentStrategy.pay(amount);
    }
}

// Step 5: Test the Strategy Implementation
public class StrategyPatternExample {
    public static void main(String[] args) {
        PaymentContext context = new PaymentContext();

        // Pay using Credit Card
        PaymentStrategy creditCardPayment = new CreditCardPayment("1234-5678-9012-3456", "John Doe");
        context.setPaymentStrategy(creditCardPayment);
        context.executePayment(100.0);

        // Pay using PayPal
        PaymentStrategy payPalPayment = new PayPalPayment("john.doe@example.com");
        context.setPaymentStrategy(payPalPayment);
        context.executePayment(200.0);
    }
}

// output
// Paid $100.0 using Credit Card.
// Paid $200.0 using PayPal.