package ru.practicum.statistic.service;

import ru.practicum.statistic.model.EndpointHit;
import ru.practicum.statistic.model.ViewStats;

import java.util.Collection;
import java.util.List;

public interface StatisticService {
    void addHit(EndpointHit endpointHit);

    Collection<ViewStats> getStatistic(String start, String end, List<String> uris, boolean uniq);
}
