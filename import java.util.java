import java.util.List;

// TaskConflictObserver class to check for task conflicts
public class TaskConflictObserver {
    
    private ScheduleManager manager;

    // Constructor
    public TaskConflictObserver(ScheduleManager manager) {
        this.manager = manager;
    }

    // Method to check for conflicts
    public boolean checkForConflicts(Task newTask) {
        List<Task> tasks = manager.viewTasks();
        for (Task task : tasks) {
            // Check for overlap
            if (!(newTask.getEndTime().compareTo(task.getStartTime()) <= 0 || 
                  newTask.getStartTime().compareTo(task.getEndTime()) >= 0)) {
                System.out.println("Error: Task conflicts with existing task " + task.getDescription());
                return true;
            }
        }
        return false;
    }
}
