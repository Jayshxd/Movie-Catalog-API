package org.cfs.movie_catalog_api.controller;

import org.cfs.movie_catalog_api.dto.MovieDTO;
import org.cfs.movie_catalog_api.entities.Movie;
import org.cfs.movie_catalog_api.repo.MovieRepo;
import org.cfs.movie_catalog_api.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movies")
public class MovieController {
    private final MovieService movieService;

    @Autowired
    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }


    @GetMapping
    public ResponseEntity<List<Movie>> allMovies(){
        List<Movie> movies = movieService.getAllMovies();
        if(movies.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(movies);
    }

    @PostMapping
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie){
        Movie newMovie = movieService.addMovie(movie);
        return ResponseEntity.status(HttpStatus.CREATED).body(newMovie);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Movie> updateMovie(@PathVariable Long id,@RequestBody Movie movie){
        Movie newMovie = movieService.updateMovie(id,movie);
        return ResponseEntity.status(HttpStatus.OK).body(newMovie);
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Movie> updateTitle(@PathVariable Long id,@RequestBody MovieDTO movieDTO){
        Movie newMovie = movieService.updateTitle(id, movieDTO);
        return ResponseEntity.status(HttpStatus.OK).body(newMovie);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id){
        movieService.deleteMovie(id);
        return ResponseEntity.status(HttpStatus.OK).body(null);
    }


    @GetMapping("/search")
    public ResponseEntity<List<Movie>> searchMovie(@RequestParam String genre,@RequestParam Long releaseYear){
        List<Movie> temp = movieService.findMoviesByGenreAndReleaseYear(genre,releaseYear);
        if(temp.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
        }
        return ResponseEntity.status(HttpStatus.OK).body(temp);
    }


}
