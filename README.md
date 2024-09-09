---

<div align="center">

  # Task Tracker CLI
  
</div>
  
---

## 🎯 Overview

A simple, lightweight Task Tracker application implemented in Java with a Command Line Interface (CLI). The project is designed to help users manage and track their tasks, offering features like adding, updating, viewing, and deleting tasks, all while storing data in a JSON file, ensuring your tasks are saved between sessions.

## ✨ Features

- **Task Management:** Create, update, view, and delete tasks.
- **Task Status Tracking:** Tasks can be marked as Todo, In-Progress, or Done using an intuitive CLI.
- **Persistent Storage:** Tasks are saved to a tasks.json file for persistent data storage.
- **Formatted Output:** Displays tasks in a clean, well-organized table format within the terminal.
- **Minimal Dependencies:** No external libraries are used, ensuring the project is simple and lightweight.

## Installation

1. **Clone the repository:**

   ```bash
   git clone https://github.com/duongkienvn/TaskTrackerCLI.git
   cd TaskTrackerCLI/src/main/java

2. **Compile the source code:**
    ```bash
   javac TaskTrackerApp.java
3. **Run the application:**
    ```bash
   java TaskTrackerApp
   ```

## 📘 Usage Example

```
+-------+------------------------------------------+--------------+------------------+------------------+
| ID    | Description                              | Status       | Created Time     | Updated Time     |
+-------+------------------------------------------+--------------+------------------+------------------+
| 1     | Finish documentation                     | TODO         | 2024-09-09 10:00 | 2024-09-09 10:00 |
| 2     | Implement CLI                            | IN_PROGRESS  | 2024-09-05 10:15 | 2024-09-06 11:00 |
+-------+------------------------------------------+--------------+------------------+------------------+
```

