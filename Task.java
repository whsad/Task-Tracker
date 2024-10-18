import java.time.LocalDateTime;
import java.util.UUID;

public class Task {

    private String id;
    private String description;
    private String status;
    private LocalDateTime createAt;
    private LocalDateTime updateAt;

    public Task(String description) {
        this.id = UUID.randomUUID().toString();
        this.description = description;
        this.status = Status.TODO.getTaskStatus();
        this.createAt = LocalDateTime.now();
        this.updateAt = LocalDateTime.now();
    }

    public Task(String id, String description, String status, String createAt, String updateAt) {
        this.id = id;
        this.description = description;
        this.status = status;
        this.createAt = LocalDateTime.parse(createAt);
        this.updateAt = LocalDateTime.parse(updateAt);
    }

    public void updateDescription(String description){
        this.description = description;
        this.updateAt = LocalDateTime.now();
    }

    public void markInProgressOrDone(String status){
        this.status = status;
        this.updateAt = LocalDateTime.now();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getCreateAt() {
        return createAt;
    }

    public void setCreateAt(LocalDateTime createAt) {
        this.createAt = createAt;
    }

    public LocalDateTime getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(LocalDateTime updateAt) {
        this.updateAt = updateAt;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id='" + id + '\'' +
                ", description='" + description + '\'' +
                ", status='" + status + '\'' +
                ", createAt=" + createAt +
                ", updateAt=" + updateAt +
                '}';
    }

    public String convertTaskToJson (){
        return "{"
                + "\"id\":\"" + id + "\","
                + "\"description\":\"" + description + "\","
                + "\"status\":\"" + status + "\","
                + "\"createAt\":\"" + createAt + "\","
                + "\"updateAt\":\"" + updateAt + "\""
                + "}";
    }

    public static Task parseTaskFromJson (String json){
        String[] jsonArr = json.substring(1, json.length() - 1)
                .replace("\"", "")
                .split(",");

        String id = jsonArr[0].split(":")[1];
        String description = jsonArr[1].split(":")[1];
        String status = jsonArr[2].split(":")[1];
        String createAt = jsonArr[3].split(":", 2)[1];
        String updateAt = jsonArr[4].split(":", 2)[1];

        return new Task(
                id,
                description,
                status,
                createAt,
                updateAt
        );
    }

}
