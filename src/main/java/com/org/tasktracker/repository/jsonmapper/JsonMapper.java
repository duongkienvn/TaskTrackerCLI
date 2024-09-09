package com.org.tasktracker.repository.jsonmapper;

import com.org.tasktracker.model.Task;
import com.org.tasktracker.model.TaskStatus;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toSet;

public class JsonMapper {
    private static String convertTaskToJson(Task task) {
        return "{" +
                "\"id\":" + task.getId() + "," +
                "\"description\":\"" + task.getDescription() + "\"," +
                "\"status\":\"" + task.getStatus() + "\"," +
                "\"createdAt\":\"" + task.getCreatedAt() + "\"," +
                "\"updatedAt\":\"" + task.getUpdatedAt() + "\"" +
                "}";
    }

    private static Task convertJsonToTask(String json) {
        String[] keyValuePairs = json.replaceAll("\"", "").split(",");

        int id = 0;
        String description = null;
        TaskStatus status = null;
        LocalDateTime createdAt = null;
        LocalDateTime updatedAt = null;

        for (String keyValuePair : keyValuePairs) {
            String key = keyValuePair.substring(0, keyValuePair.indexOf(":"));
            String value = keyValuePair.substring(keyValuePair.indexOf(":") + 1);

            switch (key) {
                case "id" -> id = Integer.parseInt(value);
                case "description" -> description = value;
                case "status" -> status = TaskStatus.valueOf(value);
                case "createdAt" -> createdAt = LocalDateTime.parse(value);
                case "updatedAt" -> updatedAt = LocalDateTime.parse(value);
            }
        }

        return new Task(id, description, status, createdAt, updatedAt);
    }

    public static String tasksToJson(Set<Task> tasks) {
        if (tasks.isEmpty()) {
            return "";
        }
        return tasks.stream()
                .map(JsonMapper::convertTaskToJson)
                .collect(joining(",", "[", "]"));
    }

    public static Set<Task> jsonToTasks(String json) {
        if (json == null || json.isEmpty()) {
            return new HashSet<>();
        }

        return Arrays.stream(json.substring(2, json.length() - 2).split("},\\{"))
                .map(JsonMapper::convertJsonToTask)
                .collect(toSet());
    }
}
