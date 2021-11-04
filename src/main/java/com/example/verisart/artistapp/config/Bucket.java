package com.example.verisart.artistapp.config;


public enum Bucket {
    ARTIST_BUCKET("artist-storage-spring");
    private final String bucketName;

    Bucket(String s) {
        bucketName = s;
    }

    public String getBucketName(){
        return bucketName;
    }
}
