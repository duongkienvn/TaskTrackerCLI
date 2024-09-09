package com.org.tasktracker.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Task implements Comparable<Task> {
    private int id;
    private String description;
    private TaskStatus status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public Task(int id, String description, TaskStatus status, LocalDateTime createdAt, LocalDateTime updatedAt) {
        checkId(id);
        checkDescription(description);

        this.id = id;
        this.description = description;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    @Override
    public String toString() {
        return String.format("[Task: %d | Description: %s | Status: %s | Created: %s | Updated: %s]",
                this.id,
                this.description,
                this.status.getTITLE_CASE_STATUS(),
                this.createdAt.toString(),
                this.updatedAt.toString()
        );
    }

    private void checkId(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("Task ID must be positive integer!");
        }
    }

    private void checkDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or empty!");
        }
        if (description.length() > 255) {
            throw new IllegalArgumentException("Description too long!");
        }
    }

    @Override
    public int compareTo(Task other) {
        return Integer.compare(this.id, other.id);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}
