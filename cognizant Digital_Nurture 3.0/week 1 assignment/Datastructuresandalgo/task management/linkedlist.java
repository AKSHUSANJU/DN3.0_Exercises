class Task {
    int taskId;
    String taskName;
    String status;

    Task(int taskId, String taskName, String status) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.status = status;
    }

    @Override
    public String toString() {
        return "Task ID: " + taskId + ", Task Name: " + taskName + ", Status: " + status;
    }
}

class Node {
    Task task;
    Node next;

    Node(Task task) {
        this.task = task;
        this.next = null;
    }
}

class TaskLinkedList {
    Node head;

    // Add task at the end of the list
    public void addTask(Task task) {
        Node newNode = new Node(task);
        if (head == null) {
            head = newNode;
        } else {
            Node current = head;
            while (current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    // Search task by taskId
    public Task searchTask(int taskId) {
        Node current = head;
        while (current != null) {
            if (current.task.taskId == taskId) {
                return current.task;
            }
            current = current.next;
        }
        return null;
    }

    // Traverse and print all tasks
    public void traverseTasks() {
        Node current = head;
        while (current != null) {
            System.out.println(current.task);
            current = current.next;
        }
    }

    // Delete task by taskId
    public boolean deleteTask(int taskId) {
        if (head == null) {
            return false;
        }

        if (head.task.taskId == taskId) {
            head = head.next;
            return true;
        }

        Node current = head;
        while (current.next != null && current.next.task.taskId != taskId) {
            current = current.next;
        }

        if (current.next == null) {
            return false;
        }

        current.next = current.next.next;
        return true;
    }
}

public class Main {
    public static void main(String[] args) {
        TaskLinkedList taskList = new TaskLinkedList();

        // Adding tasks
        taskList.addTask(new Task(1, "Task 1", "Pending"));
        taskList.addTask(new Task(2, "Task 2", "Completed"));
        taskList.addTask(new Task(3, "Task 3", "In Progress"));

        // Traversing tasks
        System.out.println("All tasks:");
        taskList.traverseTasks();

        // Searching for a task
        System.out.println("\nSearching for Task with ID 2:");
        Task task = taskList.searchTask(2);
        if (task != null) {
            System.out.println(task);
        } else {
            System.out.println("Task not found");
        }

        // Deleting a task
        System.out.println("\nDeleting Task with ID 1:");
        boolean isDeleted = taskList.deleteTask(1);
        if (isDeleted) {
            System.out.println("Task deleted successfully.");
        } else {
            System.out.println("Task not found.");
        }

        // Traversing tasks after deletion
        System.out.println("\nAll tasks after deletion:");
        taskList.traverseTasks();
    }
}