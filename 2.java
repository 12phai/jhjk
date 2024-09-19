// Task class to represent a task with description, start time, end time, and priority
public class Task {
    private String description;
    private String startTime;
    private String endTime;
    private int priority;

    // Constructor
    public Task(String description, String startTime, String endTime, int priority) {
        this.description = description;
        this.startTime = startTime;
        this.endTime = endTime;
        this.priority = priority;
    }

    // Getters and setters for the fields
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public String toString() {
        return "Task [description=" + description + ", startTime=" + startTime + ", endTime=" + endTime + ", priority=" + priority + "]";
    }
}

// TaskFactory class to create Task objects
public class TaskFactory {

    // Static method to create a Task
    public static Task createTask(String description, String startTime, String endTime, int priority) {
        return new Task(description, startTime, endTime, priority);
    }
}
