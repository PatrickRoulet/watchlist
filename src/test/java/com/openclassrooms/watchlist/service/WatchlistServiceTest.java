package com.openclassrooms.watchlist.service;

import static org.mockito.ArgumentMatchers.any;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.openclassrooms.watchlist.domain.WatchlistItem;
import com.openclassrooms.watchlist.repository.WatchlistRepository;

@RunWith(MockitoJUnitRunner.class)
public class WatchlistServiceTest {
	
	@InjectMocks
	private WatchlistService watchlistService;
	
	@Mock
	private WatchlistRepository watchlistRepositoryMock;
	
	@Mock
	private MovieRatingServiceLiveImpl movieRatingServiceMock;
	
	@Test
	public void testGetWatchlistItemsReturnsAllItemsFromRepository() {
		
		//Arrange
		WatchlistItem item1 = new WatchlistItem("Star Wars", "7.7", "M" , "");
		WatchlistItem item2 = new WatchlistItem("Star Treck", "8.8", "M" , "");
		List<WatchlistItem> mockItems = Arrays.asList(item1, item2);
		
		when(watchlistRepositoryMock.getList()).thenReturn(mockItems);
		
		//Act
		List<WatchlistItem> result = watchlistService.getWatchlistItems();
		
		//Assert
		assertTrue(result.size() == 2);
		assertTrue(result.get(0).getTitle().equals("Star Wars"));
		assertTrue(result.get(1).getTitle().equals("Star Treck"));
	}
	
	@Test
	public void testGetwatchlistItemsRatingFormOmdbServiceOverrideTheValueInItems() {
		
		//Arrange
		WatchlistItem item1 = new WatchlistItem("Star Wars", "7.7", "M" , "");
		List<WatchlistItem> mockItems = Arrays.asList(item1);
		
		when(watchlistRepositoryMock.getList()).thenReturn(mockItems);	
		when(movieRatingServiceMock.getMovieRating(any(String.class))).thenReturn("10");
		
		//Act
		List<WatchlistItem> result = watchlistService.getWatchlistItems();
		
		//Assert
		assertTrue(result.get(0).getRating().equals("10"));
	}
	
}