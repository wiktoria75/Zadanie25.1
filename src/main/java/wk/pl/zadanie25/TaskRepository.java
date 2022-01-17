package wk.pl.zadanie25;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findAll();
    List<Task> findAllByCompletedFalseAndDeadlineDateIsBefore(LocalDate localDate);
    List<Task> findAllByCompletedFalseAndDeadlineDateIsAfter(LocalDate localDate);
    List<Task> findAllByCompletedTrue();
}
