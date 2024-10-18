public class TaskCliApp {

    public static void main(String[] args) {
        if (args.length < 1) {
            showUsage();
            return;
        }

        String command = args[0];
        TaskManager taskManager = new TaskManager();
        switch (command) {
            case "add":
                if (args.length < 2) {
                    System.out.println("Please provide a task description.");
                } else {
                    taskManager.addTask(args[1]);
                }
                break;
            case "update":
                if (args.length < 3) {
                    System.out.println("Please provide a task ID and a new description.");
                } else {
                    taskManager.updateTask(args[1], args[2]);
                }
                break;
            case "delete":
                if (args.length < 2) {
                    System.out.println("Please provide a task ID");
                } else {
                    taskManager.deleteTask(args[1]);
                }
                break;
            case "mark-in-progress":
            case "mark-done":
                if (args.length < 2) {
                    System.out.println("Please provide a task ID");
                } else {
                    Status status = command.equals("mark-in-progress") ? Status.IN_PROGRESS : Status.DONE;
                    taskManager.markInProgressOrDone(args[1], status);
                }
                break;
            case "list":
                if (args.length < 2) {
                    taskManager.listTasks(null);
                } else {
                    taskManager.listTasks(args[1]);
                }
                break;
            default:
                System.out.println("Unknown command: " + command);
                System.out.println("保存成功");
                taskManager.saveTasks();
                showUsage();
                break;
        }
        taskManager.saveTasks();
    }

    private static void showUsage() {
        System.out.println("Usage:");
        System.out.println("  add <description> - Add a new task");
        System.out.println("  update <id> <description> - Update a task");
        System.out.println("  delete <id> - Delete a task");
        System.out.println("  mark-in-progress <id> - Mark task as in-progress");
        System.out.println("  mark-done <id> - Mark task as done");
        System.out.println("  list [status] - List tasks by status (todo, in-progress, done)");
    }
}
