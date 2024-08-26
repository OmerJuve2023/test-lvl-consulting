package omer.solutions.testlvlconsulting.repository;

import omer.solutions.testlvlconsulting.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.id = :id and t.proyecto.user.id = :idUser")
    Task findByIdAndUser(Long id, Long idUser);

    @Query("SELECT t FROM Task t WHERE t.proyecto.user.id = :id")
    List<Task> findAllByUser(Long id);

}
