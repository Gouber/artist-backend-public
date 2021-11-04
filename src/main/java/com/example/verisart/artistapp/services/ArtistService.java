package com.example.verisart.artistapp.services;

import com.example.verisart.artistapp.entities.Artist;
import com.example.verisart.artistapp.repositories.ArtistRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ArtistService {

    @Autowired
    ArtistRepository artistRepository;

    public List<Artist> findAll(){
        return artistRepository.findAll();
    }

    public Artist findById(Long id){
        return artistRepository.findById(id).get();
    }

    public Artist create(Artist artist){
        return artistRepository.save(artist);
    }
}
