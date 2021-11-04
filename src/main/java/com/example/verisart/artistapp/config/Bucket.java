package com.example.verisart.artistapp.config;


public enum Bucket {
    ARTIST_BUCKET("<insert_bucket_name_here>");
    private final String bucketName;

    Bucket(String s) {
        bucketName = s;
    }

    public String getBucketName(){
        return bucketName;
    }
}
