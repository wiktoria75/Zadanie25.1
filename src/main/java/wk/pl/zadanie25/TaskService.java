package wk.pl.zadanie25;

import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    TaskRepository taskRepository;

    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public void saveTaskAsCompleted(Long id) {
        Optional<Task> byId = taskRepository.findById(id);
        if (byId.isPresent()) {
            Task task = byId.get();
            task.setCompleted(true);
            task.setEndDate(LocalDate.now());
            taskRepository.save(task);
        } else {
            throw new IllegalArgumentException();
        }
    }

    public void saveTask(Task task) {
        taskRepository.save(task);
    }

    public List<Task> findTaskByCompleted(boolean completed) {
        List<Task> tasks;
        if (completed) {
            tasks = taskRepository.findAllByCompletedTrue();
        } else {
            tasks = taskRepository.findAllByCompletedFalse();
        }
        tasks.sort(Task::compareTo);
        return tasks;
    }

    public Task findTaskById(Long id) {
        Optional<Task> byId = taskRepository.findById(id);
        if (byId.isPresent()) {
            return byId.get();
        } else {
            throw new IllegalArgumentException();
        }
    }
}
