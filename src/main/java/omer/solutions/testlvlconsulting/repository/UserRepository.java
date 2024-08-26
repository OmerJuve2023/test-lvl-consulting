package omer.solutions.testlvlconsulting.repository;

import omer.solutions.testlvlconsulting.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByUsername(String username);

    User findByEmail(String email);

    @Modifying
    @Query("delete from Role u where u.id = :id")
    void deleteUserRole(@Param("id") Long id);

    @Modifying
    @Query("delete from Project u where u.user.id = :id")
    void deleteUserProject(@Param("id") Long id);

    @Modifying
    @Query("delete from Task u where u.id = :id")
    void deleteUserTask(@Param("id") Long id);

}
