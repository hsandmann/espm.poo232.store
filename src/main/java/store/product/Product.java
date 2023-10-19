package store.product;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

@Getter @Setter @Builder @AllArgsConstructor @NoArgsConstructor
@Accessors(chain = true, fluent = true)
public class Product {

    private String id;
    private String name;
    private Double price;
    
}
