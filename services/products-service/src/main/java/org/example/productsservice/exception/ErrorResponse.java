package org.example.productsservice.exception;


import java.util.Map;

public record ErrorResponse(
        Map<String, String> errors
) {

}