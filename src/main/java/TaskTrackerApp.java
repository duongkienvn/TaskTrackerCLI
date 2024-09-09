import com.org.tasktracker.cli.TaskManagerCLI;
import com.org.tasktracker.repository.TaskRepositoryImpl;
import com.org.tasktracker.service.TaskServiceImpl;

import java.io.IOException;
import java.nio.file.Path;

public class TaskTrackerApp {
    private static final String PATH = "tasks.json";
    public static void main(String[] args) throws IOException {
        new TaskManagerCLI(new TaskServiceImpl(new TaskRepositoryImpl(Path.of(PATH)))).run();
    }
}
