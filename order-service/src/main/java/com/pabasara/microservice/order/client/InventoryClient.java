package com.pabasara.microservice.order.client;

import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class InventoryClient {

    Logger log = LoggerFactory.getLogger(InventoryClient.class);

    private final RestTemplate restTemplate;

    @Value("${inventory.url}/api/inventory")
    private String inventoryServiceUrl;

    public InventoryClient(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    @CircuitBreaker(name = "inventory", fallbackMethod = "fallbackMethod")
    public boolean isInStock(String skuCode, Integer quantity) {
        try {
            String url = UriComponentsBuilder
                    .fromUriString(inventoryServiceUrl)
                    .queryParam("skuCode", skuCode)
                    .queryParam("quantity", quantity)
                    .toUriString();

            Boolean response = restTemplate.getForObject(url, Boolean.class);
            return Boolean.TRUE.equals(response);

        } catch (Exception e) {
            System.out.println("Inventory service not available: " + e.getMessage());
            return false;
        }
    }

    public boolean fallbackMethod(String code, Integer quantity, Throwable throwable) {
        log.info("Cannot get inventory for skucode {}, failure reason: {}", code, throwable.getMessage());
        return false;
    }
}
