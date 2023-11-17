package store.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@CrossOrigin(origins = "*")
@RestController
public class ProductResource implements ProductController {

    @Autowired
    private ProductService productService;

    @Override
    public List<ProductOut> list() {
        return productService.list().stream().map(ProductParser::to).toList();
    }

    @Override
    public ProductOut get(@PathVariable(required = true) String id) {
        Product found = productService.find(id);
        return found == null ? null : ProductParser.to(found);
    }

    @Override
    public void delete(@PathVariable(required = true) String id) {
        productService.delete(id);
    }

    @Override
    public ResponseEntity<Object> create(@RequestBody ProductIn in) {
        // Padrao HateOAS
        return ResponseEntity.created(
                    ServletUriComponentsBuilder
                        .fromCurrentRequest()
                        .path("/{id}")
                        .buildAndExpand(productService.create(ProductParser.to(in)).id())
                        .toUri())
                    .build();
    }

}
