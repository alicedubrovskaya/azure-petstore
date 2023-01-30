package com.epam.service;

import com.azure.core.util.BinaryData;
import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.epam.model.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CartStorageService {

    private final BlobContainerClient blobContainerClient;

    public CartStorageService(BlobContainerClient blobContainerClient) {
        this.blobContainerClient = blobContainerClient;
    }

    public void uploadItemToFile(String fileName, OrderRequest updateCartRequest) {
        BlobClient blobClient = blobContainerClient.getBlobClient(fileName + ".json");
        blobClient.upload(BinaryData.fromObject(updateCartRequest), true);
    }
}
