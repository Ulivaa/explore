package ru.practicum.statistic.controller;

import org.springframework.web.bind.annotation.*;
import ru.practicum.statistic.model.EndpointHit;
import ru.practicum.statistic.model.ViewStats;
import ru.practicum.statistic.service.StatisticService;
import ru.practicum.statistic.service.StatisticServiceImpl;

import java.util.Collection;
import java.util.List;

@RestController
public class StatisticsController {
    private final StatisticService statisticService;

    public StatisticsController(StatisticService statisticService) {
        this.statisticService = statisticService;
    }

    @PostMapping(path = "/hit")
    public void addHit(@RequestBody EndpointHit endpointHit) {
        statisticService.addHit(endpointHit);
    }

    @GetMapping(path = "/stats")
    public Collection<ViewStats> getStatistics(@RequestParam String start,
                                               @RequestParam String end,
                                               @RequestParam(required = false) List<String> uris,
                                               @RequestParam(defaultValue = "false") boolean unique) {
        return statisticService.getStatistic(start, end, uris, unique);
    }
}
