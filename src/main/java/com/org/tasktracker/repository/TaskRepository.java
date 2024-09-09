package com.org.tasktracker.repository;

import com.org.tasktracker.model.Task;

import java.io.IOException;
import java.util.Set;

public interface TaskRepository {
    void saveTasks(Set<Task> tasks) throws IOException;
    Set<Task> getTasks() throws IOException;
}
