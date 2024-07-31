public class FactoryMethodPatternExample {
    // Step 2: Define Document Interfaces
    interface Document {
        void open();
        void save();
    }

    // Step 3: Create Concrete Document Classes
    static class WordDocument implements Document {
        public void open() {
            System.out.println("Opening Word document...");
        }

        public void save() {
            System.out.println("Saving Word document...");
        }
    }

    static class PdfDocument implements Document {
        public void open() {
            System.out.println("Opening PDF document...");
        }

        public void save() {
            System.out.println("Saving PDF document...");
        }
    }

    static class ExcelDocument implements Document {
        public void open() {
            System.out.println("Opening Excel document...");
        }

        public void save() {
            System.out.println("Saving Excel document...");
        }
    }

    // Step 4: Implement the Factory Method
    static abstract class DocumentFactory {
        public abstract Document createDocument();
    }

    static class WordDocumentFactory extends DocumentFactory {
        public Document createDocument() {
            return new WordDocument();
        }
    }

    static class PdfDocumentFactory extends DocumentFactory {
        public Document createDocument() {
            return new PdfDocument();
        }
    }

    static class ExcelDocumentFactory extends DocumentFactory {
        public Document createDocument() {
            return new ExcelDocument();
        }
    }

    // Step 5: Test the Factory Method Implementation
    public static void main(String[] args) {
        // Create factory instances
        DocumentFactory wordFactory = new WordDocumentFactory();
        DocumentFactory pdfFactory = new PdfDocumentFactory();
        DocumentFactory excelFactory = new ExcelDocumentFactory();

        // Create documents using factories
        Document wordDoc = wordFactory.createDocument();
        Document pdfDoc = pdfFactory.createDocument();
        Document excelDoc = excelFactory.createDocument();

        // Test document methods
        wordDoc.open();
        wordDoc.save();
        pdfDoc.open();
        pdfDoc.save();
        excelDoc.open();
        excelDoc.save();
    }
}

//output//
//Opening Word document...
//Saving Word document...
//Opening PDF document...
//Saving PDF document...
//Opening Excel document...
//Saving Excel document...
