package ru.practicum.explore.model.user;

import lombok.*;

import javax.persistence.*;

/**
 * Класс пользователя
 */
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
}
