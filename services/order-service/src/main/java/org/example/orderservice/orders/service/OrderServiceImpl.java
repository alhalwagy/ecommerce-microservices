package org.example.orderservice.orders.service;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.example.orderservice.customers.CustomerClient;
import org.example.orderservice.exception.BusinessException;
import org.example.orderservice.massageRequests.OrderConfirmation;
import org.example.orderservice.massageRequests.OrderProducer;
import org.example.orderservice.orderItems.dto.OrderItemRequest;
import org.example.orderservice.orderItems.service.OrderItemService;
import org.example.orderservice.orders.dao.OrderRepository;
import org.example.orderservice.orders.dto.OrderMapper;
import org.example.orderservice.orders.dto.OrderRequest;
import org.example.orderservice.orders.dto.OrderResponse;
import org.example.orderservice.payment.PaymentClient;
import org.example.orderservice.payment.dto.PaymentRequest;
import org.example.orderservice.products.ProductClient;
import org.example.orderservice.products.dto.PurchaseRequest;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class OrderServiceImpl implements OrderService {

    private final OrderRepository repository;
    private final OrderMapper mapper;
    private final CustomerClient customerClient;
    private final PaymentClient paymentClient;
    private final ProductClient productClient;
    private final OrderItemService orderItemService;
    private final OrderProducer orderProducer;

    @Transactional
    public Long createOrder(OrderRequest request) {
        // Retrieve customer by ID
        var customer = this.customerClient.findCustomerById(request.customerId())
                .orElseThrow(() -> new BusinessException("Cannot create order:: No customer exists with the provided ID"));
    System.out.println(customer);
        // Retrieve purchased products
        var purchasedProducts = productClient.purchaseProducts(request.products());

        // Create a new order
        var order = this.repository.save(mapper.toOrder(request));

        // Create Order Items from purchase orders
        for (PurchaseRequest purchaseRequest : request.products()) {
            orderItemService.createOrderItem(
                    new OrderItemRequest(
                            null,
                            order.getId(),
                            purchaseRequest.productId(),
                            purchaseRequest.quantity()
                    )
            );
        }

        // Create Payment request
        var paymentRequest = new PaymentRequest(
                request.amount(),
                request.paymentMethod(),
                order.getId(),
                order.getReference(),
                customer
        );

        // Request payment
        paymentClient.requestOrderPayment(paymentRequest);


        // Produce order confirmation into message broker
//        orderProducer.sendOrderConfirmation(
//                new OrderConfirmation(
//                        request.reference(),
//                        request.amount(),
//                        request.paymentMethod(),
//                        customer,
//                        purchasedProducts
//                )
//        );

        return order.getId();
    }

    /**
     * List All Orders
     * @return
     */
    public List<OrderResponse> findAllOrders() {
        return this.repository.findAll()
                .stream()
                .map(this.mapper::toOrderResponse)
                .collect(Collectors.toList());
    }

    /**
     * Get Order by ID
     * @param id
     * @return
     */
    public OrderResponse findById(Long id) {
        return this.repository.findById(id)
                .map(this.mapper::toOrderResponse)
                .orElseThrow(() -> new EntityNotFoundException(String.format("No order found with the provided ID: %d", id)));
    }
}
