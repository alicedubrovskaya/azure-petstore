package com.epam.config;

import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.azure.storage.common.StorageSharedKeyCredential;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class StorageConfig {
    @Value("${spring.cloud.azure.storage.blob.account-name}")
    private String accountName;
    @Value("${spring.cloud.azure.storage.blob.account-key}")
    private String accountKey;
    @Value("${spring.cloud.azure.storage.blob.endpoint}")
    private String endpoint;
    @Value("${spring.cloud.azure.storage.blob.container-name}")
    private String containerName;

    @Bean
    public BlobServiceClient blobServiceClient() {
        return new BlobServiceClientBuilder()
            .credential(new StorageSharedKeyCredential(accountName, accountKey))
            .endpoint(endpoint)
            .buildClient();
    }

    @Bean
    public BlobContainerClient blobContainerClient(BlobServiceClient blobServiceClient) {
        return blobServiceClient.getBlobContainerClient(containerName);
    }
}
