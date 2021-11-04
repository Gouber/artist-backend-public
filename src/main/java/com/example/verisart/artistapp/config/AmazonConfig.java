package com.example.verisart.artistapp.config;

import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AmazonConfig {
    @Bean
    public AmazonS3 s3() {
        AWSCredentials awsCredentials =
                new BasicAWSCredentials("<insert_access_key_here>", "<insert_secret_key_here>");
        return AmazonS3ClientBuilder
                .standard()
                .withRegion("<insert_region_here>")
                .withCredentials(new AWSStaticCredentialsProvider(awsCredentials))
                .build();

    }
}