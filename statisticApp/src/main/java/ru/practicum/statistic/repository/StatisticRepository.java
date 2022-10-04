package ru.practicum.statistic.repository;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import ru.practicum.statistic.model.EndpointHit;
import ru.practicum.statistic.model.HitsRequest;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;

public interface StatisticRepository extends JpaRepository<EndpointHit, Long> {

    @Query(value = "SELECT count(s.id) as hits, s.app as app, s.uri as uri  FROM statistics s " +
            "WHERE " +
//            "(s.app = :app) and " +
            " (s.timestamp between :start and :end)" +
            " and (s.uri = :uri)  group by s.app, s.uri", nativeQuery = true)
    HitsRequest find(LocalDateTime start, LocalDateTime end, String uri);
}
