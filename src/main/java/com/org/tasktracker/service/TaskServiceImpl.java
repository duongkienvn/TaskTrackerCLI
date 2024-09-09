package com.org.tasktracker.service;

import com.org.tasktracker.exception.NoSuchTaskException;
import com.org.tasktracker.model.Task;
import com.org.tasktracker.model.TaskStatus;
import com.org.tasktracker.repository.TaskRepository;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Set;

public class TaskServiceImpl implements TaskService {
    private TaskRepository taskRepository;
    private Set<Task> tasks;

    public TaskServiceImpl(TaskRepository taskRepository) throws IOException {
        this.taskRepository = taskRepository;
        this.tasks = taskRepository.getTasks();
    }

    @Override
    public void saveTasks() throws IOException {
        taskRepository.saveTasks(tasks);
    }

    @Override
    public int addTask(String description) {
        LocalDateTime now = LocalDateTime.now();
        int newId = getNewId();
        Task task = new Task(newId, description, TaskStatus.TODO, now, now);
        tasks.add(task);
        return newId;
    }

    @Override
    public void deleteTask(int id) {
        tasks.remove(findTaskById(id));
    }

    @Override
    public void deleteAllTasks() {
        tasks.clear();
    }

    @Override
    public void updateTask(int id, String description) {
        Task originalTask = findTaskById(id);

        Task updateTask = new Task(
                originalTask.getId(),
                description,
                originalTask.getStatus(),
                originalTask.getCreatedAt(),
                LocalDateTime.now());

        tasks.remove(originalTask);
        tasks.add(updateTask);
    }

    @Override
    public void markTaskTodo(int id) {
        markTaskStatus(id, TaskStatus.TODO);
    }

    @Override
    public void markTaskInProgress(int id) {
        markTaskStatus(id, TaskStatus.IN_PROGRESS);
    }

    @Override
    public void markTaskDone(int id) {
        markTaskStatus(id, TaskStatus.DONE);
    }

    @Override
    public List<Task> getAllTasks() {
        return tasks.stream().sorted().toList();
    }

    @Override
    public List<Task> getTodoTasks() {
        return getTaskByStatus(TaskStatus.TODO);
    }

    @Override
    public List<Task> getInProgressTasks() {
        return getTaskByStatus(TaskStatus.IN_PROGRESS);
    }

    @Override
    public List<Task> getDoneTasks() {
        return getTaskByStatus(TaskStatus.DONE);
    }

    public List<Task> getTaskByStatus(TaskStatus taskStatus) {
        return tasks.stream().
                filter(task -> task.getStatus()
                        .equals(taskStatus)).sorted().toList();
    }

    private int getNewId() {
        return tasks.stream()
                .mapToInt(Task::getId)
                .max().orElse(0) + 1;
    }

    private Task findTaskById(int id) {
        return tasks.stream().
                filter(task -> task.getId() == id)
                .findFirst().
                orElseThrow(() -> new NoSuchTaskException(String.format("Task with ID %d not found", id)));
    }

    private void markTaskStatus(int id, TaskStatus taskStatus) {
        Task origionalTask = findTaskById(id);
        tasks.remove(origionalTask);

        Task changedStatusTask = new Task(
                origionalTask.getId(),
                origionalTask.getDescription(),
                taskStatus,
                origionalTask.getCreatedAt(),
                LocalDateTime.now());
        tasks.add(changedStatusTask);
    }
}
