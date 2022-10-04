package ru.practicum.statistic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Builder
@Getter
@Setter
@Entity
@Table(name = "statistics")
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EndpointHit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String app;
    private String uri;
    private String ip;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime timestamp;
//
//    @Override
//    public String toString() {
//
//        return "{\"app\": \""+this.app+"\",\n" +
//                "  \"uri\": \""+this.uri+"\",\n" +
//                "  \"ip\": \""+this.id+"\",\n" +
//                "  \"timestamp\": \""+this.timestamp+"\"}";
//    }
}
