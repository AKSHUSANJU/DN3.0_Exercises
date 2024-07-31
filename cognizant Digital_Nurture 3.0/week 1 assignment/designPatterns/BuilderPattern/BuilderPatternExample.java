public class BuilderPatternExample {

    // Step 2: Define a Product Class
    static class Computer {
        // Attributes of the Computer
        private String CPU;
        private String RAM;
        private String storage;
        private String GPU;
        private String powerSupply;
        private String motherboard;

        // Private constructor
        private Computer(Builder builder) {
            this.CPU = builder.CPU;
            this.RAM = builder.RAM;
            this.storage = builder.storage;
            this.GPU = builder.GPU;
            this.powerSupply = builder.powerSupply;
            this.motherboard = builder.motherboard;
        }

        // Getters
        public String getCPU() {
            return CPU;
        }

        public String getRAM() {
            return RAM;
        }

        public String getStorage() {
            return storage;
        }

        public String getGPU() {
            return GPU;
        }

        public String getPowerSupply() {
            return powerSupply;
        }

        public String getMotherboard() {
            return motherboard;
        }

        // Step 3: Implement the Builder Class
        public static class Builder {
            private String CPU;
            private String RAM;
            private String storage;
            private String GPU;
            private String powerSupply;
            private String motherboard;

            public Builder setCPU(String CPU) {
                this.CPU = CPU;
                return this;
            }

            public Builder setRAM(String RAM) {
                this.RAM = RAM;
                return this;
            }

            public Builder setStorage(String storage) {
                this.storage = storage;
                return this;
            }

            public Builder setGPU(String GPU) {
                this.GPU = GPU;
                return this;
            }

            public Builder setPowerSupply(String powerSupply) {
                this.powerSupply = powerSupply;
                return this;
            }

            public Builder setMotherboard(String motherboard) {
                this.motherboard = motherboard;
                return this;
            }

            // Build method to return a Computer instance
            public Computer build() {
                return new Computer(this);
            }
        }
    }

    // Step 5: Test the Builder Implementation
    public static void main(String[] args) {
        // Create different configurations of Computer using Builder
        Computer gamingPC = new Computer.Builder()
                .setCPU("Intel i9")
                .setRAM("32GB")
                .setStorage("1TB SSD")
                .setGPU("NVIDIA RTX 3080")
                .setPowerSupply("750W")
                .setMotherboard("ASUS ROG")
                .build();

        Computer officePC = new Computer.Builder()
                .setCPU("Intel i5")
                .setRAM("16GB")
                .setStorage("512GB SSD")
                .setPowerSupply("500W")
                .setMotherboard("MSI PRO")
                .build();

        // Display configurations
        System.out.println("Gaming PC Configuration:");
        System.out.println("CPU: " + gamingPC.getCPU());
        System.out.println("RAM: " + gamingPC.getRAM());
        System.out.println("Storage: " + gamingPC.getStorage());
        System.out.println("GPU: " + gamingPC.getGPU());
        System.out.println("Power Supply: " + gamingPC.getPowerSupply());
        System.out.println("Motherboard: " + gamingPC.getMotherboard());

        System.out.println("\nOffice PC Configuration:");
        System.out.println("CPU: " + officePC.getCPU());
        System.out.println("RAM: " + officePC.getRAM());
        System.out.println("Storage: " + officePC.getStorage());
        System.out.println("Power Supply: " + officePC.getPowerSupply());
        System.out.println("Motherboard: " + officePC.getMotherboard());
    }
}

//output//
// Gaming PC Configuration:
// CPU: Intel i9
// RAM: 32GB
// Storage: 1TB SSD
// GPU: NVIDIA RTX 3080
// Power Supply: 750W
// Motherboard: ASUS ROG

// Office PC Configuration:
// CPU: Intel i5
// RAM: 16GB
// Storage: 512GB SSD
// Power Supply: 500W
// Motherboard: MSI PRO
