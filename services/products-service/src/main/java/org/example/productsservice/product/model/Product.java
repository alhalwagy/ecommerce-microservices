package org.example.productsservice.product.model;


import jakarta.persistence.*;
import lombok.*;
import org.example.productsservice.Category.Category;

@Setter
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Product {
    @Id
    @GeneratedValue
    private Long id;
    private String name;
    private String description;
    private double availableQuantity;
    private double price;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
