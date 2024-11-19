package com.example.restAPI;

import com.example.restAPI.Entity.Task;
import com.example.restAPI.Enum.Status;
import com.example.restAPI.Repository.TaskRepository;
import com.example.restAPI.Service.Implementation.TaskServiceImplementation;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
//import org.mockito.junit.MockitoJUnitRunner;

import java.sql.Timestamp;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

public class TaskServiceImplementationTests {

    @Mock
    private TaskRepository taskRepository;

    @InjectMocks
    private TaskServiceImplementation taskServiceImplementation;

    private Task task;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.openMocks(this);

        // Creating a sample Task for testing
        task = new Task();
        task.setId(1);
        task.setTitle("Test Task");
        task.setDescription("Test Description");
        task.setDue_date(LocalDate.of(2024, 11, 25));
        task.setStatus(Status.pending);
        task.setCreated_at(new Timestamp(System.currentTimeMillis()));
        task.setUpdated_at(task.getCreated_at());
    }

    // Test for getting all Tasks
    @Test
    public void testGetAllTasks() {
        when(taskRepository.findAll()).thenReturn(List.of(task));

        // Act
        var tasks = taskServiceImplementation.getAllTasks();

        // Assert
        assertNotNull(tasks);
        assertEquals(1, tasks.size());
        assertEquals("Test Task", tasks.get(0).getTitle());
    }

    // Test for adding a new Task
    @Test
    public void testAddNewTask() {
        when(taskRepository.save(any(Task.class))).thenReturn(task);

        // Act
        Task createdTask = taskServiceImplementation.addNewTask(task);

        // Assert
        assertNotNull(createdTask);
        assertEquals("Test Task", createdTask.getTitle());
        assertEquals(Status.pending, createdTask.getStatus());
    }

    // Test for deleting a Task
    @Test
    public void testDeleteExistingTask() {
        when(taskRepository.findById(1)).thenReturn(Optional.of(task));
        doNothing().when(taskRepository).deleteById(1);

        // Act
        Task result = taskServiceImplementation.deleteExistingTask(1);

        // Assert
        assertNotNull(result);
        assertEquals(1, result.getId());
        verify(taskRepository, times(1)).deleteById(1);
    }
}
