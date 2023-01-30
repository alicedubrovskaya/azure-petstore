package com.epam.function;

import com.epam.model.Response;
import com.epam.model.OrderRequest;
import com.epam.service.CartStorageService;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

import java.util.function.Function;

@Component
public class OrderItemsReserverFunction implements Function<Mono<OrderRequest>, Mono<Response>> {

    private final CartStorageService cartStorageService;

    public OrderItemsReserverFunction(CartStorageService cartStorageService) {
        this.cartStorageService = cartStorageService;
    }

    @Override
    public Mono<Response> apply(Mono<OrderRequest> updateCartRequestMono) {
        return updateCartRequestMono
            .map(updateCartRequest -> {
                cartStorageService.uploadItemToFile(updateCartRequest.getId(), updateCartRequest);
                return new Response("Request has been processed");
            });
    }
}
