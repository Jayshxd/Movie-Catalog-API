package org.cfs.movie_catalog_api.repo;

import org.cfs.movie_catalog_api.entities.Director;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DirectorRepo extends JpaRepository<Director,Long> {

}
