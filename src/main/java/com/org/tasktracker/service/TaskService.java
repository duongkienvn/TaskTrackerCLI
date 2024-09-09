package com.org.tasktracker.service;

import com.org.tasktracker.model.Task;
import com.org.tasktracker.model.TaskStatus;

import java.io.IOException;
import java.util.List;

public interface TaskService {
    void saveTasks() throws IOException;
    int addTask(String description);
    void deleteTask(int id);
    void deleteAllTasks();
    void updateTask(int id, String description);
    void markTaskTodo(int id);
    void markTaskInProgress(int id);
    void markTaskDone(int id);
    List<Task> getAllTasks();
    List<Task> getTodoTasks();
    List<Task> getInProgressTasks();
    List<Task> getDoneTasks();

}
