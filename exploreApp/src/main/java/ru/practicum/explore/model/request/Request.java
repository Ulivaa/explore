package ru.practicum.explore.model.request;

import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * Класс запроса на участие в событии
 */
@Builder
@Getter
@Setter
@Entity
@Table(name = "participation_requests")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Request {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long eventId;
    private Long requesterId;
    private Status status;
    private LocalDateTime created;
}
