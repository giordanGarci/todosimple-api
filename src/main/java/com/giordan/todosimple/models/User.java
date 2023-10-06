package com.giordan.todosimple.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode
@Entity
@Table(name = User.TABLE_NAME)
public class User {
    public interface CreateUser {
    }

    public interface UpdateUser {
    }

    public static final String TABLE_NAME = "user";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", unique = true)
    @Getter @Setter
    private Long id;

    @Column(name = "username", length = 100, nullable = false, unique = true)
    @NotBlank(groups = CreateUser.class)
    @Size(min = 2, max = 100, groups = CreateUser.class)
    @Getter @Setter
    private String username;

    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    @Column(name = "password", length = 60, nullable = false)
    @NotBlank(groups = {CreateUser.class, UpdateUser.class})
    @Size(min = 8, max = 60, groups = {CreateUser.class, UpdateUser.class})
    @Getter @Setter
    private String password;

    @OneToMany(mappedBy = "user")
    List<Task> tasks = new ArrayList<>();

    @JsonIgnore
    public List<Task> getTasks() {
        return tasks;
    }

}
