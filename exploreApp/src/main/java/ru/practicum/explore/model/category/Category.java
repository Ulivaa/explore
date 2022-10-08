package ru.practicum.explore.model.category;

import lombok.*;

import javax.persistence.*;

/**
 * Класс категорий событий
 */
@Builder
@Getter
@Setter
@Entity
@Table(name = "categories")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true)
    private String name;
}
