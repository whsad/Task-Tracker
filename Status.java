
public enum Status {

    TODO("todo"),
    IN_PROGRESS("in-progress"),
    DONE("done")
    ;

    private final String TaskStatus;

    Status(String taskStatus) {
        TaskStatus = taskStatus;
    }

    public String getTaskStatus() {
        return TaskStatus;
    }
}
