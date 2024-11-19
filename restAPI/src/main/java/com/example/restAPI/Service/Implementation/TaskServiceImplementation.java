package com.example.restAPI.Service.Implementation;

import com.example.restAPI.Entity.Task;
import com.example.restAPI.Enum.Status;
import com.example.restAPI.Repository.TaskRepository;
import com.example.restAPI.Service.TaskService;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImplementation implements TaskService {

    private final TaskRepository taskRepository;

    public TaskServiceImplementation(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    // Get all Tasks
    @Override
    public List<Task> getAllTasks() {
        return taskRepository.findAll();
    }

    // Get Task by Id
    @Override
    public Optional<Task> getTaskById(int id) {
        return taskRepository.findById(id);
    }

    // Post new Task
    @Override
    public Task addNewTask(Task task) {
        Task newTask = new Task();
        newTask.setTitle(task.getTitle());
        newTask.setDescription(task.getDescription());
        newTask.setDue_date(task.getDue_date());
        newTask.setStatus(Status.pending);
        newTask.setCreated_at(new Timestamp(System.currentTimeMillis()));
        newTask.setUpdated_at(newTask.getCreated_at());
        return taskRepository.save(newTask);
    }

    // Update existing Task
    @Override
    public Task updateExistingTask(int id, Task updatedTask) {
//        Task fetchedTask = taskRepository.findById()
        return taskRepository.findById(id).map(task -> {
            task.setTitle(updatedTask.getTitle());
            task.setDescription((updatedTask.getDescription()));
            task.setDue_date(updatedTask.getDue_date());
            task.setUpdated_at(new Timestamp(System.currentTimeMillis()));
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Task not found"));
    }

    @Override
    public Task deleteExistingTask(int id) {
        Task fetchedTask = taskRepository.findById(id).
                orElseThrow(() -> new RuntimeException("Task not found with id: " + id));
        taskRepository.deleteById(id);
        return fetchedTask;
    }

    @Override
    public Task markTaskAsCompleted(int id) {
        return taskRepository.findById(id).map(task -> {
            task.setStatus(Status.completed);
            task.setUpdated_at(new Timestamp(System.currentTimeMillis()));
            return taskRepository.save(task);
        }).orElseThrow(() -> new RuntimeException("Task not found"));
    }
}
