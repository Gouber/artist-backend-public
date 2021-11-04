package com.example.verisart.artistapp.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name="artist")
public class Artist {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    @Column
    private Long id;

    @Column
    private String name;

    @OneToMany(mappedBy = "artist")
    @JsonIgnore
    private Set<Certificate> certificateSet;

    public Long getId() {
        return id;
    }

    @Override
    public String toString() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Set<Certificate> getCertificateSet() {
        return certificateSet;
    }

    public void setCertificateSet(Set<Certificate> certificateSet) {
        this.certificateSet = certificateSet;
    }
}
