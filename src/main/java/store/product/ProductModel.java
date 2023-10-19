package store.product;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@NoArgsConstructor
public class ProductModel {
    
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "price")
    private Double price;

    public ProductModel(Product o) {
        this.id = o.id();
        this.name = o.name();
        this.price = o.price();
    }

    public Product to() {
        return Product.builder()
            .id(this.id)
            .name(this.name)
            .price(this.price)
            .build();
    }

}
