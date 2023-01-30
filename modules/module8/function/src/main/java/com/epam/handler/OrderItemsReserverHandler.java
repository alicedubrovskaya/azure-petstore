package com.epam.handler;

import com.epam.model.Response;
import com.epam.model.OrderRequest;
import com.microsoft.azure.functions.ExecutionContext;
import com.microsoft.azure.functions.HttpMethod;
import com.microsoft.azure.functions.HttpRequestMessage;
import com.microsoft.azure.functions.HttpResponseMessage;
import com.microsoft.azure.functions.HttpStatus;
import com.microsoft.azure.functions.annotation.AuthorizationLevel;
import com.microsoft.azure.functions.annotation.FunctionName;
import com.microsoft.azure.functions.annotation.HttpTrigger;
import com.microsoft.azure.functions.annotation.ServiceBusQueueTrigger;
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

import java.util.Optional;

public class OrderItemsReserverHandler extends FunctionInvoker<OrderRequest, Response> {

    @FunctionName("orderReserver")
    public void execute(
        @ServiceBusQueueTrigger(name = "orders", queueName = "order-queue", connection = "AzureServiceBusConnection") OrderRequest orderRequest,
        ExecutionContext context) {
        context.getLogger().info("Getting orders item request: " + orderRequest);
        handleRequest(orderRequest, context);
    }
}
