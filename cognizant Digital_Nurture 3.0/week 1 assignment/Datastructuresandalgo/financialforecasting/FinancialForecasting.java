import java.util.HashMap;
import java.util.Map;

public class FinancialForecasting {

    // Recursive method to calculate future value
    public static double calculateFutureValueRecursive(double initialValue, double growthRate, int periods) {
        if (periods == 0) {
            return initialValue;
        }
        return (1 + growthRate) * calculateFutureValueRecursive(initialValue, growthRate, periods - 1);
    }

    // Optimized recursive method with memoization
    public static double calculateFutureValueMemoized(double initialValue, double growthRate, int periods, Map<Integer, Double> memo) {
        if (periods == 0) {
            return initialValue;
        }
        if (memo.containsKey(periods)) {
            return memo.get(periods);
        }
        double futureValue = (1 + growthRate) * calculateFutureValueMemoized(initialValue, growthRate, periods - 1, memo);
        memo.put(periods, futureValue);
        return futureValue;
    }

    public static void main(String[] args) {
        double initialValue = 1000.0;
        double growthRate = 0.05; // 5% growth rate
        int periods = 10;

        // Calculate future value using simple recursion
        double futureValueRecursive = calculateFutureValueRecursive(initialValue, growthRate, periods);
        System.out.println("Future Value (Recursive): " + futureValueRecursive);

        // Calculate future value using memoized recursion
        Map<Integer, Double> memo = new HashMap<>();
        double futureValueMemoized = calculateFutureValueMemoized(initialValue, growthRate, periods, memo);
        System.out.println("Future Value (Memoized): " + futureValueMemoized);
    }
}
