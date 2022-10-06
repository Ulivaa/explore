package ru.practicum.explore.event.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.*;
import ru.practicum.explore.category.model.Category;
import ru.practicum.explore.user.model.UserShortDto;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class EventFullDto {
    public Long id;
    @NotNull
    @Size(min = 20, max = 2000)
    private String annotation;
    @NotNull
    private Category category;
    private Long confirmedRequests;
    private LocalDateTime createdOn;
    @Size(min = 20, max = 7000)
    @NotNull
    private String description;
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime eventDate;
    private UserShortDto initiator;
    @NotNull
    Location location;
    private Boolean paid;
    private Long participantLimit;
    @JsonInclude(JsonInclude.Include.NON_EMPTY)
    private LocalDateTime publishedOn;
    private Boolean requestModeration;
    private State state;
    @NotNull
    @Size(min = 3, max = 120)
    private String title;
    private Long views;

    @Data
    public static class Location {
        private final double lat;
        private final double lon;
    }
}

