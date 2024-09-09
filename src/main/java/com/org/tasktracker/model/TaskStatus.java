package com.org.tasktracker.model;

public enum TaskStatus {
    TODO("Todo"),
    IN_PROGRESS("In progress"),
    DONE("Done");

    private final String TITLE_CASE_STATUS;

    TaskStatus(String TITLE_CASE_STATUS) {
        this.TITLE_CASE_STATUS = TITLE_CASE_STATUS;
    }

    public String getTITLE_CASE_STATUS() {
        return TITLE_CASE_STATUS;
    }
}
