package org.example.orderservice.orderItems;
import jakarta.persistence.*;
import lombok.*;
import org.example.orderservice.orders.Order;

@AllArgsConstructor
@Builder
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "cusotmer-order-items")
public class OrderItem {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    private Long productId;
    private double quantity;
}
