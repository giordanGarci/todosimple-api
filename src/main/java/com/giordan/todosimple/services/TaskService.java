package com.giordan.todosimple.services;

import com.giordan.todosimple.models.Task;
import com.giordan.todosimple.models.User;
import com.giordan.todosimple.repositories.TaskRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(Long id){
        Optional<Task> task = taskRepository.findById(id);
        return task.orElseThrow(() ->  new RuntimeException(
                "Task not found! Id: " + id + ", Type: " + User.class.getName()));
    }

    @Transactional
    public Task create(Task obj){
        User user = userService.findById(obj.getUser().getId());
        obj.setId(null);
        obj.setUser(user);
        obj = taskRepository.save(obj);
        return obj;
    }

    @Transactional
    public Task update(Task obj){
        Task newTask = findById(obj.getId());
        newTask.setDescription(obj.getDescription());
        return taskRepository.save(newTask);
    }

    @Transactional
    public void delete(Long id){
        findById(id);
        try{
            taskRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException("It is not possible to delete as there are related entities");
        }
    }

}
