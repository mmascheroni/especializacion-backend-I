package com.dh.catalogservice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
@JsonIgnoreProperties(ignoreUnknown = true)
public class Serie {
    private String id;
    private String name;
    private String genre;
    private List<Season> seasons = new ArrayList<>();

    public Serie(String name, String genre, List<Season> seasons) {
        this.name = name;
        this.genre = genre;
        this.seasons = seasons;
    }

    @Data
    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    @JsonIgnoreProperties(ignoreUnknown = true)
    public static class Season {

        private Integer seasonNumber;
        private List<Chapter> chapters = new ArrayList<>();

        @Data
        @AllArgsConstructor
        @NoArgsConstructor
        @Setter
        @Getter
        @JsonIgnoreProperties(ignoreUnknown = true)
        public static class Chapter {

            private String name;
            private Integer number;
            private String urlStream;


        }

    }
}