public class Task {
    private int id;
    private String description;
    private String deadline;
    private boolean isCompleted;

    public Task(int id, String description, String deadline, boolean isCompleted) {
        this.id = id;
        this.description = description;
        this.deadline = deadline;
        this.isCompleted = isCompleted;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public String getDeadline() {
        return deadline;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void markCompleted() {
        this.isCompleted = true;
    }

    public String toFileString() {
        return id + "," + description + "," + deadline + "," + isCompleted;
    }

    @Override
    public String toString() {
        return id + ". " + description + " | Deadline: " + deadline + 
               " | Status: " + (isCompleted ? "Done" : "Pending");
    }
}