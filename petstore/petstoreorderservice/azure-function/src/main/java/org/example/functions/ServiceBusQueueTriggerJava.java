package org.example.functions;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;
import org.springframework.beans.factory.annotation.Value;


import java.io.FileWriter;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

/**
 * Azure Functions with Service Bus Trigger.
 */
public class ServiceBusQueueTriggerJava {
    /**
     * This function will be invoked when a new message is received at the Service Bus Queue.
     */

    @Value("${container.connection-string}")
    private String blobContainerConnectionString;
    @Value("${container.name}")
    private String blobContainerName;
    @Value("${email.uri}")
    private String uriEmail;

    @FunctionName("ServiceBusQueueTriggerJava")
    public void run(
            @ServiceBusQueueTrigger(
                    name = "message",
                    queueName = "queue",
                    connection = "myConnection") String message,
                    final ExecutionContext context
    ) {

        int count = 0;
        int maxTries = 3;
        while (true) {
            try {
                processMessage(message, context);
                break;
            } catch (Exception e) {
                count++;
                context.getLogger().info("Unable to upload to Blob storage, attempt: " + count);
                if (count == maxTries) {
                    sendRequest(message, context);
                    break;
                }
            }
        }
    }

    public void processMessage(String message, ExecutionContext context) {
        BlobServiceClient blobServiceClient = new BlobServiceClientBuilder()
                .connectionString(blobContainerConnectionString)
                .buildClient();

        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(blobContainerName);
        if (!blobContainerClient.exists()) {
            blobContainerClient.create();
        }

        String fileName = "order" + java.util.UUID.randomUUID() + ".json";
        BlobClient blobClient = blobContainerClient.getBlobClient(fileName);

        FileWriter writer;
        try {
            writer = new FileWriter(fileName, true);
            writer.write(message);
            writer.close();
        } catch (IOException ex) {
            context.getLogger().info(ex.getMessage());
        }

        context.getLogger().info("Uploading to Blob storage as blob:" + blobClient.getBlobUrl());
        blobClient.uploadFromFile(fileName);
    }

    public void sendRequest(String message, ExecutionContext context) {
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(uriEmail))
                .POST(HttpRequest.BodyPublishers.ofString(message))
                .build();
        try {
            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());
            context.getLogger().info("Request was sent");
        } catch (IOException | InterruptedException exception) {
            context.getLogger().info("Error while sending a request");
        }
    }
}