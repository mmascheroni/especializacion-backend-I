package com.dh.catalogservice;

import com.dh.catalogservice.configuration.LoadBalancerConfiguration;
import com.dh.catalogservice.model.Catalog;
import com.dh.catalogservice.model.Serie;
import com.dh.catalogservice.repository.CatalogRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClient;
import org.springframework.cloud.loadbalancer.annotation.LoadBalancerClients;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@LoadBalancerClients(value = {
		@LoadBalancerClient(name = "movie-service", configuration = LoadBalancerConfiguration.class),
		@LoadBalancerClient(name = "serie-service", configuration = LoadBalancerConfiguration.class)
})
public class CatalogServiceApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatalogServiceApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(CatalogRepository repository) {
		String baseUrl = "www.netflix.com/series";

		return (args) -> {
			if (!repository.findAll().isEmpty()) {
				return;
			}

			//Serie A terror
			List<Serie.Season.Chapter> serieASeasonAChapters = List.of(
					new Serie.Season.Chapter("Chapter A", 1, baseUrl + "/terror/1/season/1/chapter/1"),
					new Serie.Season.Chapter("Chapter B", 2, baseUrl + "/terror/1/season/1/chapter/2")
			);

			List<Serie.Season.Chapter> serieASeasonBChapters = List.of(
					new Serie.Season.Chapter("Chapter A", 1, baseUrl + "/terror/1/season/2/chapter/1"),
					new Serie.Season.Chapter("Chapter B", 2, baseUrl + "/terror/1/season/2/chapter/2")
			);

			List<Serie.Season> serieASeasons = List.of(
					new Serie.Season(1, serieASeasonAChapters),
					new Serie.Season(2, serieASeasonBChapters)
			);

			//Serie B comedia
			List<Serie.Season.Chapter> serieBSeasonAChapters = List.of(
					new Serie.Season.Chapter("Chapter A", 1, baseUrl + "/comedia/1/season/1/chapter/1"),
					new Serie.Season.Chapter("Chapter B", 2, baseUrl + "/comedia/1/season/1/chapter/2")
			);

			List<Serie.Season.Chapter> serieBSeasonBChapters = List.of(
					new Serie.Season.Chapter("Chapter A", 1, baseUrl + "/comedia/1/season/2/chapter/1"),
					new Serie.Season.Chapter("Chapter B", 2, baseUrl + "/comedia/1/season/2/chapter/2")
			);

			List<Serie.Season> serieBSeasons = List.of(
					new Serie.Season(1, serieBSeasonAChapters),
					new Serie.Season(2, serieBSeasonBChapters)
			);

			Serie serieA = new Serie("Serie A", "terror", serieASeasons);
			Serie serieB = new Serie("Serie B", "comedia", serieBSeasons);

			Catalog catalogA = new Catalog(serieA);
			Catalog catalogB = new Catalog(serieB);
			repository.save(catalogA);
			repository.save(catalogB);

		};
	}

}
