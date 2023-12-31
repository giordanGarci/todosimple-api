package com.giordan.todosimple.services;

import com.giordan.todosimple.models.Task;
import com.giordan.todosimple.models.User;
import com.giordan.todosimple.repositories.TaskRepository;
import com.giordan.todosimple.services.exceptions.DataBindingViolationException;
import com.giordan.todosimple.services.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserService userService;

    public Task findById(Long id){
        Optional<Task> task = taskRepository.findById(id);
        return task.orElseThrow(() -> new ObjectNotFoundException(
                "Task not found! Id: " + id + ", Type: " + Task.class.getName()));
    }


    public List<Task> findAllByUserId(Long id){
        List<Task> tasks = taskRepository.findByUser_Id(id);
        return tasks;
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
            throw new DataBindingViolationException("It is not possible to delete as there are related entities");
        }
    }

}
