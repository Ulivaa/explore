package ru.practicum.explore.model.request.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import ru.practicum.explore.model.request.Status;

import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@RequiredArgsConstructor
public class RequestDto {
    private Long id;
    @NotNull
    private Long eventId;
    @NotNull
    private Long requesterId;
    private Status status;
    private LocalDateTime created;
}
