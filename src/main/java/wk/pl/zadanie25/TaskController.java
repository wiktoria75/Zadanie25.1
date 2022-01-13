package wk.pl.zadanie25;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.Mapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.time.LocalDate;

@Controller
public class TaskController {

    private TaskRepository taskRepository;

    public TaskController(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

//    @GetMapping("form")
//    String form(Model model) {
//        model.addAttribute("task", new Task());
//        return "form";
//    }

    @GetMapping("/list")
    String list(Model model) {
        taskRepository.save(new Task(LocalDate.now(), LocalDate.now().plusDays(5), null,"nowe zadanie", false));
        model.addAttribute("list", taskRepository.findAllByCompletedFalse());
        return "lista";
    }


}
