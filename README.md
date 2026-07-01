# Task Manager CLI

A simple Command Line Interface (CLI) application for managing your tasks, built with Java and Maven.

## How to Build and Run

Since this project uses Maven, you can build and run it using the following commands:

### Build the project
```bash
mvn clean compile
```

### Run with Maven
```bash
mvn exec:java -Dexec.mainClass="sta.Main"
```

### Run using the JAR file
You can also run the pre-built jar file directly from the `out/artifacts/` directory:
```bash
java -jar out/artifacts/Task_manager.jar
```

Once the application is running, you will see a prompt:
```
Welcome to task manager cli

>>
```
From here, you can start entering the commands below. To exit the application, type `q` or `^[`.

## Available Commands

The CLI supports the following commands to manage your tasks. Make sure to wrap your task descriptions in quotes if they contain spaces.

### 1. Add a Task
Adds a new task to your list.
```
task-cli add "Buy groceries"
```

### 2. Update a Task
Updates the description of an existing task by its ID.
```
task-cli update 1 "Buy groceries and cook dinner"
```

### 3. Delete a Task
Deletes a task by its ID.
```
task-cli delete 1
```

### 4. Mark Task in Progress
Updates the status of a task to `in-progress`.
```
task-cli mark-in-progress 1
```

### 5. Mark Task Done
Updates the status of a task to `done`.
```
task-cli mark-done 1
```

### 6. List Tasks
You can list all tasks or filter them by their status.

- **List all tasks:**
  ```
  task-cli list
  ```
- **List tasks to do:**
  ```
  task-cli list todo
  ```
- **List tasks in progress:**
  ```
  task-cli list in-progress
  ```
- **List completed tasks:**
  ```
  task-cli list done
  ```

### 7. Help
To display help information within the CLI:
```
?help
```
