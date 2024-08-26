package omer.solutions.testlvlconsulting.repository;

import omer.solutions.testlvlconsulting.entity.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {

    @Query("SELECT p FROM Project p WHERE p.user.id = :id")
    List<Project> findAllByUser(@Param("id") long id);

    @Query("SELECT p FROM Project p WHERE p.id = :id and p.user.id = :idUser")
    Project findByIdAndUser(@Param("id") long id, @Param("idUser") long idUser);

}
