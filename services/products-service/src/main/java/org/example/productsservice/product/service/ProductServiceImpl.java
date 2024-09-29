package org.example.productsservice.product.service;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.example.productsservice.exception.ProductPurchaseException;
import org.example.productsservice.product.dao.ProductRepository;
import org.example.productsservice.product.dto.*;
import org.example.productsservice.product.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService{

    @Autowired
    private final ProductMapper mapper;

    @Autowired
    private ProductRepository productRepository;


    @Override
    public List<ProductResponse> findAll() {
        return productRepository.findAll().stream().map(ProductMapper::toProductResponse).toList();
    }

    @Override
    public ProductResponse findById(Long id) {
        return productRepository.findById(id).map(ProductMapper::toProductResponse).orElse(null);
    }

    @Override
    public List<ProductPurchaseResponse> purchaseProducts(List<ProductPurchaseRequest> productPurchaseRequests) {
        var productIds = productPurchaseRequests
                .stream()
                .map(ProductPurchaseRequest::productId)
                .toList();
        var storedProducts = productRepository.findAllByIdInOrderById(productIds);
        if (productIds.size() != storedProducts.size()) {
            throw new ProductPurchaseException("One or more products does not exist");
        }
        var sortedRequest = productPurchaseRequests
                .stream()
                .sorted(Comparator.comparing(ProductPurchaseRequest::productId))
                .toList();
        var purchasedProducts = new ArrayList<ProductPurchaseResponse>(); // Specify the type here
        for (int i = 0; i < storedProducts.size(); i++) {
            var product = storedProducts.get(i);
            var productRequest = sortedRequest.get(i);
            if (product.getAvailableQuantity() < productRequest.quantity()) {
                throw new ProductPurchaseException("Insufficient stock quantity for product with ID:: " + productRequest.productId());
            }
            var newAvailableQuantity = product.getAvailableQuantity() - productRequest.quantity();
            product.setAvailableQuantity(newAvailableQuantity);
            productRepository.save(product);
            purchasedProducts.add(mapper.toproductPurchaseResponse(product, productRequest.quantity()));
        }

        return purchasedProducts;
    }


    @Override
    public Long createProduct(ProductRequest productRequest) {
        Product product = mapper.toProduct(productRequest);
        return  productRepository.save(product).getId();
    }


}
