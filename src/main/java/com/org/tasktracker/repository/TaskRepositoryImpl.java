package com.org.tasktracker.repository;

import com.org.tasktracker.model.Task;
import com.org.tasktracker.repository.jsonmapper.JsonMapper;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.HashSet;
import java.util.Set;

public class TaskRepositoryImpl implements TaskRepository {
    private Path path;

    public TaskRepositoryImpl(Path path) {
        this.path = path;
    }

    @Override
    public void saveTasks(Set<Task> tasks) throws IOException {
        if (!Files.exists(path)) {
            Files.createFile(path);
        }
        Files.writeString(path, JsonMapper.tasksToJson(tasks));
    }

    @Override
    public Set<Task> getTasks() throws IOException {
        if (!Files.exists(path)) {
            return new HashSet<>();
        }
        return JsonMapper.jsonToTasks(Files.readString(path));
    }
}
