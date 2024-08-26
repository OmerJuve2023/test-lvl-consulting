package omer.solutions.testlvlconsulting.repository;

import omer.solutions.testlvlconsulting.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    @Query("SELECT t FROM Task t WHERE t.id = :id and t.proyecto.user.id = :idUser")
    Task findByIdAndUser(Long id, Long idUser);

    @Query("SELECT t FROM Task t WHERE t.proyecto.id = :id")
    List<Task> findAllByUser(Long id);

    @Query("SELECT t FROM Task t WHERE t.proyecto.user.id = :projectId")
    List<Task> findAllByProjectId(Long projectId);


    @Query("SELECT t FROM Task t WHERE " +
            "LOWER(t.codigo) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.nombre) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.proyecto.nombre) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.proyecto.descripcion) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.proyecto.user.username) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.proyecto.user.email) LIKE LOWER(CONCAT('%', :keyword, '%')) OR " +
            "LOWER(t.categoria) LIKE LOWER(CONCAT('%', :keyword, '%'))")
    List<Task> findByKeyword(@Param("keyword") String keyword);

}
