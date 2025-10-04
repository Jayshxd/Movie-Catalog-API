package org.cfs.movie_catalog_api.repo;

import org.cfs.movie_catalog_api.entities.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepo extends JpaRepository<Movie,Long> {

    public List<Movie> findByGenreAndReleaseYear(String genre,Long releaseYear);
    public List<Movie> findByGenre(String genre);
    public List<Movie> findByReleaseYear(Long releaseYear);
    List<Movie> findByReleaseYearGreaterThan(Long releaseYear);

}
