package com.openclassrooms.watchlist.service;

import java.io.IOException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

@ConditionalOnProperty(name = "app.env", havingValue = "prod")
@Service
public class MovieRatingServiceLiveImpl implements MovieRatingService {
	
	Logger logger = LoggerFactory.getLogger(MovieRatingServiceLiveImpl.class);
	
	private String apiUrl = "http://www.omdbapi.com/?apikey=cc9bf9ef&t=";	
	
	@Override
	public String getMovieRating(String title) { 
		
		RestTemplate template = new RestTemplate();
		
		logger.debug("Calling omdbapi with url {} and title {}", apiUrl , title);
		
		ResponseEntity<String> response = template.getForEntity(apiUrl + title , String.class);
		
		try {
			JsonNode node = new ObjectMapper().readTree(response.getBody()).path("imdbRating");
			if (node.isMissingNode()) {
				logger.warn("imdbRating node is missing, returning empty.");
			}
			return node.asText();
		} catch (IOException e) {
			logger.error("ERROR! Exception happened!",e);
			return null;
		}
	}
}
