package com.infy.repo;

import org.springframework.data.jpa.repository.JpaRepository;

import com.infy.model.Movie;
import java.util.List;


public interface MovieRepository extends JpaRepository<Movie, Long> {
	
	List<Movie> findByGenera(String genera);

}
