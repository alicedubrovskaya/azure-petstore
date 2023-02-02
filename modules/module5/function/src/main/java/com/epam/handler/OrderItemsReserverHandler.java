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
import org.springframework.cloud.function.adapter.azure.FunctionInvoker;

import java.util.Optional;

public class OrderItemsReserverHandler extends FunctionInvoker<OrderRequest, Response> {

    @FunctionName("/orders")
    public HttpResponseMessage execute(
        @HttpTrigger(name = "request", methods = {HttpMethod.POST}, authLevel = AuthorizationLevel.ANONYMOUS) HttpRequestMessage<Optional<OrderRequest>> request,
        ExecutionContext context) {
        OrderRequest updateCartRequest = request.getBody()
            .filter((u -> u.getId() != null))
            .orElseThrow(() -> new IllegalArgumentException("Input is empty or has bad format"));
        context.getLogger().info("Getting orders item request: " + updateCartRequest.getId());
        return request
            .createResponseBuilder(HttpStatus.OK)
            .body(handleRequest(updateCartRequest, context))
            .header("Content-Type", "application/json")
            .build();
    }
}
