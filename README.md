# Task Tracker

------

Task Tracker is a command-line application designed to help you track and manage your tasks efficiently. It supports creating, updating, deleting, and listing tasks, as well as marking tasks as "in-progress" or "done." Tasks are stored as JSON objects in a file for persistence.

## Features

- **Add Task**: Create new tasks with a description.
- **Update Task**: Modify an existing task's description.
- **Delete Task**: Remove tasks by their ID.
- **List Tasks**: Display tasks based on status (`todo`, `in-progress`, `done`), or all tasks.
- **Task Status Management**: Mark tasks as `in-progress` or `done`.
- **Data Persistence**: Save tasks as JSON and load them automatically on startup.

## Compilation Instructions

Ensure you have the Java Development Kit (JDK) installed on your system.

1. **Clone the repository**:

```
git clone https://github.com/whsad/Task-Tracker.git
cd Task-Tracker
```

2. **Compile the project**:

```
javac -encoding UTF-8 Task.java Status.java TaskCliApp.java TaskManager.java
```

## Usage

Run the application with the following commands:

1. **Add a new task**:

```
java TaskCliApp add "Task description here"
```

2. **Update an existing task**:

```
java TaskCliApp update <taskId> "New description"
```

3. **Delete a task**:

```
java TaskCliApp delete <taskId>
```

4. **Mark a task as in-progress or done**:

```
java TaskCliApp mark-in-progress <taskId>
java TaskCliApp mark-done <taskId>
```

5. **List all tasks** or tasks filtered by status:

```
java TaskCliApp list
java TaskCliApp list todo
java TaskCliApp list in-progress
java TaskCliApp list done
```

## Notes

- The tasks are stored in `tasks.json` located in the project directory.
- If the JSON file is missing, the application creates one on startup.
