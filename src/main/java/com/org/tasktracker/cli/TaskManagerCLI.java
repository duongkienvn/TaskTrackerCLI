package com.org.tasktracker.cli;

import com.org.tasktracker.cli.taskprinter.TaskListPrinter;
import com.org.tasktracker.exception.NoSuchTaskException;
import com.org.tasktracker.service.TaskService;

import java.io.IOException;
import java.util.Scanner;

import static java.lang.System.out;

public class TaskManagerCLI {
    private TaskService taskService;
    private Scanner scanner;
    private TaskListPrinter taskListPrinter;

    public TaskManagerCLI(TaskService taskService) {
        this.taskService = taskService;
        this.scanner = new Scanner(System.in);
        this.taskListPrinter = new TaskListPrinter();
    }

    public void run() {
        printHelpMenu();

        String command;
        while (!(command = scanner.next().toLowerCase()).equals("exit")) {
            switch (command) {
                case "add" -> add();
                case "delete" -> delete();
                case "clear" -> deleteAll();
                case "update" -> update();
                case "mark-todo" -> markToDo();
                case "mark-inprogress" -> markInProgress();
                case "mark-done" -> markDone();
                case "list" -> list();
                case "list-todo" -> listTodo();
                case "list-inprogress" -> listInProgress();
                case "list-done" -> listDone();
                case "help" -> printHelpMenu();
                default -> out.println("Unknown command. Type 'help' to see the list of commands or 'exit' to exit.");
            }
        }
        exit();
    }

    private void add() {
        String description = scanner.nextLine().trim();
        int id = taskService.addTask(description);
        System.out.println("Task added successfully (ID: " + id + ")");
    }

    private void delete() {
        try {
            int id = scanner.nextInt();
            taskService.deleteTask(id);
            System.out.println("Task deleted successfully!");
            scanner.nextLine();
        } catch (NoSuchTaskException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteAll() {
        taskService.deleteAllTasks();
        System.out.println("All the task deleted!");
    }

    private void update() {
        try {
            int id = scanner.nextInt();
            String description = scanner.nextLine().trim();
            taskService.updateTask(id, description);
            System.out.println("Task updated successfully!");
        } catch (NoSuchTaskException e) {
            System.out.println(e.getMessage());
        }
    }

    private void markToDo() {
        try {
            int id = scanner.nextInt();
            taskService.markTaskTodo(id);
            System.out.println("Task marked as todo successfully!");
            scanner.nextLine();
        } catch (NoSuchTaskException e) {
            System.out.println(e.getMessage());
        }
    }

    private void markInProgress() {
        try {
            int id = scanner.nextInt();
            taskService.markTaskInProgress(id);
            System.out.println("Task marked as in-progress successfully!");
            scanner.nextLine();
        } catch (NoSuchTaskException e) {
            System.out.println(e.getMessage());
        }
    }

    private void markDone() {
        try {
            int id = scanner.nextInt();
            taskService.markTaskDone(id);
            System.out.println("Task marked as done successfully!");
            scanner.nextLine();
        } catch (NoSuchTaskException e) {
            System.out.println(e.getMessage());
        }
    }

    private void list() {
        taskListPrinter.printTask(taskService.getAllTasks());
        scanner.nextLine();
    }

    private void listTodo() {
        taskListPrinter.printTask(taskService.getTodoTasks());
        scanner.nextLine();
    }

    private void listInProgress() {
        taskListPrinter.printTask(taskService.getInProgressTasks());
        scanner.nextLine();
    }

    private void listDone() {
        taskListPrinter.printTask(taskService.getDoneTasks());
        scanner.nextLine();
    }

    private void printHelpMenu() {
        String help = """
                - add [description] : Add a new task
                - update [id] [description] : Update a task
                - delete [id] : Delete a task
                - clear: Delete all tasks
                - mark-todo [id] : Mark a task as Todo
                - mark-in-progress [id] : Mark a task as In-Progress
                - mark-done [id] : Mark a task as Done
                - list : List all tasks
                - list-todo : List all Todo tasks
                - list-in-progress : List all In-Progress tasks
                - list-done : List all Done tasks
                - exit : Exit the program
                """;
        System.out.println(help);
    }

    private void exit() {
        try {
            System.out.println("Saving the task....");
            taskService.saveTasks();
            System.out.println("The task was saved!");
        } catch (IOException e) {
            System.out.println("Something went wrong and couldn't save the tasks!");
        }
    }
}
