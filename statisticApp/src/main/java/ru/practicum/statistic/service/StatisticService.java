package ru.practicum.statistic.service;

import org.springframework.stereotype.Service;
import ru.practicum.statistic.model.EndpointHit;
import ru.practicum.statistic.model.HitsRequest;
import ru.practicum.statistic.model.ViewStats;
import ru.practicum.statistic.repository.StatisticRepository;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class StatisticService {
    private final StatisticRepository statisticRepository;

    public StatisticService(StatisticRepository statisticRepository) {
        this.statisticRepository = statisticRepository;
    }

    public void addHit(EndpointHit endpointHit) {
        statisticRepository.save(endpointHit);
    }

    public Collection<ViewStats> getStatistic(String start, String end, List<String> uris, boolean uniq) {
        LocalDateTime stDate = null;
        LocalDateTime eDate = null;
        Collection<ViewStats> viewStatsCollection = new ArrayList<>();
        if (!start.isEmpty() && !end.isEmpty()) {
            stDate = LocalDateTime.parse(URLDecoder.decode(start, StandardCharsets.UTF_8), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
            eDate = LocalDateTime.parse(URLDecoder.decode(end, StandardCharsets.UTF_8), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        }
        for (String uri : uris) {
            HitsRequest hits = statisticRepository.find(stDate, eDate, uri);
            if (hits != null) {
                viewStatsCollection.add(ViewStats.builder().hits(hits.getHits()).app(hits.getApp()).uri(hits.getUri()).build());
            } else {
                viewStatsCollection.add(ViewStats.builder().hits(0).uri(uri).build());
            }

        }
        return viewStatsCollection;
    }
}
