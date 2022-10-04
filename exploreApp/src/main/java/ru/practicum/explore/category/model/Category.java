package ru.practicum.explore.category.model;

import lombok.*;

import javax.persistence.*;

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
