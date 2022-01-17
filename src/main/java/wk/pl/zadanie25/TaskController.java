package wk.pl.zadanie25;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Controller
public class TaskController {

    private TaskService taskService;

    public TaskController(TaskService taskService) {
        this.taskService = taskService;
    }

    @GetMapping("/new")
    String form(Model model) {
        model.addAttribute("task", new Task());
        return "form";
    }

    @GetMapping("/edit")
    String form(@RequestParam Long id, Model model) {
        model.addAttribute("task", taskService.findTaskById(id));
        return "form";
    }

    @GetMapping("/")
    String list(Model model) {
        model.addAttribute("list", taskService.findTaskByCompleted(false));
        model.addAttribute("today", LocalDate.now());
        return "list";
    }

    @GetMapping("/archive")
    String archive(Model model) {
        model.addAttribute("tasks", taskService.findTaskByCompleted(true));
        return "archive";
    }

    @PostMapping("/save")
    String save(Task task) {
        taskService.saveTask(task);
        return "redirect:/";
    }

    @GetMapping("/finished")
    String check(@RequestParam Long id) {
        taskService.saveTaskAsCompleted(id);
        return "redirect:/";
    }
}
