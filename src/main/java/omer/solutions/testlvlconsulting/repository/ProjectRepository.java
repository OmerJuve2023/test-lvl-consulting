package omer.solutions.testlvlconsulting.repository;

import omer.solutions.testlvlconsulting.dto.response.ProjectReponse;
import omer.solutions.testlvlconsulting.entity.Project;
import omer.solutions.testlvlconsulting.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
    @Query("SELECT new omer.solutions.testlvlconsulting.dto.response.ProjectReponse(p) FROM Project p WHERE p.user.id = :id")
    List<ProjectReponse> findAllByUser(Long id);

//    @Query("")
//    List<ProjectReponse> findAllFilter();

}
