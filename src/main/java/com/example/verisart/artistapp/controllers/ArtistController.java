package com.example.verisart.artistapp.controllers;

import com.example.verisart.artistapp.entities.Artist;
import com.example.verisart.artistapp.entities.Certificate;
import com.example.verisart.artistapp.services.ArtistService;
import com.example.verisart.artistapp.services.CertificateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/artist")
@CrossOrigin("*")
public class ArtistController {

    private static final Logger LOG = LoggerFactory.getLogger(Artist.class);

    @Autowired
    ArtistService artistService;

    @Autowired
    CertificateService certificateService;

    @GetMapping
    public List<Artist> findAll(){ return artistService.findAll(); };

    @GetMapping("/{id}")
    public ResponseEntity<Artist> findById(@PathVariable long id){
        try{
            return new ResponseEntity<>(artistService.findById(id) , HttpStatus.OK );
        }catch(NoSuchElementException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<Artist> create(@RequestBody Artist artist){
        return new ResponseEntity<>(artistService.create(artist), HttpStatus.CREATED);
    }

    @GetMapping("/{id}/certs")
    public ResponseEntity<List<Certificate>> findByArtist(@PathVariable long id){
        try{
            return new ResponseEntity<>(certificateService.findByAristId(id), HttpStatus.OK);
        }catch(NoSuchElementException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

}
