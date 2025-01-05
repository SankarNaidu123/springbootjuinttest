package com.infy.repo;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.infy.model.Movie;

@DataJpaTest
public class MovieRepositoryTest {
	
	@Autowired
	private MovieRepository movieRepository;
	private Movie avatarMovie;
	private Movie titanicMovie;
	@BeforeEach
	void init() {
		avatarMovie = new Movie();
		avatarMovie.setName("Avatar");
		avatarMovie.setGenera("Action");
		avatarMovie.setReleaseDate(LocalDate.of(2000, Month.APRIL, 22));
		
		titanicMovie = new Movie();
		titanicMovie.setName("Avatar");
		titanicMovie.setGenera("Action");
		titanicMovie.setReleaseDate(LocalDate.of(2000, Month.APRIL, 22));
	}
	
	@Test
	void save() {
		//act
		movieRepository.save(avatarMovie);
		//assert
		assertNotNull(avatarMovie);
		assertThat(avatarMovie.getId()).isNotNull();
	}

	@Test
	void getMovieById() {
		//act
		movieRepository.save(avatarMovie);
		//Long id = existingMovie.getId();
		
		Movie movie = movieRepository.findById(avatarMovie.getId()).get();
		//arrange
		assertNotNull(movie);
		assertThat(movie.getId()).isNotNull();
		assertEquals("Action", movie.getGenera());
	}
	
	@Test
	void getAllMovies() {
		movieRepository.save(avatarMovie);
		movieRepository.save(titanicMovie);
		//act
		List<Movie> list = movieRepository.findAll();
		//assert
		assertNotNull(list);
		assertThat(list).isNotNull();
		assertEquals(2, list.size());
	}
	
	//update movie
	@Test
	void updateMovie() {
	   Movie existingMovie = movieRepository.save(avatarMovie);
		//act
	    existingMovie.setGenera("Fantacy");
	    Movie updateMovie = movieRepository.save(existingMovie);
	   //assert
	    assertEquals("Fantacy", updateMovie.getGenera());
	    assertThat(updateMovie.getId()).isNotNull();
	}
	//delete a movie
	@Test
	void deleteMovie() {
		Movie avatar = movieRepository.save(avatarMovie);
		movieRepository.save(titanicMovie);
		Movie id =movieRepository.findById(avatar.getId()).get();
		//act
		movieRepository.delete(id);
		List<Movie> list= movieRepository.findAll();
		//arrange
		assertEquals(1, list.size());
	}
	
	//find movie by genera
	@Test
	void findMovieByGenera() {
		movieRepository.save(avatarMovie);
		//act
		List<Movie> findByGenera = movieRepository.findByGenera("Action");
		//assert 
		assertEquals(1, findByGenera.size());
		assertNotNull(findByGenera);
		
	}
	
	
	
}
