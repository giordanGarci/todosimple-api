package com.giordan.todosimple.services;

import com.giordan.todosimple.models.User;
import com.giordan.todosimple.models.enums.ProfileEnum;
import com.giordan.todosimple.repositories.UserRepository;
import com.giordan.todosimple.services.exceptions.DataBindingViolationException;
import com.giordan.todosimple.services.exceptions.ObjectNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class UserService {

    private BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

    @Autowired
    private UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public User findById(Long id){
        Optional<User> user = userRepository.findById(id);
        return user.orElseThrow(() -> new ObjectNotFoundException(
                "User not found! Id: " + id + ", Type: " + User.class.getName())
        );
    }

    @Transactional
    public User create(User obj){
        obj.setId(null);
        obj.setPassword(this.bCryptPasswordEncoder.encode(obj.getPassword()));
        obj.setProfiles(Stream.of(ProfileEnum.USER.getCode()).collect(Collectors.toSet()));
        obj = userRepository.save(obj);
        return obj;
    }

    @Transactional
    public User update(User obj){
        User newObj = findById(obj.getId());
        newObj.setPassword(obj.getPassword());
        newObj.setPassword(bCryptPasswordEncoder.encode(obj.getPassword()));
        return userRepository.save(newObj);
    }

    @Transactional
    public void delete(Long id){
       findById(id);
        try {
            userRepository.deleteById(id);
        }catch (Exception e){
            throw new DataBindingViolationException(
                    "It is not possible to delete as there are related entities");
        }
    }
}
