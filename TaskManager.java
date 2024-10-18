
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TaskManager {
    private final List<Task> tasks;
    private static final String TASKS_PATH = System.getProperty("user.dir") + File.separator + "tasks.json";

    public TaskManager() {
        tasks = loadTasks();
    }

    public void addTask(String description) {
        Task newTask = new Task(description);
        tasks.add(newTask);
        System.out.println("Task added successfully (ID: " + newTask.getId() + ")");
    }

    public void updateTask(String Id, String description) {
        Task task = findTaskById(Id);
        if (task == null) {
            System.out.println("Task not found.");
            return;
        }
        task.updateDescription(description);
        System.out.println("Task updated successfully (ID: " + task.getId() + ")");
    }

    public void deleteTask(String Id) {
        Task task = findTaskById(Id);
        if (task == null) {
            System.out.println("Task not found.");
            return;
        }
        tasks.remove(task);
    }

    public void markInProgressOrDone(String Id, Status status) {
        Task task = findTaskById(Id);
        if (task == null) {
            System.out.println("Task not found.");
            return;
        }
        String newStatus = status.getTaskStatus();
        task.markInProgressOrDone(newStatus);
        System.out.println("Task marked as " + newStatus + " (ID: " + Id + ")");
    }

    private Task findTaskById(String Id) {
        for (Task task : tasks) {
            if (task.getId().equals(Id)) {
                return task;
            }
        }
        return null;
    }

    public void listTasks(String status) {
        if (status == null) {
            tasks.forEach(System.out::println);
        } else {
            String normalizedStatus = status.toUpperCase();
            boolean isValidStatus = normalizedStatus.equals(Status.TODO.getTaskStatus().toUpperCase())
                    || normalizedStatus.equals(Status.IN_PROGRESS.getTaskStatus().toUpperCase())
                    || normalizedStatus.equals(Status.DONE.getTaskStatus().toUpperCase());

            if (isValidStatus) {
                tasks.stream()
                        .filter(task -> task.getStatus().equalsIgnoreCase(status))
                        .forEach(System.out::println);
            }
        }
    }

    // 保存任务到 JSON 文件
    public void saveTasks() {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(TASKS_PATH))) {
            writer.write("[\n"); // JSON 数组开始
            for (int i = 0; i < tasks.size(); i++) {
                writer.write(tasks.get(i).convertTaskToJson());
                if (i < tasks.size() - 1) {
                    writer.write(",\n"); // 添加逗号分隔
                }
            }
            writer.write("\n]"); // JSON 数组结束
            writer.flush();
        } catch (IOException e) {
            System.err.println("Error saving tasks: " + e.getMessage());
        }
    }

    // 从 JSON 文件加载任务
    private List<Task> loadTasks() {
        File file = new File(TASKS_PATH);
        if (!file.exists()) {
            return new ArrayList<>();
        }

        List<Task> stored_task = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            StringBuilder sb = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            parseTasksFromJson(sb.toString(), stored_task);
        } catch (IOException e) {
            System.err.println("Error loading tasks: " + e.getMessage());
        }
        return stored_task;
    }

    private void parseTasksFromJson(String json, List<Task> stored_task) {
        json = json.trim();
        // 空数组
        if (json.equals("[]")) return;
        // 去除头尾[]
        json = json.substring(1, json.length() - 1);
        // 获取每一个任务
        String[] tasks = json.split("},");
        for (String taskJson : tasks) {
            if (!taskJson.endsWith("}")) {
                taskJson = taskJson + "}";
            }
            stored_task.add(Task.parseTaskFromJson(taskJson));
        }
    }
}
