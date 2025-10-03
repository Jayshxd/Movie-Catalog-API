package org.cfs.movie_catalog_api.service;

import jakarta.persistence.EntityNotFoundException;
import org.cfs.movie_catalog_api.dto.MovieDTO;
import org.cfs.movie_catalog_api.entities.Director;
import org.cfs.movie_catalog_api.entities.Movie;
import org.cfs.movie_catalog_api.repo.DirectorRepo;
import org.cfs.movie_catalog_api.repo.MovieRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MovieService {
    private final MovieRepo movieRepo;
    private final DirectorRepo directorRepo;

    @Autowired
    MovieService(MovieRepo movieRepo, DirectorRepo directorRepo) {
        this.movieRepo = movieRepo;
        this.directorRepo = directorRepo;
    }

    public List<Movie> getAllMovies(){
        return movieRepo.findAll();
    }

    public Movie addMovie(MovieDTO movieDTO){
        Director director = directorRepo.findById(movieDTO.getDirectorId()).orElseThrow(EntityNotFoundException::new);
        Movie movie = new Movie();
        movie.setTitle(movieDTO.getTitle());
        movie.setGenre(movieDTO.getGenre());
        movie.setReleaseYear(movieDTO.getReleaseYear());
        movie.setDirector(director);
        return movieRepo.save(movie);
    }

    public Movie getMovieById(Long id){
        return movieRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("Movie Not Found"));
    }

    public Movie updateMovie(Long id,Movie movie){
        Movie oldMovie = movieRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("Movie Not Found"));
        oldMovie.setTitle(movie.getTitle());
        oldMovie.setGenre(movie.getGenre());
        oldMovie.setReleaseYear(movie.getReleaseYear());
        movieRepo.save(oldMovie);
        return oldMovie;
    }

    public Movie updateTitle(Long id, MovieDTO  movieDTO){
        Movie movie = movieRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("Movie Not Found"));
        if(movieDTO.getTitle()!=null){
            movie.setTitle(movieDTO.getTitle());
        }
        if(movieDTO.getGenre()!=null){
            movie.setGenre(movieDTO.getGenre());
        }
        return movieRepo.save(movie);
    }


    public void deleteMovie(Long id){
        movieRepo.deleteById(id);
    }

    public List<Movie> findMoviesByGenreAndReleaseYear(String genre,Long releaseYear){
        return movieRepo.findByGenreAndReleaseYear(genre,releaseYear);
    }





}
