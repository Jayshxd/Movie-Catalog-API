package org.cfs.movie_catalog_api.service;

import jakarta.persistence.EntityNotFoundException;
import org.cfs.movie_catalog_api.entities.Director;
import org.cfs.movie_catalog_api.repo.DirectorRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DirectorService {

    private final DirectorRepo directorRepo;
    @Autowired
    public DirectorService(DirectorRepo directorRepo){
        this.directorRepo = directorRepo;
    }

    public List<Director> findAll(){
        return directorRepo.findAll();
    }

    public Director findById(Long id){
        return directorRepo.findById(id).orElseThrow(()-> new EntityNotFoundException("Director Not Found")
        );
    }



}
