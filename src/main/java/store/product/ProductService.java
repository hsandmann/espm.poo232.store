package store.product;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Spliterator;
import java.util.Spliterators;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {
    
    @Autowired
    private ProductRepository productRepository;

    public List<Product> list() {
        return StreamSupport.stream(productRepository.findAll().spliterator(), false)
            .collect(Collectors.toList())
            .stream().map(ProductModel::to).toList();
    }

    public Product find(String id) {
        return productRepository.findById(id).orElse(null).to();
    }

    public void delete(String id) {
        productRepository.deleteById(id);
    }

    public void create(Product in) {
        productRepository.save(new ProductModel(in));
    }

}
