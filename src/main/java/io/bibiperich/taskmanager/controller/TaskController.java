package io.bibiperich.taskmanager.controller;

import java.util.List;

// import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.bibiperich.taskmanager.model.Task;
import io.bibiperich.taskmanager.repository.TaskRepository;

@RestController
@RequestMapping("/api/tasks")
public class TaskController {
    
    private final TaskRepository repository;

    public TaskController(TaskRepository repository) {
        this.repository = repository;
    }

    @GetMapping
    public ResponseEntity<List<Task>> findAll() {
        return ResponseEntity.ok(repository.findAll());
    }

    @PostMapping
    public Task createTask(@RequestBody Task task) {
        return repository.save(task);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateTask(@PathVariable Long id, @RequestBody Task taskData) {
        return repository.findById(id)
            .map(task -> {
                task.setTitle(taskData.getTitle());
                task.setDescription(taskData.getDescription());
                task.setResponsible(taskData.getResponsible());
                task.setCompleted(taskData.getCompleted());
                repository.save(task);
                return ResponseEntity.noContent().build();
            }).orElse(ResponseEntity.notFound().build());
        }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteTask(@PathVariable Long id) {
        return repository.findById(id)
            .map(task -> {
                repository.delete(task);
                return ResponseEntity.noContent().build();
            }).orElse(ResponseEntity.notFound().build());
    }
}