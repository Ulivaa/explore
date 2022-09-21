package ru.practicum.explore.event.model;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import ru.practicum.explore.category.model.Category;
import ru.practicum.explore.user.model.User;

import javax.persistence.*;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity
@Table(name = "events")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Event {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 120)
    @Size(min = 20, max = 2000)
    private String annotation;
    @OneToOne
    @JoinColumn(name = "category_id")
    private Category category;
    private Long confirmedRequests;
//    @Column(insertable = false, updatable = false)
    private LocalDateTime createdOn;
    @Column(length = 120)
    @Size(min = 20, max = 7000)
    private String description;
    private LocalDateTime eventDate;
    @OneToOne
    @JoinColumn(name = "initiator_id")
    private User initiator;
    private Double lat;
    private Double lon;
    private Boolean paid;
    private Long participantLimit;
    private LocalDateTime publishedOn;
    private Boolean requestModeration;
    @Enumerated(EnumType.STRING)
    private State state;
    @Column(length = 120)
    @Size(min = 3, max = 120)
    private String title;
    private Long views;


}
