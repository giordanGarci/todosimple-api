package com.giordan.todosimple.services;

import com.giordan.todosimple.models.User;
import com.giordan.todosimple.repositories.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new RuntimeException(
                "User not found! Id: " + id + ", Type: " + User.class.getName())
        );
    }

    @Transactional
    public User create(User obj){
        obj.setId(null);
        obj = userRepository.save(obj);
        return obj;
    }

    @Transactional
    public User update(User obj){
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        return userRepository.save(newObj);
    }

    @Transactional
    public void delete(Long id){
       findById(id);
        try {
            userRepository.deleteById(id);
        }catch (Exception e){
            throw new RuntimeException(
                    "It is not possible to delete as there are related entities");
        }
    }
}
