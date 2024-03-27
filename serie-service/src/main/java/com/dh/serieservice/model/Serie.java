package com.dh.serieservice.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Document(collection = "Series")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Serie {

    @Id
    private String id;
    private String name;
    private String genre;
    private List<Season> seasons = new ArrayList<>();

    public Serie(String name, String genre, List<Season> seasons) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.genre = genre;
        this.seasons = seasons;
    }

    @AllArgsConstructor
    @NoArgsConstructor
    @Setter
    @Getter
    public static class Season {

        private Integer seasonNumber;
        private List<Chapter> chapters = new ArrayList<>();



        @AllArgsConstructor
        @NoArgsConstructor
        @Setter
        @Getter
        public static class Chapter {

            private String name;
            private Integer number;
            private String urlStream;


        }

    }
}
