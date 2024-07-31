public class DecoratorPatternExample {

    // Step 2: Define Component Interface
    interface Notifier {
        void send(String message);
    }

    // Step 3: Implement Concrete Component
    static class EmailNotifier implements Notifier {
        @Override
        public void send(String message) {
            System.out.println("Sending Email: " + message);
        }
    }

    // Step 4: Implement Decorator Classes
    static abstract class NotifierDecorator implements Notifier {
        protected Notifier wrappedNotifier;

        public NotifierDecorator(Notifier notifier) {
            this.wrappedNotifier = notifier;
        }

        @Override
        public void send(String message) {
            wrappedNotifier.send(message);
        }
    }

    static class SMSNotifierDecorator extends NotifierDecorator {
        public SMSNotifierDecorator(Notifier notifier) {
            super(notifier);
        }

        @Override
        public void send(String message) {
            super.send(message);
            sendSMS(message);
        }

        private void sendSMS(String message) {
            System.out.println("Sending SMS: " + message);
        }
    }

    static class SlackNotifierDecorator extends NotifierDecorator {
        public SlackNotifierDecorator(Notifier notifier) {
            super(notifier);
        }

        @Override
        public void send(String message) {
            super.send(message);
            sendSlackMessage(message);
        }

        private void sendSlackMessage(String message) {
            System.out.println("Sending Slack message: " + message);
        }
    }

    // Step 5: Test the Decorator Implementation
    public static void main(String[] args) {
        // Create basic notifier
        Notifier emailNotifier = new EmailNotifier();

        // Decorate with SMS
        Notifier smsAndEmailNotifier = new SMSNotifierDecorator(emailNotifier);

        // Decorate with Slack
        Notifier allNotifier = new SlackNotifierDecorator(smsAndEmailNotifier);

        // Send notification
        allNotifier.send("This is a test notification.");
    }
}

// output
// Sending Email: This is a test notification.
// Sending SMS: This is a test notification.
// Sending Slack message: This is a test notification.