package ru.practicum.explore.user.model;

import lombok.*;
import ru.practicum.explore.category.model.Category;

import javax.persistence.*;
import java.util.Set;

@Builder
@Getter
@Setter
@Entity
@Table(name = "users")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String name;
    private String email;
    /*-------------------------------------------- ФИЧА --------------------------------------------*/

    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "category_id"), joinColumns = @JoinColumn(name = "user_id"),
            uniqueConstraints = {@UniqueConstraint(name = "user_category_unique", columnNames = {"user_id", "category_id"})})
    private Set<Category> categories;
    /*-------------------------------------------- ФИЧА --------------------------------------------*/

}
