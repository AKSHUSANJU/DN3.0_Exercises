public class SingletonPatternExample {
    // Logger class implementing Singleton Pattern
    static class Logger {
        // Private static instance of Logger
        private static Logger instance;

        // Private constructor to prevent instantiation
        private Logger() {
            // Initialization code
        }

        // Public static method to get the instance of Logger
        public static Logger getInstance() {
            if (instance == null) {
                synchronized (Logger.class) {
                    if (instance == null) {
                        instance = new Logger();
                    }
                }
            }
            return instance;
        }

        // Method to log messages
        public void log(String message) {
            System.out.println("Log message: " + message);
        }
    }

    // Test class to verify Singleton implementation
    public static void main(String[] args) {
        // Get the instance of Logger
        Logger logger1 = Logger.getInstance();
        Logger logger2 = Logger.getInstance();

        // Log messages using logger instances
        logger1.log("This is the first log message.");
        logger2.log("This is the second log message.");

        // Verify that both logger1 and logger2 are the same instance
        if (logger1 == logger2) {
            System.out.println("Logger1 and Logger2 are the same instance.");
        } else {
            System.out.println("Logger1 and Logger2 are different instances.");
        }
    }
}

//output//
//Log message: This is the first log message.
//Log message: This is the second log message.
//Logger1 and Logger2 are the same instance.
