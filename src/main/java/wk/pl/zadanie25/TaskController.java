package wk.pl.zadanie25;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Controller
public class TaskController {

    private TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @GetMapping("/new")
    String form(Model model) {
        model.addAttribute("task", new Task());
        return "form";
    }

    @GetMapping("/edit")
    String form(@RequestParam Long id, Model model) {
        Optional<Task> byId = taskRepository.findById(id);
        byId.ifPresent(task -> model.addAttribute("task", task));
        return "form";
    }

    @GetMapping("/")
    String list(Model model) {
        List<Task> tasksOverdue = taskRepository.findAllByCompletedFalseAndDeadlineDateIsBefore(LocalDate.now());
        List<Task> tasks = taskRepository.findAllByCompletedFalseAndDeadlineDateIsAfter(LocalDate.now());
        tasksOverdue.sort(Task::compareTo);
        tasks.sort(Task::compareTo);
        model.addAttribute("listOverdue", tasksOverdue);
        model.addAttribute("list", tasks);
        return "lista";
    }
    @GetMapping("/archive")
    String archive(Model model) {
        model.addAttribute("tasks", taskRepository.findAllByCompletedTrue());
        return "archive";
    }

    @PostMapping("/save")
    String save(Task task) {
        taskRepository.save(task);
        return "redirect:/";
    }

    @GetMapping("/finished")
    String check(@RequestParam Long id) {
        Optional<Task> byId = taskRepository.findById(id);
        if (byId.isPresent()) {
            Task task = byId.get();
            task.setCompleted(true);
            task.setEndDate(LocalDate.now());
            taskRepository.save(task);
        }
        return "redirect:/";
    }


}
