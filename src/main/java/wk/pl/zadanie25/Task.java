package wk.pl.zadanie25;

import jdk.jfr.BooleanFlag;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.time.LocalDate;

@Entity
public class Task implements Comparable<Task> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate startDate;

    @DateTimeFormat(pattern="yyyy-MM-dd")
    private LocalDate endDate;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate deadlineDate;

    private String name;

    private Boolean completed;

    public Task() {
        this.completed = false;
        this.startDate = LocalDate.now();
    }

    public Task(LocalDate startDate, LocalDate deadlineDate, String name) {
        this.startDate = startDate;
        this.deadlineDate = deadlineDate;
        this.name = name;
    }

    public Task(LocalDate startDate, LocalDate endDate, LocalDate deadlineDate, String name, Boolean completed) {
        this.startDate = startDate;
        this.endDate = endDate;
        this.deadlineDate = deadlineDate;
        this.name = name;
        this.completed = completed;
    }





    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public LocalDate getDeadlineDate() {
        return deadlineDate;
    }

    public void setDeadlineDate(LocalDate deadlineDate) {
        this.deadlineDate = deadlineDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getCompleted() {
        return completed;
    }

    public void setCompleted(Boolean completed) {
        this.completed = completed;
    }

    @Override
    public int compareTo(Task task) {
        if (this.deadlineDate.isAfter(task.deadlineDate)) {
            return 1;
        } else if (this.deadlineDate.isBefore(task.deadlineDate)) {
            return -1;
        }
        return 0;
    }
}
