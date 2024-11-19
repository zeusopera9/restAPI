package com.example.restAPI.Controller;

import com.example.restAPI.Entity.Task;
import com.example.restAPI.Service.TaskService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/api/v1")
public class TaskController {


    // Implement the logic for the controller endpoints
    private final TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    // GET all Tasks
    @GetMapping("/tasks")
    public ResponseEntity<List<Task>> getAllTasks() {
        return new ResponseEntity<>(taskService.getAllTasks(), HttpStatus.OK);
    }

    // Get a specific Task
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Optional<Task>> getTaskById(@PathVariable int id) {
        Optional<Task> task = taskService.getTaskById(id);
        if(task != null) {
            return new ResponseEntity<>(task, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

//        try {
//            Task task = taskService.getTaskById(id);
//            return new ResponseEntity<>(task, HttpStatus.OK);
//        } catch(Error e) {
//            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
//        }
    }

    // Post new Task
    @PostMapping("/tasks")
    public ResponseEntity<Task> addNewTask(@RequestBody Task task) {
        // Add new Task
        return new ResponseEntity<>(taskService.addNewTask(task), HttpStatus.CREATED);
    }

    // Update existing Task
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateExistingTask(@PathVariable int id, @RequestBody Task task) {
        try {
            return new ResponseEntity<>(taskService.updateExistingTask(id, task), HttpStatus.OK);
        } catch(RuntimeException ex) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    // Delete an existing Task
    @DeleteMapping("/tasks/{id}")
    public ResponseEntity<Task> deleteExistingTask(@PathVariable int id) {
        try {
            return new ResponseEntity<>(taskService.deleteExistingTask(id), HttpStatus.NO_CONTENT);
        } catch(RuntimeException ex) {
            return new ResponseEntity<>(new Task(), HttpStatus.NOT_FOUND);
        }
    }

    // Patch a task as completed
    @PatchMapping("/tasks/{id}")
    public ResponseEntity<Task> markTaskAsCompleted(@PathVariable int id) {
        try {
            return new ResponseEntity<>(taskService.markTaskAsCompleted(id), HttpStatus.OK);
        } catch(RuntimeException ex) {
            return new ResponseEntity<>(new Task(), HttpStatus.NOT_FOUND);
        }
    }

}
