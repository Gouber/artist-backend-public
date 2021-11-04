package com.example.verisart.artistapp.services;

import com.example.verisart.artistapp.config.Bucket;
import com.example.verisart.artistapp.entities.Artist;
import com.example.verisart.artistapp.entities.Certificate;
import com.example.verisart.artistapp.repositories.ArtistRepository;
import com.example.verisart.artistapp.repositories.CertificateRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.*;

import static org.apache.http.entity.ContentType.*;

@Service
public class CertificateService {

    @Autowired
    CertificateRepository certificateRepository;
    @Autowired
    ArtistRepository artistRepository;
    @Autowired
    FileStore fileStore;

    public List<Certificate> findAll(){
        return certificateRepository.findAll();
    }

    public Certificate findById(Long id){
        return certificateRepository.findById(id).get();
    }

    public Certificate create(Certificate artist){
        return certificateRepository.save(artist);
    }

    public List<Certificate> findByAristId(Long id) {
        return certificateRepository.findCertificatesByArtist_Id(id);
    }

    public Certificate create(Certificate certificate, MultipartFile file){
        if (file.isEmpty()) {
            throw new IllegalStateException("Cannot upload empty file");
        }
        if (!Arrays.asList(IMAGE_PNG.getMimeType(),
                IMAGE_BMP.getMimeType(),
                IMAGE_GIF.getMimeType(),
                IMAGE_JPEG.getMimeType()).contains(file.getContentType())) {
            throw new IllegalStateException("File uploaded is not an image");
        }
        Map<String, String> metadata = new HashMap<>();
        metadata.put("Content-Type", file.getContentType());
        metadata.put("Content-Length", String.valueOf(file.getSize()));
        String path = String.format("%s/%s", Bucket.ARTIST_BUCKET.getBucketName(), UUID.randomUUID());
        String fileName = String.format("%s", file.getOriginalFilename());
        try {
            fileStore.upload(path, fileName, Optional.of(metadata), file.getInputStream());
        } catch (IOException e) {
            throw new IllegalStateException("Failed to upload file", e);
        }
        certificate.setAws_imagePath(path);
        certificate.setImageName(fileName);
        return certificateRepository.save(certificate);
    }

    public byte[] downloadImage(String path, String fileName){
        return fileStore.download(path,fileName);
    }
}
