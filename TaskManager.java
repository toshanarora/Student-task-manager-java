import java.io.*;
import java.util.*;

public class TaskManager {
    private ArrayList<Task> tasks = new ArrayList<>();
    private final String FILE_NAME = "tasks.txt";

    public TaskManager() {
        loadTasks();
    }

    public void addTask(String description, String deadline) {
        int id = tasks.size() + 1;
        tasks.add(new Task(id, description, deadline, false));
        saveTasks();
        System.out.println("Task added successfully!");
    }

    public void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }
        for (Task t : tasks) {
            System.out.println(t);
        }
    }

    public void markTaskComplete(int id) {
        for (Task t : tasks) {
            if (t.getId() == id) {
                t.markCompleted();
                saveTasks();
                System.out.println("Task marked as completed!");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    public void deleteTask(int id) {
        Iterator<Task> iterator = tasks.iterator();
        while (iterator.hasNext()) {
            Task t = iterator.next();
            if (t.getId() == id) {
                iterator.remove();
                saveTasks();
                System.out.println("Task deleted successfully!");
                return;
            }
        }
        System.out.println("Task not found.");
    }

    private void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Task t : tasks) {
                writer.write(t.toFileString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks.");
        }
    }

    private void loadTasks() {
        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] parts = line.split(",");
                int id = Integer.parseInt(parts[0]);
                String desc = parts[1];
                String deadline = parts[2];
                boolean status = Boolean.parseBoolean(parts[3]);
                tasks.add(new Task(id, desc, deadline, status));
            }
        } catch (IOException e) {
            // File may not exist initially — that's okay
        }
    }
}