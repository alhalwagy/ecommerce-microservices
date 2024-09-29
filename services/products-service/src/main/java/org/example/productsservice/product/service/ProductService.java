package org.example.productsservice.product.service;

import org.example.productsservice.product.dto.ProductPurchaseRequest;
import org.example.productsservice.product.dto.ProductPurchaseResponse;
import org.example.productsservice.product.dto.ProductRequest;
import org.example.productsservice.product.dto.ProductResponse;

import java.util.List;

public interface ProductService {
    /**
     * Creates a new product
     * @param productRequest
     * @return
     */
    Long createProduct(ProductRequest productRequest);

    /***
     * Find product by id and return id of it
     * @param id
     * @return
     */
    public ProductResponse findById(Long id);

    /**
     * Find all products
     * @return
     */
    public List<ProductResponse> findAll();

    /**
     * Purchase products in one transaction
     * @param productPurchaseRequests
     * @return
     */
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> productPurchaseRequests);
}
