package io.bibiperich.taskmanager.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.bibiperich.taskmanager.model.Task;

// import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {}