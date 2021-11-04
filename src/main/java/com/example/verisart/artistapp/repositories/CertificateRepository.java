package com.example.verisart.artistapp.repositories;

import com.example.verisart.artistapp.entities.Certificate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CertificateRepository extends JpaRepository<Certificate,Long> {

    List<Certificate> findCertificatesByArtist_Id(Long id);
}
