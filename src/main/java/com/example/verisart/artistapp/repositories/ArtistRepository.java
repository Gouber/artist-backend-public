package com.example.verisart.artistapp.repositories;

import com.example.verisart.artistapp.entities.Artist;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArtistRepository extends JpaRepository<Artist, Long> {


}
