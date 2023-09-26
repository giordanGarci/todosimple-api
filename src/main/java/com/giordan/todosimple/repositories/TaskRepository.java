package com.giordan.todosimple.repositories;

import com.giordan.todosimple.models.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
      List<Task> findByUser_Id(Long user_id); // Derived query method

//    @Query("SELECT t FROM Task t WHERE t.user.id = :user_id") // Automated Query with Spring
//    List<Task> findByUser_Id(@Param("user_id") Long user_id);

//    @Query(value = "SELECT * FROM task t WHERE t.user_id = :id", nativeQuery = true) // Native SQL query
//    List<Task> findByUser_Id(@Param("user_id)") Long user_id);
}
