public class AdapterPatternExample {

    // Step 2: Define Target Interface
    interface PaymentProcessor {
        void processPayment(double amount);
    }

    // Step 3: Implement Adaptee Classes
    static class PayPal {
        public void sendPayment(double amount) {
            System.out.println("Processing payment of $" + amount + " through PayPal.");
        }
    }

    static class Stripe {
        public void makePayment(double amount) {
            System.out.println("Processing payment of $" + amount + " through Stripe.");
        }
    }

    static class Square {
        public void performPayment(double amount) {
            System.out.println("Processing payment of $" + amount + " through Square.");
        }
    }

    // Step 4: Implement the Adapter Class
    static class PayPalAdapter implements PaymentProcessor {
        private PayPal payPal;

        public PayPalAdapter(PayPal payPal) {
            this.payPal = payPal;
        }

        @Override
        public void processPayment(double amount) {
            payPal.sendPayment(amount);
        }
    }

    static class StripeAdapter implements PaymentProcessor {
        private Stripe stripe;

        public StripeAdapter(Stripe stripe) {
            this.stripe = stripe;
        }

        @Override
        public void processPayment(double amount) {
            stripe.makePayment(amount);
        }
    }

    static class SquareAdapter implements PaymentProcessor {
        private Square square;

        public SquareAdapter(Square square) {
            this.square = square;
        }

        @Override
        public void processPayment(double amount) {
            square.performPayment(amount);
        }
    }

    // Step 5: Test the Adapter Implementation
    public static void main(String[] args) {
        // Create instances of adaptee classes
        PayPal payPal = new PayPal();
        Stripe stripe = new Stripe();
        Square square = new Square();

        // Create adapter instances
        PaymentProcessor payPalAdapter = new PayPalAdapter(payPal);
        PaymentProcessor stripeAdapter = new StripeAdapter(stripe);
        PaymentProcessor squareAdapter = new SquareAdapter(square);

        // Process payments using the adapters
        payPalAdapter.processPayment(100.0);
        stripeAdapter.processPayment(200.0);
        squareAdapter.processPayment(300.0);
    }
}

// output
// Processing payment of $100.0 through PayPal.
// Processing payment of $200.0 through Stripe.
// Processing payment of $300.0 through Square.