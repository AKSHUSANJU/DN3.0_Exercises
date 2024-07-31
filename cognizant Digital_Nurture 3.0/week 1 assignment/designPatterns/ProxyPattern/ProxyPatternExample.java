public class ProxyPatternExample {

    // Step 2: Define Subject Interface
    interface Image {
        void display();
    }

    // Step 3: Implement Real Subject Class
    static class RealImage implements Image {
        private String fileName;

        public RealImage(String fileName) {
            this.fileName = fileName;
            loadFromRemoteServer();
        }

        private void loadFromRemoteServer() {
            System.out.println("Loading " + fileName + " from remote server...");
            // Simulate loading time
            try {
                Thread.sleep(2000); // Simulating delay
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void display() {
            System.out.println("Displaying " + fileName);
        }
    }

    // Step 4: Implement Proxy Class
    static class ProxyImage implements Image {
        private RealImage realImage;
        private String fileName;

        public ProxyImage(String fileName) {
            this.fileName = fileName;
        }

        @Override
        public void display() {
            if (realImage == null) {
                realImage = new RealImage(fileName);
            }
            realImage.display();
        }
    }

    // Step 5: Test the Proxy Implementation
    public static void main(String[] args) {
        Image image1 = new ProxyImage("photo1.jpg");
        Image image2 = new ProxyImage("photo2.jpg");

        // Images will be loaded from remote server only when they are displayed
        System.out.println("First display call:");
        image1.display(); // Loads and displays
        System.out.println("Second display call:");
        image1.display(); // Displays from cache

        System.out.println("Displaying another image:");
        image2.display(); // Loads and displays
        System.out.println("Displaying another image again:");
        image2.display(); // Displays from cache
    }
}

// output
// First display call:
// Loading photo1.jpg from remote server...
// Displaying photo1.jpg
// Second display call:
// Displaying photo1.jpg
// Displaying another image:
// Loading photo2.jpg from remote server...
// Displaying photo2.jpg
// Displaying another image again:
// Displaying photo2.jpg