import java.util.ArrayList;
import java.util.List;

// Step 2: Define Subject Interface
interface Stock {
    void registerObserver(Observer observer);
    void removeObserver(Observer observer);
    void notifyObservers();
}

// Step 3: Implement Concrete Subject
class StockMarket implements Stock {
    private List<Observer> observers;
    private double stockPrice;

    public StockMarket() {
        this.observers = new ArrayList<>();
    }

    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(stockPrice);
        }
    }

    public void setStockPrice(double stockPrice) {
        this.stockPrice = stockPrice;
        notifyObservers();
    }
}

// Step 4: Define Observer Interface
interface Observer {
    void update(double stockPrice);
}

// Step 5: Implement Concrete Observers
class MobileApp implements Observer {
    private String name;

    public MobileApp(String name) {
        this.name = name;
    }

    @Override
    public void update(double stockPrice) {
        System.out.println(name + " received stock price update: $" + stockPrice);
    }
}

class WebApp implements Observer {
    private String name;

    public WebApp(String name) {
        this.name = name;
    }

    @Override
    public void update(double stockPrice) {
        System.out.println(name + " received stock price update: $" + stockPrice);
    }
}

// Step 6: Test the Observer Implementation
public class ObserverPatternExample {
    public static void main(String[] args) {
        StockMarket stockMarket = new StockMarket();

        Observer mobileApp1 = new MobileApp("MobileApp1");
        Observer mobileApp2 = new MobileApp("MobileApp2");
        Observer webApp1 = new WebApp("WebApp1");

        stockMarket.registerObserver(mobileApp1);
        stockMarket.registerObserver(mobileApp2);
        stockMarket.registerObserver(webApp1);

        stockMarket.setStockPrice(100.50);
        stockMarket.setStockPrice(101.00);

        stockMarket.removeObserver(mobileApp1);

        stockMarket.setStockPrice(102.75);
    }
}

// output
// MobileApp1 received stock price update: $100.5
// MobileApp2 received stock price update: $100.5
// WebApp1 received stock price update: $100.5
// MobileApp1 received stock price update: $101.0
// MobileApp2 received stock price update: $101.0
// WebApp1 received stock price update: $101.0
// MobileApp2 received stock price update: $102.75
// WebApp1 received stock price update: $102.75