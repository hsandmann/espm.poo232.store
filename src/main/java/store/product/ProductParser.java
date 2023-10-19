package store.product;

public final class ProductParser {

    public static Product to(ProductIn in) {
        return Product.builder()
            .name(in.nome())
            .price(in.preco())
            .build();
    }

    public static ProductOut to(Product o) {
        return new ProductOut(o.id(), o.name(), o.price());
    }
    
}
