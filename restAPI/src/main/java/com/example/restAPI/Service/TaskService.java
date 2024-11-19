package com.example.restAPI.Service;

import com.example.restAPI.Entity.Task;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface TaskService {

    // Get all Tasks
    List<Task> getAllTasks();

    // Get Specific Task
    Optional<Task> getTaskById(int id);

    // Post new Task
    Task addNewTask(Task task);

    // Update existing Task
    Task updateExistingTask(int id, Task updatedTask);

    // Delete Existing Task
    Task deleteExistingTask(int id);

    // Patch a Task as completed
    Task markTaskAsCompleted(int id);

}
