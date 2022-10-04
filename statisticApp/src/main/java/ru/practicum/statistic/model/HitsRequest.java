package ru.practicum.statistic.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.Table;
//
//@Builder
//@Getter
//@Setter
//@AllArgsConstructor
//@NoArgsConstructor(access = AccessLevel.PROTECTED)
public interface HitsRequest {
    int getHits();
    String getApp();
    String getUri();
}
