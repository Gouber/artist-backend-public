package com.example.verisart.artistapp.controllers;

import com.example.verisart.artistapp.entities.Artist;
import com.example.verisart.artistapp.entities.Certificate;
import com.example.verisart.artistapp.services.ArtistService;
import com.example.verisart.artistapp.services.CertificateService;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.slf4j.Logger;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/certificate")
@CrossOrigin("*")
public class CertificateController {

    private static final Logger LOG = LoggerFactory.getLogger(CertificateController.class);

    @Autowired
    CertificateService certificateService;

    @Autowired
    ArtistService artistService;

    @GetMapping
    public List<Certificate> findAll(){ return certificateService.findAll(); };

    @PostMapping
    public ResponseEntity<Certificate> createCertificate(@RequestBody Certificate certificate){
        try{
            return new ResponseEntity<>(certificateService.create(certificate),HttpStatus.OK);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Certificate> findById(@PathVariable long id){
        try{
            return new ResponseEntity<>(certificateService.findById(id), HttpStatus.OK);
        }catch(NoSuchElementException ex){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping(path="/upload/",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<Certificate> uploadFile(@RequestParam Long id, @RequestParam(value = "file",required = false) MultipartFile file){
        try{
            return new ResponseEntity<>(certificateService.create(certificateService.findById(id),file),HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping(path="/file/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable Long id){
        try{
            Certificate cert = certificateService.findById(id);
            return new ResponseEntity<>(certificateService.downloadImage(cert.getAws_imagePath(),cert.getImageName()), HttpStatus.OK );
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}
