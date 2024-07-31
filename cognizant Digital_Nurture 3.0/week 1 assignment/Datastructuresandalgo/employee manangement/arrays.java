import java.util.Arrays;

class Employee {
    private String employeeId;
    private String name;
    private String position;
    private double salary;

    public Employee(String employeeId, String name, String position, double salary) {
        this.employeeId = employeeId;
        this.name = name;
        this.position = position;
        this.salary = salary;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    @Override
    public String toString() {
        return "Employee [employeeId=" + employeeId + ", name=" + name + ", position=" + position + ", salary=" + salary + "]";
    }
}

class EmployeeManagementSystem {
    private Employee[] employees;
    private int size;
    private int capacity;

    public EmployeeManagementSystem(int capacity) {
        this.capacity = capacity;
        this.employees = new Employee[capacity];
        this.size = 0;
    }

    public boolean addEmployee(Employee employee) {
        if (size >= capacity) {
            return false; // Array is full
        }
        employees[size++] = employee;
        return true;
    }

    public Employee searchEmployee(String employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                return employees[i];
            }
        }
        return null;
    }

    public void traverseEmployees() {
        for (int i = 0; i < size; i++) {
            System.out.println(employees[i]);
        }
    }

    public boolean deleteEmployee(String employeeId) {
        for (int i = 0; i < size; i++) {
            if (employees[i].getEmployeeId().equals(employeeId)) {
                // Shift elements to the left to fill the gap
                for (int j = i; j < size - 1; j++) {
                    employees[j] = employees[j + 1];
                }
                employees[--size] = null;
                return true;
            }
        }
        return false;
    }
}

public class Main {
    public static void main(String[] args) {
        EmployeeManagementSystem ems = new EmployeeManagementSystem(10);

        // Add employees
        ems.addEmployee(new Employee("E001", "Alice", "Manager", 70000));
        ems.addEmployee(new Employee("E002", "Bob", "Developer", 60000));
        ems.addEmployee(new Employee("E003", "Charlie", "Designer", 50000));

        // Traverse employees
        System.out.println("All Employees:");
        ems.traverseEmployees();

        // Search for an employee
        String searchId = "E002";
        Employee foundEmployee = ems.searchEmployee(searchId);
        if (foundEmployee != null) {
            System.out.println("\nEmployee found: " + foundEmployee);
        } else {
            System.out.println("\nEmployee with ID " + searchId + " not found.");
        }

        // Delete an employee
        String deleteId = "E003";
        boolean isDeleted = ems.deleteEmployee(deleteId);
        if (isDeleted) {
            System.out.println("\nEmployee with ID " + deleteId + " deleted.");
        } else {
            System.out.println("\nEmployee with ID " + deleteId + " not found.");
        }

        // Traverse employees after deletion
        System.out.println("\nAll Employees after deletion:");
        ems.traverseEmployees();
    }
}
