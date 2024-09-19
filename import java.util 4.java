import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

// ScheduleManager class implementing Singleton pattern
public class ScheduleManager {

    // Static variable to hold the single instance
    private static ScheduleManager instance;

    // List to hold tasks
    private List<Task> tasks;

    // TaskConflictObserver instance
    private TaskConflictObserver observer;

    // Private constructor to restrict instantiation
    private ScheduleManager() {
        tasks = new ArrayList<>();
        observer = new TaskConflictObserver(this);
    }

    // Method to provide global point of access
    public static ScheduleManager getInstance() {
        if (instance == null) {
            instance = new ScheduleManager();
        }
        return instance;
    }

    // Method to add a task
    public void addTask(String description, String startTime, String endTime, int priority) {
        Task task = TaskFactory.createTask(description, startTime, endTime, priority);
        if (!observer.checkForConflicts(task)) {
            tasks.add(task);
            System.out.println("Task '" + description + "' added successfully.");
        }
    }

    // Method to remove a task
    public void removeTask(String description) {
        tasks.removeIf(task -> task.getDescription().equals(description));
    }

    // Method to view tasks sorted by start time
    public List<Task> viewTasks() {
        return tasks.stream()
                    .sorted((t1, t2) -> t1.getStartTime().compareTo(t2.getStartTime()))
                    .collect(Collectors.toList());
    }

    // Getter for tasks
    public List<Task> getTasks() {
        return tasks;
    }
}
