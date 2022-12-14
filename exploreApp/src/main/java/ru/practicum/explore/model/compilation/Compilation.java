package ru.practicum.explore.model.compilation;

import lombok.*;
import ru.practicum.explore.model.event.Event;

import javax.persistence.*;
import java.util.Set;

/**
 * Класс подборок событий
 */
@Builder
@Getter
@Setter
@Entity
@Table(name = "compilations")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Compilation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Boolean pinned;
    private String title;
    @ManyToMany
    @JoinTable(inverseJoinColumns = @JoinColumn(name = "event_id"), joinColumns = @JoinColumn(name = "compilation_id"),
            uniqueConstraints = {@UniqueConstraint(name = "compilation_event_unique", columnNames = {"event_id", "compilation_id"})})
    private Set<Event> events;
}
