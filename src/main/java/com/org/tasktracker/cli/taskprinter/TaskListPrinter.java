package com.org.tasktracker.cli.taskprinter;

import com.org.tasktracker.model.Task;

import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Set;

public class TaskListPrinter {
    private static final String BORDER = "+-------+------------------------------------------+--------------+------------------+------------------+";
    private static final String HEADER = "| ID    | Description                              | Status       | CreatedAt        | UpdatedAt        |";
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
    private static final String TASK_FORMAT = "| %-5d | %-40s | %-12s | %-16s | %-16s |%n";
    private static final int DESCRIPTION_MAX_LENGTH = 40;

    private String truncate(String description) {
        if (description.length() <= DESCRIPTION_MAX_LENGTH) {
            return description;
        }
        return description.substring(0, description.length() - 2) + "...";
    }

    public void printTask(List<Task> tasks) {
        System.out.println(BORDER);
        System.out.println(HEADER);
        System.out.println(BORDER);

        tasks.forEach(task -> {
            String createdAt = task.getCreatedAt() != null ? task.getCreatedAt().format(DATE_TIME_FORMATTER) : "N/A";
            String updatedAt = task.getUpdatedAt() != null ? task.getUpdatedAt().format(DATE_TIME_FORMATTER) : "N/A";

            System.out.printf(TASK_FORMAT,
                    task.getId(), task.getDescription(), task.getStatus(), createdAt, updatedAt);
        });

        System.out.println(BORDER);
    }
}
