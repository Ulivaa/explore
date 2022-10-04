package ru.practicum.statistic.model;

import lombok.*;

@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ViewStats {
    private String app;
    private String uri;
    private Integer hits;
}
