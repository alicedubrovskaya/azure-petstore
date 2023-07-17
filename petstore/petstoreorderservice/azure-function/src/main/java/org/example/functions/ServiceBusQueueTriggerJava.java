package org.example.functions;

import com.azure.storage.blob.BlobClient;
import com.azure.storage.blob.BlobContainerClient;
import com.azure.storage.blob.BlobServiceClient;
import com.azure.storage.blob.BlobServiceClientBuilder;
import com.microsoft.azure.functions.annotation.*;
import com.microsoft.azure.functions.*;


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

    private static final String BLOB_CONTAINER_CONNECTION_STRING = "efaultEndpointsProtocol=https;AccountName=alisapetstoresa;AccountKey=BbdrjaDJJ4iW9b37+PztMmYvehQYqXwM7NteFkPgvwZ12cObAbiOj0ymeQ5sKDuvZjMXe7B0ufQU+AStjM44Tw==;EndpointSuffix=core.windows.net";
    private static final String BLOB_CONTAINER_NAME = "alisacontainer";

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
                .connectionString(BLOB_CONTAINER_CONNECTION_STRING)
                .buildClient();

        BlobContainerClient blobContainerClient = blobServiceClient.getBlobContainerClient(BLOB_CONTAINER_NAME);
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
                .uri(URI.create("https://alisalogicapp.azurewebsites.net:443/api/orderEmail/triggers/When_a_HTTP_request_is_received/invoke?api-version=2022-05-01&sp=%2Ftriggers%2FWhen_a_HTTP_request_is_received%2Frun&sv=1.0&sig=hnp9P2cy6DRWDn_ZFvWADPiTX0ERrZIva05u0OjF7GY"))
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