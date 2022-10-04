//package ru.practicum.explore.event.controller;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.web.client.RestTemplateBuilder;
//import org.springframework.http.ResponseEntity;
//import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
//import org.springframework.stereotype.Service;
//import org.springframework.web.client.RestTemplate;
//import org.springframework.web.util.DefaultUriBuilderFactory;
//import ru.practicum.explore.client.BaseClient;
//import ru.practicum.explore.statistic.EndpointHit;
//
//import java.util.Map;
//@Service
//public class EventClient extends BaseClient {
//    private static final String API_PREFIX = "/hit";
//
//
//    @Autowired
//    public EventClient(@Value("${statistic-server.url}") String serverUrl, RestTemplateBuilder builder) {
//        super(
//                builder
//                        .uriTemplateHandler(new DefaultUriBuilderFactory(serverUrl + API_PREFIX))
//                        .requestFactory(HttpComponentsClientHttpRequestFactory::new)
//                        .build()
//        );
//    }
//
//    public ResponseEntity<Object> saveHit(EndpointHit endpointHit) {
//        return post("", endpointHit);
//    }
//
//}
